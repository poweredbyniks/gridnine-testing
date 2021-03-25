package com.gridnine.testing;

import com.gridnine.testing.dev.SegmentSorter;
import com.gridnine.testing.initial.Flight;
import com.gridnine.testing.initial.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SegmentSorterTest {

    @Test
    public void sortSegmentNullArgumentTest() {
        assertThrows(NullPointerException.class, () -> {
            SegmentSorter segmentSorter = new SegmentSorter();
            List<Flight> flights = segmentSorter.sortSegment(null)
                    .collect(Collectors.toList());
        });
    }

    @Test
    public void emptyStreamSortSegmentTest() {
        List<Flight> flights = new ArrayList<>();
        SegmentSorter segmentSorter = new SegmentSorter();
        flights = segmentSorter.sortSegment(flights.stream())
                .collect(Collectors.toList());

        assertTrue(flights.isEmpty());
    }

    @Test
    public void oneElementStreamSortSegmentTest() {
        Segment segment1 = new Segment(LocalDateTime.now(), LocalDateTime.now());
        List<Segment> segmentList = new ArrayList<>();
        segmentList.add(segment1);

        Flight flight = new Flight(segmentList);
        List<Flight> flights = new ArrayList<>();
        flights.add(flight);

        SegmentSorter segmentSorter = new SegmentSorter();
        flights = segmentSorter.sortSegment(flights.stream())
                .collect(Collectors.toList());

        assertTrue(flights.get(0).getSegments().contains(segment1));
    }

    @Test
    public void sortSegmentTest() {
        Segment segment1 = new Segment(LocalDateTime.now(), LocalDateTime.now());
        Segment segment2 = new Segment(LocalDateTime.now().minusHours(5L),
                LocalDateTime.now().minusHours(3L));
        Segment segment3 = new Segment(LocalDateTime.now().plusHours(2L),
                LocalDateTime.now().plusHours(3L));
        Segment segment4 = new Segment(LocalDateTime.now().plusHours(7L),
                LocalDateTime.now().plusHours(9L));

        List<Segment> segmentList = new ArrayList<>();
        segmentList.add(segment1);
        segmentList.add(segment4);
        segmentList.add(segment2);
        segmentList.add(segment3);

        Flight flight = new Flight(segmentList);
        List<Flight> flights = new ArrayList<>();
        flights.add(flight);

        SegmentSorter segmentSorter = new SegmentSorter();
        flights = segmentSorter.sortSegment(flights.stream())
                .collect(Collectors.toList());

        assertEquals(segment1, flights.get(0).getSegments().get(1));
        assertEquals(segment2, flights.get(0).getSegments().get(0));
        assertEquals(segment3, flights.get(0).getSegments().get(2));
        assertEquals(segment4, flights.get(0).getSegments().get(3));
    }
}

