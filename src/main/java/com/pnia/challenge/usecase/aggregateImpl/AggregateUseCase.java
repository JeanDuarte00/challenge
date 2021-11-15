package com.pnia.challenge.usecase.aggregateImpl;

import com.pnia.challenge.domain.entities.BusinessSector;
import com.pnia.challenge.domain.entities.occurrenceCounterImpl.Baking;
import com.pnia.challenge.domain.entities.occurrenceCounterImpl.Clothing;
import com.pnia.challenge.domain.entities.occurrenceCounterImpl.CounterAggregator;
import com.pnia.challenge.domain.entities.occurrenceCounterImpl.Technology;
import com.pnia.challenge.domain.enuns.BusinessSectorType;
import com.pnia.challenge.infrastructure.httpclient.TalkDeskBusinessSectorAPI;
import com.pnia.challenge.usecase.IAggregate;
import com.pnia.challenge.usecase.exception.AggregationException;
import com.pnia.challenge.usecase.prefixReaderImpl.FilePrefixReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class AggregateUseCase implements IAggregate {

    private static Logger logger = LoggerFactory.getLogger(AggregateUseCase.class);

    private final String regexNumber;
    private final TalkDeskBusinessSectorAPI sectorAPI;

    private int minQuantityToReturn;

    @Autowired
    FilePrefixReader prefixReader;

    @Autowired
    public AggregateUseCase(TalkDeskBusinessSectorAPI api, @Value("${talkdesk.regex.number}") String regex, @Value("${talkdesk.keynumber.minquantitytosenddatainresponse}") int minValue) {
        sectorAPI = api;
        regexNumber = regex;
        minQuantityToReturn = minValue;
    }

    @Override
    public Map<String, Map<String,String>> aggregate(List<String> numbers) throws AggregationException {
        try{
            Map<String, Set<BusinessSector>> prefixPerSector = new HashMap<>();

            Set<BusinessSector> sectors = getSectorsNumber(numbers);

            mapPrefixToSectors(prefixPerSector, sectors);

            Map<String, CounterAggregator> resp = calculatePrefixSectors(prefixPerSector);

            return getAggregateResponse(resp);

        }catch (Exception ex) {
            throw new AggregationException(ex.getMessage(), ex);
        }

    }

    private void mapPrefixToSectors(Map<String, Set<BusinessSector>> prefixPerSector, Set<BusinessSector> sectors) {
        sectors.forEach((sector) -> {
            String prefix = prefixReader.getExistingPrefix(sector.getNumber());
            if (!prefix.isBlank()) {
                if( !prefixPerSector.containsKey(prefix) ){
                    prefixPerSector.put(prefix, new HashSet<>());
                }
                prefixPerSector.get(prefix).add(sector);
            }
        });
    }

    private Map<String, Map<String,String>> getAggregateResponse(Map<String, CounterAggregator> aggregates) {
        Map<String, Map<String,String>> aggregateResponse = new HashMap<>();

        aggregates.forEach((prefix, counter)->{
            if( !aggregateResponse.containsKey(prefix) ){
                aggregateResponse.put(prefix, new HashMap<>());
            }

            setDataOnAggregateResponse(aggregateResponse, counter, prefix);
        });
        return aggregateResponse;
    }

    private void setDataOnAggregateResponse(Map<String, Map<String,String>> aggregateResponse, CounterAggregator counter, String prefix) {
        if (counter.getBaking().getValue() >= minQuantityToReturn)
            aggregateResponse.get(prefix).put(counter.getBaking().getClass().getSimpleName(), String.valueOf(counter.getBaking().getValue()));

        if (counter.getClothing().getValue() >= minQuantityToReturn)
            aggregateResponse.get(prefix).put(counter.getClothing().getClass().getSimpleName(), String.valueOf(counter.getClothing().getValue()));

        if (counter.getTechnology().getValue() >= minQuantityToReturn)
            aggregateResponse.get(prefix).put(counter.getTechnology().getClass().getSimpleName(), String.valueOf(counter.getTechnology().getValue()));
    }

    private Map<String, CounterAggregator> calculatePrefixSectors(Map<String, Set<BusinessSector>> sectorsByPrefix){
        Map<String, CounterAggregator> result = new HashMap<>();

        sectorsByPrefix.forEach((prefix, sector) -> {
            CounterAggregator counterAggregator = getSectorOccurrences(sector);
            result.put(prefix, counterAggregator);
        });

        return result;
    }

    private CounterAggregator getSectorOccurrences(Set<BusinessSector> numbers) {

        Baking banking = new Baking();
        Clothing clothing = new Clothing();
        Technology technology = new Technology();

        numbers.forEach((number) -> {
            String type = number.getSector();

            if( type.equals(BusinessSectorType.BANKING.toString()) )
                banking.addByOne();

            if( type.equals(BusinessSectorType.CLOTHING.toString()) )
                clothing.addByOne();

            if( type.equals(BusinessSectorType.TECHNOLOGY.toString()) )
                technology.addByOne();

        });

        return new CounterAggregator(banking, clothing, technology);
    }

    private Set<BusinessSector> getSectorsNumber(List<String> numbers) {

        Set<BusinessSector> sectorsResponse = new HashSet<>();

         numbers.parallelStream()
                .forEach((number) -> {
                    try{
                        ResponseEntity<BusinessSector> response = sectorAPI.getSector(number);

                        if (response.getStatusCode().is2xxSuccessful())
                            sectorsResponse.add(response.getBody());

                    } catch (Exception ex){
                        logger.error(ex.toString());
                    }
         });

        return sectorsResponse;
    }

}
