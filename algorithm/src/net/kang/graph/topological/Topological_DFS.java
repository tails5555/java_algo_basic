package net.kang.graph.topological;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 위상 정렬(Topological Sort) DFS 방법.
// 1 -> 3 -> 6 -> 2 -> 5 -> 7 -> 4 순으로 나옵니다.
// 위상 별 순서만 보장되면 됩니다. 순서에는 정답이 따로 없습니다.
// 다만 DFS 로 처리할 때, 차수가 0 개인 정점을 중복 탐색하는 경우가 있어 visited 배열로 처리해야 합니다. 그래서 BFS 를 사용하는 것이 나을 수도...
public class Topological_DFS {
    static final int N = 7;

    static List<Integer>[] graph;
    static int[] degree;
    static boolean[] visited;
    static StringBuilder sb;

    static void dfs(int vtx){
        if(visited[vtx]) return;
        if(degree[vtx] == 0) {
            visited[vtx] = true;
            sb.append(String.format("%d ", vtx));
            for (int next : graph[vtx]) {
                if (degree[next] > 0) {
                    degree[next] -= 1;
                    dfs(next);
                }
            }
        }
    }

    static void topological(){
        degree = new int[N + 1];
        for(int k = 1; k <= 7; k++){
            for(int v : graph[k]){
                degree[v] += 1;
            }
        }

        sb = new StringBuilder();
        visited = new boolean[N + 1];
        for(int v = 1; v <= N; v++){
            if(degree[v] == 0) dfs(v);
        }

        System.out.println(sb.toString());
    }

    static void graph_initialize(){
        graph = new List[N + 1];
        for(int k = 1; k <= N; k++){
            graph[k] = new ArrayList<>();
        }

        // DAG 그래프. Directed Acyclic Graph. 방향은 있지만, 순환이 없는 그래프.
        graph[1].addAll(Arrays.asList(2, 3, 4));
        graph[2].addAll(Arrays.asList(5));
        graph[3].addAll(Arrays.asList(5, 6));
        graph[5].addAll(Arrays.asList(7));
        graph[6].addAll(Arrays.asList(2));
    }

    public static void main(String[] args){
        graph_initialize();
        topological();
    }
}
