package com.server.models;

import java.io.Serializable;
import java.util.List;

public class StocksHistoricalModel implements Serializable {
    public String symbol;
    public List<StocksHistorical> historical;

    public class StocksHistorical implements Serializable{
        public String date;
        public double open;
        public double high;
        public double low;
        public double close;
        public double volume;
        public double change;
        public double changePercent;
        public String label;
        public double changeOverTime;
    }
}
