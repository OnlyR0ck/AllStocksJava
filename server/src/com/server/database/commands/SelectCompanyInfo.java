package com.server.database.commands;

import com.server.database.IRequestable;
import com.server.database.enums.Company;
import com.server.database.enums.Shemas;
import com.server.models.CompanyInfoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Vector;

public class SelectCompanyInfo implements IRequestable {

    private String symbol;

    @Override
    public void getData(String data) {
        symbol = data.toUpperCase(Locale.ROOT);
    }

    public Vector<CompanyInfoModel> executeSelect(Connection connection) {
        int count = 1;
        Vector<CompanyInfoModel> infos = new Vector<>();

        try {

            String sqlQuery = String.format("SELECT * FROM `%s`.%s WHERE %s = ?",
                    Shemas.ALLSTOCKS, Company.Table, Company.Symbol, symbol);

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

                preparedStatement.setString(count++, symbol);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    count = 2;
                    CompanyInfoModel info = new CompanyInfoModel();
                    info.symbol = resultSet.getString(count++);
                    info.companyName = resultSet.getString(count++);
                    info.currency = resultSet.getString(count++);
                    info.industry = resultSet.getString(count++);
                    info.website = resultSet.getString(count++);
                    info.description = resultSet.getString(count++);
                    info.ceo = resultSet.getString(count++);
                    info.sector = resultSet.getString(count++);
                    info.country = resultSet.getString(count++);
                    info.fullTimeEmployees = String.valueOf(resultSet.getInt(count++));
                    info.phone = resultSet.getString(count++);
                    info.address = resultSet.getString(count++);
                    info.city = resultSet.getString(count++);
                    info.state = resultSet.getString(count++);
                    info.zip = resultSet.getString(count++);
                    info.image = resultSet.getString(count++);
                    info.ipoDate = String.valueOf(resultSet.getDate(count++));
                    infos.add(info);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return infos;
    }
}

    /*`company_id` int NOT null unique auto_increment,
    `symbol` VARCHAR(5) not null unique,
    `companyName` VARCHAR(20) ,
    `currency` VARCHAR(5) ,
    `industry` VARCHAR(30) ,
    `website` VARCHAR(30) ,
    `description` VARCHAR(2000) ,
    `ceo` VARCHAR(20) ,
    `sector` VARCHAR(20) ,
    `country` VARCHAR(10) ,
    `fullTimeEmployees` INT,
    `phone` BIGINT,
    `address` VARCHAR(30) ,
    `city` VARCHAR(20) ,
    `state` VARCHAR(15) ,
    `zip` INT,
    `image` VARCHAR(80),
    `ipoDate` DATETIME,*/

