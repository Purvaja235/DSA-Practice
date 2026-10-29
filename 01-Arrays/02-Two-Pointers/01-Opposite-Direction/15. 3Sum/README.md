# 15. 3Sum

**## Pattern**

**\*\*Two Pointers → Opposite Direction\*\***

**## Difficulty**

**\*\*Medium\*\***

**## Problem**

Given an integer array `nums`, find all unique triplets `(i, j, k)` such that:

```text
i < j < k
nums[i] + nums[j] + nums[k] = 0
```

The solution must not contain duplicate triplets.

**---**

**## Approaches**

| # | Approach | Time | Space | Status |
|---|---|---|---|---|
| 1 | Brute Force | O(n³) | O(1) | ✅ Completed |
| 2 | Sort + Two Pointers | O(n²) | O(1) | ⭐ Optimal |

**---**

**## Approach 1 — Brute Force**

**### Idea**

1. Use three loops.
2. Choose the first index `i`.
3. Choose the second index `j` starting from `i + 1`.
4. Choose the third index `k` starting from `j + 1`.
5. Calculate the sum of the three elements.
6. If the sum is `0`, add the triplet to the result.

**### Formula**

```text
sum = nums[i] + nums[j] + nums[k]

if sum == 0
    add triplet
```

**### Why `j = i + 1` and `k = j + 1`?**

We need:

```text
i < j < k
```

So that we do not use the same index or check the same combination again.

```text
i = 0
    j = 1
        k = 2, 3, 4...

i = 1
    j = 2
        k = 3, 4...
```

This avoids:

```text
(i, i, k) → same index
(i, j, j) → same index
(j, i, k) → duplicate combination
```

**### Complexity**

- Time: **\*\*O(n³)\*\***
- Space: **\*\*O(1)\*\*** excluding output

**### Key Learning**

Brute force checks **\*\*every possible triplet\*\***, so it is simple but slow for large arrays.

**---**

**## Approach 2 — Optimal**

**### Pattern**

**\*\*Sort + Two Pointers — Opposite Direction\*\***

**### Idea**

Instead of choosing all three elements using three loops:

1. Sort the array.
2. Fix one element using `i`.
3. Use two pointers for the remaining two elements.
4. `left` starts after `i`.
5. `right` starts at the end.
6. Compare the three-element sum.

```text
sum = nums[i] + nums[left] + nums[right]
```

**### Pointer Movement**

```text
sum < 0
    ↓
left++

sum > 0
    ↓
right--

sum == 0
    ↓
add triplet
skip duplicates
left++
right--
```

**---**

**## Why Sort First?**

Sorting gives us an important guarantee.

Example:

```text
Index:  0   1   2   3   4   5
Value: -4  -1  -1   0   1   2
```

If the sum is too small:

```text
sum < 0
```

we need a bigger value.

Because the array is sorted:

```text
left++
```

gives us a bigger value.

If the sum is too large:

```text
sum > 0
```

we need a smaller value.

Because the array is sorted:

```text
right--
```

gives us a smaller value.

So sorting allows us to decide **which pointer to move**.

**---**

**## Why Skip Duplicates?**

The problem requires **unique triplets**.

Example:

```text
[-1, -1, 0, 1]
```

If we use both `-1`s as the fixed element, we may find the same triplet more than once.

So for `i`:

```text
if i > 0 && nums[i] == nums[i - 1]
    continue
```

For `left`:

```text
while left < right && nums[left] == nums[left + 1]
    left++
```

For `right`:

```text
while left < right && nums[right] == nums[right - 1]
    right--
```

This ensures that the same triplet is not added again.

**---**

**## Dry Run**

Input:

```text
nums = [-1, 0, 1, 2, -1, -4]
```

**### Step 1 — Sort**

```text
Index:  0   1   2   3   4   5
Value: -4  -1  -1   0   1   2
```

**### Step 2 — Fix `i = 0`**

```text
i = 0 → nums[0] = -4
left = 1 → nums[1] = -1
right = 5 → nums[5] = 2
```

Calculate:

```text
-4 + (-1) + 2 = -3
```

Since:

```text
sum < 0
```

move:

```text
left++
```

