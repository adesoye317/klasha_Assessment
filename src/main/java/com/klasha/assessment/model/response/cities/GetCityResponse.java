package com.klasha.assessment.model.response.cities;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetCityResponse{

	@JsonProperty("msg")
	private String msg;

	@JsonProperty("data")
	private List<String> data;

	@JsonProperty("error")
	private boolean error;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(List<String> data){
		this.data = data;
	}

	public List<String> getData(){
		return data;
	}

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
		return error;
	}

	@Override
 	public String toString(){
		return 
			"GetCityResponse{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",error = '" + error + '\'' + 
			"}";
		}
}