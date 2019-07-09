package com.example.distancecalculator.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class DistanceDto {

    @NotNull
    Double distanceNumber;

    @NotNull
    String unitLength;
}
