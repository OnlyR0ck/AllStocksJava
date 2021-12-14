package com.server.database.commands;

import com.server.database.IUpdateable;
import com.server.database.enums.KeyMetrics;
import com.server.database.enums.Shemas;
import com.server.database.enums.StocksQuote;
import com.server.models.KeyMetricsModel;
import com.server.models.StocksInfoModel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStockQuoteInfo implements IUpdateable {

    private StocksInfoModel stockInfoModel;

    public void setData(StocksInfoModel stocksInfoModel) {
        this.stockInfoModel = stocksInfoModel;
    }
    @Override
    public boolean update(Connection connection) {
        int count = 2;
        try {
            String sqlQuery = String.format("INSERT INTO %s.%s (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                    Shemas.ALLSTOCKS, StocksQuote.Table, StocksQuote.Symbol, StocksQuote.Name, StocksQuote.Price,
                    StocksQuote.ChangesPercentage, StocksQuote.Change, StocksQuote.DayLow, StocksQuote.DayHigh,
                    StocksQuote.YearHigh, StocksQuote.YearLow, StocksQuote.MarketCap, StocksQuote.PriceAverage50,
                    StocksQuote.PriceAverage200, StocksQuote.Open, StocksQuote.PreviousClose);


            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

                preparedStatement.setString(count++, stockInfoModel.symbol);
                preparedStatement.setString(count++, stockInfoModel.name);
                preparedStatement.setDouble(count++, stockInfoModel.price);
                preparedStatement.setDouble(count++, stockInfoModel.changesPercentage);
                preparedStatement.setDouble(count++, stockInfoModel.change);
                preparedStatement.setDouble(count++, stockInfoModel.dayLow);
                preparedStatement.setDouble(count++, stockInfoModel.dayHigh);
                preparedStatement.setDouble(count++, stockInfoModel.yearHigh);
                preparedStatement.setDouble(count++, stockInfoModel.yearLow);
                preparedStatement.setDouble(count++, stockInfoModel.marketCap);
                preparedStatement.setDouble(count++, stockInfoModel.priceAvg50);
                preparedStatement.setDouble(count++, stockInfoModel.priceAvg200);
                preparedStatement.setDouble(count++, stockInfoModel.open);
                preparedStatement.setDouble(count, stockInfoModel.previousClose);

                return preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
