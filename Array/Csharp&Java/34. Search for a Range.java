class Solution {
    public int[] searchRange(int[] nums, int target) {
        int index = Arrays.binarySearch(nums, target);
        if (index < 0) {
            return new int[]{-1, -1};
        }
        int from = index, to = index;
        while (from - 1 >= 0 && nums[from - 1] == target) {
            from--;
        }
        while (to + 1 < nums.length && nums[to + 1] == target) {
            to++;
        }
        int[] result = new int[2];
        result[0] = from;
        result[1] = to;
        return result;
    }
}
//easy peasy