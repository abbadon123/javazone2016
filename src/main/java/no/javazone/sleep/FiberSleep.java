package no.javazone.sleep;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.strands.SuspendableRunnable;
import no.javazone.StatsPrinter;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class FiberSleep {
    public static void main(String[] args) throws Exception {
        int num = 1000_000;
        CountDownLatch done = new CountDownLatch(num);
        long t = System.currentTimeMillis();
        IntStream.range(0, num).forEach(i -> new Fiber<>("ECHO", (SuspendableRunnable) () -> {
            Fiber.sleep(1000);
            done.countDown();
        }).start());
        done.await();
        System.out.println("Fibers sleeping " + StatsPrinter.stats(t));
    }
}