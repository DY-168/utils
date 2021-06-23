package com.spacedo.algorithms.sort;

import java.util.Random;

public class QuickSortMy {
    public static int[] sort(int[] arr){
        sort(arr, 0, arr.length-1);
        return arr;
    }

    //递归排序
    public static int[] sort(int[] arr, int l, int r){
        //递归结束条件
        if (l>=r) {
            return arr;
        }
        int index = partition(arr, l, r);
        sort(arr, 0, index-1);
        sort(arr, index+1, r);
        return arr;
    }

    /*private static int partition(int[] arr, int l, int r) {
        //v=arr[l]
        //arr[l+1...j-1]<v -> arr[l+1...j]<=v
        //arr[j+1...r]>v
        int j = l;// <=v 的数组下标
        for (int i = l+1; i <= r; i++) {
            if (arr[i]<arr[l]) {
                j++;
                swap(arr, i, j);
            }
        }
        //最后交换arr[l],arr[j]
        swap(arr, l, j);
        return j;
    }*/

    private static int partition(int[] arr, int l, int r) {
        //v=arr[l]
        //// arr[l+1...i) <= v; arr(j...r] >= v
        //arr[j...r]>v
        int i = l+1, j=r;// i是<v的数组元素下标, j是>v的数组元素下标
        while (true){
            while( i <= r && arr[i] < arr[l] )
                i ++;
            while( j >= l+1 && arr[j] > arr[l] )
                j --;
            if( i > j )
                break;
            swap( arr, i, j );
            i ++;
            j --;
        }
        //最后交换arr[l],arr[j]
        swap(arr, l, j);
        return j;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        print(sort(initArr(10)));
    }

    private static int[] initArr(int count) {
        int[] array = new int[count];
        Random r = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextInt(count);
        }
        return array;
    }

    private static void print(int[] array) {
        // 打印每趟排序结果
        for (int m = 0; m <= array.length - 1; m++) {
            System.out.print(array[m] + "\t");
        }
        System.out.println();
    }
}
