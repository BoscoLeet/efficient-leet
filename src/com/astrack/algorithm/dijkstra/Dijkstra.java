import java.util.*;

public class Dijkstra {
    private int mNodeCount;

    private Set<Integer> mVisitedNodeSet;

    private Set<Integer> mUnVisitedNodeSet;

    private Map<Integer, Integer> mWeightMap;

    private Map<Integer, List<Path>> mPathMap;

    public Dijkstra(final int n) {
        mNodeCount = n;
        mVisitedNodeSet = new HashSet<>(n);
        mUnVisitedNodeSet = new HashSet<>(n);
        mWeightMap = new HashMap<>(n);
        mPathMap = new HashMap<>(n);
        for (int i = 1; i <= n; i++) {
            mUnVisitedNodeSet.add(i);
            mWeightMap.put(i, Integer.MAX_VALUE);
        }
    }

    public int totalWeight(final int beginNode) {
        mWeightMap.put(beginNode, 0);
        int cursor = beginNode;
        while (!mUnVisitedNodeSet.isEmpty()) {
            if (!mPathMap.containsKey(cursor)) {
                mVisitedNodeSet.add(cursor);
                mUnVisitedNodeSet.remove(cursor);
                if (mUnVisitedNodeSet.isEmpty()) {
                    break;
                }

                int result = Integer.MAX_VALUE;
                for (int entry : mUnVisitedNodeSet) {
                    if (mWeightMap.get(entry) < result) {
                        result = mWeightMap.get(entry);
                        cursor = entry;
                    }
                }
                if (result == Integer.MAX_VALUE) {
                    return -1;
                }
                continue;
            }

            List<Path> pathList = mPathMap.getOrDefault(cursor, new ArrayList<>());
            for (int i = 0, size = pathList.size(); i < size; i++) {
                int from = pathList.get(i).mFrom;
                int to = pathList.get(i).mTo;
                int weight = pathList.get(i).mWeight;
                int base = mWeightMap.get(from);
                int nextWeight = base + weight;
                if (nextWeight < mWeightMap.get(to)) {
                    mWeightMap.put(to, nextWeight);
                }
            }
            mVisitedNodeSet.add(cursor);
            mUnVisitedNodeSet.remove(cursor);
            if (mUnVisitedNodeSet.isEmpty()) {
                break;
            }

            int result = Integer.MAX_VALUE;
            for (int entry : mUnVisitedNodeSet) {
                if (mWeightMap.get(entry) < result) {
                    result = mWeightMap.get(entry);
                    cursor = entry;
                }
            }
            if (result == Integer.MAX_VALUE) {
                return -1;
            }

        }

        int result = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : mWeightMap.entrySet()) {
            result = Math.max(result, entry.getValue());
        }
        if (result == Integer.MAX_VALUE) {
            return -1;
        }
        return result;
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
