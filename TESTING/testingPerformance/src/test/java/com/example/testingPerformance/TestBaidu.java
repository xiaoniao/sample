package com.example.testingPerformance;

import com.example.testingPerformance.model.GetRequest;
import com.example.testingPerformance.model.PostRequest;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by liuzz on 2018/06/28
 */
public class TestBaidu {

    ExecutorService executorService = Executors.newFixedThreadPool(1000);

    OkHttpClient client = new OkHttpClient();


    private List<GetRequest> generateRequestData() {
        List<GetRequest> result = new ArrayList<>();
        String url = "http://localhost:8080/sample";
        for (int i = 0; i < 10000000; i++) {
            result.add(GetRequest.create(url));
        }
        return result;
    }

    @Test
    public void testUrl2() throws IOException {
        for (int i = 0; i < 1000; i++) {
            long startTime = System.currentTimeMillis();
            String result = get(GetRequest.create("http://localhost:8080/sample" + "?ss=" + i));
            System.out.println(result + " - " + (System.currentTimeMillis() - startTime));
        }
    }


    @Test
    public void testUrlWithThread() throws IOException {
        List<Future> futures = new ArrayList<>();
        List<GetRequest> getRequests = generateRequestData();

        final long startTime = System.currentTimeMillis();
        getRequests.forEach(request -> {
            Future future = executorService.submit(() -> {
                try {
                    get(request);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "failure";
                }
                return "success-" + System.currentTimeMillis();
            });
            futures.add(future);
        });

        System.out.println("已全部提交到线程池中");
        int requestCount = futures.size();
        int failureCount = 0;


        Map<String, Long> durationMap = new HashMap<>();

        while (futures.size() != 0) {
            for (int j = futures.size() - 1; j >= 0; j--) {
                Future<String> future = futures.get(j);
                if (future.isDone()) {
                    futures.remove(j);
                    try {
                        if (!future.get().startsWith("success")) {
                            failureCount++;
                        } else {
                            long t2 = Long.valueOf(future.get().split("-")[1]);

                            System.out.println(t2 - startTime);

                            String key = String.valueOf((t2 - startTime) / 1000);
                            durationMap.put(key, durationMap.get(key) == null ? 1l : durationMap.get(key) + 1);
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }

                if (future.isCancelled()) {
                    futures.remove(j);
                }
            }
        }

        for (String key : durationMap.keySet()) {
            System.out.println("time:" + key + "s+ count:" + durationMap.get(key));
        }
        double time = (System.currentTimeMillis() - startTime) / 1000.0;
        System.out.println("请求次数:" + requestCount + " 失败次数:" + failureCount +" 耗时:" + time + " QPS:" + (requestCount / time));

        // 时间都花费了在哪里？？tomcat 200个线程 队列等待  一个线程执行多少时间？ 线程上下文切换
    }

    private String get(GetRequest getRequest) throws IOException {
        Request request = new Request.Builder()
                .url(getRequest.getUrl())
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private String post(PostRequest postRequest) throws IOException {
        RequestBody body = RequestBody.create(postRequest.JSON, postRequest.getBody());
        Request request = new Request.Builder()
                .url(postRequest.getUrl())
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
