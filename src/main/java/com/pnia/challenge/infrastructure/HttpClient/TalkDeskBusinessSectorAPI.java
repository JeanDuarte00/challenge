package com.pnia.challenge.infrastructure.HttpClient;

import com.pnia.challenge.domain.entities.BusinessSector;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(url = "${talkdesk.businessSector.api} + ${talkdesk.businessSector.endpoint.sector}", name = "sector")
public interface TalkDeskBusinessSectorAPI {

    @GetMapping("/{number}")
    ResponseEntity<BusinessSector> getSector(@PathVariable("number") final String number);

}
