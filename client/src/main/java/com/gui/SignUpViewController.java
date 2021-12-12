package com.gui;

import com.client.enumerations.SceneType;
import com.client.implementation.AllClient;
import com.server.commands.ServerCommandType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.concurrent.TimeUnit;

public class SignUpViewController {

    @FXML
    private TextField loginField;

    @FXML
    private TextField nicknameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField repeatPasswordField;

    @FXML
    private Button signUpButton;

    @FXML
    private Label warningText;

    @FXML
    void initialize() {
        signUpButton.setOnMouseClicked(mouseEvent ->
        {
            try {
                signUpButton_OnClick();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private void signUpButton_OnClick() throws InterruptedException {
        String repeatPassword = repeatPasswordField.getText();
        String password = passwordField.getText();
        String login = loginField.getText();
        String nickname = nicknameField.getText();
        String role = "0";

        boolean isDataCorrect = dataValidation(repeatPassword, password, login, nickname);


        if (isDataCorrect) {
            AllClient client = AllClient.getInstance();

            String clientRequest = String
                    .format("%s %s %s %s %s", ServerCommandType.Register, nickname, role, login, password);
            client.sendData(String.valueOf(clientRequest));

            if (client.receiveResult()) {
                warningText.setText("Регистрация прошла успешно!");
                TimeUnit.SECONDS.sleep(2);
                SceneLoader.getInstance().switchScene(SceneType.SignInView);
            }
        }
    }

    private boolean dataValidation(String repeatPassword, String password, String login, String nickname) {
        if (password.length() < 5 && !password.isEmpty()) {
            warningText.setText("Пароль должен быть не менее 5 символов");
            return false;
        }

        if (login.length() < 5 && !login.isEmpty()) {
            warningText.setText("Логин должен быть не менее 5 символов");
            return false;
        }

        if (!repeatPassword.equals(password)) {
            warningText.setText("Пароли не совпадают");
            return false;
        }

        if (nickname.length() < 5) {
            warningText.setText("Никнейм слишком короткий");
            return false;
        }

        return true;
    }
}
