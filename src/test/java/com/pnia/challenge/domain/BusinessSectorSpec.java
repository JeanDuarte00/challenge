package com.pnia.challenge.domain;

import com.pnia.challenge.domain.entities.BusinessSector;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BusinessSectorSpec {

    @Test
    @DisplayName("Sectors can't have null values, by this we don't need to check for null pointers leaving it's value as empty string")
    void shouldNotAcceptNullParameters() {
        BusinessSector sector = new BusinessSector(null, null);
        Assert.assertEquals(sector.getNumber(), "");
        Assert.assertEquals(sector.getSector(), "");
    }

}
