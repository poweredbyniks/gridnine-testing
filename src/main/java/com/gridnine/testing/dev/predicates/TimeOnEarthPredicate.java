package com.gridnine.testing.dev.predicates;

import com.gridnine.testing.initial.Flight;

import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;

public class TimeOnEarthPredicate implements FlightPredicate {
    @Override
    public boolean test(Flight flight) {
        long minutes = 0;
        for (int i = 0; i + 1 < flight.getSegments().size(); i++) {
            LocalDateTime arrDate = flight.getSegments()
                    .get(i)
                    .getArrivalDate();
            LocalDateTime depDate = flight.getSegments()
                    .get(i + 1)
                    .getDepartureDate();

            minutes += arrDate.until(depDate, ChronoUnit.MINUTES);
        }
        return minutes <= 120L;
    }
}
