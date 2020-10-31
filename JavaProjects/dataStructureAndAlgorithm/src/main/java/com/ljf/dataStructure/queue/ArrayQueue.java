package com.ljf.dataStructure.queue;

/**
 * @author ：ljf
 * @date ：Created in 2019/11/27 10:55
 * @description：数组模拟队列操作
 * @modified By：
 * @version: $
 */
public class ArrayQueue {
    /**
     * 队列遵循先进先出原则，maxSize表示队列容量，
     * rear为队尾下标的上一个索引，head为队头下标的上一个索引
     * 该类包括队列的入队和出队，查看队头元素等基本方法
     */
    private int maxSize;
    private int rear;
    private int front;
    private int[] queue;

    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        rear = -1;
        front = -1;
        queue = new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull(){
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //入队
    public void addQueue(int data){
        //判断队列是否满
        if (isFull()){
            System.out.println("队列已满，入队失败！");
            return;
        }
        queue[++rear] = data;
    }

    //出队
    public int pollQueue() throws RuntimeException{
        //判断队列是否空
        if (isEmpty()){
            throw new RuntimeException("队列为空，出队失败！");
        }
        return queue[++front];
    }

    //查看队列头数据
    public int headQueue(){
        //判断队列是否空
        if (isEmpty()){
            throw new RuntimeException("队列为空，出队失败！");
        }
        return queue[front+1];
    }

    //展示队列
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空");
        }
        for (int i = front+1; i < rear+1 ; i++) {
            System.out.println(queue[i]);
        }
    }

    public static void main(String[] args) {
        //创建队列对象
        ArrayQueue queue = new ArrayQueue(5);
        queue.addQueue(3);
        queue.addQueue(4);
        queue.addQueue(5);
        queue.addQueue(5);
        queue.addQueue(5);
        queue.addQueue(5);

        System.out.println(queue.headQueue());
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
