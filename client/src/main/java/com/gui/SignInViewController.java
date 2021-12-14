package com.gui;

import com.client.enumerations.SceneType;
import com.client.implementation.AllClient;
import com.client.services.UserService;
import com.server.commands.ServerCommandType;
import com.server.models.UserModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Vector;

public class SignInViewController {

    @FXML
    private Button enterButton;

    @FXML
    private TextField loginEnterField;

    @FXML
    private PasswordField passwordEnterField;

    @FXML
    private Label messageLabel;

    @FXML
    void initialize() {
        loginEnterField.setOnAction(event -> {
            messageLabel.setText("");
        });
        passwordEnterField.setOnAction(event -> {
            clearMessageLabel();
        });
        enterButton.setOnMouseClicked(mouseEvent -> {
            clearMessageLabel();
            EnterButton_OnClick();
        });
    }

    private void EnterButton_OnClick() {
        String password = passwordEnterField.getText().trim();
        String login = loginEnterField.getText().trim();

        String clientRequest = String.format("%s %s %s", ServerCommandType.Login, login, password);

        AllClient client = AllClient.getInstance();
        client.sendData(clientRequest);

        Vector<UserModel> users = client.receiveModels();

        if (users.size() != 0) {
            UserModel user = users
                    .stream()
                    .filter(userModel -> userModel.login.equals(login)
                            && userModel.password.equals(password)).toList().get(0);
            UserService.getInstance().setUser(user);

            switch (user.role) {
                case -1: {
                    messageLabel.setText("Вы заблокированы!.");
                    enterButton.setText("Пойти нахуй");
                    break;
                }
                case 0: {
                    SceneLoader.getInstance().switchScene(SceneType.SearchView);
                    break;
                }
                case 1: {
                    SceneLoader.getInstance().switchScene(SceneType.AdminView);
                    break;
                }
            }
        } else {
            messageLabel.setText("Пользователь не найден.");
        }
    }

    private void clearMessageLabel() {
        messageLabel.setText("");
    }
}

