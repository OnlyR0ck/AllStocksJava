package com.server.commands;

import com.server.database.IRequestable;
import com.server.database.enums.Shemas;
import com.server.database.enums.StocksHistorical;
import com.server.models.StocksHistoricalModel;
import com.server.models.StocksHistoricalModels;
import com.server.models.StocksInfoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class SelectStocksHistorical implements IRequestable {
    private String symbol;

    @Override
    public void getData(String data) {

    }

    public Vector<StocksHistoricalModels> executeSelect(Connection connection) {
        int count = 1;
        Vector<StocksHistoricalModels> vModels = new Vector<>();
        StocksHistoricalModels models = new StocksHistoricalModels();

        try {

            String sqlQuery = String.format("SELECT * FROM `%s`.%s WHERE %s = ?",
                    Shemas.ALLSTOCKS, StocksHistorical.Table, StocksHistorical.Symbol, symbol);

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

                preparedStatement.setString(count++, symbol);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    count = 2;
                    if (models.symbol.isEmpty()) {
                        models.symbol = resultSet.getString(count++);
                    }
                    else count++;

                    StocksHistoricalModel model = new StocksHistoricalModel();
                    model.date = resultSet.getString(count++);
                    model.open = resultSet.getDouble(count++);
                    model.high = resultSet.getDouble(count++);
                    model.low = resultSet.getDouble(count++);
                    model.close = resultSet.getDouble(count++);
                    model.change = resultSet.getDouble(count++);
                    model.changePercent = resultSet.getDouble(count++);
                    model.label = resultSet.getString(count++);
                    models.models.add(model);

                    vModels.add(models);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vModels;
    }

}
