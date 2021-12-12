package com.gui;

import com.client.enumerations.SceneType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainMenuInit extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource(String.valueOf(SceneType.MainMenuView)))));
        Scene scene = new Scene(root);
        stage.setTitle("AllStocks Client!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {launch(args);}
}

