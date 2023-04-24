package extension;

import interfaces.MyQueue;
import interfaces.Producer;

import java.util.LinkedList;

// Mutex
public class MyQueueImpl<T> implements MyQueue<T> {
    // 공유자원
    LinkedList<T> queue = new LinkedList<T>();

    // 협업을 가능하게 해주는 변수 (해당 변수의 값에 따라서 wait-set에서 스레드 기다리게 하기)
    private final int capacity;

    public MyQueueImpl(int _capacity) {
        this.capacity = _capacity;
    }

    @Override
    public void push(T obj) {
        try{
            System.out.println("push method called");

            // sync non-blocking
                synchronized (this) {
                    while(queue.size() >= capacity) {
                        wait();
                    }
                    System.out.println("queue added");
                    queue.add(obj);

                    notifyAll();
                    printQueue(true);
                }
        } catch (InterruptedException err){
            System.out.println("MyQueue Push Err: " + err);
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void pop()  {
        try {
            System.out.println("pop method called");
                synchronized(this) {
                    while(queue.size() <= 0){
                        wait();
                    }

                    System.out.println("queue removed");
                    queue.remove();

                    notifyAll();

                    printQueue(false);
                }
        } catch (InterruptedException err){
            System.out.println("MyQueue Pop Err: " + err);
            Thread.currentThread().interrupt();
        }
    }

    public void printQueue(Boolean state){
        System.out.println("isPushed: "+state+", result:" +queue);
    }
}
