package com.ljf.algorithm;

import java.util.*;

/**
 * @author ：ljf
 * @date ：2020/7/12 21:23
 * @description：小顶堆实现topk频率的元素；时间复杂度小于On(logn)
 * @modified By：
 * @version: $ 1.0
 */
class Item {
    int key;
    int value;

    Item(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

public class TopKFrequentLJF {

    public static int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        //构建小顶堆
        PriorityQueue<Item> priorityQueue = new PriorityQueue<>(k, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.value - o2.value;
            }
        });

        int value;
        //构建map，遍历数据
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            value = map.getOrDefault(num, 0);
            map.put(num, value + 1);
        }

        //将map中的value取出与小顶堆的堆顶进行对比
        Set<Integer> keys = map.keySet();

        int index = 0;
        int tmpValue;
        for (Integer key : keys) {
            if (index < k) {
                priorityQueue.offer(new Item(key, map.get(key)));
                index++;
            } else {
                //如果堆顶元素小于新来的元素，则更新小顶堆
                tmpValue = map.get(key);
                if (priorityQueue.peek().value < tmpValue) {
                    priorityQueue.poll();
                    priorityQueue.offer(new Item(key, tmpValue));
                }
            }
        }


        //将小顶堆中的元素依次取出放入到结果中
        for (int i = 0; i < k; i++) {
            res[i] = priorityQueue.poll().key;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println(Arrays.toString(topKFrequent(nums, k)));
    }
}
