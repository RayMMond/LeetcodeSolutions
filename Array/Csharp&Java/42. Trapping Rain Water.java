class Solution {
    public int trap(int[] height) {
        int rainWater = 0;
        int left = 0;
        int right = height.length - 1;
        int leftMax = left;
        int rightMax = right;

        while (left < right) {
            if (height[left] <= height[right]) {
                if (height[left] > height[leftMax])
                    leftMax = left;
                else
                    rainWater += height[leftMax] - height[left];
                left++;
            } else {
                if (height[right] > height[rightMax])
                    rightMax = right;
                else
                    rainWater += height[rightMax] - height[right];

                right--;
            }
        }

        return rainWater;
    }
}

//总结：核心是逐个求柱体能容纳的水 + 双指针到最高点合并
//我自己的解法太慢吗，讨论区看到了这个解法，但是没办法深入通过这个解法理解一些东西，时间所限，到此为止了