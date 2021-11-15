package com.pnia.challenge.infrastructure.entrypoint;

import com.pnia.challenge.usecase.IAggregate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.http.HTTPException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@Api(value = "Aggregate")
public class AggregatorEntrypoint {
    private static Logger logger = LoggerFactory.getLogger(AggregatorEntrypoint.class);

    @Autowired
    private IAggregate aggregateLogic;

    @PostMapping(value = "/aggregate", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Receives a list of numbers to aggregate")
    public ResponseEntity<?> aggregate(@RequestBody final List<String> numbers) {
        try {
            int requestHashBasedOnTime = LocalDateTime.now().hashCode();
            logger.debug("New request ("+requestHashBasedOnTime+") has been made at: " + LocalDateTime.now());
            ResponseEntity<Map<String, Map<String, String>>> response = ResponseEntity.ok(aggregateLogic.aggregate(numbers));
            logger.debug("Request ("+requestHashBasedOnTime+")is done: " + LocalDateTime.now());
            return response;
        } catch (HTTPException ex) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("You have sent us a invalid input");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }

    }
}
