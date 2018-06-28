package io.netty.example.definitive.futurepromise;

import java.util.concurrent.*;

/**
 * Created by liuzz on 2018/06/27
 */
public class JavaFuture {

    public static void main(String[] args) {
        future();
    }

    private static void future() {
        ExecutorService executor = Executors.newCachedThreadPool();

        // 异步 提交-Callable
        Future<String> future = executor.submit(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
            return "Result<>";
        });

        System.out.println("idDone:" + future.isDone());
        boolean cancelResult = future.cancel(true);
        System.out.println("cancelResult:" + cancelResult);
        System.out.println("isCancelled:" + future.isCancelled());

        // 同步
        displayOtherThings(); // do other things while searching
        try {
            // 异步转同步
            System.out.println("result:" + future.get());
        } catch (InterruptedException | ExecutionException | CancellationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void futureTask() {
        ExecutorService executor = Executors.newCachedThreadPool();

        FutureTask<String> futureTask = new FutureTask<>(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
            return "Hello World!";
        });
        executor.submit(futureTask);

        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void displayOtherThings() {
        System.out.println("displayOtherThings");
    }
}
