package com.server.database.commands;

import com.server.database.ISqlQuery;
import com.server.database.enums.Shemas;
import com.server.database.enums.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddNewUser implements ISqlQuery {
    private String nickname;
    private int role;
    private String login;
    private String password;


    @Override
    public void getData(String data) {
        String[] splitedData = data.split(" ");

        nickname = splitedData[0];
        role = Integer.parseInt(splitedData[1]);
        login = splitedData[2];
        password = splitedData[3];
    }

    @Override
    public boolean update(Connection connection) {
        int count = 1;
        try {
            String sqlQuery = String.format("INSERT INTO %s.%s (%s, %s, %s, %s) VALUES(?,?,?,?)",
                    Shemas.ALLSTOCKS, User.Table, User.Nickname, User.Role, User.Login, User.Password);

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setString(count++, nickname);
                preparedStatement.setInt(count++, role);
                preparedStatement.setString(count++, login);
                preparedStatement.setString(count++, password);

                return preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

