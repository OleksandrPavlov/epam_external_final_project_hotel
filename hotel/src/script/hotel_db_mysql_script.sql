DROP DATABASE IF EXISTS hotel;
CREATE DATABASE hotel
CHARACTER SET utf8
COLLATE utf8_general_ci;
USE hotel;

CREATE TABLE `user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
);

CREATE TABLE `room_classes` (
  `class_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(45) NOT NULL,
  PRIMARY KEY (`class_id`),
  UNIQUE KEY `name_UNIQUE` (`class_name`)
);

CREATE TABLE `facilities` (
  `facility_id` int(11) NOT NULL,
  `facility_name` varchar(45) NOT NULL,
  PRIMARY KEY (`facility_id`),
  UNIQUE KEY `facilities_name_UNIQUE` (`facility_name`)
);

CREATE TABLE `services` (
  `id` int(11) NOT NULL,
  `name` blob NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `rooms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` double NOT NULL DEFAULT '0',
  `seats_number` int(2) NOT NULL DEFAULT '1',
  `class_id` int(11) DEFAULT NULL,
  `picture_ref` varchar(45) DEFAULT NULL,
  `area` int(2) DEFAULT NULL,
  `short_name_rus` text NOT NULL,
  `short_name_en` text NOT NULL,
  `room_number` int(11) DEFAULT NULL,
  `availability` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `room_number_UNIQUE` (`room_number`),
  KEY `class_id_idx` (`class_id`),
  CONSTRAINT `class_id` FOREIGN KEY (`class_id`) REFERENCES `room_classes` (`class_id`) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT 'name',
  `surname` varchar(20) NOT NULL DEFAULT 'surname',
  `patronimic` varchar(20) DEFAULT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `mail` varchar(30) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `gender` int(11) DEFAULT NULL,
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `role_id` int(11) NOT NULL,
  `access_key` varchar(60) DEFAULT NULL,
  `activeStatus` varchar(10) DEFAULT 'disabled',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `password_UNIQUE` (`password`),
  UNIQUE KEY `mail_UNIQUE` (`mail`),
  UNIQUE KEY `phone_UNIQUE` (`phone`),
  KEY `role_id_idx` (`role_id`),
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `user_roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `bills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `night_number` int(2) NOT NULL DEFAULT '1',
  `total_price` double NOT NULL,
  `creation_date` date NOT NULL,
  PRIMARY KEY (`id`,`room_id`,`client_id`),
  KEY `room_id_idx` (`room_id`),
  CONSTRAINT `room_idI` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `bills_services` (
  `bill_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  PRIMARY KEY (`bill_id`,`service_id`),
  KEY `service_id_idx` (`service_id`),
  CONSTRAINT `bill_idI` FOREIGN KEY (`bill_id`) REFERENCES `bills` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `service_id` FOREIGN KEY (`service_id`) REFERENCES `services` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE `client_request` (
  `client_request_id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) NOT NULL,
  `seats_number` int(2) NOT NULL DEFAULT '1',
  `night_number` int(2) NOT NULL DEFAULT '1',
  `max_price` decimal(6,2) NOT NULL,
  `apartment_class` int(11) NOT NULL,
  `settlement_date` date NOT NULL,
  `comment` text,
  `viewed` int(11) DEFAULT '0',
  PRIMARY KEY (`client_request_id`),
  KEY `client_id_idx` (`client_id`),
  CONSTRAINT `client_id_fk` FOREIGN KEY (`client_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `cl_req_fac` (
  `client_req_id` int(11) NOT NULL,
  `facility_id` int(11) NOT NULL,
  PRIMARY KEY (`client_req_id`,`facility_id`),
  KEY `facility_id_fk_idx` (`facility_id`),
  CONSTRAINT `client_request_fk` FOREIGN KEY (`client_req_id`) REFERENCES `client_request` (`client_request_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `facility_id_fk` FOREIGN KEY (`facility_id`) REFERENCES `facilities` (`facility_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE `room_facility` (
  `room_id` int(11) NOT NULL,
  `facility_id` int(11) NOT NULL,
  PRIMARY KEY (`room_id`,`facility_id`),
  KEY `facility_id_idx` (`facility_id`),
  CONSTRAINT `facility_id` FOREIGN KEY (`facility_id`) REFERENCES `facilities` (`facility_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `room_id` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `client_bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_id` int(11) DEFAULT NULL,
  `night_number` int(11) NOT NULL DEFAULT '1',
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `Room_id_idx` (`room_id`),
  CONSTRAINT `Room_id_res` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE `manager_response` (
  `client_id` int(11) DEFAULT NULL,
  `room_id` int(11) NOT NULL,
  `begin_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`room_id`,`begin_date`),
  KEY `rooms_id_idx` (`room_id`),
  KEY `Client_id_restr` (`client_id`),
  CONSTRAINT `Client_id_restr` FOREIGN KEY (`client_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Rooms_id_restr` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `room_description` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_id` int(11) DEFAULT NULL,
  `rus` text,
  `en` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `room_id_UNIQUE` (`room_id`),
  CONSTRAINT `r_d` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `booked_rooms` (
  `client_id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `book_start` date NOT NULL,
  `book_end` date NOT NULL,
  `paid` varchar(1) DEFAULT 'y',
  `reserve_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`book_start`,`room_id`),
  KEY `rooms_id_idx` (`room_id`),
  KEY `clients_id` (`client_id`),
  CONSTRAINT `clients_id` FOREIGN KEY (`client_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rooms_id` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO `room_classes` VALUES (1,'econom'),(3,'improved'),(4,'luxury'),(2,'standard');

INSERT INTO `facilities` VALUES (2,'condition'),(4,'cooller'),(3,'tv'),(1,'wi-fi');

INSERT INTO `user_roles` VALUES (2,'client'),(1,'manager');

INSERT INTO `rooms` VALUES
(15,74.6,2,1,'room3.jpg',23,'Маленькая комната не на свои деньги','Excepteur sint occaecat cupidatat non proident',9,1),
(16,8.5,21,2,'room2.jpg',23,'Комната не в нашем отеле ','Sed ut perspiciatis, unde omnis iste natus error sit voluptatem ',6,1),
(20,87.6,2,1,'room3.jpg',22,'Это не плохая комната\r\n','On the other hand, we denounce with righteous indignation ',81,1);

INSERT INTO `room_description` VALUES
(14,15,'Эти листы надписи можно потереть на любом месте и были быстро приняты художники-графики, принтеры, архитекторов и рекламодателей для их профессионального вида ','Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.'),
(15,16,'чтобы предоставить текст-заполнитель для смоделируйте различных шрифтов для типа образца книги. Но это было только начало.','Sed ut perspiciatis, unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam eaque ipsa, quae ab illo inventore '),
(19,20,'Многие думают, что Lorem Ipsum - взятый с потолка псевдо-латинский набор слов, но это не совсем так. Его корни уходят в один фрагмент классической латыни 45 года н.э., то есть более двух тысячелетий назад. ','On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue;');

INSERT INTO `room_facility` VALUES (15,1),(16,1),(20,1),(16,2),(20,2),(16,4);

INSERT INTO `users` VALUES (16,'Oleksandr','Pavlov','Victorovich','repertuar','repertuar','oleksandrpavlov@gmail.com','+380683180013',1,DEFAULT,1,NULL,'enabled');

