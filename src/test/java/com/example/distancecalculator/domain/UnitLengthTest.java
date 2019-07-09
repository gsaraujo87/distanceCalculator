package com.example.distancecalculator.domain;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class UnitLengthTest {

    private UnitLength meterUnitLength;
    private UnitLength yardsUnitLength;

    @BeforeMethod
    public void setUp() {
        meterUnitLength = new Meters();
        yardsUnitLength = new Yards();
    }

    @Test
    public void shouldConvertMetersToYards() {

        Distance distanceInMeters = Distance.builder().distanceNumber(100).unitLength(new Meters()).build();

        BigDecimal resultInYards = yardsUnitLength.convert(distanceInMeters);

        assertThat(resultInYards.doubleValue()).isEqualTo(109.36);
    }

    @Test
    public void shouldConvertYardsToMeters() {

        Distance distanceInMeters = Distance.builder().distanceNumber(100).unitLength(new Yards()).build();

        BigDecimal resultInYards = meterUnitLength.convert(distanceInMeters);

        assertThat(resultInYards.doubleValue()).isEqualTo(91.44);
    }
}
