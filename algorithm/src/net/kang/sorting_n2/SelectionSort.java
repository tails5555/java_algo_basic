package net.kang.sorting_n2;

import java.util.Arrays;

// 선택 정렬 구현
// 시간 복잡도는 O(N2)
public class SelectionSort {
    static void swap(int[] arr, int a, int b){
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    static int min_index(int start, int[] arr){
        int min = Integer.MAX_VALUE;
        int idx = start;
        for(int k = start; k < arr.length; k++){
            if(min > arr[k]){
                min = Math.min(min, arr[k]);
                idx = k;
            }
        }

        return idx;
    }

    static void selection_sort(int[] arr){
        for(int k = 0; k < arr.length - 1; k++){
            int idx = min_index(k, arr);
            swap(arr, k, idx);
        }
    }

    public static void main(String[] args){
        int[] array = new int[] { 9, 2, 5, 3, 6, 1, 7, 8, 10, 4 };
        selection_sort(array);
        System.out.println(Arrays.toString(array));
    }
}
