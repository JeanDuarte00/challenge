package com.pnia.challenge.domain.entities.occurrenceCounterImpl;

import com.pnia.challenge.domain.entities.IOcurrenceCounter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Qualifier("Technology")
public class Technology implements Serializable, IOcurrenceCounter {

    private int technology;

    public Technology() {
        technology = 0;
    }

    public void addByOne() {
        technology = counterAdd(technology);
    }

    @Override
    public int getValue() {
        return technology;
    }
}
