package net.kang.graph.topological;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 위상 정렬(Topological Sort) BFS 방법.
// 1 -> 3 -> 4 -> 6 -> 2 -> 5 -> 7 순으로 나온다.
public class Topological_BFS {
    static final int N = 7;

    static List<Integer>[] graph;
    static int[] degree;

    static void topological_bfs(){
        degree = new int[N + 1];
        for(int k = 1; k <= 7; k++){
            for(int v : graph[k]){
                degree[v] += 1;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int v = 1; v <= N; v++){
            if(degree[v] == 0)
                queue.offer(v);
        }

        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()){
            int cur = queue.poll();
            sb.append(String.format("%d ", cur));
            for(int v : graph[cur]){
                degree[v] -= 1;
                if(degree[v] == 0)
                    queue.offer(v);
            }
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
        topological_bfs();
    }
}
