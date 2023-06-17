package com.klasha.assessment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.klasha.assessment.model.request.get_details.GetDetailsRequest;
import com.klasha.assessment.model.response.get_population.GetPopulationResponse;
import com.klasha.assessment.model.response.location.GetLocationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetLocationService {

    private final Environment env;

    public GetLocationResponse getLocation(GetDetailsRequest request){

        GetLocationResponse response = null;
        try {

            //Making the HTTP Call
            String url = env.getProperty("get.location.details.url");
            log.info("THE URL CALLED FOR GET LOCATION DETAILS::{}", url);
            String payload = new Gson().toJson(request);
            log.info("THE GET LOCATION REQUEST::{}", payload);
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, payload);
            Request requestx = new Request.Builder()
                    .url(url)
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response responses = client.newCall(requestx).execute();
            String responseBody =  responses.peekBody(Long.MAX_VALUE).string();

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
