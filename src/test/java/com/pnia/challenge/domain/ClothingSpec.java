package com.pnia.challenge.domain;

import com.pnia.challenge.domain.entities.occurrenceCounterImpl.Clothing;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClothingSpec {

    @Test
    @DisplayName("Should return a integer after updated value")
    void shouldReturnDifferentValueWhenUpdated() {
        Clothing counter = new Clothing();
        counter.addByOne();
        Assert.assertEquals(1, counter.getValue());
    }
}
