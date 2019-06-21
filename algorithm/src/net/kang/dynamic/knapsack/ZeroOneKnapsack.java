package net.kang.dynamic.knapsack;

import java.util.Arrays;
import java.util.Random;

// 0-1 배낭(Knapsack) 알고리즘
// 여기엔 다루진 않지만, Backtracking 으로 Tree 를 까는 방법으로 풀이할 수 있습니다.
// 그렇지만 이는 시간 복잡도가 O(2N) 이 걸리기 때문에 성능이 안 좋습니다.
public class ZeroOneKnapsack {
    static final int SIZE = 30, SCORE_BOUND = 100, WEIGHT_BOUND = 20;

    static Random random;
    static Knapsack[] knapsacks;
    static int limit_w;

    static class Knapsack {
        int score;
        int weight;

        public Knapsack(int score, int weight){
            this.score = score;
            this.weight = weight;
        }

        @Override
        public String toString(){
            return String.format("{ \"점수\" : %d, \"무게\" : %d }", this.score, this.weight);
        }
    }

    static void initialize(){
        knapsacks = new Knapsack[SIZE];
        random = new Random();
        for(int k = 0; k < SIZE; k++){
            int score = random.nextInt(SCORE_BOUND) + 1;
            int weight = random.nextInt(WEIGHT_BOUND) + 1;
            knapsacks[k] = new Knapsack(score, weight);
        }

        limit_w = (random.nextInt(WEIGHT_BOUND) + 1) * (random.nextInt(SIZE) + 1);
    }

    // Dynamic Programming 으로 풀이하는 방법.
    // 시간 복잡도는 O(N * W). 공간 복잡도는 O(N * W).
    static int zero_one_knapsack_dp(){
        int[][] dp = new int[SIZE + 1][limit_w + 1];
        for(int k = 0; k <= SIZE; k++){
            for(int w = 0; w <= limit_w; w++){
                if(k == 0 || w == 0)
                    dp[k][w] = 0;
                else if(knapsacks[k - 1].weight <= w)
                    dp[k][w] = Math.max(dp[k - 1][w], knapsacks[k - 1].score + dp[k - 1][w - knapsacks[k - 1].weight]);
                else
                    dp[k][w] = dp[k - 1][w];
            }
        }

        return dp[SIZE][limit_w];
    }

    public static void main(String[] args){
        initialize();
        System.out.println(Arrays.toString(knapsacks)); // 초기 배열 값 출력
        System.out.println(limit_w);
        System.out.println(zero_one_knapsack_dp());
    }
}
