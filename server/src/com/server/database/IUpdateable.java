package com.server.database;

import java.sql.Connection;

public interface IUpdateable {
    boolean update(Connection connection);
}
