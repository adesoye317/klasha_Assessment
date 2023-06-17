package com.klasha.assessment.model.response.get_currency;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data{

	@JsonProperty("name")
	private String name;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("iso2")
	private String iso2;

	@JsonProperty("iso3")
	private String iso3;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCurrency(String currency){
		this.currency = currency;
	}

	public String getCurrency(){
		return currency;
	}

	public void setIso2(String iso2){
		this.iso2 = iso2;
	}

	public String getIso2(){
		return iso2;
	}

	public void setIso3(String iso3){
		this.iso3 = iso3;
	}

	public String getIso3(){
		return iso3;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"name = '" + name + '\'' + 
			",currency = '" + currency + '\'' + 
			",iso2 = '" + iso2 + '\'' + 
			",iso3 = '" + iso3 + '\'' + 
			"}";
		}
}