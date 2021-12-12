package com.gui;

import com.client.enumerations.SceneType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuViewController {

    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;


    @FXML
    void initialize()
    {
        signInButton.setOnMouseClicked(mouseEvent ->
        {
            SceneLoader.getInstance().switchScene(SceneType.SignInView);
        });

        signUpButton.setOnMouseClicked(mouseEvent ->
        {
            SceneLoader.getInstance().switchScene(SceneType.SignUpView);
        });
    }
}
