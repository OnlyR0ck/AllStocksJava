package com.gui;

import com.client.implementation.AllClient;
import com.client.services.SearchService;
import com.server.commands.ServerCommandType;
import com.server.models.CompanyInfoModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Vector;

public class UserViewController {
    @FXML
    private Label addressLabel;

    @FXML
    private Label ceoLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private Tab companyInfoTab;

    @FXML
    private ImageView companyLogoImage;

    @FXML
    private Label companyNameTitle;

    @FXML
    private Label countryLabel;

    @FXML
    private Label currencyLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label employeesLabel;

    @FXML
    private Label industryLabel;

    @FXML
    private Label ipoDateLabel;

    @FXML
    private Tab keyMetricsTab;

    @FXML
    private Label numberLabel;

    @FXML
    private Label sectorLabel;

    @FXML
    private Label stateLabel;

    @FXML
    private Tab ticketDailyTab;

    @FXML
    private Tab ticketRangedTab;

    @FXML
    private Label ticketTextLabel;

    @FXML
    private Label websiteLabel;

    @FXML
    private Label zipLabel;

    @FXML
    void initialize() {
        updateView();
    }

    private void updateView() {
        String searchTerm = SearchService.getInstance().getSearchTerm();
        String clientRequest = String.format("%s %s", ServerCommandType.CompanyInfo, searchTerm);
        AllClient client = AllClient.getInstance();
        client.sendData(clientRequest);

        Vector<CompanyInfoModel> infoModels = client.receiveInfoModels();
        CompanyInfoModel infoModel = infoModels.get(0);

        setReceivedData(infoModel);
    }

    private void setReceivedData(CompanyInfoModel infoModel) {
        companyNameTitle.setText(infoModel.companyName);
        currencyLabel.setText(infoModel.currency);
        industryLabel.setText(infoModel.industry);
        websiteLabel.setText(infoModel.website);
        descriptionLabel.setText(infoModel.description);
        ceoLabel.setText(infoModel.ceo);
        sectorLabel.setText(infoModel.sector);
        countryLabel.setText(infoModel.country);
        employeesLabel.setText(infoModel.fullTimeEmployees);
        numberLabel.setText(infoModel.phone);
        addressLabel.setText(infoModel.address);
        cityLabel.setText(infoModel.city);
        stateLabel.setText(infoModel.state);
        zipLabel.setText(infoModel.zip);
        ipoDateLabel.setText(infoModel.ipoDate);

        Image logoImage = new Image(infoModel.image);
        companyLogoImage.setImage(logoImage);
    }

}
/*public class CompanyInfoModel implements Serializable {
    public String symbol;
    public long mktCap;
    public String companyName;
    public String currency;
    public String industry;
    public String website;
    public String description;
    public String ceo;
    public String sector;
    public String country;
    public String fullTimeEmployees;
    public String phone;
    public String address;
    public String city;
    public String state;
    public String zip;
    public String image;
    public String ipoDate;
}*/
