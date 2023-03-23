package application;

import extension.ConsumerImpl;
import extension.MyQueueImpl;
import extension.ProducerImpl;
import interfaces.Consumer;
import interfaces.MyQueue;
import interfaces.Producer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestFacade {
    Consumer<Integer> consumer;
    Producer<Integer> producer;
    MyQueue<Integer> myQueue;

    @BeforeEach
    public void init(){
        myQueue = new MyQueueImpl<>();
    }


    @Test
    public void testSynchronization_IfDefinitionIsRight_Success() throws InterruptedException {
        // example array given
        Integer[] integersInput  = {13,14,65,456,31,83,1331,65456};

        producer = new ProducerImpl<>(myQueue, integersInput);
        consumer = new ConsumerImpl<>(myQueue);
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        producerThread.start();
        consumerThread.start();
    }
}
