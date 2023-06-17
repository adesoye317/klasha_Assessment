package com.klasha.assessment.model.request.get_cities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetTopCitiesRequest{

	@JsonProperty("country")
	private String country;

	@JsonProperty("limit")
	private int limit;

	@JsonProperty("orderBy")
	private String orderBy;

	@JsonProperty("order")
	private String order;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setLimit(int limit){
		this.limit = limit;
	}

	public int getLimit(){
		return limit;
	}

	public void setOrderBy(String orderBy){
		this.orderBy = orderBy;
	}

	public String getOrderBy(){
		return orderBy;
	}

	public void setOrder(String order){
		this.order = order;
	}

	public String getOrder(){
		return order;
	}

	@Override
 	public String toString(){
		return 
			"GetTopCitiesRequest{" + 
			"country = '" + country + '\'' + 
			",limit = '" + limit + '\'' + 
			",orderBy = '" + orderBy + '\'' + 
			",order = '" + order + '\'' + 
			"}";
		}
}