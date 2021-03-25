package com.gridnine.testing;

import com.gridnine.testing.dev.FlightProcessor;
import com.gridnine.testing.dev.SegmentSorter;
import com.gridnine.testing.dev.predicates.AfterCurrentTimePredicate;
import com.gridnine.testing.dev.predicates.FlightPredicate;
import com.gridnine.testing.dev.predicates.SegmentsRightOrderPredicate;
import com.gridnine.testing.dev.predicates.TimeOnEarthPredicate;
import com.gridnine.testing.dev.validation.Validator;
import com.gridnine.testing.initial.Flight;
import com.gridnine.testing.initial.FlightBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        /**
         * Exclusion of flights from the test set
         * if departure before the current time
         */
        System.out.println("Task 1:");
        List<Flight> testSet = FlightBuilder.createFlights();

        SegmentSorter segmentSorter = new SegmentSorter();
        Validator validator = new Validator();
        FlightPredicate predicate1 = new AfterCurrentTimePredicate();

        List<FlightPredicate> flightPredicates = new ArrayList<>();
        flightPredicates.add(predicate1);
        flightPredicates.add(validator);

        FlightProcessor processor = new FlightProcessor(flightPredicates);

        Stream<Flight> pipeline1 = testSet.stream();
        pipeline1 = segmentSorter.sortSegment(pipeline1);
        processor.applyPredicates(pipeline1)
                .forEach(System.out::println);

        /**
         * Exclusion of flights from the test set
         * if there are segments with an arrival date earlier
         * than the departure date
         */
        System.out.println("Task 2:");
        FlightPredicate predicate2 = new SegmentsRightOrderPredicate();

        flightPredicates = new ArrayList<>();
        flightPredicates.add(predicate2);
        flightPredicates.add(validator);

        processor = new FlightProcessor(flightPredicates);

        Stream<Flight> pipeline2 = testSet.stream();
        pipeline2 = segmentSorter.sortSegment(pipeline2);
        processor.applyPredicates(pipeline2)
                .forEach(System.out::println);

        /**
         * Exclusion of flights from the test set
         * if the total time spent on the ground
         * exceeds two hours
         */
        System.out.println("Task 3:");
        FlightPredicate predicate3 = new TimeOnEarthPredicate();

        flightPredicates = new ArrayList<>();
        flightPredicates.add(predicate3);
        flightPredicates.add(validator);

        processor = new FlightProcessor(flightPredicates);

        Stream<Flight> pipeline3 = testSet.stream();
        pipeline3 = segmentSorter.sortSegment(pipeline3);
        processor.applyPredicates(pipeline3)
                .forEach(System.out::println);
    }
}
