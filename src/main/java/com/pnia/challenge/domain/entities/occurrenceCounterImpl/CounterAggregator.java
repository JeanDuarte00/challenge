package com.pnia.challenge.domain.entities.occurrenceCounterImpl;

import com.pnia.challenge.domain.entities.IOcurrenceCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class CounterAggregator implements Serializable {

    private IOcurrenceCounter baking;
    private IOcurrenceCounter clothing;
    private IOcurrenceCounter technology;

    @Autowired
    public CounterAggregator(@Qualifier("Baking")IOcurrenceCounter baking, @Qualifier("Clothing")IOcurrenceCounter clothing, @Qualifier("Technology")IOcurrenceCounter technology) {
        this.baking = baking;
        this.clothing = clothing;
        this.technology = technology;
    }

    public IOcurrenceCounter getBaking() {
        return baking;
    }

    public IOcurrenceCounter getClothing() {
        return clothing;
    }

    public IOcurrenceCounter getTechnology() {
        return technology;
    }
}
