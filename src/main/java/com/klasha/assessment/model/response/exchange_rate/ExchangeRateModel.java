package com.klasha.assessment.model.response.exchange_rate;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExchangeRateModel{

	@JsonProperty("ExchangeRateModel")
	private List<ExchangeRateModelItem> exchangeRateModel;

	public void setExchangeRateModel(List<ExchangeRateModelItem> exchangeRateModel){
		this.exchangeRateModel = exchangeRateModel;
	}

	public List<ExchangeRateModelItem> getExchangeRateModel(){
		return exchangeRateModel;
	}

	@Override
 	public String toString(){
		return 
			"ExchangeRateModel{" + 
			"exchangeRateModel = '" + exchangeRateModel + '\'' + 
			"}";
		}
}