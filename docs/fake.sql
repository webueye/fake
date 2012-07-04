# MySQL-Front 5.1  (Build 3.57)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: 127.0.0.1    Database: fake
# ------------------------------------------------------
# Server version 5.1.57-community

USE `fake`;

#
# Source for table taoists_account
#

CREATE TABLE `taoists_account` (
  `id` bigint(20) NOT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `lastModifyDateTime` datetime DEFAULT NULL,
  `admin` tinyint(1) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `email` varchar(16) DEFAULT '',
  `mobile` varchar(16) DEFAULT '',
  `nickname` varchar(16) DEFAULT '',
  `password` varchar(64) DEFAULT '',
  `phone` varchar(16) DEFAULT '',
  `sex` tinyint(1) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `userNo` varchar(16) DEFAULT '',
  `username` varchar(16) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table taoists_account
#
LOCK TABLES `taoists_account` WRITE;
/*!40000 ALTER TABLE `taoists_account` DISABLE KEYS */;

INSERT INTO `taoists_account` (`id`,`createDateTime`,`lastModifyDateTime`,`admin`,`companyId`,`email`,`mobile`,`nickname`,`password`,`phone`,`sex`,`status`,`userNo`,`username`) VALUES (1,NULL,NULL,NULL,1,'','','admin','21232f297a57a5a743894a0e4a801fc3','',NULL,NULL,'','admin');
INSERT INTO `taoists_account` (`id`,`createDateTime`,`lastModifyDateTime`,`admin`,`companyId`,`email`,`mobile`,`nickname`,`password`,`phone`,`sex`,`status`,`userNo`,`username`) VALUES (2,'2012-06-30 16:15:56','2012-06-30 16:23:29',0,2,'rubys@vip.qq.com','','rubys','21232f297a57a5a743894a0e4a801fc3','',0,1,'0000002','rubys');
/*!40000 ALTER TABLE `taoists_account` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table taoists_box_code
#

CREATE TABLE `taoists_box_code` (
  `id` bigint(20) NOT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `lastModifyDateTime` datetime DEFAULT NULL,
  `boxCode` varchar(32) DEFAULT '',
  `status` int(11) DEFAULT NULL,
  `statusCode` int(11) DEFAULT NULL,
  `box_spec_id` bigint(20) DEFAULT NULL,
  `code_issue_id` bigint(20) DEFAULT NULL,
  `creation_company_id` bigint(20) DEFAULT NULL,
  `storage_company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3A2AFDD59B26006F` (`creation_company_id`),
  KEY `FK3A2AFDD51CD02F25` (`code_issue_id`),
  KEY `FK3A2AFDD53D9C81D3` (`box_spec_id`),
  KEY `FK3A2AFDD56AC61353` (`storage_company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table taoists_box_code
#
LOCK TABLES `taoists_box_code` WRITE;
/*!40000 ALTER TABLE `taoists_box_code` DISABLE KEYS */;

INSERT INTO `taoists_box_code` (`id`,`createDateTime`,`lastModifyDateTime`,`boxCode`,`status`,`statusCode`,`box_spec_id`,`code_issue_id`,`creation_company_id`,`storage_company_id`) VALUES (1,'2012-06-30 23:14:11','2012-06-30 23:30:20','00001120630000001',4,2,1,1,1,2);
INSERT INTO `taoists_box_code` (`id`,`createDateTime`,`lastModifyDateTime`,`boxCode`,`status`,`statusCode`,`box_spec_id`,`code_issue_id`,`creation_company_id`,`storage_company_id`) VALUES (2,'2012-06-30 23:14:11','2012-06-30 23:14:11','00001120630000002',3,1,1,1,1,1);
INSERT INTO `taoists_box_code` (`id`,`createDateTime`,`lastModifyDateTime`,`boxCode`,`status`,`statusCode`,`box_spec_id`,`code_issue_id`,`creation_company_id`,`storage_company_id`) VALUES (3,'2012-07-02 19:50:58','2012-07-02 19:50:58','00002120702000001',3,1,2,3,1,1);
INSERT INTO `taoists_box_code` (`id`,`createDateTime`,`lastModifyDateTime`,`boxCode`,`status`,`statusCode`,`box_spec_id`,`code_issue_id`,`creation_company_id`,`storage_company_id`) VALUES (4,'2012-07-02 19:50:58','2012-07-02 19:50:58','00002120702000002',3,1,2,3,1,1);
/*!40000 ALTER TABLE `taoists_box_code` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table taoists_box_spec
#

CREATE TABLE `taoists_box_spec` (
  `id` bigint(20) NOT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `lastModifyDateTime` datetime DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `creationCompanyId` bigint(20) DEFAULT NULL,
  `memo` varchar(255) DEFAULT '',
  `specName` varchar(64) DEFAULT '',
  `specNo` varchar(64) DEFAULT '',
  `status` tinyint(1) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3A3247A3F06C75C` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table taoists_box_spec
#
LOCK TABLES `taoists_box_spec` WRITE;
/*!40000 ALTER TABLE `taoists_box_spec` DISABLE KEYS */;

INSERT INTO `taoists_box_spec` (`id`,`createDateTime`,`lastModifyDateTime`,`capacity`,`creationCompanyId`,`memo`,`specName`,`specNo`,`status`,`product_id`) VALUES (1,'2012-06-29 21:42:09','2012-06-29 21:42:09',30,NULL,'','VT','00001',1,1);
INSERT INTO `taoists_box_spec` (`id`,`createDateTime`,`lastModifyDateTime`,`capacity`,`creationCompanyId`,`memo`,`specName`,`specNo`,`status`,`product_id`) VALUES (2,'2012-06-29 21:42:24','2012-06-29 21:42:24',50,NULL,'','AML','00002',1,2);
INSERT INTO `taoists_box_spec` (`id`,`createDateTime`,`lastModifyDateTime`,`capacity`,`creationCompanyId`,`memo`,`specName`,`specNo`,`status`,`product_id`) VALUES (3,'2012-06-29 21:42:44','2012-06-29 21:42:44',20,NULL,'','PAR','00003',1,3);
/*!40000 ALTER TABLE `taoists_box_spec` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table taoists_box_trace
#

CREATE TABLE `taoists_box_trace` (
  `id` bigint(20) NOT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `lastModifyDateTime` datetime DEFAULT NULL,
  `eventType` int(11) DEFAULT NULL,
  `traceDateTime` datetime DEFAULT NULL,
  `box_code_id` bigint(20) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC259E5DE89ACB0F` (`company_id`),
  KEY `FKC259E5DC12039F7` (`box_code_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table taoists_box_trace
#
LOCK TABLES `taoists_box_trace` WRITE;
/*!40000 ALTER TABLE `taoists_box_trace` DISABLE KEYS */;

INSERT INTO `taoists_box_trace` (`id`,`createDateTime`,`lastModifyDateTime`,`eventType`,`traceDateTime`,`box_code_id`,`company_id`) VALUES (1,'2012-06-30 23:27:49','2012-06-30 23:27:49',NULL,'2012-06-30 23:27:49',1,1);
INSERT INTO `taoists_box_trace` (`id`,`createDateTime`,`lastModifyDateTime`,`eventType`,`traceDateTime`,`box_code_id`,`company_id`) VALUES (2,'2012-06-30 23:29:50','2012-06-30 23:29:50',NULL,'2012-06-30 23:29:50',1,1);
INSERT INTO `taoists_box_trace` (`id`,`createDateTime`,`lastModifyDateTime`,`eventType`,`traceDateTime`,`box_code_id`,`company_id`) VALUES (3,'2012-06-30 23:30:20','2012-06-30 23:30:20',NULL,'2012-06-30 23:30:20',1,2);
/*!40000 ALTER TABLE `taoists_box_trace` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table taoists_brand
#

CREATE TABLE `taoists_brand` (
  `id` bigint(20) NOT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `lastModifyDateTime` datetime DEFAULT NULL,
  `brandDesc` varchar(255) DEFAULT '',
  `brandName` varchar(32) DEFAULT '',
  `brandSpell` varchar(32) DEFAULT '',
  `link` varchar(32) DEFAULT '',
  `logoLink` varchar(255) DEFAULT '',
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table taoists_brand
#
LOCK TABLES `taoists_brand` WRITE;
/*!40000 ALTER TABLE `taoists_brand` DISABLE KEYS */;

INSERT INTO `taoists_brand` (`id`,`createDateTime`,`lastModifyDateTime`,`brandDesc`,`brandName`,`brandSpell`,`link`,`logoLink`,`status`) VALUES (1,'2012-06-16 19:45:40','2012-06-29 23:54:00','VT','维亭','vt','http://test.com','http://statics.amr01.com/images/logo.jpg',1);
INSERT INTO `taoists_brand` (`id`,`createDateTime`,`lastModifyDateTime`,`brandDesc`,`brandName`,`brandSpell`,`link`,`logoLink`,`status`) VALUES (2,'2012-06-16 19:46:13','2012-06-29 23:54:06','aml','安美莱','aml','http://test.com','http://statics.amr01.com/images/logo.jpg',1);
INSERT INTO `taoists_brand` (`id`,`createDateTime`,`lastModifyDateTime`,`brandDesc`,`brandName`,`brandSpell`,`link`,`logoLink`,`status`) VALUES (3,'2012-06-16 19:46:43','2012-06-29 23:54:12','porai','佰利莱','porai','http://test.com','http://statics.amr01.com/images/logo.jpg',1);
/*!40000 ALTER TABLE `taoists_brand` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table taoists_code_issue
#

CREATE TABLE `taoists_code_issue` (
  `id` bigint(20) NOT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `lastModifyDateTime` datetime DEFAULT NULL,
  `barCode` varchar(32) DEFAULT '',
  `codeLength` int(11) DEFAULT NULL,
  `codeType` tinyint(1) DEFAULT NULL,
  `issueCount` int(11) DEFAULT NULL,
  `issueName` varchar(32) DEFAULT '',
  `memo` varchar(255) DEFAULT '',
  `status` tinyint(1) DEFAULT NULL,
  `box_spec_id` bigint(20) DEFAULT NULL,
  `creationCompany_id` bigint(20) DEFAULT NULL,
  `operator_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK685954FB3D9C81D3` (`box_spec_id`),
  KEY `FK685954FB6B8CCE69` (`operator_id`),
  KEY `FK685954FB5FE26F2E` (`creationCompany_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table taoists_code_issue
#
LOCK TABLES `taoists_code_issue` WRITE;
/*!40000 ALTER TABLE `taoists_code_issue` DISABLE KEYS */;

INSERT INTO `taoists_code_issue` (`id`,`createDateTime`,`lastModifyDateTime`,`barCode`,`codeLength`,`codeType`,`issueCount`,`issueName`,`memo`,`status`,`box_spec_id`,`creationCompany_id`,`operator_id`) VALUES (1,'2012-06-30 23:14:11','2012-06-30 23:14:11',NULL,NULL,1,2,'vt-box-code-gen','vt-box-code-gen',NULL,1,1,NULL);
INSERT INTO `taoists_code_issue` (`id`,`createDateTime`,`lastModifyDateTime`,`barCode`,`codeLength`,`codeType`,`issueCount`,`issueName`,`memo`,`status`,`box_spec_id`,`creationCompany_id`,`operator_id`) VALUES (2,'2012-06-30 23:20:39','2012-06-30 23:20:39',NULL,12,0,5,'vt-fake-code-gen','vt-fake-code-gen',NULL,1,1,NULL);
INSERT INTO `taoists_code_issue` (`id`,`createDateTime`,`lastModifyDateTime`,`barCode`,`codeLength`,`codeType`,`issueCount`,`issueName`,`memo`,`status`,`box_spec_id`,`creationCompany_id`,`operator_id`) VALUES (3,'2012-07-02 19:50:58','2012-07-02 19:50:58',NULL,NULL,1,2,'box-code-gen-02','',NULL,2,1,NULL);
INSERT INTO `taoists_code_issue` (`id`,`createDateTime`,`lastModifyDateTime`,`barCode`,`codeLength`,`codeType`,`issueCount`,`issueName`,`memo`,`status`,`box_spec_id`,`creationCompany_id`,`operator_id`) VALUES (4,'2012-07-02 20:05:38','2012-07-02 20:05:38',NULL,12,0,6,'gen-fake-code','gen-fake-code',NULL,3,1,NULL);
/*!40000 ALTER TABLE `taoists_code_issue` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table taoists_code_query
#

CREATE TABLE `taoists_code_query` (
  `id` bigint(20) NOT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `lastModifyDateTime` datetime DEFAULT NULL,
  `codeNo` varchar(16) DEFAULT '',
  `fakeCode` tinyblob,
  `queryDateTime` varchar(32) DEFAULT '',
  `queryResult` int(11) DEFAULT NULL,
  `queryWay` int(11) DEFAULT NULL,
  `userNo` varchar(32) DEFAULT '',
  `userPhone` varchar(16) DEFAULT '',
  `fakeCode_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK68CAC4EA43B295F4` (`fakeCode_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table taoists_code_query
#
LOCK TABLES `taoists_code_query` WRITE;
/*!40000 ALTER TABLE `taoists_code_query` DISABLE KEYS */;

INSERT INTO `taoists_code_query` (`id`,`createDateTime`,`lastModifyDateTime`,`codeNo`,`fakeCode`,`queryDateTime`,`queryResult`,`queryWay`,`userNo`,`userPhone`,`fakeCode_id`) VALUES (1,'2012-07-04 20:09:32','2012-07-04 20:09:32','947098234535',NULL,'2012-07-04 20:09:32',1,1,'2','18621512999',1);
INSERT INTO `taoists_code_query` (`id`,`createDateTime`,`lastModifyDateTime`,`codeNo`,`fakeCode`,`queryDateTime`,`queryResult`,`queryWay`,`userNo`,`userPhone`,`fakeCode_id`) VALUES (2,'2012-07-04 20:10:50','2012-07-04 20:10:50','947098234535',NULL,'2012-07-04 20:10:50',1,1,'2','18621512999',1);
INSERT INTO `taoists_code_query` (`id`,`createDateTime`,`lastModifyDateTime`,`codeNo`,`fakeCode`,`queryDateTime`,`queryResult`,`queryWay`,`userNo`,`userPhone`,`fakeCode_id`) VALUES (3,'2012-07-04 20:11:32','2012-07-04 20:11:32','947098234535',NULL,'2012-07-04 20:11:32',1,1,'2','18621512999',1);
INSERT INTO `taoists_code_query` (`id`,`createDateTime`,`lastModifyDateTime`,`codeNo`,`fakeCode`,`queryDateTime`,`queryResult`,`queryWay`,`userNo`,`userPhone`,`fakeCode_id`) VALUES (4,'2012-07-04 20:12:40','2012-07-04 20:12:40','947098234535',NULL,'2012-07-04 20:12:40',0,1,'2','18621512999',1);
INSERT INTO `taoists_code_query` (`id`,`createDateTime`,`lastModifyDateTime`,`codeNo`,`fakeCode`,`queryDateTime`,`queryResult`,`queryWay`,`userNo`,`userPhone`,`fakeCode_id`) VALUES (5,'2012-07-04 20:14:25','2012-07-04 20:14:25','9470982345305',NULL,'2012-07-04 20:14:25',-1,1,'2','18621512999',NULL);
/*!40000 ALTER TABLE `taoists_code_query` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table taoists_company
#

CREATE TABLE `taoists_company` (
  `id` bigint(20) NOT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `lastModifyDateTime` datetime DEFAULT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  `address` varchar(128) DEFAULT '',
  `bankAccountName` varchar(16) DEFAULT '',
  `bankAccountNo` varchar(32) DEFAULT '',
  `belongBankName` varchar(32) DEFAULT '',
  `businessCount` varchar(16) DEFAULT '',
  `companyName` varchar(32) DEFAULT '',
  `companyZip` varchar(16) DEFAULT '',
  `compnayNo` varchar(32) DEFAULT '',
  `depotArea` varchar(16) DEFAULT '',
  `employeeCount` varchar(16) DEFAULT '',
  `memo` varchar(255) DEFAULT '',
  `scale` varchar(16) DEFAULT '',
  `shippingAddress` varchar(128) DEFAULT '',
  `shippingMan` varchar(16) DEFAULT '',
  `shippingPhone` varchar(16) DEFAULT '',
  `status` tinyint(1) DEFAULT NULL,
  `zoneNo` varchar(16) DEFAULT '',
  `companyNature_id` bigint(20) DEFAULT NULL,
  `companyType_id` bigint(20) DEFAULT NULL,
  `saleForm_id` bigint(20) DEFAULT NULL,
  `saleRegion_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD31A80092B3C67E1` (`companyType_id`),
  KEY `FKD31A80091634709D` (`saleRegion_id`),
  KEY `FKD31A8009BAE73A94` (`companyNature_id`),
  KEY `FKD31A8009A40A0B0D` (`saleForm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table taoists_company
#
LOCK TABLES `taoists_company` WRITE;
/*!40000 ALTER TABLE `taoists_company` DISABLE KEYS */;

INSERT INTO `taoists_company` (`id`,`createDateTime`,`lastModifyDateTime`,`parentId`,`address`,`bankAccountName`,`bankAccountNo`,`belongBankName`,`businessCount`,`companyName`,`companyZip`,`compnayNo`,`depotArea`,`employeeCount`,`memo`,`scale`,`shippingAddress`,`shippingMan`,`shippingPhone`,`status`,`zoneNo`,`companyNature_id`,`companyType_id`,`saleForm_id`,`saleRegion_id`) VALUES (1,NULL,NULL,NULL,'','','','','','System','','','','','','','','','',NULL,'',NULL,NULL,NULL,NULL);
INSERT INTO `taoists_company` (`id`,`createDateTime`,`lastModifyDateTime`,`parentId`,`address`,`bankAccountName`,`bankAccountNo`,`belongBankName`,`businessCount`,`companyName`,`companyZip`,`compnayNo`,`depotArea`,`employeeCount`,`memo`,`scale`,`shippingAddress`,`shippingMan`,`shippingPhone`,`status`,`zoneNo`,`companyNature_id`,`companyType_id`,`saleForm_id`,`saleRegion_id`) VALUES (2,'2012-06-30 10:39:15','2012-06-30 11:01:56',1,'','','','','100','泰尔','','000001','11','100','\t\t\t\t\t\t\t\t\t','11','','','',1,'',7,11,14,1);
INSERT INTO `taoists_company` (`id`,`createDateTime`,`lastModifyDateTime`,`parentId`,`address`,`bankAccountName`,`bankAccountNo`,`belongBankName`,`businessCount`,`companyName`,`companyZip`,`compnayNo`,`depotArea`,`employeeCount`,`memo`,`scale`,`shippingAddress`,`shippingMan`,`shippingPhone`,`status`,`zoneNo`,`companyNature_id`,`companyType_id`,`saleForm_id`,`saleRegion_id`) VALUES (3,'2012-06-30 10:40:55','2012-06-30 11:01:44',1,'','','','','','惠美惠','','000002','','','\t\t\t\t\t\t\t\t\t','','','','',0,'',9,11,15,3);
/*!40000 ALTER TABLE `taoists_company` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table taoists_contact
#

CREATE TABLE `taoists_contact` (
  `id` bigint(20) NOT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `lastModifyDateTime` datetime DEFAULT NULL,
  `birthday` tinyblob,
  `contactType` int(11) DEFAULT NULL,
  `deptName` varchar(32) DEFAULT '',
  `email` varchar(32) DEFAULT '',
  `idCard` varchar(32) DEFAULT '',
  `interest` varchar(128) DEFAULT '',
  `mobile` varchar(16) DEFAULT '',
  `name` varchar(16) DEFAULT '',
  `nativePlace` varchar(16) DEFAULT '',
  `officePhone` varchar(16) DEFAULT '',
  `ownerDeptId` bigint(20) DEFAULT NULL,
  `ownerEmployeeId` bigint(20) DEFAULT NULL,
  `position` varchar(16) DEFAULT '',
  `sex` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD32A67ACE89ACB0F` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table taoists_contact
#
LOCK TABLES `taoists_contact` WRITE;
/*!40000 ALTER TABLE `taoists_contact` DISABLE KEYS */;

INSERT INTO `taoists_contact` (`id`,`createDateTime`,`lastModifyDateTime`,`birthday`,`contactType`,`deptName`,`email`,`idCard`,`interest`,`mobile`,`name`,`nativePlace`,`officePhone`,`ownerDeptId`,`ownerEmployeeId`,`position`,`sex`,`status`,`company_id`) VALUES (1,'2012-06-30 10:39:15','2012-06-30 10:39:15',X'ACED0005737200176F72672E6A6F64612E74696D652E4C6F63616C44617465FFFFF804D3E4EBB50200024A000C694C6F63616C4D696C6C69734C000B694368726F6E6F6C6F677974001A4C6F72672F6A6F64612F74696D652F4368726F6E6F6C6F67793B787000000137E323C000737200276F72672E6A6F64612E74696D652E6368726F6E6F2E49534F4368726F6E6F6C6F67792453747562A9C811667137502703000078707372001F6F72672E6A6F64612E74696D652E4461746554696D655A6F6E652453747562A62F019A7C321AE30300007870770500035554437878',0,NULL,'','',NULL,'','A','','',NULL,NULL,NULL,NULL,NULL,2);
INSERT INTO `taoists_contact` (`id`,`createDateTime`,`lastModifyDateTime`,`birthday`,`contactType`,`deptName`,`email`,`idCard`,`interest`,`mobile`,`name`,`nativePlace`,`officePhone`,`ownerDeptId`,`ownerEmployeeId`,`position`,`sex`,`status`,`company_id`) VALUES (2,'2012-06-30 10:39:15','2012-06-30 10:39:15',X'ACED0005737200176F72672E6A6F64612E74696D652E4C6F63616C44617465FFFFF804D3E4EBB50200024A000C694C6F63616C4D696C6C69734C000B694368726F6E6F6C6F677974001A4C6F72672F6A6F64612F74696D652F4368726F6E6F6C6F67793B787000000137BF173C00737200276F72672E6A6F64612E74696D652E6368726F6E6F2E49534F4368726F6E6F6C6F67792453747562A9C811667137502703000078707372001F6F72672E6A6F64612E74696D652E4461746554696D655A6F6E652453747562A62F019A7C321AE30300007870770500035554437878',2,NULL,'','',NULL,'','B','','',NULL,NULL,NULL,NULL,NULL,2);
INSERT INTO `taoists_contact` (`id`,`createDateTime`,`lastModifyDateTime`,`birthday`,`contactType`,`deptName`,`email`,`idCard`,`interest`,`mobile`,`name`,`nativePlace`,`officePhone`,`ownerDeptId`,`ownerEmployeeId`,`position`,`sex`,`status`,`company_id`) VALUES (3,'2012-06-30 10:40:55','2012-06-30 10:40:55',NULL,0,NULL,'','',NULL,'','C','','',NULL,NULL,NULL,NULL,NULL,3);
INSERT INTO `taoists_contact` (`id`,`createDateTime`,`lastModifyDateTime`,`birthday`,`contactType`,`deptName`,`email`,`idCard`,`interest`,`mobile`,`name`,`nativePlace`,`officePhone`,`ownerDeptId`,`ownerEmployeeId`,`position`,`sex`,`status`,`company_id`) VALUES (4,'2012-06-30 10:40:55','2012-06-30 10:40:55',NULL,2,NULL,'','',NULL,'','D','','',NULL,NULL,NULL,NULL,NULL,3);
/*!40000 ALTER TABLE `taoists_contact` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table taoists_data_dict
#

CREATE TABLE `taoists_data_dict` (
  `id` bigint(20) NOT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `lastModifyDateTime` datetime DEFAULT NULL,
  `code` varchar(32) DEFAULT '',
  `memo` varchar(255) DEFAULT '',
  `name` varchar(32) DEFAULT '',
  `status` tinyint(1) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB5009FB78DAC440E` (`category_id`),
  KEY `FKB5009FB74C8C0B8E` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table taoists_data_dict
#
LOCK TABLES `taoists_data_dict` WRITE;
/*!40000 ALTER TABLE `taoists_data_dict` DISABLE KEYS */;

INSERT INTO `taoists_data_dict` (`id`,`createDateTime`,`lastModifyDateTime`,`code`,`memo`,`name`,`status`,`category_id`,`parent_id`) VALUES (1,'2012-06-11 19:36:06','2012-06-16 19:36:06','DB','东北大区','东北大区',1,1,NULL);
INSERT INTO `taoists_data_dict` (`id`,`createDateTime`,`lastModifyDateTime`,`code`,`memo`,`name`,`status`,`category_id`,`parent_id`) VALUES (2,'2012-06-16 19:36:26','2012-06-16 19:36:26','HB','华北大区','华北大区',1,1,NULL);
INSERT INTO `taoists_data_dict` (`id`,`createDateTime`,`lastModifyDateTime`,`code`,`memo`,`name`,`status`,`category_id`,`parent_id`) VALUES (3,'2012-06-16 19:36:44','2012-06-16 19:36:44','XB','西北大区','西北大区',1,1,NULL);
INSERT INTO `taoists_data_dict` (`id`,`createDateTime`,`lastModifyDateTime`,`code`,`memo`,`name`,`status`,`category_id`,`parent_id`) VALUES (4,'2012-06-16 19:37:02','2012-06-16 19:37:02','HD','华东大区','华东大区',1,1,NULL);
INSERT INTO `taoists_data_dict` (`id`,`createDateTime`,`lastModifyDateTime`,`code`,`memo`,`name`,`status`,`category_id`,`parent_id`) VALUES (5,'2012-06-16 19:37:22','2012-06-16 19:37:22','HN','华南大区','华南大区',1,1,NULL);
INSERT INTO `taoists_data_dict` (`id`,`createDateTime`,`lastModifyDateTime`,`code`,`memo`,`name`,`status`,`category_id`,`parent_id`) VALUES (6,'2012-06-16 19:37:40','2012-06-16 19:37:40','XN','西南大区','西南大区',1,1,NULL);
INSERT INTO `taoists_data_dict` (`id`,`createDateTime`,`lastModifyDateTime`,`code`,`memo`,`name`,`status`,`category_id`,`parent_id`) VALUES (7,'2012-06-16 19:38:55','2012-06-16 19:38:55','GQ','国企','国企',1,3,NULL);
INSERT INTO `taoists_data_dict` (`id`,`createDateTime`,`lastModifyDateTime`,`code`,`memo`,`name`,`status`,`category_id`,`parent_id`) VALUES (8,'2012-06-16 19:39:09','2012-06-16 19:39:09','WQ','外企','外企',1,3,NULL);
INSERT INTO `taoists_data_dict` (`id`,`createDateTime`,`lastModifyDateTime`,`code`,`memo`,`name`,`status`,`category_id`,`parent_id`) VALUES (9,'2012-06-16 19:39:21','2012-06-16 19:39:21','HZ','合资','合资',1,3,NULL);
INSERT INTO `taoists_data_dict` (`id`,`createDateTime`,`lastModifyDateTime`,`code`,`memo`,`name`,`status`,`category_id`,`parent_id`) VALUES (10,'2012-06-16 19:39:36','2012-06-16 19:39:36','MY','民营','民营',1,3,NULL);
INSERT INTO `taoists_data_dict` (`id`,`createDateTime`,`lastModifyDateTime`,`code`,`memo`,`name`,`status`,`category_id`,`parent_id`) VALUES (11,'2012-06-16 19:41:42','2012-06-16 19:41:42','distributor','经销商','经销商',1,2,NULL);
INSERT INTO `taoists_data_dict` (`id`,`createDateTime`,`lastModifyDateTime`,`code`,`memo`,`name`,`status`,`category_id`,`parent_id`) VALUES (12,'2012-06-16 19:42:06','2012-06-16 19:42:06','potential-customer','潜在客户','潜在客户',1,2,NULL);
INSERT INTO `taoists_data_dict` (`id`,`createDateTime`,`lastModifyDateTime`,`code`,`memo`,`name`,`status`,`category_id`,`parent_id`) VALUES (13,'2012-06-16 19:42:20','2012-06-16 19:42:20','terminal-customer','终端客户','终端客户',1,2,NULL);
INSERT INTO `taoists_data_dict` (`id`,`createDateTime`,`lastModifyDateTime`,`code`,`memo`,`name`,`status`,`category_id`,`parent_id`) VALUES (14,'2012-06-16 19:42:51','2012-06-16 19:42:51','wholesale','批发','批发',1,4,NULL);
INSERT INTO `taoists_data_dict` (`id`,`createDateTime`,`lastModifyDateTime`,`code`,`memo`,`name`,`status`,`category_id`,`parent_id`) VALUES (15,'2012-06-16 19:43:08','2012-06-16 19:43:08','retail','零售','零售',1,4,NULL);
INSERT INTO `taoists_data_dict` (`id`,`createDateTime`,`lastModifyDateTime`,`code`,`memo`,`name`,`status`,`category_id`,`parent_id`) VALUES (16,'2012-06-16 19:43:47','2012-06-16 19:43:47','health-care','保健','保健',1,5,NULL);
INSERT INTO `taoists_data_dict` (`id`,`createDateTime`,`lastModifyDateTime`,`code`,`memo`,`name`,`status`,`category_id`,`parent_id`) VALUES (17,'2012-06-16 19:44:12','2012-06-16 19:44:12','to-lose-weight','减肥','减肥',1,5,NULL);
/*!40000 ALTER TABLE `taoists_data_dict` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table taoists_dept
#

CREATE TABLE `taoists_dept` (
  `id` bigint(20) NOT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `lastModifyDateTime` datetime DEFAULT NULL,
  `deptNo` varchar(32) DEFAULT '',
  `faxNo` varchar(16) DEFAULT '',
  `name` varchar(32) DEFAULT '',
  `ordres` int(11) DEFAULT NULL,
  `parent` tinyblob,
  `recordStatus` int(11) DEFAULT NULL,
  `telNo` varchar(16) DEFAULT '',
  `company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKF314639E89ACB0F` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table taoists_dept
#
LOCK TABLES `taoists_dept` WRITE;
/*!40000 ALTER TABLE `taoists_dept` DISABLE KEYS */;

/*!40000 ALTER TABLE `taoists_dept` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table taoists_dict_category
#

CREATE TABLE `taoists_dict_category` (
  `id` bigint(20) NOT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `lastModifyDateTime` datetime DEFAULT NULL,
  `categoryCode` varchar(32) DEFAULT '',
  `categoryDesc` varchar(32) DEFAULT '',
  `categoryName` varchar(32) DEFAULT '',
  `companyId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table taoists_dict_category
#
LOCK TABLES `taoists_dict_category` WRITE;
/*!40000 ALTER TABLE `taoists_dict_category` DISABLE KEYS */;

INSERT INTO `taoists_dict_category` (`id`,`createDateTime`,`lastModifyDateTime`,`categoryCode`,`categoryDesc`,`categoryName`,`companyId`) VALUES (1,'2012-06-16 19:33:47','2012-06-16 19:33:47','saleRegion','销售区域','销售区域',1);
INSERT INTO `taoists_dict_category` (`id`,`createDateTime`,`lastModifyDateTime`,`categoryCode`,`categoryDesc`,`categoryName`,`companyId`) VALUES (2,'2012-06-16 19:34:11','2012-06-16 19:34:11','companyType','企业类型','企业类型',1);
INSERT INTO `taoists_dict_category` (`id`,`createDateTime`,`lastModifyDateTime`,`categoryCode`,`categoryDesc`,`categoryName`,`companyId`) VALUES (3,'2012-06-16 19:34:31','2012-06-16 19:34:31','companyNature','企业性质','企业性质',1);
INSERT INTO `taoists_dict_category` (`id`,`createDateTime`,`lastModifyDateTime`,`categoryCode`,`categoryDesc`,`categoryName`,`companyId`) VALUES (4,'2012-06-16 19:34:52','2012-06-16 19:34:52','saleForm','销售形式','销售形式',1);
INSERT INTO `taoists_dict_category` (`id`,`createDateTime`,`lastModifyDateTime`,`categoryCode`,`categoryDesc`,`categoryName`,`companyId`) VALUES (5,'2012-06-16 19:35:08','2012-06-16 19:35:08','productCategory','产品分类','产品分类',1);
/*!40000 ALTER TABLE `taoists_dict_category` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table taoists_fake_code
#

CREATE TABLE `taoists_fake_code` (
  `id` bigint(20) NOT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `lastModifyDateTime` datetime DEFAULT NULL,
  `fakeCode` varchar(32) DEFAULT '',
  `firstQueryDateTime` datetime DEFAULT NULL,
  `plainCode` varchar(32) DEFAULT '',
  `queryCount` int(11) DEFAULT NULL,
  `queryWayStatus` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `box_code_id` bigint(20) DEFAULT NULL,
  `box_spec_id` bigint(20) DEFAULT NULL,
  `code_issue_id` bigint(20) DEFAULT NULL,
  `queryWayStatusCode` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8446D231CD02F25` (`code_issue_id`),
  KEY `FK8446D233D9C81D3` (`box_spec_id`),
  KEY `FK8446D23C12039F7` (`box_code_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table taoists_fake_code
#
LOCK TABLES `taoists_fake_code` WRITE;
/*!40000 ALTER TABLE `taoists_fake_code` DISABLE KEYS */;

INSERT INTO `taoists_fake_code` (`id`,`createDateTime`,`lastModifyDateTime`,`fakeCode`,`firstQueryDateTime`,`plainCode`,`queryCount`,`queryWayStatus`,`status`,`box_code_id`,`box_spec_id`,`code_issue_id`,`queryWayStatusCode`) VALUES (1,'2012-06-30 23:20:39','2012-07-04 20:12:40','947098234535','2012-07-04 20:12:40','606003739085',1,0,1,1,1,2,NULL);
INSERT INTO `taoists_fake_code` (`id`,`createDateTime`,`lastModifyDateTime`,`fakeCode`,`firstQueryDateTime`,`plainCode`,`queryCount`,`queryWayStatus`,`status`,`box_code_id`,`box_spec_id`,`code_issue_id`,`queryWayStatusCode`) VALUES (2,'2012-06-30 23:20:39','2012-06-30 23:21:24','586138224374',NULL,'659070076969',NULL,NULL,NULL,1,1,2,NULL);
INSERT INTO `taoists_fake_code` (`id`,`createDateTime`,`lastModifyDateTime`,`fakeCode`,`firstQueryDateTime`,`plainCode`,`queryCount`,`queryWayStatus`,`status`,`box_code_id`,`box_spec_id`,`code_issue_id`,`queryWayStatusCode`) VALUES (3,'2012-06-30 23:20:39','2012-06-30 23:21:24','473426687794',NULL,'950310869685',NULL,NULL,NULL,1,1,2,NULL);
INSERT INTO `taoists_fake_code` (`id`,`createDateTime`,`lastModifyDateTime`,`fakeCode`,`firstQueryDateTime`,`plainCode`,`queryCount`,`queryWayStatus`,`status`,`box_code_id`,`box_spec_id`,`code_issue_id`,`queryWayStatusCode`) VALUES (4,'2012-06-30 23:20:39','2012-06-30 23:20:39','055566161927',NULL,'380658602100',NULL,NULL,NULL,NULL,1,2,NULL);
INSERT INTO `taoists_fake_code` (`id`,`createDateTime`,`lastModifyDateTime`,`fakeCode`,`firstQueryDateTime`,`plainCode`,`queryCount`,`queryWayStatus`,`status`,`box_code_id`,`box_spec_id`,`code_issue_id`,`queryWayStatusCode`) VALUES (5,'2012-06-30 23:20:39','2012-07-01 10:18:06','062708104074',NULL,'047331846164',NULL,NULL,NULL,2,1,2,NULL);
INSERT INTO `taoists_fake_code` (`id`,`createDateTime`,`lastModifyDateTime`,`fakeCode`,`firstQueryDateTime`,`plainCode`,`queryCount`,`queryWayStatus`,`status`,`box_code_id`,`box_spec_id`,`code_issue_id`,`queryWayStatusCode`) VALUES (6,'2012-07-02 20:05:38','2012-07-02 20:05:38','784177690575',NULL,'642811889424',NULL,NULL,NULL,NULL,3,4,NULL);
INSERT INTO `taoists_fake_code` (`id`,`createDateTime`,`lastModifyDateTime`,`fakeCode`,`firstQueryDateTime`,`plainCode`,`queryCount`,`queryWayStatus`,`status`,`box_code_id`,`box_spec_id`,`code_issue_id`,`queryWayStatusCode`) VALUES (7,'2012-07-02 20:05:38','2012-07-02 20:05:38','944973354469',NULL,'021109004623',NULL,NULL,NULL,NULL,3,4,NULL);
INSERT INTO `taoists_fake_code` (`id`,`createDateTime`,`lastModifyDateTime`,`fakeCode`,`firstQueryDateTime`,`plainCode`,`queryCount`,`queryWayStatus`,`status`,`box_code_id`,`box_spec_id`,`code_issue_id`,`queryWayStatusCode`) VALUES (8,'2012-07-02 20:05:38','2012-07-02 20:05:38','411459258009',NULL,'053918153626',NULL,NULL,NULL,NULL,3,4,NULL);
INSERT INTO `taoists_fake_code` (`id`,`createDateTime`,`lastModifyDateTime`,`fakeCode`,`firstQueryDateTime`,`plainCode`,`queryCount`,`queryWayStatus`,`status`,`box_code_id`,`box_spec_id`,`code_issue_id`,`queryWayStatusCode`) VALUES (9,'2012-07-02 20:05:38','2012-07-02 20:05:38','747690493211',NULL,'249830906511',NULL,NULL,NULL,NULL,3,4,NULL);
INSERT INTO `taoists_fake_code` (`id`,`createDateTime`,`lastModifyDateTime`,`fakeCode`,`firstQueryDateTime`,`plainCode`,`queryCount`,`queryWayStatus`,`status`,`box_code_id`,`box_spec_id`,`code_issue_id`,`queryWayStatusCode`) VALUES (10,'2012-07-02 20:05:38','2012-07-02 20:05:38','087062281387',NULL,'478847311121',NULL,NULL,NULL,NULL,3,4,NULL);
INSERT INTO `taoists_fake_code` (`id`,`createDateTime`,`lastModifyDateTime`,`fakeCode`,`firstQueryDateTime`,`plainCode`,`queryCount`,`queryWayStatus`,`status`,`box_code_id`,`box_spec_id`,`code_issue_id`,`queryWayStatusCode`) VALUES (11,'2012-07-02 20:05:38','2012-07-02 20:05:38','042061985813',NULL,'093903876780',NULL,NULL,NULL,NULL,3,4,NULL);
/*!40000 ALTER TABLE `taoists_fake_code` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table taoists_menu
#

CREATE TABLE `taoists_menu` (
  `id` bigint(20) NOT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `lastModifyDateTime` datetime DEFAULT NULL,
  `expanded` tinyint(1) DEFAULT NULL,
  `leaf` tinyint(1) DEFAULT NULL,
  `width` int(11) DEFAULT '0',
  `action` varchar(32) DEFAULT '',
  `icon` varchar(32) DEFAULT '',
  `label` varchar(32) DEFAULT '',
  `name` varchar(32) DEFAULT '',
  `orderValue` int(11) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKF355D533CA2AA49` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table taoists_menu
#
LOCK TABLES `taoists_menu` WRITE;
/*!40000 ALTER TABLE `taoists_menu` DISABLE KEYS */;

INSERT INTO `taoists_menu` (`id`,`createDateTime`,`lastModifyDateTime`,`expanded`,`leaf`,`width`,`action`,`icon`,`label`,`name`,`orderValue`,`parent_id`) VALUES (1,'2012-06-16 19:15:07','2012-06-16 19:30:06',1,0,NULL,'',NULL,'信息管理',NULL,1,NULL);
INSERT INTO `taoists_menu` (`id`,`createDateTime`,`lastModifyDateTime`,`expanded`,`leaf`,`width`,`action`,`icon`,`label`,`name`,`orderValue`,`parent_id`) VALUES (2,'2012-06-16 19:16:15','2012-06-16 19:16:15',0,1,NULL,'user/edit-new',NULL,'密码修改',NULL,10,1);
INSERT INTO `taoists_menu` (`id`,`createDateTime`,`lastModifyDateTime`,`expanded`,`leaf`,`width`,`action`,`icon`,`label`,`name`,`orderValue`,`parent_id`) VALUES (3,'2012-06-16 19:17:19','2012-06-16 19:30:16',1,0,NULL,'',NULL,'仓库管理',NULL,5,NULL);
INSERT INTO `taoists_menu` (`id`,`createDateTime`,`lastModifyDateTime`,`expanded`,`leaf`,`width`,`action`,`icon`,`label`,`name`,`orderValue`,`parent_id`) VALUES (4,'2012-06-16 19:18:17','2012-06-16 19:18:17',0,1,NULL,'warehousing/edit-new',NULL,'箱码贴箱入库',NULL,10,3);
INSERT INTO `taoists_menu` (`id`,`createDateTime`,`lastModifyDateTime`,`expanded`,`leaf`,`width`,`action`,`icon`,`label`,`name`,`orderValue`,`parent_id`) VALUES (5,'2012-06-16 19:18:37','2012-06-16 19:18:37',0,1,NULL,'warehousing-box',NULL,'在库箱码列表',NULL,10,3);
INSERT INTO `taoists_menu` (`id`,`createDateTime`,`lastModifyDateTime`,`expanded`,`leaf`,`width`,`action`,`icon`,`label`,`name`,`orderValue`,`parent_id`) VALUES (6,'2012-06-16 19:19:25','2012-06-16 19:30:22',1,0,NULL,'',NULL,'渠道业务',NULL,10,NULL);
INSERT INTO `taoists_menu` (`id`,`createDateTime`,`lastModifyDateTime`,`expanded`,`leaf`,`width`,`action`,`icon`,`label`,`name`,`orderValue`,`parent_id`) VALUES (7,'2012-06-16 19:19:54','2012-06-16 19:19:54',0,1,NULL,'delivery/edit-new',NULL,'经销商供货',NULL,10,6);
INSERT INTO `taoists_menu` (`id`,`createDateTime`,`lastModifyDateTime`,`expanded`,`leaf`,`width`,`action`,`icon`,`label`,`name`,`orderValue`,`parent_id`) VALUES (8,'2012-06-16 19:20:14','2012-06-16 19:20:14',0,1,NULL,'delivery',NULL,'发货单列表',NULL,10,6);
INSERT INTO `taoists_menu` (`id`,`createDateTime`,`lastModifyDateTime`,`expanded`,`leaf`,`width`,`action`,`icon`,`label`,`name`,`orderValue`,`parent_id`) VALUES (9,'2012-06-16 19:20:30','2012-06-16 19:20:30',0,1,NULL,'purchase',NULL,'采购单列表',NULL,10,6);
INSERT INTO `taoists_menu` (`id`,`createDateTime`,`lastModifyDateTime`,`expanded`,`leaf`,`width`,`action`,`icon`,`label`,`name`,`orderValue`,`parent_id`) VALUES (10,'2012-06-16 19:20:52','2012-06-16 19:30:29',1,0,NULL,'',NULL,'码管理',NULL,15,NULL);
INSERT INTO `taoists_menu` (`id`,`createDateTime`,`lastModifyDateTime`,`expanded`,`leaf`,`width`,`action`,`icon`,`label`,`name`,`orderValue`,`parent_id`) VALUES (11,'2012-06-16 19:21:28','2012-06-30 17:48:55',0,1,NULL,'code-issue/box-code-gen',NULL,'生成箱码',NULL,1,10);
INSERT INTO `taoists_menu` (`id`,`createDateTime`,`lastModifyDateTime`,`expanded`,`leaf`,`width`,`action`,`icon`,`label`,`name`,`orderValue`,`parent_id`) VALUES (13,'2012-06-16 19:22:36','2012-06-16 19:22:36',0,1,NULL,'box-code',NULL,'箱码列表',NULL,20,10);
INSERT INTO `taoists_menu` (`id`,`createDateTime`,`lastModifyDateTime`,`expanded`,`leaf`,`width`,`action`,`icon`,`label`,`name`,`orderValue`,`parent_id`) VALUES (14,'2012-06-16 19:22:50','2012-06-16 19:22:50',0,1,NULL,'fake-code',NULL,'防伪码列表',NULL,25,10);
INSERT INTO `taoists_menu` (`id`,`createDateTime`,`lastModifyDateTime`,`expanded`,`leaf`,`width`,`action`,`icon`,`label`,`name`,`orderValue`,`parent_id`) VALUES (15,'2012-06-16 19:23:14','2012-06-16 19:23:14',0,1,NULL,'box-code/to-bind',NULL,'箱码明码绑定',NULL,30,10);
INSERT INTO `taoists_menu` (`id`,`createDateTime`,`lastModifyDateTime`,`expanded`,`leaf`,`width`,`action`,`icon`,`label`,`name`,`orderValue`,`parent_id`) VALUES (16,'2012-06-16 19:23:52','2012-06-16 19:30:35',1,0,NULL,'',NULL,'基础设置',NULL,25,NULL);
INSERT INTO `taoists_menu` (`id`,`createDateTime`,`lastModifyDateTime`,`expanded`,`leaf`,`width`,`action`,`icon`,`label`,`name`,`orderValue`,`parent_id`) VALUES (17,'2012-06-16 19:25:57','2012-06-16 19:25:57',0,1,NULL,'company',NULL,'经销商管理',NULL,1,16);
INSERT INTO `taoists_menu` (`id`,`createDateTime`,`lastModifyDateTime`,`expanded`,`leaf`,`width`,`action`,`icon`,`label`,`name`,`orderValue`,`parent_id`) VALUES (18,'2012-06-16 19:26:28','2012-06-16 19:26:28',0,1,NULL,'brand',NULL,'品牌管理',NULL,10,16);
INSERT INTO `taoists_menu` (`id`,`createDateTime`,`lastModifyDateTime`,`expanded`,`leaf`,`width`,`action`,`icon`,`label`,`name`,`orderValue`,`parent_id`) VALUES (19,'2012-06-16 19:26:44','2012-06-16 19:26:44',0,1,NULL,'product',NULL,'产品管理',NULL,10,16);
INSERT INTO `taoists_menu` (`id`,`createDateTime`,`lastModifyDateTime`,`expanded`,`leaf`,`width`,`action`,`icon`,`label`,`name`,`orderValue`,`parent_id`) VALUES (21,'2012-06-16 19:28:03','2012-06-16 19:28:03',0,1,NULL,'data-dict/category/saleRegion',NULL,'销售区域设置',NULL,10,16);
INSERT INTO `taoists_menu` (`id`,`createDateTime`,`lastModifyDateTime`,`expanded`,`leaf`,`width`,`action`,`icon`,`label`,`name`,`orderValue`,`parent_id`) VALUES (22,'2012-06-16 19:28:36','2012-06-30 01:19:22',0,1,NULL,'box-spec',NULL,'包装箱规格管理',NULL,10,16);
INSERT INTO `taoists_menu` (`id`,`createDateTime`,`lastModifyDateTime`,`expanded`,`leaf`,`width`,`action`,`icon`,`label`,`name`,`orderValue`,`parent_id`) VALUES (23,'2012-06-30 17:03:28','2012-06-30 17:49:24',0,1,NULL,'code-issue/fake-code-gen',NULL,'生成防伪码',NULL,10,10);
INSERT INTO `taoists_menu` (`id`,`createDateTime`,`lastModifyDateTime`,`expanded`,`leaf`,`width`,`action`,`icon`,`label`,`name`,`orderValue`,`parent_id`) VALUES (24,'2012-06-30 17:05:38','2012-06-30 20:23:35',0,1,NULL,'code-issue/box-code-gen-list',NULL,'箱码生成记录',NULL,5,10);
INSERT INTO `taoists_menu` (`id`,`createDateTime`,`lastModifyDateTime`,`expanded`,`leaf`,`width`,`action`,`icon`,`label`,`name`,`orderValue`,`parent_id`) VALUES (25,'2012-06-30 17:05:51','2012-06-30 20:23:25',0,1,NULL,'code-issue/fake-code-gen-list',NULL,'防伪码生成记录',NULL,15,10);
/*!40000 ALTER TABLE `taoists_menu` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table taoists_product
#

CREATE TABLE `taoists_product` (
  `id` bigint(20) NOT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `lastModifyDateTime` datetime DEFAULT NULL,
  `barCode` varchar(32) DEFAULT '',
  `marketPrice` decimal(19,2) DEFAULT NULL,
  `memo` varchar(255) DEFAULT '',
  `name` varchar(32) DEFAULT '',
  `origin` varchar(32) DEFAULT '',
  `productDesc` varchar(255) DEFAULT '',
  `productNo` varchar(32) DEFAULT '',
  `productNoExt` varchar(32) DEFAULT '',
  `productSpell` varchar(32) DEFAULT '',
  `recordStatus` int(11) DEFAULT NULL,
  `retentioPeriod` int(11) DEFAULT NULL,
  `spec` varchar(32) DEFAULT '',
  `status` tinyint(1) DEFAULT NULL,
  `unit` varchar(16) DEFAULT '',
  `brand_id` bigint(20) DEFAULT NULL,
  `productCategory_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8801107BF3C21E6B` (`productCategory_id`),
  KEY `FK8801107BC70338DC` (`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table taoists_product
#
LOCK TABLES `taoists_product` WRITE;
/*!40000 ALTER TABLE `taoists_product` DISABLE KEYS */;

INSERT INTO `taoists_product` (`id`,`createDateTime`,`lastModifyDateTime`,`barCode`,`marketPrice`,`memo`,`name`,`origin`,`productDesc`,`productNo`,`productNoExt`,`productSpell`,`recordStatus`,`retentioPeriod`,`spec`,`status`,`unit`,`brand_id`,`productCategory_id`) VALUES (1,'2012-06-25 20:04:26','2012-06-30 00:17:32','00000001',1000,'','维亭',NULL,'','000001',NULL,NULL,NULL,10000,'',0,NULL,1,16);
INSERT INTO `taoists_product` (`id`,`createDateTime`,`lastModifyDateTime`,`barCode`,`marketPrice`,`memo`,`name`,`origin`,`productDesc`,`productNo`,`productNoExt`,`productSpell`,`recordStatus`,`retentioPeriod`,`spec`,`status`,`unit`,`brand_id`,`productCategory_id`) VALUES (2,'2012-06-25 20:05:13','2012-06-25 20:05:13','0000002',2000,'','安美莱',NULL,'安美莱','00000002',NULL,NULL,NULL,99,'',1,NULL,2,17);
INSERT INTO `taoists_product` (`id`,`createDateTime`,`lastModifyDateTime`,`barCode`,`marketPrice`,`memo`,`name`,`origin`,`productDesc`,`productNo`,`productNoExt`,`productSpell`,`recordStatus`,`retentioPeriod`,`spec`,`status`,`unit`,`brand_id`,`productCategory_id`) VALUES (3,'2012-06-25 20:05:35','2012-06-25 20:05:35','00000003',5555,'','佰利莱',NULL,'','000000003',NULL,NULL,NULL,555,'',1,NULL,3,17);
/*!40000 ALTER TABLE `taoists_product` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table taoists_product_inventory
#

CREATE TABLE `taoists_product_inventory` (
  `id` bigint(20) NOT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `lastModifyDateTime` datetime DEFAULT NULL,
  `availableQty` int(11) DEFAULT NULL,
  `checkInterval` int(11) DEFAULT NULL,
  `lastCheckDateTime` datetime DEFAULT NULL,
  `maxQty` int(11) DEFAULT NULL,
  `productId` int(11) DEFAULT NULL,
  `safeQty` int(11) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3EA00698E89ACB0F` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table taoists_product_inventory
#
LOCK TABLES `taoists_product_inventory` WRITE;
/*!40000 ALTER TABLE `taoists_product_inventory` DISABLE KEYS */;

/*!40000 ALTER TABLE `taoists_product_inventory` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table taoists_purchase
#

CREATE TABLE `taoists_purchase` (
  `id` bigint(20) NOT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `lastModifyDateTime` datetime DEFAULT NULL,
  `arrivalDateTime` datetime DEFAULT NULL,
  `arrivalId` bigint(20) DEFAULT NULL,
  `arrivalMemo` varchar(255) DEFAULT NULL,
  `arrivalName` varchar(255) DEFAULT NULL,
  `completeDateTime` datetime DEFAULT NULL,
  `completer` varchar(255) DEFAULT NULL,
  `completerId` bigint(20) DEFAULT NULL,
  `creater` varchar(255) DEFAULT NULL,
  `createrId` bigint(20) DEFAULT NULL,
  `deliveryDateTime` datetime DEFAULT NULL,
  `deliveryId` bigint(20) DEFAULT NULL,
  `deliveryMemo` varchar(255) DEFAULT '',
  `deliveryName` varchar(16) DEFAULT '',
  `discountAmount` decimal(19,2) DEFAULT NULL,
  `hasInvoice` int(11) DEFAULT NULL,
  `memo` varchar(255) DEFAULT '',
  `purchaseNo` varchar(32) DEFAULT '',
  `purchaseType` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `statusCode` int(11) DEFAULT NULL,
  `totalAmount` decimal(19,2) DEFAULT NULL,
  `purchase_company_id` bigint(20) DEFAULT NULL,
  `supplier_company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1BDE32F565DCA10D` (`purchase_company_id`),
  KEY `FK1BDE32F571274F62` (`supplier_company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table taoists_purchase
#
LOCK TABLES `taoists_purchase` WRITE;
/*!40000 ALTER TABLE `taoists_purchase` DISABLE KEYS */;

INSERT INTO `taoists_purchase` (`id`,`createDateTime`,`lastModifyDateTime`,`arrivalDateTime`,`arrivalId`,`arrivalMemo`,`arrivalName`,`completeDateTime`,`completer`,`completerId`,`creater`,`createrId`,`deliveryDateTime`,`deliveryId`,`deliveryMemo`,`deliveryName`,`discountAmount`,`hasInvoice`,`memo`,`purchaseNo`,`purchaseType`,`status`,`statusCode`,`totalAmount`,`purchase_company_id`,`supplier_company_id`) VALUES (1,'2012-06-30 23:29:20','2012-06-30 23:30:20','2012-06-30 23:30:20',1,'receive','rubys',NULL,NULL,NULL,'admin',1,'2012-06-30 23:29:50',1,'delivery','admin',NULL,NULL,'00001120630000001',NULL,NULL,3,4,NULL,2,1);
/*!40000 ALTER TABLE `taoists_purchase` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table taoists_purchase_box
#

CREATE TABLE `taoists_purchase_box` (
  `id` bigint(20) NOT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `lastModifyDateTime` datetime DEFAULT NULL,
  `boxCode_id` bigint(20) DEFAULT NULL,
  `purchase_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCB1E5D81486E8840` (`boxCode_id`),
  KEY `FKCB1E5D81A2B808C2` (`purchase_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table taoists_purchase_box
#
LOCK TABLES `taoists_purchase_box` WRITE;
/*!40000 ALTER TABLE `taoists_purchase_box` DISABLE KEYS */;

INSERT INTO `taoists_purchase_box` (`id`,`createDateTime`,`lastModifyDateTime`,`boxCode_id`,`purchase_id`) VALUES (1,'2012-06-30 23:29:20','2012-06-30 23:29:20',1,1);
/*!40000 ALTER TABLE `taoists_purchase_box` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table taoists_purchase_item
#

CREATE TABLE `taoists_purchase_item` (
  `id` bigint(20) NOT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `lastModifyDateTime` datetime DEFAULT NULL,
  `actualQty` int(11) DEFAULT NULL,
  `memo` varchar(255) DEFAULT '',
  `price` decimal(19,2) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `subAmount` decimal(19,2) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `purchase_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK98B0921DA2B808C2` (`purchase_id`),
  KEY `FK98B0921DF06C75C` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table taoists_purchase_item
#
LOCK TABLES `taoists_purchase_item` WRITE;
/*!40000 ALTER TABLE `taoists_purchase_item` DISABLE KEYS */;

INSERT INTO `taoists_purchase_item` (`id`,`createDateTime`,`lastModifyDateTime`,`actualQty`,`memo`,`price`,`qty`,`subAmount`,`product_id`,`purchase_id`) VALUES (1,'2012-06-30 23:29:20','2012-06-30 23:29:20',30,NULL,NULL,NULL,NULL,1,1);
/*!40000 ALTER TABLE `taoists_purchase_item` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table taoists_stock
#

CREATE TABLE `taoists_stock` (
  `id` bigint(20) NOT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `lastModifyDateTime` datetime DEFAULT NULL,
  `billNo` varchar(32) DEFAULT '',
  `changeType` int(11) DEFAULT NULL,
  `inAmount` decimal(19,2) DEFAULT NULL,
  `inCount` int(11) DEFAULT NULL,
  `operateType` int(11) DEFAULT NULL,
  `outAmount` decimal(19,2) DEFAULT NULL,
  `outCount` int(11) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD7D1ADA2E89ACB0F` (`company_id`),
  KEY `FKD7D1ADA2F06C75C` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table taoists_stock
#
LOCK TABLES `taoists_stock` WRITE;
/*!40000 ALTER TABLE `taoists_stock` DISABLE KEYS */;

INSERT INTO `taoists_stock` (`id`,`createDateTime`,`lastModifyDateTime`,`billNo`,`changeType`,`inAmount`,`inCount`,`operateType`,`outAmount`,`outCount`,`company_id`,`product_id`) VALUES (1,'2012-06-30 23:27:49','2012-06-30 23:27:49',NULL,0,1000,30,NULL,NULL,NULL,1,1);
INSERT INTO `taoists_stock` (`id`,`createDateTime`,`lastModifyDateTime`,`billNo`,`changeType`,`inAmount`,`inCount`,`operateType`,`outAmount`,`outCount`,`company_id`,`product_id`) VALUES (2,'2012-06-30 23:29:50','2012-06-30 23:29:50',NULL,1,NULL,NULL,NULL,1000,30,1,1);
INSERT INTO `taoists_stock` (`id`,`createDateTime`,`lastModifyDateTime`,`billNo`,`changeType`,`inAmount`,`inCount`,`operateType`,`outAmount`,`outCount`,`company_id`,`product_id`) VALUES (3,'2012-06-30 23:30:20','2012-06-30 23:30:20',NULL,0,1000,30,NULL,NULL,NULL,2,1);
/*!40000 ALTER TABLE `taoists_stock` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table taoists_warehousing
#

CREATE TABLE `taoists_warehousing` (
  `id` bigint(20) NOT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `lastModifyDateTime` datetime DEFAULT NULL,
  `memo` varchar(255) DEFAULT '',
  `warehousingDateTime` datetime DEFAULT NULL,
  `warehousingNo` varchar(32) DEFAULT '',
  `company_id` bigint(20) DEFAULT NULL,
  `operator_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKEADA504CE89ACB0F` (`company_id`),
  KEY `FKEADA504C6B8CCE69` (`operator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table taoists_warehousing
#
LOCK TABLES `taoists_warehousing` WRITE;
/*!40000 ALTER TABLE `taoists_warehousing` DISABLE KEYS */;

INSERT INTO `taoists_warehousing` (`id`,`createDateTime`,`lastModifyDateTime`,`memo`,`warehousingDateTime`,`warehousingNo`,`company_id`,`operator_id`) VALUES (1,'2012-06-30 23:27:49','2012-06-30 23:27:49',NULL,NULL,NULL,1,NULL);
INSERT INTO `taoists_warehousing` (`id`,`createDateTime`,`lastModifyDateTime`,`memo`,`warehousingDateTime`,`warehousingNo`,`company_id`,`operator_id`) VALUES (2,'2012-06-30 23:30:20','2012-06-30 23:30:20',NULL,'2012-06-30 23:30:20',NULL,2,1);
/*!40000 ALTER TABLE `taoists_warehousing` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table taoists_warehousing_box
#

CREATE TABLE `taoists_warehousing_box` (
  `id` bigint(20) NOT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `lastModifyDateTime` datetime DEFAULT NULL,
  `boxCode_id` bigint(20) DEFAULT NULL,
  `warehousing_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK598DF758486E8840` (`boxCode_id`),
  KEY `FK598DF75840A05612` (`warehousing_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table taoists_warehousing_box
#
LOCK TABLES `taoists_warehousing_box` WRITE;
/*!40000 ALTER TABLE `taoists_warehousing_box` DISABLE KEYS */;

INSERT INTO `taoists_warehousing_box` (`id`,`createDateTime`,`lastModifyDateTime`,`boxCode_id`,`warehousing_id`) VALUES (1,'2012-06-30 23:27:49','2012-06-30 23:27:49',1,1);
INSERT INTO `taoists_warehousing_box` (`id`,`createDateTime`,`lastModifyDateTime`,`boxCode_id`,`warehousing_id`) VALUES (2,'2012-06-30 23:30:20','2012-06-30 23:30:20',1,2);
/*!40000 ALTER TABLE `taoists_warehousing_box` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table taoists_warehousing_item
#

CREATE TABLE `taoists_warehousing_item` (
  `id` bigint(20) NOT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `lastModifyDateTime` datetime DEFAULT NULL,
  `memo` varchar(255) DEFAULT '',
  `price` decimal(19,2) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `subAmount` decimal(19,2) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `warehousing_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD834332640A05612` (`warehousing_id`),
  KEY `FKD8343326F06C75C` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table taoists_warehousing_item
#
LOCK TABLES `taoists_warehousing_item` WRITE;
/*!40000 ALTER TABLE `taoists_warehousing_item` DISABLE KEYS */;

INSERT INTO `taoists_warehousing_item` (`id`,`createDateTime`,`lastModifyDateTime`,`memo`,`price`,`qty`,`subAmount`,`product_id`,`warehousing_id`) VALUES (1,'2012-06-30 23:27:49','2012-06-30 23:27:49',NULL,1000,1,1000,1,1);
INSERT INTO `taoists_warehousing_item` (`id`,`createDateTime`,`lastModifyDateTime`,`memo`,`price`,`qty`,`subAmount`,`product_id`,`warehousing_id`) VALUES (2,'2012-06-30 23:30:20','2012-06-30 23:30:20',NULL,1000,1,1000,1,2);
/*!40000 ALTER TABLE `taoists_warehousing_item` ENABLE KEYS */;
UNLOCK TABLES;

#
#  Foreign keys for table taoists_box_code
#

ALTER TABLE `taoists_box_code`
ADD CONSTRAINT `FK3A2AFDD51CD02F25` FOREIGN KEY (`code_issue_id`) REFERENCES `taoists_code_issue` (`id`),
ADD CONSTRAINT `FK3A2AFDD53D9C81D3` FOREIGN KEY (`box_spec_id`) REFERENCES `taoists_box_spec` (`id`),
ADD CONSTRAINT `FK3A2AFDD56AC61353` FOREIGN KEY (`storage_company_id`) REFERENCES `taoists_company` (`id`),
ADD CONSTRAINT `FK3A2AFDD59B26006F` FOREIGN KEY (`creation_company_id`) REFERENCES `taoists_company` (`id`);

#
#  Foreign keys for table taoists_box_spec
#

ALTER TABLE `taoists_box_spec`
ADD CONSTRAINT `FK3A3247A3F06C75C` FOREIGN KEY (`product_id`) REFERENCES `taoists_product` (`id`);

#
#  Foreign keys for table taoists_box_trace
#

ALTER TABLE `taoists_box_trace`
ADD CONSTRAINT `FKC259E5DC12039F7` FOREIGN KEY (`box_code_id`) REFERENCES `taoists_box_code` (`id`),
ADD CONSTRAINT `FKC259E5DE89ACB0F` FOREIGN KEY (`company_id`) REFERENCES `taoists_company` (`id`);

#
#  Foreign keys for table taoists_code_issue
#

ALTER TABLE `taoists_code_issue`
ADD CONSTRAINT `FK685954FB3D9C81D3` FOREIGN KEY (`box_spec_id`) REFERENCES `taoists_box_spec` (`id`),
ADD CONSTRAINT `FK685954FB5FE26F2E` FOREIGN KEY (`creationCompany_id`) REFERENCES `taoists_company` (`id`),
ADD CONSTRAINT `FK685954FB6B8CCE69` FOREIGN KEY (`operator_id`) REFERENCES `taoists_account` (`id`);

#
#  Foreign keys for table taoists_code_query
#

ALTER TABLE `taoists_code_query`
ADD CONSTRAINT `FK68CAC4EA43B295F4` FOREIGN KEY (`fakeCode_id`) REFERENCES `taoists_fake_code` (`id`);

#
#  Foreign keys for table taoists_company
#

ALTER TABLE `taoists_company`
ADD CONSTRAINT `FKD31A80091634709D` FOREIGN KEY (`saleRegion_id`) REFERENCES `taoists_data_dict` (`id`),
ADD CONSTRAINT `FKD31A80092B3C67E1` FOREIGN KEY (`companyType_id`) REFERENCES `taoists_data_dict` (`id`),
ADD CONSTRAINT `FKD31A8009A40A0B0D` FOREIGN KEY (`saleForm_id`) REFERENCES `taoists_data_dict` (`id`),
ADD CONSTRAINT `FKD31A8009BAE73A94` FOREIGN KEY (`companyNature_id`) REFERENCES `taoists_data_dict` (`id`);

#
#  Foreign keys for table taoists_contact
#

ALTER TABLE `taoists_contact`
ADD CONSTRAINT `FKD32A67ACE89ACB0F` FOREIGN KEY (`company_id`) REFERENCES `taoists_company` (`id`);

#
#  Foreign keys for table taoists_data_dict
#

ALTER TABLE `taoists_data_dict`
ADD CONSTRAINT `FKB5009FB74C8C0B8E` FOREIGN KEY (`parent_id`) REFERENCES `taoists_data_dict` (`id`),
ADD CONSTRAINT `FKB5009FB78DAC440E` FOREIGN KEY (`category_id`) REFERENCES `taoists_dict_category` (`id`);

#
#  Foreign keys for table taoists_dept
#

ALTER TABLE `taoists_dept`
ADD CONSTRAINT `FKF314639E89ACB0F` FOREIGN KEY (`company_id`) REFERENCES `taoists_company` (`id`);

#
#  Foreign keys for table taoists_fake_code
#

ALTER TABLE `taoists_fake_code`
ADD CONSTRAINT `FK8446D231CD02F25` FOREIGN KEY (`code_issue_id`) REFERENCES `taoists_code_issue` (`id`),
ADD CONSTRAINT `FK8446D233D9C81D3` FOREIGN KEY (`box_spec_id`) REFERENCES `taoists_box_spec` (`id`),
ADD CONSTRAINT `FK8446D23C12039F7` FOREIGN KEY (`box_code_id`) REFERENCES `taoists_box_code` (`id`);

#
#  Foreign keys for table taoists_menu
#

ALTER TABLE `taoists_menu`
ADD CONSTRAINT `FKF355D533CA2AA49` FOREIGN KEY (`parent_id`) REFERENCES `taoists_menu` (`id`);

#
#  Foreign keys for table taoists_product
#

ALTER TABLE `taoists_product`
ADD CONSTRAINT `FK8801107BC70338DC` FOREIGN KEY (`brand_id`) REFERENCES `taoists_brand` (`id`),
ADD CONSTRAINT `FK8801107BF3C21E6B` FOREIGN KEY (`productCategory_id`) REFERENCES `taoists_data_dict` (`id`);

#
#  Foreign keys for table taoists_product_inventory
#

ALTER TABLE `taoists_product_inventory`
ADD CONSTRAINT `FK3EA00698E89ACB0F` FOREIGN KEY (`company_id`) REFERENCES `taoists_company` (`id`);

#
#  Foreign keys for table taoists_purchase
#

ALTER TABLE `taoists_purchase`
ADD CONSTRAINT `FK1BDE32F565DCA10D` FOREIGN KEY (`purchase_company_id`) REFERENCES `taoists_company` (`id`),
ADD CONSTRAINT `FK1BDE32F571274F62` FOREIGN KEY (`supplier_company_id`) REFERENCES `taoists_company` (`id`);

#
#  Foreign keys for table taoists_purchase_box
#

ALTER TABLE `taoists_purchase_box`
ADD CONSTRAINT `FKCB1E5D81486E8840` FOREIGN KEY (`boxCode_id`) REFERENCES `taoists_box_code` (`id`),
ADD CONSTRAINT `FKCB1E5D81A2B808C2` FOREIGN KEY (`purchase_id`) REFERENCES `taoists_purchase` (`id`);

#
#  Foreign keys for table taoists_purchase_item
#

ALTER TABLE `taoists_purchase_item`
ADD CONSTRAINT `FK98B0921DA2B808C2` FOREIGN KEY (`purchase_id`) REFERENCES `taoists_purchase` (`id`),
ADD CONSTRAINT `FK98B0921DF06C75C` FOREIGN KEY (`product_id`) REFERENCES `taoists_product` (`id`);

#
#  Foreign keys for table taoists_stock
#

ALTER TABLE `taoists_stock`
ADD CONSTRAINT `FKD7D1ADA2E89ACB0F` FOREIGN KEY (`company_id`) REFERENCES `taoists_company` (`id`),
ADD CONSTRAINT `FKD7D1ADA2F06C75C` FOREIGN KEY (`product_id`) REFERENCES `taoists_product` (`id`);

#
#  Foreign keys for table taoists_warehousing
#

ALTER TABLE `taoists_warehousing`
ADD CONSTRAINT `FKEADA504C6B8CCE69` FOREIGN KEY (`operator_id`) REFERENCES `taoists_account` (`id`),
ADD CONSTRAINT `FKEADA504CE89ACB0F` FOREIGN KEY (`company_id`) REFERENCES `taoists_company` (`id`);

#
#  Foreign keys for table taoists_warehousing_box
#

ALTER TABLE `taoists_warehousing_box`
ADD CONSTRAINT `FK598DF75840A05612` FOREIGN KEY (`warehousing_id`) REFERENCES `taoists_warehousing` (`id`),
ADD CONSTRAINT `FK598DF758486E8840` FOREIGN KEY (`boxCode_id`) REFERENCES `taoists_box_code` (`id`);

#
#  Foreign keys for table taoists_warehousing_item
#

ALTER TABLE `taoists_warehousing_item`
ADD CONSTRAINT `FKD834332640A05612` FOREIGN KEY (`warehousing_id`) REFERENCES `taoists_warehousing` (`id`),
ADD CONSTRAINT `FKD8343326F06C75C` FOREIGN KEY (`product_id`) REFERENCES `taoists_product` (`id`);


/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
