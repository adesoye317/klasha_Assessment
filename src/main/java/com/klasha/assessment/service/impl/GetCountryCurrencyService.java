package com.klasha.assessment.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.klasha.assessment.model.request.get_currency.GetCurrencyRequest;
import com.klasha.assessment.model.request.get_details.GetDetailsRequest;
import com.klasha.assessment.model.response.exchange_rate.ExchangeFinalResponse;
import com.klasha.assessment.model.response.exchange_rate.ExchangeRateModel;
import com.klasha.assessment.model.response.exchange_rate.ExchangeRateModelItem;
import com.klasha.assessment.model.response.get_currency.GetCountryCurrencyResponse;
import com.klasha.assessment.model.response.get_currency.GetCurrencyResponse;
import com.klasha.assessment.service.GetCountryCurrencyInterface;
import com.klasha.assessment.util.HTTPUTILL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.CDL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author adegokeadesoye
 * @apiNote This service is for Getting country currency
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GetCountryCurrencyService implements GetCountryCurrencyInterface {


    private final HTTPUTILL httputill;
    private final GetCurrencyDetailService getCurrencyDetailService;
    private final Environment env;

    @Override
    public GetCountryCurrencyResponse countryCurrency(GetCurrencyRequest request){
        GetCountryCurrencyResponse finalResp = new GetCountryCurrencyResponse();

        try {
            GetDetailsRequest getDetailsRequest = new GetDetailsRequest();
            getDetailsRequest.setCountry(request.getCountry());
            GetCurrencyResponse response = getCurrencyDetailService.getCurrency(getDetailsRequest);
            finalResp.setCurrency(response.getData().getCurrency());
            finalResp.setCountry(request.getCountry());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("THE FINAL RESPONSE FOR GET COUNTRY CURRENCY::{}", finalResp);
        return finalResp;
    }

    public  String csvFileReder() throws FileNotFoundException {
        String fileDirectory = env.getProperty("exchange.rate.file.directory");

        String csvAsString = new BufferedReader(new FileReader(fileDirectory)).lines().collect(Collectors.joining("\n"));
        String csvAsJson = CDL.toJSONArray(csvAsString).toString();
        System.out.println(csvAsJson);
        return csvAsJson;
    }

    @Override
    public ExchangeFinalResponse exchangeRate(GetCurrencyRequest request){
        ExchangeRateModel rateModel = null;
        ExchangeFinalResponse finalResponse = new ExchangeFinalResponse();
        try {

            //Getting the rate model in form of pojo
            String getRate = csvFileReder();
            ObjectMapper objectMapper = new ObjectMapper();

            ExchangeRateModelItem[] exchangeRateModelItems = objectMapper.readValue(getRate, ExchangeRateModelItem[].class);
            List<ExchangeRateModelItem> exchangeRateModelItemArrayList = new ArrayList<>(Arrays.asList(exchangeRateModelItems));

            log.info("exchangeRateModelItems::: " + exchangeRateModelItemArrayList);

            rateModel = new ExchangeRateModel();
            rateModel.setExchangeRateModel(exchangeRateModelItemArrayList);

            log.info("THE RATEMODEL::{}", new Gson().toJson(rateModel));

            GetDetailsRequest getDetailsRequest = new GetDetailsRequest();
            getDetailsRequest.setCountry(request.getCountry());
            GetCurrencyResponse countryCurrency = getCurrencyDetailService.getCurrency(getDetailsRequest);

            List<ExchangeRateModelItem> listRate = rateModel.getExchangeRateModel();
            BigDecimal rate = BigDecimal.ZERO;
            String value = "";
            for (int i = 0; i < listRate.size(); i++){

                if (countryCurrency.getData().getCurrency().equalsIgnoreCase(listRate.get(i).getSourceCurrency())
                        && request.getTargetCurrency().equalsIgnoreCase(listRate.get(i).getTargetCurrency())){
                    log.info("THE RATE EXIST::");
                    log.info("THE AMOUNTT::{}", request.getMonetryAmount());
                    //Rate conversion
                    rate = request.getMonetryAmount().multiply(new BigDecimal(listRate.get(i).getRate()));
                    DecimalFormat formatter = new DecimalFormat("#,###.00");
                    value = formatter.format(rate);
                }
            }
            log.info("THE RATE::{}", value);
            finalResponse.setMsg("The conversion of " + countryCurrency.getData().getCurrency() +
                    " to " + request.getTargetCurrency());
            finalResponse.setAmount(request.getMonetryAmount());

            finalResponse.setConvertedAmount(value +" "+ request.getTargetCurrency());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return finalResponse;
    }


}
