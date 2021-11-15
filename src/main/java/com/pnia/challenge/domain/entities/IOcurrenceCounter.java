package com.pnia.challenge.domain.entities;

import org.springframework.stereotype.Component;

@Component
public interface IOcurrenceCounter {

    default int counterAdd(int increase) {
        return (increase + 1);
    }

    int getValue ();

}
