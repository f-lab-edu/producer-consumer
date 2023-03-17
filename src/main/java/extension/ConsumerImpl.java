package extension;

import interfaces.Consumer;
import interfaces.MyQueue;

public class ConsumerImpl<T> implements Consumer<T> {
    @Override
    public void consume(MyQueue<T> queue) throws InterruptedException {
        queue.pop();
    }
}
