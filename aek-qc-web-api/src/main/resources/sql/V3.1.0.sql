CREATE TABLE `mt_implement_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `plan_implement_id` bigint(20) DEFAULT NULL COMMENT '保养计划实施ID',
  `plan_id` bigint(20) DEFAULT NULL COMMENT '保养计划ID',
  `report_no` varchar(16) DEFAULT NULL COMMENT '保养报告编号',
  `actual_end_date` datetime DEFAULT NULL COMMENT '保养实施实际结束时间',
  `end_date` datetime DEFAULT NULL COMMENT '实施结束时间',
  `assets_status` int(1) DEFAULT NULL COMMENT '设备现状(1=正常工作,2=小问题不影响使用,3=有故障需要维修,4=不能使用)',
  `remarks` varchar(300) DEFAULT NULL COMMENT '备注',
  `status` int(1) DEFAULT NULL COMMENT '状态(1=实施中，2=已实施)',
  `implement_user_id` bigint(20) DEFAULT NULL COMMENT '实施人ID',
  `implement_user_name` varchar(40) DEFAULT NULL COMMENT '实施人姓名',
  `administrator` varchar(40) DEFAULT NULL COMMENT '专管人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='保养实施结果表';

CREATE TABLE `mt_implement_result_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `result_id` bigint(20) DEFAULT NULL COMMENT '保养实施结果ID',
  `file_name` varchar(255) DEFAULT NULL COMMENT '文件名称',
  `file_path` varchar(255) DEFAULT NULL COMMENT '文件路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='保养实施结果附件表';

CREATE TABLE `mt_implement_result_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `result_id` bigint(20) DEFAULT NULL COMMENT '保养实施结果ID',
  `plan_template_id` bigint(20) DEFAULT NULL COMMENT '保养计划模板ID ',
  `plan_template_item_id` bigint(20) DEFAULT NULL COMMENT '保养计划模板项目ID',
  `plan_template_item_name` varchar(40) DEFAULT NULL COMMENT '保养计划模板项目名称',
  `item_result` int(1) DEFAULT NULL COMMENT '保养计划模板项目结果(1=正常，2=不正常)',
  `item_remarks` varchar(300) DEFAULT NULL COMMENT '保养计划模板项目结果备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='保养实施项目结果表';

CREATE TABLE `mt_plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '机构ID',
  `tenant_name` varchar(40) DEFAULT NULL COMMENT '机构名称',
  `plan_template_id` bigint(20) DEFAULT NULL COMMENT '计划模板ID',
  `template_id` bigint(20) DEFAULT NULL COMMENT '模板库模板ID',
  `rate` int(1) DEFAULT NULL COMMENT '保养频率(1=每天，2=每周)',
  `administrator` varchar(40) CHARACTER SET utf32 DEFAULT NULL COMMENT '专管人',
  `last_implement_time` datetime DEFAULT NULL COMMENT '上次实施时间',
  `next_implement_time` datetime DEFAULT NULL COMMENT '下次实施时间',
  `enable` bit(1) DEFAULT NULL COMMENT '启用状态(0=停用，1=启用)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='保养计划';

CREATE TABLE `mt_plan_assets` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `plan_id` bigint(20) DEFAULT NULL COMMENT '保养计划ID',
  `assets_id` bigint(20) DEFAULT NULL COMMENT '资产ID',
  `assets_num` varchar(50) DEFAULT NULL COMMENT '资产编号',
  `hospital_assets_num` varchar(50) DEFAULT NULL COMMENT '资产院内编码',
  `assets_name` varchar(50) DEFAULT NULL COMMENT '资产名称',
  `factory_num` varchar(50) DEFAULT '' COMMENT '出厂编号',
  `assets_spec` varchar(50) DEFAULT NULL COMMENT '设备型号',
  `assets_dept_id` bigint(20) DEFAULT NULL COMMENT '设备所在科室ID',
  `assets_dept_name` varchar(50) DEFAULT NULL COMMENT '设备所在科室名称',
  `create_time` datetime DEFAULT NULL COMMENT '设备加入保养计划时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='保养计划资产信息表';

CREATE TABLE `mt_plan_implement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `plan_id` bigint(20) DEFAULT NULL COMMENT '保养计划ID',
  `administrator` varchar(40) DEFAULT NULL COMMENT '专管人',
  `rate` int(1) DEFAULT NULL COMMENT '保养频率(1=每天，2=每周)',
  `last_implement_time` datetime DEFAULT NULL COMMENT '上次实施时间',
  `next_implement_time` datetime DEFAULT NULL COMMENT '下次实施时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='保养计划实施表';

CREATE TABLE `mt_plan_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '机构ID',
  `plan_id` bigint(20) DEFAULT NULL COMMENT '保养计划ID',
  `template_id` bigint(20) DEFAULT NULL COMMENT '模板库中模板ID',
  `name` varchar(40) DEFAULT NULL COMMENT '模板名称',
  `type` int(1) DEFAULT NULL COMMENT '模板类型(1=系统模板，2=自定义模板)',
  `remarks` varchar(40) DEFAULT NULL COMMENT '备注',
  `enable` bit(1) DEFAULT NULL COMMENT '启用标记(0=停用，1=启用)',
  `del_flag` bit(1) DEFAULT NULL COMMENT '删除标记(0=未删除，1=已删除)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='保养计划模板表';

