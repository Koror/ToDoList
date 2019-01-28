-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.5.23 - MySQL Community Server (GPL)
-- Операционная система:         Win64
-- HeidiSQL Версия:              9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп данных таблицы test.tm_assigneegroup: 0 rows
/*!40000 ALTER TABLE `tm_assigneegroup` DISABLE KEYS */;
INSERT INTO `tm_assigneegroup` (`id`, `group_id`, `user_id`) VALUES
	('2c9200c06894440d0168944506ba0005', '2c9200c06894440d0168944506ba0004', '2c9200c06894440d0168944457210000');
/*!40000 ALTER TABLE `tm_assigneegroup` ENABLE KEYS */;

-- Дамп данных таблицы test.tm_assigneetask: 3 rows
/*!40000 ALTER TABLE `tm_assigneetask` DISABLE KEYS */;
INSERT INTO `tm_assigneetask` (`id`, `task_id`, `user_id`) VALUES
	('2c9200c06894440d0168944494580003', '2c9200c06894440d0168944494580002', '2c9200c06894440d0168944457210000');
/*!40000 ALTER TABLE `tm_assigneetask` ENABLE KEYS */;

-- Дамп данных таблицы test.tm_group: 1 rows
/*!40000 ALTER TABLE `tm_group` DISABLE KEYS */;
INSERT INTO `tm_group` (`id`, `creator`, `name`, `user_id`) VALUES
	('2c9200c06894440d0168944506ba0004', '2c9200c06894440d0168944457210000', 'Application', '2c9200c06894440d0168944457210000');
/*!40000 ALTER TABLE `tm_group` ENABLE KEYS */;

-- Дамп данных таблицы test.tm_session: 3 rows
/*!40000 ALTER TABLE `tm_session` DISABLE KEYS */;
INSERT INTO `tm_session` (`id`, `ip`, `signature`, `user_id`) VALUES
	('2c9200c06894440d01689444573d0001', '172.18.128.64', '24D794DFC756320FFADB905D526299BC', '2c9200c06894440d0168944457210000');
/*!40000 ALTER TABLE `tm_session` ENABLE KEYS */;

-- Дамп данных таблицы test.tm_task: 6 rows
/*!40000 ALTER TABLE `tm_task` DISABLE KEYS */;
INSERT INTO `tm_task` (`id`, `complete`, `creator`, `name`, `priority`, `group_id`, `user_id`) VALUES
	('2c9200c06894440d0168944494580002', b'0', '2c9200c06894440d0168944457210000', 'Work', NULL, '2c9200c06894440d0168944506ba0004', '2c9200c06894440d0168944457210000');
/*!40000 ALTER TABLE `tm_task` ENABLE KEYS */;

-- Дамп данных таблицы test.tm_user: 1 rows
/*!40000 ALTER TABLE `tm_user` DISABLE KEYS */;
INSERT INTO `tm_user` (`id`, `access`, `email`, `login`, `name`, `password`) VALUES
	('2c9200c06894440d0168944457210000', 0, NULL, 'admin', NULL, '21232F297A57A5A743894A0E4A801FC3');
/*!40000 ALTER TABLE `tm_user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
