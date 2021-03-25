package com.gridnine.testing;

import com.gridnine.testing.dev.validation.ValidationException;
import com.gridnine.testing.dev.validation.Validator;
import com.gridnine.testing.initial.Flight;
import com.gridnine.testing.initial.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {

    @Test
    public void validatorNullTest() {
        assertThrows(ValidationException.class, () -> {
            Validator validator = new Validator();
            validator.test(null);
        });
    }

    @Test
    public void nullInSegmentsValidatorTest() {
        assertThrows(ValidationException.class, () -> {
            List<Segment> segmentList = null;

            Flight flight = new Flight(segmentList);

            Validator validator = new Validator();
            validator.test(flight);
        });
    }

    @Test
    public void segmentNullValidatorTest() {
        assertThrows(ValidationException.class, () -> {
            Segment segment = null;

            List<Segment> segmentList = new ArrayList<>();
            segmentList.add(segment);

            Flight flight = new Flight(segmentList);

            Validator validator = new Validator();
            validator.test(flight);
        });
    }

    @Test
    public void validatorTest() {
        Segment segment = new Segment(LocalDateTime.now().minusHours(2L),
                LocalDateTime.now());

        List<Segment> segmentList = new ArrayList<>();
        segmentList.add(segment);

        Flight f = new Flight(segmentList);

        Validator validator = new Validator();
        assertTrue(validator.test(f));
    }
}

