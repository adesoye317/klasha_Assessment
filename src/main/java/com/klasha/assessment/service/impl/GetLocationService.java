package com.klasha.assessment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klasha.assessment.model.request.get_details.GetDetailsRequest;
import com.klasha.assessment.model.response.location.GetLocationResponse;
import com.klasha.assessment.util.HTTPUTILL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;


/**
 * @author adegokeadesoye
 * @apiNote This service is for Getting Location
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GetLocationService {

    private final HTTPUTILL httputill;
    private final Environment env;

    public GetLocationResponse getLocation(GetDetailsRequest request){

        GetLocationResponse response = null;
        try {

            //Making the HTTP Call
            String url = env.getProperty("get.location.details.url");
            log.info("THE URL CALLED FOR GET LOCATION DETAILS::{}", url);
            String responseBody = httputill.post(request, url);
            log.info("THE RESPONSE::{}", responseBody);
            ObjectMapper objectMapper = new ObjectMapper();
            response = objectMapper.readValue(responseBody, GetLocationResponse.class);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info(Arrays.toString(e.getStackTrace()).replaceAll(", ", "\n"));
        }
        return response;
    }
}
