package net.kang.graph.spanning_tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

// Prim 알고리즘
// 최소 신장 트리를 만들기 위해선 그래프가 무방향으로 이뤄져야 한다.
public class PrimAlgorithm {
    static final int VTX_SIZE = 7;
    static Map<Integer, List<Edge>> graph;

    // Prim Algorithm 에서 사용할 간선(Edge) 자료구조.
    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            // Edge 객체 기반 Priority Queue 를 만들기 위해 가중치가 작은 순서의 Comparator 를 만든다.
            return this.weight - o.weight;
        }

        @Override
        public String toString() {
            return String.format("(%d) -- (W : %d) -- (%d)", this.from, this.weight, this.to);
        }
    }

    // 그래프 초기화
    static void initialize(){
        graph = new HashMap<>();
        graph.put(1, Arrays.asList(new Edge(1, 2, 5), new Edge(1, 3, 10), new Edge(1, 5, 8)));
        graph.put(2, Arrays.asList(new Edge(2, 1, 5), new Edge(2, 4, 7), new Edge(2, 6,  3)));
        graph.put(3, Arrays.asList(new Edge(3, 1, 10), new Edge(3, 4, 6), new Edge(3, 5, 9), new Edge(3, 7, 11)));
        graph.put(4, Arrays.asList(new Edge(4, 2, 7), new Edge(4, 3, 6), new Edge(4, 5, 2)));
        graph.put(5, Arrays.asList(new Edge(5, 1, 8), new Edge(5, 3, 9), new Edge(5, 4, 2)));
        graph.put(6, Arrays.asList(new Edge(6, 2, 3), new Edge(6, 7, 13)));
        graph.put(7, Arrays.asList(new Edge(7, 3, 11), new Edge(7, 6, 13)));
    }

    // 기록된 거리들 중에 거리가 가장 짧은 정점을 반환.
    static int extract_min(Set<Integer> visited, int[] dist){
        int min = Integer.MAX_VALUE;
        int vtx = -1;
        for(int v = 1; v <= VTX_SIZE; v++){
            if(!visited.contains(v) && dist[v] < min) {
                vtx = v;
                min = dist[v];
            }
        }

        return vtx;
    }

    // 방법 1. extract_min 함수 사용.
    // 모든 정점들을 방문해야 하는 구찮음 때문에 비효율적.
    // 시간 복잡도는 O(V2). 공간 복잡도는 O(V).
    static void prim_algorithm_extract(){
        int[] dist, tree;
        Set<Integer> visited;
        for(int s = 1; s <= VTX_SIZE; s++) {
            dist = new int[VTX_SIZE + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);

            visited = new HashSet<>();
            tree = new int[VTX_SIZE + 1];

            dist[s] = 0;
            while (visited.size() < VTX_SIZE) {
                int vtx = extract_min(visited, dist);
                visited.add(vtx);
                for(Edge next : graph.get(vtx)){
                    if(!visited.contains(next.to) && next.weight < dist[next.to]) {
                        dist[next.to] = next.weight;
                        tree[next.to] = vtx;
                    }
                }
            }

            System.out.printf("-- Starting To Making MST At %d --\n", s);
            for(int v = 1; v <= VTX_SIZE; v++){
                if(v != s) System.out.printf("(%d) -- (W : %d) -- (%d)\n", v, dist[v], tree[v]);
            }
            System.out.println();
        }
    }

    // 방법 2. 최소 힙(Min Heap). 즉 Priority Queue 를 사용하는 방법.
    // 최소 힙에 데이터를 추가하는 시간이 필요해서 O(log V) 만큼의 시간을 요구함.
    // 시간 복잡도는 O((E + V) * log E). 공간 복잡도는 O(V).
    // 참고로 이는 다익스트라 알고리즘의 원리가 약간 섞여 들어 있기도 함.
    static void prim_algorithm_heap(){
        PriorityQueue<Edge> pq;
        Set<Integer> visited;
        List<Edge> mst_edges;
        for(int s = 1; s <= VTX_SIZE; s++) {
            pq = new PriorityQueue<>();
            pq.offer(new Edge(Integer.MIN_VALUE, s, 0));

            visited = new HashSet<>();
            mst_edges = new ArrayList<>();

            while(!pq.isEmpty()){
                Edge cur = pq.poll();
                if(!visited.contains(cur.to)) {
                    visited.add(cur.to);
                    if (cur.from != Integer.MIN_VALUE) {
                        mst_edges.add(cur);
                    }
                    for (Edge next : graph.get(cur.to)) {
                        if(!visited.contains(next.to))
                            pq.offer(next);
                    }
                }
            }

            System.out.printf("-- Starting To Making MST At %d --\n", s);
            for(Edge e : mst_edges) {
                System.out.println(e);
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        initialize();

        System.out.println("[Prim MST With Extract Min]");
        prim_algorithm_extract();

        System.out.println("[Prim MST With Heap]");
        prim_algorithm_heap();
    }
}
