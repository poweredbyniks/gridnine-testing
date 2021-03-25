package com.gridnine.testing;

import com.gridnine.testing.dev.predicates.SegmentsRightOrderPredicate;
import com.gridnine.testing.initial.Flight;
import com.gridnine.testing.initial.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SegmentsRightOrderPredicateTest {
    @Test
    public void predicateNullTest() {
        assertThrows(NullPointerException.class, () -> {
            SegmentsRightOrderPredicate predicate = new SegmentsRightOrderPredicate();
            predicate.test(null);
        });
    }

    @Test
    public void predicateTrueTest() {
        Segment segment = new Segment(LocalDateTime.now(),
                LocalDateTime.now().plusHours(1L));

        List<Segment> segmentList = new ArrayList<>();
        segmentList.add(segment);

        Flight flight = new Flight(segmentList);

        SegmentsRightOrderPredicate predicate = new SegmentsRightOrderPredicate();
        assertTrue(predicate.test(flight));
    }

    @Test
    public void predicateFalseTest() {
        Segment segment = new Segment(LocalDateTime.now(),
                LocalDateTime.now().minusHours(1L));

        List<Segment> segmentList = new ArrayList<>();
        segmentList.add(segment);

        Flight flight = new Flight(segmentList);

        SegmentsRightOrderPredicate predicate = new SegmentsRightOrderPredicate();
        assertFalse(predicate.test(flight));
    }
}
