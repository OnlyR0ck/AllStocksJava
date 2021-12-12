package com.server.database.enums;

public enum StocksQuote {
    Table {
        @Override
        public String toString() {
            return "stocks_info";
        }
    },
    Id {
        @Override
        public String toString() {
            return "stock_id";
        }
    },
    Symbol {
        @Override
        public String toString() {
            return "symbol";
        }
    },
    Name {
        @Override
        public String toString() {
            return "name";
        }
    },
    Price {
        @Override
        public String toString() {
            return "price";
        }
    },
    ChangesPercentage {
        @Override
        public String toString() {
            return "changesPercentage";
        }
    },
    Change {
        @Override
        public String toString() {
            return "change";
        }
    },
    DayLow {
        @Override
        public String toString() {
            return "dayLow";
        }
    },
    DayHigh {
        @Override
        public String toString() {
            return "dayHigh";
        }
    },
    YearHigh {
        @Override
        public String toString() {
            return "yearHigh";
        }
    },
    YearLow {
        @Override
        public String toString() {
            return "yearLow";
        }
    },
    MarketCap {
        @Override
        public String toString() {
            return "marketCap";
        }
    },
    PriceAverage50 {
        @Override
        public String toString() {
            return "priceAvg50";
        }
    },
    PriceAverage200 {
        @Override
        public String toString() {
            return "priceAvg200";
        }
    },
    Open {
        @Override
        public String toString() {
            return "open";
        }
    },
    PreviousClose {
        @Override
        public String toString() {
            return "previousClose";
        }
    }
}

/*{
  "symbol" : "AAPL",
  "name" : "Apple Inc.",
  "price" : 146.15000000,
  "changesPercentage" : 2.60000000,
  "change" : 3.70000000,
  "dayLow" : 142.96000000,
  "dayHigh" : 147.09970000,
  "yearHigh" : 150.00000000,
  "yearLow" : 89.14500000,
  "marketCap" : 2438892617728.00000000,
  "priceAvg50" : 135.25772000,
  "priceAvg200" : 130.42052000,
  "open" : 143.46000000,
  "previousClose" : 142.45000000
}*/

