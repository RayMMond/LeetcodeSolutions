class Solution {
    public void nextPermutation(int[] nums) {
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                for (int j = nums.length - 1; j > i; j--) {
                    if (nums[j] > nums[i]){
                        int t = nums[i];
                        nums[i] = nums[j];
                        nums[j] = t;
                        Arrays.sort(nums, i + 1, nums.length);
                        return;
                    }
                }
            }
        }
        Arrays.sort(nums);
    }
}

//总结:
//核心是字典序算法，一开始我都不知道什么是字典序（lexicographic ），在网上看到一个Blog：https://www.cnblogs.com/darklights/p/5285598.html，用很好的方式描述了字典序算法
//上面只是一个简单的字典序实现