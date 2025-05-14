package com.recruitment.fund_box.service;

import com.recruitment.fund_box.enums.Currency;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@Service
public class CurrencyExchangeService {
    private final WebClient webClient;
    private final String apiKey;

    public CurrencyExchangeService(
            @Value("${exchangerate.api.key:}") String apiKey,
            WebClient webClient) {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalStateException(
                "ExchangeRate API key not configured. Please set EXCHANGERATE_API_KEY environment variable or configure it in application.properties");
        }
        this.apiKey = apiKey;
        this.webClient = webClient;
    }

    public BigDecimal convertCurrency(BigDecimal amount, Currency fromCurrency, Currency toCurrency) {
        if (fromCurrency == toCurrency) {
            return amount;
        }

        try {
            Map<String, Object> response = webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/convert")
                .queryParam("access_key", apiKey)
                .queryParam("from", fromCurrency)
                .queryParam("to", toCurrency)
                .queryParam("amount", amount)
                .build())
            .retrieve()
            .bodyToMono(Map.class)
            .block();


            if (response == null || !Boolean.TRUE.equals(response.get("success"))) {
                String errorMessage = "Failed to fetch exchange rates";
                if (response != null && response.containsKey("error")) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> error = (Map<String, Object>) response.get("error");
                    errorMessage += ": " + error.get("info");
                }
                throw new IllegalStateException(errorMessage);
            }

            Object resultObj = response.get("result");
            if (resultObj == null) {
                throw new IllegalStateException("No conversion result returned from API");
            }

            BigDecimal result;
            if (resultObj instanceof Double) {
                result = BigDecimal.valueOf((Double) resultObj);
            } else if (resultObj instanceof Integer) {
                result = BigDecimal.valueOf((Integer) resultObj);
            } else {
                result = new BigDecimal(resultObj.toString());
            }

            return result.setScale(2, RoundingMode.HALF_UP);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to convert currency: " + e.getMessage(), e);
        }
    }
}