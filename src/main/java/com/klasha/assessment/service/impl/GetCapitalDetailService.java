package com.klasha.assessment.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.klasha.assessment.model.request.get_details.GetDetailsRequest;
import com.klasha.assessment.model.response.get_capital.GetCapitalResponse;
import com.klasha.assessment.util.HTTPUTILL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author adegokeadesoye
 * @apiNote This service is for Getting Capital details
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class GetCapitalDetailService {

    private final Environment env;
    private final HTTPUTILL httputill;

    public GetCapitalResponse getCapital(GetDetailsRequest request){

        GetCapitalResponse response = null;
        try {

            //Making the HTTP Call
            String url = env.getProperty("get.capital.details.url");
            log.info("THE URL CALLED FOR GET CAPITAL DETAILS::{}", url);
            String payload = new Gson().toJson(request);


            log.info("THE GET CAPITAL REQUEST::{}", payload);
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
            response = objectMapper.readValue(responseBody, GetCapitalResponse.class);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info(Arrays.toString(e.getStackTrace()).replaceAll(", ", "\n"));
        }
        return response;
    }
}
