package com.ljf.dataStructure.queue;

/**
 * @author ：ljf
 * @date ：Created in 2019/11/27 13:42
 * @description：环形队列
 * @modified By：
 * @version: $
 */
public class CircleQueue {
    /**
     * 队列遵循先进先出原则，maxSize表示队列容量，
     * rear为队尾下标的下一个索引，head为队头下标
     * 该类包括队列的入队和出队，查看队头元素等基本方法
     */
    private int maxSize;
    private int rear;
    private int front;
    private int[] queue;

    public CircleQueue(int maxSize) {
        this.maxSize = maxSize + 1;
        rear = 0;
        front = 0;
        queue = new int[maxSize + 1];
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //入队
    public void addQueue(int data) {
        //判断队列是否满
        if (isFull()) {
            System.out.println("队列已满，入队失败！");
            return;
        }
        queue[rear] = data;
        rear = (rear + 1) % maxSize; //解决rear到达队列尾部的情况，rear = maxSize-1，下一个坐标应该为0
    }

    //出队
    public int pollQueue() throws RuntimeException {
        //判断队列是否空
        if (isEmpty()) {
            throw new RuntimeException("队列为空，出队失败！");
        }
        int res = queue[front];
        front = (front + 1) % maxSize;
        return res;
    }

    //查看队列头数据
    public int headQueue() {
        //判断队列是否空
        if (isEmpty()) {
            throw new RuntimeException("队列为空，出队失败！");
        }
        return queue[front];
    }

    //展示队列
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
        } else {
            if (rear > front){
                for (int i = front; i < rear; i++) {
                    System.out.println(queue[i]);
                }
            }else{
                for (int i = front; i < maxSize ; i++) {
                    System.out.println(queue[i]);
                }
                for (int i = 0; i < rear; i++) {
                    System.out.println(queue[i]);
                }
            }
        }
    }

    //返回队列的元素个数
    public int length() {
        if (isEmpty()) {
            return 0;
        } else {
            return (rear - front + maxSize) % maxSize;
        }
    }

    public static void main(String[] args) {
        //创建队列对象
        CircleQueue queue = new CircleQueue(5);
        queue.addQueue(3);
        queue.addQueue(4);
        queue.addQueue(5);
        queue.addQueue(5);
        queue.addQueue(5);
        queue.addQueue(5);

        System.out.println(queue.length());
        try {
            System.out.println(queue.pollQueue());
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        System.out.println("===================================");
        queue.addQueue(5);
        queue.showQueue();
    }
}
