package net.kang.dynamic.lis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

// 최장 증가 부분 수열(Longest Increasing Subsequence)
public class LIS {
    static final int SIZE = 30, BOUND = 1000;
    static Random random;
    static int[] array;

    // 방법 1) Dynamic Programming 사용.
    // 시간 복잡도는 O(N2). 공간 복잡도는 O(N).
    static int lis_dynamic(){
        int max = Integer.MIN_VALUE;
        int[] dp = new int[SIZE];
        dp[0] = 1;

        for(int k = 1; k < SIZE; k++){
            dp[k] = 1;
            for(int l = 0; l < k; l++){
                if(array[k] > array[l])
                    dp[k] = Math.max(dp[k], dp[l] + 1);
            }
            max = Math.max(max, dp[k]);
        }

        System.out.println(Arrays.toString(dp)); // dp 결과 값 출력

        return max;
    }

    // 방법 2) Index Tree 사용.
    // 시간 복잡도는 O(N log N). 공간 복잡도는 O(N).
    static int lis_index_tree(){
        int[] sorted = Arrays.copyOf(array, SIZE);
        Arrays.sort(sorted);

        List<Integer> index_tree = new ArrayList<>();
        index_tree.add(Integer.MIN_VALUE);
        int size = 0;

        for(int k = 0; k < SIZE; k++){
            if(array[k] > index_tree.get(size)) {
                index_tree.add(array[k]);
                size += 1;
            } else {
                int idx = lower_bound(index_tree, array[k]);
                index_tree.set(idx, array[k]);
            }
        }

        // Index Tree 에 남아 있는 값들을 출력하는 문장입니다.
        for(int k = 1; k <= size; k++) {
            System.out.print(index_tree.get(k) + " ");
            if(k == size) {
                System.out.println();
            }
        }

        return size;
    }

    // Index Tree 에 타겟 값을 넣기 위해 이진 탐색으로 인덱스 자리를 탐색합니다.
    // 이진 탐색의 시간 복잡도는 O(log N).
    static int lower_bound(List<Integer> list, int target){
        int mid;
        int start = 0, end = list.size() - 1;
        while(start < end){
            mid = (start + end) / 2;
            if(list.get(mid) >= target) end = mid;
            else start = mid + 1;
        }

        return end;
    }

    static void initialize(){
        random = new Random();
        array = new int[SIZE];
        for(int k = 0; k < SIZE; k++){
            array[k] = random.nextInt(BOUND) + 1;
        }
    }

    public static void main(String[] args){
        initialize();
        System.out.println(Arrays.toString(array)); // 초기 배열 값 출력
        System.out.println(lis_dynamic());
        System.out.println(lis_index_tree());
    }
}
