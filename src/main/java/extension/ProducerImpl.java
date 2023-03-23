package extension;

import interfaces.MyQueue;
import interfaces.Producer;

public class ProducerImpl<T> implements Producer<T> {
    MyQueue<T> queue;
    T[] objects;

    public ProducerImpl(MyQueue<T> _queue, T[] _objects){
        System.out.println("Producer obj start");
        queue = _queue;
        objects = _objects;
    }
    @Override
    public void send(T object) throws InterruptedException {
        System.out.println("Producer method called");
        queue.push(object);
    }

    @Override
    public void run() {
        System.out.println("Producer Thread start");
        try{
            int length = objects.length;
            for(int i=0;i<length;++i) {
                this.send(objects[i]);
                Thread.sleep(1000);
            }

        }catch(Exception err){
            System.out.println("Producer Thread Err: " + err);
        }


    }
}
