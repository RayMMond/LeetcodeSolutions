class Solution {
    public int removeDuplicates(int[] nums) {
        int trueIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[trueIndex]){
                nums[++trueIndex] = nums[i];
            }
        }
        
        return trueIndex + 1;
    }
}

//比较简单就不总结了