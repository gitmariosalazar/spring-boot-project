-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: hexagonal
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id_product` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `iva` decimal(38,2) DEFAULT NULL,
  `mark` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `percentage_increment` decimal(38,2) NOT NULL,
  `public_price` decimal(38,2) NOT NULL,
  `quantity` int NOT NULL,
  `supplier_price` decimal(38,2) NOT NULL,
  PRIMARY KEY (`id_product`),
  UNIQUE KEY `UKh3w5r1mx6d0e5c6um32dgyjej` (`code`),
  UNIQUE KEY `UKjmivyxk9rmgysrmsqw15lqr5b` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'PT001TECH','This mouse is black',12.00,'SONY','Mouse keyboard',35.00,18.69,4,12.36),(2,'PT002TECH','This computer is on stock',12.00,'DELL','Laptop Dell Inspiron 5567 16 GB RAM',35.00,1667.09,151,1102.57),(3,'PT003TECH','Ergonomic wireless mouse',12.00,'LOGITECH','Wireless Mouse',35.00,38.56,12,25.50),(4,'PT004TECH','Docking station for laptops',12.00,'BELKIN','USB-C Docking Station',35.00,196.54,6,129.99),(5,'PT005TECH','Noise-canceling Bluetooth headset',12.00,'BOSE','Bluetooth Headset',35.00,302.32,18,199.95),(6,'PT006TECH','27 inch 4K Ultra HD monitor',12.00,'SAMSUNG','4K Monitor',35.00,527.69,20,349.00),(7,'PT007TECH','RGB mechanical keyboard',12.00,'RAZER','Mechanical Keyboard',35.00,227.93,47,150.75),(8,'PT008TECH','Portable solid-state drive',12.00,'SANDISK','External SSD 1TB',35.00,166.30,8,109.99),(9,'PT009TECH','Surround sound gaming headset',12.00,'LOGITECH','Gaming Headset',35.00,136.06,18,89.99),(10,'PT010TECH','Protective sleeve for 15-inch laptops',12.00,'TARGUS','Laptop Sleeve',35.00,39.30,10,25.99),(11,'PT011TECH','Fast-charging power bank',12.00,'ANKER','Portable Charger 10000mAh',35.00,45.34,29,29.99),(12,'PT012TECH','Fast charging USB-C cable',12.00,'ANKER','Smartphone Charger',35.00,22.68,5,15.00),(13,'PT013TECH','High-speed flash drive',12.00,'SANDISK','USB Flash Drive 64GB',35.00,30.22,23,19.99),(14,'PT014TECH','Water-resistant smart watch',12.00,'FITBIT','Smart Watch',35.00,196.48,20,129.95),(15,'PT015TECH','Adjustable laptop stand',12.00,'MOFT','Laptop Stand',35.00,45.28,15,29.95),(16,'PT016TECH','Qi wireless charging pad',12.00,'CHOETECH','Wireless Charger',35.00,30.22,39,19.99),(17,'PT017TECH','Portable mini projector',12.00,'ELEPHAS','Mini Projector',35.00,196.54,12,129.99),(18,'PT018TECH','Portable wireless speaker',12.00,'JBL','Bluetooth Speaker',35.00,90.64,39,59.95),(19,'PT019TECH','True wireless earbuds with ANC',12.00,'SONY','Noise-Canceling Earbuds',35.00,302.38,24,199.99),(20,'PT020TECH','Adjustable tablet stand',12.00,'TARGUS','Tablet Stand',35.00,37.78,24,24.99),(21,'PT021TECH','Car phone mount holder',12.00,'MOKO','Smartphone Holder',35.00,19.64,21,12.99),(22,'PT022TECH','Virtual Reality headset',12.00,'OCULUS','VR Headset',35.00,604.78,3,399.99),(23,'PT023TECH','4K Action camera',12.00,'GOPro','Action Camera',35.00,377.98,37,249.99),(24,'PT024TECH','Smart home controller hub',12.00,'Amazon','Smart Home Hub',35.00,151.18,21,99.99),(25,'PT025TECH','Drone with 4K camera',12.00,'DJI','Drone with Camera',35.00,907.18,3,599.99);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `returns`
--

DROP TABLE IF EXISTS `returns`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `returns` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `reason` varchar(255) NOT NULL,
  `return_date` date NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `selling` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKpgpr2lg81cxtkorytrw8x5629` (`selling`),
  CONSTRAINT `FKv07tsrjosatlab14yscafogj` FOREIGN KEY (`selling`) REFERENCES `selling` (`id_selling`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `returns`
--

LOCK TABLES `returns` WRITE;
/*!40000 ALTER TABLE `returns` DISABLE KEYS */;
INSERT INTO `returns` VALUES (1,'string','2024-12-21','annulled',1);
/*!40000 ALTER TABLE `returns` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `selling`
--

DROP TABLE IF EXISTS `selling`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `selling` (
  `id_selling` bigint NOT NULL AUTO_INCREMENT,
  `iva` decimal(38,2) DEFAULT NULL,
  `selling_code` varchar(255) NOT NULL,
  `selling_date` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `subtotal` decimal(38,2) DEFAULT NULL,
  `total` decimal(38,2) DEFAULT NULL,
  `id_user` bigint NOT NULL,
  PRIMARY KEY (`id_selling`),
  UNIQUE KEY `UKo76knn45mkqiaa5ok1gxnj901` (`selling_code`),
  KEY `FKd30yh6vq1y7bkfga5rc5eilgc` (`id_user`),
  CONSTRAINT `FKd30yh6vq1y7bkfga5rc5eilgc` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `selling`
--

LOCK TABLES `selling` WRITE;
/*!40000 ALTER TABLE `selling` DISABLE KEYS */;
INSERT INTO `selling` VALUES (1,367.24,'09c81052-4f33-47a2-859f-47d034743c4e','2024-12-19','delivered',3060.39,3427.63,2),(2,121.97,'d2cd8566-bd86-4eba-8003-bddb37f8e621','2024-12-19','delivered',1016.45,1138.42,1),(3,129.59,'09011386-a298-42c3-b967-fb6c2e8ee3b1','2024-12-19','delivered',1079.91,1209.50,10),(4,303.73,'3842d025-bb4f-43d4-8aa9-7ba2c77d4c0c','2024-12-19','delivered',2531.15,2834.88,5),(5,73.00,'3cedf505-81fc-4a68-9e92-aac541df09b8','2024-12-19','delivered',608.33,681.33,7),(6,46.96,'f5e6a577-fa60-4fab-b1d5-08dfe4eeb007','2024-12-20','delivered',391.36,438.32,1),(7,421.00,'e2cc9342-259b-4d60-9f4e-08e0126b3e48','2024-12-19','delivered',3508.44,3929.44,2),(8,215.36,'f53bc42f-6830-4507-824d-c5e4b0f48bc3','2024-12-20','delivered',1794.70,2010.06,2),(9,279.71,'cf299ec9-da0f-4b73-bf4a-61b3d5f74f68','2024-12-20','delivered',2330.96,2610.67,1),(10,306.43,'e9f132e2-5d12-42ad-b5c4-05c3996753c8','2024-12-20','delivered',2553.62,2860.05,7);
/*!40000 ALTER TABLE `selling` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `selling_items`
--

DROP TABLE IF EXISTS `selling_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `selling_items` (
  `id_selling_item` bigint NOT NULL AUTO_INCREMENT,
  `iva` decimal(38,2) NOT NULL,
  `quantity` int NOT NULL,
  `subtotal` decimal(38,2) NOT NULL,
  `total_price` decimal(38,2) NOT NULL,
  `unit_price` decimal(38,2) NOT NULL,
  `product` bigint NOT NULL,
  `selling` bigint NOT NULL,
  PRIMARY KEY (`id_selling_item`),
  KEY `FKfx7u0qh6fcoy0m4qkg3iexsuc` (`product`),
  KEY `FKfl5882b1gdb4jtc04qr0wfcj8` (`selling`),
  CONSTRAINT `FKfl5882b1gdb4jtc04qr0wfcj8` FOREIGN KEY (`selling`) REFERENCES `selling` (`id_selling`),
  CONSTRAINT `FKfx7u0qh6fcoy0m4qkg3iexsuc` FOREIGN KEY (`product`) REFERENCES `product` (`id_product`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `selling_items`
--

LOCK TABLES `selling_items` WRITE;
/*!40000 ALTER TABLE `selling_items` DISABLE KEYS */;
INSERT INTO `selling_items` VALUES (1,10.01,5,83.44,93.45,18.69,1,1),(2,357.23,2,2976.95,3334.18,1667.09,2,1),(3,97.68,4,814.04,911.72,227.93,7,2),(4,3.24,1,26.98,30.22,30.22,13,2),(5,21.05,1,175.43,196.48,196.48,14,2),(6,81.00,2,674.96,755.96,377.98,23,3),(7,48.59,3,404.95,453.54,151.18,24,3),(8,12.14,3,101.20,113.34,37.78,20,4),(9,291.59,3,2429.95,2721.54,907.18,25,4),(10,24.42,1,203.51,227.93,227.93,7,5),(11,6.48,2,53.96,60.44,30.22,13,5),(12,42.10,2,350.86,392.96,196.48,14,5),(13,9.71,3,80.95,90.66,30.22,13,6),(14,19.43,4,161.93,181.36,45.34,11,6),(15,17.82,1,148.48,166.30,166.30,8,6),(16,97.17,3,809.79,906.96,302.32,5,7),(17,226.15,4,1884.61,2110.76,527.69,6,7),(18,97.68,4,814.04,911.72,227.93,7,7),(19,32.39,1,269.93,302.32,302.32,5,8),(20,113.08,2,942.30,1055.38,527.69,6,8),(21,48.84,2,407.02,455.86,227.93,7,8),(22,21.05,5,175.45,196.50,39.30,10,8),(23,32.39,1,269.93,302.32,302.32,5,9),(24,113.08,2,942.30,1055.38,527.69,6,9),(25,24.42,1,203.51,227.93,227.93,7,9),(26,12.63,3,105.27,117.90,39.30,10,9),(27,97.19,3,809.95,907.14,302.38,18,9),(28,32.39,1,269.93,302.32,302.32,5,10),(29,113.08,2,942.30,1055.38,527.69,6,10),(30,24.42,1,203.51,227.93,227.93,7,10),(31,12.63,3,105.27,117.90,39.30,10,10),(32,97.19,3,809.95,907.14,302.38,18,10),(33,2.43,1,20.25,22.68,22.68,12,10),(34,3.24,1,26.98,30.22,30.22,13,10),(35,21.05,1,175.43,196.48,196.48,14,10);
/*!40000 ALTER TABLE `selling_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id_user` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `identification` varchar(255) NOT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UK88ud7ehaqhr1hexbhnpmph9wy` (`identification`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'El Tejar - Ibarra','mariosalazar.ms.10@gmail.com','Mario','1003938477','Salazar','$2a$10$qSrzwo5QdzClnEbhYGak0.xHSTMT6ddysbKYZeOtpbojsug0ES3WO','0994532438',''),(2,'El Tejar - Ibarra','lizbeth@gmail.com','Lizbeth','1003938410','Ruales','$2a$10$.mdnj1PZ.124SicT2LJubO5GRxgwKjXPhvkKs1MeguF56p.4cOZwu','0994532438',''),(3,'El Tejar - Ibarra','ricardo@gmail.com','Ricardo','1003938411','Gonzales','$2a$10$ssEnhWd/KE2QDpg.griQD.bQgmQZg3okkwZnczycPrmgRUnJijDRi','0994532438',''),(4,'El Tejar - Ibarra','mariajose@gmail.com','Maria Jose','1003938412','Salazar','$2a$10$SZ7T6Hxwb5iGenlb0UbA3ORTiMZkZUEYPcnmB2DxDGEROUuv4kX/K','0994532438',''),(5,'El Tejar - Ibarra','melany@gmail.com','Melany','1003938413','Escobar','$2a$10$pp9OwzwFF1ZJCSxwZ3oRgOmZOT7yTEqUhx0X2OXdXAgWedN9rmOQu','0994532438',''),(6,'El Tejar - Ibarra','pedroalvarez@gmail.com','Pedro','1003938414','Alvarez','$2a$10$E/4yfs.zlNdTjliaCuaBg.Egb/qmfTkwuJXehPv/0xqXPO469xXzS','0994532438',''),(7,'El Tejar - Ibarra','clarity@gmail.com','Clarity','1003938415','June','$2a$10$dugKXGJ/./CPgegAN6RAnuLWbVec9iBbOoW56BIgPF3vr2YtLv7kC','0994532438',''),(8,'El Tejar - Ibarra','bjuan@gmail.com','Juan','1003938416','Bolivar','$2a$10$n4VueDI0cTT7PT4HFJHbFe2A9hBUzQy3EaXFLGNCCUh8ar6S604Ri','0994532438',''),(9,'El Tejar - Ibarra','cruzgermania@gmail.com','Germania','1003938417','De La Cruz','$2a$10$Xbnl88iy6XAWGta7DJiujeSAvYLO2jj4L1SlTblMembHoiGupqgJO','0994532438',''),(10,'El Tejar - Ibarra','andreasuarez@gmail.com','Andrea','1003938418','Suarez','$2a$10$XzrzgaOggGA0hoCeiXlhs.cuE6NHL6ELxLLiHN7.BSZkAa5rcUu6q','0994532438','');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-25 14:28:02
