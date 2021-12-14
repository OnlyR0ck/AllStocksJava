package com.gui;

import com.client.enumerations.SceneType;
import com.client.services.SearchService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SearchViewController {
    @FXML
    private TextField searchTermField;

    @FXML
    private Button startSearchButton;

    @FXML
    void initialize()
    {
        startSearchButton.setOnMouseClicked(mouseEvent ->
        {
            String term = searchTermField.getText();

            if (!term.isEmpty() && !term.isBlank()) {
                SearchService.getInstance().setSearchTerm(term);
                SceneLoader.getInstance().switchScene(SceneType.UserView);
            }
        });
    }
}
