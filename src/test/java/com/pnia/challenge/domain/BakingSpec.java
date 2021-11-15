package com.pnia.challenge.domain;

import com.pnia.challenge.domain.entities.occurrenceCounterImpl.Baking;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BakingSpec {

    @Test
    @DisplayName("Should return a integer after updated value")
    void shouldReturnDifferentValueWhenUpdated() {
        Baking counter = new Baking();
        counter.addByOne();
        Assert.assertEquals(1, counter.getValue());
    }
}
