package com.klasha.assessment.model.response.exchange_rate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExchangeRateModelItem{

	@JsonProperty("targetCurrency")
	private String targetCurrency;

	@JsonProperty("rate")
	private String rate;

	@JsonProperty("sourceCurrency")
	private String sourceCurrency;

	public void setTargetCurrency(String targetCurrency){
		this.targetCurrency = targetCurrency;
	}

	public String getTargetCurrency(){
		return targetCurrency;
	}

	public void setRate(String rate){
		this.rate = rate;
	}

	public String getRate(){
		return rate;
	}

	public void setSourceCurrency(String sourceCurrency){
		this.sourceCurrency = sourceCurrency;
	}

	public String getSourceCurrency(){
		return sourceCurrency;
	}

	@Override
 	public String toString(){
		return 
			"ExchangeRateModelItem{" + 
			"targetCurrency = '" + targetCurrency + '\'' + 
			",rate = '" + rate + '\'' + 
			",sourceCurrency = '" + sourceCurrency + '\'' + 
			"}";
		}
}