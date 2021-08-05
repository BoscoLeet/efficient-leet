import java.util.*;

public class Dijkstra {
    private int mNodeCount;

    private Set<Integer> mVisitedNodeSet;

    private Set<Integer> mUnVisitedNodeSet;

    private Map<Integer, Integer> mWeightMap;

    private Map<Integer, List<Path>> mPathMap;

    public Dijkstra(final int n) {
        mUnVisitedNodeSet = new HashSet<>(n);
        mWeightMap = new HashMap<>(n);
        mPathMap = new HashMap<>(n);
        for (int i = 1; i <= n; i++) {
            mUnVisitedNodeSet.add(i);
            mWeightMap.put(i, Integer.MAX_VALUE);
        }
    }

    public int totalWeight() {
        // 实现遍历一次的时间
        return -1;
    }

    public Dijkstra withPath(final int from, final int to, final int weight) {
        List<Path> pathList = mPathMap.getOrDefault(from, new ArrayList<>());
        pathList.add(new Path(from, to, weight));
        mPathMap.put(from, pathList);
        return this;
    }

    private static class Path {
        int mFrom;

        int mTo;

        int mWeight;

        public Path(int from, int to, int weight) {
            mFrom = from;
            mTo = to;
            mWeight = weight;
        }
    }
}
