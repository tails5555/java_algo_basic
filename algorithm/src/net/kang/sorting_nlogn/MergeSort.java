package net.kang.sorting_nlogn;

import java.util.Arrays;

// 병합 정렬 구현
// 시간 복잡도는 O(N log N), 공간 복잡도는 O(N)
public class MergeSort {
    static void merge(int[] arr, int start, int mid, int end){
        int len = end - start + 1;
        int[] tmp = new int[len];

        int i = 0;
        int idx1 = start;
        int idx2 = mid + 1;

        // 일단 두 배열에 있는 값을 비교하여 작은 것을 넣어줍니다.
        while(idx1 <= mid && idx2 <= end){
            if(arr[idx1] < arr[idx2])
                tmp[i++] = arr[idx1++];
            else
                tmp[i++] = arr[idx2++];
        }

        // 나머지 처리(시작 - 중앙 부분)
        while(idx1 <= mid)
            tmp[i++] = arr[idx1++];

        // 나머지 처리(중앙 - 끝 부분)
        while(idx2 <= end)
            tmp[i++] = arr[idx2++];

        // 배열 복사
        for(int k = 0; k < len; k++)
            arr[start + k] = tmp[k];
    }

    static void merge_sort(int[] arr, int start, int end){
        if(start >= end) return;
        int mid = (start + end) / 2;
        merge_sort(arr, start, mid);
        merge_sort(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }

    public static void main(String[] args){
        int[] array = new int[] { 9, 2, 5, 3, 6, 1, 7, 8, 10, 4 };
        merge_sort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }
}
