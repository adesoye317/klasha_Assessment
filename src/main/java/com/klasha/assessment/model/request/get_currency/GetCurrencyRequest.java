package com.klasha.assessment.model.request.get_currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;


public class GetCurrencyRequest {

    @JsonProperty("country")
    private String country;
    @JsonProperty("monetryAmount")
    private BigDecimal monetryAmount;
    @JsonProperty("targetCurrency")
    private String targetCurrency;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public BigDecimal getMonetryAmount() {
        return monetryAmount;
    }

    public void setMonetryAmount(BigDecimal monetryAmount) {
        this.monetryAmount = monetryAmount;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }


    @Override
    public String toString() {
        return "GetCurrencyRequest{" +
                "country='" + country + '\'' +
                ", monetryAmount='" + monetryAmount + '\'' +
                ", targetCurrency='" + targetCurrency + '\'' +
                '}';
    }
}
