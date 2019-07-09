package com.example.distancecalculator.dto;

import com.example.distancecalculator.domain.Distance;
import com.example.distancecalculator.domain.Meters;
import com.example.distancecalculator.domain.UnitLength;
import com.example.distancecalculator.domain.Yards;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class DistanceCalculatorDtoConverterTest {

    private DistanceCalculatorDtoConverter distanceCalculatorDtoConverter;

    @BeforeMethod
    public void setUp() {
        distanceCalculatorDtoConverter = new DistanceCalculatorDtoConverter();
    }


    @Test
    public void shouldGetDistanceListFromDto() {
        DistanceCalculatorDto dto = new DistanceCalculatorDto();
        dto.setDistances(Stream.of(
                DistanceDto.builder()
                        .distanceNumber(100d)
                        .unitLength(Meters.METERS)
                        .build(),
                DistanceDto.builder()
                        .distanceNumber(50d)
                        .unitLength(Yards.YARDS)
                        .build())
                .collect(Collectors.toList()));

        List<Distance> resultList = distanceCalculatorDtoConverter.getDistanceListFromDto(dto);

        assertThat(resultList).hasSize(2);
        assertThat(resultList.get(0).getDistanceNumber()).isEqualTo(100d);
        assertThat(resultList.get(0).getUnitLength().getName()).isEqualTo(Meters.METERS);
        assertThat(resultList.get(1).getDistanceNumber()).isEqualTo(50d);
        assertThat(resultList.get(1).getUnitLength().getName()).isEqualTo(Yards.YARDS);
    }

    @DataProvider(name = "unitLengthUnitInput")
    public Object[][] unitLengthUnitInput() {
        return new Object[][]{
                {"meters", Meters.class, Meters.METERS},
                {"METERS", Meters.class, Meters.METERS},
                {"MeTeRs", Meters.class, Meters.METERS},
                {"yards", Yards.class, Yards.YARDS},
                {"YARDS", Yards.class, Yards.YARDS},
                {"YaRdS", Yards.class, Yards.YARDS},
        };
    }

    @Test(dataProvider = "unitLengthUnitInput")
    public void shouldGetUnitLengthFromMetric(String unitLengthInput, Class unitLengthClass, String unitLengthExpected) {

        UnitLength resultUnitLength = distanceCalculatorDtoConverter.getUnitLengthFromMetric(unitLengthInput);

        assertThat(resultUnitLength).isInstanceOf(unitLengthClass);
        assertThat(resultUnitLength.getName()).isEqualTo(unitLengthExpected);
    }

    @Test
    public void shouldGetDistanceDtoFromDistanceWhenMeters() {

        Distance distance = Distance.builder().distanceNumber(100).unitLength(new Meters()).build();

        DistanceDto distanceDto = distanceCalculatorDtoConverter.getDistanceDtoFromDistance(distance);

        assertThat(distanceDto.getDistanceNumber()).isEqualTo(100);
        assertThat(distanceDto.getUnitLength()).isEqualTo(Meters.METERS);
    }

    @Test
    public void shouldGetDistanceDtoFromDistanceWhenYards() {

        Distance distance = Distance.builder().distanceNumber(100).unitLength(new Yards()).build();

        DistanceDto distanceDto = distanceCalculatorDtoConverter.getDistanceDtoFromDistance(distance);

        assertThat(distanceDto.getDistanceNumber()).isEqualTo(100);
        assertThat(distanceDto.getUnitLength()).isEqualTo(Yards.YARDS);
    }
}
