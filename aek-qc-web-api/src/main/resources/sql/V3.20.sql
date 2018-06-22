ALTER TABLE `qc_implement`
ADD COLUMN `tenant_id`  bigint(20) NULL COMMENT '机构ID' AFTER `id`;

ALTER TABLE `qc_implement`
ADD COLUMN `next_time` datetime DEFAULT NULL COMMENT '下次巡检时间';

update qc_implement q,qc_plan c  set q.tenant_id=c.tenant_id where q.paln_id=c.id;
update qc_implement q,qc_plan c  set q.tenant_id=c.tenant_id,q.next_time=q.actual_start_date where q.paln_id=c.id;

ALTER TABLE `qc_assets`
ADD COLUMN `tenant_id`  bigint(20) NULL COMMENT '机构ID' AFTER `id`;

update qc_assets q,qc_implement c  set q.tenant_id=c.tenant_id where q.implement_id=c.id;
