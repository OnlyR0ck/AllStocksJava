package com.server.database.commands;

import com.server.database.IUpdateable;
import com.server.database.enums.Company;
import com.server.database.enums.KeyMetrics;
import com.server.database.enums.Shemas;
import com.server.models.CompanyInfoModel;
import com.server.models.KeyMetricsModel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddKeyMetricsInfo implements IUpdateable {
    private KeyMetricsModel keyMetricsModel;

    public void setData(KeyMetricsModel keyMetricsModel) {
        this.keyMetricsModel = keyMetricsModel;
    }

    @Override
    public boolean update(Connection connection) {
        //TODO:possible error here
        int count = 2;
        try {
            String sqlQuery = String.format("INSERT INTO %s.%s (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES(?,?,?,?,?,?,?,?,?,?,?)",
                    Shemas.ALLSTOCKS, KeyMetrics.Table, KeyMetrics.Symbol, KeyMetrics.Date, KeyMetrics.MarketCap,
                    KeyMetrics.EnterpriseValue, KeyMetrics.PERatio, KeyMetrics.PSRatio, KeyMetrics.PBRatio,
                    KeyMetrics.EvToS, KeyMetrics.EvToEbitda, KeyMetrics.Roic, KeyMetrics.Roe);

            Date date = Date.valueOf(keyMetricsModel.date);

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

                preparedStatement.setString(count++, keyMetricsModel.symbol);
                preparedStatement.setDate(count++, Date.valueOf(keyMetricsModel.date));
                preparedStatement.setDouble(count++, keyMetricsModel.marketCap);
                preparedStatement.setDouble(count++, keyMetricsModel.enterpriseValue);
                preparedStatement.setDouble(count++, keyMetricsModel.peRatio);
                preparedStatement.setDouble(count++, keyMetricsModel.priceToSalesRatio);
                preparedStatement.setDouble(count++, keyMetricsModel.pbRatio);
                preparedStatement.setDouble(count++, keyMetricsModel.evToSales);
                preparedStatement.setDouble(count++, keyMetricsModel.enterpriseValueOverEBITDA);
                preparedStatement.setDouble(count++, keyMetricsModel.roic);
                preparedStatement.setDouble(count++, keyMetricsModel.roe);

                return preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

/*{
    "symbol" : "AAPL",
    "date" : "2021-09-25",
    "marketCap" : 2482477053378.728,
    "enterpriseValue" : 2572256053378.728,
    "peRatio" : 26.219656246078664,
    "priceToSalesRatio" : 6.786117248183458,
    "pbRatio" : 39.34818597842333,
    "evToSales" : 7.031537772653343,
    "enterpriseValueOverEBITDA" : 20.889553448047103,
    "roic" : 0.48309913489209433,
    "roe" : 1.5007132667617689
} */