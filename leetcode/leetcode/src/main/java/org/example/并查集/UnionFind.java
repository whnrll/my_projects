package org.example.并查集;

/**
 * 描述：并查集API实现
 *
 * @author xutao
 * @date 2023-07-22 17:12:56
 * @since 1.0.0
 */
public class UnionFind {

    /**
     * 连通分量
     */
    private int count;

    /**
     * 图中的节点的父节点
     */
    private int[] parents;

    public UnionFind(int n) {
        // 初始时，图中的节点都不连通，联通分量的个数等于图中的节点数
        this.count = n;
        this.parents = new int[n];
        for (int i = 0; i < parents.length; i++) {
            // 初始时，图中的节点都不连通，节点的父节点就是自己
            parents[i] = i;
        }
    }

    /**
     * 描述：返回图中有多少个联通分量
     *
     * @return int
     */
    public int count() {
        return count;
    }

    /**
     * 描述：将节点p和节点q连接
     *
     * @param p p
     * @param q q
     */
    public void union(int p, int q) {
        // 分别找到两个节点的根节点
        int rootP = find(p);
        int rootQ = find(q);

        // 将p的根节点的根节点连到q的根节点
        parents[rootP] = rootQ;

        // 每连通一个节点，连通分量-1
        count--;
    }

    /**
     * 描述：检查p和q是否连通
     *
     * @param p
     * @param q
     * @return boolean
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 描述：查找节点p挂在哪个节点。递归找到最上层的节点
     *
     * @param p
     * @return int
     */
    private int find(int p) {
        // 当p节点的根节点不是自己，一直递归的查找到最顶层的节点，然后顶层节点的根节点的通过方法返回值，赋值给每一层节点。
        // 比如查询p1的根节点 p1->p2->p3->p4
        //
        // p1->p2
        //     p2->p3
        //         p3->p4
        //             p4->p4
        //         p3->p4
        //     p2->p4
        // p1->p4
        // 最后返回p4

        while (parents[p] != p) {
            parents[p] = find(parents[p]);
        }

        return parents[p];
    }
}
