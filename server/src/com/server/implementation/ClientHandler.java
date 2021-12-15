package com.server.implementation;


import com.server.commands.ServerCommandType;
import com.server.database.DatabaseConnection;
import com.server.database.ISqlQuery;
import com.server.database.commands.AddNewUser;
import com.server.database.commands.SelectUser;
import com.server.models.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Vector;

public class ClientHandler implements Runnable {
    private Socket client;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private static int allClientCount = 0;
    private int currentClient;
    private DatabaseConnection dbConnection;

    public ClientHandler(ServerSocket serverSocket, DatabaseConnection dbConnection) {

        try {
            client = serverSocket.accept();
            allClientCount++;
            currentClient = allClientCount;
            System.out.println("client №" + currentClient + " connected");
            inputStream = new ObjectInputStream(client.getInputStream());
            outputStream = new ObjectOutputStream(client.getOutputStream());
            this.dbConnection = dbConnection;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Release() throws IOException, NullPointerException {
        inputStream.close();
        outputStream.close();
        client.close();
    }

    private void closeThread() {
        try {
            System.out.println("client №" + currentClient + " disconnected");
            allClientCount--;
            this.Release();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        while (true) {
            try {
                String clientQuery = ((String) inputStream.readObject()).trim();
                System.out.println(clientQuery);
                String[] splitedQuery = clientQuery.split(" ");
                ServerCommandType commandType = ServerCommandType.valueOf(splitedQuery[0]);
                String dataFromClient = String.
                        join(" ", Arrays.copyOfRange(splitedQuery, 1, splitedQuery.length));

                ISqlQuery sqlUpdateQuery = null;

                switch (commandType) {

                    case Exit -> {
                        closeThread();
                        continue;
                    }
                    case Login -> {
                        getUser(dataFromClient);
                        continue;
                    }
                    case Register -> sqlUpdateQuery = new AddNewUser();
                    case CompanyInfo -> {
                        getCompanyInfo(dataFromClient);
                        continue;
                    }
                    case KeyMetrics -> {
                        getKeyMetrics(dataFromClient);
                        continue;
                    }
                    case StockQuote -> {
                        getStockQuote(dataFromClient);
                        continue;
                    }
                    case TicketInfo -> {
                        getStockHistoricalInfo(dataFromClient);
                    }
                }

                if (sqlUpdateQuery != null) {
                    sqlUpdateQuery.getData(dataFromClient);
                    boolean result = sqlUpdateQuery.update(dbConnection.getMyConnection());
                    outputStream.writeObject(result);
                    outputStream.flush();
                    continue;
                }

                switch (clientQuery) {

                }


                //check all select requests
                switch (clientQuery) {
                }
            } catch (SocketException e) {
                System.out.println("client №" + currentClient + " break connection");
                closeThread();
                return;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private void getStockHistoricalInfo(String dataFromClient) throws IOException {
        AllStocksService service = new AllStocksService(dbConnection.getMyConnection());
        String[] splitedData = dataFromClient.split(" ");
        Vector<StocksHistoricalModels> stocksInfoModels = service.getTicketHistorical(splitedData[0], Integer.parseInt(splitedData[1]));
        outputStream.writeObject(stocksInfoModels);
        outputStream.flush();
    }

    private void getStockQuote(String dataFromClient) throws IOException {
        AllStocksService service = new AllStocksService(dbConnection.getMyConnection());
        Vector<StocksInfoModel> stocksInfoModels = service.getStockQuote(dataFromClient);
        outputStream.writeObject(stocksInfoModels);
        outputStream.flush();
    }

    private void getKeyMetrics(String dataFromClient) throws IOException {
        AllStocksService service = new AllStocksService(dbConnection.getMyConnection());
        Vector<KeyMetricsModel> keyMetricsModels = service.getKeyMetrics(dataFromClient);
        outputStream.writeObject(keyMetricsModels);
        outputStream.flush();
    }

    private void getCompanyInfo(String dataFromClient) throws IOException {
        AllStocksService service = new AllStocksService(dbConnection.getMyConnection());
        Vector<CompanyInfoModel> infoModels = service.getCompanyInfo(dataFromClient);
        outputStream.writeObject(infoModels);
        outputStream.flush();
    }

    private void getUser(String dataFromClient) throws IOException {
        SelectUser sqlSelect = new SelectUser();
        sqlSelect.getData(dataFromClient);
        Vector<UserModel> user = sqlSelect.executeSelect(dbConnection.getMyConnection());
        outputStream.writeObject(user);
        outputStream.flush();
    }
}

