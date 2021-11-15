package com.pnia.challenge.domain.entities.occurrenceCounterImpl;

import com.pnia.challenge.domain.entities.IOcurrenceCounter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Qualifier("Clothing")
public class Clothing implements Serializable, IOcurrenceCounter {

    private int clothing;

    public Clothing() {
        clothing = 0;
    }

    public void addByOne() {
        clothing = counterAdd(clothing);
    }

    @Override
    public int getValue() {
        return clothing;
    }
}
