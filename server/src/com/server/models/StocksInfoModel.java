package com.server.models;

import java.io.Serializable;
import java.util.List;

public class StocksInfoModel implements Serializable {
    public String symbol;
    public String name;
    public double price;
    public double changesPercentage;
    public double change;
    public double dayLow;
    public double dayHigh;
    public double yearHigh;
    public double yearLow;
    public double marketCap;
    public double priceAvg50;
    public double priceAvg200;
    public double open;
    public double previousClose;
}


