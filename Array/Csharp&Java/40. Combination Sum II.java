class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        return getResult(candidates, 0, target);
    }

    static List<List<Integer>> getResult(int[] candidates, int startIndex, int target) {
        List<List<Integer>> results = new LinkedList<>();
        if (startIndex < candidates.length) {
            if (target >= candidates[startIndex]) {
                for (int i = startIndex; i < candidates.length; i++) {
                    if (i > startIndex && candidates[i] == candidates[i - 1])
                        continue;
                    if (candidates[i] < target) {
                        for (List<Integer> list : getResult(candidates, i + 1, target - candidates[i])) {
                            list.add(0, candidates[i]);
                            results.add(list);
                        }
                    } else if (candidates[i] == target) {
                        results.add(new LinkedList<>(Arrays.asList(candidates[i])));
                    } else {
                        break;
                    }
                }
            }
        }
        return results;
    }
}
//比较简单
//1.通过i > startIndex && candidates[i] == candidates[i - 1] 确保不出现重复解
//2.通过getResult(candidates, i + 1, target - candidates[i])中的i + 1 确保每个元素使用一次