package com.server.models;

import java.io.Serializable;

public class StocksHistoricalModel implements Serializable {
    public String date;
    public double open;
    public double high;
    public double low;
    public double close;
    public double change;
    public double changePercent;
    public String label;
}
