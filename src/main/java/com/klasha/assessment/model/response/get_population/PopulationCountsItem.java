package com.klasha.assessment.model.response.get_population;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PopulationCountsItem{

	@JsonProperty("year")
	private int year;

	@JsonProperty("value")
	private int value;

	public void setYear(int year){
		this.year = year;
	}

	public int getYear(){
		return year;
	}

	public void setValue(int value){
		this.value = value;
	}

	public int getValue(){
		return value;
	}

	@Override
 	public String toString(){
		return 
			"PopulationCountsItem{" + 
			"year = '" + year + '\'' + 
			",value = '" + value + '\'' + 
			"}";
		}
}