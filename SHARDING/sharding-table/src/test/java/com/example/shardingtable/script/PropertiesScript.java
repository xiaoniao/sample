package com.example.shardingtable.script;

/**
 * Created by liuzz on 2018/06/01
 */
public class PropertiesScript {

    public static void main(String[] args) {


        String pro = "sharding.jdbc.config.sharding.tables.{table}.actual-data-nodes=my_sharding_test.{table}_${0..1}\n" +
                "sharding.jdbc.config.sharding.tables.{table}.table-strategy.standard.sharding-column={key}\n" +
                "sharding.jdbc.config.sharding.tables.{table}.table-strategy.standard.precise-algorithm-class-name=com.example.shardingtable.sharding.DefaultPreciseShardingAlgorithm\n" +
                "sharding.jdbc.config.sharding.tables.{table}.table-strategy.standard.range-algorithm-class-name=com.example.shardingtable.sharding.DefaultRangeShardingAlgorithm\n" +
                "sharding.jdbc.config.sharding.tables.{table}.key-generator-column-name={key}\n" +
                "sharding.jdbc.config.sharding.tables.{table}.key-generator-class-name=io.shardingsphere.core.keygen.DefaultKeyGenerator \n\n\n\n";

        System.out.println(pro.replaceAll("\\{table}", "tp_stage").replaceAll("\\{key}", "ID"));
        System.out.println(pro.replaceAll("\\{table}", "tp_study").replaceAll("\\{key}", "study_no"));
        System.out.println(pro.replaceAll("\\{table}", "tp_study_assignment").replaceAll("\\{key}", "ID"));
        System.out.println(pro.replaceAll("\\{table}", "tp_study_knowledge_process").replaceAll("\\{key}", "ID"));
        System.out.println(pro.replaceAll("\\{table}", "tp_study_process").replaceAll("\\{key}", "ID"));
        System.out.println(pro.replaceAll("\\{table}", "tp_study_stage").replaceAll("\\{key}", "ID"));
        System.out.println(pro.replaceAll("\\{table}", "tp_study_stage_knowledge").replaceAll("\\{key}", "ID"));


    }
}
