package com.klasha.assessment.service.impl;

import com.klasha.assessment.model.request.get_cities.GetCityandStateRequest;
import com.klasha.assessment.model.request.get_details.GetDetailsRequest;
import com.klasha.assessment.model.response.cities.GetCityResponse;
import com.klasha.assessment.model.response.city_state.GetStateandCitiesResponse;
import com.klasha.assessment.model.response.city_state.StateandCittiesResponse;
import com.klasha.assessment.model.response.states.GetStateResponse;
import com.klasha.assessment.model.response.states.StatesItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetCitiesandStateService {

    private final GetStatesService getStatesService;
    private final GetCityService getCityService;

    @Async
    public CompletableFuture<GetStateandCitiesResponse> getDetails(GetDetailsRequest request){
        GetStateandCitiesResponse response = new GetStateandCitiesResponse();
        try {

            GetStateResponse stateResponse = getStatesService.getState(request);
            List<StatesItem> statesItems = stateResponse.getData().getStates();
            List<StateandCittiesResponse> stateandCittiesResponses = new ArrayList<>();

            for (int i = 0; i < statesItems.size(); i++){
                StateandCittiesResponse cittiesResponse = new StateandCittiesResponse();
                GetCityandStateRequest stateRequest = new GetCityandStateRequest();
                stateRequest.setCountry(request.getCountry());
                //Note running Lagos as Lagos State is not providing response
                // Therefore, I will be replacing Lagos state with "Lagos"
                stateRequest.setState(statesItems.get(i).getName().equalsIgnoreCase("Lagos State")
                        ? statesItems.get(i).getName().replace(" State", "") : statesItems.get(i).getName());
                GetCityResponse cityResponse = getCityService.getCity(stateRequest);

                cittiesResponse.setState(statesItems.get(i).getName());
                cittiesResponse.setCities(cityResponse.getData());
                stateandCittiesResponses.add(cittiesResponse);
            }
            response.setCountry(request.getCountry());
            response.setStateCities(stateandCittiesResponses);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return CompletableFuture.completedFuture(response);
    }
}
