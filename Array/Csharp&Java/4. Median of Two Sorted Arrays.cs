public class Solution {
    public double FindMedianSortedArrays (int[] nums1, int[] nums2) {
        int index1 = -1;
        int index2 = -1;
        int len1 = nums1.Length;
        int len2 = nums2.Length;
        bool isOdd = (len1 + len2) % 2 == 1;
        int loopCnt = isOdd ? ((len1 + len2) / 2 + 1) : (len1 + len2) / 2;

        double p = 0;
        for (int i = 0; i < loopCnt; i++) {
            int t1 = index1 + 1 < len1 ? nums1[index1 + 1] : int.MaxValue;
            int t2 = index2 + 1 < len2 ? nums2[index2 + 1] : int.MaxValue;

            if (index1 + 1 == len1)
                p = nums2[++index2];
            else if (index2 + 1 == len2)
                p = nums1[++index1];
            else if (nums1[index1 + 1] > nums2[index2 + 1])
                p = nums2[++index2];
            else
                p = nums1[++index1];
        }

        if (!isOdd) {
            int q = -1;
            int t1 = index1 + 1 < len1 ? nums1[index1 + 1] : int.MaxValue;
            int t2 = index2 + 1 < len2 ? nums2[index2 + 1] : int.MaxValue;

            if (index1 + 1 == len1)
                q = nums2[++index2];
            else if (index2 + 1 == len2)
                q = nums1[++index1];
            else if (nums1[index1 + 1] > nums2[index2 + 1])
                q = nums2[++index2];
            else
                q = nums1[++index1];

            p = (p + q) / 2;
        }

        return p;
    }

    public double FindMedianSortedArraysRecursive (int[] nums1, int[] nums2) {
        int m = nums1.Length;
        int n = nums2.Length;
        if (m > n) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }

        int iMax = m;
        int iMin = 0;
        int halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i > iMin && nums1[i - 1] > nums2[j]) {
                iMax--;
            } else if (i < iMax && nums2[j - 1] > nums1[i]) {
                iMin++;
            } else {
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max (A[i - 1], B[j - 1]);
                }
                if ((m + n) % 2 == 1) { return maxLeft; }

                int minRight = 0;
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min (B[j], A[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }

        }
        return 0;
    }
}

// LeetCode上的解法：
// To solve this problem, we need to understand "What is the use of median". In statistics, the median is used for:
// Dividing a set into two equal length subsets, that one subset is always greater than the other.
// If we understand the use of median for dividing, we are very close to the answer.

//核心是理解中位数的作用即将一个数组分为等长的两个数组，
//那么本题求解两个数组的中位数->即求解将两个数组的合并数组分为两个等长数组的那个数值
//我的方法：对两个数组的逐个元素查找确认，直到找到合并数组的中间值，所以时间复杂度为O((n+m)/2)
//LeetCode的上的思路是：A,B是两个数组，长度分别为m和n
//若i和j分别将A和B分割为A_Left/A_Right和B_Left/B_Right,且满足下面两个等式：
//  1. len(A_Left) + len(B_Left) == len(A_Right) + len(B_Right)
//  2. Max(A_Left) <= Min(B_Right) && Max(B_Left) <= Min(A_Right)
//若将A_Left和B_Left合并，A_Right和B_Left合并则上面两个等式为：
//  1.len(Left) == len(Right)
//  2.Max(Left) <= Min(Right)
//那么中位数就是 (Max(Left) + Min(Right)) / 2
//将等式转化为使用A/B/i/j的表示方式为:
//  1.i + j == m - i + n - j (m+n奇数时为m-i+n-j+1)
//  2.A[i-1] <= B[j] && B[j-1] <= A[i]
//可以得到 i = [0,m] j = (m+n) / 2 - i
//因为两数组总长度m + n可能为奇数或者偶数，因为奇数时(m+n) / 2存在小数，所以令j = (m+n+1) / 2 - i
//那么问题转化为：求解i([0,m])，使满足A[i-1] <= B[j] && B[j-1] <= A[i],若找到i，则结果应为：
//当m+n为奇数，mid = Max(A[i-1], B[j-1])
//当m+n为偶数，mid = (Max(A[i-1], B[j-1]) + Min(A[i],B[j])) / 2
//最后是分析一下边缘值,i=0,i=m,j=0,j=m
//当i=0或j=n时，A[i-1],B[j]不存在时，则不需判断条件A[i-1] <= B[j]，同理j=0和i=m也是
//最后条件为
//  1.i>0 && A[i-1] > B[j] i 太大
//  2.i<m && B[j-1] > A[i] i 太小
//  3.(i=0 || j=n || A[i-1] <= B[j]) && (i=m || j=0 || B[j-1] <= A[i]) 找到i