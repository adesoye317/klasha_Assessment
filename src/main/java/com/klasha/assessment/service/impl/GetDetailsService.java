package com.klasha.assessment.service.impl;

import com.klasha.assessment.model.request.get_details.GetDetailsRequest;
import com.klasha.assessment.model.response.get_capital.GetCapitalResponse;
import com.klasha.assessment.model.response.get_currency.GetCurrencyResponse;
import com.klasha.assessment.model.response.get_details.GetDetailsResponse;
import com.klasha.assessment.model.response.get_details.Location;
import com.klasha.assessment.model.response.get_population.GetPopulationResponse;
import com.klasha.assessment.model.response.location.GetLocationResponse;
import com.klasha.assessment.service.GetDetailInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


/**
 * @author adegokeadesoye
 * @apiNote This service is for Getting Details like location, currency, population and capital of a country
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GetDetailsService implements GetDetailInterface {

    private final GetLocationService getLocationService;
    private final GetCurrencyDetailService getCurrencyDetailService;
    private final GetPopulationService getPopulationService;
    private final GetCapitalDetailService getCapitalDetailService;

    @Override
    public CompletableFuture<GetDetailsResponse> getDetails(GetDetailsRequest request){
        GetDetailsResponse detailsResponse = null;

        try {
            //Calling GetLocation Service

                GetLocationResponse locationResponse = getLocationService.getLocation(request);
                GetCurrencyResponse currencyResponse = getCurrencyDetailService.getCurrency(request);
                GetPopulationResponse populationResponse = getPopulationService.getPopulation(request);
                GetCapitalResponse capitalResponse = getCapitalDetailService.getCapital(request);
                Location location = new Location();

                detailsResponse = new GetDetailsResponse();
                detailsResponse.setCurrency(currencyResponse.getData().getCurrency());
                detailsResponse.setCapital(capitalResponse.getData().getCapital());
                detailsResponse.setISO2(capitalResponse.getData().getIso2());
                detailsResponse.setISO3(capitalResponse.getData().getIso3());

                location.setLatitude(locationResponse.getData().getLat());
                location.setLongitude(locationResponse.getData().getJsonMemberLong());
                detailsResponse.setLocation(location);
                detailsResponse.setPopulation(populationResponse.getData().getPopulationCounts());
                log.info("THE FINAL RESPONSE FOR DETAIL::{}", detailsResponse);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return CompletableFuture.completedFuture(detailsResponse);
    }

}
