package com.example.shardingtable.script;

/**
 * Created by liuzz on 2018/05/31
 */
public class SqlScript {

    public static void main(String[] args) {

        String sql = "DROP TABLE IF EXISTS `tp_knowledge_0`;" +
                " CREATE TABLE `tp_knowledge_0` (\n" +
                "  `ID` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',\n" +
                "  `KNOWLEDGE_NO` bigint(20) DEFAULT NULL COMMENT '知识点编号',\n" +
                "  `KNOWLEDGE_NAME` varchar(100) DEFAULT NULL COMMENT '知识名称',\n" +
                "  `KNOWLEDGE_IMG` text COMMENT '知识点图片地址',\n" +
                "  `KNOWLEDGE_STATUS` char(1) DEFAULT NULL COMMENT '状态，1_归档，2_过期，3_发布中，4_隐藏，5_删除',\n" +
                "  `DATE_CREATED` datetime DEFAULT NULL COMMENT '创建时间',\n" +
                "  `LAST_UPDATED` datetime DEFAULT NULL COMMENT '最后修改时间',\n" +
                "  PRIMARY KEY (`ID`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';";



        for (int i = 0; i < 10; i++) {
            System.out.println(sql.replaceAll("tp_knowledge_0", "tp_knowledge_" + i));
        }


    }
}
