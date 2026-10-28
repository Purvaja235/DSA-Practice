// ==================================================
// PROBLEM: 167. Two Sum II - Input Array Is Sorted
// Pattern: Two Pointers — Opposite Direction
// ==================================================


// ==================================================
// APPROACH 1: BRUTE FORCE
// Time: O(n²)
// Space: O(1)
// ==================================================

class SolutionBrute {
    public int[] twoSum(int[] numbers, int target) {

        for (int i = 0; i < numbers.length; i++) {

            for (int j = i + 1; j < numbers.length; j++) {

                int sum = numbers[i] + numbers[j];

                if (sum == target) {
                    return new int[]{i + 1, j + 1};
                }
            }
        }

        return new int[]{};
    }
}


// ==================================================
// APPROACH 2: OPTIMAL — TWO POINTERS
// Time: O(n)
// Space: O(1)
// ==================================================

class SolutionOptimal {
    public int[] twoSum(int[] numbers, int target) {

        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {

            int sum = numbers[left] + numbers[right];

            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else {
                return new int[]{left + 1, right + 1};
            }
        }

        return new int[]{};
    }
}