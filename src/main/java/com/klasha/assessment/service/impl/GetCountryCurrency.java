package com.klasha.assessment.service.impl;


import com.klasha.assessment.model.request.get_currency.GetCurrencyRequest;
import com.klasha.assessment.model.request.get_details.GetDetailsRequest;
import com.klasha.assessment.model.response.get_currency.GetCountryCurrencyResponse;
import com.klasha.assessment.model.response.get_currency.GetCurrencyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetCountryCurrency {

    private final GetCurrencyDetailService getCurrencyDetailService;

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
        return finalResp;
    }
}
