CREATE DATABASE  IF NOT EXISTS `ufartdb`; 
USE `ufartdb`;

--
-- Table structure for table `persons`
--

DROP TABLE IF EXISTS `persons`;

CREATE TABLE `persons` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `PersonNameIndex` (`Name`(767))
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


--
-- Table structure for table `sites`
--

DROP TABLE IF EXISTS `sites`;

CREATE TABLE `sites` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `SitesNameIndex` (`Name`(767))
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- Table structure for table `keywords`
--

DROP TABLE IF EXISTS `keywords`;

CREATE TABLE `keywords` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(2048) DEFAULT NULL,
  `PersonId` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `KeywordNameIndex` (`Name`(767)),
  KEY `FKPersonKeywords_idx` (`PersonId`),
  CONSTRAINT `FKPersonKeywords` FOREIGN KEY (`PersonId`) REFERENCES `persons` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- Table structure for table `pages`
--

DROP TABLE IF EXISTS `pages`;
CREATE TABLE `pages` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Url` varchar(2048) DEFAULT NULL,
  `SiteId` int(11) NOT NULL,
  `FoundDateTime` datetime DEFAULT NULL,
  `LastScanDate` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `PagesFoundDateIndex` (`FoundDateTime`),
  KEY `PagesLastScanDateIndex` (`LastScanDate`),
  KEY `PagesUrlnameIndex` (`Url`(767)),
  KEY `FKSitePage_idx` (`SiteId`),
  CONSTRAINT `FKSitePage` FOREIGN KEY (`SiteId`) REFERENCES `sites` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--
-- Table structure for table `personpagerank`
--

DROP TABLE IF EXISTS `personpagerank`;

CREATE TABLE `personpagerank` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `PersonId` int(11) NOT NULL,
  `PageId` int(11) NOT NULL,
  `Rank` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `PersonIdPageIdIndex` (`PersonId`,`PageId`),
  KEY `FKPageRank_idx` (`PageId`),
  CONSTRAINT `FKPersonRank` FOREIGN KEY (`PersonId`) REFERENCES `persons` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FKPageRank` FOREIGN KEY (`PageId`) REFERENCES `pages` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

