package com.klasha.assessment.model.response.states;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatesItem{

	@JsonProperty("name")
	private String name;

	@JsonProperty("state_code")
	private String stateCode;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setStateCode(String stateCode){
		this.stateCode = stateCode;
	}

	public String getStateCode(){
		return stateCode;
	}

	@Override
 	public String toString(){
		return 
			"StatesItem{" + 
			"name = '" + name + '\'' + 
			",state_code = '" + stateCode + '\'' + 
			"}";
		}
}