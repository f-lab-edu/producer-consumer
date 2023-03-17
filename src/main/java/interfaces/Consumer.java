package interfaces;

public interface Consumer<T>{
    public void consume(MyQueue<T> queue) throws InterruptedException;
}
