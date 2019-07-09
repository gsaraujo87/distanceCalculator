package com.example.distancecalculator.controller;

import com.example.distancecalculator.domain.Distance;
import com.example.distancecalculator.domain.UnitLength;
import com.example.distancecalculator.dto.DistanceCalculatorDto;
import com.example.distancecalculator.dto.DistanceCalculatorDtoConverter;
import com.example.distancecalculator.dto.DistanceDto;
import com.example.distancecalculator.service.DistanceCalculatorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class DistanceCalculatorController {

    @Autowired
    private DistanceCalculatorService distanceCalculatorService;

    @Autowired
    private DistanceCalculatorDtoConverter distanceCalculatorDtoConverter;

    @PostMapping(value = "/sum", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DistanceDto sumDistances(@RequestBody @Valid DistanceCalculatorDto payload, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid parameters");
        }

        List<Distance> distanceList = distanceCalculatorDtoConverter.getDistanceListFromDto(payload);
        UnitLength unitLength = distanceCalculatorDtoConverter.getUnitLengthFromMetric(payload.getResultMetric());
        return distanceCalculatorDtoConverter.getDistanceDtoFromDistance(distanceCalculatorService.sumDistances(distanceList, unitLength));
    }
}
