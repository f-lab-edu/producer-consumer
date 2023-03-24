package extension;

import interfaces.MyQueue;

import java.util.LinkedList;

// Mutex
public class MyQueueImpl<T> implements MyQueue<T> {
    // 공유자원
    LinkedList<T> queue = new LinkedList<T>();

    // 협업을 가능하게 해주는 변수 (해당 변수의 값에 따라서 wait-set에서 스레드 기다리게 하기)
    private int count = 0;
    private boolean isPushed = false;
    private boolean isPop = false;
    @Override
    public synchronized void push(T obj) throws InterruptedException {
        System.out.println("push method called");
        while(isPop){
            wait();
        }
        isPushed = true;
        count +=1;
        System.out.println("queue added");
        queue.add(obj);

        notifyAll();

        printQueue(isPushed);

        isPushed = false;
    }

    @Override
    public synchronized void pop() throws InterruptedException {
        System.out.println("pop method called");
        while (isPushed){
            wait();
        }
        isPop = true;

        System.out.println("queue removed");
        queue.remove();

        notifyAll();

        printQueue(isPushed);

        isPop = false;
    }

    public void printQueue(Boolean state){
        System.out.println("isPushed: "+state+", result:" +queue);
    }
}
