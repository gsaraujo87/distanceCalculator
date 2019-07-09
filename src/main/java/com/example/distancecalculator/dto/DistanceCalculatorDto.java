package com.example.distancecalculator.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class DistanceCalculatorDto {

    @NotNull
    @Valid
    List<DistanceDto> distances;

    @NotNull
    String resultMetric;
}
