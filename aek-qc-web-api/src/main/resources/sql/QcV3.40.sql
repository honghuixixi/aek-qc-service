
CREATE TABLE `qc_plan_check` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '机构ID',
  `paln_id` bigint(20) DEFAULT NULL COMMENT '关联巡检计划id',
  `implement_id` bigint(20) DEFAULT NULL COMMENT '关联实施ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '验收人ID',
  `user_name` varchar(50) DEFAULT NULL COMMENT '验收人姓名',
  `status` int(1) DEFAULT NULL COMMENT '验收状态（1，待验收 2，已验收）',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` bit(1) DEFAULT b'0' COMMENT '作废标记，0：启用，1：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='qc验收人员表';



ALTER TABLE `qc_implement`
ADD COLUMN `is_check`  int(1) NULL COMMENT '是否验收（1，待验收 2，已验收）' ;
ALTER TABLE `qc_implement`
ADD COLUMN `charge_id`  bigint(20) NULL COMMENT '负责人id' ;
ALTER TABLE `qc_implement`
ADD COLUMN `charge_name`  varchar(50) NULL COMMENT '负责人姓名';

UPDATE qc_implement  set is_check=`status`;
UPDATE qc_implement q,qc_plan p set q.charge_id=p.charge_id,q.charge_name=p.charge_name where q.paln_id=p.id and q.`status`=2;

ALTER TABLE `pm_answers_option`
ADD COLUMN `setnum`  varchar(40) NULL  COMMENT '设置值';

ALTER TABLE `pm_plan`
ADD COLUMN `check_id`  bigint(20) NULL COMMENT '验收人id' ;
ALTER TABLE `pm_plan`
ADD COLUMN `check_name`  varchar(50) NULL COMMENT '验收人姓名';



ALTER TABLE `pm_plan_implement_help`
ADD COLUMN `check_id`  bigint(20) NULL COMMENT '验收人id' ;
ALTER TABLE `pm_plan_implement_help`
ADD COLUMN `check_name`  varchar(50) NULL COMMENT '验收人姓名';

ALTER TABLE `pm_plan_implement`
ADD COLUMN `check_id`  bigint(20) NULL COMMENT '验收人id' ;
ALTER TABLE `pm_plan_implement`
ADD COLUMN `check_name`  varchar(50) NULL COMMENT '验收人姓名';


ALTER TABLE `pm_implement`
ADD COLUMN `is_check`  int(1) NULL COMMENT '是否验收（1，待验收 2，已验收）' ;

UPDATE pm_implement  set is_check=`status`;