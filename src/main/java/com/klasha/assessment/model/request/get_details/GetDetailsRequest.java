package com.klasha.assessment.model.request.get_details;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetDetailsRequest{

	@JsonProperty("country")
	private String country;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	@Override
 	public String toString(){
		return 
			"GetDetailsRequest{" + 
			"country = '" + country + '\'' + 
			"}";
		}
}