package com.server.implementation;

//import com.SQLsupport.DatabaseConnection;

import com.server.database.DatabaseConnection;
import com.server.database.enums.Company;
import com.server.database.enums.Shemas;

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
        /*String sqlQuery = String.format("INSERT INTO %s.%s (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                Shemas.ALLSTOCKS, Company.Table, Company.Symbol, Company.CompanyName, Company.Currency,
                Company.Industry, Company.Website, Company.Description, Company.Ceo, Company.Sector,
                Company.Country, Company.FullTimeEmployees, Company.Phone, Company.Address, Company.City,
                Company.State, Company.Zip, Company.Image, Company.IpoDate);
        System.out.printf(sqlQuery);*/
    }
}
