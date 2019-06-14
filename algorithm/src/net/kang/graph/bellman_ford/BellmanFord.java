package net.kang.graph.bellman_ford;

import java.util.Arrays;
import java.util.List;

// 벨만-포드 알고리즘(Bellman-Ford Algorithm)
// 시간 복잡도는 O(V * E) 로 해결할 수 있습니다. 다만 다익스트라는 음의 가중치를 사용할 수 없는 단점이 존재합니다.
// 우선 모든 정점들을 방문하고, 음수로 해결 못 한 정점 간 거리를 계산하여 처리합니다.
// 그렇지만, 음수 사이클이 존재하는 그래프는 벨만 포드 알고리즘을 사용해도 못 풀게 됩니다. 주의하시길 바랍니다.
public class BellmanFord {
    static final int INF = 987654321;
    static final int N = 7;
    static final int S = 1; // 시작점에 따라 각 정점 별 거리가 달라집니다.

    static List<Edge>[] graph;
    static class Edge {
        int to;
        int weight;
        public Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }
    
    static void first_initialize(){
        graph = new List[N + 1];
        graph[1] = Arrays.asList(new Edge(2, 10), new Edge(4, 8));
        graph[2] = Arrays.asList(new Edge(3, 4), new Edge(5, 4));
        graph[3] = Arrays.asList(new Edge(1, 7), new Edge(6, 5));
        graph[4] = Arrays.asList(new Edge(1, 8), new Edge(7, -6));
        graph[5] = Arrays.asList(new Edge(2, 4));
        graph[6] = Arrays.asList(new Edge(3, 5), new Edge(7, 2));
        graph[7] = Arrays.asList(new Edge(4, 6), new Edge(6, 2));
    }

    static void bellman_ford(){
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[S] = 0;
        boolean minus_cycle = false; // 음수 사이클 존재 여부 확인을 위한 변수.

        // Cycle 이 존재하지 않는 Tree 기반 경로를 찾기 위해 (정점 - 1) 만큼 경로를 둘러야 함.
        // 마지막 Loop 에도 최소값이 존재하면, 이는 음수 사이클에 갖힌 것이기 때문에 반환해서 예외 처리해야 함.
        for(int k = 0; k < N; k++){
            for(int v = 1; v <= N; v++){ // 여기서는 V 만큼 반복하는 것으로 보이지만, 실제로는 E 를 찾기 위해 도는 Loop 임. 그래서 E 만큼 반복한다고 봐야 함.
                for(Edge e : graph[v]) {
                    int next = e.to;
                    int weight = e.weight;
                    if(dist[v] != INF && dist[next] > dist[v] + weight) {
                        if(k == N - 1) {
                            minus_cycle = true;
                        }
                        dist[next] = dist[v] + e.weight;
                    }
                }
            }
            if(minus_cycle) break;
        }

        if(minus_cycle) { // 음수 사이클 존재 시.
            System.out.println("Graph Has Minus Cycle.");
        } else { // S 지점부터 ? 지점까지 최소 거리 출력.
            for (int v = 1; v <= N; v++) {
                System.out.printf("%d -> %d min : %d\n", S, v, dist[v]);
            }
        }
    }

    public static void main(String[] args){
        first_initialize();
        bellman_ford();
    }
}
