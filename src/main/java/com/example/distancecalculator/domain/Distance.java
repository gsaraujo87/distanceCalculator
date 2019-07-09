package com.example.distancecalculator.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Distance {
    private double distanceNumber;
    private UnitLength unitLength;
}
