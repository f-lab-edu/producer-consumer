package extension;

import interfaces.MyQueue;
import interfaces.Producer;

public class ProducerImpl<T> implements Producer<T> {
    MyQueue<T> queue;
    T[] objects;
    public static boolean work;

    public ProducerImpl(MyQueue<T> _queue, T[] _objects){
        System.out.println("Producer obj start");
        queue = _queue;
        objects = _objects;
        work = false;
    }
    @Override
    public void send(T object) {
        try{
        System.out.println("Producer method called");
        queue.push(object);
        } catch(Exception err){
            System.out.println("Producer send Err: " + err);
        }
    }

    @Override
    public void run() {
        System.out.println("Producer Thread start");
        work = true;
        try{
            int length = objects.length;
            for(int i=0;i<length;++i) {
                this.send(objects[i]);
                Thread.sleep(1000);
            }
            work = false;

        }catch(Exception err){
            System.out.println("Producer Thread Err: " + err);
        }


    }
}
