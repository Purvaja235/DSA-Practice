# 2824. Count Pairs Whose Sum is Less than Target

## Pattern

**Two Pointers → Opposite Direction**

## Difficulty

**Easy**

## Problem

Given a list of integers and a target value, count the number of pairs of indices `(i, j)` such that:

```text
i < j
nums[i] + nums[j] < target
```

---

## Approaches

| # | Approach | Time | Space | Status |
|---|---|---|---|---|
| 1 | Brute Force | O(n²) | O(1) | ✅ Completed |
| 2 | Sort + Two Pointers | O(n log n) | O(1) | ⭐ Optimal |

---

## Approach 1 — Brute Force

### Idea

1. Use two loops.
2. Choose the first index `i`.
3. Start `j` from `i + 1` so that `i < j`.
4. Calculate the sum of the two elements.
5. If the sum is less than the target, increase `count`.

### Formula

```text
sum = nums[i] + nums[j]

if sum < target
    count++
```

### Why `j = i + 1`?

We only want each unique pair once.

```text
i = 0 → j = 1, 2, 3...
i = 1 → j = 2, 3...
i = 2 → j = 3...
```

This avoids:

```text
(i, i)     → same element
(j, i)     → duplicate pair
```

### Complexity

- Time: **O(n²)**
- Space: **O(1)**

### Key Learning

Brute force checks **every possible pair**, so no sorting or pointer elimination is required.

---

## Approach 2 — Optimal

### Pattern

**Sort + Two Pointers — Opposite Direction**

### Idea

The original list is not sorted, so first sort it.

```text
Original:
[-1, 1, 2, 3, 1]

After sorting:
[-1, 1, 1, 2, 3]
```

Then place two pointers:

```text
left →              ← right
[-1, 1, 1, 2, 3]
```

Calculate:

```text
sum = nums[left] + nums[right]
```

### Pointer Movement

```text
sum < target
    ↓
count += right - left
left++

sum >= target
    ↓
right--
```

---

## Why Sort First?

Sorting gives us an important guarantee.

Example:

```text
[-1, 1, 1, 2, 3]
 ↑           ↑
left        right
```

Suppose:

```text
-1 + 2 < target
```

Because the list is sorted, `-1` will also make a valid pair with every element between `left` and `right`.

So we can count multiple pairs at once.

---

## Why `count += right - left`?

Example:

```text
[-1, 1, 1, 2, 3]
 ↑        ↑
left     right
```

Suppose:

```text
-1 + 2 < target
```

Valid pairs using `-1` are:

```text
(-1, 1)
(-1, 1)
(-1, 2)
```

There are 3 pairs.

Their count is:

```text
right - left
= 3 - 0
= 3
```

So:

```text
count += right - left
```

means:

```text
count = count + number of valid pairs
```

Then:

```text
left++
```

because we have already counted all valid pairs involving the current `left`.

---

## Why Move `right` When `sum >= target`?

Suppose:

```text
nums[left] + nums[right] >= target
```

The sum is too large.

Because the list is sorted, moving `left` forward would make the sum even larger.

So we move `right` backward:

```text
right--
```

This gives us a smaller value and therefore a smaller sum.

---

## Dry Run

Input:

```text
nums = [-1, 1, 2, 3, 1]
target = 2
```

### Step 1 — Sort

```text
[-1, 1, 1, 2, 3]
```

### Step 2 — Two Pointers

| Left | Right | Sum | Action | Count |
|---:|---:|---:|---|---:|
| 0 | 4 | -1 + 3 = 2 | `sum >= target` → `right--` | 0 |
| 0 | 3 | -1 + 2 = 1 | `count += 3 - 0`, `left++` | 3 |
| 1 | 3 | 1 + 2 = 3 | `sum >= target` → `right--` | 3 |
| 1 | 2 | 1 + 1 = 2 | `sum >= target` → `right--` | 3 |

Final:

```text
count = 3
```

Valid pairs:

```text
(-1, 1)
(-1, 1)
(-1, 2)
```

---

## Complexity

### Sorting

```text
O(n log n)
```

### Two Pointers

```text
O(n)
```

### Overall

- Time: **O(n log n)**
- Space: **O(1)** extra space for the algorithm

### Why O(n) for Two Pointers?

Both pointers only move toward each other.

```text
left  → → →
           ← ← ←  right
```

They never move backward, so the two-pointer traversal is linear.

---

## Mistake I Made

Initially, I tried moving both pointers:

```text
left++
right--
```

after every comparison.

### Lesson

Do **not** move both pointers automatically.

Instead:

```text
sum < target
    ↓
count all valid pairs
left++

sum >= target
    ↓
right--
```

The pointer movement depends on the comparison.

---

## Final Pattern

**Sort → Two Pointers → Compare Sum With Target → Count `right - left` When Valid → Move One Pointer**

---

## Status

- ✅ Brute Force completed
- ⭐ Optimal approach completed
- ✅ Sorting requirement understood
- ✅ `count += right - left` understood
- ✅ Pointer movement understood
- ✅ Pattern understood
- ✅ Problem solved