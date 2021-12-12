package com.server.database.commands;

import com.server.database.IRequestable;
import com.server.database.enums.Shemas;
import com.server.database.enums.User;
import com.server.models.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class SelectUser implements IRequestable {
    private String login;
    private String password;

    @Override
    public void getData(String data) {
        String[] splitedData = data.split(" ");

        login = splitedData[0];
        password = splitedData[1];
    }

    public Vector<UserModel> executeSelect(Connection connection) {
        int count = 1;
        Vector<UserModel> users = new Vector<>();

        try {

            String sqlQuery = String.format("SELECT * FROM `%s`.%s WHERE %s = ? and %s = ?",
                    Shemas.ALLSTOCKS, User.Table, User.Login, User.Password);

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

                preparedStatement.setString(count++, login);
                preparedStatement.setString(count++, password);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    count = 1;
                    UserModel user = new UserModel();
                    user
                            .setUserID(resultSet.getInt(count++))
                            .setNickname(resultSet.getString(count++))
                            .setRole(resultSet.getInt(count++))
                            .setLogin(resultSet.getString(count++))
                            .setPassword(resultSet.getString(count++));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}

