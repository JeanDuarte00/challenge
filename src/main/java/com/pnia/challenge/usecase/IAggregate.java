package com.pnia.challenge.usecase;

import com.pnia.challenge.usecase.exception.AggregationException;

import java.util.List;
import java.util.Map;

public interface IAggregate {

    Map<String, Map<String,String>> aggregate(final List<String> numbers) throws AggregationException;

}
