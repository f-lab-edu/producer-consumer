package interfaces;

public interface Consumer<T> extends Runnable{
    public void consume() throws InterruptedException;
}
