package net.kang.hash;

import java.util.Arrays;

// Division 기법을 적용한 Hash Table
// 값을 추가할 때, 나머지 값으로 인덱스를 설정합니다.
public class DivisionHashTable implements HashTable {
    static final int EMPTY = Integer.MIN_VALUE;
    static final int SIZE = 61; // Division 기법은 Hash Table 크기를 소수(Prime) 로 둡니다.
    private int[] array;

    public DivisionHashTable(){
        this.array = new int[SIZE];
        Arrays.fill(this.array, EMPTY);
    }

    @Override
    public void add(int value) throws Exception {
        int start = value % SIZE;
        int cnt = 0;
        while(cnt < SIZE) {
            int idx = (start + cnt) % SIZE;
            if(array[idx] == value) return;
            if(array[idx] == EMPTY) {
                array[idx] = value;
                return;
            }
            cnt += 1;
        }
        throw new Exception(String.format("공간 부족. 지정 공간 크기는 %d 입니다.", SIZE));
    }

    @Override
    public void remove(int value) {
        int start = value % SIZE;
        int cnt = 0;
        while(cnt < SIZE) {
            int idx = (start + cnt) % SIZE;
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
        int start = value % SIZE;
        int cnt = 0;
        while(cnt < SIZE) {
            int idx = (start + cnt) % SIZE;
            if (array[idx] == value) return true;
            if (array[idx] == EMPTY) return false;
            cnt += 1;
        }
        return false;
    }
}
