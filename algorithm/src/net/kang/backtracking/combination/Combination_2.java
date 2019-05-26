package net.kang.backtracking.combination;

// 조합 알고리즘 With Backtracking
// visited 배열을 사용하지 않고 조합 구현.
public class Combination_2 {
    static int tar_cnt;
    static char[] str;

    static void recursive(int cnt, int idx, String tmp){
        if(cnt == tar_cnt){
            System.out.println(tmp);
            return;
        }
        if(idx == str.length) return;
        recursive(cnt + 1, idx + 1, tmp + str[idx]);
        recursive(cnt, idx + 1, tmp);
    }

    static void combination(String target, int count){
        str = target.toCharArray();
        tar_cnt = count;
        recursive(0, 0, "");
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
