# 977. Squares of a Sorted Array

## Pattern

**Two Pointers → Opposite Direction**

## Difficulty

**Easy**

## Problem

Given a sorted array, return a new array containing the squares of all numbers, also sorted in non-decreasing order.

---

## Approaches

| # | Approach | Time | Space | Status |
|---|---|---|---|---|
| 1 | Brute Force + Sorting | O(n log n) | O(n) | ✅ Completed |
| 2 | Square In-Place + Sorting | O(n log n) | O(1) | ✅ Completed |
| 3 | Two Pointers | O(n) | O(n) | ⭐ Completed |

---

## Approach 1 — Brute Force

### Idea

1. Create a new array.
2. Square every element.
3. Sort the result.

### Complexity

- Time: **O(n log n)**
- Space: **O(n)**

### Key Learning

Simple approach, but sorting takes extra time.

---

## Approach 2 — Better

### Idea

1. Square every element directly in the input array.
2. Sort the array.

### Complexity

- Time: **O(n log n)**
- Space: **O(1)**

### Key Learning

No extra result array, but sorting is still required.

---

## Approach 3 — Optimal

### Pattern

**Two Pointers — Opposite Direction**

### Idea

The array is already sorted.

The largest square can come from either end:

- `left` → most negative number
- `right` → largest positive number

Compare the absolute values of both ends.

Take the larger value, square it, and put it at the **end** of the answer array.

### Pointer Movement

```text
if abs(nums[left]) > abs(nums[right])
        ↓
use left
left++

else
        ↓
use right
right--
```

Fill the answer from **right → left**.

### Dry Run

Input:

```text
[-4, -1, 0, 3, 10]
```

| left | right | Compare | Put in ans | Move |
|---:|---:|---|---:|---|
| -4 | 10 | 4 < 10 | 100 | right-- |
| -4 | 3 | 4 > 3 | 16 | left++ |
| -1 | 3 | 1 < 3 | 9 | right-- |
| -1 | 0 | 1 > 0 | 1 | left++ |
| 0 | 0 | equal | 0 | right-- |

Final answer:

```text
[0, 1, 9, 16, 100]
```

### Complexity

- Time: **O(n)**
- Space: **O(n)**

### Key Learning

When a sorted array contains negative and positive numbers, the largest square can be at either end. Compare absolute values using two pointers.

---

## Mistake I Made

I initially tried modifying `nums` while still needing the original values for comparison.

**Lesson:** Do not overwrite values that you still need later.

---

## Final Pattern

**Two Pointers → Opposite Direction → Compare Both Ends → Fill Answer Right to Left**

---

## Status

- ✅ All approaches completed
- ⭐ Optimal approach understood
- ✅ Problem solved