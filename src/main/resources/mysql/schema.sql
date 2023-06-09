CREATE SCHEMA IF NOT EXISTS `exam_51` DEFAULT CHARACTER SET utf8 ;
USE `exam_51` ;

DROP TABLE IF EXISTS `employee_department`;
DROP TABLE IF EXISTS `departments`;
DROP TABLE IF EXISTS `employee`;

CREATE TABLE IF NOT EXISTS `employee` (
    `id` BIGINT NOT NULL,
    `name` VARCHAR(45) NULL,
    PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `departments` (
    `id` VARCHAR(45) NOT NULL,
    `name` VARCHAR(45) NULL,
    PRIMARY KEY (`id`));


CREATE TABLE IF NOT EXISTS `employee_department` (
    `employee_id` BIGINT NOT NULL,
    `department_id` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`employee_id`, `department_id`),
    FOREIGN KEY (`employee_id`)
    REFERENCES `employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`department_id`)
    REFERENCES `departments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);