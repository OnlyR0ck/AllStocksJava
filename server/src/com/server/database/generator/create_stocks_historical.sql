drop table if exists `stocks_historical`;

CREATE TABLE IF NOT EXISTS stocks_historical (
	`stock_id` int not null auto_increment,
    `symbol` VARCHAR(10) not null,
    `date` DATETIME not null,
    `open` NUMERIC(6, 2),
    `high` NUMERIC(6, 2),
    `low` NUMERIC(6, 2),
    `close` NUMERIC(5, 2),
    `change` NUMERIC(4, 1),
    `changePercent` NUMERIC(4, 1),
    `label` VARCHAR(30),
    primary key (`stock_id`)
)default charset = utf8mb4;