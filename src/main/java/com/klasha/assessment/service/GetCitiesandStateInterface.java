package com.klasha.assessment.service;

import com.klasha.assessment.model.request.get_details.GetDetailsRequest;
import com.klasha.assessment.model.response.city_state.GetStateandCitiesResponse;

import java.util.concurrent.CompletableFuture;

public interface GetCitiesandStateInterface {
    CompletableFuture<GetStateandCitiesResponse> getDetails(GetDetailsRequest request);
}
