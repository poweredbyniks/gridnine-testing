package com.gridnine.testing;

import com.gridnine.testing.dev.predicates.AfterCurrentTimePredicate;
import com.gridnine.testing.initial.Flight;
import com.gridnine.testing.initial.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AfterCurrentTimePredicateTest {

    @Test
    public void predateNullTest() {
        assertThrows(NullPointerException.class, () -> {
            AfterCurrentTimePredicate predicate = new AfterCurrentTimePredicate();
            predicate.test(null);
        });
    }

    @Test
    public void predicateTrueTest() {
        Segment segment1 = new Segment(LocalDateTime.now().plusSeconds(1L),
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

        AfterCurrentTimePredicate predicate = new AfterCurrentTimePredicate();
        assertTrue(predicate.test(flight));
    }

    @Test
    public void predicateFalseTest() {
        Segment segment1 = new Segment(LocalDateTime.now().minusHours(1L),
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

        AfterCurrentTimePredicate predicate = new AfterCurrentTimePredicate();
        assertFalse(predicate.test(flight));
    }
}

