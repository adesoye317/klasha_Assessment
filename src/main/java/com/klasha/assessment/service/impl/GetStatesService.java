package com.klasha.assessment.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.klasha.assessment.model.request.get_details.GetDetailsRequest;
import com.klasha.assessment.model.response.get_capital.GetCapitalResponse;
import com.klasha.assessment.model.response.states.GetStateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetStatesService {

    private final Environment env;

    public GetStateResponse getState(GetDetailsRequest request) throws IOException {

        GetStateResponse response = null;
        try {

            //Making the HTTP Call
            String url = env.getProperty("get.state.details.url");
            log.info("THE URL CALLED FOR GET STATE DETAILS::{}", url);
            String payload = new Gson().toJson(request);
            log.info("THE GET STATE REQUEST::{}", payload);
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
            response = objectMapper.readValue(responseBody, GetStateResponse.class);
        } catch (Exception e) {
            throw new IOException(e);
        }
        return response;
    }
}
