package leetcode.UnionFind;

public class UnionFindSet {
    private final boolean useRank;
    private final int[] parents;
    private int[] rank;

    public UnionFindSet(int size, boolean useRank) {
        parents = new int[size];
        for (int i = 0; i < size; i++) {
            parents[i] = i;
        }

        this.useRank = useRank;
        if (useRank) {
            rank = new int[size];
        }
    }

    public int find(int x) {
        if (x != parents[x]) {
            // path compression
            parents[x] = find(parents[x]);
        }
        return parents[x];
    }

    public void union(int x, int y) {
        if (useRank) {
            unionByRank(x, y);
        } else {
            unionNaively(x, y);
        }
    }

    private void unionNaively(int x, int y) {
        int rootOfX = find(x);
        int rootOfY = find(y);
        parents[rootOfY] = rootOfX;
    }

    private void unionByRank(int x, int y) {
        int rootOfX = find(x);
        int rootOfY = find(y);
        if (rank[rootOfX] < rank[rootOfY]) {
            parents[rootOfX] = rootOfY;
        } else if (rank[rootOfY] < rank[rootOfX]) {
            parents[rootOfY] = rootOfX;
        } else {
            parents[rootOfX] = rootOfY;
            rank[rootOfY]++;
        }
    }
}
