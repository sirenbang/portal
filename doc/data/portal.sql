/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50162
Source Host           : localhost:3306
Source Database       : portal

Target Server Type    : MYSQL
Target Server Version : 50162
File Encoding         : 65001

Date: 2013-04-15 17:22:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `pt_abstractportletdescription`
-- ----------------------------
DROP TABLE IF EXISTS `pt_abstractportletdescription`;
CREATE TABLE `pt_abstractportletdescription` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `createTime` date DEFAULT NULL,
  `image` varchar(50) DEFAULT NULL,
  `imagePath` varchar(255) DEFAULT NULL,
  `draggable` int(11) DEFAULT '0' COMMENT '0 可拖动 1 不可拖动',
  `type` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pt_abstractportletdescription
-- ----------------------------

-- ----------------------------
-- Table structure for `pt_defaultportlet`
-- ----------------------------
DROP TABLE IF EXISTS `pt_defaultportlet`;
CREATE TABLE `pt_defaultportlet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `column` int(11) NOT NULL DEFAULT '1',
  `columnIndex` int(11) NOT NULL DEFAULT '1',
  `hidden` int(11) NOT NULL DEFAULT '0' COMMENT '0 不隐藏 1 隐藏',
  `portalGroup_id` int(11) NOT NULL,
  `abstractPortletDescription_id` int(11) NOT NULL,
  `portalLayout_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `portalGroup_id` (`portalGroup_id`),
  KEY `abstractPortletDescription_id` (`abstractPortletDescription_id`),
  KEY `portalLayout_id` (`portalLayout_id`),
  CONSTRAINT `pt_defaultportlet_ibfk_1` FOREIGN KEY (`portalGroup_id`) REFERENCES `pt_portalgroup` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pt_defaultportlet_ibfk_2` FOREIGN KEY (`abstractPortletDescription_id`) REFERENCES `pt_abstractportletdescription` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pt_defaultportlet_ibfk_3` FOREIGN KEY (`portalLayout_id`) REFERENCES `pt_portallayout` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pt_defaultportlet
-- ----------------------------

-- ----------------------------
-- Table structure for `pt_dict`
-- ----------------------------
DROP TABLE IF EXISTS `pt_dict`;
CREATE TABLE `pt_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(50) NOT NULL,
  `code` int(11) NOT NULL,
  `value` varchar(50) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0 可用 -1 不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pt_dict
-- ----------------------------
INSERT INTO `pt_dict` VALUES ('1', 'searchType', '1', 'gt', '0');
INSERT INTO `pt_dict` VALUES ('2', 'searchType', '2', 'lt', '0');
INSERT INTO `pt_dict` VALUES ('3', 'searchType', '3', 'ge', '0');
INSERT INTO `pt_dict` VALUES ('4', 'searchType', '4', 'le', '0');
INSERT INTO `pt_dict` VALUES ('5', 'searchType', '5', 'eq', '0');
INSERT INTO `pt_dict` VALUES ('6', 'searchType', '6', 'like', '0');
INSERT INTO `pt_dict` VALUES ('7', 'columnType', '1', 'number', '0');
INSERT INTO `pt_dict` VALUES ('8', 'columnType', '2', 'string', '0');
INSERT INTO `pt_dict` VALUES ('9', 'columnType', '3', 'date', '0');
INSERT INTO `pt_dict` VALUES ('10', 'columnType', '4', 'money', '0');
INSERT INTO `pt_dict` VALUES ('11', 'portletType', '1', 'iframe', '0');
INSERT INTO `pt_dict` VALUES ('12', 'portletType', '2', 'link', '0');
INSERT INTO `pt_dict` VALUES ('13', 'portletType', '3', 'grid', '0');

-- ----------------------------
-- Table structure for `pt_gridportletdescription`
-- ----------------------------
DROP TABLE IF EXISTS `pt_gridportletdescription`;
CREATE TABLE `pt_gridportletdescription` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `abstractPortletDescription_id` int(11) NOT NULL,
  `dataRepository` varchar(300) DEFAULT NULL,
  `pageIs` int(11) DEFAULT '0' COMMENT '0 分页 1 不分页',
  `pageSize` int(11) DEFAULT '4',
  PRIMARY KEY (`id`),
  KEY `abstractPortletDescription_id` (`abstractPortletDescription_id`),
  CONSTRAINT `pt_gridportletdescription_ibfk_1` FOREIGN KEY (`abstractPortletDescription_id`) REFERENCES `pt_abstractportletdescription` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pt_gridportletdescription
-- ----------------------------

-- ----------------------------
-- Table structure for `pt_gridproperty`
-- ----------------------------
DROP TABLE IF EXISTS `pt_gridproperty`;
CREATE TABLE `pt_gridproperty` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `desc` varchar(200) DEFAULT NULL,
  `type` varchar(30) NOT NULL DEFAULT 'String' COMMENT 'String numeral date money',
  `orderBy` int(11) DEFAULT '0' COMMENT '0 升序 1 降序',
  `index` int(11) NOT NULL,
  `gridportletDescription_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `gridportletDescription_id` (`gridportletDescription_id`),
  CONSTRAINT `pt_gridproperty_ibfk_1` FOREIGN KEY (`gridportletDescription_id`) REFERENCES `pt_gridportletdescription` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pt_gridproperty
