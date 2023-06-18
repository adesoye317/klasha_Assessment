package com.klasha.assessment.service;

import com.klasha.assessment.model.request.get_details.GetDetailsRequest;
import com.klasha.assessment.model.response.get_details.GetDetailsResponse;

import java.util.concurrent.CompletableFuture;

public interface GetDetailInterface {
    CompletableFuture<GetDetailsResponse> getDetails(GetDetailsRequest request);
}
