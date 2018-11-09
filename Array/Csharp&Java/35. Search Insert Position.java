class Solution {
    public int searchInsert(int[] nums, int target) {
        if (nums.length > 0) {
            int left = 0;
            int right = nums.length - 1;
            while (left < right) {
                if (target < nums[left]) return Math.max(0, left - 1);
                else if (nums[left] == target) return left;
                else if (nums[right] < target) return right + 1;
                else if (nums[right] == target) return right;
                else {
                    if (right - left < 2) return left + 1;
                    else {
                        int mid = (left + right) / 2;
                        if (nums[mid] == target) return mid;
                        else {
                            if (nums[left] < target && target < nums[mid]) right = mid;
                            else left = mid;
                        }
                    }
                }
            }
            if (target < nums[left]) return 0;
            else if (nums[left] == target) return left;
            else return left + 1;
        }
        return 0;
    }
}
//总结：依然是二分查找的变体，比较简单