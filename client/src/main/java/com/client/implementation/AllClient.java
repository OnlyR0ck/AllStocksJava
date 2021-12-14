package com.client.implementation;

import com.server.models.CompanyInfoModel;
import com.server.models.KeyMetricsModel;
import com.server.models.UserModel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class AllClient {
    private Socket client;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private Scanner scan;
    private static AllClient instance = null;
    private UserModel user = null;


    private AllClient(String host, int port) {
        try {
            client = new Socket(host, port);
            outputStream = new ObjectOutputStream(client.getOutputStream());
            inputStream = new ObjectInputStream(client.getInputStream());
            scan = new Scanner(System.in);
            System.out.println("connection established...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AllClient getInstance() {
        if (instance == null) {
            instance = new AllClient("127.0.0.1", 2525);
        }
        return instance;
    }

    public void sendData(String data) {
        try {
            outputStream.writeObject(data);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean receiveResult() {
        try {
            return (boolean) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


    public String receiveFilePath() {
        try {
            return (String) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*public Vector<UserModel> receiveUsers() {
        try {
            //TODO: App stunning after second readObject (sometimes)
            Vector<UserModel> users = (Vector<UserModel>) inputStream.readObject();
            return users;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Vector<CompanyInfoModel> receiveInfoModels() {
        try {
            //TODO: App stunning after second readObject (sometimes)
            Vector<CompanyInfoModel> infoModels = (Vector<CompanyInfoModel>) inputStream.readObject();
            return infoModels;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }*/


    public void close() {
        try {
            outputStream.close();
            inputStream.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public Vector<KeyMetricsModel> receiveKeyMetricsModel() {
        try {
            //TODO: App stunning after second readObject (sometimes)
            Vector<KeyMetricsModel> keyMetricsModels = (Vector<KeyMetricsModel>) inputStream.readObject();
            return keyMetricsModels;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    public <T> Vector<T> receiveModels() {
        try {
            //TODO: App stunning after second readObject (sometimes)
            Vector<T> models = (Vector<T>) inputStream.readObject();
            return models;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
