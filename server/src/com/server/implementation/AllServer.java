package com.server.implementation;

//import com.SQLsupport.DatabaseConnection;

import com.server.database.DatabaseConnection;

import java.io.IOException;
import java.net.ServerSocket;

public class AllServer {

    private final int port;
    private ServerSocket serverSocket;
    private static AllServer instance = null;
    private DatabaseConnection dbConnection = null;


    public static AllServer getInstance() {
        if (instance == null) {
            instance = new AllServer();
        }

        return instance;
    }

    private AllServer() {
        port = 2525;
    }

    public void startServer()
    {
        try {
            serverSocket = new ServerSocket(port);
            dbConnection = new DatabaseConnection();
            dbConnection.init();
        } catch (IOException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true)
            new Thread(new ClientHandler(serverSocket, dbConnection)).start();
    }

    public void close() {
        try {
            serverSocket.close();
            dbConnection.destroy();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        AllServer.getInstance().startServer();
        AllServer.getInstance().run();
       /* String sqlQuery = String.format("SELECT * FROM %s.%s WHERE %s = ? and %s = ?",
                Shemas.ALLSTOCKS, User.TABLE, User.LOGIN, User.PASSWORD);
        System.out.printf(sqlQuery);*/
    }
}
