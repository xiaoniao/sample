package com.example.kafkaapi.script;

import com.example.kafkaapi.script.bean.Replication;
import com.example.kafkaapi.script.bean.Replications;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 *  增加副本/修改副本
 *
 * ./bin/kafka-reassign-partitions.sh  --zookeeper localhost:2181 --reassignment-json-file replication.json --execute
 *
 * Created by liuzz on 2018/05/28
 */
public class ReassignmentJsonFile {

    public static void main(String[] args) {
        Replications replications = new Replications();
        replications.setVersion(1);

        List<Replication> replicationList = new ArrayList<>();
        for (int i = 0; i < 49; i++) {
            Replication replication = new Replication();
            replication.setPartition(i);
            replication.setTopic("__consumer_offsets");
            replication.setReplicas(Arrays.asList(new Integer[]{0, 1, 2, 3}));
            replicationList.add(replication);
        }
        replications.setPartitions(replicationList);


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(replications));
    }

}