CREATE TABLE `mt_plan_template_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `plan_template_id` bigint(20) DEFAULT NULL COMMENT '模板ID',
  `item_name` varchar(40) DEFAULT NULL COMMENT '项目名称',
  `del_flag` bit(1) DEFAULT NULL COMMENT '删除标记(0=未删除，1=已删除)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='保养计划模板项目表';

CREATE TABLE `mt_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '机构ID',
  `name` varchar(40) DEFAULT NULL COMMENT '模板名称',
  `type` int(1) DEFAULT NULL COMMENT '模板类型(1=系统模板，2=自定义模板)',
  `remarks` varchar(40) DEFAULT NULL COMMENT '备注',
  `enable` bit(1) DEFAULT NULL COMMENT '状态(0=停用,1=启用)',
  `del_flag` bit(1) DEFAULT NULL COMMENT '删除标记(0=未删除,1=已删除)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='保养模板表';

CREATE TABLE `mt_template_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `template_id` bigint(20) DEFAULT NULL COMMENT '模板ID',
  `item_name` varchar(40) DEFAULT NULL COMMENT '项目名称',
  `del_flag` bit(1) DEFAULT NULL COMMENT '删除标记(0=未删除，1=已删除)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='保养模板项目表';

CREATE TABLE `mt_template_item_common` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `item_name` varchar(40) DEFAULT NULL COMMENT '项目名称',
  `del_flag` bit(1) DEFAULT NULL COMMENT '删除标记(0=未删除，1=已删除)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='保养模板通用项目表';

INSERT INTO `mt_template` (`tenant_id`, `name`, `type`, `remarks`, `enable`, `del_flag`, `create_time`, `update_time`) VALUES (NULL, '保养通用模板', '1', '系统通用模板', b'1', b'0', now(), now());
INSERT INTO `mt_template_item` (`template_id`, `item_name`, `del_flag`, `create_time`, `update_time`) VALUES ('1', '日常清洁保养', b'0', now(), now());
INSERT INTO `mt_template_item` (`template_id`, `item_name`, `del_flag`, `create_time`, `update_time`) VALUES ('1', '仪器时间准确', b'0', now(), now());
INSERT INTO `mt_template_item` (`template_id`, `item_name`, `del_flag`, `create_time`, `update_time`) VALUES ('1', '仪器标签完整', b'0', now(), now());
INSERT INTO `mt_template_item` (`template_id`, `item_name`, `del_flag`, `create_time`, `update_time`) VALUES ('1', '仪器附件齐全', b'0', now(), now());
INSERT INTO `mt_template_item` (`template_id`, `item_name`, `del_flag`, `create_time`, `update_time`) VALUES ('1', '仪器状态正常', b'0', now(), now());

INSERT INTO `mt_template_item_common` (`item_name`, `del_flag`, `create_time`, `update_time`) VALUES ('仪器状态正常', b'0', now(), now());
INSERT INTO `mt_template_item_common` (`item_name`, `del_flag`, `create_time`, `update_time`) VALUES ('日常清洁消毒保养', b'0', now(), now());
INSERT INTO `mt_template_item_common` (`item_name`, `del_flag`, `create_time`, `update_time`) VALUES ('仪器附件齐全', b'0', now(), now());
INSERT INTO `mt_template_item_common` (`item_name`, `del_flag`, `create_time`, `update_time`) VALUES ('仪器标签完整', b'0', now(), now());
INSERT INTO `mt_template_item_common` (`item_name`, `del_flag`, `create_time`, `update_time`) VALUES ('仪器时间准确', b'0', now(), now());
INSERT INTO `mt_template_item_common` (`item_name`, `del_flag`, `create_time`, `update_time`) VALUES ('供电电源是否满足', b'0', now(), now());
INSERT INTO `mt_template_item_common` (`item_name`, `del_flag`, `create_time`, `update_time`) VALUES ('接地是良好', b'0', now(), now());
INSERT INTO `mt_template_item_common` (`item_name`, `del_flag`, `create_time`, `update_time`) VALUES ('环境温湿度正常', b'0', now(), now());
INSERT INTO `mt_template_item_common` (`item_name`, `del_flag`, `create_time`, `update_time`) VALUES ('参数和性能校对', b'0', now(), now());
INSERT INTO `mt_template_item_common` (`item_name`, `del_flag`, `create_time`, `update_time`) VALUES ('开机前清洁与检查', b'0', now(), now());

