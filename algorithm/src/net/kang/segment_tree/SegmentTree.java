package net.kang.segment_tree;

// 세그먼트 트리(Segment Tree)
// 구간에 대한 정보를 빠르게 구하기 위해 완전 이진트리 형식의 구조를 가지는 자료구조.
// 모든 값을 최하단(Leaf Node) 에 두고, 위로 올라오면서 원하는 연산을 처리합니다.
public class SegmentTree {
    private int[] tree;
    private int st;

    public SegmentTree(int size){
        int log2 = (int) (Math.log(size) / Math.log(2.0));
        st = (int) Math.pow(2, log2);
        tree = new int[st * 2];
        for(int k = 1; k < st * 2; k++)
            tree[k] = Integer.MAX_VALUE;
    }

    public void insert(int[] values) {
        for(int k = st; k < st + values.length; k++)
            tree[k] = values[k - st];
        for(int k = st - 1; k >= 1; k--) {
            tree[k] = Math.min(tree[k * 2], tree[k * 2 + 1]);
        }
    }

    public int get_min(int left, int right) {
        int l = left + st - 1;
        int r = right + st - 1;
        int res = Integer.MAX_VALUE;
        while(l <= r) {
            if(l % 2 == 0) l /= 2;
            else {
                res = Math.min(res, tree[l]);
                l = (l / 2) + 1;
            }

            if(r % 2 == 1) r /= 2;
            else {
                res = Integer.min(res, tree[r]);
                r = (r / 2) - 1;
            }
        }

        return res;
    }
}
