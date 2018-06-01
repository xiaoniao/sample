package com.example.shardingtable.script;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzz on 2018/05/31
 */
public class SqlScript {

    public static class Table {
        String name;
        String dropIfExists;
        String createTable;
    }

    public static void main(String[] args) {
        List<Table> sql = new ArrayList<>();
        sql.add(knowledge());
        sql.add(stage());
        sql.add(student());
        sql.add(study());
        sql.add(studyAssignment());
        sql.add(studyknowledgeProcess());
        sql.add(studyProcess());
        sql.add(studyStage());
        sql.add(studyStageKnowledge());

        for (Table t : sql) {
            for (int partition = 0; partition < 2; partition++) {
                System.out.println(t.dropIfExists.replaceAll(t.name, t.name + "_" + partition));
                System.out.println(t.createTable.replaceAll(t.name, t.name + "_" + partition));
            }
        }
    }

    private static Table knowledge() {
        Table table = new Table();
        table.name = "tp_knowledge";
        table.dropIfExists = "DROP TABLE IF EXISTS `tp_knowledge`;";
        table.createTable = "CREATE TABLE `tp_knowledge` (\n" +
                "  `ID` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',\n" +
                "  `KNOWLEDGE_NO` bigint(20) DEFAULT NULL COMMENT '知识点编号',\n" +
                "  `KNOWLEDGE_NAME` varchar(100) DEFAULT NULL COMMENT '知识名称',\n" +
                "  `KNOWLEDGE_IMG` text COMMENT '知识点图片地址',\n" +
                "  `KNOWLEDGE_STATUS` char(1) DEFAULT NULL COMMENT '状态，1_归档，2_过期，3_发布中，4_隐藏，5_删除',\n" +
                "  `DATE_CREATED` datetime DEFAULT NULL COMMENT '创建时间',\n" +
                "  `LAST_UPDATED` datetime DEFAULT NULL COMMENT '最后修改时间',\n" +
                "  PRIMARY KEY (`ID`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=1220 DEFAULT CHARSET=utf8 COMMENT='资源表';";
        return table;
    }

    private static Table stage() {
        Table table = new Table();
        table.name = "tp_stage";
        table.dropIfExists = "DROP TABLE IF EXISTS `tp_stage`;";
        table.createTable = "CREATE TABLE `tp_stage` (\n" +
                "  `ID` bigint(20) NOT NULL DEFAULT '0',\n" +
                "  `STAGE_NAME` varchar(30) DEFAULT NULL COMMENT '阶段名称',\n" +
                "  `STAGE_DESC` varchar(100) DEFAULT NULL COMMENT '阶段简介',\n" +
                "  `DATE_CREATED` datetime DEFAULT NULL,\n" +
                "  `LAST_UPDATED` datetime DEFAULT NULL,\n" +
                "  PRIMARY KEY (`ID`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务阶段表';";
        return table;
    }

    private static Table student() {
        Table table = new Table();
        table.name = "tp_student";
        table.dropIfExists = "DROP TABLE IF EXISTS `tp_student`;";
        table.createTable = "CREATE TABLE `tp_student` (\n" +
                "  `STUDENT_NO` bigint(20) NOT NULL COMMENT '学员编号',\n" +
                "  `NAME` varchar(20) DEFAULT NULL COMMENT '学员名称',\n" +
                "  `STATUS` tinyint(4) DEFAULT '1' COMMENT '是否可用，0_已禁用,1_已启用',\n" +
                "  `DATE_CREATED` datetime DEFAULT NULL COMMENT '创建日期',\n" +
                "  `LAST_UPDATED` datetime DEFAULT NULL COMMENT '最近更新时间',\n" +
                "  PRIMARY KEY (`STUDENT_NO`),\n" +
                "  UNIQUE KEY `tp_student_STUDENT_NO_uindex` (`STUDENT_NO`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学员表';";
        return table;
    }

    private static Table study() {
        Table table = new Table();
        table.name = "tp_study";
        table.dropIfExists = "DROP TABLE IF EXISTS `tp_study`;";
        table.createTable = "CREATE TABLE `tp_study` (\n" +
                "  `STUDY_NO` bigint(20) NOT NULL DEFAULT '0' COMMENT '学习任务编号',\n" +
                "  `NAME` varchar(40) DEFAULT NULL COMMENT '学习计划名称',\n" +
                "  `STATUS` char(1) DEFAULT '0' COMMENT '状态,-1 删除, 0_下架, 1_已发布,2_已结束,3_已删除,4_待发布',\n" +
                "  `DATE_CREATED` datetime DEFAULT NULL COMMENT '创建时间',\n" +
                "  `LAST_UPDATED` datetime DEFAULT NULL COMMENT '最后一次修改时间',\n" +
                "  PRIMARY KEY (`STUDY_NO`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务表';";
        return table;
    }

