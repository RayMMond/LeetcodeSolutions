public class Solution{

    public List<List<Integer>> fourSum(int[] nums, int target){
        Arrays.sort(nums);

        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < nums.length - 3; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = nums.length - 1; j > i + 2; j--) {
                if (j != nums.length - 1) {
                    if (nums[j] == nums[j + 1]) {
                        continue;
                    }
                }

                int diff = target - (nums[i] + nums[j]);
                int m = i + 1;
                int n = j - 1;
                while (m < n) {
                    if (nums[m] + nums[n] - diff > 0) {
                        while (m < n && nums[n - 1] == nums[n]) n--;
                        n--;
                    } else if (nums[m] + nums[n] - diff < 0) {
                        while (m < n && nums[m + 1] == nums[m]) m++;
                        m++;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[m], nums[n], nums[j]));
                        while (m < n && nums[n - 1] == nums[n]) n--;
                        n--;
                        while (m < n && nums[m + 1] == nums[m]) m++;
                        m++;
                    }
                }
            }
        }


        return result;

    }


}



//总结 在讨论区看到了下面这个解法（多谢 @mtyylx），通用化的在数组中查找k数和
    // List<List<Integer>> kSum_Trim(int[] a, int target, int k) {
    //     List<List<Integer>> result = new ArrayList<>();
    //     if (a == null || a.length < k || k < 2) return result;
    //     Arrays.sort(a);
    //     kSum_Trim(a, target, k, 0, result, new ArrayList<>());
    //     return result;
    // }
    
    // void kSum_Trim(int[] a, int target, int k, int start, List<List<Integer>> result, List<Integer> path) {
    //     int max = a[a.length - 1];
    //     if (a[start] * k > target || max * k < target) return;
        
    //     if (k == 2) {                        // 2 Sum
    //         int left = start;
    //         int right = a.length - 1;
    //         while (left < right) {
    //             if      (a[left] + a[right] < target) left++;
    //             else if (a[left] + a[right] > target) right--;
    //             else {
    //                 result.add(new ArrayList<>(path));
    //                 result.get(result.size() - 1).addAll(Arrays.asList(a[left], a[right]));
    //                 left++; right--;
    //                 while (left < right && a[left] == a[left - 1]) left++;
    //                 while (left < right && a[right] == a[right + 1]) right--;
    //             }
    //         }
    //     }
    //     else {                        // k Sum
    //         for (int i = start; i < a.length - k + 1; i++) {
    //             if (i > start && a[i] == a[i - 1]) continue;
    //             if (a[i] + max * (k - 1) < target) continue;
    //             if (a[i] * k > target) break;
    //             if (a[i] * k == target) {
    //                 if (a[i + k - 1] == a[i]) {
    //                     result.add(new ArrayList<>(path));
    //                     List<Integer> temp = new ArrayList<>();
    //                     for (int x = 0; x < k; x++) temp.add(a[i]);
    //                     result.get(result.size() - 1).addAll(temp);    // Add result immediately.
    //                 }
    //                 break;
    //             }
    //             path.add(a[i]);
    //             kSum_Trim(a, target - a[i], k - 1, i + 1, result, path);
    //             path.remove(path.size() - 1);        // Backtracking
    //         }
    //     }
    // }