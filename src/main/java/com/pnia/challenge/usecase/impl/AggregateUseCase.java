package com.pnia.challenge.usecase.impl;

import com.pnia.challenge.domain.entities.BusinessSector;
import com.pnia.challenge.usecase.IAgrregate;
import com.pnia.challenge.usecase.exception.CommunicationException;

import java.util.List;
import java.util.Map;

public class AggregateUseCase implements IAgrregate {

    @Override
    public Map<String, BusinessSector> aggregate(List<String> numbers) throws CommunicationException {
        return null;
    }
}
