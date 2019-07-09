package com.example.distancecalculator.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public abstract class UnitLength {
    private String name;

    public abstract BigDecimal convert(Distance distance);
}
