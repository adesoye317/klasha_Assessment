package com.klasha.assessment.controller;

import com.klasha.assessment.model.request.get_details.GetDetailsRequest;
import com.klasha.assessment.model.response.get_capital.GetCapitalResponse;
import com.klasha.assessment.model.response.get_cities.GetTopCitiesResponse;
import com.klasha.assessment.model.response.get_currency.GetCurrencyResponse;
import com.klasha.assessment.model.response.get_population.GetPopulationResponse;
import com.klasha.assessment.service.GetTopCitiesInterface;
import com.klasha.assessment.service.impl.GetCapitalDetailService;
import com.klasha.assessment.service.impl.GetCurrencyDetailService;
import com.klasha.assessment.service.impl.GetPopulationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("klasha")
public class MainController {

    private final GetTopCitiesInterface getTopCitiesInterface;
    private final GetCurrencyDetailService getPopulationService;

    @GetMapping("/get-top/cities-desc")
    public ResponseEntity<GetTopCitiesResponse> getTopCities(@RequestParam int numberCities, @RequestParam String country){
        log.info("The request Param numberCities {} and country {}", numberCities, country);
        return  ResponseEntity.ok(getTopCitiesInterface.getTopCities(numberCities, country));
    }
    @PostMapping("/test")
    public ResponseEntity<GetCurrencyResponse> getTop(@RequestBody GetDetailsRequest request){
        //log.info("The request Param numberCities {} and country {}", numberCities, country);
        return  ResponseEntity.ok(getPopulationService.getCurrency(request));
    }
}
