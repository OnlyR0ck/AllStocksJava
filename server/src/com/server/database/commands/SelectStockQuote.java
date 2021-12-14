package com.server.database.commands;

import com.server.database.IRequestable;
import com.server.database.enums.Shemas;
import com.server.database.enums.StocksQuote;
import com.server.models.StocksInfoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class SelectStockQuote implements IRequestable {
    private String symbol;

    @Override
    public void getData(String data) {
        symbol = data;
    }

    public Vector<StocksInfoModel> executeSelect(Connection connection) {
        int count = 1;
        Vector<StocksInfoModel> models = new Vector<>();

        try {

            String sqlQuery = String.format("SELECT * FROM `%s`.%s WHERE %s = ?",
                    Shemas.ALLSTOCKS, StocksQuote.Table, StocksQuote.Symbol, symbol);

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

                preparedStatement.setString(count++, symbol);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    count = 2;
                    StocksInfoModel model = new StocksInfoModel();
                    model.symbol = resultSet.getString(count++);
                    model.name = resultSet.getString(count++);
                    model.price = resultSet.getDouble(count++);
                    model.changesPercentage = resultSet.getDouble(count++);
                    model.change = resultSet.getDouble(count++);
                    model.dayLow = resultSet.getDouble(count++);
                    model.dayHigh = resultSet.getDouble(count++);
                    model.yearLow = resultSet.getDouble(count++);
                    model.yearHigh = resultSet.getDouble(count++);
                    model.marketCap = resultSet.getDouble(count++);
                    model.priceAvg50 = resultSet.getDouble(count++);
                    model.priceAvg200 = resultSet.getDouble(count++);
                    model.open = resultSet.getDouble(count++);
                    model.previousClose = resultSet.getDouble(count++);

                    models.add(model);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return models;
    }

}
