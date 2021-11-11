package com.pnia.challenge.infrastructure.entrypoint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@Api(value = "Aggregate")
public class AggregatorEntrypoint {

    @PostMapping("/aggregate")
    @ApiOperation(value = "Receives a list of numbers to aggregate")
    public ResponseEntity<?> aggregate(@RequestBody final List<String> numbers) {

        try {
            return ResponseEntity.ok(numbers); // todo: fazer chamada do service - usecase
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }

    }
}
