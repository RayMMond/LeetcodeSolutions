class Solution {
    public int jump(int[] nums) {
        int jumps = 0;
        int i = 0;
        while (i < nums.length - 1) {
            int bestMove = 0;
            int bestMoveIndex = -1;
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j >= nums.length - 1) return jumps + 1;
                if (nums[i + j] + j >= bestMove) {
                    bestMove = nums[i + j] + j;
                    bestMoveIndex = i + j;
                }
            }
            i = bestMoveIndex;
            jumps++;
        }
        return jumps;
    }
}

//总结：讨论区有个O(n)的解法，后续补充