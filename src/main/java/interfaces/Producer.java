package interfaces;

public interface Producer<T> {
    public void send(MyQueue<T> queue, T obj) throws InterruptedException;
}
