package com.example.distancecalculator.service;

import com.example.distancecalculator.domain.Distance;
import com.example.distancecalculator.domain.Meters;
import com.example.distancecalculator.domain.Yards;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class DistanceCalculatorServiceTest {

    private DistanceCalculatorService distanceCalculatorService;

    @BeforeMethod
    public void setUp() {
        distanceCalculatorService = new DistanceCalculatorService();
    }


    @Test
    public void shouldSumDistancesBothMetersWithResultMeters() {
        List<Distance> distanceList = Stream.of(
                Distance.builder()
                        .distanceNumber(100d)
                        .unitLength(new Meters())
                        .build(),
                Distance.builder()
                        .distanceNumber(50d)
                        .unitLength(new Meters())
                        .build())
                .collect(Collectors.toList());

        Distance resultedDistance = distanceCalculatorService.sumDistances(distanceList, new Meters());

        assertThat(resultedDistance.getUnitLength()).isInstanceOf(Meters.class);
        assertThat(resultedDistance.getDistanceNumber()).isEqualTo(150);
    }

    @Test
    public void shouldSumDistancesBothMetersWithResultYards() {
        List<Distance> distanceList = Stream.of(
                Distance.builder()
                        .distanceNumber(100d)
                        .unitLength(new Meters())
                        .build(),
                Distance.builder()
                        .distanceNumber(50d)
                        .unitLength(new Meters())
                        .build())
                .collect(Collectors.toList());

        Distance resultedDistance = distanceCalculatorService.sumDistances(distanceList, new Yards());

        assertThat(resultedDistance.getUnitLength()).isInstanceOf(Yards.class);
        assertThat(resultedDistance.getDistanceNumber()).isEqualTo(164.04);
    }

    @Test
    public void shouldSumDistancesBothYardsWithResultYards() {
        List<Distance> distanceList = Stream.of(
                Distance.builder()
                        .distanceNumber(100d)
                        .unitLength(new Yards())
                        .build(),
                Distance.builder()
                        .distanceNumber(50d)
                        .unitLength(new Yards())
                        .build())
                .collect(Collectors.toList());

        Distance resultedDistance = distanceCalculatorService.sumDistances(distanceList, new Yards());

        assertThat(resultedDistance.getUnitLength()).isInstanceOf(Yards.class);
        assertThat(resultedDistance.getDistanceNumber()).isEqualTo(150);
    }

    @Test
    public void shouldSumDistancesBothYardsWithResultMeters() {
        List<Distance> distanceList = Stream.of(
                Distance.builder()
                        .distanceNumber(100d)
                        .unitLength(new Yards())
                        .build(),
                Distance.builder()
                        .distanceNumber(50d)
                        .unitLength(new Yards())
                        .build())
                .collect(Collectors.toList());

        Distance resultedDistance = distanceCalculatorService.sumDistances(distanceList, new Meters());

        assertThat(resultedDistance.getUnitLength()).isInstanceOf(Meters.class);
        assertThat(resultedDistance.getDistanceNumber()).isEqualTo(137.16);
    }

    @Test
    public void shouldSumDistancesDifferentUnitsWithResultMeters() {
        List<Distance> distanceList = Stream.of(
                Distance.builder()
                        .distanceNumber(100d)
                        .unitLength(new Yards())
                        .build(),
                Distance.builder()
                        .distanceNumber(50d)
                        .unitLength(new Meters())
                        .build())
                .collect(Collectors.toList());

        Distance resultedDistance = distanceCalculatorService.sumDistances(distanceList, new Meters());

        assertThat(resultedDistance.getUnitLength()).isInstanceOf(Meters.class);
        assertThat(resultedDistance.getDistanceNumber()).isEqualTo(141.44);
    }

    @Test
    public void shouldSumDistancesDifferentUnitsWithResultYards() {
        List<Distance> distanceList = Stream.of(
                Distance.builder()
                        .distanceNumber(100d)
                        .unitLength(new Yards())
                        .build(),
                Distance.builder()
                        .distanceNumber(50d)
                        .unitLength(new Meters())
                        .build())
                .collect(Collectors.toList());

        Distance resultedDistance = distanceCalculatorService.sumDistances(distanceList, new Yards());

        assertThat(resultedDistance.getUnitLength()).isInstanceOf(Yards.class);
        assertThat(resultedDistance.getDistanceNumber()).isEqualTo(154.68);
    }
}