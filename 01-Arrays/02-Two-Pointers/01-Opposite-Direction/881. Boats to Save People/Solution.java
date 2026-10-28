// ==================================================
// PROBLEM: 881. Boats to Save People
// Pattern: Two Pointers — Opposite Direction
// ==================================================


// ==================================================
// APPROACH 1: BRUTE FORCE
// Time: O(n²)
// Space: O(n)
// ==================================================

import java.util.Arrays;

class SolutionBrute {
    public int numRescueBoats(int[] people, int limit) {

        boolean[] used = new boolean[people.length];
        int boat = 0;

        for (int i = 0; i < people.length; i++) {

            if (used[i]) {
                continue;
            }

            boolean paired = false;

            for (int j = i + 1; j < people.length; j++) {

                if (!used[j] && people[i] + people[j] <= limit) {

                    used[j] = true;
                    used[i] = true;

                    paired = true;
                    boat++;

                    break;
                }
            }

            if (!used[i]) {

                used[i] = true;
                boat++;
            }
        }

        return boat;
    }
}


// ==================================================
// APPROACH 2: OPTIMAL — TWO POINTERS
// Time: O(n log n)
// Space: O(1)*
// ==================================================

class SolutionOptimal {
    public int numRescueBoats(int[] people, int limit) {

        Arrays.sort(people);

        int boat = 0;
        int left = 0;
        int right = people.length - 1;

        while (left <= right) {

            if (people[left] + people[right] <= limit) {

                left++;
                right--;

            } else {

                right--;
            }

            boat++;
        }

        return boat;
    }
}