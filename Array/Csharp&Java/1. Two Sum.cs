public class Solution {
    public int[] TwoSum (int[] nums, int target) {
        for (int i = 0; i < nums.Length; i++) {
            for (int j = i + 1; j < nums.Length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] { i, j };
                }
            }
        }
        throw new Exception ("Should Not Exists");
    }

    public int[] TwoSumHashTable (int[] nums, int target) {
        Dictionary<int, int> ht = new Dictionary<int, int> ();
        for (int i = 0; i < nums.Length; i++) {
            if (ht.ContainsKey (target - nums[i])) {
                return new int[] { ht[target - nums[i]], i };
            }

            if (ht.ContainsKey (nums[i]))
                ht[nums[i]] = i;
            else
                ht.Add (nums[i], i);
        }
        throw new Exception ("Should Not Exists");
    }
}

// 两种实现方式
// 1.上述暴力查询法
// 2.Hash Table 法 具体如下

// 总结：
// 第二种方法将原来的比较问题，转化为查询问题，这种思想可以借鉴