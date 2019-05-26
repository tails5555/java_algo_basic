package net.kang.backtracking.permutation;

// 순열 알고리즘 With Backtracking
// 매개변수의 메모리가 많이 소요됨.
public class Permutation_1 {
    static int tar_cnt;
    static char[] str;
    static boolean[] visited;

    static void backtracking(int cnt, String tmp){
        if(cnt == tar_cnt){
            System.out.println(tmp);
            return;
        }
        for(int k = 0; k < str.length; k++){
            if(!visited[k]){
                visited[k] = true;
                backtracking(cnt + 1, tmp + str[k]);
                visited[k] = false;
            }
        }
    }

    static void permutation(String target, int count){
        str = target.toCharArray();
        visited = new boolean[target.length()];
        tar_cnt = count;
        backtracking(0, "");
    }

    public static void main(String[] args){
        System.out.println("-- 3 P 2 With ABC --");
        permutation("ABC", 2); // 3 P 2 = 6
        System.out.println("-- 3! With ABC --");
        permutation("ABC", 3); // 3! = 3
        System.out.println("-- 4 P 1 With ABCD --");
        permutation("ABCD", 1); // 4 P 1 = 4
        System.out.println("-- 4 P 2 With ABCD --");
        permutation("ABCD", 2); // 4 P 2 = 12
        System.out.println("-- 4 P 3 With ABCD --");
        permutation("ABCD", 3); // 4 P 3 = 24
        System.out.println("-- 4! With ABCD --");
        permutation("ABCD", 4); // 4! = 24
    }
}
