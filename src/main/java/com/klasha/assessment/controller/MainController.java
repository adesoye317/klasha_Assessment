package com.klasha.assessment.controller;

import com.klasha.assessment.model.get_cities.response.GetTopCitiesResponse;
import com.klasha.assessment.service.GetTopCitiesInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MainController {

    private GetTopCitiesInterface getTopCitiesInterface;

    @GetMapping("/get-top/cities-desc")
    public ResponseEntity<GetTopCitiesResponse> getTopCities(@RequestParam String numberCities, @RequestParam String country){
        return  ResponseEntity.ok(getTopCitiesInterface.getTopCities(numberCities, country));
    }
}
