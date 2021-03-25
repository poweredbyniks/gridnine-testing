package com.gridnine.testing;


import com.gridnine.testing.dev.FlightProcessor;
import com.gridnine.testing.dev.predicates.AfterCurrentTimePredicate;
import com.gridnine.testing.dev.predicates.FlightPredicate;
import com.gridnine.testing.dev.predicates.SegmentsRightOrderPredicate;
import com.gridnine.testing.dev.predicates.TimeOnEarthPredicate;
import com.gridnine.testing.initial.Flight;
import com.gridnine.testing.initial.FlightBuilder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlightProcessorTest {

    @Test
    public void processorNullTest() {
        assertThrows(NullPointerException.class, () -> {
            FlightProcessor processor = new FlightProcessor(null);
            processor.applyPredicates(FlightBuilder.createFlights().stream());
        });
    }

    @Test
    public void notApplyPredicatesTest() {
        List<Flight> unfilteredFlightList = FlightBuilder.createFlights();

        List<FlightPredicate> predicateList = new ArrayList<>();

        FlightProcessor flightProcessor = new FlightProcessor(predicateList);
        List<Flight> filteredFlightList = flightProcessor.applyPredicates(unfilteredFlightList.stream())
                .collect(Collectors.toList());

        assertEquals(unfilteredFlightList, filteredFlightList);
    }

    @Test
    public void applyPredicatesTest() {
        List<Flight> unfilteredFlightList = FlightBuilder.createFlights();

        AfterCurrentTimePredicate predicate1 = new AfterCurrentTimePredicate();
        SegmentsRightOrderPredicate predicate2 = new SegmentsRightOrderPredicate();
        TimeOnEarthPredicate predicate3 = new TimeOnEarthPredicate();

        List<FlightPredicate> predicateList = new ArrayList<>();
        predicateList.add(predicate1);
        predicateList.add(predicate2);
        predicateList.add(predicate3);

        FlightProcessor flightProcessor = new FlightProcessor(predicateList);
        List<Flight> filteredFlightList = flightProcessor.applyPredicates(unfilteredFlightList.stream())
                .collect(Collectors.toList());

        assertFalse(filteredFlightList.contains(unfilteredFlightList.get(2)));
        assertFalse(filteredFlightList.contains(unfilteredFlightList.get(3)));
        assertFalse(filteredFlightList.contains(unfilteredFlightList.get(4)));
        assertFalse(filteredFlightList.contains(unfilteredFlightList.get(5)));

        assertTrue(filteredFlightList.contains(unfilteredFlightList.get(0)));
        assertTrue(filteredFlightList.contains(unfilteredFlightList.get(1)));
    }

}

