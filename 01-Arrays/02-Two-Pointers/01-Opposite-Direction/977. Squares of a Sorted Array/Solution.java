import java.util.Arrays;

// ==================================================
// APPROACH 1: BRUTE FORCE
// Time: O(n log n)
// Space: O(n)
// ==================================================

class SolutionBrute {
    public int[] sortedSquares(int[] nums) {

        int[] ans = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[i] * nums[i];
        }

        Arrays.sort(ans);

        return ans;
    }
}


// ==================================================
// APPROACH 2: BETTER
// Time: O(n log n)
// Space: O(1)
// ==================================================

class SolutionBetter {
    public int[] sortedSquares(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }

        Arrays.sort(nums);

        return nums;
    }
}


// ==================================================
// APPROACH 3: OPTIMAL — TWO POINTERS
// Time: O(n)
// Space: O(n)
// ==================================================

class SolutionOptimal {
    public int[] sortedSquares(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        int[] ans = new int[nums.length];

        for (int i = nums.length - 1; i >= 0; i--) {

            if (Math.abs(nums[left]) > Math.abs(nums[right])) {

                ans[i] = nums[left] * nums[left];
                left++;

            } else {

                ans[i] = nums[right] * nums[right];
                right--;
            }
        }

        return ans;
    }
}