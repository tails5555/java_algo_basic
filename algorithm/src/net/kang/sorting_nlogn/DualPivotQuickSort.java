package net.kang.sorting_nlogn;

import java.util.Arrays;

// 3-Way Partition Quick Sort (삼분할 파티션 퀵 정렬)
// 기존 Quick Sort 의 최악의 상황(최댓값 - 최솟값 선택) 에 맞딱뜨릴 때 해결하기 위한 방안.
// 시간 복잡도는 O (n log n). 대신 3 분할로 진행하기 때문에 더욱 빨라진 셈.
public class DualPivotQuickSort {
    static class Pair {
        int primary;
        int secondary;
        public Pair(int primary, int secondary){
            this.primary = primary;
            this.secondary = secondary;
        }
    }

    static void swap(int[] a, int x, int y){
        int tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }

    static Pair partition(int[] a, int start, int end){
        if(a[end - 1] > a[end]) swap(a, end - 1, end);

        int v1 = a[end - 1];
        int v2 = a[end];

        int i1 = start - 1;
        int i2 = start - 1;

        for(int k = start; k <= end - 2; k++){
            if(a[k] < v1) {
                swap(a, ++i1, k);
                if(i2 < i1) i2 = i1;
                else swap(a, ++i2, k);
            } else if(a[k] < v2) {
                swap(a, ++i2, k);
            } else continue;
        }

        swap(a, ++i1, end - 1);
        if(i2 < i1) i2 = i1;
        else swap(a, ++i2, end - 1);
        swap(a, ++i2, end);

        return new Pair(i1, i2);
    }

    static void three_way_quick_sort(int[] a, int start, int end){
        if(start >= end) return;
        Pair pivot = partition(a, start, end);
        three_way_quick_sort(a, start, pivot.primary - 1);
        three_way_quick_sort(a, pivot.primary + 1, pivot.secondary - 1);
        three_way_quick_sort(a, pivot.secondary + 1, end);
    }

    public static void main(String[] args){
        int[] array = new int[] { 9, 2, 5, 3, 6, 1, 7, 8, 10, 4, -2, -9, -3, -1, -7, -8, 0, 6 };
        three_way_quick_sort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }
}
