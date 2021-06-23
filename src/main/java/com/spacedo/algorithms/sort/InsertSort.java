package com.spacedo.algorithms.sort;

/**
 * 直接插入排序
 */
public class InsertSort {

    //100次10W随机排序-1200ms
    public static void sort1(int[] array) {
        int last = array.length;
        int temp, i, j;
        for (i = 0; i <= last - 1; i++) {
            temp = array[i];
            j = i - 1;
            while (j >= 0 && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
    }

    //100次10W随机排序-1162ms
    public static void sort2(int[] array) {
        int last = array.length;
        int temp, i, j;
        for (i = 1; i < last; i++) {
            temp = array[i];
            j = i - 1;
            for (; j >=0 ; j--) {
                if (array[j] > temp) {
                    array[j+1] = array[j];
                }else {
                    break;
                }
            }
            array[j+1] = temp;
        }
    }
}
