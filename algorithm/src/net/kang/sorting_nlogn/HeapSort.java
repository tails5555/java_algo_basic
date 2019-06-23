package net.kang.sorting_nlogn;

import java.util.Arrays;

// 힙 정렬 구현
// 시간 복잡도는 O(N log N)
public class HeapSort {
    static void swap(int[] arr, int x, int y){
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }

    // build_heap 메소드는 중앙 인덱스부터 0 까지 Heap 자료구조로
    static void build_heap(int[] arr){
        int end = arr.length - 1;
        for(int k = (end - 1) / 2; k >= 0; k--)
            heapify(arr, k, end);
    }

    // heapify 메소드는 배열을 Heap 자료구조로 정리하기 위한 용도입니다.
    static void heapify(int[] arr, int k, int end){
        int left = 2 * k + 1, right = 2 * k + 2;
        int smaller;
        if(right <= end)
            smaller = (arr[left] > arr[right]) ? left : right;
        else if(left <= end) smaller = left;
        else return;
        if(arr[smaller] > arr[k]){
            swap(arr, smaller, k);
            heapify(arr, smaller, end);
        }
    }

    static void heap_sort(int[] arr){
        build_heap(arr);
        for(int end = arr.length - 1; end >= 1; end--) {
            swap(arr, 0, end);
            heapify(arr, 0, end - 1);
        }
    }

    public static void main(String[] args){
        int[] array = new int[] { 9, 2, 5, 3, 6, 1, 7, 8, 10, 4 };
        heap_sort(array);
        System.out.println(Arrays.toString(array));
    }
}
