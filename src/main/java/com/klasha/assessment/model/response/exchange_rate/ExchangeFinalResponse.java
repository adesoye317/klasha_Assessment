package com.klasha.assessment.model.response.exchange_rate;

import lombok.Data;

import java.math.BigDecimal;
import java.text.NumberFormat;

@Data
public class ExchangeFinalResponse {

    private String msg;
    private BigDecimal amount;
    private String convertedAmount;

}
