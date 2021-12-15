package com.server.database.commands;

import com.server.database.IUpdateable;
import com.server.database.enums.Company;
import com.server.database.enums.Shemas;
import com.server.models.CompanyInfoModel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddCompanyInfo implements IUpdateable {
    private CompanyInfoModel infoModel;

    public void setData(CompanyInfoModel infoModel) {
        this.infoModel = infoModel;
    }

    @Override
    public boolean update(Connection connection) {
        int count = 1;
        try {
            String sqlQuery = String.format("INSERT INTO %s.%s (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                    Shemas.ALLSTOCKS, Company.Table, Company.Symbol, Company.CompanyName, Company.Currency,
                    Company.Industry, Company.Website, Company.Description, Company.Ceo, Company.Sector,
                    Company.Country, Company.FullTimeEmployees, Company.Phone, Company.Address, Company.City,
                    Company.State, Company.Zip, Company.Image, Company.IpoDate);

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

                preparedStatement.setString(count++, infoModel.symbol);
                preparedStatement.setString(count++, infoModel.companyName);
                preparedStatement.setString(count++, infoModel.currency);
                preparedStatement.setString(count++, infoModel.industry);
                preparedStatement.setString(count++, infoModel.website);
                preparedStatement.setString(count++, infoModel.description);
                preparedStatement.setString(count++, infoModel.ceo);
                preparedStatement.setString(count++, infoModel.sector);
                preparedStatement.setString(count++, infoModel.country);
                preparedStatement.setInt(count++, Integer.parseInt(infoModel.fullTimeEmployees));
                preparedStatement.setString(count++, infoModel.phone);
                preparedStatement.setString(count++, infoModel.address);
                preparedStatement.setString(count++, infoModel.city);
                preparedStatement.setString(count++, infoModel.state);
                preparedStatement.setString(count++, infoModel.zip);
                preparedStatement.setString(count++, infoModel.image);
                preparedStatement.setDate(count++, Date.valueOf(infoModel.ipoDate));

                return preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}