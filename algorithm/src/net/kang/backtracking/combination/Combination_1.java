package net.kang.backtracking.combination;

// 조합 알고리즘 With Backtracking
// 굳이 순서대로 출력하는 것이면 visited 배열을 사용해야 할까?
public class Combination_1 {
    static int tar_cnt;
    static char[] str;
    static boolean[] visited;

    static void backtracking(int cnt, int idx, String tmp){
        if(cnt == tar_cnt){
            System.out.println(tmp);
            return;
        }
        for(int k = idx; k < str.length; k++) {
            if (!visited[k]) {
                visited[k] = true;
                backtracking(cnt + 1, k, tmp + str[k]);
                visited[k] = false;
            }
        }
    }

    static void combination(String target, int count){
        str = target.toCharArray();
        visited = new boolean[target.length()];
        tar_cnt = count;
        backtracking(0, 0, "");
    }

    public static void main(String[] args){
        System.out.println("-- 3 C 2 With ABC --");
        combination("ABC", 2); // 3 C 2 = 3
        System.out.println("-- 3 C 3 With ABC --");
        combination("ABC", 3); // 3 C 3 = 1
        System.out.println("-- 4 C 1 With ABCD --");
        combination("ABCD", 1); // 4 C 1 = 4
        System.out.println("-- 4 C 2 With ABCD --");
        combination("ABCD", 2); // 4 C 2 = 12
        System.out.println("-- 4 C 3 With ABCD --");
        combination("ABCD", 3); // 4 C 3 = 4
        System.out.println("-- 4 C 4 With ABCD --");
        combination("ABCD", 4); // 4! = 24
    }
}
