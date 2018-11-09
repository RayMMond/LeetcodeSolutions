class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int left = i + 1;
                int right = nums.length - 1;
                int sum = 0 - nums[i];
                while (left < right) {
                    if (nums[left] + nums[right] == sum) {
                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        left++;
                        right--;
                    } else if (nums[left] + nums[right] < sum) {
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        left++;
                    } else {
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        right--;
                    }
                }
            }
        }
        return result;
    }
}


//总结：从讨论区得到灵感，可以先对数组进行排序
//分析过程：本题所有解的可能性只有四种（+代表正数，-代表负数）
//1.[0,0,0]
//2.[-,0,+]
//3.[-,+,+]
//4.[-,-,+]
//先分析几种特殊情况：
//1.数量小于3，无解
//2.全部元素大于0或小于0，无解
//排除以上特殊情况，所有解的第一个元素为-或者0，如果数组已排序那么：
//若A[i]<=0 则 A[i]可能为解的第一个元素，只需要在[i+1,len-1](len为数组长度)的范围中中找到A[j] + A[k] = 0 - A[i]，则A[i],A[j],A[k]是一个有效解
//令j=i+1, k=len-1, 则A[j]+A[k]又三种可能：
//1.A[j] + A[k] < 0 - A[i]  ==> A[j] + A[k] 需要增大 因为j < k 且 A[j] < A[k] 所以A[j+1] + A[k]为下一组可能解
//2.A[j] + A[k] > 0 - A[i]  ==> A[j] + A[k] 需要减小, 同上， A[j] + A[k-1]为下一组可能解
//3.A[j] + A[k] == 0 - A[i] ==> A[j] + A[k] 刚好合适，是一组解，下一组可能解为 A[j+1] + A[k-1]