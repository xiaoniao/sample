//package com.example.kafkaconsumer;
//
//import org.apache.kafka.streams.kstream.JoinWindows;
//import org.apache.kafka.streams.kstream.TimeWindows;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by liuzz on 2018/05/21
// *
// *
// * 窗口操作一般在连接和聚合等保存本地状态的程序中使用
// *
// *
// * TimeWindows 聚合操作
// *
// * 跳跃时间窗口 【hopping time window】 滑动步长小于窗口大小
// *
// * 翻转时间窗口 【tumbling time window】 滑动步长等于窗口大小  一个数据属于一个窗口
// *
// *
// * JoinWindows 连接操作
// *
// * 滑动时间窗口 【sliding window】 窗口大小固定，沿着时间轴连续滑动
// */
//@Component
//public class WindowSample {
//
//    public static void main(String[] args) {
//
//        joinWindows();
//
//        timeWindows();
//    }
//
//
//    private static void joinWindows() {
//
//        JoinWindows joinWindows = JoinWindows.of(TimeUnit.MINUTES.toMillis(5));
//
//        // before = after = timeDifferenceMs
//
//        System.out.println(joinWindows.beforeMs + " " + joinWindows.afterMs);
//
//
//
//
//        JoinWindows beforeJoinWindows = joinWindows.before(TimeUnit.MINUTES.toMillis(3));
//
//        // before = timeDifferenceMs
//
//        System.out.println(beforeJoinWindows.beforeMs + " " + beforeJoinWindows.afterMs);
//
//
//
//        JoinWindows afterJoinWindows = joinWindows.after(TimeUnit.MINUTES.toMillis(3));
//
//        // after = timeDifferenceMs
//
//        System.out.println(afterJoinWindows.beforeMs + " " + afterJoinWindows.afterMs);
//
//
//
//
//    }
//
//    private static void timeWindows() {
//
//        long windowSizeMs = TimeUnit.MINUTES.toMillis(5);
//
//        TimeWindows timeWindows = TimeWindows.of(windowSizeMs);
//
//        long intervalMs = TimeUnit.MINUTES.toMillis(5);
//
//        TimeWindows advanceWindows = timeWindows.advanceBy(intervalMs);
//
//
//        // windowSizeMs == intervalMs 翻转窗口
//
//        // windowSizeMs > intervalMs 跳跃窗口
//
//
//    }
//
//}
