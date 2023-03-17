package com.ning.table_sql;


public class PartnerGrowthValueCreator extends SqlCreator{

    public static void main(String[] args) {
        new PartnerGrowthValueCreator().printSqlByLength(90);
    }

    @Override
    protected String createSql() {
        return "\n" +
                "create table t_tz_growth_value_record_@values(\n" +
                "    order_id bigint(32) not null comment '成长值增加的订单id',\n" +
                "    uid bigint(32) not null comment '用户uid',\n" +
                "    level_code bigint(32) null comment '用户当前等级编码',\n" +
                "    level_name varchar(64) null comment '用户当前等级名称',\n" +
                "    task_id bigint(32) null comment '用户的任务id',\n" +
                "    task_template_id bigint(32) null comment '用户的任务模板id',\n" +
                "    task_name varchar(64) null comment '任务名称',\n" +
                "    incr int(8) null comment '本次任务完成的成长值增量',\n" +
                "    cur_growth_value bigint(32) null comment '本次增加后用户的当前总成长值',\n" +
                "    create_time datetime null comment '创建时间',\n" +
                "    update_time datetime null comment '最后一次修改时间',\n" +
                "    PRIMARY KEY (`order_id`),\n" +
                "    KEY idx_uid_template_id_create_time (uid, task_template_id, create_time)\n" +
                ") ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '合伙人体系-成长值模块-成长值明细表';\n" +
                "create table t_tz_growth_value_summary_@values (\n" +
                "    id bigint(32) auto_increment comment '主键',\n" +
                "    uid bigint(32) not null comment '用户uid',\n" +
                "    task_template_id bigint(32) null comment '任务模板id',\n" +
                "    sum bigint(12) null comment '成长值总和',\n" +
                "    create_time datetime null comment '记录创建时间',\n" +
                "    update_time datetime null comment '记录最后一次更新时间',\n" +
                "    PRIMARY KEY (`id`),\n" +
                "    KEY idx_uid_date_template_id(uid, task_template_id)\n" +
                ") ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '合伙人体系-成长值模块-成长值汇总表';\n";
    }
}
