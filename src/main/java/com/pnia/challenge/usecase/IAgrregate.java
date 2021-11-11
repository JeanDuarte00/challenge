package com.pnia.challenge.usecase;

import com.pnia.challenge.domain.entities.BusinessSector;
import com.pnia.challenge.usecase.exception.CommunicationException;

import java.util.List;
import java.util.Map;

public interface IAgrregate {

    public Map<String, BusinessSector> aggregate(final List<String> numbers) throws CommunicationException;

}
