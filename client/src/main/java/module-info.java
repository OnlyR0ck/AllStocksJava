module com.gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires server;

    /*opens com.client.client to javafx.fxml;
    exports com.client.client;*/

    opens com.gui to javafx.fxml;
    exports com.gui;
    exports com.client.implementation;
    opens com.client.implementation to javafx.fxml;
    exports com.client.services;
    opens com.client.services to javafx.fxml;
}