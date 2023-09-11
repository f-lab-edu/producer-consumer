package extension;

import interfaces.Consumer;
import interfaces.MyQueue;

import java.beans.IntrospectionException;

public class ConsumerImpl<T> implements Consumer<T> {
    MyQueue<T> queue;

    public ConsumerImpl(MyQueue<T> _queue){
        System.out.println("Consumer obj start");
        queue = _queue;
    }
    @Override
    public void run() {
        System.out.println("Consumer Thread start");
        try{
            for(int i=0;i<1000;++i) {
                Thread.sleep(2000);
                queue.pop();
                System.out.println("Consumer Thread end");
                throw new IntrospectionException("Consumer Thread end");
            }
        } catch (Exception err){
            System.out.println("Consumer Thread Err: " + err);
            Thread.currentThread().interrupt();
        }

    }
}
