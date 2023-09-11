package extension;

import interfaces.MyQueue;
import interfaces.Producer;

public class ProducerImpl<T> implements Producer<T> {
    MyQueue<T> queue;
    T[] objects;
    private int number = 0;

    public ProducerImpl(MyQueue<T> _queue, T[] _objects){
        System.out.println("Producer obj start");
        queue = _queue;
        objects = _objects;
    }

    @Override
    public void run() {
        System.out.println("Producer Thread start");
        try{
            int length = objects.length;
            for(int i=0;i<length;++i) {
                queue.push(objects[i]);
                number++;
            }

        }catch(Exception err){
            System.out.println("Producer Thread Err: " + err);
        }


    }
}
