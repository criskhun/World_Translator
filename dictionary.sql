-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 20, 2020 at 12:24 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dictionary`
--

-- --------------------------------------------------------

--
-- Table structure for table `access`
--

CREATE TABLE `access` (
  `ID` int(11) NOT NULL,
  `Full_Name` varchar(225) NOT NULL,
  `Birthdate` varchar(225) NOT NULL,
  `Username` varchar(225) NOT NULL,
  `Password` varchar(225) NOT NULL,
  `Level` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `access`
--

INSERT INTO `access` (`ID`, `Full_Name`, `Birthdate`, `Username`, `Password`, `Level`) VALUES
(3, 'Crispin Jose Uriarte', '11/30/1995', 'admin', 'open', 'Admin'),
(9, 'Danrus', '', 'danrus', 'danrus', 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `accountlog`
--

CREATE TABLE `accountlog` (
  `Date` varchar(225) NOT NULL,
  `Time` varchar(225) NOT NULL,
  `Name` varchar(225) NOT NULL,
  `Username` varchar(225) NOT NULL,
  `Level` varchar(225) NOT NULL,
  `Remarks` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accountlog`
--

INSERT INTO `accountlog` (`Date`, `Time`, `Name`, `Username`, `Level`, `Remarks`) VALUES
('04/11/2019', '6:04 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('09/11/2019', '6:09 PM', 'Danrus', 'danrus', 'Admin', 'Log-out'),
('26/14/2019', '8:26 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('36/14/2019', '8:36 PM', 'Danrus', 'danrus', 'Admin', 'Log-out'),
('30/16/2019', '5:30 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('32/16/2019', '5:32 PM', 'Danrus', 'danrus', 'Admin', 'Log-out'),
('31/05/2020', '11:31 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('42/05/2020', '11:42 PM', 'Danrus', 'danrus', 'Admin', 'Log-out'),
('44/05/2020', '11:44 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('49/05/2020', '11:49 PM', 'Danrus', 'danrus', 'Admin', 'Log-out'),
('58/05/2020', '11:58 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('01/06/2020', '12:01 AM', 'Danrus', 'danrus', 'Admin', 'Log-out'),
('23/06/2020', '9:23 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('17/07/2020', '2:17 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('18/07/2020', '2:18 PM', 'Danrus', 'danrus', 'Admin', 'Log-out'),
('54/08/2020', '9:54 AM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('56/08/2020', '9:56 AM', 'Danrus', 'danrus', 'Admin', 'Log-out'),
('55/09/2020', '6:55 AM', 'Danrus', 'DANRUS', 'Admin', 'Log-in'),
('55/09/2020', '6:55 AM', 'Danrus', 'DANRUS', 'Admin', 'Log-out'),
('56/09/2020', '6:56 AM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('56/09/2020', '6:56 AM', 'Danrus', 'danrus', 'Admin', 'Log-out'),
('57/09/2020', '6:57 AM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('59/09/2020', '6:59 AM', 'Danrus', 'danrus', 'Admin', 'Log-out'),
('00/09/2020', '7:00 AM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('26/10/2020', '9:26 AM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('20/10/2020', '10:20 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('24/11/2020', '6:24 AM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('25/11/2020', '6:25 AM', 'Danrus', 'danrus', 'Admin', 'Log-out'),
('25/11/2020', '6:25 AM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('31/11/2020', '6:31 AM', 'Danrus', 'danrus', 'Admin', 'Log-out'),
('00/11/2020', '11:00 AM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('32/11/2020', '11:32 AM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('54/11/2020', '11:54 AM', 'Danrus', 'danrus', 'Admin', 'Log-out'),
('55/11/2020', '11:55 AM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('12/11/2020', '12:12 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('25/11/2020', '12:25 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('41/11/2020', '12:41 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('54/11/2020', '12:54 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('02/11/2020', '1:02 PM', 'Danrus', 'danrus', 'Admin', 'Log-out'),
('04/11/2020', '1:04 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('16/11/2020', '1:16 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('26/11/2020', '1:26 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('28/11/2020', '1:28 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('32/11/2020', '1:32 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('37/11/2020', '6:37 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('48/11/2020', '6:48 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('04/11/2020', '7:04 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('20/11/2020', '7:20 PM', 'Danrus', 'danrus', 'Admin', 'Log-out'),
('22/12/2020', '7:22 AM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('30/12/2020', '7:30 AM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('58/12/2020', '10:58 AM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('02/12/2020', '11:02 AM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('17/12/2020', '11:17 AM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('29/12/2020', '7:29 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('04/12/2020', '9:04 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('31/12/2020', '9:31 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('54/12/2020', '9:54 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('15/12/2020', '10:15 PM', 'Danrus', 'danrus', 'Admin', 'Log-out'),
('15/12/2020', '10:15 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('21/12/2020', '10:21 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('36/12/2020', '10:36 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('47/12/2020', '10:47 PM', 'Danrus', 'danrus', 'Admin', 'Log-out'),
('40/13/2020', '9:40 AM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('41/13/2020', '9:41 AM', 'Danrus', 'danrus', 'Admin', 'Log-out'),
('55/14/2020', '7:55 AM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('56/14/2020', '7:56 AM', 'Danrus', 'danrus', 'Admin', 'Log-out'),
('01/14/2020', '8:01 AM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('02/14/2020', '8:02 AM', 'Danrus', 'danrus', 'Admin', 'Log-out'),
('20/14/2020', '10:20 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-in'),
('24/14/2020', '10:24 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-out'),
('24/14/2020', '10:24 PM', 'Danrus', 'danrus', 'Admin', 'Log-in'),
('29/14/2020', '10:29 PM', 'Danrus', 'danrus', 'Admin', 'Log-out'),
('23/19/2020', '4:23 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-in'),
('23/19/2020', '4:23 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-out'),
('25/19/2020', '4:25 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-in'),
('25/19/2020', '4:25 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-out'),
('26/19/2020', '4:26 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-in'),
('26/19/2020', '4:26 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-out'),
('46/19/2020', '4:46 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-in'),
('51/19/2020', '4:51 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-out'),
('53/19/2020', '4:53 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-in'),
('54/19/2020', '4:54 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-out'),
('58/19/2020', '4:58 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-in'),
('00/19/2020', '5:00 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-out'),
('00/19/2020', '5:00 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-in'),
('00/19/2020', '5:00 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-out'),
('01/19/2020', '05:01 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-in'),
('01/19/2020', '05:02 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-out'),
('01/19/2020', '05:15 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-in'),
('01/19/2020', '05:16 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-out'),
('01/19/2020', '05:21 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-in'),
('01/19/2020', '05:21 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-out'),
('01/19/2020', '05:22 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-in'),
('01/19/2020', '05:22 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-out'),
('01/19/2020', '05:26 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-in'),
('01/19/2020', '05:28 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-out'),
('01/19/2020', '06:07 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-in'),
('01/19/2020', '07:38 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-in'),
('01/19/2020', '07:38 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-out'),
('01/19/2020', '07:39 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-in'),
('01/19/2020', '07:39 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-out'),
('01/19/2020', '07:44 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-in'),
('01/19/2020', '10:02 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-out'),
('01/20/2020', '07:07 PM', 'Crispin Jose Uriarte', 'admin', 'Admin', 'Log-in');

-- --------------------------------------------------------

--
-- Table structure for table `frequent`
--

CREATE TABLE `frequent` (
  `ID` int(11) NOT NULL,
  `Word` varchar(225) NOT NULL,
  `Count` varchar(225) NOT NULL,
  `Date` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `frequent`
--

INSERT INTO `frequent` (`ID`, `Word`, `Count`, `Date`) VALUES
(36, 'a', '6', '01/19/2020'),
(37, 'z', '1', '01/20/2020');

-- --------------------------------------------------------

--
-- Table structure for table `inactive`
--

CREATE TABLE `inactive` (
  `ID` int(11) NOT NULL,
  `Full_Name` varchar(225) NOT NULL,
  `Birthdate` varchar(225) NOT NULL,
  `Username` varchar(225) NOT NULL,
  `Password` varchar(225) NOT NULL,
  `Level` varchar(225) NOT NULL,
  `Reason` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `inactive`
--

INSERT INTO `inactive` (`ID`, `Full_Name`, `Birthdate`, `Username`, `Password`, `Level`, `Reason`) VALUES
(8, 'jaymar', '12/10/2019', 'jay', 'open', 'User', 'dasdas');

-- --------------------------------------------------------

--
-- Table structure for table `userlog`
--

CREATE TABLE `userlog` (
  `Date` varchar(225) NOT NULL,
  `Time` varchar(225) NOT NULL,
  `Name` varchar(225) NOT NULL,
  `Remarks` varchar(225) NOT NULL,
  `Entry` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userlog`
--

INSERT INTO `userlog` (`Date`, `Time`, `Name`, `Remarks`, `Entry`) VALUES
('07/11/2019', '6:07 PM', 'Danrus', 'Inactive Account', 'admin'),
('08/11/2019', '6:08 PM', 'Danrus', 'Re-active Account', 'admin'),
('07/06/2020', '10:07 PM', 'Danrus', 'Add Entry', 'abaca'),
('17/06/2020', '10:17 PM', 'Danrus', 'Add Entry', 'abacus'),
('46/06/2020', '10:46 PM', 'Danrus', 'Update Entry', 'abacus'),
('51/06/2020', '10:51 PM', 'Danrus', 'Add Entry', 'abandon'),
('00/06/2020', '11:00 PM', 'Danrus', 'Add Entry', 'abattoir'),
('03/06/2020', '11:03 PM', 'Danrus', 'Add Entry', 'abbey'),
('12/06/2020', '11:12 PM', 'Danrus', 'Add Entry', 'baboon'),
('08/09/2020', '7:08 AM', 'Danrus', 'Add Entry', 'baby'),
('16/09/2020', '7:16 AM', 'Danrus', 'Add Entry', 'babysitter'),
('22/09/2020', '7:22 AM', 'Danrus', 'Add Entry', 'bachelor'),
('31/09/2020', '7:31 AM', 'Danrus', 'Add Entry', 'backbone'),
('48/09/2020', '7:48 AM', 'Danrus', 'Add Entry', 'cab'),
('52/09/2020', '7:52 AM', 'Danrus', 'Add Entry', 'cabbage'),
('59/09/2020', '7:59 AM', 'Danrus', 'Add Entry', 'cabin'),
('06/09/2020', '8:06 AM', 'Danrus', 'Add Entry', 'cabinet'),
('12/09/2020', '8:12 AM', 'Danrus', 'Add Entry', 'cable'),
('12/10/2020', '10:12 AM', 'Danrus', 'Add Entry', 'daddy'),
('15/10/2020', '10:15 AM', 'Danrus', 'Add Entry', 'dagger'),
('16/10/2020', '11:16 AM', 'Danrus', 'Add Entry', 'daily'),
('20/10/2020', '11:20 AM', 'Danrus', 'Add Entry', 'dainty'),
('23/10/2020', '11:23 AM', 'Danrus', 'Add Entry', 'dairy'),
('34/10/2020', '11:34 AM', 'Danrus', 'Add Entry', 'each'),
('43/10/2020', '11:43 AM', 'Danrus', 'Add Entry', 'eagle'),
('27/10/2020', '10:27 PM', 'Danrus', 'Add Entry', 'ear'),
('31/10/2020', '10:31 PM', 'Danrus', 'Add Entry', 'eardrum'),
('35/10/2020', '10:35 PM', 'Danrus', 'Add Entry', 'earn'),
('40/10/2020', '10:40 PM', 'Danrus', 'Add Entry', 'fable'),
('47/10/2020', '10:47 PM', 'Danrus', 'Add Entry', 'fabric'),
('50/10/2020', '10:50 PM', 'Danrus', 'Add Entry', 'face'),
('55/10/2020', '10:55 PM', 'Danrus', 'Add Entry', 'facility'),
('13/10/2020', '11:13 PM', 'Danrus', 'Add Entry', 'facsimile machine'),
('17/10/2020', '11:17 PM', 'Danrus', 'Add Entry', 'gadget'),
('27/10/2020', '11:27 PM', 'Danrus', 'Add Entry', 'gaiety'),
('30/10/2020', '11:30 PM', 'Danrus', 'Add Entry', 'gaily'),
('34/10/2020', '11:34 PM', 'Danrus', 'Add Entry', 'gaff'),
('40/10/2020', '11:40 PM', 'Danrus', 'Add Entry', 'galaxy'),
('04/11/2020', '12:04 AM', 'Danrus', 'Add Entry', 'habitat'),
('16/11/2020', '12:16 AM', 'Danrus', 'Add Entry', 'hacker'),
('24/11/2020', '12:24 AM', 'Danrus', 'Add Entry', 'haggard'),
('28/11/2020', '12:28 AM', 'Danrus', 'Add Entry', 'HAIL'),
('37/11/2020', '12:37 AM', 'Danrus', 'Add Entry', 'hair'),
('38/11/2020', '12:38 AM', 'Danrus', 'Add Entry', 'hair'),
('30/11/2020', '6:30 AM', 'Danrus', 'Add Entry', 'hair breadth'),
('02/11/2020', '11:02 AM', 'Danrus', 'Add Entry', 'ice'),
('07/11/2020', '11:07 AM', 'Danrus', 'Add Entry', 'ice age'),
('10/11/2020', '11:10 AM', 'Danrus', 'Add Entry', 'iceberg'),
('16/11/2020', '11:16 AM', 'Danrus', 'Add Entry', 'ice cream'),
('21/11/2020', '11:21 AM', 'Danrus', 'Add Entry', 'ice hockey'),
('23/11/2020', '11:23 AM', 'Danrus', 'Add Entry', 'jab'),
('25/11/2020', '11:25 AM', 'Danrus', 'Add Entry', 'jabber'),
('30/11/2020', '11:30 AM', 'Danrus', 'Add Entry', 'jack'),
('33/11/2020', '11:33 AM', 'Danrus', 'Add Entry', 'jacket'),
('39/11/2020', '11:39 AM', 'Danrus', 'Add Entry', 'jacuzzi'),
('40/11/2020', '11:40 AM', 'Danrus', 'Add Entry', 'kakapo'),
('46/11/2020', '11:46 AM', 'Danrus', 'Add Entry', 'kaleidoscope'),
('48/11/2020', '11:48 AM', 'Danrus', 'Add Entry', 'kangaroo'),
('50/11/2020', '11:50 AM', 'Danrus', 'Add Entry', 'karaoke'),
('51/11/2020', '11:51 AM', 'Danrus', 'Add Entry', 'karate'),
('52/11/2020', '11:52 AM', 'Danrus', 'Add Entry', 'label'),
('57/11/2020', '11:57 AM', 'Danrus', 'Add Entry', 'labor'),
('00/11/2020', '12:00 PM', 'Danrus', 'Add Entry', 'laboratory'),
('02/11/2020', '12:02 PM', 'Danrus', 'Add Entry', 'laborer'),
('04/11/2020', '12:04 PM', 'Danrus', 'Add Entry', 'lace'),
('07/11/2020', '12:07 PM', 'Danrus', 'Add Entry', 'macabre'),
('09/11/2020', '12:09 PM', 'Danrus', 'Add Entry', 'machination'),
('10/11/2020', '12:10 PM', 'Danrus', 'Add Entry', 'machine gun'),
('14/11/2020', '12:14 PM', 'Danrus', 'Add Entry', 'machine'),
('17/11/2020', '12:17 PM', 'Danrus', 'Add Entry', 'machinery'),
('19/11/2020', '12:19 PM', 'Danrus', 'Add Entry', 'nail'),
('22/11/2020', '12:22 PM', 'Danrus', 'Add Entry', 'naive'),
('23/11/2020', '12:23 PM', 'Danrus', 'Add Entry', 'naked'),
('26/11/2020', '12:26 PM', 'Danrus', 'Add Entry', 'name'),
('28/11/2020', '12:28 PM', 'Danrus', 'Add Entry', 'named'),
('30/11/2020', '12:30 PM', 'Danrus', 'Add Entry', 'oar'),
('32/11/2020', '12:32 PM', 'Danrus', 'Add Entry', 'oasis'),
('35/11/2020', '12:35 PM', 'Danrus', 'Add Entry', 'oath'),
('37/11/2020', '12:37 PM', 'Danrus', 'Add Entry', 'obedience'),
('39/11/2020', '12:39 PM', 'Danrus', 'Add Entry', 'obedience'),
('43/11/2020', '12:43 PM', 'Danrus', 'Add Entry', 'obedient'),
('45/11/2020', '12:45 PM', 'Danrus', 'Add Entry', 'pacece'),
('49/11/2020', '12:49 PM', 'Danrus', 'Add Entry', 'pacific'),
('50/11/2020', '12:50 PM', 'Danrus', 'Add Entry', 'pacifist'),
('53/11/2020', '12:53 PM', 'Danrus', 'Add Entry', 'pacify'),
('55/11/2020', '12:55 PM', 'Danrus', 'Add Entry', 'pack'),
('57/11/2020', '12:57 PM', 'Danrus', 'Add Entry', 'quack'),
('58/11/2020', '12:58 PM', 'Danrus', 'Add Entry', 'quadrangle'),
('00/11/2020', '1:00 PM', 'Danrus', 'Add Entry', 'quadrant'),
('01/11/2020', '1:01 PM', 'Danrus', 'Add Entry', 'quadruple'),
('05/11/2020', '1:05 PM', 'Danrus', 'Add Entry', 'quadruplet'),
('09/11/2020', '1:09 PM', 'Danrus', 'Add Entry', 'rabbit'),
('13/11/2020', '1:13 PM', 'Danrus', 'Add Entry', 'rabble'),
('15/11/2020', '1:15 PM', 'Danrus', 'Add Entry', 'rabid'),
('15/11/2020', '1:15 PM', 'Danrus', 'Add Entry', 'rabid'),
('18/11/2020', '1:18 PM', 'Danrus', 'Add Entry', 'raccoon'),
('20/11/2020', '1:20 PM', 'Danrus', 'Add Entry', 'race'),
('22/11/2020', '1:22 PM', 'Danrus', 'Add Entry', 'sabbath'),
('25/11/2020', '1:25 PM', 'Danrus', 'Add Entry', 'saber toothed cat'),
('29/11/2020', '1:29 PM', 'Danrus', 'Add Entry', 'saber'),
('30/11/2020', '1:30 PM', 'Danrus', 'Add Entry', 'sabotage'),
('33/11/2020', '1:33 PM', 'Danrus', 'Add Entry', 'sack'),
('35/11/2020', '1:35 PM', 'Danrus', 'Add Entry', 'tab'),
('39/11/2020', '6:39 PM', 'Danrus', 'Add Entry', 'tabernacle'),
('42/11/2020', '6:42 PM', 'Danrus', 'Add Entry', 'table'),
('45/11/2020', '6:45 PM', 'Danrus', 'Add Entry', 'table cloth'),
('56/11/2020', '6:56 PM', 'Danrus', 'Add Entry', 'table spoon'),
('01/11/2020', '7:01 PM', 'Danrus', 'Add Entry', 'ubiquitos'),
('03/11/2020', '7:03 PM', 'Danrus', 'Add Entry', 'ugly'),
('03/11/2020', '7:03 PM', 'Danrus', 'Add Entry', 'ugly'),
('06/11/2020', '7:06 PM', 'Danrus', 'Add Entry', 'ulcer'),
('12/11/2020', '7:12 PM', 'Danrus', 'Add Entry', 'ulterior'),
('15/11/2020', '7:15 PM', 'Danrus', 'Add Entry', 'ultimate'),
('19/11/2020', '7:19 PM', 'Danrus', 'Add Entry', 'vacancy'),
('25/12/2020', '7:25 AM', 'Danrus', 'Add Entry', 'vacant'),
('27/12/2020', '7:27 AM', 'Danrus', 'Add Entry', 'vacate'),
('29/12/2020', '7:29 AM', 'Danrus', 'Add Entry', 'vacation'),
('32/12/2020', '7:32 AM', 'Danrus', 'Add Entry', 'vaccinate'),
('35/12/2020', '7:35 AM', 'Danrus', 'Add Entry', 'wack'),
('39/12/2020', '7:39 AM', 'Danrus', 'Add Entry', 'wad'),
('00/12/2020', '11:00 AM', 'Danrus', 'Add Entry', 'waddle'),
('03/12/2020', '11:03 AM', 'Danrus', 'Add Entry', 'wade'),
('07/12/2020', '11:07 AM', 'Danrus', 'Add Entry', 'wafer'),
('12/12/2020', '11:12 AM', 'Danrus', 'Add Entry', 'xenix'),
('19/12/2020', '11:19 AM', 'Danrus', 'Add Entry', 'xray'),
('20/12/2020', '11:20 AM', 'Danrus', 'Add Entry', 'xylem'),
('33/12/2020', '7:33 PM', 'Danrus', 'Add Entry', 'xylography'),
('37/12/2020', '7:37 PM', 'Danrus', 'Add Entry', 'xylophone'),
('40/12/2020', '7:40 PM', 'Danrus', 'Add Entry', 'yacht'),
('41/12/2020', '7:41 PM', 'Danrus', 'Add Entry', 'yacht'),
('34/12/2020', '9:34 PM', 'Danrus', 'Add Entry', 'yak'),
('36/12/2020', '9:36 PM', 'Danrus', 'Add Entry', 'yam'),
('41/12/2020', '9:41 PM', 'Danrus', 'Add Entry', 'yank'),
('41/12/2020', '9:41 PM', 'Danrus', 'Add Entry', 'yank'),
('57/12/2020', '9:57 PM', 'Danrus', 'Add Entry', 'yun gun'),
('10/12/2020', '10:10 PM', 'Danrus', 'Add Entry', 'zaniness'),
('15/12/2020', '10:15 PM', 'Danrus', 'Add Entry', 'zaniness'),
('22/12/2020', '10:22 PM', 'Danrus', 'Add Entry', 'zany'),
('32/12/2020', '10:32 PM', 'Danrus', 'Add Entry', 'zap'),
('34/12/2020', '10:34 PM', 'Danrus', 'Add Entry', 'zeal'),
('36/12/2020', '10:36 PM', 'Danrus', 'Add Entry', 'zeal'),
('46/12/2020', '10:46 PM', 'Danrus', 'Add Entry', 'zealous'),
('46/19/2020', '4:46 PM', 'Crispin Jose Uriarte', 'Add Entry', 'a'),
('47/19/2020', '4:47 PM', 'Crispin Jose Uriarte', 'Add Entry', 'b'),
('47/19/2020', '4:47 PM', 'Crispin Jose Uriarte', 'Add Entry', 'c'),
('47/19/2020', '4:47 PM', 'Crispin Jose Uriarte', 'Add Entry', 'd'),
('47/19/2020', '4:47 PM', 'Crispin Jose Uriarte', 'Add Entry', 'e'),
('47/19/2020', '4:47 PM', 'Crispin Jose Uriarte', 'Add Entry', 'f'),
('47/19/2020', '4:47 PM', 'Crispin Jose Uriarte', 'Add Entry', 'g'),
('47/19/2020', '4:47 PM', 'Crispin Jose Uriarte', 'Add Entry', 'h'),
('48/19/2020', '4:48 PM', 'Crispin Jose Uriarte', 'Add Entry', 'i'),
('48/19/2020', '4:48 PM', 'Crispin Jose Uriarte', 'Add Entry', 'j'),
('48/19/2020', '4:48 PM', 'Crispin Jose Uriarte', 'Add Entry', 'k'),
('48/19/2020', '4:48 PM', 'Crispin Jose Uriarte', 'Add Entry', 'l'),
('48/19/2020', '4:48 PM', 'Crispin Jose Uriarte', 'Add Entry', 'm'),
('48/19/2020', '4:48 PM', 'Crispin Jose Uriarte', 'Add Entry', 'n'),
('48/19/2020', '4:48 PM', 'Crispin Jose Uriarte', 'Add Entry', 'o'),
('48/19/2020', '4:48 PM', 'Crispin Jose Uriarte', 'Add Entry', 'p'),
('49/19/2020', '4:49 PM', 'Crispin Jose Uriarte', 'Update Entry', 'o'),
('49/19/2020', '4:49 PM', 'Crispin Jose Uriarte', 'Add Entry', 'q'),
('49/19/2020', '4:49 PM', 'Crispin Jose Uriarte', 'Add Entry', 'r'),
('50/19/2020', '4:50 PM', 'Crispin Jose Uriarte', 'Add Entry', 's'),
('50/19/2020', '4:50 PM', 'Crispin Jose Uriarte', 'Add Entry', 't'),
('50/19/2020', '4:50 PM', 'Crispin Jose Uriarte', 'Add Entry', 'w'),
('50/19/2020', '4:50 PM', 'Crispin Jose Uriarte', 'Add Entry', 'z'),
('50/19/2020', '4:50 PM', 'Crispin Jose Uriarte', 'Add Entry', 'y'),
('50/19/2020', '4:50 PM', 'Crispin Jose Uriarte', 'Add Entry', 'x');

-- --------------------------------------------------------

--
-- Table structure for table `word`
--

CREATE TABLE `word` (
  `ID` int(11) NOT NULL,
  `Word` varchar(225) NOT NULL,
  `Meaning` varchar(225) NOT NULL,
  `Filipino` varchar(225) NOT NULL,
  `Spanish` varchar(225) NOT NULL,
  `Noun` varchar(225) NOT NULL,
  `Verb` varchar(225) NOT NULL,
  `Image` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `word`
--

INSERT INTO `word` (`ID`, `Word`, `Meaning`, `Filipino`, `Spanish`, `Noun`, `Verb`, `Image`) VALUES
(140, 'a', 'a', 'a', 'a', 'a', 'a', 0x696e7365727420696d616765),
(141, 'b', 'b', 'b', 'b', 'b', 'b', 0x696e7365727420696d616765),
(142, 'c', '', 'c', 'c', 'c', 'c', 0x696e7365727420696d616765),
(143, 'd', 'd', 'd', 'd', 'd', 'd', 0x696e7365727420696d616765),
(144, 'e', 'e', 'e', 'e', 'e', 'e', 0x696e7365727420696d616765),
(145, 'f', 'f', 'f', 'f', 'f', 'f', 0x696e7365727420696d616765),
(146, 'g', 'g', 'g', 'g', 'g', 'g', 0x696e7365727420696d616765),
(147, 'h', 'h', 'h', 'h', 'h', 'h', 0x696e7365727420696d616765),
(148, 'i', 'i', 'i', 'i', 'i', 'i', 0x696e7365727420696d616765),
(149, 'j', 'j', 'j', 'j', 'j', 'j', 0x696e7365727420696d616765),
(150, 'k', 'k', 'k', 'k', 'k', 'k', 0x696e7365727420696d616765),
(151, 'l', 'l', 'l', 'l', 'l', 'l', 0x696e7365727420696d616765),
(152, 'm', 'm', 'm', 'm', 'm', 'm', 0x696e7365727420696d616765),
(153, 'n', 'n', 'n', 'n', 'n', 'n', 0x696e7365727420696d616765),
(154, 'o', 'o', 'o', 'o', 'o', 'o', 0x89504e470d0a1a0a0000000d49484452000002000000020008040000005e711c710000000467414d410000b18f0bfc6105000000206348524d00007a26000080840000fa00000080e8000075300000ea6000003a98000017709cba513c00000002624b47440000aa8d2332000000097048597300000ec400000ec401952b0e1b0000000774494d4507e401010f17075466ebe900000f174944415478daeddd7fccae7541c7f1cfe1201e0e073d1d040d2a4351c135123be5af9a3b64b8367fb5a6d45a6a16b4a156f6c3dcca3597953afcb112d9d4368595627354dafc9181ab147558c24245509ca622084304ce01c4d31ffe8163a4e7393cd7fdbdafebf37a3dff31ceb5cff59cfb7e9feb7a9efb79ee2de1de6cc94fe4e49c9847e661d995237344b68f9ec486dc965bf3addc906b72653e9bcb7345f68f9eb48eb68c1eb0761e9c5fca9eecc9d1a387b0a9aecbc5b92817e6fad143d68b00dc6d5b9e995fcf5373e8e8214ce6cebc3fe7e53db97df490752100dfb523bf9997e6d8d1335889eb726e5e9f6f8e9eb10e0420d9963fcc4bb26bf40c56ea1b393bafcf1da3678c26007bf2a69c387a04435c9d17e503a3478cd51d809d3937bf327a04439d9f17e65ba3478cd31c80c7e65d79f8e8110c77559e934f8d1e31ca21a3070cf3dbb9c4d39f248fc825f98dd12346d93a7ac0207f9c37d49e3bf774689e99c3f3a1d13346687c126ccd9bf2b2d12358333f9ba3f281be570b3606e00d79e1e809aca1c7e598fccbe811abd6178057e4a5a327b0a67e3adfce7f8c1eb15a6d013833678f9ec01adb932fe6b2d12356a9ebdb8027e7e3d9367a046b6d5f9ed0f44dc1a600ecc8a579d4e811acbdabb23b378f1eb12a4db7007f9b53474f60068eca43f24fa347ac4acf15c0937371d1d9725feccfa9f9f0e811abd1f294382c9fca49a347301b57e494dc397ac42ab4dc02fc417e75f40466e498dc988f8d1eb10a1d5700db734d8e193d8259f97a8ecfded123a6d7f1c340677afab3410fce0b464f5885862b80c3f2f9fcc8e8111b70733ebac89f50df9127e681a3476cc0977242c7d70196ee59d93fa38f8b177cb5f2a0fcebf0cfef463e9e36fa13c66678f7f007d2817f5c9d23467fba2675783e33fc737ce01f178cfe7471dfedcabee10fa403ff78c6e84fd7e44e1bfe393ef08fbdb3ba653928cbff22e03372ffd1130ed8d579efe80993fb60ae183de1806d5bfe4dc0f20330a797ff5e98ef8c9eb002ef1e3d6003e6f4e83928cb0fc09ed10336e03f470f58897f1f3d60037e7ef480a92d3d008f98d537003f3d7ac04acce7162079681e367ac2b4961e809f1c3d6043ae1d3d6025ae9bd58dcec9a3074c6be90178e4e8011b72dbe8012bf19d599de7c2df356ae90198d75fdf7cbe5f71dfcce9b732cdeb9f900d5b7a00e6750777d4e8012bb173566fc07ec2e801d35a7a007e68f4800de9f88565f3ba2adb397ac0b4961e801da3076cc8e3460f5889c78f1eb0210f183d605a02b04e16ffbab32499d9cb9d8f1c3d605a4bff71e0bdb3fa825372cae27f25f549b962568fbabdd93e7ac294967e053037af9bd593e360bc76666738afb51b2600eb654f5eb5e887dc2bf38ba327f0bd96fc604be6770b90241fcd858b7c638a23f3ccfcdce8111bb62f878f9e30250180ef67e101700b00c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a09001413002836a7b7695c9dfdb969f40436ddcec5ff0adc832000f7666f768d9ec0a6bb290f1c3d61fdb80580620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a098004031018062878e1eb096b6e6d9a327b0e9ee377ac03ada327ac0c4f666dbe809ccdabe1c3e7ac294dc0240310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0980040310180620200c504008a0900141300282600504c00a0d8a1a30730c85773756ec8ad49b6e74179788e1b3d881104a0cd67f2cfb93897e4e67bfcf723f384ecc933f2e8d10359a52da3074c6c6fb68d9eb0366ecf7979732efd01ffd7ee9c91e7e5fea3c7ae8d7d397cf48429094087bbf2e6fc45be7280fff771f9939c99ada347af05019835014892ffce19f9e406ffcceebc258f193d7c0d2c3c00be0bb07ce7e7491b7efa2797e6f1f9ebd1d3999a002cdb5d3933cfcdde83fab3b7e777f3fc7c7bf4293025df0558b23b737a2ebc4f47787b6ece05b9dfe813612aae00966b7fcebc8f4fff24b930cfcf77469f0a531180e57a79deb629c7f9fbbc62f4a93015df0558aa8b725aeedaa4631d92f7e5b4d12734c8c2bf0b2000cbf4cd9c94af6de2f18ecd67f280d12735c4c203e01660995ebea94fffe4abf9b3d1a7c4145c012cd1e773e2a67ffbeed07c2ec78f3eb1015c01303baf9ae0bbf7dfce6b469f169bcf15c0f2dc986373fb04c7dd96af65e7e8935b395700cccc3b2779fa27fbf20fa34f8dcd2600cbf3aec98e7cc1e85363b3b905589adbb26ba22b80645b6e5cf605f1bd700bc0ac7c6cb2a77fb22f1f1f7d7a6c2e01589aff99f4e8578c3e3d3697002ccd95931efdb3a34f8fcd25004b73ed8c8fceca09c0d27c6bc64767e5046069a6fb12609283fcdd42ac2d01589aed931e7dc7e8d3637309c0d21c39e3a3b37202b0343f36e9d11f3afaf4d85c02b0348f9af1d1593901589a53263dfa63469f1e9bcbcf022ccd5d79506e9ae8d8bb727ddd3f197e168059d99a53273bf6533c5e96c65fe8f2fcda0c8fcc206e0196e78e1c976f4c70dc63f2e51c36fae456ce2d003373587e6792e3fe5ee1d37ff15c012cd14d397ed3bf10b82bd754be33802b00666767fe7cd38ff9cacaa7ffe2b90258a6bbf2f85cba89c7fb995c52fa8f852b0066686bdeb189ff62efc8791e29cbe4af75a94ec85b37e9faee90bccd4b80974a0096ebd979e3a61ce7ecfcf2e853612a02b06467e52fefe355c096fc555e32fa34988e2f022edd5b7356ee3cc83f7b58cecd0b469fc0600bff22a0002cdfa5393d5f38883ff7a379679e387afc700b0f805b80e5db9d4fe6ac0dfe4d6fcd59b9dcd37ff904a0c1ce9c934fe4690778bdb7254fcb27724ee13b0117720bd0e4b2bc25efcc0ddfe7ff382aa7e70cbff6e37b2cfc164000dadc918fe4e27c2457e62bdff35f8fcd8979624ecd93fcc0cf3d08c0acdd905da327acaddb72436e49b223bb72c4e8316bebc61c357ac2940e1d3d6062b70ac0ff6bfbc4ef21b00c0b7f2fa4a57f1170e17f7d4c6ee18fa0a507e0a6d10398b99b460f98d6d20370302f8081bb5d3d7ac0b4961e802b470f60e616fe081200f87e16fe085a7a002e1b3d8099bb7cf480692dfd7500c9ffe6b8d11398ad2f2dfded50977e05905c3c7a0033f66fa3074c6df901b868f400666cf18f9ee5df02ecca5773ffd12398a57df961af0398bb1bf3bed11398a9f72cfde9df1080e4fcd10398a98247cef26f0192c3f205df0960c3be9c871ff46f539c8d862b803bf2dad11398a1d72cffe9df7105906ccf1773f4e811cccad7737cf68e1e31bd862b80e4b69c3d7a0233f3ea86a77fcb154072582ecb89a347301b57e494861b80962b80e48ebc78f40466637f5edcf1f4ef0940f2a1fcdde809ccc4db7b5e40de720b90243b72a977b9e507ba2abb73f3e811abd2730590dc92e764dfe811acb97d39bde7e99f6c1d3d60a5be9eebf3f4d1235863fb7346de3f7ac42a750520f9640ec993478f606dfd69de387ac26ab50520f9708ecae3468f602d9d9b978d9eb06a7d01483e9887e4a7468f60ed9c931767ffe811abd61880fd796ff6e529a367b0565e9ddfef7bfa770620493e926bf30b8b7f63340eccbe9cd1fa62f1a6d701dcd36373414e183d82e13e97d3f3a9d12346697a1dc03dfd5776e71da34730d879d9ddfbf4efbe02f8ae3d3927278d1ec11057e545f9e0e81163b57e0de06e5fcc5bb3378ff156d965aecf2bf2bc7c6ef48cd15c017cd711f9adfc915f1c56e2babc2e7f93db46cf58070270b76d797a9e9ba7e67ea38730993bf2be9c9ff7e6f6d143d68500dcd3d179564ecd9e3c78f41036d5b5b92817e51f73c3e821eb4500eedd963c3a27e7c43c2a3f9ea3734476e488d193d8905b734b6ec937724daecc6773793e3d7ad07afa3fe35690b0f26c65560000002574455874646174653a63726561746500323032302d30312d30315431353a32333a30372b30303a303083ca19120000002574455874646174653a6d6f6469667900323032302d30312d30315431353a32333a30372b30303a3030f297a1ae0000001974455874536f667477617265007777772e696e6b73636170652e6f72679bee3c1a0000000049454e44ae426082),
(155, 'p', 'p', 'p', 'p', 'p', 'p', 0x696e7365727420696d616765),
(156, 'q', 'q', 'q', 'q', 'q', 'q', 0x696e7365727420696d616765),
(157, 'r', 'r', 'r', 'r', 'r', 'r', 0x696e7365727420696d616765),
(158, 's', 's', 's', 's', 's', 's', 0x696e7365727420696d616765),
(159, 't', 't', 't', 't', 't', 't', 0x696e7365727420696d616765),
(160, 'w', 'w', 'w', 'w', 'w', 'w', 0x696e7365727420696d616765),
(161, 'z', 'z', 'z', 'z', 'z', 'z', 0x696e7365727420696d616765),
(162, 'y', 'y', 'y', 'y', 'y', 'y', 0x696e7365727420696d616765),
(163, 'x', 'x', 'x', 'x', 'x', 'x', 0x696e7365727420696d616765);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `access`
--
ALTER TABLE `access`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `frequent`
--
ALTER TABLE `frequent`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `word`
--
ALTER TABLE `word`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `access`
--
ALTER TABLE `access`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `frequent`
--
ALTER TABLE `frequent`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT for table `word`
--
ALTER TABLE `word`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=164;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
