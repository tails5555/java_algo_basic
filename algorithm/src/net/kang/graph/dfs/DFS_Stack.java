package net.kang.graph.dfs;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

// DFS(Depth 1st Search) Stack 자료구조를 사용한 구현.
// 개인적으로 Stack 보단 Recursive 방법이 더 잘 먹힐 수 있습니다.
public class DFS_Stack {
    static final int N = 7;
    static final int S = 1; // 시작점에 따라 순회가 달라집니다.

    // Stack 으로 DFS 를 구현하기 위해 방문 정점 순서를 뒤집어야 재귀 구현 방법과 같은 결과가 나옵니다.
    static List<Integer>[] graph = new List[] {
        null,
        Arrays.asList(5, 2),
        Arrays.asList(7, 4, 1),
        Arrays.asList(7, 6, 5),
        Arrays.asList(7, 2),
        Arrays.asList(3, 1),
        Arrays.asList(7, 3),
        Arrays.asList(6, 4, 3, 2)
    };

    // DFS 는 방문 정점이 false 인 경우 곧장 방문 처리를 하고, 인접한 정점으로 Push 를 진행합니다.
    static void dfs_stack(){
        boolean[] visited = new boolean[N + 1];

        Stack<Integer> stack = new Stack<>();
        stack.push(S);

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            int cur = stack.pop();
            if(!visited[cur]) {
                sb.append(String.format("%d ", cur));
                visited[cur] = true;
                for (int next : graph[cur]) {
                    if (!visited[next]) {
                        stack.push(next);
                    }
                }
            }
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args){
        dfs_stack();
    }
}
