class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int i = 0;
        int diff = Integer.MAX_VALUE;
        int ret = 0;
        while (i < nums.length - 2) {
            if (i == 0 || (i > 0 && (nums[i] != nums[i - 1]))) {
                int j = i + 1;
                int k = nums.length - 1;
                int value = target - nums[i];
                while (j < k) {
                    if (nums[j] + nums[k] > value) {
                        if (Math.abs(nums[i] + nums[j] + nums[k] - target) < diff) {
                            diff = Math.abs(nums[i] + nums[j] + nums[k] - target);
                            ret = nums[i] + nums[j] + nums[k];
                        }
                        while (j < k && nums[k] == nums[k - 1]) k--;
                        k--;
                    } else if (nums[j] + nums[k] < value) {
                        if (Math.abs(nums[i] + nums[j] + nums[k] - target) < diff) {
                            diff = Math.abs(nums[i] + nums[j] + nums[k] - target);
                            ret = nums[i] + nums[j] + nums[k];
                        }
                        while (j < k && nums[j] == nums[j + 1]) j++;
                        j++;
                    } else {
                        return target;
                    }
                }
            }
            i++;
        }

        return ret;
    }
}

//总结：与15. 3Sum类似