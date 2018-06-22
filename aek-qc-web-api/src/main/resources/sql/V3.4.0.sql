#质控检测单表
CREATE TABLE `md_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '质控检测主键',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '机构id',
  `md_num` varchar(30) DEFAULT NULL COMMENT '质控检测单号',
  `md_archive_num` varchar(30) DEFAULT NULL COMMENT '质控检测档案号',
  `status` int(5) DEFAULT NULL COMMENT '质检单状态(1=暂存、2=待审核、3=审核通过、4=审核未通过、5=已撤销)',
  `contact_number` varchar(40) DEFAULT NULL COMMENT '联系电话',
  `check_according` varchar(255) DEFAULT NULL COMMENT '检测依据',
  `environment_condition` varchar(255) DEFAULT NULL COMMENT '环境条件',
  `deviation_record` varchar(500) DEFAULT NULL COMMENT '偏离情况记录',
  `check_instrument_name` varchar(50) DEFAULT NULL COMMENT '检测仪器(标准器)名称',
  `check_instrument_spec` varchar(50) DEFAULT NULL COMMENT '检测仪器规格型号',
  `check_instrument_producer` varchar(50) DEFAULT NULL COMMENT '检测仪器生产商',
  `check_type` int(5) DEFAULT NULL COMMENT '质控检测类型(1=验收检测、2=状态性检测、3=稳定性检测)',
  `check_result` tinyint(1) DEFAULT NULL COMMENT '检测结论(0=不合格、1=合格)',
  `check_time` datetime DEFAULT NULL COMMENT '检测日期',
  `check_by` bigint(20) DEFAULT NULL COMMENT '检测人',
  `check_name` varchar(50) DEFAULT NULL COMMENT '检测人姓名',
  `verify_result` tinyint(1) DEFAULT NULL COMMENT '审核结果(0=审核未通过、1=审核通过)',
  `verify_time` datetime DEFAULT NULL COMMENT '审核日期',
  `verify_by` bigint(20) DEFAULT NULL COMMENT '审核人',
  `verify_name` varchar(50) DEFAULT NULL COMMENT '审核人姓名',
  `verify_remark` varchar(500) DEFAULT NULL COMMENT '审核备注',
  `appearance_work_check` varchar(500) DEFAULT NULL COMMENT '外观及工作正常性检查',
  `maximum_allowable_error` json DEFAULT NULL COMMENT '最大允差',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新日期',
  `del_flag` bit(1) DEFAULT b'0' COMMENT '作废标识(0=未作废、1=作废)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='质控检测单表';

#质控检测设备信息表
CREATE TABLE `md_report_assets` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `md_report_id` bigint(20) DEFAULT NULL COMMENT '质控报告ID',
  `assets_id` bigint(20) DEFAULT NULL COMMENT '设备id',
  `assets_name` varchar(50) DEFAULT NULL COMMENT '设备名称',
  `assets_num` varchar(50) DEFAULT NULL COMMENT '设备编号',
  `assets_spec` varchar(50) DEFAULT NULL COMMENT '设备规格型号',
  `factory_num` varchar(50) DEFAULT NULL COMMENT '出厂编号',
  `factory_name` varchar(50) DEFAULT NULL COMMENT '厂家名称',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '设备所在部门',
  `dept_name` varchar(50) DEFAULT NULL COMMENT '所在部门',
  `del_flag` bit(1) DEFAULT b'0' COMMENT '作废标识(0=未作废、1=作废)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='质控检测设备信息表';

#质控报告模板表
CREATE TABLE `md_report_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `source_template_id` bigint(20) DEFAULT NULL COMMENT '系统模板ID',
  `md_report_id` bigint(20) DEFAULT NULL COMMENT '质控报告ID',
  `name` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '模板名称',
  `remarks` varchar(300) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `enable` bit(1) DEFAULT NULL COMMENT '启用标记(0=停用，1=启用)',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='质控报告模板表';

#质控报告模板子项目表
CREATE TABLE `md_report_template_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `source_item_id` bigint(20) DEFAULT NULL COMMENT '系统项目ID',
  `report_template_id` bigint(20) DEFAULT NULL COMMENT '质控模板ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父项目ID',
  `name` varchar(12) COLLATE utf8_bin DEFAULT NULL COMMENT '项目名称',
  `remarks` varchar(12) COLLATE utf8_bin DEFAULT NULL COMMENT '项目备注',
  `input_type` int(1) DEFAULT NULL COMMENT '输入类型(1=文本，2=选择)',
  `columns` int(11) DEFAULT NULL COMMENT '列数(type=子项目,input_type=文本)',
  `cross_row` bit(1) DEFAULT NULL COMMENT '跨行显示(0=不跨行,1=跨行显示)(type=子项目备注，input_type=文本)',
  `type` int(1) DEFAULT NULL COMMENT '项目类型(1=子项目,2=子项目备注)',
  `select_type` int(1) DEFAULT NULL COMMENT '选择类型(1=单选，2=多选)(input_type=选择)',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='质控报告模板子项目表';

#质控模板项目选项表
CREATE TABLE `md_report_template_item_option` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `source_item_option_id` int(11) DEFAULT NULL COMMENT '系统选项ID',
  `item_id` bigint(20) DEFAULT NULL COMMENT '报告模板项目ID',
  `name` varchar(12) DEFAULT NULL COMMENT '项目名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='质控模板项目选项表';

#质控报告模板子项目结果表
CREATE TABLE `md_report_template_item_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `item_id` bigint(20) DEFAULT NULL COMMENT '质控模板项目ID',
  `result` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '项目结果',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='质控报告模板子项目结果表';

#质控检测系统模板表
CREATE TABLE `md_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '模板名称',
  `remarks` varchar(300) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `enable` bit(1) DEFAULT NULL COMMENT '启用标记(0=停用，1=启用)',
  `release_flag` bit(1) DEFAULT b'0' COMMENT '发布状态(0=待发布，1=已发布)',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` bit(1) DEFAULT NULL COMMENT '删除标记(0=未删除，1=已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='质控检测系统模板表';

#质控检测系统模板子项目表
CREATE TABLE `md_template_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `template_id` bigint(20) DEFAULT NULL COMMENT '模板ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父项目ID',
  `name` varchar(12) COLLATE utf8_bin DEFAULT NULL COMMENT '项目名称',
  `remarks` varchar(12) COLLATE utf8_bin DEFAULT NULL COMMENT '项目备注',
  `input_type` int(1) DEFAULT NULL COMMENT '输入类型(1=文本，2=选择)',
  `columns` int(11) DEFAULT NULL COMMENT '列数(type=子项目,input_type=文本)',
  `cross_row` bit(1) DEFAULT NULL COMMENT '跨行显示(0=不跨行,1=跨行显示)(type=子项目备注，input_type=文本)',
  `type` int(1) DEFAULT NULL COMMENT '项目类型(1=子项目,2=子项目备注)',
  `select_type` int(1) DEFAULT NULL COMMENT '选择类型(1=单选，2=多选)(input_type=选择)',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='质控检测系统模板子项目表';

#质控模板项目选项表
CREATE TABLE `md_template_item_option` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `item_id` bigint(20) DEFAULT NULL COMMENT '项目ID',
  `name` varchar(12) DEFAULT NULL COMMENT '项目名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='质控模板项目选项表';