package extension;

import interfaces.Consumer;
import interfaces.MyQueue;

import java.beans.IntrospectionException;

public class ConsumerImpl<T> implements Consumer<T> {
    MyQueue<T> queue;
    static boolean work;

    public ConsumerImpl(MyQueue<T> _queue){
        System.out.println("Consumer obj start");
        queue = _queue;
        work = false;
    }
    @Override
    public void consume() {
        try{
        System.out.println("Consume method called");
        queue.pop();
        } catch (Exception err){
            System.out.println("Consumer consume Err: " + err);
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        System.out.println("Consumer Thread start");
        work = true;
        try{
            for(int i=0;i<1000;++i) {
                Thread.sleep(2000);
                this.consume();
                if(!work) {
                    System.out.println("Consumer Thread end");
                    throw new IntrospectionException("Consumer Thread end");
                }
            }
        } catch (Exception err){
            System.out.println("Consumer Thread Err: " + err);
            Thread.currentThread().interrupt();
        }

    }
}
