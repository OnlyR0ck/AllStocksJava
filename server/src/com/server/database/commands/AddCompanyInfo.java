package com.server.database.commands;

import com.server.database.ISqlQuery;
import com.server.database.IUpdateable;
import com.server.database.enums.Company;
import com.server.database.enums.Shemas;
import com.server.database.enums.User;
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
            String sqlQuery = String.format("INSERT INTO %s.%s (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s," +
                            " %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s) " +
                            "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
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
                preparedStatement.setInt(count++, Integer.parseInt(infoModel.phone));
                preparedStatement.setString(count++, infoModel.address);
                preparedStatement.setString(count++, infoModel.city);
                preparedStatement.setString(count++, infoModel.state);
                preparedStatement.setInt(count++, Integer.parseInt(infoModel.zip));
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
/*{
    "symbol" : "AAPL",
    "companyName" : "Apple Inc.",
    "currency" : "USD",
    "industry" : "Consumer Electronics",
    "website" : "http://www.apple.com",
    "description" : "Apple Inc. designs, manufactures, and markets smartphones, personal computers, tablets, wearables, and accessories worldwide. It also sells various related services. The company offers iPhone, a line of smartphones; Mac, a line of personal computers; iPad, a line of multi-purpose tablets; and wearables, home, and accessories comprising AirPods, Apple TV, Apple Watch, Beats products, HomePod, iPod touch, and other Apple-branded and third-party accessories. It also provides AppleCare support services; cloud services store services; and operates various platforms, including the App Store, that allow customers to discover and download applications and digital content, such as books, music, video, games, and podcasts. In addition, the company offers various services, such as Apple Arcade, a game subscription service; Apple Music, which offers users a curated listening experience with on-demand radio stations; Apple News+, a subscription news and magazine service; Apple TV+, which offers exclusive original content; Apple Card, a co-branded credit card; and Apple Pay, a cashless payment service, as well as licenses its intellectual property. The company serves consumers, and small and mid-sized businesses; and the education, enterprise, and government markets. It sells and delivers third-party applications for its products through the App Store. The company also sells its products through its retail and online stores, and direct sales force; and third-party cellular network carriers, wholesalers, retailers, and resellers. Apple Inc. was founded in 1977 and is headquartered in Cupertino, California.",
    "ceo" : "Mr. Timothy Cook",
    "sector" : "Technology",
    "country" : "US",
    "fullTimeEmployees" : "147000", int
    "phone" : "14089961010", int
    "address" : "1 Apple Park Way",
    "city" : "Cupertino",
    "state" : "CALIFORNIA",
    "zip" : "95014", int
    "image" : "https://financialmodelingprep.com/image-stock/AAPL.png",
    "ipoDate" : "1980-12-12", datetime
  }*/
