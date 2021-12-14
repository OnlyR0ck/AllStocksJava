drop table if exists `stocks_info`;

CREATE TABLE IF NOT EXISTS `allstocks`.stocks_info (
	`stock_id` int NOT NULL auto_increment,
    `symbol` VARCHAR(10) CHARACTER SET utf8mb4,
    `name` VARCHAR(20) CHARACTER SET utf8mb4,
    `price` NUMERIC(8, 2),
    `changesPercentage` NUMERIC(9, 2),
    `change` NUMERIC(9, 3),
    `dayLow` NUMERIC(5, 2),
    `dayHigh` NUMERIC(5, 2),
    `yearHigh` NUMERIC(5, 2),
    `yearLow` NUMERIC(4, 1),
    `marketCap` NUMERIC(14, 1),
    `priceAvg50` NUMERIC(8, 2),
    `priceAvg200` NUMERIC(8, 2),
    `open` NUMERIC(5, 2),
    `previousClose` NUMERIC(5, 2),
    PRIMARY KEY (`stock_id`)
)DEFAULT CHARSET=utf8mb3;