package net.kang.sorting_n2;

import java.util.Arrays;

// 버블 정렬 구현
// 시간 복잡도는 O(N2)
public class BubbleSort {
    static void swap(int[] arr, int a, int b){
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    static void bubble_sort(int[] arr){
        for(int k = arr.length - 1; k >= 1; k--){
            // 버블 정렬 최적화를 위한 변수
            // 시간 복잡도엔 영향이 없지만, 정렬이 완료되면 굳이 필요 없는 반복문 횟수를 줄입니다.
            boolean completed = true;
            for(int l = 0; l < k; l++){
                if(arr[l] > arr[l + 1]) {
                    swap(arr, l, l + 1);
                    completed = false;
                }
            }
            if(completed) break;
        }
    }

    public static void main(String[] args){
        int[] array = new int[] { 9, 2, 5, 3, 6, 1, 7, 8, 10, 4 };
        bubble_sort(array);
        System.out.println(Arrays.toString(array));
    }
}
