package com.spacedo.algorithms.sort;

import java.util.Random;

public class ShellSort {

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
        int i, j, k, temp, gap;
        int[] gaps = initGaps();
        //print(gaps);
        for (int g = gaps.length - 1 ; g >= 0; g--) {
            gap = gaps[g];
            if(gap>=length){
                continue;
            }
            //System.out.println(gap);
        //for (gap = arr.length / 2; gap >  0; gap /= 2) {
            //gap：步长，数据间隔gap-1分为gap组
            //每份数据做插入排序，注意每组数据间隔为gap-1
            for (i = 0; i < gap; i+=1) {//i:起始位置
                for (j = i+gap; j < length; j+=gap) {
                    temp = arr[j];
                    k = j - gap;
                    for (; k >=0 ; k-=gap) {
                        if (arr[k] > temp) {
                            arr[k+gap] = arr[k];
                        }else {
                            break;
                        }
                    }
                    arr[k+gap] = temp;
                }
            }
            if (gap==1) {
                break;
            }
        }
    }

    private static int[] initGaps() {
        return new int[]{1,	3,	7,	13,	37,	73,	217,	433,	1297,	2593,	7777,	15553,	46657,	93313,	279937,	559873,	1679617,	3359233};
        //return new int[]{1,	3,	9,	13,	35,	61,	161,	287,	777,	1413,	3887,	7177,	19995,	37323,	104975,	197601,	559871,	1060809,	3023307,	5758683};
        /*return new int[]{1,	3,	9,	25,	65,	161,	385,	897,	2049,	4609,	10241,	22529,	49153,	106497,	229377,	491521,	1048577,
                2228225,	4718593,	9961473,	20971521,	44040193,	92274689,	192937985,	402653185,	838860801,	1744830465};*/
        /*return new int[]{1,5,19,41,109,209,505,929,
                2161,3905,8929,16001,36289,64769,146305,260609,
                587521,1045505,2354689,4188161,9427969};*/
        /*return new int[]{1,3,7,15,31,63,127,255,
                511,1023,2047,4095,8191,16383,32767,65535,
                131071,262143,524287,1048575,2097151,4194303,8388607,16777215,
                33554431,67108863,134217727,268435455,536870911,1073741823};*/
        /*return new int[]{1,5,19,41,109,209,505,929,
                2161,3905,8929,16001,36289,64769,146305,260609,
                587521,1045505,2354689,4188161,9427969,16764929,37730305,67084289,
                150958081,268386305,603906049,1073643521};*/
        /*int[] gaps = new int[100];
        gaps[0]=1;
        int l = 0;
        int t;
        while ((t = gaps[l]*2+1) < length){
            l++;
            gaps[l]=t;
        }
        return gaps;*/
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
            System.out.print(array[m] + ",\t");
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
       /* int[] array1 = initArr(1000_0000);
        //System.out.println("排序前");
        //print(array1);
        long sStart = System.currentTimeMillis();
        sortD(array1);
        //print(array1);
        System.out.println(System.currentTimeMillis() - sStart);
*/
        insertSortTest(10);
        //System.out.println(count(1000_0000));;
        //rGaps2();
    }

    private static int[] initArr(int count) {
        int[] array = new int[count];
        Random r = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextInt(count);
        }
        return array;
    }

    private static int[] rGaps() {
        int t;
        int[] array = new int[30];
        for (int i = 0; i<30; i++) {
            t = i*((int)Math.pow(2, i))+1;
            array[i]=t;
        }
        print(array);
        return array;
    }


    private static int[] rGaps1() {
        int[] array = new int[30];
        array[0]=1;
        for (int i = 0; i<29; i++) {
            array[i+1]=array[i]*2;
        }
        print(array);
        for (int i = 1; i < array.length; i++) {
            array[i]=array[i]/i;
        }
        print(array);
        for (int i = 1; i < array.length; i++) {
            array[i]=array[i]%2==0?array[i]-1:array[i];
        }
        print(array);
        //array[2]=1;
        return array;
    }

    private static int[] rGaps3() {
        int[] array = new int[30];
        array[0]=1;
        for (int i = 0; i<29; i++) {
            array[i+1]=array[i]*(i%2+2);
        }
        print(array);
        for (int i = 1; i < array.length; i++) {
            array[i]=array[i]/i;
        }
        print(array);
        for (int i = 1; i < array.length; i++) {
            array[i]=array[i]%2==0?array[i]-1:array[i];
        }
        print(array);
        //array[2]=1;
        return array;
    }

    //小于2^k的最大质数
    private static int[] rGaps2() {
        int[] array = new int[30];
        array[0]=1;
        double k;
        boolean[] sst = sst(1000_0000);
        int j = 0;
        for (int i = 1; i<30; i++) {
            k = Math.pow(2, i);
            for (; j < sst.length && j<k; j++) {
                if (sst[j]) {
                    array[i]=j;
                }
            }
        }
        print(array);
        return array;
    }


    // 希尔排序
    // N/2:100_0000:100:208
    // N/2:104_8576:100:1298
    // N/2:1000_0000:10:3339
    // 2^k-1:100_0000:100:274
    // 2^k-1:104_8576:100:290
    // 2^k-1:1000_0000:10:4633
    // hi=max(9∗4j−9∗2j+1,4k−3∗2k+1):100_0000:100:175
    // hi=max(9∗4j−9∗2j+1,4k−3∗2k+1):104_8576:100:185
    // hi=max(9∗4j−9∗2j+1,4k−3∗2k+1):1000_0000:10:2407
    // 2^k/k 奇数：1000_0000:2649
    // 小于2^k的最大质数：1000_0000:10:2853

    // 快速排序
    // 随机快排:100_0000:10:100
    // 随机快排:1000_0000:10:1100
    // QuickSort2Ways:100_0000:10:102
    // QuickSort2Ways:1000_0000:10:1155
    // QuickSort3Ways:100_0000:10:128
    // QuickSort3Ways:1000_0000:10:1434

    // jdk Arrays
    // Arrays.sort():100_0000:10:77
    // Arrays.sort():1000_0000:10:802
    private static void insertSortTest(int count){
        int sumTime = 0;
        for (int i = 0; i < count; i++) {
            int[] array = initArr(1000_0000);
            //int[] array = initArr(104_8576);
            long iStart = System.currentTimeMillis();
            //sortD(array);
            //Arrays.sort(array);
            //RQuickSort.sort(array);
            //QuickSort2Ways.sort(array);
            sortD(array);
            long time = System.currentTimeMillis() - iStart;
            System.out.println(time);
            sumTime+=time;
        }
        System.out.println("avg："+(sumTime/count));
    }

    // >=n的质数表
    static boolean[] sst(int n) {
        boolean[] Bit_array = new boolean[n+1];
        int i,j;
        Bit_array[0] = false;Bit_array[1] = false;//0和1都是合数
        for( i=2; i<=n; ++i)
            Bit_array[i] = true;//初始化数组

        for( i=2; i<=n; ++i)//找到合数
            if(Bit_array[i]) // if i is prime number
                for( j=i*i; j<=n&&j>0; j+=i)
                    Bit_array[j] = false;

        return Bit_array;
    }

}
