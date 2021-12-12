package com.server.database.enums;

public enum KeyMetrics {
    Table {
        @Override
        public String toString() {
            return "key_metrics";
        }
    },
    Id {
        @Override
        public String toString() {
            return "keymetrics_id";
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
    MarketCap {
        @Override
        public String toString() {
            return "marketCap";
        }
    },
    EnterpriseValue {
        @Override
        public String toString() {
            return "enterpriseValue";
        }
    },
    PERatio {
        @Override
        public String toString() {
            return "peRatio";
        }
    },
    PSRatio {
        @Override
        public String toString() {
            return "priceToSalesRatio";
        }
    },
    PBRatio {
        @Override
        public String toString() {
            return "pbRatio";
        }
    },
    EvToS {
        @Override
        public String toString() {
            return "evToSales";
        }
    },
    EvToEbitda {
        @Override
        public String toString() {
            return "enterpriseValueOverEBITDA";
        }
    },
    Roic {
        @Override
        public String toString() {
            return "roic";
        }
    },
    Roe {
        @Override
        public String toString() {
            return "roe";
        }
    }
}

/*
* `symbol` VARCHAR(4) ,
    `date` DATETIME,
    `marketCap` NUMERIC(16, 3),
    `enterpriseValue` NUMERIC(16, 3),
    `peRatio` NUMERIC(17, 15),
    `priceToSalesRatio` NUMERIC(16, 15),
    `pbRatio` NUMERIC(16, 14),
    `evToSales` NUMERIC(16, 15),
    `enterpriseValueOverEBITDA` NUMERIC(17, 15),
    `roic` NUMERIC(18, 17),
    `roe` NUMERIC(17, 16)*/