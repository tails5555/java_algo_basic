package net.kang.hash;

import java.util.Random;

// Hash Table 자료 구조 예제
public class Main {
    static Random random = new Random();
    public static void main(String[] args) throws Exception {
        HashTable table1 = new DivisionHashTable();
        for(int k = 0; k < 61; k++){
            table1.add(random.nextInt(100) + 1);
        }
        for(int k = 1; k <= 100; k++){
            if(table1.contains(k))
                System.out.printf("%d ", k);
        }
        System.out.println();

        HashTable table2 = new MultiplicationHashTable();
        for(int k = 0; k < 61; k++){
            table2.add(random.nextInt(100) + 1);
        }
        for(int k = 1; k <= 100; k++){
            if(table2.contains(k))
                System.out.printf("%d ", k);
        }
        System.out.println();
    }
}
