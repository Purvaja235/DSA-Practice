// ==================================================
// PROBLEM: 16. 3Sum Closest
// Pattern: Two Pointers — Opposite Direction
// ==================================================


// ==================================================
// APPROACH 1: BRUTE FORCE
// Time: O(n³)
// Space: O(1)
// ==================================================

import java.util.Arrays;

class SolutionBrute {
    public int threeSumClosest(int[] nums, int target) {

        int n = nums.length;

        // Start with the first valid triplet
        int closeSum = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < n - 2; i++) {

            for (int j = i + 1; j < n - 1; j++) {

                for (int k = j + 1; k < n; k++) {

                    int sum = nums[i] + nums[j] + nums[k];

                    // Check if current sum is closer to target
                    if (Math.abs(sum - target) <
                        Math.abs(closeSum - target)) {

                        closeSum = sum;
                    }
                }
            }
        }

        return closeSum;
    }
}


// ==================================================
// APPROACH 2: OPTIMAL — SORT + TWO POINTERS
// Time: O(n²)
// Space: O(1)*
// ==================================================

class SolutionOptimal {
    public int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);

        int n = nums.length;

        // Start with the first valid triplet
        int closeSum = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < n - 2; i++) {

            int left = i + 1;
            int right = n - 1;

            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];

                // Update closest sum
                if (Math.abs(sum - target) <
                    Math.abs(closeSum - target)) {

                    closeSum = sum;
                }

                // Exact target found
                if (sum == target) {
                    return sum;
                }

                // Sum is too small
                if (sum < target) {

                    left++;

                // Sum is too large
                } else {

                    right--;
                }
            }
        }

        return closeSum;
    }
}