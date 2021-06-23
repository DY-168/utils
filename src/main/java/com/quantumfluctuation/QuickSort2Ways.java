package com.quantumfluctuation;

/**
 * 双路快速排序
 */
public class QuickSort2Ways {

    //核心代码---开始
    private static int partition(int[] arr, int l, int r){
        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        swap( arr, l , (int)(Math.random()*(r-l+1))+l );
        int v = arr[l];
        // arr[l+1...i) <= v; arr(j...r] >= v
        int i = l+1, j = r;
        while( true ){
            while( i <= r && arr[i] < v )
                i ++;
            while( j >= l+1 && arr[j] > v )
                j --;
            if( i > j )
                break;
            swap( arr, i, j );
            i ++;
            j --;
        }
        swap(arr, l, j);
        return j;
    }
    //核心代码---结束

    // 递归使用快速排序,对arr[l...r]的范围进行排序
    private static void sort(int[] arr, int l, int r){
        if (l >= r) {
            return;
        }
        int p = partition(arr, l, r);
        sort(arr, l, p-1 );
        sort(arr, p+1, r);
    }

    public static void sort(int[] arr){

        int n = arr.length;
        sort(arr, 0, n-1);
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

}
