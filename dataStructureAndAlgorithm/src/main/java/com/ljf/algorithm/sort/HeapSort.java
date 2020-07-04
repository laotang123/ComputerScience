package com.ljf.algorithm.sort;

import java.util.Arrays;

/**
 * @author ：ljf
 * @date ：Created in 2019/12/20 9:35
 * @modified By：
 * @version: $
 */
public class HeapSort {
    /**
     * 非递归实现堆排序
     */
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9,-1,999,-989,1000,33};

        initHeap(arr);

        int temp = 0;
        //依次将堆顶和堆尾元素交换，然后将堆顶元素进行下沉
        for (int i = arr.length-1; i >0 ; i--) {
            temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            sink(arr,0,i);
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 1.初始化堆
     * 2.交换堆顶和堆尾，重构除堆尾之外的堆
     *
     * @param arr
     */
    public static void heapSort(int[] arr) {
        //创建堆
        initHeap(arr);

        //交换堆顶和堆尾，将交换后的堆顶元素进行下沉。此时只有堆顶元素
        //不符合堆得定义，其他子树都符合
        int lastIndex = arr.length - 1;
        int temp = 0; //交换的中间变量
        while (lastIndex > 0) {
            //交换堆顶和堆尾
            temp = arr[0];
            arr[0] = arr[lastIndex];
            arr[lastIndex] = temp;
            lastIndex--;

            sink(arr, 0, lastIndex);
        }
    }

    /**
     * 将下标为i的节点，在指定堆下沉
     *
     * @param arr：引用数组
     * @param i：待下沉的节点
     * @param lastIndex：堆尾下标
     */
    private static void sink(int[] arr, int i, int lastIndex) {
        //保存下沉的堆顶元素
        int temp = arr[i];

        //循环将对顶元素下沉
        for (int k = 2 * i + 1; k < lastIndex; k = k * 2 + 1) {
            //找到当前k节点的左右子节点中最大值
            if (k + 1 < lastIndex && arr[k] < arr[k + 1]) {//防止右节点越界
                k++;
            }
            //判断最大子节点是否大于父节点，如果大于，父节点下沉
            if(arr[k] > temp){
                arr[i] = arr[k];
                i = k;//i指向k，继续下沉
            }else{
                break;//当前堆顶大于左右子节点，不必再下沉
            }
        }
        //堆顶的临时保存至temp，放到所在位置
        arr[i] = temp;
    }

    private static void initHeap(int[] arr) {
        //从arr.length/2处逆序调整堆，叶子节点默认为有序堆
        int len = arr.length;
        for (int i = len/2-1; i >=0 ; i--) {
            sink(arr,i,len);
        }
    }
}
