package interfaces;

public interface MyQueue<T> {

    public void push(T obj) throws InterruptedException;
    public void pop() throws InterruptedException;
}
