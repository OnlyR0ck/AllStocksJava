package com.server.implementation;

import com.google.gson.Gson;
import com.server.database.commands.AddCompanyInfo;
import com.server.database.commands.AddKeyMetricsInfo;
import com.server.database.commands.SelectCompanyInfo;
import com.server.database.commands.SelectCompanyKeyMetrics;
import com.server.models.CompanyInfoModel;
import com.server.models.KeyMetricsModel;

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

            if(!addCompanyInfo.update(dataBaseConnection))
            {
                System.out.println("Error during add company info to database (AllStocksService:35)");
            }
        }
        return infos;
    }

    public String getTicketRealTimeInfo(String symbol) {
        return "";
    }

    public String getTicketHistorical(String symbol, int timeSeries) {
        return "";
    }

    public String getTicketHistorical(String symbol, String dateFrom, String dateTo) {
        return "";
    }

    public Vector<CompanyInfoModel> JsonToCompanyInfo(String json)
    {
        Vector<CompanyInfoModel > infos = new Vector<>();
        /*GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();*/

        Gson gson = new Gson();

        return infos;
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

            if(!addKeyMetricsInfo.update(dataBaseConnection))
            {
                System.out.println("Error during add company info to database (AllStocksService:85)");
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
