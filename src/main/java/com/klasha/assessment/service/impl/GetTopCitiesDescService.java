package com.klasha.assessment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.klasha.assessment.model.get_cities.request.GetTopCitiesRequest;
import com.klasha.assessment.model.get_cities.response.GetTopCitiesResponse;
import com.klasha.assessment.service.GetTopCitiesInterface;
import com.klasha.assessment.util.http.HttpHeader;
import com.klasha.assessment.util.http.HttpResponse;
import com.klasha.assessment.util.http.HttpUtil;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author adegokeadesoye
 * @apiNote This service is for Getting top 10 cities population
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class GetTopCitiesDescService implements GetTopCitiesInterface {

    private final Environment env;
    private final HttpUtil httpUtil;

    @Override
    public GetTopCitiesResponse getTopCities(String numberCities, String country){

        GetTopCitiesResponse response = null;
        try {
            //Creating an Instance of the Get Population Request
            GetTopCitiesRequest request = new GetTopCitiesRequest();
            request.setCountry(country);
            request.setLimit(Integer.parseInt(env.getProperty("population.limit")));
            request.setOrder(env.getProperty("population.order"));
            request.setOrderBy(env.getProperty("population.orderby"));

            //Making the HTTP Call
            String url = env.getProperty("get.population.cities.url");
            log.info("THE URL CALLED FOR GET POPULATION CITIES::{}", url);
            String payload = new Gson().toJson(request);
            log.info("THE GET CITIES REQUEST::{}", payload);

            List<HttpHeader> headers = new ArrayList<>();
            HttpResponse post = httpUtil.post(headers, url, 2000, "application/json", payload);
            String responseBody = post.getBody();
            ObjectMapper objectMapper = new ObjectMapper();

            response = objectMapper.readValue(responseBody, GetTopCitiesResponse.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

}
