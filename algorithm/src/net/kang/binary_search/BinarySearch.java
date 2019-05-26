package net.kang.binary_search;

import java.util.LinkedList;
import java.util.Queue;

// 이진 탐색 구현
public class BinarySearch {
    static int[] array;

    // 방법 1. 재귀 사용
    static int binary_recursive(int from, int to, int value){
        if(from > to) return -1;
        int mid = (from + to) / 2;
        if(value < array[mid]) return binary_recursive(from, mid - 1, value);
        else if(value > array[mid]) return binary_recursive(mid + 1, to, value);
        else return mid;
    }

    // 방법 2. while 반복문 사용
    static int binary_loop(int value){
        int first = 0;
        int last = array.length - 1;

        int mid;
        while(first <= last){
            mid = (first + last) / 2;
            if(value == array[mid]) return mid;
            else if(value < array[mid]) last = mid - 1;
            else first = mid + 1;
        }

        return -1;
    }

    // 방법 3. BFS 원리 적용
    static int binary_bfs(int value){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { 0, array.length - 1 });

        int mid;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            if(cur[0] > cur[1]) break;

            mid = (cur[0] + cur[1]) / 2;
            if(value == array[mid]) return mid;
            else if(value < array[mid]) queue.offer(new int[] { cur[0], mid - 1 });
            else queue.offer(new int[] { mid + 1, cur[1] });
        }

        return -1;
    }

    public static void main(String[] args){
        array = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
        System.out.println(binary_recursive(0, 15, 12));
        System.out.println(binary_loop(12));
        System.out.println(binary_bfs(12));
    }
}
