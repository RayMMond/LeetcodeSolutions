class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        return getResult(candidates, 0, target);
    }

    static List<List<Integer>> getResult(int[] candidates, int startIndex, int target) {
        List<List<Integer>> results = new LinkedList<>();
        if (startIndex < candidates.length) {
            if (target >= candidates[startIndex]) {
                for (int i = startIndex; i < candidates.length; i++) {
                    if (candidates[i] < target) {
                        for (List<Integer> list : getResult(candidates, i, target - candidates[i])) {
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

//分析：首先题目应该说明candidates中没有0，不然就有无限可能的解
//本题核心思想是分治法然后递归
//假设候选数组A为为a1,a2...an，且A升序排列
//那么假设a1是所有可能解中其中一个到多个解的第一个元素，那么它满足两个条件
//1.a1 不大于 target
//2.a1~an中能找到一个或多个(s1,s2..sn)使target - a1 = Sum(s1,s2...sn);
//按上面2的过程其实和题目要求一样，所以用递归的方法
//在遍历A的所有元素后就能找到所有解了
//对上面1的特殊情况a1 == target 由于数组是升序排列，那么说明不需要继续递归了，也就是递归的终止条件