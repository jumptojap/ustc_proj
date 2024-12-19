-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: ustc_proj
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `machine_status`
--

DROP TABLE IF EXISTS `machine_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `machine_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sid` varchar(50) NOT NULL,
  `upload_time` timestamp NOT NULL,
  `cpu_usage` double DEFAULT NULL,
  `total_memory` bigint DEFAULT NULL,
  `free_memory` bigint DEFAULT NULL,
  `total_disk_space` bigint DEFAULT NULL,
  `free_disk_space` bigint DEFAULT NULL,
  `memory_usage` double DEFAULT NULL,
  `disk_usage` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `machine_status`
--

LOCK TABLES `machine_status` WRITE;
/*!40000 ALTER TABLE `machine_status` DISABLE KEYS */;
INSERT INTO `machine_status` VALUES (6,'49196','2024-12-18 12:39:58',0,16969424896,1925918720,403396620288,240813400064,88.65065415119652,40.30356528716719),(7,'30528','2024-12-18 12:42:48',1,16969424896,2286080000,403396620288,240813400064,86.52824115130224,40.30356528716719),(8,'49196','2024-12-18 12:44:57',0,16969424896,2085662720,403396620288,240813400064,87.709290486965,40.30356528716719),(9,'30528','2024-12-18 12:47:47',0,16969424896,2456891392,403396620288,240813293568,85.52165788141039,40.303591686991744),(10,'49196','2024-12-18 12:49:57',0,16969424896,2265489408,403396620288,240813293568,86.6495805138687,40.303591686991744),(11,'30528','2024-12-18 12:52:47',0,16969424896,2398834688,403396620288,240813293568,85.86378322953391,40.303591686991744),(12,'49196','2024-12-18 12:54:57',0,16969424896,2182299648,403396620288,240813228032,87.13981374516464,40.30360793303762),(13,'30528','2024-12-18 12:57:47',0,16969424896,2349748224,403396620288,240813228032,86.15304738728136,40.30360793303762),(14,'49196','2024-12-18 12:59:57',0,16969424896,2492178432,403396620288,240813228032,85.31371306173463,40.30360793303762),(15,'30528','2024-12-18 13:02:47',0,16969424896,2324529152,403396620288,240813228032,86.30166215858067,40.30360793303762),(16,'41764','2024-12-18 13:04:16',0.624,16969424896,2223661056,403396620288,240813228032,86.89607296871824,40.30360793303762),(17,'42052','2024-12-18 13:05:07',0.373015873015873,16969424896,2013712384,403396620288,240813228032,88.13329033634683,40.30360793303762),(18,'40112','2024-12-18 13:08:12',0.873015873015873,16969424896,2086080512,403396620288,240813228032,87.7068284589201,40.30360793303762),(19,'40112','2024-12-18 13:13:12',0,16969424896,2542456832,403396620288,240813228032,85.01742488280021,40.30360793303762),(20,'40112','2024-12-18 13:18:12',0,16969424896,2293256192,403396620288,240813228032,86.48595219900137,40.30360793303762),(21,'40112','2024-12-18 13:23:12',0,16969424896,2147909632,403396620288,240813228032,87.3424724458028,40.30360793303762),(22,'41636','2024-12-18 13:31:15',0.626984126984127,16969424896,2036621312,403396620288,240813228032,87.99828913188409,40.30360793303762),(23,'40048','2024-12-18 13:31:51',0.7479674796747967,16969424896,2170630144,403396620288,240813228032,87.20858156771325,40.30360793303762),(24,'2056','2024-12-18 13:33:48',0.6190476190476191,16969424896,2113138688,403396620288,240813228032,87.54737593671719,40.30360793303762),(25,'42280','2024-12-18 13:36:01',0,16969424896,2058440704,403396620288,240813228032,87.869708510362,40.30360793303762),(26,'32428','2024-12-18 13:37:05',0,16969424896,2176745472,403396620288,240813228032,87.17254423564408,40.30360793303762),(27,'22856','2024-12-18 13:37:28',0,16969424896,2047873024,403396620288,240813228032,87.93198333738039,40.30360793303762),(28,'34024','2024-12-18 13:38:36',0.8709677419354839,16969424896,2043678720,403396620288,240813228032,87.95670016794894,40.30360793303762),(29,'29504','2024-12-18 13:41:25',0.5,16969424896,1883725824,403396620288,240813228032,88.8992948462029,40.30360793303762),(30,'27364','2024-12-18 13:41:45',0,16969424896,1841975296,403396620288,240813228032,89.14532868798526,40.30360793303762),(31,'29504','2024-12-18 13:46:25',0,16969424896,1757511680,403396620288,240813228032,89.64306869106521,40.30360793303762),(32,'27364','2024-12-18 13:46:44',0,16969424896,1766813696,403396620288,240813228032,89.58825236077111,40.30360793303762),(33,'29504','2024-12-18 13:51:25',0,16969424896,1853509632,403396620288,240813228032,89.07735740392177,40.30360793303762),(34,'27364','2024-12-18 13:51:44',0,16969424896,1865703424,403396620288,240813228032,89.00549997755209,40.30360793303762),(35,'29504','2024-12-18 13:56:25',0,16969424896,1856163840,403396620288,240813228032,89.06171628457761,40.30360793303762),(36,'27364','2024-12-18 13:56:44',0,16969424896,1860276224,403396620288,240813228032,89.03748220460612,40.30360793303762),(37,'29504','2024-12-18 14:01:25',0,16969424896,1862770688,403396620288,240813211648,89.0227824489262,40.30361199454909),(38,'27364','2024-12-18 14:01:44',0,16969424896,1857753088,403396620288,240813211648,89.052350922995,40.30361199454909),(39,'28784','2024-12-18 14:22:17',1,16969424896,2237001728,403396620288,240813121536,86.81745703398998,40.30363433286217),(40,'29496','2024-12-18 14:24:27',0.7479674796747967,16969424896,1805910016,403396620288,240813121536,89.3578596383329,40.30363433286217),(41,'44444','2024-12-18 14:28:36',0.744,16969424896,1967386624,403396620288,240813121536,88.40628579897395,40.30363433286217),(42,'51140','2024-12-18 14:31:42',0,16969424896,1792102400,403396620288,240813121536,89.4392272514643,40.30363433286217),(43,'50976','2024-12-18 14:32:46',0,16969424896,1726955520,403396620288,240813121536,89.82313466376179,40.30363433286217),(44,'51140','2024-12-18 14:36:41',0,16969424896,2119667712,403396620288,240813121536,87.50890071413295,40.30363433286217),(45,'50976','2024-12-18 14:37:45',0,16969424896,2108960768,403396620288,240813121536,87.57199621716633,40.30363433286217),(46,'51140','2024-12-18 14:41:41',0,16969424896,2004348928,403396620288,240813121536,88.18846872958869,40.30363433286217),(47,'50976','2024-12-18 14:42:45',0,16969424896,1979604992,403396620288,240813121536,88.33428354742517,40.30363433286217),(48,'41964','2024-12-18 14:48:05',0,16969424896,2088181760,403396620288,240813121536,87.69444590610598,40.30363433286217),(49,'41964','2024-12-18 14:53:04',0,16969424896,2032709632,403396620288,240813121536,88.02134047289283,40.30363433286217),(50,'33376','2024-12-19 01:15:46',0,16969424896,1317023744,403396620288,240812707840,92.23884278888882,40.30373688602677),(51,'33376','2024-12-19 01:20:45',0,16969424896,1519046656,403396620288,240812707840,91.04833154152404,40.30373688602677),(52,'33376','2024-12-19 01:25:45',0,16969424896,1587118080,403396620288,240812707840,90.64718993291216,40.30373688602677),(53,'39932','2024-12-19 01:29:53',1,16969424896,2112172032,403396620288,240812707840,87.55307239376229,40.30373688602677),(54,'39932','2024-12-19 01:30:54',0,16969424896,2037395456,403396620288,240812707840,87.99372713874205,40.30373688602677),(55,'39932','2024-12-19 01:50:54',0,16969424896,2427547648,403396620288,240811724800,85.69457914527075,40.303980576714935),(56,'38752','2024-12-19 01:56:25',0.6190476190476191,16969424896,2695548928,403396620288,240811724800,84.11526056704851,40.303980576714935),(57,'25540','2024-12-19 01:57:22',1,16969424896,2538934272,403396620288,240811724800,85.03818315847302,40.303980576714935),(58,'42996','2024-12-19 01:57:33',0.8809523809523809,16969424896,2602971136,403396620288,240811724800,84.66081701676545,40.303980576714935),(59,'38976','2024-12-19 01:59:01',1,16969424896,2789138432,403396620288,240811724800,83.5637421474581,40.303980576714935),(60,'41564','2024-12-19 02:30:21',0.872,16969424896,2336501760,403396620288,240811573248,86.231108158823,40.30401814569603),(61,'23624','2024-12-19 02:40:13',1,16969424896,1921372160,403396620288,240811573248,88.67744680933234,40.30401814569603),(62,'31512','2024-12-19 05:48:57',1,16969424896,1997058048,403396620288,240810971136,88.23143353272542,40.30416740624252),(63,'31512','2024-12-19 05:53:56',0,16969424896,1758322688,403396620288,240810971136,89.63828946015448,40.30416740624252),(64,'40864','2024-12-19 05:56:22',0.624,16969424896,1888735232,403396620288,240810962944,88.8697746471938,40.30416943699826),(65,'49084','2024-12-19 05:57:49',0.504,16969424896,2043609088,403396620288,240810962944,87.95711050595642,40.30416943699826),(66,'26568','2024-12-19 07:39:13',0,16969424896,1840668672,403396620288,240809271296,89.15302856000807,40.304588788057465),(67,'43284','2024-12-19 07:39:51',0,16969424896,1884987392,403396620288,240809271296,88.89186048700846,40.304588788057465);
/*!40000 ALTER TABLE `machine_status` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-19 15:54:50