class Solution{
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; ++i)
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i])
                swap(nums, i, nums[i] - 1);

        for (int i = 0; i < nums.length; ++i)
            if (nums[i] != i + 1)
                return i + 1;

        return nums.length + 1;
    }

    public void swap(int[] nums, int x, int y) {
        if (x != y) {
            nums[x] ^= nums[y];
            nums[y] ^= nums[x];
            nums[x] ^= nums[y];
        }
    }
}

//查看了讨论区的解答，使时间复杂度为O(n)的关键：
//数组的索引一定是从最小的正整数开始的，所以将数组内的满足 0<值<len(A)的数放入相应的位置，那么第一个不满足A[i] = i的就是缺少的数组
//方法确实很巧妙，要将数组索引和题目要求的求最小正整数过程联系起来才能想出解法