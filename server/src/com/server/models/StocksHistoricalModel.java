package com.server.models;

import java.util.List;

public class StocksHistoricalModel {
    public String symbol;
    public List<StocksHistorical> historical;

    public class StocksHistorical {
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
