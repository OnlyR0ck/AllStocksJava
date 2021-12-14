module server{
    exports com.server.models;
    exports com.server.commands;
    requires java.sql;
    requires com.google.gson;
/*
    requires java.connector.sql;
*/
}