-- ----------------------------

-- ----------------------------
-- Table structure for `pt_gridsearch`
-- ----------------------------
DROP TABLE IF EXISTS `pt_gridsearch`;
CREATE TABLE `pt_gridsearch` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `searchName` varchar(100) NOT NULL,
  `searchType` varchar(30) NOT NULL DEFAULT 'gt' COMMENT 'gt lt ge le eq like',
  `searchDesc` varchar(300) DEFAULT NULL,
  `gridPortletDescription_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `gridPortletDescription_id` (`gridPortletDescription_id`),
  CONSTRAINT `pt_gridsearch_ibfk_1` FOREIGN KEY (`gridPortletDescription_id`) REFERENCES `pt_gridportletdescription` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pt_gridsearch
-- ----------------------------

-- ----------------------------
-- Table structure for `pt_iframeportletdescription`
-- ----------------------------
DROP TABLE IF EXISTS `pt_iframeportletdescription`;
CREATE TABLE `pt_iframeportletdescription` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(100) NOT NULL DEFAULT 'http://localhost:8080/Portal/index.jsp',
  `abstractPortletDescription_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `abstractPortletDescription_id` (`abstractPortletDescription_id`),
  CONSTRAINT `pt_iframeportletdescription_ibfk_1` FOREIGN KEY (`abstractPortletDescription_id`) REFERENCES `pt_abstractportletdescription` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pt_iframeportletdescription
-- ----------------------------

-- ----------------------------
-- Table structure for `pt_linkportletdescription`
-- ----------------------------
DROP TABLE IF EXISTS `pt_linkportletdescription`;
CREATE TABLE `pt_linkportletdescription` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lineClass` varchar(30) NOT NULL DEFAULT 'none' COMMENT 'none dot line',
  `abstractPortletDescription_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `abstractPortletDescription_id` (`abstractPortletDescription_id`),
  CONSTRAINT `pt_linkportletdescription_ibfk_1` FOREIGN KEY (`abstractPortletDescription_id`) REFERENCES `pt_abstractportletdescription` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pt_linkportletdescription
-- ----------------------------

-- ----------------------------
-- Table structure for `pt_linkproperty`
-- ----------------------------
DROP TABLE IF EXISTS `pt_linkproperty`;
CREATE TABLE `pt_linkproperty` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(100) NOT NULL,
  `description` varchar(300) DEFAULT NULL,
  `icon` varchar(100) DEFAULT NULL,
  `linkportletDescription_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `linkportletDescription_id` (`linkportletDescription_id`),
  CONSTRAINT `pt_linkproperty_ibfk_1` FOREIGN KEY (`linkportletDescription_id`) REFERENCES `pt_linkportletdescription` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pt_linkproperty
-- ----------------------------

-- ----------------------------
-- Table structure for `pt_portal`
-- ----------------------------
DROP TABLE IF EXISTS `pt_portal`;
CREATE TABLE `pt_portal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `layoutId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `portalGroupId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `layoutId` (`layoutId`),
  KEY `userId` (`userId`),
  KEY `portalGroupId` (`portalGroupId`),
  CONSTRAINT `pt_portal_ibfk_1` FOREIGN KEY (`layoutId`) REFERENCES `pt_portallayout` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pt_portal_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `pt_user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pt_portal_ibfk_3` FOREIGN KEY (`portalGroupId`) REFERENCES `pt_portalgroup` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pt_portal
-- ----------------------------

