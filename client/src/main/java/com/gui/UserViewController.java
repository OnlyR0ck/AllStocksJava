package com.gui;

import com.client.enumerations.SceneType;
import com.client.implementation.AllClient;
import com.client.services.SearchService;
import com.server.commands.ServerCommandType;
import com.server.models.*;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Date;
import java.util.List;
import java.util.Vector;

public class UserViewController {

    //region Company Info View
    @FXML
    private Tab companyInfoTab;

    @FXML
    private Label addressLabel;

    @FXML
    private Label ceoLabel;

    @FXML
    private Label cityLabel;

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
    private Label numberLabel;

    @FXML
    private Label sectorLabel;

    @FXML
    private Label stateLabel;

    @FXML
    private Label ticketTextLabel;

    @FXML
    private Label websiteLabel;

    @FXML
    private Label zipLabel;
    //endregion

    //region Key Metrics View

    @FXML
    private Tab keyMetricsTab;

    @FXML
    private Label ticketMetricsField;

    @FXML
    private Label dateTextField;

    @FXML
    private Label marketCapField;

    @FXML
    private Label pbRatioField;

    @FXML
    private Label peRatioField;

    @FXML
    private Label psRatioField;

    @FXML
    private Label roeField;

    @FXML
    private Label roicField;

    @FXML
    private Label evField;

    @FXML
    private Label evToEbitda;

    @FXML
    private Label evToSalesField;

    private boolean isKeyMetricsLoaded;

    //endregion

    //region TicketDaily

    @FXML
    private Tab ticketDailyTab;

    @FXML
    private Label companyNameDaily;

    @FXML
    private Label symbolDaily;

    @FXML
    private Label priceDaily;

    @FXML
    private Label changePercentageDaily;

    @FXML
    private Label changeDaily;

    @FXML
    private Label dayLow;

    @FXML
    private Label dayHigh;

    @FXML
    private Label yearLow;

    @FXML
    private Label yearHigh;

    @FXML
    private Label marketCapDaily;

    @FXML
    private Label priceAvg50;

    @FXML
    private Label priceAvg200;

    @FXML
    private Label openDailyPrice;

    @FXML
    private Label closeDailyPrice;

    private boolean isTicketDailyInfoLoaded;
    
    //endregion

    //region TicketRanged

    @FXML
    private Tab ticketRangedTab;

    @FXML
    private Button fiveDaysButton;

    @FXML
    private Button tenDaysButton;

    @FXML
    private Button twentyDaysButton;

    @FXML
    private Button monthButton;

    @FXML
    private AreaChart<String, Number> ticketPriceChart;

    private boolean isGraphLoaded;

    //endregion

