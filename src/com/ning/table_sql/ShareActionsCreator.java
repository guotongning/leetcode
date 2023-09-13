package com.ning.table_sql;


public class ShareActionsCreator extends SqlCreator {

    public static void main(String[] args) {
        ShareActionsCreator creator = new ShareActionsCreator();
        creator.createSqlByDtRange("20230601", "20230831");
    }

    @Override
    protected String createSql() {
        return "\n" +
                "create table t_tz_share_actions_record_@values\n" +
                "(\n" +
                "    id             bigint auto_increment comment '主键',\n" +
                "    uid            bigint null comment 'uid',\n" +
                "    info_id        bigint null comment '帖子id',\n" +
                "    source         int null comment '帖子来源',\n" +
                "    share_location int null comment '分享位置',\n" +
                "    from_where     varchar(32) null comment '页面来源',\n" +
                "    act_from       varchar(32) null comment '页面来源',\n" +
                "    share_time     datetime null comment '分享时间',\n" +
                "    domain         varchar(256) null comment '分享域名',\n" +
                "    app_id         varchar(128) null comment '分享appId',\n" +
                "    create_time    datetime null comment '记录创建时间',\n" +
                "    PRIMARY KEY (`id`),\n" +
                "    KEY            idx_uid_share_time (`uid`,`share_time`)\n" +
                ") ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = '本地版分享记录表';\n" +
                "\n";
    }
}
