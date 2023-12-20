package algorithm;

import utils.AlgorithmUtils;

/**
 * java基础算法
 * @author: MatthewTse
 * @date: 2023/12/19
 */
public class BasicAlgorithm {
    public static void main(String[] args) {

    }

    /**
     * 排序算法一：冒泡排序
     * 通过与相邻元素比较和交换把小的交换到最前面，时间复杂度为O(n^2)
     */
    public void bubbleSort(int[] arr) {
        if (arr == null || arr.length==0){
            return;
        }
        for (int i=0;i<arr.length;i++){
            for (int j=0;j<arr.length-1-i;j++){
                if (arr[j]>arr[i]){
                    AlgorithmUtils.bubbleSortSwap(arr,i,j);
                }
            }
        }
    }

}
