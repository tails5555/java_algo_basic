package net.kang.sorting_special;

import java.util.Arrays;
import java.util.Random;

// 기수 정렬(Radix Sort)
// 시간 복잡도는 O(dn). 공간 복잡도는 O(d + n).
public class RadixSort {
    static final int SIZE = 30, MIN_BOUND = 100000, MAX_BOUND = 1000000;
    static Random random;
    static int[] array;

    static void counting_sort_decimal(int[] array, int pivot) {
        int[] counting = new int[10]; // 십진수로 기수 정렬을 하기 위해 10개의 카운팅이 필요.

        // 1단계. 기준 값(일, 십, 백, 천...) 의 자리수를 카운팅 처리합니다.
        for(int k = 0; k < array.length; k++) {
            int digit = (array[k] / pivot) % 10;
            counting[digit] += 1;
        }

        // 2단계. 누적 합계로 카운팅 정렬을 위한 인덱스 위치를 설정합니다.
        for(int k = 1; k < 10; k++)
            counting[k] += counting[k - 1];

        // 3단계. 계산된 시작 위치로 tmp 배열에 복사합니다.
        // 기수 정렬에서의 카운팅 순서는 뒤에서 부터 정리해야 합니다.
        int[] tmp = new int[array.length];
        for(int k = array.length - 1; k >= 0; k--){
            int digit = (array[k] / pivot) % 10;
            int index = counting[digit] - 1;
            tmp[index] = array[k];
            counting[digit] -= 1;
        }

        // 최종 단계. tmp 배열을 array 에 다시 메꿉니다.
        for(int k = 0; k < array.length; k++)
            array[k] = tmp[k];
    }

    static void radix_sort_decimal(int[] array){
        int log10 = (int) Math.log10(MAX_BOUND);
        int pivot = 1;
        for(int d = 0; d < log10; d++) {
            counting_sort_decimal(array, pivot);
            pivot *= 10;
        }
    }

    static void initialize(){
        random = new Random();
        array = new int[SIZE];
        for(int k = 0; k < SIZE; k++)
            array[k] = random.nextInt(MAX_BOUND - MIN_BOUND) + MIN_BOUND;
    }

    public static void main(String[] args){
        initialize();
        radix_sort_decimal(array);
        System.out.println(Arrays.toString(array));
    }
}
