package net.kang.sorting_n2;

import java.util.Arrays;

// 삽입 정렬 구현 (안정 정렬)
// 시간 복잡도는 O(N2)
public class InsertionSort {
    static void insert_sort(int[] arr){
        for(int k = 1; k < arr.length; k++){
            int value = arr[k];
            int l;
            for(l = k - 1; l >= 0 && arr[l] > value; l--)
                arr[l + 1] = arr[l];
            arr[l + 1] = value;
        }
    }

    public static void main(String[] args){
        int[] array = new int[] { 9, 2, 5, 3, 6, 1, 7, 8, 10, 4 };
        insert_sort(array);
        System.out.println(Arrays.toString(array));
    }
}
