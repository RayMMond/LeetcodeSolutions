class Solution {
    public int rob(int[] nums) {
        Map<Integer, Integer> hashMap = new HashMap<>(nums.length);
        return rob_recursive(hashMap, nums, 0);
    }

    static int rob_recursive(Map<Integer, Integer> map, int[] nums, int start) {
        Integer v = map.get(start);
        if (v == null) {
            if (nums.length - start <= 0)
                return 0;
            else if (nums.length - start < 3)
                return Arrays.stream(Arrays.copyOfRange(nums, start, nums.length)).max().getAsInt();
            else if (nums.length - start == 3)
                return Math.max(nums[start] + nums[start + 2], nums[start + 1]);
            else {
                int takeCurrent = nums[start] + rob_recursive(map, nums, start + 2);
                int takeNext = nums[start + 1] + rob_recursive(map, nums, start + 3);
                int max = Math.max(takeCurrent, takeNext);
                map.put(start, max);
                return max;
            }
        } else {
            return v;
        }
    }
}

//典型的DP问题，算是比较简单的类型