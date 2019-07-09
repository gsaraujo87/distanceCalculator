package com.example.distancecalculator.controller;

import com.example.distancecalculator.domain.Meters;
import com.example.distancecalculator.domain.Yards;
import com.example.distancecalculator.dto.DistanceCalculatorDto;
import com.example.distancecalculator.dto.DistanceCalculatorDtoConverter;
import com.example.distancecalculator.dto.DistanceDto;
import com.example.distancecalculator.service.DistanceCalculatorService;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

public class DistanceCalculatorControllerTest {

    private DistanceCalculatorController distanceCalculatorController;

    @Mock
    private BindingResult bindingResult;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        DistanceCalculatorService service = new DistanceCalculatorService();
        DistanceCalculatorDtoConverter converter = new DistanceCalculatorDtoConverter();
        distanceCalculatorController = new DistanceCalculatorController(service, converter);
    }

    @Test
    public void shouldSumDistances() {

        DistanceCalculatorDto payload = new DistanceCalculatorDto();
        payload.setDistances(Stream.of(
                DistanceDto.builder()
                        .distanceNumber(100d)
                        .unitLength(Yards.YARDS)
                        .build(),
                DistanceDto.builder()
                        .distanceNumber(50d)
                        .unitLength(Meters.METERS)
                        .build())
                .collect(Collectors.toList()));
        payload.setResultMetric("yards");
        given(bindingResult.hasErrors()).willReturn(false);

        DistanceDto resultedDistanceDto = distanceCalculatorController.sumDistances(payload, bindingResult);

        assertThat(resultedDistanceDto.getUnitLength()).isEqualTo(Yards.YARDS);
        assertThat(resultedDistanceDto.getDistanceNumber()).isEqualTo(154.68);
    }

    @Test
    public void shouldReturnBadRequestWhenPayloadIsInvalid() {

        given(bindingResult.hasErrors()).willReturn(true);

        assertThatThrownBy(() -> distanceCalculatorController.sumDistances(Mockito.mock(DistanceCalculatorDto.class), bindingResult))
                .isInstanceOf(ResponseStatusException.class);
    }
}
