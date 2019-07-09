package com.example.distancecalculator.domain;

import java.math.BigDecimal;

public class Meters extends UnitLength {

    private static final BigDecimal YARDS_TO_METERS = BigDecimal.valueOf(0.9144);
    public static final String METERS = "meters";

    public Meters() {
        this.setName(METERS);
    }

    @Override
    public BigDecimal convert(Distance distance) {
        if (distance.getUnitLength() instanceof Yards) {
            return BigDecimal.valueOf(distance.getDistanceNumber()).multiply(YARDS_TO_METERS);
        } else {
            return BigDecimal.valueOf(distance.getDistanceNumber());
        }
    }
}
