package com.gui;

import com.client.enumerations.SceneType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class SceneLoader {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private static SceneLoader instance;

    public static SceneLoader getInstance() {
        if (instance == null) {
            instance = new SceneLoader();
        }

        return instance;
    }

    private SceneLoader() {

    }

    /*public void switchScene(MouseEvent event, SceneType type) {
        try {
            List<Window> opened = Stage.getWindows().stream().filter(Window::isShowing).toList();

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(type.name())));
            //stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage = (Stage) opened.get(0);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException exception) {
            System.out.println(exception);
        }

    }*/

    public void switchScene(SceneType type) {
        try {
            List<Window> opened = Stage.getWindows().stream().filter(Window::isShowing).toList();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(String.valueOf(type))));
            stage = (Stage) opened.get(0);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException exception) {
            System.out.println(exception);
        }

    }
}

