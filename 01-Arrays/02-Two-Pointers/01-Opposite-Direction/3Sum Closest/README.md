# 16. 3Sum Closest

**## Pattern**

**\*\*Two Pointers → Opposite Direction\*\***

**## Difficulty**

**\*\*Medium\*\***

**## Problem**

Given an integer array `nums` and a target value, find three integers at distinct indices such that their sum is closest to the target.

Return the sum of the three integers.

```text
i < j < k

sum = nums[i] + nums[j] + nums[k]
```

The answer is the sum whose difference from `target` is minimum.

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
3. Start `j` from `i + 1` so that `i < j`.
4. Start `k` from `j + 1` so that `j < k`.
5. Calculate the sum of the three elements.
6. Check how close the current sum is to the target.
7. If the current sum is closer, update `closeSum`.

**### Formula**

```text
sum = nums[i] + nums[j] + nums[k]

difference = |sum - target|

if difference < |closeSum - target|
    closeSum = sum
```

**### Why `j = i + 1` and `k = j + 1`?**

We need three distinct indices:

```text
i < j < k
```

So:

```text
i = 0 → j = 1 → k = 2, 3, 4...
i = 0 → j = 2 → k = 3, 4...
i = 1 → j = 2 → k = 3, 4...
```

This avoids:

```text
(i, i, k) → same index
(i, j, j) → same index
```

and checks every possible triplet once.

**### Why `Math.abs(sum - target)`?**

We need to know how far the current sum is from the target.

Example:

```text
target = 1

sum = 2
|2 - 1| = 1

sum = 5
|5 - 1| = 4
```

So `2` is closer to the target.

Therefore:

```text
Math.abs(sum - target)
```

gives us the distance from the target.

**### Complexity**

- Time: **\*\*O(n³)\*\***
- Space: **\*\*O(1)\*\***

**### Key Learning**

Brute force checks **\*\*every possible triplet\*\*** and keeps the sum that has the smallest distance from the target.

**---**

**## Approach 2 — Optimal**

**### Pattern**

**\*\*Sort + Two Pointers — Opposite Direction\*\***

**### Idea**

The original array is not sorted, so first sort it.

```text
Original:
[-1, 2, 1, -4]

After sorting:
[-4, -1, 1, 2]
```

Then:

1. Fix one element using `i`.
2. Place `left` after `i`.
3. Place `right` at the end.
4. Calculate the three-element sum.
5. Update `closeSum` if the current sum is closer to the target.
6. Move the correct pointer depending on the sum.

```text
        left        right
          ↓           ↓
[-4, -1, 1, 2]
```

Calculate:

```text
sum = nums[i] + nums[left] + nums[right]
```

**### Pointer Movement**

```text
sum < target
    ↓
Need a bigger sum
    ↓
left++

sum > target
    ↓
Need a smaller sum
    ↓
right--

sum == target
    ↓
Exact answer
    ↓
return sum
```

**---**

**## Why Sort First?**

Sorting gives us an important guarantee.

Example:

```text
[-4, -1, 1, 2]
     ↑        ↑
   left      right
```

Suppose:

```text
sum < target
```

The sum is too small.

Because the array is sorted, moving `left` forward gives us a bigger value.

So:

```text
left++
```

If:

```text
sum > target
```

the sum is too large.

Because the array is sorted, moving `right` backward gives us a smaller value.

So:

```text
right--
```

Without sorting, we cannot make this pointer-movement decision reliably.

**---**

**## Why Update `closeSum`?**

We need the sum that is **closest to the target**.

Example:

```text
target = 1
```

Current:

```text
closeSum = 5
```

New sum:

```text
sum = 2
```

Compare their distances:

```text
|2 - 1| = 1

|5 - 1| = 4
```

Since `2` is closer:

```text
closeSum = 2
```

So:

```text
if Math.abs(sum - target) < Math.abs(closeSum - target)
    closeSum = sum
```

**---**

**## Why Move `left` When `sum < target`?**

Suppose:

```text
target = 10

sum = 6
```

The sum is too small.

We need to increase the sum.

Because the array is sorted:

```text
left++
```

moves `left` to a bigger value.

Example:

```text
[-4, -1, 1, 2, 5]
     ↑           ↑
    left        right
```

If we move:

```text
left++
```

we get a bigger value and therefore a bigger sum.

So:

```text
sum < target
    ↓
left++
```

**---**

**## Why Move `right` When `sum > target`?**

Suppose:

