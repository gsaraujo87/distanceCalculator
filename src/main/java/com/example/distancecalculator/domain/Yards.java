package com.example.distancecalculator.domain;

import java.math.BigDecimal;

public class Yards extends UnitLength {

    private static final BigDecimal METERS_TO_YARDS = BigDecimal.valueOf(1.0936);
    public static final String YARDS = "yards";

    public Yards() {
        this.setName(YARDS);
    }

    @Override
    public BigDecimal convert(Distance distance) {
        BigDecimal distanceValue = BigDecimal.valueOf(distance.getDistanceNumber());

        if (distance.getUnitLength() instanceof Meters) {
            return distanceValue.multiply(METERS_TO_YARDS);
        } else {
            return distanceValue;
        }
    }
}
