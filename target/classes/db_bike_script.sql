DROP SCHEMA IF EXISTS `bike_db`;

CREATE SCHEMA IF NOT EXISTS `bike_db`
CHARACTER SET `utf8`;

USE `bike_db`;

CREATE TABLE `users` (
  `id`  INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `login`   	VARCHAR(255),
  `email`    	VARCHAR(255), 
  `password`   	VARCHAR(255),
  `first_name`	VARCHAR(255),
  `last_name`	VARCHAR(255),
  `tel`	        VARCHAR(255),
  `city`		VARCHAR(255),
  `country`		VARCHAR(255),
  `gender`	    VARCHAR(255),
  `birth`	    DATE,
  `role`        INTEGER
);

CREATE TABLE `points` (
  `id`          	INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `num_point`       VARCHAR(255),
  `slots`	        INTEGER,
  `free_bikes`		INTEGER,
  `longitude`		DECIMAL (10,8),
  `latitude`	    DECIMAL (10,8),
  `address_mark`	VARCHAR(255),
  `active_status`   INTEGER DEFAULT "1",
  `description`     VARCHAR(255)
);

CREATE TABLE `prices` (
	`id`                INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`tarif`             DECIMAL (10,2),
	`description`       VARCHAR(255)
);

CREATE TABLE `bikes` (
    `id`                INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `vin`          VARCHAR(255),
    `description` 	    VARCHAR(255),
    `point_id`          INTEGER,
    `available_status`  INTEGER,
    `condit`    	    VARCHAR(255),
    `price_id`          INTEGER, 
    
    CONSTRAINT `fk_bike_to_point` FOREIGN KEY (`point_id`) REFERENCES `points` (`id`)
       ON DELETE CASCADE
       ON UPDATE CASCADE,
	
    CONSTRAINT `fk_bike_to_price` FOREIGN KEY (`price_id`) REFERENCES `prices` (`id`)
       ON DELETE CASCADE
       ON UPDATE CASCADE
	);
	
CREATE TABLE `orders` (
  `id`          	INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `user_id`	        INTEGER,
  `bike_id`         INTEGER,
  `start_point_id`	INTEGER,
  `start_time`		DATETIME,
  `finish_point_id` INTEGER,
  `finish_time`		DATETIME,
  `total_cost`    	DECIMAL (10,2),
  `status`          VARCHAR(255),
  `description`     VARCHAR(255),
 
  CONSTRAINT `fk_order_to_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    
  CONSTRAINT `fk_order_to_bike` FOREIGN KEY (`bike_id`) REFERENCES `bikes` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    
  CONSTRAINT `fk_start_order_to_point` FOREIGN KEY (`start_point_id`) REFERENCES `points` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    
  CONSTRAINT `fk_finish_order_to_point` FOREIGN KEY (`finish_point_id`) REFERENCES `points` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

INSERT INTO `users` (`login`, `email`, `password`, `first_name`, `last_name`, `tel`, `city`, `country`, `gender`, `role`) VALUES ('admin', 'a@a.com', 'admin', 'Admin', 'Adminovich', 'not_indicated', 'Minsk', 'Belarus', 'not_indicated', '1');
INSERT INTO `users` (`login`, `email`, `password`, `first_name`, `last_name`, `tel`, `city`, `country`, `gender`, `role`) VALUES ('u', 'u@u.com', 'u', 'User', 'Userovich', 'not_indicated', 'Minsk', 'Belarus', 'not_indicated', '0');

INSERT INTO `points` (`num_point`, `slots`, `free_bikes`, `latitude`, `longitude`, `address_mark`) VALUES ('10', '5', '2', '53.9023', '27.5614', '34 Karlova av.');
INSERT INTO `points` (`num_point`, `slots`, `free_bikes`, `latitude`, `longitude`, `address_mark`) VALUES ('20', '5', '3', '53.9046', '27.5747', '80 Petrova st.');
INSERT INTO `points` (`num_point`, `slots`, `free_bikes`, `latitude`, `longitude`, `address_mark`) VALUES ('30', '5', '0', '53.8984', '27.5611', '37 Bondara av.');
INSERT INTO `points` (`num_point`, `slots`, `free_bikes`, `latitude`, `longitude`, `address_mark`) VALUES ('40', '5', '0', '53.9204', '27.6123', '76 Koltsova st.');
INSERT INTO `points` (`num_point`, `slots`, `free_bikes`, `latitude`, `longitude`, `address_mark`) VALUES ('50', '5', '0', '53.9366', '27.5701', '2 Ivanova av.');
INSERT INTO `points` (`num_point`, `slots`, `free_bikes`, `latitude`, `longitude`, `address_mark`) VALUES ('60', '5', '0', '53.8975', '27.6164', '45 Sidorova st.');
INSERT INTO `points` (`num_point`, `slots`, `free_bikes`, `latitude`, `longitude`, `address_mark`) VALUES ('70', '5', '0', '53.8846', '27.4939', '7 Dinova av.');
INSERT INTO `points` (`num_point`, `slots`, `free_bikes`, `latitude`, `longitude`, `address_mark`) VALUES ('80', '5', '0', '53.9360', '27.4338', '10 Nikolaeva st.');
INSERT INTO `points` (`num_point`, `slots`, `free_bikes`, `latitude`, `longitude`, `address_mark`) VALUES ('90', '5', '0', '53.9636', '27.5450', '175 Scorinov av.');
INSERT INTO `points` (`num_point`, `slots`, `free_bikes`, `latitude`, `longitude`, `address_mark`) VALUES ('95', '5', '0', '53.8531', '27.5778', '32 Malaya av.');

INSERT INTO `prices` (`tarif`, `description`) VALUES ('0.10', 'standard');
INSERT INTO `prices` (`tarif`, `description`) VALUES ('0.15', 'comfort');
INSERT INTO `prices` (`tarif`, `description`) VALUES ('0.20', 'sport');

INSERT INTO `bikes` (`vin`, `description`, `point_id`, `available_status`, `condit`, `price_id`) VALUES ('1001B', 'bikeStandard.jpg', '1', '1', 'good', '1');
INSERT INTO `bikes` (`vin`, `description`, `point_id`, `available_status`, `condit`, `price_id`) VALUES ('1002D', 'bikeStandard.jpg', '2', '1', 'good', '1');
INSERT INTO `bikes` (`vin`, `description`, `point_id`, `available_status`, `condit`, `price_id`) VALUES ('1003X', 'bikeComfort.jpg',  '2', '1', 'good', '2');
INSERT INTO `bikes` (`vin`, `description`, `point_id`, `available_status`, `condit`, `price_id`) VALUES ('1004H', 'bikeSport.jpg',    '1', '1', 'good', '3');
INSERT INTO `bikes` (`vin`, `description`, `point_id`, `available_status`, `condit`, `price_id`) VALUES ('1005M', 'bikeStandard.jpg', '2', '1', 'good', '1');

INSERT INTO `orders` (`user_id`, `bike_id`, `start_point_id`, `start_time`, `finish_point_id`, `finish_time`, `status`, `total_cost`) VALUES ('2', '1', '1', '2015-09-03 19:16:00', '2', '2015-09-03 20:16:00', 'complete', '6');