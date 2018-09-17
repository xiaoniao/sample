package com.liuzhuang.thread.forkjoin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 模拟网络请求
 * 注意网络请求工具类需要支持多线程调用
 *
 * Created by liuzhuang on 2018/1/6.
 */
public class NetworkTask extends RecursiveTask<List<NetworkBean>> {

    private static final int THRESHOLD = 1; // 阀值 一个线程只一次网络请求
    private int startPage;
    private int endPage;

    private HashMap<String, Integer> countThreadNameHashMap = new HashMap<>();

    public NetworkTask(int startPage, int endPage) {
        this.startPage = startPage;
        this.endPage = endPage;
    }

    @Override
    protected List<NetworkBean> compute() {
        List<NetworkBean> all = new ArrayList<>();;
        boolean canCompute = endPage - startPage < THRESHOLD;
        if (canCompute) {
            NetworkBean networkBean = new NetworkBean();
            networkBean.setStartPage(startPage);
            networkBean.setEndPage(endPage);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            all.add(networkBean);
        } else {
            int middle = (startPage + endPage) / 2;
            NetworkTask leftCount = new NetworkTask(startPage, middle);
            NetworkTask rightCount = new NetworkTask(middle + 1, endPage);
            leftCount.fork();
            rightCount.fork();
            List<NetworkBean> le = leftCount.join();
            List<NetworkBean> ri = rightCount.join();
            all.addAll(le);
            all.addAll(ri);
        }
        return all;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long startTime = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        NetworkTask countTask = new NetworkTask(1, 100);
        forkJoinPool.submit(countTask);
        List<NetworkBean> result = countTask.get();
//        for (NetworkBean networkBean : result) {
//            System.out.println(networkBean.getStartPage() + " : " + networkBean.getEndPage());
//        }
        System.out.println(System.currentTimeMillis() - startTime); // 3163
    }
}
