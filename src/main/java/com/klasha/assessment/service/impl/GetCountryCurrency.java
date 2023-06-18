package com.klasha.assessment.service.impl;


import com.klasha.assessment.model.request.get_currency.GetCurrencyRequest;
import com.klasha.assessment.model.request.get_details.GetDetailsRequest;
import com.klasha.assessment.model.response.get_currency.GetCountryCurrencyResponse;
import com.klasha.assessment.model.response.get_currency.GetCurrencyResponse;
import com.klasha.assessment.service.GetCountryCurrencyInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author adegokeadesoye
 * @apiNote This service is for Getting country currency
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GetCountryCurrency implements GetCountryCurrencyInterface {

    private final GetCurrencyDetailService getCurrencyDetailService;

    @Override
    public GetCountryCurrencyResponse countryCurrency(GetCurrencyRequest request){
        GetCountryCurrencyResponse finalResp = new GetCountryCurrencyResponse();

        try {
            GetDetailsRequest getDetailsRequest = new GetDetailsRequest();
            getDetailsRequest.setCountry(request.getCountry());
            GetCurrencyResponse response = getCurrencyDetailService.getCurrency(getDetailsRequest);
            finalResp.setCurrency(response.getData().getCurrency());
            finalResp.setCountry(request.getCountry());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("THE FINAL RESPONSE FOR GET COUNTRY CURRENCY::{}", finalResp);
        return finalResp;
    }
}
