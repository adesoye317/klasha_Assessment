package com.klasha.assessment.model.response.get_details;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Location {

    private int longitude;

    private int latitude;
}
