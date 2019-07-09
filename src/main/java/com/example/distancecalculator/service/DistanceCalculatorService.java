package com.example.distancecalculator.service;

import com.example.distancecalculator.domain.Distance;
import com.example.distancecalculator.domain.UnitLength;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistanceCalculatorService {

    public Distance sumDistances(List<Distance> distanceList, UnitLength unitLength) {
        return Distance.builder().distanceNumber(distanceList.stream().mapToDouble(distance -> unitLength.convert(distance).doubleValue()).sum())
                .unitLength(unitLength).build();
    }
}
