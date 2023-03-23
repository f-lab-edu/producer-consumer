package extension;

import interfaces.Consumer;
import interfaces.MyQueue;

public class ConsumerImpl<T> implements Consumer<T> {
    MyQueue<T> queue;

    public ConsumerImpl(MyQueue<T> _queue){
        System.out.println("Consumer obj start");
        queue = _queue;
    }
    @Override
    public void consume() throws InterruptedException {
        System.out.println("Consume method called");
        queue.pop();
    }

    @Override
    public void run() {
        System.out.println("Consumer Thread start");
        try{
            for(int i=0;i<3;++i) {
                this.consume();
            }

        }catch(Exception err){
            System.out.println("Consumer Thread Err: " + err);
        }

    }
}
