package com.server.database.commands;

import com.server.database.IRequestable;
import com.server.database.enums.Company;
import com.server.database.enums.KeyMetrics;
import com.server.database.enums.Shemas;
import com.server.models.CompanyInfoModel;
import com.server.models.KeyMetricsModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class SelectCompanyKeyMetrics implements IRequestable {
    private String symbol;

    @Override
    public void getData(String data) {
        symbol = data;
    }

    public Vector<KeyMetricsModel> executeSelect(Connection connection) {
        int count = 1;
        Vector<KeyMetricsModel> models = new Vector<>();

        try {

            String sqlQuery = String.format("SELECT * FROM `%s`.%s WHERE %s = ?",
                    Shemas.ALLSTOCKS, KeyMetrics.Table, KeyMetrics.Symbol, symbol);

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

                preparedStatement.setString(count++, symbol);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    count = 2;
                    KeyMetricsModel model = new KeyMetricsModel();
                    model.symbol = resultSet.getString(count++);
                    model.date = String.valueOf(resultSet.getDate(count++));
                    model.marketCap = resultSet.getDouble(count++);
                    model.enterpriseValue = resultSet.getDouble(count++);
                    model.peRatio = resultSet.getDouble(count++);
                    model.evToSales = resultSet.getDouble(count++);
                    model.enterpriseValueOverEBITDA = resultSet.getDouble(count++);
                    model.roic = resultSet.getDouble(count++);
                    model.roe = resultSet.getDouble(count);
                    models.add(model);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return models;
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