Now:

```text
left = 2 → nums[2] = -1
right = 5 → nums[5] = 2
```

Calculate:

```text
-4 + (-1) + 2 = -3
```

Again:

```text
sum < 0
    ↓
left++
```

Now:

```text
left = 3 → nums[3] = 0
right = 5 → nums[5] = 2
```

Calculate:

```text
-4 + 0 + 2 = -2
```

Again:

```text
sum < 0
    ↓
left++
```

Now:

```text
left = 4 → nums[4] = 1
right = 5 → nums[5] = 2
```

Calculate:

```text
-4 + 1 + 2 = -1
```

Again:

```text
sum < 0
    ↓
left++
```

Now:

```text
left = 5
right = 5
```

Stop because:

```text
left < right
```

is false.

**### Step 3 — Fix `i = 1`**

```text
i = 1 → nums[1] = -1
left = 2 → nums[2] = -1
right = 5 → nums[5] = 2
```

Calculate:

```text
-1 + (-1) + 2 = 0
```

Found:

```text
[-1, -1, 2]
```

Now skip duplicates and move both pointers:

```text
left++
right--
```

So:

```text
left = 3 → nums[3] = 0
right = 4 → nums[4] = 1
```

Calculate:

```text
-1 + 0 + 1 = 0
```

Found:

```text
[-1, 0, 1]
```

Move both pointers:

```text
left++
right--
```

Now:

```text
left = 4
right = 3
```

Stop because:

```text
left < right
```

is false.

**### Dry Run Table**

| i | nums[i] | left | nums[left] | right | nums[right] | Sum | Action |
|---:|---:|---:|---:|---:|---:|---:|---|
| 0 | -4 | 1 | -1 | 5 | 2 | -3 | `sum < 0` → `left++` |
| 0 | -4 | 2 | -1 | 5 | 2 | -3 | `sum < 0` → `left++` |
| 0 | -4 | 3 | 0 | 5 | 2 | -2 | `sum < 0` → `left++` |
| 0 | -4 | 4 | 1 | 5 | 2 | -1 | `sum < 0` → `left++` |
| 1 | -1 | 2 | -1 | 5 | 2 | 0 | Add `[-1,-1,2]` |
| 1 | -1 | 3 | 0 | 4 | 1 | 0 | Add `[-1,0,1]` |

Final result:

```text
[[-1, -1, 2],
 [-1, 0, 1]]
```

**---**

**## Complexity**

**### Sorting**

```text
O(n log n)
```

**### Two Pointers**

For every fixed `i`, the two pointers move toward each other.

```text
left → → →
        ← ← ← right
```

So each fixed `i` takes:

```text
O(n)
```

There are `n` possible values of `i`.

Therefore:

```text
O(n) × O(n)
= O(n²)
```

**### Overall**

- Time: **\*\*O(n²)\*\***
- Space: **\*\*O(1)\*\*** extra space for the algorithm, excluding output

**---**

**## Mistake I Made**

I initially made a mistake while handling the duplicate check for `right`.

I wrote:

```text
nums[right] == nums[right] - 1
```

This is incorrect.

The correct condition is:

```text
nums[right] == nums[right - 1]
```

Because `right` moves backward.

For `left`:

```text
nums[left] == nums[left + 1]
```

because `left` moves forward.

**### Lesson**

Always think about **which direction the pointer is moving** before deciding which neighboring index to compare.

```text
left →
compare with left + 1

right ←
compare with right - 1
```

Also, the fixed element must skip duplicates:

```text
if i > 0 && nums[i] == nums[i - 1]
    continue
```

**---**

**## Final Pattern**

**\*\*Sort → Fix One Element → Two Pointers → Compare 3-Element Sum → Move Left/Right → Skip Duplicates\*\***

**---**

**## Status**

- ✅ Brute Force completed
- ⭐ Optimal approach completed
- ✅ Sorting requirement understood
- ✅ Three-element sum understood
- ✅ Pointer movement understood
- ✅ Duplicate handling understood
- ✅ `left++` when `sum < 0` understood
- ✅ `right--` when `sum > 0` understood
- ✅ Pattern understood
- ✅ Problem solved