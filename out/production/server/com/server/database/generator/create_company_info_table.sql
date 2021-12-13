drop table if exists `company_info`;

CREATE TABLE IF NOT EXISTS company_info (
    `company_id` int NOT null unique auto_increment,
    `symbol` VARCHAR(5) not null unique,
    `companyName` VARCHAR(20) ,
    `currency` VARCHAR(5) ,
    `industry` VARCHAR(30) ,
    `website` VARCHAR(30) ,
    `description` VARCHAR(2000) ,
    `ceo` VARCHAR(20) ,
    `sector` VARCHAR(20) ,
    `country` VARCHAR(10) ,
    `fullTimeEmployees` INT,
    `phone` BIGINT,
    `address` VARCHAR(30) ,
    `city` VARCHAR(20) ,
    `state` VARCHAR(15) ,
    `zip` INT,
    `image` VARCHAR(80),
    `ipoDate` DATETIME,
    PRIMARY KEY(`company_id`)
) default charset = utf8mb4;