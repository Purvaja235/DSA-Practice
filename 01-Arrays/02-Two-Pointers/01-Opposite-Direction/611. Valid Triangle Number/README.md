# 611. Valid Triangle Number

## Pattern

**Two Pointers → Opposite Direction**

## Difficulty

**Medium**

## Problem

Given an integer array `nums`, return the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.

For three sides:

```text
a <= b <= c
```

They can form a triangle when:

```text
a + b > c
```

We need to count all valid triplets.

**Example:**

```text
nums = [2,2,3,4]

Valid:
2,2,3
2,3,4   ← using first 2
2,3,4   ← using second 2

Answer = 3
```

**---**

## Approaches

| # | Approach | Time | Space | Status |
|---|---|---|---|---|
| 1 | Brute Force | O(n³) | O(1) | ✅ Completed |
| 2 | Sort + Two Pointers | O(n²) | O(1) | ⭐ Optimal |

**---**

## Approach 1 — Brute Force

### Idea

1. Sort the array.
2. Use three loops.
3. Choose the first index `i`.
4. Choose the second index `j` after `i`.
5. Choose the third index `k` after `j`.
6. Since the array is sorted, `nums[k]` is the largest side.
7. Check whether the two smaller sides can form a triangle with it.
8. If `nums[i] + nums[j] > nums[k]`, increment `count`.

### Formula

```text
i < j < k

nums[i] <= nums[j] <= nums[k]

if nums[i] + nums[j] > nums[k]
    count++
```

### Why `i < j < k`?

We need to choose three different indices.

```text
i = 0
j = 1
k = 2, 3, 4...

i = 0
j = 2
k = 3, 4...

i = 1
j = 2
k = 3, 4...
```

This ensures every triplet is checked once.

### Why only check `nums[i] + nums[j] > nums[k]`?

After sorting:

```text
nums[i] <= nums[j] <= nums[k]
```

So `nums[k]` is the largest side.

For three sides to form a triangle, the important condition is:

```text
smaller side + middle side > largest side
```

Therefore:

```text
nums[i] + nums[j] > nums[k]
```

is enough.

### Example

```text
[2, 3, 4]
```

Check:

```text
2 + 3 > 4
5 > 4
```

Valid triangle.

But:

```text
[2, 3, 5]
```

Check:

```text
2 + 3 > 5
5 > 5
```

False.

So it is not a valid triangle.

### Complexity

- Time: **O(n³)**
- Space: **O(1)**

### Key Learning

Brute force checks **every possible triplet** and directly tests the triangle condition.

**---**

## Approach 2 — Optimal

### Pattern

**Sort + Two Pointers — Opposite Direction**

### Idea

After sorting:

```text
nums[0] <= nums[1] <= ... <= nums[n-1]
```

We fix the **largest side** using `k`.

Then use two pointers:

```text
i = 0
j = k - 1
```

Example:

```text
[2, 2, 3, 4]
 ↑        ↑
 i        k
      ↑
      j
```

Calculate:

```text
sum = nums[i] + nums[j]
```

Then:

```text
if sum > nums[k]
    count += j - i
    j--

else
    i++
```

**---**

## Why Sort First?

Sorting gives us:

```text
nums[i] <= nums[j] <= nums[k]
```

So `nums[k]` is always the largest side.

Therefore we only need:

```text
nums[i] + nums[j] > nums[k]
```

Sorting also allows us to decide which pointer to move.

Without sorting, we cannot make this decision reliably.

**---**

## Why `k` Starts From `n - 1`?

We use `k` as the **largest side**.

So we start from the largest element:

```text
k = n - 1
```

Then move backward:

```text
k--
```

Example:

```text
[2, 2, 3, 4]
       ↑
       k
```

Here:

```text
nums[k] = 4
```

is the largest side.

Then:

```text
k = 2
```

gives:

```text
nums[k] = 3
```

as the largest side for the next group.

**---**

## Why `i = 0` and `j = k - 1`?

For a fixed largest side `nums[k]`:

```text
i = 0
j = k - 1
```

So:

```text
i → smallest possible side
j → largest possible side before k
```

Example:

```text
[2, 2, 3, 4]
 ↑     ↑  ↑
 i     j  k
```

For:

```text
k = 3
```

we have:

```text
i = 0
j = 2
```

and check:

```text
nums[i] + nums[j] > nums[k]
```

**---**

## Why Move `i` When `sum <= nums[k]`?

Suppose:

```text
nums = [2, 2, 3, 4]

i = 0 → 2
j = 2 → 3
k = 3 → 4
```

Calculate:

```text
2 + 3 > 4
5 > 4
```

This works.

But suppose:

```text
i = 0 → 2
j = 1 → 2
k = 3 → 4
```

Calculate:

```text
2 + 2 > 4
4 > 4
```

False.

The sum is too small.

We need a **larger first side**.

Because the array is sorted:

```text
i++
```

moves `i` to a bigger value.

So:

```text
sum <= nums[k]
        ↓
Need a bigger sum
        ↓
i++
```

**---**

## Why `count += j - i`?

This is the **most important part** of the optimal solution.

Suppose:

```text
nums = [2, 2, 3, 4, 5]
```

and:

```text
i = 0
j = 3
k = 4
```

So:

