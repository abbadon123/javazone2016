package no.javazone.sleep;

import no.javazone.StatsPrinter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ExecutorNonBlockingSleep {
    public static void main(String[] args) throws Exception {
        ExecutorService ex = Executors.newFixedThreadPool(2000);
        int num = 10_000;
        CountDownLatch done = new CountDownLatch(num);
        long t = System.currentTimeMillis();
        IntStream.range(0, num).forEach(i -> ex.submit(() -> {
            NonBlockingSleeper.sleep(1000);
            done.countDown();
        }));
        done.await(15, TimeUnit.SECONDS);
        System.out.println("Executor non-blocking sleep "  + StatsPrinter.stats(t) + " with " + done.getCount() + " remaining tasks");
    }
}