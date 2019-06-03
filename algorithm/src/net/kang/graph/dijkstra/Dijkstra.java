package net.kang.graph.dijkstra;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// 다익스트라 알고리즘
// 원래 시간 복잡도는 O(V2) 를 생각했지만, 우선 순위 큐를 활용하여 O((V + E) log V) 으로 줄였습니다.
// DFS 보다 Priority Queue 를 사용할 수 있는 BFS 를 더 사용하는 추세입니다.
public class Dijkstra {
    static final int INF = 987654321;
    static final int N = 7;
    static final int S = 1; // 시작점에 따라 각 정점 별 거리가 달라집니다.

    static List<Edge>[] graph;
    static class Edge implements Comparable<Edge> {
        int to;
        int weight;
        public Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static void first_initialize(){
        graph = new List[N + 1];
        graph[1] = Arrays.asList(new Edge(2, 10), new Edge(3, 7), new Edge(4, 8));
        graph[2] = Arrays.asList(new Edge(1, 10), new Edge(3, 4), new Edge(5, 4));
        graph[3] = Arrays.asList(new Edge(1, 7), new Edge(2, 4), new Edge(6, 5));
        graph[4] = Arrays.asList(new Edge(1, 8), new Edge(5, 3), new Edge(7, 6));
        graph[5] = Arrays.asList(new Edge(2, 4), new Edge(4, 3));
        graph[6] = Arrays.asList(new Edge(3, 5), new Edge(7, 2));
        graph[7] = Arrays.asList(new Edge(4, 6), new Edge(6, 2));
    }

    static void dijkstra(){
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(S, 0));

        dist[S] = 0;
        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            for(Edge next : graph[cur.to]){
                if(dist[next.to] > dist[cur.to] + next.weight){
                    dist[next.to] = dist[cur.to] + next.weight;
                    pq.offer(new Edge(next.to, dist[next.to]));
                }
            }
        }

        for(int v = 1; v <= N; v++){
            System.out.printf("%d -> %d min : %d\n", S, v, dist[v]);
        }
    }

    public static void main(String[] args){
        first_initialize();
        dijkstra();
    }
}