    private static Table studyAssignment() {
        Table table = new Table();
        table.name = "tp_study_assignment";
        table.dropIfExists = "DROP TABLE IF EXISTS `tp_study_assignment`;";
        table.createTable = "CREATE TABLE `tp_study_assignment` (\n" +
                "  `ID` bigint(20) NOT NULL DEFAULT '0',\n" +
                "  `STUDY_NO` bigint(20) DEFAULT NULL,\n" +
                "  `STUDENT_NO` bigint(20) DEFAULT NULL,\n" +
                "  `STATUS` int(11) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`ID`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        return table;
    }

    private static Table studyknowledgeProcess() {
        Table table = new Table();
        table.name = "tp_study_knowledge_process";
        table.dropIfExists = "DROP TABLE IF EXISTS `tp_study_knowledge_process`;";
        table.createTable = "CREATE TABLE `tp_study_knowledge_process` (\n" +
                "  `ID` bigint(20) NOT NULL COMMENT '主键',\n" +
                "  `STUDY_NO` bigint(20) DEFAULT NULL COMMENT '学习编号',\n" +
                "  `STUDENT_NO` bigint(20) DEFAULT NULL COMMENT '学员编号',\n" +
                "  `KNOWLEDGE_NO` bigint(20) DEFAULT NULL COMMENT '知识点编号',\n" +
                "  `FINISH_PERCENT` int(5) DEFAULT NULL COMMENT '完成百分比%',\n" +
                "  `STATUS` char(1) DEFAULT '0' COMMENT '知识点状态,0_未开始,1_学习中,2_已完成未领取,3_已领取',\n" +
                "  `DATE_CREATED` datetime DEFAULT NULL COMMENT '创建时间',\n" +
                "  `LAST_UPDATED` datetime DEFAULT NULL COMMENT '最后一次修改时间',\n" +
                "  PRIMARY KEY (`ID`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学员资源学习进度表';";
        return table;
    }

    private static Table studyProcess() {
        Table table = new Table();
        table.name = "tp_study_process";
        table.dropIfExists = "DROP TABLE IF EXISTS `tp_study_process`;";
        table.createTable = "CREATE TABLE `tp_study_process` (\n" +
                "  `ID` bigint(20) NOT NULL COMMENT '主键',\n" +
                "  `STUDY_NO` bigint(20) DEFAULT NULL COMMENT '学习计划编号',\n" +
                "  `STUDENT_NO` bigint(20) DEFAULT NULL COMMENT '学员编号',\n" +
                "  `FINISH_PERCENT` int(5) DEFAULT NULL COMMENT '完成百分比%',\n" +
                "  `STATUS` char(1) DEFAULT NULL COMMENT '学习状态,0_未开始,1_学习中,2_已完成,3_已过期',\n" +
                "  `DATE_CREATED` datetime DEFAULT NULL COMMENT '创建时间',\n" +
                "  `LAST_UPDATED` datetime DEFAULT NULL COMMENT '最后一次修改时间',\n" +
                "  PRIMARY KEY (`ID`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学员学习表';";
        return table;
    }

    private static Table studyStage() {
        Table table = new Table();
        table.name = "tp_study_stage";
        table.dropIfExists = "DROP TABLE IF EXISTS `tp_study_stag`;";
        table.createTable = "CREATE TABLE `tp_study_stage` (\n" +
                "  `ID` bigint(20) NOT NULL COMMENT '主键',\n" +
                "  `STUDY_NO` bigint(20) DEFAULT NULL COMMENT '学习编号',\n" +
                "  `STAGE_ID` bigint(20) DEFAULT NULL COMMENT '阶段id',\n" +
                "  `SORT_NUM` int(5) DEFAULT NULL COMMENT '阶段序号',\n" +
                "  `DATE_CREATED` datetime DEFAULT NULL COMMENT '创建时间',\n" +
                "  `LAST_UPDATED` datetime DEFAULT NULL COMMENT '最后一次修改时间',\n" +
                "  PRIMARY KEY (`ID`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学习阶段关系表';";
        return table;
    }

    private static Table studyStageKnowledge() {
        Table table = new Table();
        table.name = "tp_study_stage_knowledge";
        table.dropIfExists = "DROP TABLE IF EXISTS `tp_study_stage_knowledge`;";
        table.createTable = "CREATE TABLE `tp_study_stage_knowledge` (\n" +
                "  `ID` bigint(20) NOT NULL COMMENT '主键',\n" +
                "  `STAGE_ID` bigint(20) DEFAULT NULL COMMENT '阶段id',\n" +
                "  `KNOWLEDGE_NO` bigint(20) DEFAULT NULL COMMENT '知识点编号',\n" +
                "  `DATE_CREATED` datetime DEFAULT NULL COMMENT '创建时间',\n" +
                "  `LAST_UPDATED` datetime DEFAULT NULL COMMENT '最后一次修改时间',\n" +
                "  PRIMARY KEY (`ID`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阶段资源关系表';";
        return table;
    }

}
