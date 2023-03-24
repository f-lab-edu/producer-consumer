package test;

import extension.ConsumerImpl;
import extension.MyQueueImpl;
import extension.ProducerImpl;
import interfaces.Consumer;
import interfaces.MyQueue;
import interfaces.Producer;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class TestFacade {
    Consumer<Integer> consumer;
    Producer<Integer> producer;
    MyQueue<Integer> myQueue;

    @Setup(Level.Trial)
    public void init(){
        myQueue = new MyQueueImpl<>();
    }


    @Benchmark
    public void testSynchronization_IfDefinitionIsRight_Success() throws InterruptedException {
        // example array given
        Integer[] integersInput  = {13,14,65,456,31,83,1331,65456};

        producer = new ProducerImpl<>(myQueue, integersInput);
        consumer = new ConsumerImpl<>(myQueue);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();
    }
    @Test
    public void runBenchmarks() throws Exception {
        Options options = new OptionsBuilder()
                .include(this.getClass().getName() + ".*")
                .mode(Mode.SingleShotTime)
                .threads(2)
                .measurementIterations(6)
                .forks(5)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .build();

        new Runner(options).run();
    }
}
