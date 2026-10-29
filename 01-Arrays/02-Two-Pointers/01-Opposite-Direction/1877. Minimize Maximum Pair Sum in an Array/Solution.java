// ==================================================
// PROBLEM: 1877. Minimize Maximum Pair Sum in an Array
// Pattern: Two Pointers — Opposite Direction
// ==================================================


// ==================================================
// APPROACH 1: BRUTE FORCE
// Time: O(n²)
// Space: O(n)
// ==================================================

import java.util.Arrays;

class SolutionBrute {
    public int minPairSum(int[] nums) {

        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);

        int maxSum = 0;

        for (int i = 0; i < nums.length; i++) {

            if (used[i]) {
                continue;
            }

            boolean paired = false;

            for (int j = nums.length - 1; j >= 0; j--) {

                if (!used[j]) {

                    used[i] = true;
                    used[j] = true;

                    paired = true;

                    int sum = nums[i] + nums[j];

                    maxSum = Math.max(maxSum, sum);

                    break;
                }
            }
        }

        return maxSum;
    }
}


// ==================================================
// APPROACH 2: OPTIMAL — TWO POINTERS
// Time: O(n log n)
// Space: O(1)*
// ==================================================

class SolutionOptimal {
    public int minPairSum(int[] nums) {

        Arrays.sort(nums);

        int maxSum = 0;
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int sum = nums[left] + nums[right];

            maxSum = Math.max(maxSum, sum);

            left++;
            right--;
        }

        return maxSum;
    }
}