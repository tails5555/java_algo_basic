package net.kang.backtracking.combination;

// 조합 알고리즘 With Backtracking
// 파라미터에 문자열을 받은 것을 없애고, 백트래킹으로 저장하여 메모리 양을 최소화 함.
public class Combination_3 {
    static int tar_cnt;
    static char[] str;
    static char[] print_str;

    static void recursive(int cnt, int idx){
        if(cnt == tar_cnt){
            System.out.println(new String(print_str));
            return;
        }
        if(idx == str.length) return;

        print_str[cnt] = str[idx];
        recursive(cnt + 1, idx + 1);
        recursive(cnt, idx + 1);
        print_str[cnt] = ' ';
    }

    static void combination(String target, int count){
        str = target.toCharArray();
        print_str = new char[count];
        tar_cnt = count;

        recursive(0, 0);
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
