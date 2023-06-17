package com.klasha.assessment.model.get_cities.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;


public class GetTopCitiesResponse{

	@JsonProperty("msg")
	private String msg;

	@JsonProperty("data")
	private List<DataItem> data;
	@JsonProperty("error")
	private boolean error;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<DataItem> getData() {
		return data;
	}

	public void setData(List<DataItem> data) {
		this.data = data;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}


	@Override
	public String toString() {
		return "GetTopCitiesResponse{" +
				"msg='" + msg + '\'' +
				", data=" + data +
				", error=" + error +
				'}';
	}
}