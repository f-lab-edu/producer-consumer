package extension;

import interfaces.MyQueue;
import interfaces.Producer;

public class ProducerImpl<T> implements Producer<T> {
    @Override
    public void send(MyQueue<T> queue, T obj) throws InterruptedException {
        queue.push(obj);
    }
}
