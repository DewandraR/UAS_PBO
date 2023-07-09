-- MariaDB dump 10.19  Distrib 10.4.28-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: penjualan
-- ------------------------------------------------------
-- Server version	10.4.28-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `barang`
--

DROP TABLE IF EXISTS `barang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `barang` (
  `kd_brg` char(10) NOT NULL,
  `nm_brg` char(25) DEFAULT NULL,
  `satuan` char(10) DEFAULT NULL,
  `harga_beli` double NOT NULL,
  `stok` int(5) DEFAULT NULL,
  `stok_min` int(4) NOT NULL,
  `harga_jual` double NOT NULL,
  PRIMARY KEY (`kd_brg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `barang`
--

LOCK TABLES `barang` WRITE;
/*!40000 ALTER TABLE `barang` DISABLE KEYS */;
INSERT INTO `barang` VALUES ('0001','Mie','bungkus',2000,100,10,3000),('0002','Kaos','lembar',30000,91,10,50000),('0003','Tumbler','buah',120000,95,10,150000),('0004','mainan','buah',10000,55,1,8000),('0005','jeans','lembar',50000,49,10,80000);
/*!40000 ALTER TABLE `barang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `beli`
--

DROP TABLE IF EXISTS `beli`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `beli` (
  `no_beli` int(10) NOT NULL,
  `kd_sup` char(6) NOT NULL,
  `tgl_beli` date NOT NULL,
  PRIMARY KEY (`no_beli`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beli`
--

LOCK TABLES `beli` WRITE;
/*!40000 ALTER TABLE `beli` DISABLE KEYS */;
INSERT INTO `beli` VALUES (1,'ks2','2023-07-08'),(2,'ks2','2023-07-08'),(3,'ks1','2023-07-09'),(4,'ks2','2023-07-09'),(5,'ks3','2023-07-10');
/*!40000 ALTER TABLE `beli` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbeli`
--

DROP TABLE IF EXISTS `dbeli`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dbeli` (
  `no_beli` int(10) NOT NULL,
  `kd_brg` char(6) NOT NULL,
  `harga_beli` float NOT NULL,
  `jml_beli` int(4) NOT NULL,
  PRIMARY KEY (`no_beli`,`kd_brg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbeli`
--

LOCK TABLES `dbeli` WRITE;
/*!40000 ALTER TABLE `dbeli` DISABLE KEYS */;
INSERT INTO `dbeli` VALUES (1,'0001',2000,20),(2,'0002',30000,5),(3,'0004',10000,5),(4,'0003',120000,5),(5,'0005',50000,4);
/*!40000 ALTER TABLE `dbeli` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `djual`
--

DROP TABLE IF EXISTS `djual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `djual` (
  `no_jual` int(10) NOT NULL,
  `kd_brg` char(6) NOT NULL,
  `harga_jual` float DEFAULT NULL,
  `jml_jual` int(4) DEFAULT NULL,
  PRIMARY KEY (`no_jual`,`kd_brg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `djual`
--

LOCK TABLES `djual` WRITE;
/*!40000 ALTER TABLE `djual` DISABLE KEYS */;
INSERT INTO `djual` VALUES (1,'0001',3000,7),(2,'0002',50000,2),(3,'0001',3000,3),(4,'0003',150000,6),(5,'0005',80000,5);
/*!40000 ALTER TABLE `djual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jual`
--

DROP TABLE IF EXISTS `jual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jual` (
  `no_jual` int(10) NOT NULL,
  `kd_kons` char(6) DEFAULT NULL,
  `tgl_jual` date DEFAULT NULL,
  PRIMARY KEY (`no_jual`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jual`
--

LOCK TABLES `jual` WRITE;
/*!40000 ALTER TABLE `jual` DISABLE KEYS */;
INSERT INTO `jual` VALUES (1,'k1','2023-07-08'),(2,'k2','2023-07-08'),(3,'k3','2023-07-09'),(4,'k3','2023-07-09'),(5,'k4','2023-07-10');
/*!40000 ALTER TABLE `jual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `konsumen`
--

DROP TABLE IF EXISTS `konsumen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `konsumen` (
  `kd_kons` varchar(6) NOT NULL,
  `nm_kons` varchar(30) DEFAULT NULL,
  `alm_kons` varchar(50) DEFAULT NULL,
  `kota_kons` varchar(20) DEFAULT NULL,
  `kd_pos` varchar(5) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`kd_kons`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `konsumen`
--

LOCK TABLES `konsumen` WRITE;
/*!40000 ALTER TABLE `konsumen` DISABLE KEYS */;
INSERT INTO `konsumen` VALUES ('k1','Budi','Jl.Pangeran Sukarma','Semarang','1002','082158783230','budi@gmail.com'),('k2','Rasyid','Semarang Indah','Semarang','2343','082189393741','rasyid@gmail.com'),('k3','Hemosa','Genuk','Solo','2453','085391409742','hermosa@gmail.com'),('k4','andra','pocowolo','Semarang','67990','082279319482','andraku76@gmail.com');
/*!40000 ALTER TABLE `konsumen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `kd_sup` varchar(6) NOT NULL,
  `nm_sup` varchar(30) NOT NULL,
  `alm_sup` varchar(50) NOT NULL,
  `kota_sup` varchar(20) NOT NULL,
  `kd_pos` varchar(6) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL,
  PRIMARY KEY (`kd_sup`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES ('ks1','Handoko','Jl.Chakra Adwijaya','Jakarta','2003','082158763432','handoko@gmail.com'),('ks2','Faith Industries','Jl.Soekarno Hatta','Bandung','1234','082279319482','faith@gmail.com'),('ks3','HM corporation','Jl.Bj Hbibi','Jakarta','76809','086162378','hm@gmail.com');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `password` varchar(254) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'A1','Dewandra','e34be847c582a333f9f0fb777c74191e'),(2,'Z1','Zauqy','4e8140e128660831be55fcbd2b5d3a7e'),(3,'G1','Gullam','e911247f33a82158b76ba871702c6870');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-10  2:26:24
