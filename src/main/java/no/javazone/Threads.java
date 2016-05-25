package no.javazone;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

import static no.javazone.LongRunningTask.*;

public class Threads {

    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(numRuns);
        long t = System.currentTimeMillis();
        IntStream.range(0,numRuns).forEach(i->new Thread(() -> task(countDownLatch)).start());
        countDownLatch.await();
        System.out.println("");
        System.out.println("threads: " + stats(t));
    }

}
