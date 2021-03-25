package com.gridnine.testing.dev;

import com.gridnine.testing.initial.Flight;
import com.gridnine.testing.initial.Segment;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

public class SegmentSorter {

    public Stream<Flight> sortSegment(Stream<Flight> flightStream) {
        return flightStream.filter(Objects::nonNull).map(flight -> {
            flight.getSegments()
                    .sort(Comparator.comparing(Segment::getDepartureDate));
            return flight;
        });
    }
}
