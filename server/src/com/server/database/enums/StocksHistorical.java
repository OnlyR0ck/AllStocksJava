package com.server.database.enums;

public enum StocksHistorical {
    Table {
        @Override
        public String toString() {
            return "stocks_historical";
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
    Date {
        @Override
        public String toString() {
            return "date";
        }
    },
    Open {
        @Override
        public String toString() {
            return "open";
        }
    },
    High {
        @Override
        public String toString() {
            return "high";
        }
    },
    Low {
        @Override
        public String toString() {
            return "low";
        }
    },
    Close {
        @Override
        public String toString() {
            return "close";
        }
    },
    Change {
        @Override
        public String toString() {
            return "change";
        }
    },
    ChangePercent {
        @Override
        public String toString() {
            return "changePercent";
        }
    },
    Label {
        @Override
        public String toString() {
            return "label";
        }
    },
}

/*{
  "symbol" : "AAPL",
    "date" : "2021-12-10",
    "open" : 175.205,
    "high" : 179.63,
    "low" : 174.7,
    "close" : 179.45,
    "change" : 4.245,
    "changePercent" : 2.423,
    "label" : "December 10, 21"
}*/
