package net.kang.hash;

import java.util.Arrays;

// Multiplication 기법을 적용한 Hash Table
// Multiplication 은 임의의 소수(0 < A < 1) 를 곱해, floor 한 값을 인덱스로 저장하는 과정입니다.
// 충돌 횟수와 소수를 곱하는 Double Hashing 기법으로 테이블 안에 있는 값들의 군집을 최소화 합니다.
public class MultiplicationHashTable implements HashTable {
    static final int EMPTY = Integer.MIN_VALUE;
    static final double A = 0.4321;
    static final int[] PRIME_NUMS = { 3, 5, 7, 11, 13, 17, 19, 23, 29 };

    private int[] array;

    public MultiplicationHashTable() {
        this(64);
    }

    public MultiplicationHashTable(int size){
        this.array = new int[size];
        Arrays.fill(this.array, EMPTY);
    }

    private int getIndex(int value, int collision){
        int start = (int) ((value * A) % 1 * array.length);
        int step = PRIME_NUMS[value % PRIME_NUMS.length];
        return (start + collision * step) % array.length;
    }

    @Override
    public void add(int value) throws Exception {
        int cnt = 0;
        while(cnt < array.length) {
            int idx = this.getIndex(value, cnt);
            if(array[idx] == value) return;
            if(array[idx] == EMPTY) {
                array[idx] = value;
                return;
            }
            cnt += 1;
        }
        throw new Exception(String.format("공간 부족. 지정 공간 크기는 %d 입니다.", array.length));
    }

    @Override
    public void remove(int value) {
        int cnt = 0;
        while(cnt < array.length) {
            int idx = this.getIndex(value, cnt);
            if(array[idx] == EMPTY) return;
            if(array[idx] == value) {
                array[idx] = EMPTY;
                return;
            }
            cnt += 1;
        }
    }

    @Override
    public boolean contains(int value) {
        int cnt = 0;
        while(cnt < array.length) {
            int idx = this.getIndex(value, cnt);
            if (array[idx] == value) return true;
            if (array[idx] == EMPTY) return false;
            cnt += 1;
        }
        return false;
    }
}
