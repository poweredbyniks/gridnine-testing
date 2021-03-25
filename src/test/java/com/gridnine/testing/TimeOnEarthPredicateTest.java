package com.gridnine.testing;

import com.gridnine.testing.dev.predicates.TimeOnEarthPredicate;
import com.gridnine.testing.initial.Flight;
import com.gridnine.testing.initial.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TimeOnEarthPredicateTest {

    @Test
    public void predicateNullTest() {
        assertThrows(NullPointerException.class, () -> {
            TimeOnEarthPredicate predicate = new TimeOnEarthPredicate();
            predicate.test(null);
        });
    }

    @Test
    public void predicateTrueTest() {
        Segment segment1 = new Segment(LocalDateTime.now(),
                LocalDateTime.now().plusHours(1L));
        Segment segment2 = new Segment(LocalDateTime.now().plusHours(2L),
                LocalDateTime.now().plusHours(3L));
        Segment segment3 = new Segment(LocalDateTime.now().plusHours(4L),
                LocalDateTime.now().plusHours(5L));

        List<Segment> segmentList = new ArrayList<>();
        segmentList.add(segment1);
        segmentList.add(segment2);
        segmentList.add(segment3);

        Flight flight = new Flight(segmentList);

        TimeOnEarthPredicate predicate = new TimeOnEarthPredicate();
        assertTrue(predicate.test(flight));
    }

    @Test
    public void predicateFalseTest() {
        Segment segment1 = new Segment(LocalDateTime.now(),
                LocalDateTime.now().plusHours(1L));
        Segment segment2 = new Segment(LocalDateTime.now().plusHours(2L),
                LocalDateTime.now().plusHours(3L));
        Segment segment3 = new Segment(LocalDateTime.now().plusHours(4L).plusMinutes(1L),
                LocalDateTime.now().plusHours(5L));

        List<Segment> segmentList = new ArrayList<>();
        segmentList.add(segment1);
        segmentList.add(segment2);
        segmentList.add(segment3);

        Flight f = new Flight(segmentList);

        TimeOnEarthPredicate predicate = new TimeOnEarthPredicate();
        assertFalse(predicate.test(f));
    }
}

