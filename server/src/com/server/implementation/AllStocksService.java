package com.server.implementation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.server.commands.SelectStocksHistorical;
import com.server.database.commands.*;
import com.server.models.*;

import java.sql.Connection;
import java.util.Vector;

public class AllStocksService {
    private Connection dataBaseConnection;
    private FMPClient fmpClient;

    public AllStocksService(Connection connection) {
        this.dataBaseConnection = connection;
        fmpClient = new FMPClient();
    }


    public Vector<CompanyInfoModel> getCompanyInfo(String symbol) {
        SelectCompanyInfo sqlQuery = new SelectCompanyInfo();
        sqlQuery.getData(symbol);
        Vector<CompanyInfoModel> infos = sqlQuery.executeSelect(dataBaseConnection);
        if (infos.size() == 0) {
            FMPClient fmpClient = new FMPClient();
            String apiResponse = fmpClient.getCompanyInfo(symbol);

            Gson gson = new Gson();
            CompanyInfoModel[] companyInfoModel = gson.fromJson(apiResponse, CompanyInfoModel[].class);

            infos.add(companyInfoModel[0]);
            AddCompanyInfo addCompanyInfo = new AddCompanyInfo();
            addCompanyInfo.setData(companyInfoModel[0]);

            if (!addCompanyInfo.update(dataBaseConnection)) {
                System.out.println("Error during add company info to database (AllStocksService:35)");
            }
        }
        return infos;
    }

    public Vector<StocksInfoModel> getStockQuote(String symbol) {
        SelectStockQuote sqlQuery = new SelectStockQuote();
        sqlQuery.getData(symbol);
        Vector<StocksInfoModel> models = sqlQuery.executeSelect(dataBaseConnection);
        if (models.size() == 0) {
            FMPClient fmpClient = new FMPClient();
            String apiResponse = fmpClient.getTicketQuoteInfo(symbol);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            StocksInfoModel[] stocksInfoModels = gson.fromJson(apiResponse, StocksInfoModel[].class);

            models.add(stocksInfoModels[0]);
            AddStockQuoteInfo addKeyMetricsInfo = new AddStockQuoteInfo();
            addKeyMetricsInfo.setData(stocksInfoModels[0]);

            if (!addKeyMetricsInfo.update(dataBaseConnection)) {
                System.out.println("Error during add stock quote info to database (AllStocksService:85)");
            }
        }
        return models;
    }

    public Vector<StocksHistoricalModels> getTicketHistorical(String symbol, int timeSeries) {
        SelectStocksHistorical sqlQuery = new SelectStocksHistorical();
        sqlQuery.getData(symbol);
        Vector<StocksHistoricalModels> models = sqlQuery.executeSelect(dataBaseConnection);
        if (models.size() == 0) {
            FMPClient fmpClient = new FMPClient();
            String apiResponse = fmpClient.getTicketHistorical(symbol, timeSeries);

            Gson gson = new Gson();

            StocksHistoricalModels stocksHistoricalModels = gson.fromJson(apiResponse, StocksHistoricalModels.class);

            models.add(stocksHistoricalModels);
            for (StocksHistoricalModel model :
                    models.get(0).models) {
                AddStocksHistorical addKeyMetricsInfo = new AddStocksHistorical();
                addKeyMetricsInfo.setData(model, symbol);

                if (!addKeyMetricsInfo.update(dataBaseConnection)) {
                    System.out.println("Error during add tickets historical to database (AllStocksService:85)");
                }
            }
        }
        return models;
    }

    //TODO: should add date checker, if data is not valid
    public Vector<KeyMetricsModel> getKeyMetrics(String symbol) {
        SelectCompanyKeyMetrics sqlQuery = new SelectCompanyKeyMetrics();
        sqlQuery.getData(symbol);
        Vector<KeyMetricsModel> models = sqlQuery.executeSelect(dataBaseConnection);
        if (models.size() == 0) {
            FMPClient fmpClient = new FMPClient();
            String apiResponse = fmpClient.getKeyMetrics(symbol);

            Gson gson = new Gson();
            KeyMetricsModel[] keyMetricsModels = gson.fromJson(apiResponse, KeyMetricsModel[].class);

            models.add(keyMetricsModels[0]);
            AddKeyMetricsInfo addKeyMetricsInfo = new AddKeyMetricsInfo();
            addKeyMetricsInfo.setData(keyMetricsModels[0]);

            if (!addKeyMetricsInfo.update(dataBaseConnection)) {
                System.out.println("Error during add key metrics to database (AllStocksService:85)");
            }
        }
        return models;
    }
}
/*
 * 1. Check if there is data in database
 * 2. Check if data isn't out of time
 * 3. If it's alright - parse to model and send to client
 * 4. If not - get data from api, write it to database
 * 5. Parse to model
 * 6. send to client
 * */
