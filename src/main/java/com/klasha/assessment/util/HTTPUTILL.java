package com.klasha.assessment.util;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HTTPUTILL {

    public String post(Object request, String url){
        String responseBody = null;
        try{
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
            responseBody =  responses.peekBody(Long.MAX_VALUE).string();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return responseBody;
    }
}
