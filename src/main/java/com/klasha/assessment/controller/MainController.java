package com.klasha.assessment.controller;

import com.klasha.assessment.model.request.get_cities.GetCityandStateRequest;
import com.klasha.assessment.model.request.get_currency.GetCurrencyRequest;
import com.klasha.assessment.model.request.get_details.GetDetailsRequest;
import com.klasha.assessment.model.response.get_capital.GetCapitalResponse;
import com.klasha.assessment.model.response.get_cities.GetTopCitiesResponse;
import com.klasha.assessment.model.response.get_currency.GetCountryCurrencyResponse;
import com.klasha.assessment.model.response.get_currency.GetCurrencyResponse;
import com.klasha.assessment.model.response.get_details.GetDetailsResponse;
import com.klasha.assessment.model.response.get_population.GetPopulationResponse;
import com.klasha.assessment.model.response.location.GetLocationResponse;
import com.klasha.assessment.service.GetTopCitiesInterface;
import com.klasha.assessment.service.impl.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("klasha")
public class MainController {

    private final GetTopCitiesInterface getTopCitiesInterface;
    private final GetDetailsService getDetailsService;
    private final GetCitiesandStateService getCitiesandStateService;

    private final GetCountryCurrency getCountryCurrency;

    @GetMapping("/get-top/cities-desc")
    public ResponseEntity<GetTopCitiesResponse> getTopCities(@RequestParam int numberCities, @RequestParam String country){
        log.info("The request Param numberCities {} and country {}", numberCities, country);
        return  ResponseEntity.ok(getTopCitiesInterface.getTopCities(numberCities, country));
    }
    @PostMapping("/getdetails/country")
    public Object getDetailsByCountry(@RequestBody GetDetailsRequest request){
        return  CompletableFuture.completedFuture(getDetailsService.getDetails(request));
    }

    @PostMapping("/getdetails/state-cities")
    public Object getStateandCities(@RequestBody GetDetailsRequest request){
        return  CompletableFuture.completedFuture(getCitiesandStateService.getDetails(request));
    }

    @PostMapping("/country/currency")
    public ResponseEntity<GetCountryCurrencyResponse> getCurrency(@RequestBody GetCurrencyRequest request){
        return  ResponseEntity.ok(getCountryCurrency.countryCurrency(request));
    }
}
