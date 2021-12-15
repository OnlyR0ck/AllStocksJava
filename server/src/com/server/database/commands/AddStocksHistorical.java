package com.server.database.commands;

import com.server.database.IUpdateable;
import com.server.database.enums.Shemas;
import com.server.database.enums.StocksHistorical;
import com.server.database.enums.StocksQuote;
import com.server.models.StocksHistoricalModel;
import com.server.models.StocksHistoricalModels;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStocksHistorical implements IUpdateable {
    private StocksHistoricalModel model;
    private String symbol;

    public void setData(StocksHistoricalModel model, String symbol) {
        this.model = model;
        this.symbol = symbol;
    }

    @Override
    public boolean update(Connection connection) {
        int count = 1;
        try {
            String sqlQuery = String.format("INSERT INTO `%s`.%s (%s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES(?,?,?,?,?,?,?,?,?)",
                    Shemas.ALLSTOCKS, StocksHistorical.Table, StocksHistorical.Symbol, StocksHistorical.Date,
                    StocksHistorical.Open, StocksHistorical.High, StocksHistorical.Low, StocksHistorical.Close,
                    StocksHistorical.Change, StocksHistorical.ChangePercent, StocksHistorical.Label);


            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

                preparedStatement.setString(count++, symbol);
                preparedStatement.setDate(count++, Date.valueOf(model.date));
                preparedStatement.setDouble(count++, model.open);
                preparedStatement.setDouble(count++, model.high);
                preparedStatement.setDouble(count++, model.low);
                preparedStatement.setDouble(count++, model.close);
                preparedStatement.setDouble(count++, model.change);
                preparedStatement.setDouble(count++, model.changePercent);
                preparedStatement.setString(count++, model.label);

                return preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