```text
nums[i] = 2
nums[j] = 4
nums[k] = 5
```

Check:

```text
2 + 4 > 5
6 > 5
```

This is valid.

But we don't have only one valid triangle.

Because the array is sorted:

```text
nums[i] <= nums[i+1] <= ... <= nums[j]
```

So if:

```text
nums[i] + nums[j] > nums[k]
```

then:

```text
nums[i] + nums[i+1] > nums[k]
nums[i] + nums[i+2] > nums[k]
...
nums[i] + nums[j] > nums[k]
```

are all valid.

Therefore the number of valid pairs is:

```text
j - i
```

So:

```text
count += j - i
```

### Example

```text
i = 0
j = 3
```

Then:

```text
j - i
= 3 - 0
= 3
```

So we found **3 valid triangles**.

After counting them:

```text
j--
```

because we have already counted all valid pairs involving the current `i`.

**---**

## Why Move `j--` When `sum > nums[k]`?

When:

```text
nums[i] + nums[j] > nums[k]
```

we already know that all positions between `i` and `j` can form valid triangles with `k`.

So:

```text
count += j - i
```

Then we move:

```text
j--
```

to check another possible middle side.

Therefore:

```text
sum > nums[k]
        ↓
count all valid pairs
        ↓
j--
```

**---**

## Dry Run

Input:

```text
nums = [2,2,3,4]
```

### Step 1 — Sort

```text
Index:  0  1  2  3
Value:  2  2  3  4
```

Initial:

```text
count = 0
```

### Step 2 — `k = 3`

```text
k = 3 → nums[3] = 4

i = 0 → nums[0] = 2
j = 2 → nums[2] = 3
```

Check:

```text
2 + 3 > 4
5 > 4
```

Valid.

Number of valid pairs:

```text
j - i
= 2 - 0
= 2
```

So:

```text
count = 2
```

These are:

```text
(2,3,4) using index 0
(2,3,4) using index 1
```

Now:

```text
j--
```

So:

```text
j = 1
```

Now:

```text
i = 0
j = 1
```

Check:

```text
2 + 2 > 4
4 > 4
```

False.

So:

```text
i++
```

Now:

```text
i = 1
j = 1
```

Stop because:

```text
i < j
```

is false.

### Step 3 — `k = 2`

Now:

```text
k = 2 → nums[2] = 3

i = 0
j = 1
```

Check:

```text
2 + 2 > 3
4 > 3
```

Valid.

Number of valid pairs:

```text
j - i
= 1 - 0
= 1
```

So:

```text
count = 3
```

Now:

```text
j--
```

Stop.

Final:

```text
count = 3
```

### Dry Run Table

| k | nums[k] | i | nums[i] | j | nums[j] | Sum | Action | Count |
|---:|---:|---:|---:|---:|---:|---:|---|---:|
| 3 | 4 | 0 | 2 | 2 | 3 | 5 | `sum > nums[k]` → `count += 2`, `j--` | 2 |
| 3 | 4 | 0 | 2 | 1 | 2 | 4 | `sum <= nums[k]` → `i++` | 2 |
| 2 | 3 | 0 | 2 | 1 | 2 | 4 | `sum > nums[k]` → `count += 1`, `j--` | 3 |

Final:

```text
count = 3
```

**---**

## Complexity

### Sorting

```text
O(n log n)
```

### Two Pointers

For every fixed `k`, both `i` and `j` move toward each other.

```text
i  → → →
        ← ← ←  j
```

So each `k` takes:

```text
O(n)
```

There are `n` possible values of `k`.

Therefore:

```text
O(n) × O(n)
= O(n²)
```

### Overall

- Time: **O(n²)**
- Space: **O(1)** extra space for the algorithm

**---**

## Mistake I Made

Initially, I used:

```java
if(sum > nums[right]){
    count++;
    left++;
}
```

The problem was that when the triangle condition is true, there can be **multiple valid triangles**, not just one.

The important observation is:

```text
if nums[i] + nums[j] > nums[k]
```

then every index between `i` and `j` can also form a valid triangle with `k`.

Therefore:

```text
count += j - i
```

instead of:

```text
count++
```

### Another Small Mistake

I initially wrote:

```java
if(n < 0)
```

But array length can never be negative.

The correct condition for fewer than three elements would be:

```java
if(n < 3)
    return 0;
```

Although the given constraints allow `n >= 1`, this is the logically correct check.

### Lesson

The most important thing I learned is:

```text
sum > nums[k]
        ↓
All pairs from i to j work
        ↓
count += j - i
        ↓
j--
```

and:

```text
sum <= nums[k]
        ↓
Need a bigger sum
        ↓
i++
```

**---**

## Final Pattern

**Sort → Fix Largest Side `k` → Two Pointers `i` and `j` → If `nums[i] + nums[j] > nums[k]`, Count `j - i` → `j--` → Otherwise `i++`**

**---**

## Status

- ✅ Brute Force completed
- ⭐ Optimal approach completed
- ✅ Sorting requirement understood
- ✅ Triangle condition understood
- ✅ `k` as largest side understood
- ✅ `sum > nums[k]` understood
- ✅ `count += j - i` understood
- ✅ `sum <= nums[k] → i++` understood
- ✅ `sum > nums[k] → j--` understood
- ✅ Pattern understood
- ✅ Problem solved