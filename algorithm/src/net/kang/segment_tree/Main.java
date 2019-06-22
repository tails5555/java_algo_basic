package net.kang.segment_tree;

import java.util.Arrays;
import java.util.Random;

public class Main {
    static final int SIZE = 16, BOUND = 100;
    static SegmentTree tree;

    static void divide_conquer(int left, int right) {
        if(left >= right) return;
        System.out.printf("[%d %d] %d\n", left, right, tree.get_min(left, right));
        int mid = (left + right) / 2;
        divide_conquer(left, mid);
        divide_conquer(mid + 1, right);
    }

    public static void main(String[] args){
        Random random = new Random();

        tree = new SegmentTree(SIZE);
        int[] values = new int[SIZE];
        for(int k = 0; k < SIZE; k++)
            values[k] = random.nextInt(BOUND) + 1;
        tree.insert(values);

        System.out.println(Arrays.toString(values));
        divide_conquer(1, SIZE);
    }
}
