ALTER TABLE `qc_plan`
ADD COLUMN `msg_status`  int(1) NULL COMMENT '是否可以发送消息（1，是 2，不）' AFTER `del_flag`;

UPDATE  `qc_plan`  set `msg_status`=1;

ALTER TABLE `pm_plan_implement_help`
ADD COLUMN `msg_status`  int(1) NULL COMMENT '是否可以发送消息（1，是 2，不）' AFTER `del_flag`;

UPDATE  `pm_plan_implement_help`  set `msg_status`=1;
