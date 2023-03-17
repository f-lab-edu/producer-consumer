package extension;

import interfaces.MyQueue;

import java.util.LinkedList;

// Mutex
public class MyQueueImpl<T> implements MyQueue<T> {
    // 공유자원
    LinkedList<T> queue = new LinkedList<T>();
    // 공유자원에 접근하기 위한 키
    private boolean isPushed = true;
    @Override
    public synchronized void push(T obj) throws InterruptedException {
        while(isPushed){
            wait();
        }
        isPushed = true;

        queue.add(obj);

        notifyAll();

        printQueue();
    }

    @Override
    public synchronized void pop() throws InterruptedException {
        while (!isPushed){
            wait();
        }
        isPushed = false;

        queue.remove();

        notifyAll();

        printQueue();
    }

    public void printQueue(){
        System.out.println("result"+queue);
    }
}
