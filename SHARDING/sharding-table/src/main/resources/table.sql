/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50640
 Source Host           : localhost
 Source Database       : my_sharding_test2

 Target Server Type    : MySQL
 Target Server Version : 50640
 File Encoding         : utf-8

 Date: 06/04/2018 16:28:08 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `tp_knowledge_0`
-- ----------------------------
DROP TABLE IF EXISTS `tp_knowledge_0`;
CREATE TABLE `tp_knowledge_0` (
  `ID` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `KNOWLEDGE_NO` bigint(20) DEFAULT NULL COMMENT '知识点编号',
  `KNOWLEDGE_NAME` varchar(100) DEFAULT NULL COMMENT '知识名称',
  `KNOWLEDGE_IMG` text COMMENT '知识点图片地址',
  `KNOWLEDGE_STATUS` char(1) DEFAULT NULL COMMENT '状态，1_归档，2_过期，3_发布中，4_隐藏，5_删除',
  `DATE_CREATED` datetime DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATED` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';

-- ----------------------------
--  Table structure for `tp_knowledge_1`
-- ----------------------------
DROP TABLE IF EXISTS `tp_knowledge_1`;
CREATE TABLE `tp_knowledge_1` (
  `ID` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `KNOWLEDGE_NO` bigint(20) DEFAULT NULL COMMENT '知识点编号',
  `KNOWLEDGE_NAME` varchar(100) DEFAULT NULL COMMENT '知识名称',
  `KNOWLEDGE_IMG` text COMMENT '知识点图片地址',
  `KNOWLEDGE_STATUS` char(1) DEFAULT NULL COMMENT '状态，1_归档，2_过期，3_发布中，4_隐藏，5_删除',
  `DATE_CREATED` datetime DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATED` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';

-- ----------------------------
--  Table structure for `tp_student_0`
-- ----------------------------
DROP TABLE IF EXISTS `tp_student_0`;
CREATE TABLE `tp_student_0` (
  `STUDENT_NO` bigint(20) NOT NULL COMMENT '学员编号',
  `NAME` varchar(20) DEFAULT NULL COMMENT '学员名称',
  `STATUS` tinyint(4) DEFAULT '1' COMMENT '是否可用，0_已禁用,1_已启用',
  `DATE_CREATED` datetime DEFAULT NULL COMMENT '创建日期',
  `LAST_UPDATED` datetime DEFAULT NULL COMMENT '最近更新时间',
  PRIMARY KEY (`STUDENT_NO`),
  UNIQUE KEY `tp_student_0_STUDENT_NO_uindex` (`STUDENT_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学员表';

-- ----------------------------
--  Table structure for `tp_student_1`
-- ----------------------------
DROP TABLE IF EXISTS `tp_student_1`;
CREATE TABLE `tp_student_1` (
  `STUDENT_NO` bigint(20) NOT NULL COMMENT '学员编号',
  `NAME` varchar(20) DEFAULT NULL COMMENT '学员名称',
  `STATUS` tinyint(4) DEFAULT '1' COMMENT '是否可用，0_已禁用,1_已启用',
  `DATE_CREATED` datetime DEFAULT NULL COMMENT '创建日期',
  `LAST_UPDATED` datetime DEFAULT NULL COMMENT '最近更新时间',
  PRIMARY KEY (`STUDENT_NO`),
  UNIQUE KEY `tp_student_1_STUDENT_NO_uindex` (`STUDENT_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学员表';

-- ----------------------------
--  Table structure for `tp_study_0`
-- ----------------------------
DROP TABLE IF EXISTS `tp_study_0`;
CREATE TABLE `tp_study_0` (
  `STUDY_NO` bigint(20) NOT NULL DEFAULT '0' COMMENT '学习任务编号',
  `NAME` varchar(40) DEFAULT NULL COMMENT '学习计划名称',
  `STATUS` char(1) DEFAULT '0' COMMENT '状态,-1 删除, 0_下架, 1_已发布,2_已结束,3_已删除,4_待发布',
  `DATE_CREATED` datetime DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATED` datetime DEFAULT NULL COMMENT '最后一次修改时间',
  PRIMARY KEY (`STUDY_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务表';

-- ----------------------------
--  Table structure for `tp_study_1`
-- ----------------------------
DROP TABLE IF EXISTS `tp_study_1`;
CREATE TABLE `tp_study_1` (
  `STUDY_NO` bigint(20) NOT NULL DEFAULT '0' COMMENT '学习任务编号',
  `NAME` varchar(40) DEFAULT NULL COMMENT '学习计划名称',
  `STATUS` char(1) DEFAULT '0' COMMENT '状态,-1 删除, 0_下架, 1_已发布,2_已结束,3_已删除,4_待发布',
  `DATE_CREATED` datetime DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATED` datetime DEFAULT NULL COMMENT '最后一次修改时间',
  PRIMARY KEY (`STUDY_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务表';

-- ----------------------------
--  Table structure for `tp_study_assignment_0`
-- ----------------------------
DROP TABLE IF EXISTS `tp_study_assignment_0`;
CREATE TABLE `tp_study_assignment_0` (
  `ID` bigint(20) NOT NULL DEFAULT '0',
  `STUDY_NO` bigint(20) DEFAULT NULL,
  `STUDENT_NO` bigint(20) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tp_study_assignment_1`
-- ----------------------------
DROP TABLE IF EXISTS `tp_study_assignment_1`;
CREATE TABLE `tp_study_assignment_1` (
  `ID` bigint(20) NOT NULL DEFAULT '0',
  `STUDY_NO` bigint(20) DEFAULT NULL,
  `STUDENT_NO` bigint(20) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tp_study_knowledge_process_0`
-- ----------------------------
DROP TABLE IF EXISTS `tp_study_knowledge_process_0`;
CREATE TABLE `tp_study_knowledge_process_0` (
  `ID` bigint(20) NOT NULL COMMENT '主键',
  `STUDY_NO` bigint(20) DEFAULT NULL COMMENT '学习编号',
  `STUDENT_NO` bigint(20) DEFAULT NULL COMMENT '学员编号',
  `KNOWLEDGE_NO` bigint(20) DEFAULT NULL COMMENT '知识点编号',
  `FINISH_PERCENT` int(5) DEFAULT NULL COMMENT '完成百分比%',
  `STATUS` char(1) DEFAULT '0' COMMENT '知识点状态,0_未开始,1_学习中,2_已完成未领取,3_已领取',
  `DATE_CREATED` datetime DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATED` datetime DEFAULT NULL COMMENT '最后一次修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学员资源学习进度表';

-- ----------------------------
--  Table structure for `tp_study_knowledge_process_1`
-- ----------------------------
DROP TABLE IF EXISTS `tp_study_knowledge_process_1`;
CREATE TABLE `tp_study_knowledge_process_1` (
  `ID` bigint(20) NOT NULL COMMENT '主键',
  `STUDY_NO` bigint(20) DEFAULT NULL COMMENT '学习编号',
  `STUDENT_NO` bigint(20) DEFAULT NULL COMMENT '学员编号',
  `KNOWLEDGE_NO` bigint(20) DEFAULT NULL COMMENT '知识点编号',
  `FINISH_PERCENT` int(5) DEFAULT NULL COMMENT '完成百分比%',
  `STATUS` char(1) DEFAULT '0' COMMENT '知识点状态,0_未开始,1_学习中,2_已完成未领取,3_已领取',
  `DATE_CREATED` datetime DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATED` datetime DEFAULT NULL COMMENT '最后一次修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学员资源学习进度表';

-- ----------------------------
--  Table structure for `tp_study_process_0`
-- ----------------------------
DROP TABLE IF EXISTS `tp_study_process_0`;
CREATE TABLE `tp_study_process_0` (
  `ID` bigint(20) NOT NULL COMMENT '主键',
  `STUDY_NO` bigint(20) DEFAULT NULL COMMENT '学习计划编号',
  `STUDENT_NO` bigint(20) DEFAULT NULL COMMENT '学员编号',
  `FINISH_PERCENT` int(5) DEFAULT NULL COMMENT '完成百分比%',
  `STATUS` char(1) DEFAULT NULL COMMENT '学习状态,0_未开始,1_学习中,2_已完成,3_已过期',
  `DATE_CREATED` datetime DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATED` datetime DEFAULT NULL COMMENT '最后一次修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学员学习表';

-- ----------------------------
--  Table structure for `tp_study_process_1`
-- ----------------------------
DROP TABLE IF EXISTS `tp_study_process_1`;
CREATE TABLE `tp_study_process_1` (
  `ID` bigint(20) NOT NULL COMMENT '主键',
  `STUDY_NO` bigint(20) DEFAULT NULL COMMENT '学习计划编号',
  `STUDENT_NO` bigint(20) DEFAULT NULL COMMENT '学员编号',
  `FINISH_PERCENT` int(5) DEFAULT NULL COMMENT '完成百分比%',
  `STATUS` char(1) DEFAULT NULL COMMENT '学习状态,0_未开始,1_学习中,2_已完成,3_已过期',
  `DATE_CREATED` datetime DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATED` datetime DEFAULT NULL COMMENT '最后一次修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学员学习表';

-- ----------------------------
--  Table structure for `tp_study_stage_knowledge_0`
-- ----------------------------
DROP TABLE IF EXISTS `tp_study_stage_knowledge_0`;
CREATE TABLE `tp_study_stage_knowledge_0` (
  `ID` bigint(20) NOT NULL COMMENT '主键',
  `STUDY_NO` bigint(20) DEFAULT NULL,
  `KNOWLEDGE_NO` bigint(20) DEFAULT NULL COMMENT '知识点编号',
  `STAGE_NAME` varchar(200) DEFAULT NULL COMMENT '阶段id',
  `SORT_NUM` int(11) DEFAULT NULL,
  `DATE_CREATED` datetime DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATED` datetime DEFAULT NULL COMMENT '最后一次修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阶段资源关系表';

-- ----------------------------
--  Table structure for `tp_study_stage_knowledge_1`
-- ----------------------------
DROP TABLE IF EXISTS `tp_study_stage_knowledge_1`;
CREATE TABLE `tp_study_stage_knowledge_1` (
  `ID` bigint(20) NOT NULL COMMENT '主键',
  `STUDY_NO` bigint(20) DEFAULT NULL,
  `KNOWLEDGE_NO` bigint(20) DEFAULT NULL COMMENT '知识点编号',
  `STAGE_NAME` varchar(200) DEFAULT NULL COMMENT '阶段id',
  `SORT_NUM` int(11) DEFAULT NULL,
  `DATE_CREATED` datetime DEFAULT NULL COMMENT '创建时间',
  `LAST_UPDATED` datetime DEFAULT NULL COMMENT '最后一次修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阶段资源关系表';

SET FOREIGN_KEY_CHECKS = 1;
