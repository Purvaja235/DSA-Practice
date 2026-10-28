// ==================================================
// PROBLEM: 11. Container With Most Water
// Pattern: Two Pointers — Opposite Direction
// ==================================================


// ==================================================
// APPROACH 1: BRUTE FORCE
// Time: O(n²)
// Space: O(1)
// ==================================================

class SolutionBrute {
    public int maxArea(int[] height) {

        int maxWater = 0;

        for (int i = 0; i < height.length; i++) {

            for (int j = height.length - 1; j >= 0; j--) {

                int width = j - i;
                int h = Math.min(height[i], height[j]);
                int area = width * h;

                maxWater = Math.max(maxWater, area);
            }
        }

        return maxWater;
    }
}


// ==================================================
// APPROACH 2: OPTIMAL — TWO POINTERS
// Time: O(n)
// Space: O(1)
// ==================================================

class SolutionOptimal {
    public int maxArea(int[] height) {

        int left = 0;
        int right = height.length - 1;
        int maxWater = 0;

        while (left < right) {

            int width = right - left;
            int h = Math.min(height[left], height[right]);
            int area = width * h;

            maxWater = Math.max(maxWater, area);

            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }

        return maxWater;
    }
}