package net.kang.graph.spanning_tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

// Kruskal Algorithm
// 이를 구현하기 위해 Disjoint Set 를 구현해야 한다.
// 시간 복잡도는 O(E log E). Disjoint Set 의 시간 복잡도는 O(V) 이지만, V < E < V (V + 1) / 2 이 대부분.
public class KruskalAlgorithm {
    static final int VTX_SIZE = 7;
    static Map<Integer, List<Edge>> graph;

    // Kruskal Algorithm 에서 사용할 간선(Edge) 자료구조.
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

    // Disjoint Set 자료구조
    static class DisjointSet {
        int[] ele;
        public DisjointSet(int size){
            this.ele = new int[size + 1];
            for(int k = 1; k <= size; k++){
                ele[k] = k;
            }
        }

        public int find(int e){
            if(ele[e] == e) return e;
            ele[e] = find(ele[e]);
            return ele[e];
        }

        public boolean union(int a, int b){
            int root_a = find(a);
            int root_b = find(b);
            if(root_a == root_b) return false;
            else {
                ele[root_a] = b;
                return true;
            }
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

    static void kruskal_algorithm(){
        DisjointSet set = new DisjointSet(VTX_SIZE);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int v = 1; v <= VTX_SIZE; v++){
            Iterator<Edge> iter = graph.get(v).iterator();
            while(iter.hasNext()){
                Edge next = iter.next();
                pq.offer(next);
            }
        }

        List<Edge> mst_edges = new LinkedList<>();
        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            int a = set.find(cur.from);
            int b = set.find(cur.to);
            if(a != b) {
                mst_edges.add(cur);
                set.union(a, b);
            }
        }

        for(Edge e : mst_edges){
            System.out.println(e);
        }
    }

    public static void main(String[] args){
        initialize();
        kruskal_algorithm();
    }
}
