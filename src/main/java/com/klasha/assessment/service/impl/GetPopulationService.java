package com.klasha.assessment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.klasha.assessment.model.request.get_details.GetDetailsRequest;
import com.klasha.assessment.model.response.get_population.GetPopulationResponse;
import com.klasha.assessment.util.HTTPUTILL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author adegokeadesoye
 * @apiNote This service is for Getting Population
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GetPopulationService {

    private final Environment env;
    private final HTTPUTILL httputill;

    public GetPopulationResponse getPopulation(GetDetailsRequest request){

        GetPopulationResponse response = null;
        try {

            //Making the HTTP Call
            String url = env.getProperty("get.population.details.url");
            log.info("THE URL CALLED FOR GET POPULATION DETAILS::{}", url);
            String responseBody = httputill.post(request, url);
            log.info("THE RESPONSE::{}", responseBody);
            ObjectMapper objectMapper = new ObjectMapper();
            response = objectMapper.readValue(responseBody, GetPopulationResponse.class);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info(Arrays.toString(e.getStackTrace()).replaceAll(", ", "\n"));
        }
        return response;
    }
}
