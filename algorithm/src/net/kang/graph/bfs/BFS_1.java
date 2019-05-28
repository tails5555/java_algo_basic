package net.kang.graph.bfs;

import java.util.LinkedList;
import java.util.Queue;

// BFS_1 (Breadth 1st Search) 알고리즘
// Vertex Matrix 를 활용 방법
// 희소 행렬 그래프는 List 를 사용하는 것이 좋습니다.
// 시간 복잡도는 O(V2)
public class BFS_1 {
    // boolean true, false 로 희소 행렬을 쓰면 파악하기 힘들어 상수를 일부러 만들었습니다.
    static final boolean O = true;
    static final boolean X = false;

    static final int N = 7;
    static final int S = 1; // 시작점에 따라 순회 방법이 달라집니다.

    static boolean[][] graph = new boolean[][] {
        { X, X, X, X, X, X, X, X },
        { X, X, O, X, X, O, X, X },
        { X, O, X, X, O, X, X, O },
        { X, X, X, X, X, O, O, O },
        { X, X, O, X, X, X, X, O },
        { X, O, X, O, X, X, X, X },
        { X, X, X, O, X, X, X, O },
        { X, X, O, O, O, X, O, X }
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
            for(int v = 1; v <= N; v++){
                if(!visited[v] && graph[cur][v]){
                    queue.offer(v);
                    visited[v] = true;
                }
            }
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args){
        bfs();
    }
}
