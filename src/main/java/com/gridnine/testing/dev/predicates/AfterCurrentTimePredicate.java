package com.gridnine.testing.dev.predicates;

import com.gridnine.testing.initial.Flight;

import java.time.LocalDateTime;

public class AfterCurrentTimePredicate implements FlightPredicate {

    @Override
    public boolean test(Flight flight) {
        LocalDateTime deppDate = flight.getSegments().get(0).getDepartureDate();
        return !deppDate.isBefore(LocalDateTime.now());
    }
}
