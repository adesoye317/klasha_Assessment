package com.klasha.assessment.model.response.get_population;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Data{

	@JsonProperty("country")
	private String country;

	@JsonProperty("code")
	private String code;

	@JsonProperty("populationCounts")
	private List<PopulationCountsItem> populationCounts;

	@JsonProperty("iso3")
	private String iso3;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setPopulationCounts(List<PopulationCountsItem> populationCounts){
		this.populationCounts = populationCounts;
	}

	public List<PopulationCountsItem> getPopulationCounts(){
		return populationCounts;
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
			"country = '" + country + '\'' + 
			",code = '" + code + '\'' + 
			",populationCounts = '" + populationCounts + '\'' + 
			",iso3 = '" + iso3 + '\'' + 
			"}";
		}
}