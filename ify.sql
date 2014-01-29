-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Czas wygenerowania: 29 Sty 2014, 18:58
-- Wersja serwera: 5.5.32
-- Wersja PHP: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Baza danych: `ify`
--
CREATE DATABASE IF NOT EXISTS `ify` DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci;
USE `ify`;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `grouppermissions`
--

CREATE TABLE IF NOT EXISTS `grouppermissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `a` bit(1) NOT NULL,
  `d` bit(1) NOT NULL,
  `i` bit(1) NOT NULL,
  `r` bit(1) NOT NULL,
  `x` bit(1) NOT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`,`name`,`username`),
  UNIQUE KEY `group_id` (`group_id`,`name`,`username`),
  UNIQUE KEY `name` (`name`,`username`),
  UNIQUE KEY `group_id_2` (`group_id`,`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=129 ;

--
-- Zrzut danych tabeli `grouppermissions`
--

INSERT INTO `grouppermissions` (`id`, `sys_created_date`, `a`, `d`, `i`, `r`, `x`, `group_id`, `name`, `user_id`, `username`) VALUES
(3, '2014-01-29 08:25:56', b'1', b'1', b'1', b'1', b'1', 3, 'TestCreateGroup11390983956164', 11, 'userTestGroupServiceTest1390983956051'),
(4, '2014-01-29 08:25:56', b'0', b'0', b'0', b'1', b'1', 3, 'TestCreateGroup11390983956164', 12, 'userTest2GroupServiceTest1390983956347'),
(7, '2014-01-29 08:25:56', b'1', b'1', b'1', b'1', b'1', 6, 'TestCreateGroup11390983956865', 14, 'userTestGroupServiceTest1390983956770'),
(14, '2014-01-29 08:33:23', b'1', b'1', b'1', b'1', b'1', 12, 'TestCreateGroup11390984403174', 23, 'userTestGroupServiceTest1390984402983'),
(15, '2014-01-29 08:33:24', b'0', b'0', b'0', b'1', b'1', 12, 'TestCreateGroup11390984403174', 24, 'userTest2GroupServiceTest1390984403551'),
(18, '2014-01-29 08:33:24', b'1', b'1', b'1', b'1', b'1', 15, 'TestCreateGroup11390984404889', 26, 'userTestGroupServiceTest1390984404652'),
(25, '2014-01-29 08:44:48', b'1', b'1', b'1', b'1', b'1', 21, 'TestCreateGroup11390985088787', 35, 'userTestGroupServiceTest1390985088674'),
(26, '2014-01-29 08:44:49', b'0', b'0', b'0', b'1', b'1', 21, 'TestCreateGroup11390985088787', 36, 'userTest2GroupServiceTest1390985089134'),
(29, '2014-01-29 08:44:50', b'1', b'1', b'1', b'1', b'1', 24, 'TestCreateGroup11390985090101', 38, 'userTestGroupServiceTest1390985089943'),
(36, '2014-01-29 08:59:43', b'1', b'1', b'1', b'1', b'1', 30, 'TestCreateGroup11390985983810', 47, 'userTestGroupServiceTest1390985983686'),
(37, '2014-01-29 08:59:44', b'0', b'0', b'0', b'1', b'1', 30, 'TestCreateGroup11390985983810', 48, 'userTest2GroupServiceTest1390985984018'),
(40, '2014-01-29 08:59:44', b'1', b'1', b'1', b'1', b'1', 33, 'TestCreateGroup11390985984737', 50, 'userTestGroupServiceTest1390985984628'),
(45, '2014-01-29 09:02:53', b'0', b'0', b'0', b'1', b'1', 37, 'grupaTest1', 1, 'test1'),
(46, '2014-01-29 09:02:54', b'0', b'0', b'0', b'1', b'1', 37, 'grupaTest1', 2, 'test2'),
(49, '2014-01-29 09:27:12', b'1', b'1', b'1', b'1', b'1', 40, 'TestCreateGroup11390987632432', 59, 'userTestGroupServiceTest1390987632312'),
(50, '2014-01-29 09:27:12', b'0', b'0', b'0', b'1', b'1', 40, 'TestCreateGroup11390987632432', 60, 'userTest2GroupServiceTest1390987632613'),
(53, '2014-01-29 09:27:13', b'1', b'1', b'1', b'1', b'1', 43, 'TestCreateGroup11390987633254', 62, 'userTestGroupServiceTest1390987633173'),
(60, '2014-01-29 09:32:22', b'1', b'1', b'1', b'1', b'1', 49, 'TestCreateGroup11390987942712', 71, 'userTestGroupServiceTest1390987942563'),
(61, '2014-01-29 09:32:23', b'0', b'0', b'0', b'1', b'1', 49, 'TestCreateGroup11390987942712', 72, 'userTest2GroupServiceTest1390987942988'),
(64, '2014-01-29 09:32:23', b'1', b'1', b'1', b'1', b'1', 52, 'TestCreateGroup11390987943851', 74, 'userTestGroupServiceTest1390987943615'),
(71, '2014-01-29 09:46:32', b'1', b'1', b'1', b'1', b'1', 58, 'TestCreateGroup11390988792942', 83, 'userTestGroupServiceTest1390988792775'),
(72, '2014-01-29 09:46:33', b'0', b'0', b'0', b'1', b'1', 58, 'TestCreateGroup11390988792942', 84, 'userTest2GroupServiceTest1390988793116'),
(75, '2014-01-29 09:46:33', b'1', b'1', b'1', b'1', b'1', 61, 'TestCreateGroup11390988793643', 86, 'userTestGroupServiceTest1390988793547'),
(80, '2014-01-29 09:48:47', b'0', b'0', b'0', b'1', b'1', 65, 'grupaTest2', 1, 'test1'),
(81, '2014-01-29 09:48:47', b'0', b'0', b'0', b'1', b'1', 65, 'grupaTest2', 2, 'test2'),
(82, '2014-01-29 09:48:48', b'0', b'0', b'1', b'0', b'0', 65, 'grupaTest2', 3, 'test3'),
(85, '2014-01-29 09:53:58', b'1', b'1', b'1', b'1', b'1', 68, 'TestCreateGroup11390989238357', 95, 'userTestGroupServiceTest1390989238260'),
(86, '2014-01-29 09:53:58', b'0', b'0', b'0', b'1', b'1', 68, 'TestCreateGroup11390989238357', 96, 'userTest2GroupServiceTest1390989238513'),
(89, '2014-01-29 09:53:59', b'1', b'1', b'1', b'1', b'1', 71, 'TestCreateGroup11390989239052', 98, 'userTestGroupServiceTest1390989238945'),
(96, '2014-01-29 12:11:40', b'1', b'1', b'1', b'1', b'1', 77, 'TestCreateGroup11390997500193', 107, 'userTestGroupServiceTest1390997500086'),
(97, '2014-01-29 12:11:40', b'0', b'0', b'0', b'1', b'1', 77, 'TestCreateGroup11390997500193', 108, 'userTest2GroupServiceTest1390997500358'),
(100, '2014-01-29 12:11:41', b'1', b'1', b'1', b'1', b'1', 80, 'TestCreateGroup11390997501088', 110, 'userTestGroupServiceTest1390997500904'),
(105, '2014-01-29 12:18:15', b'0', b'0', b'0', b'1', b'1', 84, 'jsonGroup1', 1, 'test1'),
(106, '2014-01-29 12:18:16', b'0', b'0', b'0', b'1', b'1', 84, 'jsonGroup1', 3, 'test3'),
(109, '2014-01-29 16:03:24', b'1', b'1', b'1', b'1', b'1', 87, 'TestCreateGroup11391011404218', 119, 'userTestGroupServiceTest1391011404118'),
(110, '2014-01-29 16:03:24', b'0', b'0', b'0', b'1', b'1', 87, 'TestCreateGroup11391011404218', 120, 'userTest2GroupServiceTest1391011404363'),
(113, '2014-01-29 16:03:24', b'1', b'1', b'1', b'1', b'1', 90, 'TestCreateGroup11391011404929', 122, 'userTestGroupServiceTest1391011404809'),
(120, '2014-01-29 16:21:23', b'1', b'1', b'1', b'1', b'1', 96, 'TestCreateGroup11391012483325', 131, 'userTestGroupServiceTest1391012483151'),
(121, '2014-01-29 16:21:23', b'0', b'0', b'0', b'1', b'1', 96, 'TestCreateGroup11391012483325', 132, 'userTest2GroupServiceTest1391012483566'),
(124, '2014-01-29 16:21:24', b'1', b'1', b'1', b'1', b'1', 99, 'TestCreateGroup11391012484402', 134, 'userTestGroupServiceTest1391012484284');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `groups`
--

CREATE TABLE IF NOT EXISTS `groups` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `name_2` (`name`,`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=103 ;

--
-- Zrzut danych tabeli `groups`
--

INSERT INTO `groups` (`id`, `sys_created_date`, `name`, `user_id`, `username`) VALUES
(3, '2014-01-29 08:25:56', 'TestCreateGroup11390983956164', 11, 'userTestGroupServiceTest1390983956051'),
(6, '2014-01-29 08:25:56', 'TestCreateGroup11390983956865', 14, 'userTestGroupServiceTest1390983956770'),
(12, '2014-01-29 08:33:23', 'TestCreateGroup11390984403174', 23, 'userTestGroupServiceTest1390984402983'),
(15, '2014-01-29 08:33:24', 'TestCreateGroup11390984404889', 26, 'userTestGroupServiceTest1390984404652'),
(21, '2014-01-29 08:44:48', 'TestCreateGroup11390985088787', 35, 'userTestGroupServiceTest1390985088674'),
(24, '2014-01-29 08:44:50', 'TestCreateGroup11390985090101', 38, 'userTestGroupServiceTest1390985089943'),
(30, '2014-01-29 08:59:43', 'TestCreateGroup11390985983810', 47, 'userTestGroupServiceTest1390985983686'),
(33, '2014-01-29 08:59:44', 'TestCreateGroup11390985984737', 50, 'userTestGroupServiceTest1390985984628'),
(37, '2014-01-29 09:02:53', 'grupaTest1', 1, 'test1'),
(40, '2014-01-29 09:27:12', 'TestCreateGroup11390987632432', 59, 'userTestGroupServiceTest1390987632312'),
(43, '2014-01-29 09:27:13', 'TestCreateGroup11390987633254', 62, 'userTestGroupServiceTest1390987633173'),
(49, '2014-01-29 09:32:22', 'TestCreateGroup11390987942712', 71, 'userTestGroupServiceTest1390987942563'),
(52, '2014-01-29 09:32:23', 'TestCreateGroup11390987943851', 74, 'userTestGroupServiceTest1390987943615'),
(58, '2014-01-29 09:46:32', 'TestCreateGroup11390988792942', 83, 'userTestGroupServiceTest1390988792775'),
(61, '2014-01-29 09:46:33', 'TestCreateGroup11390988793643', 86, 'userTestGroupServiceTest1390988793547'),
(65, '2014-01-29 09:48:47', 'grupaTest2', 1, 'test1'),
(68, '2014-01-29 09:53:58', 'TestCreateGroup11390989238357', 95, 'userTestGroupServiceTest1390989238260'),
(71, '2014-01-29 09:53:59', 'TestCreateGroup11390989239052', 98, 'userTestGroupServiceTest1390989238945'),
(77, '2014-01-29 12:11:40', 'TestCreateGroup11390997500193', 107, 'userTestGroupServiceTest1390997500086'),
(80, '2014-01-29 12:11:41', 'TestCreateGroup11390997501088', 110, 'userTestGroupServiceTest1390997500904'),
(84, '2014-01-29 12:18:15', 'jsonGroup1', 1, 'test1'),
(87, '2014-01-29 16:03:24', 'TestCreateGroup11391011404218', 119, 'userTestGroupServiceTest1391011404118'),
(90, '2014-01-29 16:03:24', 'TestCreateGroup11391011404929', 122, 'userTestGroupServiceTest1391011404809'),
(96, '2014-01-29 16:21:23', 'TestCreateGroup11391012483325', 131, 'userTestGroupServiceTest1391012483151'),
(99, '2014-01-29 16:21:24', 'TestCreateGroup11391012484402', 134, 'userTestGroupServiceTest1391012484284');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `parameters`
--

CREATE TABLE IF NOT EXISTS `parameters` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `booleanValue` bit(1) DEFAULT NULL,
  `device` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `doubleValue` double DEFAULT NULL,
  `integerValue` int(11) DEFAULT NULL,
  `lobValue` longblob,
  `name` varchar(25) COLLATE utf8_polish_ci NOT NULL,
  `recipe` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `stringValue` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `type` varchar(25) COLLATE utf8_polish_ci NOT NULL,
  `groupname` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1B57C1EADF8E90FE` (`username`,`user_id`),
  KEY `FK1B57C1EA329EA100` (`groupname`,`group_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=12 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `queue`
--

CREATE TABLE IF NOT EXISTS `queue` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `data` longblob NOT NULL,
  `recipe` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `source_user_id` bigint(20) DEFAULT NULL,
  `target_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK66F19111934FF7A` (`target_user_id`),
  KEY `FK66F191126D4CC04` (`source_user_id`),
  KEY `FK66F19118AF78776` (`group_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=46 ;

--
-- Zrzut danych tabeli `queue`
--

INSERT INTO `queue` (`id`, `sys_created_date`, `data`, `recipe`, `group_id`, `source_user_id`, `target_user_id`) VALUES
(41, '2014-01-29 16:07:22', 0xaced00057372002e706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e726573742e6d6f64656c2e4d657373616765cb93e5a64b88437a0200034c00056576656e747400354c706c2f706f7a6e616e2f7075742f63732f6966792f7765626966792f726573742f6d6f64656c2f4d6573736167654576656e743b4c0004757365727400344c706c2f706f7a6e616e2f7075742f63732f6966792f7765626966792f726573742f6d6f64656c2f4d657373616765557365723b4c000676616c75657374000f4c6a6176612f7574696c2f4d61703b787073720033706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e726573742e6d6f64656c2e4d6573736167654576656e74983c63178183a3100200024900037461674c00067461726765747400124c6a6176612f6c616e672f537472696e673b787000000001740005746573743273720032706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e726573742e6d6f64656c2e4d65737361676555736572a79ef6907bd2ddea0200054c000664657669636571007e00064c000567726f757071007e00064c000870617373776f726471007e00064c000672656369706571007e00064c0008757365726e616d6571007e0006787074000f33353437313730343139353933373174000a6a736f6e47726f7570317400007400035952437400057465737431737200176a6176612e7574696c2e4c696e6b6564486173684d617034c04e5c106cc0fb0200015a000b6163636573734f72646572787200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f4000000000000c770800000010000000017400047465787473720033706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e726573742e6d6f64656c2e4d657373616765506172616d07c1a715e43d14940200034c00047479706571007e00064c0008757365726e616d6571007e00064c000576616c756571007e00067870740006537472696e677074000a7369656d6161616161617800, 'YRC', 84, 1, 2);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `enabled` bit(1) NOT NULL,
  `firstName` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `lastName` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `rolesLob` longblob NOT NULL,
  `username` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `username_2` (`username`,`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=139 ;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`id`, `sys_created_date`, `enabled`, `firstName`, `lastName`, `password`, `rolesLob`, `username`) VALUES
(1, '2014-01-29 08:25:52', b'1', 'Systemuser', 'Systemuser', 'b5e9c9ab4f777c191bc847e1aca222d6836714b7', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'test1'),
(2, '2014-01-29 08:25:52', b'1', 'Systemuser', 'Systemuser', '272a11a0206b949355be4b0bda9a8918609f1ac6', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'test2'),
(3, '2014-01-29 08:25:52', b'1', 'Systemuser', 'Systemuser', 'bc003187c32e37942110b9a59e19c2d0431deedc', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'test3'),
(4, '2014-01-29 08:25:52', b'1', 'Systemuser', 'Systemuser', 'aab7045ff18ef337060dcbe7887f6cf2e6b74582', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'test4'),
(5, '2014-01-29 08:25:52', b'1', 'Systemuser', 'Systemuser', '383170b3f039ad47372f57ec4b8f49f2d7a19022', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'test5'),
(6, '2014-01-29 08:25:53', b'1', 'Systemuser', 'Systemuser', 'b61cdb05abe9cf491a9ad0bc0adf919bb4671ecc', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'BROADCAST'),
(11, '2014-01-29 08:25:56', b'0', 'test1', 'test1', '83da051f9eea5178b4a1bea59088eecfd52c8a07', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTestGroupServiceTest1390983956051'),
(12, '2014-01-29 08:25:56', b'0', 'test2', 'test2', '36ee65be7a0412646f500af0070688f8f9f5ad27', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTest2GroupServiceTest1390983956347'),
(14, '2014-01-29 08:25:56', b'0', 'test1', 'test1', '83da051f9eea5178b4a1bea59088eecfd52c8a07', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTestGroupServiceTest1390983956770'),
(23, '2014-01-29 08:33:23', b'0', 'test1', 'test1', '83da051f9eea5178b4a1bea59088eecfd52c8a07', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTestGroupServiceTest1390984402983'),
(24, '2014-01-29 08:33:23', b'0', 'test2', 'test2', '36ee65be7a0412646f500af0070688f8f9f5ad27', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTest2GroupServiceTest1390984403551'),
(26, '2014-01-29 08:33:24', b'0', 'test1', 'test1', '83da051f9eea5178b4a1bea59088eecfd52c8a07', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTestGroupServiceTest1390984404652'),
(35, '2014-01-29 08:44:48', b'0', 'test1', 'test1', '83da051f9eea5178b4a1bea59088eecfd52c8a07', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTestGroupServiceTest1390985088674'),
(36, '2014-01-29 08:44:49', b'0', 'test2', 'test2', '36ee65be7a0412646f500af0070688f8f9f5ad27', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTest2GroupServiceTest1390985089134'),
(38, '2014-01-29 08:44:49', b'0', 'test1', 'test1', '83da051f9eea5178b4a1bea59088eecfd52c8a07', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTestGroupServiceTest1390985089943'),
(47, '2014-01-29 08:59:43', b'0', 'test1', 'test1', '83da051f9eea5178b4a1bea59088eecfd52c8a07', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTestGroupServiceTest1390985983686'),
(48, '2014-01-29 08:59:44', b'0', 'test2', 'test2', '36ee65be7a0412646f500af0070688f8f9f5ad27', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTest2GroupServiceTest1390985984018'),
(50, '2014-01-29 08:59:44', b'0', 'test1', 'test1', '83da051f9eea5178b4a1bea59088eecfd52c8a07', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTestGroupServiceTest1390985984628'),
(59, '2014-01-29 09:27:12', b'0', 'test1', 'test1', '83da051f9eea5178b4a1bea59088eecfd52c8a07', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTestGroupServiceTest1390987632312'),
(60, '2014-01-29 09:27:12', b'0', 'test2', 'test2', '36ee65be7a0412646f500af0070688f8f9f5ad27', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTest2GroupServiceTest1390987632613'),
(62, '2014-01-29 09:27:13', b'0', 'test1', 'test1', '83da051f9eea5178b4a1bea59088eecfd52c8a07', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTestGroupServiceTest1390987633173'),
(71, '2014-01-29 09:32:22', b'0', 'test1', 'test1', '83da051f9eea5178b4a1bea59088eecfd52c8a07', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTestGroupServiceTest1390987942563'),
(72, '2014-01-29 09:32:23', b'0', 'test2', 'test2', '36ee65be7a0412646f500af0070688f8f9f5ad27', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTest2GroupServiceTest1390987942988'),
(74, '2014-01-29 09:32:23', b'0', 'test1', 'test1', '83da051f9eea5178b4a1bea59088eecfd52c8a07', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTestGroupServiceTest1390987943615'),
(83, '2014-01-29 09:46:32', b'0', 'test1', 'test1', '83da051f9eea5178b4a1bea59088eecfd52c8a07', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTestGroupServiceTest1390988792775'),
(84, '2014-01-29 09:46:33', b'0', 'test2', 'test2', '36ee65be7a0412646f500af0070688f8f9f5ad27', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTest2GroupServiceTest1390988793116'),
(86, '2014-01-29 09:46:33', b'0', 'test1', 'test1', '83da051f9eea5178b4a1bea59088eecfd52c8a07', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTestGroupServiceTest1390988793547'),
(95, '2014-01-29 09:53:58', b'0', 'test1', 'test1', '83da051f9eea5178b4a1bea59088eecfd52c8a07', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTestGroupServiceTest1390989238260'),
(96, '2014-01-29 09:53:58', b'0', 'test2', 'test2', '36ee65be7a0412646f500af0070688f8f9f5ad27', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTest2GroupServiceTest1390989238513'),
(98, '2014-01-29 09:53:58', b'0', 'test1', 'test1', '83da051f9eea5178b4a1bea59088eecfd52c8a07', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTestGroupServiceTest1390989238945'),
(107, '2014-01-29 12:11:40', b'0', 'test1', 'test1', '83da051f9eea5178b4a1bea59088eecfd52c8a07', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTestGroupServiceTest1390997500086'),
(108, '2014-01-29 12:11:40', b'0', 'test2', 'test2', '36ee65be7a0412646f500af0070688f8f9f5ad27', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTest2GroupServiceTest1390997500358'),
(110, '2014-01-29 12:11:40', b'0', 'test1', 'test1', '83da051f9eea5178b4a1bea59088eecfd52c8a07', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTestGroupServiceTest1390997500904'),
(119, '2014-01-29 16:03:24', b'0', 'test1', 'test1', '83da051f9eea5178b4a1bea59088eecfd52c8a07', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTestGroupServiceTest1391011404118'),
(120, '2014-01-29 16:03:24', b'0', 'test2', 'test2', '36ee65be7a0412646f500af0070688f8f9f5ad27', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTest2GroupServiceTest1391011404363'),
(122, '2014-01-29 16:03:24', b'0', 'test1', 'test1', '83da051f9eea5178b4a1bea59088eecfd52c8a07', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTestGroupServiceTest1391011404809'),
(131, '2014-01-29 16:21:23', b'0', 'test1', 'test1', '83da051f9eea5178b4a1bea59088eecfd52c8a07', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTestGroupServiceTest1391012483151'),
(132, '2014-01-29 16:21:23', b'0', 'test2', 'test2', '36ee65be7a0412646f500af0070688f8f9f5ad27', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTest2GroupServiceTest1391012483566'),
(134, '2014-01-29 16:21:24', b'0', 'test1', 'test1', '83da051f9eea5178b4a1bea59088eecfd52c8a07', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000177040000000a7e720034706c2e706f7a6e616e2e7075742e63732e6966792e7765626966792e646174612e656e756d732e757365722e55736572526f6c6500000000000000001200007872000e6a6176612e6c616e672e456e756d000000000000000012000078707400045553455278, 'userTestGroupServiceTest1391012484284');

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `parameters`
--
ALTER TABLE `parameters`
  ADD CONSTRAINT `FK1B57C1EA329EA100` FOREIGN KEY (`groupname`, `group_id`) REFERENCES `groups` (`name`, `id`),
  ADD CONSTRAINT `FK1B57C1EADF8E90FE` FOREIGN KEY (`username`, `user_id`) REFERENCES `users` (`username`, `id`);

--
-- Ograniczenia dla tabeli `queue`
--
ALTER TABLE `queue`
  ADD CONSTRAINT `FK66F19118AF78776` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`),
  ADD CONSTRAINT `FK66F19111934FF7A` FOREIGN KEY (`target_user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FK66F191126D4CC04` FOREIGN KEY (`source_user_id`) REFERENCES `users` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
