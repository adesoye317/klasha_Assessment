package com.klasha.assessment.model.response.location;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data{

	@JsonProperty("name")
	private String name;

	@JsonProperty("iso2")
	private String iso2;

	@JsonProperty("long")
	private int jsonMemberLong;

	@JsonProperty("lat")
	private int lat;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setIso2(String iso2){
		this.iso2 = iso2;
	}

	public String getIso2(){
		return iso2;
	}

	public void setJsonMemberLong(int jsonMemberLong){
		this.jsonMemberLong = jsonMemberLong;
	}

	public int getJsonMemberLong(){
		return jsonMemberLong;
	}

	public void setLat(int lat){
		this.lat = lat;
	}

	public int getLat(){
		return lat;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"name = '" + name + '\'' + 
			",iso2 = '" + iso2 + '\'' + 
			",long = '" + jsonMemberLong + '\'' + 
			",lat = '" + lat + '\'' + 
			"}";
		}
}