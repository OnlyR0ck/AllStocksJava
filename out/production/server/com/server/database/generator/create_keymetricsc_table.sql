drop table if exists `key_metrics`;

CREATE TABLE IF NOT EXISTS key_metrics (
    `keymetrics_id` int not null unique auto_increment,
    `symbol` VARCHAR(5) unique ,
    `date` DATETIME,
    `marketCap` NUMERIC(20, 2),
    `enterpriseValue` NUMERIC(20, 2),
    `peRatio` NUMERIC(17, 2),
    `priceToSalesRatio` NUMERIC(16, 2),
    `pbRatio` NUMERIC(16, 2),
    `evToSales` NUMERIC(16, 2),
    `enterpriseValueOverEBITDA` NUMERIC(17, 2),
    `roic` NUMERIC(18, 2),
    `roe` NUMERIC(17, 2),
    primary key(`keymetrics_id`)
)default charset=utf8mb4;