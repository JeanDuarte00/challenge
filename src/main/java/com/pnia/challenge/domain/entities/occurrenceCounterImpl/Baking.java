package com.pnia.challenge.domain.entities.occurrenceCounterImpl;

import com.pnia.challenge.domain.entities.IOcurrenceCounter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Qualifier("Baking")
public class Baking implements Serializable, IOcurrenceCounter {

    private int banking;

    public Baking() {
        banking = 0;
    }

    public void addByOne() {
        banking = counterAdd(banking);
    }

    @Override
    public int getValue() {
        return banking;
    }
}
