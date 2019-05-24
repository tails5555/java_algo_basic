package net.kang.tree;

// 이진 탐색 트리 구현
// 시간 복잡도는 삽입, 삭제는 O(log N) 최악은 O(N)
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value){
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public void print(){
        if(this.left != null) this.left.print();
        System.out.printf("%d ", this.value);
        if(this.right != null) this.right.print();
    }

    public int getLeftMostValue() {
        if (this.left != null) return this.left.getLeftMostValue();
        return this.value;
    }

    public void add(int value){
        if(value < this.value)
            if(this.left == null)
                this.left = new Node(value);
            else
                this.left.add(value);
        else if(value > this.value)
            if(this.right == null)
                this.right = new Node(value);
            else
                this.right.add(value);
    }

    public void remove(int value, Node parent){
        if(value < this.value){
            if(this.left != null)
                this.left.remove(value, this);
        } else if(value > this.value){
            if(this.right != null)
                this.right.remove(value, this);
        } else {
            if(this.left != null && this.right != null) {
                int tmp = this.right.getLeftMostValue();
                this.value = tmp;
                this.right.remove(tmp, this);
            } else {
                Node child = (this.left != null) ? this.left : this.right;
                if (parent.left == this)
                    parent.left = child;
                else
                    parent.right = child;
            }
        }
    }
}

public class BinarySearchTree {
    public static void main(String[] args){
        Node node = new Node(10);
        node.add(5);
        node.add(3);
        node.add(8);
        node.add(15);
        node.add(12);
        node.add(18);

        node.print();
        System.out.println();

        node.remove(12, node);
        node.print();
        System.out.println();
    }
}
