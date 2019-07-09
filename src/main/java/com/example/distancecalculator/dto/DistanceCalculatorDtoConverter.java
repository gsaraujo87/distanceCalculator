package com.example.distancecalculator.dto;

import com.example.distancecalculator.domain.Distance;
import com.example.distancecalculator.domain.Meters;
import com.example.distancecalculator.domain.UnitLength;
import com.example.distancecalculator.domain.Yards;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DistanceCalculatorDtoConverter {

    public List<Distance> getDistanceListFromDto(DistanceCalculatorDto dto) {
        return dto.getDistances().stream().map(distance ->
                Distance.builder().distanceNumber(distance.getDistanceNumber())
                        .unitLength(getUnitLengthFromMetric(distance.getUnitLength())).build()
        ).collect(Collectors.toList());
    }

    public UnitLength getUnitLengthFromMetric(String unitLength) {
        if ("meters".compareToIgnoreCase(unitLength) == 0) {
            return new Meters();
        } else if("yards".compareToIgnoreCase(unitLength) == 0){
            return new Yards();
        } else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "unit length can only be meters or yards");
        }
    }

    public DistanceDto getDistanceDtoFromDistance(Distance distance) {
        return DistanceDto.builder().distanceNumber(distance.getDistanceNumber())
                .unitLength(distance.getUnitLength().getName())
                .build();
    }
}
