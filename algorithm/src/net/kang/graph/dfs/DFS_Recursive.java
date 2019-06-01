package net.kang.graph.dfs;

import java.util.Arrays;
import java.util.List;

// DFS(Depth 1st Search) 재귀 함수를 사용한 구현.
// 정점 순서를 걱정하지 않아도 됩니다. 그래서 개인적으로 재귀 함수를 사용하여 구현하는 것을 추천.
public class DFS_Recursive {
    static final int N = 7;
    static final int S = 1; // 시작점에 따라 순회가 달라집니다.

    static List<Integer>[] graph = new List[] {
        null,
        Arrays.asList(2, 5),
        Arrays.asList(1, 4, 7),
        Arrays.asList(5, 6, 7),
        Arrays.asList(2, 7),
        Arrays.asList(1, 3),
        Arrays.asList(3, 7),
        Arrays.asList(2, 3, 4, 6)
    };

    static boolean[] visited;
    static StringBuilder sb;

    static void dfs_recursive(int vtx){
        visited[vtx] = true;
        sb.append(String.format("%d ", vtx));
        for(int next : graph[vtx]){
            if(!visited[next]){
                dfs_recursive(next);
            }
        }
    }

    static void initialize(){
        visited = new boolean[N + 1];
        sb = new StringBuilder();
    }

    public static void main(String[] args){
        initialize();
        dfs_recursive(S);
        System.out.println(sb.toString());
    }
}
