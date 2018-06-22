/*
Navicat MySQL Data Transfer

Source Server         : 135
Source Server Version : 50718
Source Host           : 192.168.1.135:3306
Source Database       : qc_dev

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-12-20 09:30:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pm_template_help
-- ----------------------------
DROP TABLE IF EXISTS `pm_template_help`;
CREATE TABLE `pm_template_help` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `name` varchar(40) DEFAULT NULL COMMENT '模板名称',
  `remarks` varchar(40) DEFAULT NULL COMMENT '备注',
  `template_type` int(1) DEFAULT NULL COMMENT '状态，1:系统、2:自定义',
  `status` int(1) DEFAULT NULL COMMENT '状态 1:启用、2:停用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='PM模版';

-- ----------------------------
-- Table structure for pm_template
-- ----------------------------
DROP TABLE IF EXISTS `pm_template`;
CREATE TABLE `pm_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '机构ID',
  `name` varchar(40) DEFAULT NULL COMMENT '模板名称',
  `remarks` varchar(40) DEFAULT NULL COMMENT '备注',
  `template_type` int(1) DEFAULT NULL COMMENT '状态，1:系统、2:自定义',
  `status` int(1) DEFAULT NULL COMMENT '状态 1:启用、2:停用',
  `del_flag` bit(1) DEFAULT b'0' COMMENT '作废标记，0：启用，1：删除',
  `has_project` bit(1) DEFAULT b'0' COMMENT '是否有选项(0：没有，1：有)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='PM模版';

-- ----------------------------
-- Records of pm_template
-- ----------------------------
INSERT INTO `pm_template` VALUES ('1', null, '通用PM模板', '系统应用模板，不可编辑', '1', '1', '\0', '');

-- ----------------------------
-- Table structure for pm_project_help
-- ----------------------------
DROP TABLE IF EXISTS `pm_project_help`;
CREATE TABLE `pm_project_help` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `template_id` bigint(20) DEFAULT NULL COMMENT '关联模版id',
  `name` varchar(40) DEFAULT NULL COMMENT '项目名称',
  `status` int(1) DEFAULT NULL COMMENT '状态 1:启用、2:停用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='PM项目';

-- ----------------------------
-- Table structure for pm_project
-- ----------------------------
DROP TABLE IF EXISTS `pm_project`;
CREATE TABLE `pm_project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `template_id` bigint(20) DEFAULT NULL COMMENT '关联模版id',
  `name` varchar(40) DEFAULT NULL COMMENT '项目名称',
  `status` int(1) DEFAULT NULL COMMENT '状态 1:启用、2:停用',
  `del_flag` bit(1) DEFAULT b'0' COMMENT '作废标记，0：启用，1：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='PM项目';

-- ----------------------------
-- Records of pm_project
-- ----------------------------
INSERT INTO `pm_project` VALUES ('1', '1', '外观及附件检查', '1', '\0');
INSERT INTO `pm_project` VALUES ('2', '1', '性能', '1', '\0');
INSERT INTO `pm_project` VALUES ('3', '1', '保养校准', '1', '\0');

-- ----------------------------
-- Table structure for pm_plan_implement_help
-- ----------------------------
DROP TABLE IF EXISTS `pm_plan_implement_help`;
CREATE TABLE `pm_plan_implement_help` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `plan_id` bigint(20) DEFAULT NULL COMMENT '计划id',
  `assets_id` bigint(20) DEFAULT NULL COMMENT '设备id',
  `assets_num` varchar(50) DEFAULT NULL COMMENT '设备编号',
  `assets_name` varchar(50) DEFAULT NULL COMMENT '设备名称',
  `assets_spec` varchar(50) DEFAULT NULL COMMENT '设备型号',
  `assets_dept_id` bigint(20) DEFAULT NULL COMMENT '设备所在科室id',
  `assets_dept_name` varchar(50) DEFAULT NULL COMMENT '设备所在科室名称',
  `assets_status` int(1) DEFAULT NULL COMMENT '设备状态',
  `assets_status_text` varchar(50) DEFAULT NULL COMMENT '设备状态',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '机构ID',
  `tenant_name` varchar(50) DEFAULT NULL COMMENT '机构名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `template_id` bigint(20) DEFAULT NULL COMMENT '关联模版id',
  `cycle` int(10) DEFAULT NULL COMMENT '巡检周期',
  `level` int(1) DEFAULT NULL COMMENT 'PM登记1：一级 2：二级 3：三级',
  `first_time` datetime DEFAULT NULL COMMENT '首次巡检开始时间',
  `pre_time` datetime DEFAULT NULL COMMENT '上次巡检时间',
  `next_time` datetime DEFAULT NULL COMMENT '下次巡检时间',
  `charge_id` bigint(20) DEFAULT NULL COMMENT '负责人ID',
  `charge_name` varchar(50) DEFAULT NULL COMMENT '负责人姓名',
  `status` int(1) DEFAULT NULL COMMENT '状态 1:启用、2:停用',
  `check_status` int(1) DEFAULT NULL COMMENT '巡检状态 1:待实施、2:实施中',
  `del_flag` bit(1) DEFAULT b'0' COMMENT '作废标记，0：启用，1：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='PM计划实施help';


-- ----------------------------
-- Table structure for pm_plan_implement
-- ----------------------------
DROP TABLE IF EXISTS `pm_plan_implement`;
CREATE TABLE `pm_plan_implement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `implement_id` bigint(20) DEFAULT NULL COMMENT '关联实施id',
  `plan_id` bigint(20) DEFAULT NULL COMMENT '计划id',
  `assets_id` bigint(20) DEFAULT NULL COMMENT '设备id',
  `assets_num` varchar(50) DEFAULT NULL COMMENT '设备编号',
  `assets_name` varchar(50) DEFAULT NULL COMMENT '设备名称',
  `assets_spec` varchar(50) DEFAULT NULL COMMENT '设备型号',
  `assets_dept_id` bigint(20) DEFAULT NULL COMMENT '设备所在科室id',
  `assets_dept_name` varchar(50) DEFAULT NULL COMMENT '设备所在科室名称',
  `assets_status` int(1) DEFAULT NULL COMMENT '设备状态',
  `assets_status_text` varchar(50) DEFAULT NULL COMMENT '设备状态',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '机构ID',
  `tenant_name` varchar(50) DEFAULT NULL COMMENT '机构名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `template_id` bigint(20) DEFAULT NULL COMMENT '关联模版id',
  `cycle` int(10) DEFAULT NULL COMMENT '巡检周期',
  `level` int(1) DEFAULT NULL COMMENT 'PM登记1：一级 2：二级 3：三级',
  `first_time` datetime DEFAULT NULL COMMENT '首次巡检开始时间',
  `pre_time` datetime DEFAULT NULL COMMENT '上次巡检时间',
  `next_time` datetime DEFAULT NULL COMMENT '下次巡检时间',
  `charge_id` bigint(20) DEFAULT NULL COMMENT '负责人ID',
  `charge_name` varchar(50) DEFAULT NULL COMMENT '负责人姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='PM计划实施';


-- ----------------------------
-- Table structure for pm_plan
-- ----------------------------
DROP TABLE IF EXISTS `pm_plan`;
CREATE TABLE `pm_plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `assets_id` bigint(20) DEFAULT NULL COMMENT '设备id',
  `assets_num` varchar(50) DEFAULT NULL COMMENT '设备编号',
  `assets_name` varchar(50) DEFAULT NULL COMMENT '设备名称',
  `assets_spec` varchar(50) DEFAULT NULL COMMENT '设备型号',
  `assets_dept_id` bigint(20) DEFAULT NULL COMMENT '设备所在科室id',
  `assets_dept_name` varchar(50) DEFAULT NULL COMMENT '设备所在科室名称',
  `assets_status` int(1) DEFAULT NULL COMMENT '设备状态',
  `assets_status_text` varchar(50) DEFAULT NULL COMMENT '设备状态',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '机构ID',
  `tenant_name` varchar(50) DEFAULT NULL COMMENT '机构名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `template_id` bigint(20) DEFAULT NULL COMMENT '关联模版id',
  `pre_template_id` bigint(20) DEFAULT NULL COMMENT '原来模版id',
  `cycle` int(10) DEFAULT NULL COMMENT '巡检周期',
  `level` int(1) DEFAULT NULL COMMENT 'PM登记1：一级 2：二级 3：三级',
  `first_time` datetime DEFAULT NULL COMMENT '首次巡检开始时间',
  `pre_time` datetime DEFAULT NULL COMMENT '上次巡检时间',
  `next_time` datetime DEFAULT NULL COMMENT '下次巡检时间',
  `charge_id` bigint(20) DEFAULT NULL COMMENT '负责人ID',
  `charge_name` varchar(50) DEFAULT NULL COMMENT '负责人姓名',
  `status` int(1) DEFAULT NULL COMMENT '状态 1:启用、2:停用',
  `check_status` int(1) DEFAULT NULL COMMENT '巡检状态 1:待实施、2:实施中',
  `del_flag` bit(1) DEFAULT b'0' COMMENT '作废标记，0：启用，1：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='PM巡检计划';


-- ----------------------------
-- Table structure for pm_option_help
-- ----------------------------
DROP TABLE IF EXISTS `pm_option_help`;
CREATE TABLE `pm_option_help` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `project_id` bigint(20) DEFAULT NULL COMMENT '关联项目 id',
  `name` varchar(40) DEFAULT NULL COMMENT '选项名称',
  `status` int(1) DEFAULT NULL COMMENT '状态 1:启用、2:停用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='PM项目选项';

-- ----------------------------
-- Table structure for pm_option
-- ----------------------------
DROP TABLE IF EXISTS `pm_option`;
CREATE TABLE `pm_option` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `project_id` bigint(20) DEFAULT NULL COMMENT '关联项目 id',
  `name` varchar(40) DEFAULT NULL COMMENT '选项名称',
  `status` int(1) DEFAULT NULL COMMENT '状态 1:启用、2:停用',
  `del_flag` bit(1) DEFAULT b'0' COMMENT '作废标记，0：启用，1：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='PM项目选项';

-- ----------------------------
-- Records of pm_option
-- ----------------------------
INSERT INTO `pm_option` VALUES ('1', '1', '电源插头是及电源线无破损', '1', '\0');
INSERT INTO `pm_option` VALUES ('2', '1', '外观整洁，无明显缺陷', '1', '\0');
INSERT INTO `pm_option` VALUES ('3', '1', '旋钮和按键无松动或破损', '1', '\0');
INSERT INTO `pm_option` VALUES ('4', '1', '电极、导联、探头等无断线或松动', '1', '\0');
INSERT INTO `pm_option` VALUES ('5', '1', '其它附件无缺陷', '1', '\0');
INSERT INTO `pm_option` VALUES ('6', '1', '马达模块无缺损', '1', '\0');
INSERT INTO `pm_option` VALUES ('7', '2', '1、基本功能是否正常', '1', '\0');
INSERT INTO `pm_option` VALUES ('8', '2', 'a) 按键功能是否正常', '1', '\0');
INSERT INTO `pm_option` VALUES ('9', '2', 'b) 马达功能是否正常', '1', '\0');
INSERT INTO `pm_option` VALUES ('10', '2', '2、数据结果有无明显差异', '1', '\0');
INSERT INTO `pm_option` VALUES ('11', '2', 'a)导丝成像校正相距是否正常', '1', '\0');
INSERT INTO `pm_option` VALUES ('12', '2', 'b) 数据输出刻录系统是否正常', '1', '\0');
INSERT INTO `pm_option` VALUES ('13', '2', '3、电池是否已充足电（选配）', '1', '\0');
INSERT INTO `pm_option` VALUES ('14', '3', '表面清洁', '1', '\0');

-- ----------------------------
-- Table structure for pm_implement_file
-- ----------------------------
DROP TABLE IF EXISTS `pm_implement_file`;
CREATE TABLE `pm_implement_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `implement_id` int(11) DEFAULT NULL COMMENT '关联实施id',
  `name` varchar(50) DEFAULT NULL COMMENT '文件名称',
  `url` varchar(300) DEFAULT NULL COMMENT '文件路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='PM实施文件表';


-- ----------------------------
-- Table structure for pm_implement
-- ----------------------------
DROP TABLE IF EXISTS `pm_implement`;
CREATE TABLE `pm_implement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `implement_id` bigint(20) DEFAULT NULL COMMENT '关联实施id',
  `report_no` char(16) DEFAULT NULL COMMENT '报告编号',
  `paln_id` bigint(20) DEFAULT NULL COMMENT '关联巡检计划id',
  `actual_start_date` datetime DEFAULT NULL COMMENT '实际开始日期',
  `actual_end_date` datetime DEFAULT NULL COMMENT '时间结束时间',
  `plan_date` datetime DEFAULT NULL COMMENT '计划巡检日期',
  `create_date` datetime DEFAULT NULL COMMENT '实施开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '实施结束时间',
  `live` int(1) DEFAULT NULL COMMENT '设备现状1：正常工作 2：小问题不影响使用 3：有故障需要维修 4：不能使用',
  `work_time` decimal(7,2) DEFAULT NULL COMMENT '工时',
  `remarks` varchar(300) DEFAULT NULL COMMENT '备注',
  `status` int(1) DEFAULT NULL COMMENT '实施状态(1，实施中 2，已完成）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='PM实施';


-- ----------------------------
-- Table structure for pm_answers_option
-- ----------------------------
DROP TABLE IF EXISTS `pm_answers_option`;
CREATE TABLE `pm_answers_option` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `implement_id` bigint(20) DEFAULT NULL COMMENT '关联实施id',
  `answer_id` bigint(20) DEFAULT NULL COMMENT '答案id',
  `option_id` bigint(20) DEFAULT NULL COMMENT '选项id',
  `option_name` varchar(40) DEFAULT NULL COMMENT '选项名称',
  `answer` int(1) DEFAULT NULL COMMENT ' 答案1：合格 2：修改 3：可用 4：待修',
  `remarks` varchar(300) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='PMoption答案';


-- ----------------------------
-- Table structure for pm_answers
-- ----------------------------
DROP TABLE IF EXISTS `pm_answers`;
CREATE TABLE `pm_answers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `implement_id` bigint(20) DEFAULT NULL COMMENT '关联实施id',
  `project_id` bigint(20) DEFAULT NULL COMMENT '项目id',
  `project_name` varchar(40) DEFAULT NULL COMMENT '项目名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='PM答案';