    @FXML
    void onKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            SceneLoader.getInstance().switchScene(SceneType.SearchView);
        }
    }


    @FXML
    void initialize() {
        updateView();

        keyMetricsTab.setOnSelectionChanged(event ->
        {
            if(keyMetricsTab.isSelected())
            {
                if (!isKeyMetricsLoaded) {
                    loadKeyMetrics();
                }
            }
        });

        ticketDailyTab.setOnSelectionChanged(event ->
        {
            if(ticketRangedTab.isSelected())
            {
                if(!isGraphLoaded)
                {
                    loadTicketPriceGraph(5);
                }
            }
        });

        ticketDailyTab.setOnSelectionChanged(event ->
        {
            if(ticketDailyTab.isSelected())
            {
                if(!isTicketDailyInfoLoaded)
                {
                    updateDailyTicketTab();
                }
            }
        });

        fiveDaysButton.setOnMouseClicked(mouseEvent ->
        {
            loadTicketPriceGraph(5);
        });

        tenDaysButton.setOnMouseClicked(mouseEvent ->
        {
            loadTicketPriceGraph(10);
        });

        twentyDaysButton.setOnMouseClicked(mouseEvent ->
        {
            loadTicketPriceGraph(20);
        });

        monthButton.setOnMouseClicked(mouseEvent ->
        {
            loadTicketPriceGraph(30);
        });
    }

    private void loadTicketPriceGraph(int days) {
        String searchTerm = SearchService.getInstance().getSearchTerm();
        String clientRequest = String.
                format("%s %s %d", ServerCommandType.TicketInfo, searchTerm, days);

        AllClient client = AllClient.getInstance();
        client.sendData(clientRequest);

        Vector<StocksHistoricalModels> infoModels = client.receiveModels();
        StocksHistoricalModels infoModel = infoModels.get(0);

        setDataToGraph(infoModel, days);
    }

    private void setDataToGraph(StocksHistoricalModels infoModel, int days) {
        ticketPriceChart.getData().clear();
        String title = String.format("???????????? ?????????????????? ???????? %s ???? %d ????????",
                infoModel.symbol, days);


        Series seriesOpen = new Series();
        seriesOpen.setName("????????????????");

        Series seriesClose = new Series();
        seriesClose.setName("????????????????");

        ticketPriceChart.setTitle(title);

        for(StocksHistoricalModel model : infoModel.models)
        {
            seriesOpen.getData().add(new XYChart.Data<>(String.valueOf(model.date), model.open));
            seriesClose.getData().add(new XYChart.Data<>(String.valueOf(model.date), model.close));
        }
        ticketPriceChart.setAnimated(false);
        ticketPriceChart.getData().addAll(seriesOpen, seriesClose);
    }

    private void updateDailyTicketTab() {
        String searchTerm = SearchService.getInstance().getSearchTerm();
        String clientRequest = String.format("%s %s", ServerCommandType.StockQuote, searchTerm);
        AllClient client = AllClient.getInstance();
        client.sendData(clientRequest);

        Vector<StocksInfoModel> infoModels = client.receiveModels();
        StocksInfoModel infoModel = infoModels.get(0);

        setDailyTicketData(infoModel);
    }

    private void setDailyTicketData(StocksInfoModel model) {
        companyNameDaily.setText(model.name);
        symbolDaily.setText(model.symbol);
        priceDaily.setText(String.valueOf(model.price));
        changePercentageDaily.setText(String.valueOf(model.changesPercentage));
        changeDaily.setText(String.valueOf(model.change));
        dayLow.setText(String.valueOf(model.dayLow));
        dayHigh.setText(String.valueOf(model.dayHigh));
        yearLow.setText(String.valueOf(model.yearLow));
        yearHigh.setText(String.valueOf(model.yearHigh));
        marketCapDaily.setText(String.valueOf(model.marketCap));
        priceAvg50.setText(String.valueOf(model.priceAvg50));
        priceAvg200.setText(String.valueOf(model.priceAvg200));
        openDailyPrice.setText(String.valueOf(model.open));
        closeDailyPrice.setText(String.valueOf(model.previousClose));

        isTicketDailyInfoLoaded = true;
    }

    private void updateView() {

        String searchTerm = SearchService.getInstance().getSearchTerm();
        String clientRequest = String.format("%s %s", ServerCommandType.CompanyInfo, searchTerm);
        AllClient client = AllClient.getInstance();
        client.sendData(clientRequest);

        Vector<CompanyInfoModel> infoModels = client.receiveModels();
        CompanyInfoModel infoModel = infoModels.get(0);

        setReceivedData(infoModel);
    }

    private void setReceivedData(CompanyInfoModel infoModel) {
        ticketTextLabel.setText(infoModel.symbol);
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

    private void loadKeyMetrics() {
        String searchTerm = SearchService.getInstance().getSearchTerm();
        String clientRequest = String.format("%s %s", ServerCommandType.KeyMetrics, searchTerm);
        AllClient client = AllClient.getInstance();
        client.sendData(clientRequest);

        Vector<KeyMetricsModel> keyMetricsModels = client.receiveModels();
        KeyMetricsModel keyMetricsModel = keyMetricsModels.get(0);
        setKeyMetrics(keyMetricsModel);
    }

    private void setKeyMetrics(KeyMetricsModel keyMetricsModel) {
        ticketMetricsField.setText(keyMetricsModel.symbol);
        dateTextField.setText(keyMetricsModel.date);
        marketCapField.setText(String.valueOf(keyMetricsModel.marketCap));
        evField.setText(String.valueOf(keyMetricsModel.enterpriseValue));
        peRatioField.setText(String.valueOf(keyMetricsModel.peRatio));
        psRatioField.setText(String.valueOf(keyMetricsModel.priceToSalesRatio));
        pbRatioField.setText(String.valueOf(keyMetricsModel.pbRatio));
        evToSalesField.setText(String.valueOf(keyMetricsModel.evToSales));
        evToEbitda.setText(String.valueOf(keyMetricsModel.enterpriseValueOverEBITDA));
        roicField.setText(String.valueOf(keyMetricsModel.roic));
        roeField.setText(String.valueOf(keyMetricsModel.roe));

        isKeyMetricsLoaded = true;
    }

}