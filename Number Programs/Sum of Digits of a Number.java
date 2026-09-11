/*
===========================================================
Problem Name : Sum of Digits of a Number
Category     : Number Programs → Digit Manipulation
Difficulty   : Easy
Importance   : Very Important for Java Fresher Interviews
===========================================================

PROBLEM STATEMENT:
Given an integer, find the sum of all its digits.

Example:
Input  : 12345
Output : 15

===========================================================
CONCEPTS USED:
1. Modulus operator (%) → Extracts the last digit.
2. Integer division (/) → Removes the last digit.
3. while loop → Repeats until the number becomes 0.
4. Running sum → Stores the accumulated digit sum.

===========================================================
APPROACH:
1. Store the number in a variable.
2. Initialize sum = 0.
3. Extract the last digit using n % 10.
4. Add the digit to sum.
5. Remove the last digit using n / 10.
6. Repeat until n becomes 0.
7. Print sum.

===========================================================
DRY RUN:
Input: 12345

n       digit       sum
12345     5           5
1234      4           9
123       3          12
12        2          14
1         1          15
0         -          Stop

Output: 15

===========================================================
TIME COMPLEXITY:
O(d), where d = number of digits.

SPACE COMPLEXITY:
O(1), constant extra space.

===========================================================
EDGE CASES:
1. n = 0       → Output 0
2. n = 7       → Output 7
3. n = 1000    → Output 1

===========================================================
COMMON MISTAKES:
1. Confusing % and /.
2. Forgetting to update n = n / 10.
3. Overwriting the number incorrectly.
4. Using an incorrect loop condition.

===========================================================
RELATED PROBLEMS:
1. Reverse a Number
2. Palindrome Number
3. Armstrong Number
4. Count Digits
5. Product of Digits

===========================================================
*/

public class Main {
    public static void main(String[] args) {

        // 1. Input
        int n = 12345;

        // 2. Initialize result
        int sum = 0;

        // 3. Process each digit
        while (n > 0) {

            // Extract the last digit
            int digit = n % 10;

            // Add digit to sum
            sum += digit;

            // Remove the last digit
            n = n / 10;
        }

        // 4. Output
        System.out.println("Sum of digits = " + sum);
    }
}