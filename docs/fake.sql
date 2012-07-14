/*
Navicat MySQL Data Transfer

Source Server         : ueye
Source Server Version : 50157
Source Host           : localhost:3306
Source Database       : fake

Target Server Type    : MYSQL
Target Server Version : 50157
File Encoding         : 65001

Date: 2012-07-09 22:05:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `taoists_account`
-- ----------------------------
DROP TABLE IF EXISTS `taoists_account`;
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

-- ----------------------------
-- Records of taoists_account
-- ----------------------------
INSERT INTO `taoists_account` VALUES ('1', null, null, null, '1', '', '', 'admin', '21232f297a57a5a743894a0e4a801fc3', '', null, null, '', 'admin');
INSERT INTO `taoists_account` VALUES ('2', '2012-06-30 16:15:56', '2012-06-30 16:23:29', '0', '2', 'rubys@vip.qq.com', '', 'rubys', '21232f297a57a5a743894a0e4a801fc3', '', '0', '1', '0000002', 'rubys');

-- ----------------------------
-- Table structure for `taoists_box_code`
-- ----------------------------
DROP TABLE IF EXISTS `taoists_box_code`;
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
  KEY `FK3A2AFDD56AC61353` (`storage_company_id`),
  CONSTRAINT `FK3A2AFDD51CD02F25` FOREIGN KEY (`code_issue_id`) REFERENCES `taoists_code_issue` (`id`),
  CONSTRAINT `FK3A2AFDD53D9C81D3` FOREIGN KEY (`box_spec_id`) REFERENCES `taoists_box_spec` (`id`),
  CONSTRAINT `FK3A2AFDD56AC61353` FOREIGN KEY (`storage_company_id`) REFERENCES `taoists_company` (`id`),
  CONSTRAINT `FK3A2AFDD59B26006F` FOREIGN KEY (`creation_company_id`) REFERENCES `taoists_company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taoists_box_code
-- ----------------------------
INSERT INTO `taoists_box_code` VALUES ('1', '2012-06-30 23:14:11', '2012-06-30 23:30:20', '00001120630000001', '4', '2', '1', '1', '1', '2');
INSERT INTO `taoists_box_code` VALUES ('2', '2012-06-30 23:14:11', '2012-06-30 23:14:11', '00001120630000002', '3', '1', '1', '1', '1', '1');
INSERT INTO `taoists_box_code` VALUES ('3', '2012-07-02 19:50:58', '2012-07-02 19:50:58', '00002120702000001', '3', '1', '2', '3', '1', '1');
INSERT INTO `taoists_box_code` VALUES ('4', '2012-07-02 19:50:58', '2012-07-02 19:50:58', '00002120702000002', '3', '1', '2', '3', '1', '1');

-- ----------------------------
-- Table structure for `taoists_box_spec`
-- ----------------------------
DROP TABLE IF EXISTS `taoists_box_spec`;
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
  KEY `FK3A3247A3F06C75C` (`product_id`),
  CONSTRAINT `FK3A3247A3F06C75C` FOREIGN KEY (`product_id`) REFERENCES `taoists_product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taoists_box_spec
-- ----------------------------
INSERT INTO `taoists_box_spec` VALUES ('1', '2012-06-29 21:42:09', '2012-06-29 21:42:09', '30', null, '', 'VT', '00001', '1', '1');
INSERT INTO `taoists_box_spec` VALUES ('2', '2012-06-29 21:42:24', '2012-06-29 21:42:24', '50', null, '', 'AML', '00002', '1', '2');
INSERT INTO `taoists_box_spec` VALUES ('3', '2012-06-29 21:42:44', '2012-06-29 21:42:44', '20', null, '', 'PAR', '00003', '1', '3');

-- ----------------------------
-- Table structure for `taoists_box_trace`
-- ----------------------------
DROP TABLE IF EXISTS `taoists_box_trace`;
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
  KEY `FKC259E5DC12039F7` (`box_code_id`),
  CONSTRAINT `FKC259E5DC12039F7` FOREIGN KEY (`box_code_id`) REFERENCES `taoists_box_code` (`id`),
  CONSTRAINT `FKC259E5DE89ACB0F` FOREIGN KEY (`company_id`) REFERENCES `taoists_company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taoists_box_trace
-- ----------------------------
INSERT INTO `taoists_box_trace` VALUES ('1', '2012-06-30 23:27:49', '2012-06-30 23:27:49', null, '2012-06-30 23:27:49', '1', '1');
INSERT INTO `taoists_box_trace` VALUES ('2', '2012-06-30 23:29:50', '2012-06-30 23:29:50', null, '2012-06-30 23:29:50', '1', '1');
INSERT INTO `taoists_box_trace` VALUES ('3', '2012-06-30 23:30:20', '2012-06-30 23:30:20', null, '2012-06-30 23:30:20', '1', '2');

-- ----------------------------
-- Table structure for `taoists_brand`
-- ----------------------------
DROP TABLE IF EXISTS `taoists_brand`;
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

-- ----------------------------
-- Records of taoists_brand
-- ----------------------------
INSERT INTO `taoists_brand` VALUES ('1', '2012-06-16 19:45:40', '2012-06-29 23:54:00', 'VT', '维亭', 'vt', 'http://test.com', 'http://statics.amr01.com/images/logo.jpg', '1');
INSERT INTO `taoists_brand` VALUES ('2', '2012-06-16 19:46:13', '2012-06-29 23:54:06', 'aml', '安美莱', 'aml', 'http://test.com', 'http://statics.amr01.com/images/logo.jpg', '1');
INSERT INTO `taoists_brand` VALUES ('3', '2012-06-16 19:46:43', '2012-06-29 23:54:12', 'porai', '佰利莱', 'porai', 'http://test.com', 'http://statics.amr01.com/images/logo.jpg', '1');

-- ----------------------------
-- Table structure for `taoists_code_issue`
-- ----------------------------
DROP TABLE IF EXISTS `taoists_code_issue`;
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
  KEY `FK685954FB5FE26F2E` (`creationCompany_id`),
  CONSTRAINT `FK685954FB3D9C81D3` FOREIGN KEY (`box_spec_id`) REFERENCES `taoists_box_spec` (`id`),
  CONSTRAINT `FK685954FB5FE26F2E` FOREIGN KEY (`creationCompany_id`) REFERENCES `taoists_company` (`id`),
  CONSTRAINT `FK685954FB6B8CCE69` FOREIGN KEY (`operator_id`) REFERENCES `taoists_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taoists_code_issue
-- ----------------------------
INSERT INTO `taoists_code_issue` VALUES ('1', '2012-06-30 23:14:11', '2012-06-30 23:14:11', null, null, '1', '2', 'vt-box-code-gen', 'vt-box-code-gen', null, '1', '1', null);
INSERT INTO `taoists_code_issue` VALUES ('2', '2012-06-30 23:20:39', '2012-06-30 23:20:39', null, '12', '0', '5', 'vt-fake-code-gen', 'vt-fake-code-gen', null, '1', '1', null);
INSERT INTO `taoists_code_issue` VALUES ('3', '2012-07-02 19:50:58', '2012-07-02 19:50:58', null, null, '1', '2', 'box-code-gen-02', '', null, '2', '1', null);
INSERT INTO `taoists_code_issue` VALUES ('4', '2012-07-02 20:05:38', '2012-07-02 20:05:38', null, '12', '0', '6', 'gen-fake-code', 'gen-fake-code', null, '3', '1', null);
INSERT INTO `taoists_code_issue` VALUES ('5', '2012-07-09 21:57:16', '2012-07-09 21:57:16', null, '12', '0', '10', 'gen-fake-test-0709', '', null, '1', '1', null);
INSERT INTO `taoists_code_issue` VALUES ('6', '2012-07-09 21:59:01', '2012-07-09 21:59:01', null, '12', '0', '2', 'first', '', null, '2', '1', null);
INSERT INTO `taoists_code_issue` VALUES ('7', '2012-07-09 22:04:04', '2012-07-09 22:04:04', null, '12', '0', '100', 'gen-100', 'gen-100', null, '1', '1', null);

-- ----------------------------
-- Table structure for `taoists_code_query`
-- ----------------------------
DROP TABLE IF EXISTS `taoists_code_query`;
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
  KEY `FK68CAC4EA43B295F4` (`fakeCode_id`),
  CONSTRAINT `FK68CAC4EA43B295F4` FOREIGN KEY (`fakeCode_id`) REFERENCES `taoists_fake_code` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taoists_code_query
-- ----------------------------
INSERT INTO `taoists_code_query` VALUES ('1', '2012-07-04 20:09:32', '2012-07-04 20:09:32', '947098234535', null, '2012-07-04 20:09:32', '1', '1', '2', '18621512999', '1');
INSERT INTO `taoists_code_query` VALUES ('2', '2012-07-04 20:10:50', '2012-07-04 20:10:50', '947098234535', null, '2012-07-04 20:10:50', '1', '1', '2', '18621512999', '1');
INSERT INTO `taoists_code_query` VALUES ('3', '2012-07-04 20:11:32', '2012-07-04 20:11:32', '947098234535', null, '2012-07-04 20:11:32', '1', '1', '2', '18621512999', '1');
INSERT INTO `taoists_code_query` VALUES ('4', '2012-07-04 20:12:40', '2012-07-04 20:12:40', '947098234535', null, '2012-07-04 20:12:40', '0', '1', '2', '18621512999', '1');
INSERT INTO `taoists_code_query` VALUES ('5', '2012-07-04 20:14:25', '2012-07-04 20:14:25', '9470982345305', null, '2012-07-04 20:14:25', '-1', '1', '2', '18621512999', null);

-- ----------------------------
-- Table structure for `taoists_company`
-- ----------------------------
DROP TABLE IF EXISTS `taoists_company`;
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
  KEY `FKD31A8009A40A0B0D` (`saleForm_id`),
  CONSTRAINT `FKD31A80091634709D` FOREIGN KEY (`saleRegion_id`) REFERENCES `taoists_data_dict` (`id`),
  CONSTRAINT `FKD31A80092B3C67E1` FOREIGN KEY (`companyType_id`) REFERENCES `taoists_data_dict` (`id`),
  CONSTRAINT `FKD31A8009A40A0B0D` FOREIGN KEY (`saleForm_id`) REFERENCES `taoists_data_dict` (`id`),
  CONSTRAINT `FKD31A8009BAE73A94` FOREIGN KEY (`companyNature_id`) REFERENCES `taoists_data_dict` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taoists_company
-- ----------------------------
INSERT INTO `taoists_company` VALUES ('1', null, null, null, '', '', '', '', '', 'System', '', '', '', '', '', '', '', '', '', null, '', null, null, null, null);
INSERT INTO `taoists_company` VALUES ('2', '2012-06-30 10:39:15', '2012-06-30 11:01:56', '1', '', '', '', '', '100', '泰尔', '', '000001', '11', '100', '									', '11', '', '', '', '1', '', '7', '11', '14', '1');
INSERT INTO `taoists_company` VALUES ('3', '2012-06-30 10:40:55', '2012-06-30 11:01:44', '1', '', '', '', '', '', '惠美惠', '', '000002', '', '', '									', '', '', '', '', '0', '', '9', '11', '15', '3');

-- ----------------------------
-- Table structure for `taoists_contact`
-- ----------------------------
DROP TABLE IF EXISTS `taoists_contact`;
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
  KEY `FKD32A67ACE89ACB0F` (`company_id`),
  CONSTRAINT `FKD32A67ACE89ACB0F` FOREIGN KEY (`company_id`) REFERENCES `taoists_company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taoists_contact
-- ----------------------------
INSERT INTO `taoists_contact` VALUES ('1', '2012-06-30 10:39:15', '2012-06-30 10:39:15', 0xACED0005737200176F72672E6A6F64612E74696D652E4C6F63616C44617465FFFFF804D3E4EBB50200024A000C694C6F63616C4D696C6C69734C000B694368726F6E6F6C6F677974001A4C6F72672F6A6F64612F74696D652F4368726F6E6F6C6F67793B787000000137E323C000737200276F72672E6A6F64612E74696D652E6368726F6E6F2E49534F4368726F6E6F6C6F67792453747562A9C811667137502703000078707372001F6F72672E6A6F64612E74696D652E4461746554696D655A6F6E652453747562A62F019A7C321AE30300007870770500035554437878, '0', null, '', '', null, '', 'A', '', '', null, null, null, null, null, '2');
INSERT INTO `taoists_contact` VALUES ('2', '2012-06-30 10:39:15', '2012-06-30 10:39:15', 0xACED0005737200176F72672E6A6F64612E74696D652E4C6F63616C44617465FFFFF804D3E4EBB50200024A000C694C6F63616C4D696C6C69734C000B694368726F6E6F6C6F677974001A4C6F72672F6A6F64612F74696D652F4368726F6E6F6C6F67793B787000000137BF173C00737200276F72672E6A6F64612E74696D652E6368726F6E6F2E49534F4368726F6E6F6C6F67792453747562A9C811667137502703000078707372001F6F72672E6A6F64612E74696D652E4461746554696D655A6F6E652453747562A62F019A7C321AE30300007870770500035554437878, '2', null, '', '', null, '', 'B', '', '', null, null, null, null, null, '2');
INSERT INTO `taoists_contact` VALUES ('3', '2012-06-30 10:40:55', '2012-06-30 10:40:55', null, '0', null, '', '', null, '', 'C', '', '', null, null, null, null, null, '3');
INSERT INTO `taoists_contact` VALUES ('4', '2012-06-30 10:40:55', '2012-06-30 10:40:55', null, '2', null, '', '', null, '', 'D', '', '', null, null, null, null, null, '3');

-- ----------------------------
-- Table structure for `taoists_data_dict`
-- ----------------------------
DROP TABLE IF EXISTS `taoists_data_dict`;
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
  KEY `FKB5009FB74C8C0B8E` (`parent_id`),
  CONSTRAINT `FKB5009FB74C8C0B8E` FOREIGN KEY (`parent_id`) REFERENCES `taoists_data_dict` (`id`),
  CONSTRAINT `FKB5009FB78DAC440E` FOREIGN KEY (`category_id`) REFERENCES `taoists_dict_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taoists_data_dict
-- ----------------------------
INSERT INTO `taoists_data_dict` VALUES ('1', '2012-06-11 19:36:06', '2012-06-16 19:36:06', 'DB', '东北大区', '东北大区', '1', '1', null);
INSERT INTO `taoists_data_dict` VALUES ('2', '2012-06-16 19:36:26', '2012-06-16 19:36:26', 'HB', '华北大区', '华北大区', '1', '1', null);
INSERT INTO `taoists_data_dict` VALUES ('3', '2012-06-16 19:36:44', '2012-06-16 19:36:44', 'XB', '西北大区', '西北大区', '1', '1', null);
INSERT INTO `taoists_data_dict` VALUES ('4', '2012-06-16 19:37:02', '2012-06-16 19:37:02', 'HD', '华东大区', '华东大区', '1', '1', null);
INSERT INTO `taoists_data_dict` VALUES ('5', '2012-06-16 19:37:22', '2012-06-16 19:37:22', 'HN', '华南大区', '华南大区', '1', '1', null);
INSERT INTO `taoists_data_dict` VALUES ('6', '2012-06-16 19:37:40', '2012-06-16 19:37:40', 'XN', '西南大区', '西南大区', '1', '1', null);
INSERT INTO `taoists_data_dict` VALUES ('7', '2012-06-16 19:38:55', '2012-06-16 19:38:55', 'GQ', '国企', '国企', '1', '3', null);
INSERT INTO `taoists_data_dict` VALUES ('8', '2012-06-16 19:39:09', '2012-06-16 19:39:09', 'WQ', '外企', '外企', '1', '3', null);
INSERT INTO `taoists_data_dict` VALUES ('9', '2012-06-16 19:39:21', '2012-06-16 19:39:21', 'HZ', '合资', '合资', '1', '3', null);
INSERT INTO `taoists_data_dict` VALUES ('10', '2012-06-16 19:39:36', '2012-06-16 19:39:36', 'MY', '民营', '民营', '1', '3', null);
INSERT INTO `taoists_data_dict` VALUES ('11', '2012-06-16 19:41:42', '2012-06-16 19:41:42', 'distributor', '经销商', '经销商', '1', '2', null);
INSERT INTO `taoists_data_dict` VALUES ('12', '2012-06-16 19:42:06', '2012-06-16 19:42:06', 'potential-customer', '潜在客户', '潜在客户', '1', '2', null);
INSERT INTO `taoists_data_dict` VALUES ('13', '2012-06-16 19:42:20', '2012-06-16 19:42:20', 'terminal-customer', '终端客户', '终端客户', '1', '2', null);
INSERT INTO `taoists_data_dict` VALUES ('14', '2012-06-16 19:42:51', '2012-06-16 19:42:51', 'wholesale', '批发', '批发', '1', '4', null);
INSERT INTO `taoists_data_dict` VALUES ('15', '2012-06-16 19:43:08', '2012-06-16 19:43:08', 'retail', '零售', '零售', '1', '4', null);
INSERT INTO `taoists_data_dict` VALUES ('16', '2012-06-16 19:43:47', '2012-06-16 19:43:47', 'health-care', '保健', '保健', '1', '5', null);
INSERT INTO `taoists_data_dict` VALUES ('17', '2012-06-16 19:44:12', '2012-06-16 19:44:12', 'to-lose-weight', '减肥', '减肥', '1', '5', null);

-- ----------------------------
-- Table structure for `taoists_dept`
-- ----------------------------
DROP TABLE IF EXISTS `taoists_dept`;
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
  KEY `FKF314639E89ACB0F` (`company_id`),
  CONSTRAINT `FKF314639E89ACB0F` FOREIGN KEY (`company_id`) REFERENCES `taoists_company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taoists_dept
-- ----------------------------

-- ----------------------------
-- Table structure for `taoists_dict_category`
-- ----------------------------
DROP TABLE IF EXISTS `taoists_dict_category`;
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

-- ----------------------------
-- Records of taoists_dict_category
-- ----------------------------
INSERT INTO `taoists_dict_category` VALUES ('1', '2012-06-16 19:33:47', '2012-06-16 19:33:47', 'saleRegion', '销售区域', '销售区域', '1');
INSERT INTO `taoists_dict_category` VALUES ('2', '2012-06-16 19:34:11', '2012-06-16 19:34:11', 'companyType', '企业类型', '企业类型', '1');
INSERT INTO `taoists_dict_category` VALUES ('3', '2012-06-16 19:34:31', '2012-06-16 19:34:31', 'companyNature', '企业性质', '企业性质', '1');
INSERT INTO `taoists_dict_category` VALUES ('4', '2012-06-16 19:34:52', '2012-06-16 19:34:52', 'saleForm', '销售形式', '销售形式', '1');
INSERT INTO `taoists_dict_category` VALUES ('5', '2012-06-16 19:35:08', '2012-06-16 19:35:08', 'productCategory', '产品分类', '产品分类', '1');

-- ----------------------------
-- Table structure for `taoists_fake_code`
-- ----------------------------
DROP TABLE IF EXISTS `taoists_fake_code`;
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
  KEY `FK8446D23C12039F7` (`box_code_id`),
  CONSTRAINT `FK8446D231CD02F25` FOREIGN KEY (`code_issue_id`) REFERENCES `taoists_code_issue` (`id`),
  CONSTRAINT `FK8446D233D9C81D3` FOREIGN KEY (`box_spec_id`) REFERENCES `taoists_box_spec` (`id`),
  CONSTRAINT `FK8446D23C12039F7` FOREIGN KEY (`box_code_id`) REFERENCES `taoists_box_code` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taoists_fake_code
-- ----------------------------
INSERT INTO `taoists_fake_code` VALUES ('1', '2012-06-30 23:20:39', '2012-07-04 20:12:40', '947098234535', '2012-07-04 20:12:40', '606003739085', '1', '0', '1', '1', '1', '2', null);
INSERT INTO `taoists_fake_code` VALUES ('2', '2012-06-30 23:20:39', '2012-06-30 23:21:24', '586138224374', null, '659070076969', null, null, null, '1', '1', '2', null);
INSERT INTO `taoists_fake_code` VALUES ('3', '2012-06-30 23:20:39', '2012-06-30 23:21:24', '473426687794', null, '950310869685', null, null, null, '1', '1', '2', null);
INSERT INTO `taoists_fake_code` VALUES ('4', '2012-06-30 23:20:39', '2012-06-30 23:20:39', '055566161927', null, '380658602100', null, null, null, null, '1', '2', null);
INSERT INTO `taoists_fake_code` VALUES ('5', '2012-06-30 23:20:39', '2012-07-01 10:18:06', '062708104074', null, '047331846164', null, null, null, '2', '1', '2', null);
INSERT INTO `taoists_fake_code` VALUES ('6', '2012-07-02 20:05:38', '2012-07-02 20:05:38', '784177690575', null, '642811889424', null, null, null, null, '3', '4', null);
INSERT INTO `taoists_fake_code` VALUES ('7', '2012-07-02 20:05:38', '2012-07-02 20:05:38', '944973354469', null, '021109004623', null, null, null, null, '3', '4', null);
INSERT INTO `taoists_fake_code` VALUES ('8', '2012-07-02 20:05:38', '2012-07-02 20:05:38', '411459258009', null, '053918153626', null, null, null, null, '3', '4', null);
INSERT INTO `taoists_fake_code` VALUES ('9', '2012-07-02 20:05:38', '2012-07-02 20:05:38', '747690493211', null, '249830906511', null, null, null, null, '3', '4', null);
INSERT INTO `taoists_fake_code` VALUES ('10', '2012-07-02 20:05:38', '2012-07-02 20:05:38', '087062281387', null, '478847311121', null, null, null, null, '3', '4', null);
INSERT INTO `taoists_fake_code` VALUES ('11', '2012-07-02 20:05:38', '2012-07-02 20:05:38', '042061985813', null, '093903876780', null, null, null, null, '3', '4', null);
INSERT INTO `taoists_fake_code` VALUES ('12', '2012-07-09 21:57:16', '2012-07-09 21:57:16', '01620030115', null, '003100001', null, null, null, null, '1', '5', null);
INSERT INTO `taoists_fake_code` VALUES ('13', '2012-07-09 21:57:16', '2012-07-09 21:57:16', '05090031625', null, '003100002', null, null, null, null, '1', '5', null);
INSERT INTO `taoists_fake_code` VALUES ('14', '2012-07-09 21:57:16', '2012-07-09 21:57:16', '07060037484', null, '003100003', null, null, null, null, '1', '5', null);
INSERT INTO `taoists_fake_code` VALUES ('15', '2012-07-09 21:57:16', '2012-07-09 21:57:16', '03040036628', null, '003100004', null, null, null, null, '1', '5', null);
INSERT INTO `taoists_fake_code` VALUES ('16', '2012-07-09 21:57:16', '2012-07-09 21:57:16', '04860030215', null, '003100005', null, null, null, null, '1', '5', null);
INSERT INTO `taoists_fake_code` VALUES ('17', '2012-07-09 21:57:16', '2012-07-09 21:57:16', '02250036809', null, '003100006', null, null, null, null, '1', '5', null);
INSERT INTO `taoists_fake_code` VALUES ('18', '2012-07-09 21:57:16', '2012-07-09 21:57:16', '08840032666', null, '003100007', null, null, null, null, '1', '5', null);
INSERT INTO `taoists_fake_code` VALUES ('19', '2012-07-09 21:57:16', '2012-07-09 21:57:16', '09930037981', null, '003100008', null, null, null, null, '1', '5', null);
INSERT INTO `taoists_fake_code` VALUES ('20', '2012-07-09 21:57:16', '2012-07-09 21:57:16', '04120037984', null, '003100009', null, null, null, null, '1', '5', null);
INSERT INTO `taoists_fake_code` VALUES ('21', '2012-07-09 21:57:16', '2012-07-09 21:57:16', '03190039677', null, '003100010', null, null, null, null, '1', '5', null);
INSERT INTO `taoists_fake_code` VALUES ('22', '2012-07-09 21:59:01', '2012-07-09 21:59:01', '037520044963', null, '2004100001', null, null, null, null, '2', '6', null);
INSERT INTO `taoists_fake_code` VALUES ('23', '2012-07-09 21:59:01', '2012-07-09 21:59:01', '080620045926', null, '2004100002', null, null, null, null, '2', '6', null);
INSERT INTO `taoists_fake_code` VALUES ('24', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '019720053198', null, '2005100001', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('25', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '015220059698', null, '2005100002', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('26', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '017320056209', null, '2005100003', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('27', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '094720058138', null, '2005100004', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('28', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '065820054808', null, '2005100005', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('29', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '046520052548', null, '2005100006', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('30', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '022720050009', null, '2005100007', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('31', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '081020054973', null, '2005100008', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('32', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '041820054568', null, '2005100009', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('33', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '085920056973', null, '2005100010', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('34', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '069020055287', null, '2005100011', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('35', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '010420059118', null, '2005100012', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('36', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '077420053747', null, '2005100013', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('37', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '043520052612', null, '2005100014', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('38', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '039820057192', null, '2005100015', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('39', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '082920055130', null, '2005100016', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('40', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '061720050864', null, '2005100017', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('41', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '006920059446', null, '2005100018', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('42', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '069020057871', null, '2005100019', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('43', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '087620055164', null, '2005100020', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('44', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '012420052199', null, '2005100021', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('45', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '039420056668', null, '2005100022', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('46', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '059620053198', null, '2005100023', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('47', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '014220050646', null, '2005100024', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('48', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '047520058991', null, '2005100025', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('49', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '014120058987', null, '2005100026', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('50', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '028420058152', null, '2005100027', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('51', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '064220052151', null, '2005100028', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('52', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '014320052408', null, '2005100029', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('53', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '046820053748', null, '2005100030', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('54', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '053720055999', null, '2005100031', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('55', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '024320052055', null, '2005100032', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('56', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '031820053424', null, '2005100033', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('57', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '089620055763', null, '2005100034', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('58', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '060620051031', null, '2005100035', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('59', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '056720053651', null, '2005100036', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('60', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '004020056962', null, '2005100037', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('61', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '017820051328', null, '2005100038', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('62', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '092620056221', null, '2005100039', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('63', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '088220056423', null, '2005100040', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('64', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '035020052241', null, '2005100041', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('65', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '060120051239', null, '2005100042', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('66', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '015420055341', null, '2005100043', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('67', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '045920052824', null, '2005100044', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('68', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '084320057732', null, '2005100045', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('69', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '000220056300', null, '2005100046', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('70', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '025820051271', null, '2005100047', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('71', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '025820052635', null, '2005100048', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('72', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '012120052303', null, '2005100049', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('73', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '099620056487', null, '2005100050', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('74', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '027820053236', null, '2005100051', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('75', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '094820054765', null, '2005100052', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('76', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '002420052747', null, '2005100053', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('77', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '069620056703', null, '2005100054', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('78', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '073420056383', null, '2005100055', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('79', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '095520055470', null, '2005100056', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('80', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '086320051992', null, '2005100057', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('81', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '026220056104', null, '2005100058', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('82', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '023120055983', null, '2005100059', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('83', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '073420059589', null, '2005100060', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('84', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '045620052029', null, '2005100061', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('85', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '018320050127', null, '2005100062', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('86', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '092620051288', null, '2005100063', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('87', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '043220053055', null, '2005100064', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('88', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '047620054113', null, '2005100065', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('89', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '071020053398', null, '2005100066', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('90', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '077320054760', null, '2005100067', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('91', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '035220050768', null, '2005100068', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('92', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '065320058829', null, '2005100069', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('93', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '066520054907', null, '2005100070', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('94', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '076220053161', null, '2005100071', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('95', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '015220050550', null, '2005100072', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('96', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '063020057496', null, '2005100073', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('97', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '032120056493', null, '2005100074', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('98', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '080220053384', null, '2005100075', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('99', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '062820059621', null, '2005100076', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('100', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '004620057962', null, '2005100077', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('101', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '003720056796', null, '2005100078', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('102', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '006820051315', null, '2005100079', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('103', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '022020057879', null, '2005100080', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('104', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '098820050198', null, '2005100081', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('105', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '018320059463', null, '2005100082', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('106', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '092720053036', null, '2005100083', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('107', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '074720054579', null, '2005100084', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('108', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '082320058626', null, '2005100085', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('109', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '096720057720', null, '2005100086', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('110', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '014120051168', null, '2005100087', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('111', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '033620055594', null, '2005100088', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('112', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '066720054286', null, '2005100089', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('113', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '075120055579', null, '2005100090', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('114', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '005320055512', null, '2005100091', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('115', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '026420055776', null, '2005100092', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('116', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '003220050663', null, '2005100093', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('117', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '099620057739', null, '2005100094', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('118', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '074720055796', null, '2005100095', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('119', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '026520053516', null, '2005100096', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('120', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '081820054737', null, '2005100097', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('121', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '097820053030', null, '2005100098', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('122', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '036720058590', null, '2005100099', null, null, null, null, '1', '7', null);
INSERT INTO `taoists_fake_code` VALUES ('123', '2012-07-09 22:04:04', '2012-07-09 22:04:04', '030720052105', null, '2005100100', null, null, null, null, '1', '7', null);

-- ----------------------------
-- Table structure for `taoists_menu`
-- ----------------------------
DROP TABLE IF EXISTS `taoists_menu`;
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
  KEY `FKF355D533CA2AA49` (`parent_id`),
  CONSTRAINT `FKF355D533CA2AA49` FOREIGN KEY (`parent_id`) REFERENCES `taoists_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taoists_menu
-- ----------------------------
INSERT INTO `taoists_menu` VALUES ('1', '2012-06-16 19:15:07', '2012-06-16 19:30:06', '1', '0', null, '', null, '信息管理', null, '1', null);
INSERT INTO `taoists_menu` VALUES ('2', '2012-06-16 19:16:15', '2012-06-16 19:16:15', '0', '1', null, 'user/edit-new', null, '密码修改', null, '10', '1');
INSERT INTO `taoists_menu` VALUES ('3', '2012-06-16 19:17:19', '2012-06-16 19:30:16', '1', '0', null, '', null, '仓库管理', null, '5', null);
INSERT INTO `taoists_menu` VALUES ('4', '2012-06-16 19:18:17', '2012-06-16 19:18:17', '0', '1', null, 'warehousing/edit-new', null, '箱码贴箱入库', null, '10', '3');
INSERT INTO `taoists_menu` VALUES ('5', '2012-06-16 19:18:37', '2012-06-16 19:18:37', '0', '1', null, 'warehousing-box', null, '在库箱码列表', null, '10', '3');
INSERT INTO `taoists_menu` VALUES ('6', '2012-06-16 19:19:25', '2012-06-16 19:30:22', '1', '0', null, '', null, '渠道业务', null, '10', null);
INSERT INTO `taoists_menu` VALUES ('7', '2012-06-16 19:19:54', '2012-06-16 19:19:54', '0', '1', null, 'delivery/edit-new', null, '经销商供货', null, '10', '6');
INSERT INTO `taoists_menu` VALUES ('8', '2012-06-16 19:20:14', '2012-06-16 19:20:14', '0', '1', null, 'delivery', null, '发货单列表', null, '10', '6');
INSERT INTO `taoists_menu` VALUES ('9', '2012-06-16 19:20:30', '2012-06-16 19:20:30', '0', '1', null, 'purchase', null, '采购单列表', null, '10', '6');
INSERT INTO `taoists_menu` VALUES ('10', '2012-06-16 19:20:52', '2012-06-16 19:30:29', '1', '0', null, '', null, '码管理', null, '15', null);
INSERT INTO `taoists_menu` VALUES ('11', '2012-06-16 19:21:28', '2012-06-30 17:48:55', '0', '1', null, 'code-issue/box-code-gen', null, '生成箱码', null, '1', '10');
INSERT INTO `taoists_menu` VALUES ('13', '2012-06-16 19:22:36', '2012-06-16 19:22:36', '0', '1', null, 'box-code', null, '箱码列表', null, '20', '10');
INSERT INTO `taoists_menu` VALUES ('14', '2012-06-16 19:22:50', '2012-06-16 19:22:50', '0', '1', null, 'fake-code', null, '防伪码列表', null, '25', '10');
INSERT INTO `taoists_menu` VALUES ('15', '2012-06-16 19:23:14', '2012-06-16 19:23:14', '0', '1', null, 'box-code/to-bind', null, '箱码明码绑定', null, '30', '10');
INSERT INTO `taoists_menu` VALUES ('16', '2012-06-16 19:23:52', '2012-06-16 19:30:35', '1', '0', null, '', null, '基础设置', null, '25', null);
INSERT INTO `taoists_menu` VALUES ('17', '2012-06-16 19:25:57', '2012-06-16 19:25:57', '0', '1', null, 'company', null, '经销商管理', null, '1', '16');
INSERT INTO `taoists_menu` VALUES ('18', '2012-06-16 19:26:28', '2012-06-16 19:26:28', '0', '1', null, 'brand', null, '品牌管理', null, '10', '16');
INSERT INTO `taoists_menu` VALUES ('19', '2012-06-16 19:26:44', '2012-06-16 19:26:44', '0', '1', null, 'product', null, '产品管理', null, '10', '16');
INSERT INTO `taoists_menu` VALUES ('21', '2012-06-16 19:28:03', '2012-06-16 19:28:03', '0', '1', null, 'data-dict/category/saleRegion', null, '销售区域设置', null, '10', '16');
INSERT INTO `taoists_menu` VALUES ('22', '2012-06-16 19:28:36', '2012-06-30 01:19:22', '0', '1', null, 'box-spec', null, '包装箱规格管理', null, '10', '16');
INSERT INTO `taoists_menu` VALUES ('23', '2012-06-30 17:03:28', '2012-06-30 17:49:24', '0', '1', null, 'code-issue/fake-code-gen', null, '生成防伪码', null, '10', '10');
INSERT INTO `taoists_menu` VALUES ('24', '2012-06-30 17:05:38', '2012-06-30 20:23:35', '0', '1', null, 'code-issue/box-code-gen-list', null, '箱码生成记录', null, '5', '10');
INSERT INTO `taoists_menu` VALUES ('25', '2012-06-30 17:05:51', '2012-06-30 20:23:25', '0', '1', null, 'code-issue/fake-code-gen-list', null, '防伪码生成记录', null, '15', '10');

-- ----------------------------
-- Table structure for `taoists_product`
-- ----------------------------
DROP TABLE IF EXISTS `taoists_product`;
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
  KEY `FK8801107BC70338DC` (`brand_id`),
  CONSTRAINT `FK8801107BC70338DC` FOREIGN KEY (`brand_id`) REFERENCES `taoists_brand` (`id`),
  CONSTRAINT `FK8801107BF3C21E6B` FOREIGN KEY (`productCategory_id`) REFERENCES `taoists_data_dict` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taoists_product
-- ----------------------------
INSERT INTO `taoists_product` VALUES ('1', '2012-06-25 20:04:26', '2012-06-30 00:17:32', '00000001', '1000.00', '', '维亭', null, '', '000001', null, null, null, '10000', '', '0', null, '1', '16');
INSERT INTO `taoists_product` VALUES ('2', '2012-06-25 20:05:13', '2012-06-25 20:05:13', '0000002', '2000.00', '', '安美莱', null, '安美莱', '00000002', null, null, null, '99', '', '1', null, '2', '17');
INSERT INTO `taoists_product` VALUES ('3', '2012-06-25 20:05:35', '2012-06-25 20:05:35', '00000003', '5555.00', '', '佰利莱', null, '', '000000003', null, null, null, '555', '', '1', null, '3', '17');

-- ----------------------------
-- Table structure for `taoists_product_inventory`
-- ----------------------------
DROP TABLE IF EXISTS `taoists_product_inventory`;
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
  KEY `FK3EA00698E89ACB0F` (`company_id`),
  CONSTRAINT `FK3EA00698E89ACB0F` FOREIGN KEY (`company_id`) REFERENCES `taoists_company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taoists_product_inventory
-- ----------------------------

-- ----------------------------
-- Table structure for `taoists_purchase`
-- ----------------------------
DROP TABLE IF EXISTS `taoists_purchase`;
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
  KEY `FK1BDE32F571274F62` (`supplier_company_id`),
  CONSTRAINT `FK1BDE32F565DCA10D` FOREIGN KEY (`purchase_company_id`) REFERENCES `taoists_company` (`id`),
  CONSTRAINT `FK1BDE32F571274F62` FOREIGN KEY (`supplier_company_id`) REFERENCES `taoists_company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taoists_purchase
-- ----------------------------
INSERT INTO `taoists_purchase` VALUES ('1', '2012-06-30 23:29:20', '2012-06-30 23:30:20', '2012-06-30 23:30:20', '1', 'receive', 'rubys', null, null, null, 'admin', '1', '2012-06-30 23:29:50', '1', 'delivery', 'admin', null, null, '00001120630000001', null, null, '3', '4', null, '2', '1');

-- ----------------------------
-- Table structure for `taoists_purchase_box`
-- ----------------------------
DROP TABLE IF EXISTS `taoists_purchase_box`;
CREATE TABLE `taoists_purchase_box` (
  `id` bigint(20) NOT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `lastModifyDateTime` datetime DEFAULT NULL,
  `boxCode_id` bigint(20) DEFAULT NULL,
  `purchase_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCB1E5D81486E8840` (`boxCode_id`),
  KEY `FKCB1E5D81A2B808C2` (`purchase_id`),
  CONSTRAINT `FKCB1E5D81486E8840` FOREIGN KEY (`boxCode_id`) REFERENCES `taoists_box_code` (`id`),
  CONSTRAINT `FKCB1E5D81A2B808C2` FOREIGN KEY (`purchase_id`) REFERENCES `taoists_purchase` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taoists_purchase_box
-- ----------------------------
INSERT INTO `taoists_purchase_box` VALUES ('1', '2012-06-30 23:29:20', '2012-06-30 23:29:20', '1', '1');

-- ----------------------------
-- Table structure for `taoists_purchase_item`
-- ----------------------------
DROP TABLE IF EXISTS `taoists_purchase_item`;
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
  KEY `FK98B0921DF06C75C` (`product_id`),
  CONSTRAINT `FK98B0921DA2B808C2` FOREIGN KEY (`purchase_id`) REFERENCES `taoists_purchase` (`id`),
  CONSTRAINT `FK98B0921DF06C75C` FOREIGN KEY (`product_id`) REFERENCES `taoists_product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taoists_purchase_item
-- ----------------------------
INSERT INTO `taoists_purchase_item` VALUES ('1', '2012-06-30 23:29:20', '2012-06-30 23:29:20', '30', null, null, null, null, '1', '1');

-- ----------------------------
-- Table structure for `taoists_sale_region`
-- ----------------------------
DROP TABLE IF EXISTS `taoists_sale_region`;
CREATE TABLE `taoists_sale_region` (
  `id` bigint(20) NOT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `lastModifyDateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taoists_sale_region
-- ----------------------------

-- ----------------------------
-- Table structure for `taoists_stock`
-- ----------------------------
DROP TABLE IF EXISTS `taoists_stock`;
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
  KEY `FKD7D1ADA2F06C75C` (`product_id`),
  CONSTRAINT `FKD7D1ADA2E89ACB0F` FOREIGN KEY (`company_id`) REFERENCES `taoists_company` (`id`),
  CONSTRAINT `FKD7D1ADA2F06C75C` FOREIGN KEY (`product_id`) REFERENCES `taoists_product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taoists_stock
-- ----------------------------
INSERT INTO `taoists_stock` VALUES ('1', '2012-06-30 23:27:49', '2012-06-30 23:27:49', null, '0', '1000.00', '30', null, null, null, '1', '1');
INSERT INTO `taoists_stock` VALUES ('2', '2012-06-30 23:29:50', '2012-06-30 23:29:50', null, '1', null, null, null, '1000.00', '30', '1', '1');
INSERT INTO `taoists_stock` VALUES ('3', '2012-06-30 23:30:20', '2012-06-30 23:30:20', null, '0', '1000.00', '30', null, null, null, '2', '1');

-- ----------------------------
-- Table structure for `taoists_warehousing`
-- ----------------------------
DROP TABLE IF EXISTS `taoists_warehousing`;
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
  KEY `FKEADA504C6B8CCE69` (`operator_id`),
  CONSTRAINT `FKEADA504C6B8CCE69` FOREIGN KEY (`operator_id`) REFERENCES `taoists_account` (`id`),
  CONSTRAINT `FKEADA504CE89ACB0F` FOREIGN KEY (`company_id`) REFERENCES `taoists_company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taoists_warehousing
-- ----------------------------
INSERT INTO `taoists_warehousing` VALUES ('1', '2012-06-30 23:27:49', '2012-06-30 23:27:49', null, null, null, '1', null);
INSERT INTO `taoists_warehousing` VALUES ('2', '2012-06-30 23:30:20', '2012-06-30 23:30:20', null, '2012-06-30 23:30:20', null, '2', '1');

-- ----------------------------
-- Table structure for `taoists_warehousing_box`
-- ----------------------------
DROP TABLE IF EXISTS `taoists_warehousing_box`;
CREATE TABLE `taoists_warehousing_box` (
  `id` bigint(20) NOT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `lastModifyDateTime` datetime DEFAULT NULL,
  `boxCode_id` bigint(20) DEFAULT NULL,
  `warehousing_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK598DF758486E8840` (`boxCode_id`),
  KEY `FK598DF75840A05612` (`warehousing_id`),
  CONSTRAINT `FK598DF75840A05612` FOREIGN KEY (`warehousing_id`) REFERENCES `taoists_warehousing` (`id`),
  CONSTRAINT `FK598DF758486E8840` FOREIGN KEY (`boxCode_id`) REFERENCES `taoists_box_code` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taoists_warehousing_box
-- ----------------------------
INSERT INTO `taoists_warehousing_box` VALUES ('1', '2012-06-30 23:27:49', '2012-06-30 23:27:49', '1', '1');
INSERT INTO `taoists_warehousing_box` VALUES ('2', '2012-06-30 23:30:20', '2012-06-30 23:30:20', '1', '2');

-- ----------------------------
-- Table structure for `taoists_warehousing_item`
-- ----------------------------
DROP TABLE IF EXISTS `taoists_warehousing_item`;
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
  KEY `FKD8343326F06C75C` (`product_id`),
  CONSTRAINT `FKD834332640A05612` FOREIGN KEY (`warehousing_id`) REFERENCES `taoists_warehousing` (`id`),
  CONSTRAINT `FKD8343326F06C75C` FOREIGN KEY (`product_id`) REFERENCES `taoists_product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taoists_warehousing_item
-- ----------------------------
INSERT INTO `taoists_warehousing_item` VALUES ('1', '2012-06-30 23:27:49', '2012-06-30 23:27:49', null, '1000.00', '1', '1000.00', '1', '1');
INSERT INTO `taoists_warehousing_item` VALUES ('2', '2012-06-30 23:30:20', '2012-06-30 23:30:20', null, '1000.00', '1', '1000.00', '1', '2');
