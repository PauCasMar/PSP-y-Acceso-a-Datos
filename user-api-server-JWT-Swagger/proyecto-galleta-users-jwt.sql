-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         12.0.2-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.11.0.7065
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para proyecto-galleta
CREATE DATABASE IF NOT EXISTS `proyecto-galleta` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_uca1400_ai_ci */;
USE `proyecto-galleta`;

-- Volcando estructura para tabla proyecto-galleta.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fecha_alta` date DEFAULT NULL,
  `fecha_ult_login` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_uca1400_ai_ci;

-- Volcando datos para la tabla proyecto-galleta.usuarios: ~7 rows (aproximadamente)
DELETE FROM `usuarios`;
INSERT INTO `usuarios` (`id`, `username`, `password`, `email`, `fecha_alta`, `fecha_ult_login`) VALUES
	(1, 'kuky', '$2a$10$vJEzU/cuizciRFpmjsoDfeoE76xFWkBm4cO.ME2Guv9jb4xOyD4hK', 'kuky@kuky.com', '2026-02-01', NULL),
	(2, 'admin', '$2a$10$vJEzU/cuizciRFpmjsoDfeoE76xFWkBm4cO.ME2Guv9jb4xOyD4hK', 'admin@demo.com', '2026-02-01', NULL),
	(3, 'juan', '$2a$10$vJEzU/cuizciRFpmjsoDfeoE76xFWkBm4cO.ME2Guv9jb4xOyD4hK', 'juan@demo.com', '2026-02-01', NULL),
	(4, 'ana', '$2a$10$vJEzU/cuizciRFpmjsoDfeoE76xFWkBm4cO.ME2Guv9jb4xOyD4hK', 'ana@demo.com', '2026-02-01', NULL),
	(5, 'luis', '$2a$10$vJEzU/cuizciRFpmjsoDfeoE76xFWkBm4cO.ME2Guv9jb4xOyD4hK', 'luis@demo.com', '2026-02-01', NULL),
	(6, 'maria', '$2a$10$vJEzU/cuizciRFpmjsoDfeoE76xFWkBm4cO.ME2Guv9jb4xOyD4hK', 'maria@demo.com', '2026-02-01', NULL),
	(7, 'alvaro', '$2a$10$v6FsvvYnvhvthP/A7tTMcO9U8x.6LH7OKfAbKPzd.pRgT5j.80Bba', 'alvaro@demo.com', '2026-05-24', NULL);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
