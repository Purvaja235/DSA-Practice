// ==================================================
// PROBLEM: 2824. Count Pairs Whose Sum is Less than Target
// Pattern: Two Pointers — Opposite Direction
// ==================================================


// ==================================================
// APPROACH 1: BRUTE FORCE
// Time: O(n²)
// Space: O(1)
// ==================================================

import java.util.Collections;
import java.util.List;

class SolutionBrute {
    public int countPairs(List<Integer> nums, int target) {

        int count = 0;

        for (int i = 0; i < nums.size(); i++) {

            for (int j = i + 1; j < nums.size(); j++) {

                int sum = nums.get(i) + nums.get(j);

                if (sum < target) {
                    count++;
                }
            }
        }

        return count;
    }
}


// ==================================================
// APPROACH 2: OPTIMAL — TWO POINTERS
// Time: O(n log n)
// Space: O(1)*
// ==================================================

class SolutionOptimal {
    public int countPairs(List<Integer> nums, int target) {

        Collections.sort(nums);

        int left = 0;
        int right = nums.size() - 1;
        int count = 0;

        while (left < right) {

            int sum = nums.get(left) + nums.get(right);

            if (sum < target) {

                count += right - left;
                left++;

            } else {

                right--;
            }
        }

        return count;
    }
}