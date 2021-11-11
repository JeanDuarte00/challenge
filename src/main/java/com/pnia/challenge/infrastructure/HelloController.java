package com.pnia.challenge.infrastructure;

import com.pnia.challenge.domain.enuns.BusinessSectorType;
import com.pnia.challenge.infrastructure.HttpClient.TalkDeskBusinessSectorAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Question")
@RestController
public class HelloController {

    private TalkDeskBusinessSectorAPI endpoint;

    @GetMapping("/")
    @ApiOperation(value = "Mostra lista de questões")
    public String index() {
        var resp = "health check => http://localhost:8080/swagger-ui.html#/";//endpoint.getSector("1");
        return "Greetings from Spring Boot! : " + resp ;
    }

}