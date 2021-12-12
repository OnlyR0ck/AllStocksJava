package com.server.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Locale;

public class FMPClient {
    private final String apiKey = "e9f1e718d3f97c8d9ee5257bae15bd6a";

    public String getApiKey() {
        return apiKey;
    }

    public FMPClient() {

    }

    public String getKeyMetrics(String symbol)
    {
        StringBuilder answer = new StringBuilder();
        try {
            String query = String
                    .format("https://financialmodelingprep.com/api/v3/key-metrics/%s?limit=1&apikey=%s",
                            symbol.toUpperCase(Locale.ROOT), getApiKey());

            URL url = new URL(query);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                for (String line; (line = reader.readLine()) != null;) {
                    answer.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer.toString();
    }

    public String getCompanyInfo(String symbol)
    {
        StringBuilder answer = new StringBuilder();

        try {
            String query = String
                    .format("https://financialmodelingprep.com/api/v3/profile/%s?apikey=%s", symbol, getApiKey());

            URL url = new URL(query);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                for (String line; (line = reader.readLine()) != null;) {
                    answer.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return answer.toString();
    }

    public String getTicketRealTimeInfo(String symbol)
    {
        StringBuilder answer = new StringBuilder();

        try {
            String query = String
                    .format("https://financialmodelingprep.com/api/v3/quote/%s?apikey=%s", symbol, getApiKey());

            URL url = new URL(query);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                for (String line; (line = reader.readLine()) != null;) {
                    answer.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return answer.toString();
    }

    /**
     * Connect to FMP Api and get ticket data for last x days
     @param symbol ticket symbol
     @param timeSeries return data for last x days
     @return return fmp api response via json
     */
    public String getTicketHistorical(String symbol, int timeSeries)
    {
        StringBuilder answer = new StringBuilder();

        try {
            String query = String
                    .format("https://financialmodelingprep.com/api/v3/historical-price-full/%s?apikey=%s", symbol, getApiKey());

            URL url = new URL(query);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                for (String line; (line = reader.readLine()) != null;) {
                    answer.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return answer.toString();
    }

    /**
     * Connect to FMP Api and get historical ranged price
     @param symbol ticket symbol
     @param dateFrom ticket price from range
     @param dateTo ticket price to range
     @return return fmp api response via json
     */
    public String getTicketHistorical(String symbol,String dateFrom, String dateTo)
    {
        StringBuilder answer = new StringBuilder();

        try {
            String query = String
                    .format("https://financialmodelingprep.com/api/v3/historical-price-full/%s?from=%s&to=%s&apikey=%s",
                            symbol,dateFrom, dateTo, getApiKey());

            URL url = new URL(query);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                for (String line; (line = reader.readLine()) != null;) {
                    answer.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return answer.toString();
    }
}
