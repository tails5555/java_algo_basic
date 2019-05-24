package net.kang.sorting_nlogn;

import java.util.Arrays;

// 퀵 정렬 구현
// 시간 복잡도는 O(N log N). 최악은 역순이 나오면 O(N2) 도 나올 수 있음.
// Dual Pivot 방안을 택하여 3등분으로 처리하면 빠른 정렬이 가능하긴 합니다.
public class QuickSort {
    static void swap(int[] arr, int a, int b){
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    static int partition(int[] arr, int from, int to){
        int value = arr[to]; // 기준 값은 끝탱이를 잡아도 큰 상관은 없습니다.
        int k = from - 1;
        for(int l = from; l <= to - 1; l++)
            if(arr[l] < value)
                swap(arr, ++k, l);
        swap(arr, k + 1, to); // 마지막에 끝탱이를 맨 가운데로 가져옵니다.
        return k + 1;
    }

    static void quick_sort(int[] arr, int start, int end){
        if(start >= end) return;
        int pivot = partition(arr, start, end);
        quick_sort(arr, start, pivot - 1);
        quick_sort(arr, pivot + 1, end);
    }

    public static void main(String[] args){
        int[] array = new int[] { 9, 2, 5, 3, 6, 1, 7, 8, 10, 4 };
        quick_sort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }
}
