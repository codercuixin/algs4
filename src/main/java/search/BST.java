package search;

import fundementals.Queue;

import java.util.LinkedList;
import java.util.List;

/**
 * * @Author: cuixin
 * * @Date: 2020/6/29 9:34
 */
public class BST<Key extends Comparable<Key>, Value> {
    //二叉查找树的根节点
    private Node root;

    private class Node {
        private Node left;
        private Node right;
        private Key key;
        private Value val;
        //以该节点为根的子树的节点总数
        private int N;

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    public int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = x.key.compareTo(key);
        if (cmp > 0) {
            return get(x.left, key);
        } else if (cmp < 0) {
            return get(x.right, key);
        } else {
            return x.val;
        }
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        //如果key存在于以x为跟节点的子树中则更新它的值
        //否则将以key和val为键值对的新节点插入到该子树中
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }


    public Key max() {
        //最大键需要不断查看右子树（如果有的话）
        if (root == null) {
            return null;
        }
        Node cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur.key;
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    // 在BST中找到小于等于key的最大节点
    public Key floor(Key key) {
        return floor(root, key);
    }

    // 在BST中找到小于等于key的最大节点
    private Key floor(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        //如果key值小于当前根节点，则在左节点中寻找floor节点
        if (cmp < 0) {
            return floor(node.left, key);
        } else {
            //如果给定的key值大于等于当前根节点，
            //那么只有当根节点右子树存在小于等于key的节点时，小于等于key的最大键才会出现在右子树中，
            // 否则根节点就是小于等于key的最大键。
            Key rightFloorKey = floor(node.right, key);
            return (rightFloorKey != null) ? rightFloorKey : node.key;
        }
    }

    // 在BST中找到大于等于key的最小节点
    public Key ceiling(Key key) {
        return ceiling(root, key);
    }

    private Key ceiling(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        //如果key值大于当前根节点，则在右节点中寻找ceiling节点
        if (cmp > 0) {
            return ceiling(node.right, key);
        } else {
            //如果给定的key值小于等于当前根节点，
            //那么只有当根节点左子树存在大于等于key的节点时，大于等于key的最大键才会出现在左子树中，
            // 否则根节点就是大于等于key的最小键。
            Key leftCeilingKey = ceiling(node.left, key);
            return (leftCeilingKey != null) ? leftCeilingKey : node.key;
        }
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        //1.如果key小于当前根节点，则递归在左子树中查找排名
        if (cmp < 0) {
            return rank(x.left, key);
        }
        //2.如果key等于当前根节点，则排名为左子树的节点个数
        else if (cmp == 0) {
            return size(x.left);
        }
        //3.如果key大于当前根节点，则递归在右子树中查找排名，且结果需要加上左子树个数，并加1
        else {
            return size(x.left) + 1 + rank(x.right, key);
        }
    }

    public Key select(int k) {
        return select(root, k);
    }

    private Key select(Node x, int k) {
        //寻找书中正好有小于k个小于他的键
        if (x == null) {
            return null;
        }
        int t = size(x.left);
        //1.如果给定的左子树的键个数大于k，则在左子树上递归查找
        if (t > k) {
            return select(x.left, k);
        }
        //2.如果给定的左子树的键个数等于k，则返回当前节点
        else if (t == k) {
            return x.key;
        }
        //3.如果给定的左子树的键个数小于k，则在右子树上查找排名为k-t-1的键
        else {
            return select(x.right, k - t - 1);
        }
    }

    public void delete(Key key) {

    }

    public Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        //先找到key
        if (cmp < 0) {
            return delete(x.left, key);
        } else if (cmp > 0) {
            return delete(x.right, key);
        } else {
            //1.如果要删除的key只有一个子节点
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            //2.如果要删除的key有两个子节点，使用右子树上的最小节点替换该节点（Hibbard的方法）
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        //不断深入地深入根节点的左子树中直到遇见一个空链接，然后将指向该节点的链接指向该节点的右子树（只需要在递归调用中返回他的右链接即可）
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        //更新计数值
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        //不断深入地深入根节点的右子树中直到遇见一个空链接，然后将指向该节点的链接指向该节点的左子树（只需要在递归调用中返回他的左链接即可）
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        //更新计数值
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Iterable<Key> keys() {
        List<Key> keys = new LinkedList<Key>();
        middleVisit(root, keys);
        return keys;
    }

    public void middleVisit(Node node, List<Key> keys) {
        if (node == null) return;
        middleVisit(node.left, keys);
        keys.add(node.key);
        middleVisit(node.right, keys);
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    public void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmpLo = x.key.compareTo(lo);
        int cmpHi = x.key.compareTo(hi);
        if (cmpLo > 0) {
            keys(x.left, queue, lo, hi);
        }
        if (cmpHi < 0) {
            keys(x.right, queue, lo, hi);
        }
        if (cmpLo >= 0 && cmpHi <= 0) {
            queue.enqueue(x.key);
        }
    }
}
