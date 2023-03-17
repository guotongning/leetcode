package com.ning.table_sql;

public class FlowUvCreator extends SqlCreator {

    public static void main(String[] args) {
        FlowUvCreator creator = new FlowUvCreator();
//        creator.printSqlByLength(30);
        creator.createSqlByDtRange("20230401", "20230831");
    }

    @Override
    protected String createSql() {
        return "CREATE TABLE `tz_flow_uv_job_@values`\n" +
                "(\n" +
                "    `id`          bigint(20) NOT NULL AUTO_INCREMENT,\n" +
                "    `uid`         bigint(20) NOT NULL COMMENT '分享者uid',\n" +
                "    `client_type` int(4) DEFAULT '0' COMMENT '端类型',\n" +
                "    `open_id`     varchar(128) NOT NULL COMMENT '贡献者openId',\n" +
                "    `create_time` datetime DEFAULT NULL COMMENT '创建时间',\n" +
                "    `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',\n" +
                "    PRIMARY KEY (`id`),\n" +
                "    UNIQUE KEY `uniq_uid_open_id_client_type` (`uid`,`open_id`,`client_type`) USING BTREE\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='uv表-招聘业务线';\n" +
                "CREATE TABLE `tz_flow_uv_news_@values`\n" +
                "(\n" +
                "    `id`          bigint(20) NOT NULL AUTO_INCREMENT,\n" +
                "    `uid`         bigint(20) NOT NULL COMMENT '分享者uid',\n" +
                "    `client_type` int(4) DEFAULT '0' COMMENT '端类型',\n" +
                "    `open_id`     varchar(128) NOT NULL COMMENT '贡献者openId',\n" +
                "    `create_time` datetime DEFAULT NULL COMMENT '创建时间',\n" +
                "    `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',\n" +
                "    PRIMARY KEY (`id`),\n" +
                "    UNIQUE KEY `uniq_uid_open_id_client_type` (`uid`,`open_id`,`client_type`) USING BTREE\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='uv表-资讯业务线';\n";
    }
}
