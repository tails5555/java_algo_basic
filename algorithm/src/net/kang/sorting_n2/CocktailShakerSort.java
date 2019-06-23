package net.kang.sorting_n2;

import java.util.Arrays;

// 칵테일 쉐이커 정렬(Cocktail Shaker Sort)
// 시간 복잡도는 O(N2). 양방향 버블 정렬(Bidirectional Bubble Sort).
// 이전 Bubble Sort 의 시간 낭비를 최소화 시킴.
public class CocktailShakerSort {
    static void swap(int[] arr, int a, int b){
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    static void cocktail_shaker_sort(int[] arr){
        boolean swapped = true;
        int start = 0;
        int end = arr.length;
        while(swapped){
            swapped = false;
            for(int k = start; k < end - 1; k++){
                if(arr[k] > arr[k + 1]) {
                    swap(arr, k, k + 1);
                    swapped = true;
                }
            }

            if(!swapped) break;

            end = end - 1;
            for(int k = end - 1; k >= start; k--){
                if(arr[k] > arr[k + 1]) {
                    swap(arr, k, k + 1);
                    swapped = true;
                }
            }

            start = start + 1;
        }
    }

    public static void main(String[] args){
        int[] array = new int[] { 9, 2, 5, 3, 6, 1, 7, 8, 10, 4 };
        cocktail_shaker_sort(array);
        System.out.println(Arrays.toString(array));
    }
}
