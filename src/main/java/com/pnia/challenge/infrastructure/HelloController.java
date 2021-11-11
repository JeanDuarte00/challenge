package com.pnia.challenge.infrastructure;

import com.pnia.challenge.domain.entities.BusinessSector;
import com.pnia.challenge.domain.enuns.BusinessSectorType;
import com.pnia.challenge.infrastructure.HttpClient.TalkDeskBusinessSectorAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Tests")
@RestController
public class HelloController {

    @Autowired
    private TalkDeskBusinessSectorAPI endpoint;

    @GetMapping("/swagger")
    public String index() {
        return "http://localhost:8080/swagger-ui.html#/" ;
    }

    @GetMapping("/ping")
    public String healthCheck() {
        return "pong";
    }
    @GetMapping("/call/{number}")
    public BusinessSector callTDApi(@PathVariable("number") final String number) {
        String path = "${talkdesk.businessSector.api} + ${talkdesk.businessSector.endpoint.sector}";
        var resp = endpoint.getSector(number).getBody();
        return resp;
    }
}