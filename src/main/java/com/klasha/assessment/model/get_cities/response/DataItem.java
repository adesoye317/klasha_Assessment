package com.klasha.assessment.model.get_cities.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DataItem{

	@JsonProperty("country")
	private String country;

	@JsonProperty("city")
	private String city;

	@JsonProperty("populationCounts")
	private List<PopulationCountsItem> populationCounts;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<PopulationCountsItem> getPopulationCounts() {
		return populationCounts;
	}

	public void setPopulationCounts(List<PopulationCountsItem> populationCounts) {
		this.populationCounts = populationCounts;
	}

	@Override
	public String toString() {
		return "DataItem{" +
				"country='" + country + '\'' +
				", city='" + city + '\'' +
				", populationCounts=" + populationCounts +
				'}';
	}
}