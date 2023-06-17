package com.klasha.assessment.model.response.get_population;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetPopulationResponse{

	@JsonProperty("msg")
	private String msg;

	@JsonProperty("data")
	private Data data;

	@JsonProperty("error")
	private boolean error;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
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
			"GetPopulationResponse{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",error = '" + error + '\'' + 
			"}";
		}
}