package com.klasha.assessment.service;


import com.klasha.assessment.model.request.get_currency.GetCurrencyRequest;
import com.klasha.assessment.model.response.get_currency.GetCountryCurrencyResponse;

public interface GetCountryCurrencyInterface {
    GetCountryCurrencyResponse countryCurrency(GetCurrencyRequest request);
    Object exchangeRate(GetCurrencyRequest request);
}