```text
target = 10

sum = 15
```

The sum is too large.

We need to decrease the sum.

Because the array is sorted:

```text
right--
```

moves `right` to a smaller value.

So:

```text
sum > target
    ↓
right--
```

This moves the sum closer to the target.

**---**

**## Dry Run**

Input:

```text
nums = [-1, 2, 1, -4]
target = 1
```

**### Step 1 — Sort**

```text
Index:  0   1   2   3
Value: -4  -1   1   2
```

Initial:

```text
closeSum = -4 + (-1) + 1
         = -4
```

**### Step 2 — Fix `i = 0`**

```text
i = 0 → nums[0] = -4
left = 1 → nums[1] = -1
right = 3 → nums[3] = 2
```

Calculate:

```text
-4 + (-1) + 2 = -3
```

Compare:

```text
|-3 - 1| = 4
|-4 - 1| = 5
```

`-3` is closer.

```text
closeSum = -3
```

Since:

```text
sum < target
```

move:

```text
left++
```

Now:

```text
left = 2 → nums[2] = 1
right = 3 → nums[3] = 2
```

Calculate:

```text
-4 + 1 + 2 = -1
```

Compare:

```text
|-1 - 1| = 2
|-3 - 1| = 4
```

Update:

```text
closeSum = -1
```

Again:

```text
sum < target
```

so:

```text
left++
```

Now `left == right`, so stop.

**### Step 3 — Fix `i = 1`**

```text
i = 1 → nums[1] = -1
left = 2 → nums[2] = 1
right = 3 → nums[3] = 2
```

Calculate:

```text
-1 + 1 + 2 = 2
```

Compare:

```text
|2 - 1| = 1
|-1 - 1| = 2
```

Update:

```text
closeSum = 2
```

Now:

```text
sum > target
```

so:

```text
right--
```

Now `left == right`, so stop.

Final:

```text
closeSum = 2
```

**### Dry Run Table**

| i | nums[i] | left | nums[left] | right | nums[right] | Sum | Closest Sum | Action |
|---:|---:|---:|---:|---:|---:|---:|---:|---|
| 0 | -4 | 1 | -1 | 3 | 2 | -3 | -3 | `sum < target` → `left++` |
| 0 | -4 | 2 | 1 | 3 | 2 | -1 | -1 | `sum < target` → `left++` |
| 1 | -1 | 2 | 1 | 3 | 2 | 2 | 2 | `sum > target` → `right--` |

Final:

```text
closeSum = 2
```

**---**

**## Complexity**

**### Sorting**

```text
O(n log n)
```

**### Two Pointers**

For every fixed `i`, both pointers only move toward each other.

```text
left  → → →
          ← ← ←  right
```

So for each `i`:

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
- Space: **\*\*O(1)\*\*** extra space for the algorithm

**### Why O(n) for Two Pointers?**

For each fixed `i`, `left` only moves forward and `right` only moves backward.

They never move backward in the opposite direction.

So the two-pointer traversal is linear.

**---**

**## Mistake I Made**

Initially, I tried moving both pointers after every comparison:

```text
left++
right--
```

This is incorrect.

The pointer movement depends on whether the current sum is too small or too large.

The correct logic is:

```text
sum < target
    ↓
Need bigger sum
    ↓
left++

sum > target
    ↓
Need smaller sum
    ↓
right--
```

I also needed to understand why `closeSum` should start with a **real three-element sum**:

```text
closeSum = nums[0] + nums[1] + nums[2]
```

instead of:

```text
closeSum = 0
```

because `0` may not be a possible triplet sum.

**### Lesson**

Do **\*\*not\*\*** move both pointers automatically.

First ask:

```text
Is my sum too small?
    ↓
left++

Is my sum too large?
    ↓
right--
```

And always compare the distance from the target:

```text
Math.abs(sum - target)
```

**---**

**## Final Pattern**

**\*\*Sort → Fix One Element → Two Pointers → Calculate 3-Element Sum → Update Closest Sum → Sum < Target: Left++ → Sum > Target: Right--\*\***

**---**

**## Status**

- ✅ Brute Force completed
- ⭐ Optimal approach completed
- ✅ Sorting requirement understood
- ✅ `closeSum` understood
- ✅ `Math.abs(sum - target)` understood
- ✅ Pointer movement understood
- ✅ `sum < target → left++` understood
- ✅ `sum > target → right--` understood
- ✅ Exact target → return understood
- ✅ Pattern understood
- ✅ Problem solved