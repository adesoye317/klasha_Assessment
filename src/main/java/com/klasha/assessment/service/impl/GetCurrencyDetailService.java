package com.klasha.assessment.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.klasha.assessment.model.request.get_details.GetDetailsRequest;
import com.klasha.assessment.model.response.get_currency.GetCurrencyResponse;
import com.klasha.assessment.util.HTTPUTILL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author adegokeadesoye
 * @apiNote This service is for Getting currency
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GetCurrencyDetailService {

    private final Environment env;
    private final HTTPUTILL httputill;

    public GetCurrencyResponse getCurrency(GetDetailsRequest request){

        GetCurrencyResponse response = null;
        try {

            //Making the HTTP Call
            String url = env.getProperty("get.currency.details.url");
            log.info("THE URL CALLED FOR GET CAPITAL DETAILS::{}", url);
            String responseBody = httputill.post(request, url);
            log.info("THE RESPONSE::{}", responseBody);
            ObjectMapper objectMapper = new ObjectMapper();
            response = objectMapper.readValue(responseBody, GetCurrencyResponse.class);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info(Arrays.toString(e.getStackTrace()).replaceAll(", ", "\n"));
        }
        return response;
    }
}
