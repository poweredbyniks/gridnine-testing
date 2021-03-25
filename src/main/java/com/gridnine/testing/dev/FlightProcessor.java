package com.gridnine.testing.dev;

import com.gridnine.testing.dev.predicates.FlightPredicate;
import com.gridnine.testing.initial.Flight;

import java.util.List;
import java.util.stream.Stream;

public class FlightProcessor {
    private final List<FlightPredicate> flightPredicates;

    public FlightProcessor(List<FlightPredicate> flightPredicates) {
        this.flightPredicates = flightPredicates;
    }

    public Stream<Flight> applyPredicates(Stream<Flight> flightStream) {
        for (FlightPredicate flightPredicate : flightPredicates) {
            flightStream = flightStream.filter(flightPredicate);
        }
        return flightStream;
    }
}
