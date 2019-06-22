package net.kang.sorting_special;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// 계수 정렬(Counting Sort. 카운팅 정렬.)
// 시간 복잡도는 O(n + k). 공간 복잡도는 O(n + k).
// 여기서 k 는 배열에 들어 있는 값들의 범위입니다. { 1, 2, 3, 4, 5, -1 } 가 들어 있으면, -1 ~ 5 까지 탐색합니다.
// 카운팅 정렬은 일반적으로 값들의 종류가 적고, 값들의 분산이 작은 집합에 적합합니다.
public class CountingSort {
    static final int SIZE = 30, BOUND = 500;

    // 카운팅 정렬 with Array (자연수 배열 Only)
    static void counting_sort_array_1(int[] array){
        // 1단계. 배열에 있는 값들 중 최댓값을 찾습니다.
        int max = Integer.MIN_VALUE;
        for(int k = 0; k < array.length; k++)
            max = Math.max(max, array[k]);

        // 2단계. 배열에 있는 원소의 값 만큼 계수(카운팅)를 위한 배열을 생성합니다.
        // 여기서 별로 사용되지 않은 값인 희소 카운팅이 생성되어 공간 낭비를 유발합니다.
        int[] counting = new int[max + 1];
        for(int k = 0; k < array.length; k++)
            counting[array[k]] += 1;

        // 3단계. 카운팅 완료 이후 0 부터 X 값까지 누적합을 구성합니다.
        for(int n = 1; n <= max; n++)
            counting[n] += counting[n - 1];

        // 4단계. 뒤에서 부터 정렬을 원하는 배열을 순회하고 값을 새로운 배열에 값을 채웁니다.
        // 순회 방향은 솔직히 상관 없습니다. 앞으로 해도 됩니다.
        // 물론 index 를 끝탱이로 반전 시키면 DESC 정렬도 가능합니다.
        int[] tmp = new int[array.length];
        for(int k = array.length - 1; k >= 0; k--){
            int index = counting[array[k]] - 1;
            tmp[index] = array[k];
            counting[array[k]] -= 1;
        }

        // 최종 단계. 새로운 배열을 원본 배열에 복사합니다.
        for(int k = 0; k < array.length; k++){
            array[k] = tmp[k];
        }
    }

    // 카운팅 정렬 with Array (모든 정수 허용 개선.)
    static void counting_sort_array_2(int[] array){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int k = 0; k < array.length; k++) {
            max = Math.max(max, array[k]);
            min = Math.min(min, array[k]);
        }

        int count_size = max - min + 1;
        int[] counting = new int[count_size];
        for(int k = 0; k < array.length; k++)
            counting[array[k] - min] += 1;

        for(int n = 1; n < counting.length; n++)
            counting[n] += counting[n - 1];

        int[] tmp = new int[array.length];
        for(int k = 0; k < array.length; k++){
            int index = counting[array[k] - min] - 1;
            tmp[index] = array[k];
            counting[array[k] - min] -= 1;
        }

        for(int k = 0; k < array.length; k++){
            array[k] = tmp[k];
        }
    }

    // 카운팅 정렬 with Map (희소 카운팅에 대한 공간 복잡도 최소화)
    static void counting_sort_map(int[] array){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int k = 0; k < array.length; k++) {
            max = Math.max(max, array[k]);
            min = Math.min(min, array[k]);
        }

        Map<Integer, Integer> counting = new HashMap<>();
        for(int k = 0; k < array.length; k++){
            int cnt = counting.getOrDefault(array[k], 0);
            counting.put(array[k], cnt + 1);
        }

        // Map 을 사용하면 희소 카운팅이 최소화 됩니다.
        // { 1, 1, 1, 1000, 100000 } 같이 분산이 매우 큰 배열을 Map 으로 사용하면 시간, 공간 복잡도를 최소화 할 수 있습니다.
        // 그렇지만 분산이 적은 배열의 값들을 Map 으로 사용하면 Array 를 사용하는 것과 변함이 없습니다.
        int[] ele_set = counting.keySet().stream().sorted().mapToInt(Integer::intValue).toArray();
        for(int k = 1; k < ele_set.length; k++){
            int before_cnt = counting.get(ele_set[k - 1]);
            int now_cnt = counting.get(ele_set[k]);
            counting.put(ele_set[k], before_cnt + now_cnt);
        }

        int[] tmp = new int[array.length];
        for(int k = 0; k < array.length; k++){
            int index = counting.get(array[k]) - 1;
            tmp[index] = array[k];
            counting.put(array[k], counting.get(array[k]) - 1);
        }

        for(int k = 0; k < array.length; k++){
            array[k] = tmp[k];
        }
    }

    public static void main(String[] args) {
        int[] array1 = new int[]{ 9, 2, 5, 3, 6, 1, 7, 8, 10, 4, 2, 9, 3, 1, 7, 8, 0 };
        counting_sort_array_1(array1);
        System.out.println(Arrays.toString(array1));

        int[] array2 = new int[]{ 9, 2, 5, 3, 6, 1, 7, 8, 10, 4, -2, -9, -3, -1, -7, -8, 0 };
        counting_sort_array_2(array2);
        System.out.println(Arrays.toString(array2));

        int[] array3 = new int[]{ 9, 2, 5, 3, 6, 1, 7, 8, 10, 4, -2, -9, -3, -1, -7, -8, 0 };
        counting_sort_map(array3);
        System.out.println(Arrays.toString(array3));

        Random random = new Random();
        int[] rand_array1 = new int[SIZE];
        for(int k = 0; k < SIZE; k++){
            rand_array1[k] = random.nextInt(BOUND) + 1;
        }
        counting_sort_array_1(rand_array1);
        System.out.println(Arrays.toString(rand_array1));

        int[] rand_array2 = new int[SIZE];
        for(int k = 0; k < SIZE; k++){
            rand_array2[k] = random.nextInt(BOUND) - 250;
        }
        counting_sort_array_2(rand_array2);
        System.out.println(Arrays.toString(rand_array2));

        int[] rand_array3 = new int[SIZE];
        for(int k = 0; k < SIZE; k++){
            rand_array3[k] = random.nextInt(BOUND) - 250;
        }
        counting_sort_map(rand_array3);
        System.out.println(Arrays.toString(rand_array3));
    }
}
