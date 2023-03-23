package interfaces;

public interface Producer<T> extends Runnable {
    public void send(T object) throws InterruptedException;
}
