class Solution {
    public int search(int[] nums, int target) {
        if (nums.length > 0) {
            int left = 0;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[left] == target) {
                    return left;
                } else if (nums[right] == target) {
                    return right;
                } else {
                    if (right - left < 2) {
                        return -1;
                    } else {
                        int mid = (left + right) / 2;
                        if (nums[mid] == target) {
                            return mid;
                        } else {
                            if (nums[left] < nums[mid]) {
                                if (nums[left] < target && target < nums[mid]) {
                                    return Math.max(-1, Arrays.binarySearch(nums, left, mid + 1, target));
                                } else {
                                    left = mid;
                                }
                            } else {
                                if (nums[mid] < target && target < nums[right]) {
                                    return Math.max(-1, Arrays.binarySearch(nums, mid, right + 1, target));
                                } else {
                                    right = mid;
                                }
                            }
                        }
                    }
                }
            }
            if (nums[left] == target)
                return left;
        }
        return -1;
    }
}

//总结：核心是根据题目要求对普通的二分查找进行修正（revised）即可，上面的代码是最基本的if else形式，存在优化空间
//在讨论区看到另一个问题，如果数组降序或升序该怎么实现？
//一个思路，通过判断nums[0]和nums[len-1]的大小来判断升序或降序，然后分别处理即可