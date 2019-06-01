package net.kang.graph.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// BFS_1 (Breadth 1st Search) 알고리즘
// Vertex List 활용 방법
// 1번째 구현 방법보다 시간 복잡도가 꽤 줄어듭니다.
// 시간 복잡도는 O(V + E)
public class BFS_2 {
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

    static void bfs(){
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];

        queue.offer(S);
        visited[S] = true;

        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()){
            int cur = queue.poll();
            sb.append(String.format("%d ", cur));
            for(int next : graph[cur]) {
                if(!visited[next]){
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args){
        bfs();
    }
}
