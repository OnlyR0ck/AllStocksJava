package com.client.implementation;

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

    public Vector<UserModel> receiveUsers() {
        try {
            //TODO: App stunning after second readObject
            var users = (Vector<UserModel>) inputStream.readObject();
            return users;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void close() {
        try {
            outputStream.close();
            inputStream.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
