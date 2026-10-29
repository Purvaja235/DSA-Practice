// ==================================================
// PROBLEM: 611. Valid Triangle Number
// Pattern: Two Pointers — Opposite Direction
// ==================================================


// ==================================================
// APPROACH 1: BRUTE FORCE
// Time: O(n³)
// Space: O(1)
// ==================================================

import java.util.Arrays;

class SolutionBrute {
    public int triangleNumber(int[] nums) {

        Arrays.sort(nums);

        int n = nums.length;
        int count = 0;

        // Less than 3 sides cannot form a triangle
        if (n < 3) {
            return 0;
        }

        for (int i = 0; i < n - 2; i++) {

            for (int j = i + 1; j < n - 1; j++) {

                for (int k = j + 1; k < n; k++) {

                    int sum = nums[i] + nums[j];

                    // Check triangle condition
                    if (sum > nums[k]) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}


// ==================================================
// APPROACH 2: OPTIMAL — SORT + TWO POINTERS
// Time: O(n²)
// Space: O(1)*
// ==================================================

class SolutionOptimal {
    public int triangleNumber(int[] nums) {

        Arrays.sort(nums);

        int n = nums.length;
        int count = 0;

        // Less than 3 sides cannot form a triangle
        if (n < 3) {
            return 0;
        }

        // Fix the largest side
        for (int k = n - 1; k >= 2; k--) {

            int left = 0;
            int right = k - 1;

            while (left < right) {

                int sum = nums[left] + nums[right];

                // If two smaller sides can form a triangle
                // with nums[k], then all indices from
                // left to right - 1 are also valid.
                if (sum > nums[k]) {

                    count += right - left;

                    right--;

                // Sum is too small
                } else {

                    left++;
                }
            }
        }

        return count;
    }
}