public class Solution{

    public static int maxAreaMy(int[] height) {
        int len = height.length;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                int max = getArea(height, i, j);
                if (max > maxValue) {
                    maxValue = max;
                }
            }
        }

        return maxValue;
    }

    public static int getArea(int[] a, int i, int j) {
        return Math.abs(j - i) * Math.min(a[i], a[j]);
    }

    public static int maxAreaLeetCode(int[] height){
        int left = 0, right = height.length - 1;
        int maxArea = Integer.MIN_VALUE;
        while(left < right){
            maxArea = Math.max(maxArea, (right - left)*Math.min(height[left], height[right]));
            if (height[left] < height[right]){
                left++;
            }
            else{
                right--;
            }
        }

        return maxArea;
    }


}
//总结：我采用的是暴力法，求解数组内所有值的两两组合出的面积，然后求最大，LeetCode视为TLE
//Code的解法：采用反证法，如果采用上面的方法得到的不是最优解，那么必定存在一个其他的最优解，且这个最优解的值不在这个解法经过的所有值中，又因为解法遍历整
//个数组，所以这个没找到的最优解一定满足：
//1.算法没有经过这对最优解 2.left和right指针只经过其中一个(left为算法的左边索引，right为算法的右边索引)

//假设：这个最优解的left和right分别为realLeft和realRight，且算法经历过realLeft，那么当left=realLeft时，上面的算法存在以下两种情况：

//1.left没有移动，那么left一直是realLeft，也就是height[right]一直小于height[left]，根据算法right会遍历realLeft到n-1所有元素，这和假设2不符

//2.left有移动，也就是height[right] > height[realLeft], 假设当前的right = tmpRight,根据假设right没有经过realRight所以 realRight < tmpRight => 最优解的长度 < 当前长度,
//又因为最优解的高度 <= height[realLeft]，所以最优解面积比当前realLeft到tmpRight的面积小，与假设矛盾
