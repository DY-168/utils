package com.quantumfluctuation;

import java.util.Random;

public class Sort {
    //100次10W随机排序-1200ms
    public static void insertSort(int[] array) {
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
    public static void insertSort1(int[] array) {
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

    public static void shellSort(int[] array) {
        int n = array.length;
        int i, j, gap;
        int temp;
        for (gap = n / 2; gap > 0; gap /= 2) {
            for (i = gap; i < n; i++) {
                for (j = i - gap; j >= 0 && array[j] > array[j + gap]; j -= gap) {
                    temp = array[j];
                    array[j] = array[j + gap];
                    array[j + gap] = temp;
                }
            }
        }
    }

    public static void sort(int[] arr) {
        int j, temp;
        for (int gap = arr.length / 2; gap >  0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                temp = arr[i];
                for (j = i; j >= gap; j -= gap) {
                    if (temp < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                    }
                }
                arr[j] = temp;
            }
        }
    }

    public static void sortD(int[] arr) {
        int length = arr.length;
        int i, j, temp;
        for (int gap = length / 2; gap >  0; gap /= 2) {
            System.out.println("gap:"+gap);
            //gap：步长，数据间隔gap-1分为gap组
            //每份数据做插入排序，注意每组数据间隔为gap-1
            for (i = gap; i < length; i+=gap) {
                System.out.println("插入：");
                temp = arr[i];
                j = i - gap;
                for (; j >=0 ; j-=gap) {
                    if (arr[j] > temp) {
                        arr[j+gap] = arr[j];
                    }else {
                        break;
                    }
                }
                arr[j+gap] = temp;
            }
        }
    }

    public static void insertSortP(int[] array) {
        int last = array.length;
        int temp, i, j;
        long compareCount=0, exchangeCount=0;
        for (i = 1; i < last; i++) {// 默认以第一个数为有序序列，后面的数为要插入的数。
            temp = array[i];
            j = i - 1;
            for (; j >=0 ; j--) {
                compareCount++;
                //exchangeCount++;
                if (array[j] > temp) {
                    array[j+1] = array[j];
                }else {
                    break;
                }
            }
            array[j+1] = temp;
            //print(array);
        }
        System.out.println("比较次数："+compareCount);
        System.out.println("交换次数："+exchangeCount);
    }

    public static void shellSortP(int[] array) {
        int n = array.length;
        int i, j, gap, temp;
        long compareCount=0, exchangeCount=0;
        for (gap = n / 2; gap > 0; gap /= 2) {// 计算gap大小
            System.out.println("gap:"+gap);
            for (i = gap; i < n; i++) {// 将数据进行分组
                for (j = i - gap; j >= 0; j -= gap) {// 对每组数据进行插入排序
                    compareCount++;
                    //System.out.print("\t比较["+j+"],["+(j+gap)+"]");
                    if (array[j] > array[j + gap]) {
                        temp = array[j];
                        array[j] = array[j + gap];
                        array[j + gap] = temp;
                        exchangeCount++;
                        //System.out.print("\t交换");
                    }
                    //System.out.print("\n");
                }
            }
            //System.out.print("gap");
            //print(array);
        }
        System.out.print("比较次数："+compareCount);
        System.out.print("交换次数："+exchangeCount);
    }

    private static void print(int[] array) {
        // 打印每趟排序结果
        for (int m = 0; m <= array.length - 1; m++) {
            System.out.print(array[m] + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] array = {9,8,7,6,5,4,3,2,1};
        //int[] array = initArr(100_0000);
        /*System.out.println("排序前");
        print(array);
        long iStart = System.currentTimeMillis();
        insertSort1(array);
        System.out.println(System.currentTimeMillis() - iStart);
        System.out.println("排序后");
        print(array);*/
        int[] array1 = initArr(10_0000);
        //System.out.println("排序前");
        //print(array1);
        long sStart = System.currentTimeMillis();
        shellSort(array1);
        //print(array1);
        System.out.println(System.currentTimeMillis() - sStart);

        //insertSortTest(100);
    }

    private static int[] initArr(int count) {
        int[] array = new int[count];
        Random r = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextInt(count);
        }
        return array;
    }

    private static void insertSortTest(int count){
        int sumTime = 0;
        for (int i = 0; i < count; i++) {
            int[] array = initArr(100_0000);
            long iStart = System.currentTimeMillis();
            sortD(array);
            long time = System.currentTimeMillis() - iStart;
            System.out.println(time);
            sumTime+=time;
        }
        System.out.println("avg："+(sumTime/count));
    }
}
