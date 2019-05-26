package net.kang.backtracking.permutation;

// 순열 알고리즘 With Backtracking
// 순열 결과 출력도 백트래킹으로 처리하여 사용 메모리를 최소화 시킴.
public class Permutation_2 {
    static char[] str;
    static char[] print_str;
    static boolean[] visited;

    static void backtracking(int cnt){
        if(cnt == print_str.length){
            System.out.println(new String(print_str));
            return;
        }
        for(int k = 0; k < str.length; k++){
            if(!visited[k]){
                visited[k] = true;
                print_str[cnt] = str[k];
                backtracking(cnt + 1);
                visited[k] = false;
                print_str[cnt] = ' ';
            }
        }
    }

    static void permutation(String target, int count){
        str = target.toCharArray();
        visited = new boolean[target.length()];
        print_str = new char[count];
        backtracking(0);
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
