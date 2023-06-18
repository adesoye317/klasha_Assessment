package com.klasha.assessment.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.klasha.assessment.model.request.get_cities.GetCityandStateRequest;

import com.klasha.assessment.model.response.cities.GetCityResponse;

import com.klasha.assessment.util.HTTPUTILL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;



/**
 * @author adegokeadesoye
 * @apiNote This service is for Getting cities
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GetCityService {

    private final Environment env;
    private final HTTPUTILL httputill;

    public GetCityResponse getCity(GetCityandStateRequest request) throws IOException {

        GetCityResponse response = null;
        try {

            //Making the HTTP Call
            String url = env.getProperty("get.city.details.url");
            log.info("THE URL CALLED FOR GET CITY DETAILS::{}", url);
            String responseBody = httputill.post(request, url);
            log.info("THE RESPONSE::{}", responseBody);
            ObjectMapper objectMapper = new ObjectMapper();
            response = objectMapper.readValue(responseBody, GetCityResponse.class);
        } catch (Exception e) {
            throw new IOException(e);
        }
        return response;
    }
}