-- ----------------------------
-- Table structure for `pt_portalgroup`
-- ----------------------------
DROP TABLE IF EXISTS `pt_portalgroup`;
CREATE TABLE `pt_portalgroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `hidden` int(11) DEFAULT '0' COMMENT '0 不隐藏 1 隐藏',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pt_portalgroup
-- ----------------------------
INSERT INTO `pt_portalgroup` VALUES ('6', 'main', '主门户组', '0');
INSERT INTO `pt_portalgroup` VALUES ('7', 'test', '', '0');
INSERT INTO `pt_portalgroup` VALUES ('10', 'test1', '', '0');

-- ----------------------------
-- Table structure for `pt_portallayout`
-- ----------------------------
DROP TABLE IF EXISTS `pt_portallayout`;
CREATE TABLE `pt_portallayout` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pt_portallayout
-- ----------------------------

-- ----------------------------
-- Table structure for `pt_portlet`
-- ----------------------------
DROP TABLE IF EXISTS `pt_portlet`;
CREATE TABLE `pt_portlet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `column` int(11) NOT NULL DEFAULT '0',
  `columnIndex` int(11) NOT NULL DEFAULT '0',
  `hidden` int(11) NOT NULL,
  `abstractPortletDescriptionId` int(11) NOT NULL,
  `portalId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `abstractPortletDescriptionId` (`abstractPortletDescriptionId`),
  KEY `portalId` (`portalId`),
  CONSTRAINT `pt_portlet_ibfk_1` FOREIGN KEY (`abstractPortletDescriptionId`) REFERENCES `pt_abstractportletdescription` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pt_portlet_ibfk_2` FOREIGN KEY (`portalId`) REFERENCES `pt_portal` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pt_portlet
-- ----------------------------

-- ----------------------------
-- Table structure for `pt_role`
-- ----------------------------
DROP TABLE IF EXISTS `pt_role`;
CREATE TABLE `pt_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `description` varchar(300) DEFAULT NULL,
  `enable` int(11) NOT NULL DEFAULT '0' COMMENT '0 可用 1 不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pt_role
-- ----------------------------
INSERT INTO `pt_role` VALUES ('2', '超级管理员', '超级管理员', '0');

-- ----------------------------
-- Table structure for `pt_role_portalgroup`
-- ----------------------------
DROP TABLE IF EXISTS `pt_role_portalgroup`;
CREATE TABLE `pt_role_portalgroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL,
  `portalGroupid` int(11) NOT NULL,
  `permission` int(11) NOT NULL DEFAULT '0' COMMENT '0 未授权 1 已授权',
  PRIMARY KEY (`id`),
  KEY `roleId` (`roleId`),
  KEY `portalGroupid` (`portalGroupid`),
  CONSTRAINT `pt_role_portalgroup_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `pt_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pt_role_portalgroup_ibfk_2` FOREIGN KEY (`portalGroupid`) REFERENCES `pt_portalgroup` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pt_role_portalgroup
-- ----------------------------
INSERT INTO `pt_role_portalgroup` VALUES ('3', '2', '6', '0');
INSERT INTO `pt_role_portalgroup` VALUES ('4', '2', '7', '0');
INSERT INTO `pt_role_portalgroup` VALUES ('7', '2', '10', '1');

-- ----------------------------
-- Table structure for `pt_user`
-- ----------------------------
DROP TABLE IF EXISTS `pt_user`;
CREATE TABLE `pt_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(20) NOT NULL,
  `ENABLED` int(1) NOT NULL DEFAULT '1',
  `DESCRIPTION` varchar(100) DEFAULT NULL,
  `TYPE` int(1) NOT NULL DEFAULT '0' COMMENT '0 普通用户 1 普通管理员 2 超级管理员',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pt_user
-- ----------------------------
INSERT INTO `pt_user` VALUES ('2', 'admin', 'admin', '0', '超级管理员', '2');
INSERT INTO `pt_user` VALUES ('12', 'test', '1', '0', '测试用户', '0');

-- ----------------------------
-- Table structure for `pt_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `pt_user_role`;
CREATE TABLE `pt_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0 未授权 1 已授权',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `pt_user_role_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `pt_user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pt_user_role_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `pt_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pt_user_role
-- ----------------------------
INSERT INTO `pt_user_role` VALUES ('3', '2', '2', '1');
INSERT INTO `pt_user_role` VALUES ('4', '12', '2', '0');
