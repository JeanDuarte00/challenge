package com.pnia.challenge.domain;

import com.pnia.challenge.domain.entities.occurrenceCounterImpl.Technology;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TechnologySpec {

    @Test
    @DisplayName("Should return a integer after updated value")
    void shouldReturnDifferentValueWhenUpdated() {
        Technology counter = new Technology();
        counter.addByOne();
        Assert.assertEquals(1, counter.getValue());
    }
}
