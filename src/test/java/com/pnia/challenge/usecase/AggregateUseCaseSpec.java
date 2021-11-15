package com.pnia.challenge.usecase;

import com.pnia.challenge.usecase.exception.AggregationException;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AggregateUseCaseSpec {

    @Autowired
    IAggregate aggregate;

    @Mock
    IAggregate mockAggregate;


    @Test
    @DisplayName("Based on the moked data, map should have two keys")
    public void shouldBeMapWithTowKeys() throws AggregationException {
        Map<String, Map<String, String>> resp = aggregate.aggregate(mockNumberList());

        Assert.assertEquals(2, resp.size());
    }

    @Test
    @DisplayName("Based on the when something happens should return AggregationException")
    public void shouldReturnException() throws AggregationException {
        String message = "AggregationException";
        doThrow( new AggregationException(message)).when(mockAggregate).aggregate(anyList());

        Exception exception = assertThrows(AggregationException.class, () -> {
            Map<String, Map<String, String>> resp = mockAggregate.aggregate(mockNumberList());
        });

        Assert.assertEquals(message, exception.getMessage());
    }

    private List<String> mockNumberList() {

        List<String> list = new LinkedList<>();
        list.add("+1983248");
        list.add("+1382355");
        list.add("+1478192");
        list.add("+4439877");
        return list;
    }
}
