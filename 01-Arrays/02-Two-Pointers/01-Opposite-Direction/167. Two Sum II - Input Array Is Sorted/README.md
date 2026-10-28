# 167. Two Sum II - Input Array Is Sorted

## Pattern

**Two Pointers → Opposite Direction**

## Difficulty

**Medium**

## Problem

Given a sorted array and a target value, find two numbers that add up to the target and return their 1-based indices.

---

## Approaches

| # | Approach | Time | Space | Status |
|---|---|---|---|---|
| 1 | Brute Force | O(n²) | O(1) | ✅ Completed |
| 2 | Two Pointers | O(n) | O(1) | ⭐ Optimal |

---

## Approach 1 — Brute Force

### Idea

1. Use two loops.
2. Check every possible pair.
3. Calculate their sum.
4. If the sum equals the target, return their 1-based indices.

### Complexity

- Time: **O(n²)**
- Space: **O(1)**

### Key Learning

Checking pairs with nested loops works, but it checks many unnecessary combinations.

---

## Approach 2 — Optimal

### Pattern

**Two Pointers — Opposite Direction**

### Idea

The array is already sorted.

Start with:

```text
left →                 ← right
[2, 7, 11, 15]
```

Calculate:

```text
sum = numbers[left] + numbers[right]
```

Then:

- If `sum < target` → need a bigger sum → `left++`
- If `sum > target` → need a smaller sum → `right--`
- If `sum == target` → answer found

### Pointer Movement

```text
sum < target
    ↓
left++

sum > target
    ↓
right--

sum == target
    ↓
return left + 1, right + 1
```

### Dry Run

Input:

```text
numbers = [2, 7, 11, 15]
target = 9
```

| left | right | Sum | Action |
|---:|---:|---:|---|
| 2 | 15 | 17 | `sum > target` → `right--` |
| 2 | 11 | 13 | `sum > target` → `right--` |
| 2 | 7 | 9 | Found → return `[1, 2]` |

Final answer:

```text
[1, 2]
```

### Complexity

- Time: **O(n)**
- Space: **O(1)**

### Key Learning

Because the array is sorted, we can decide which pointer to move based on whether the current sum is too small or too large.

---

## Mistake I Made

Initially, I compared individual numbers with the target instead of comparing their **sum** with the target.

### Lesson

Always calculate:

```text
sum = numbers[left] + numbers[right]
```

Then decide the pointer movement.

---

## Final Pattern

**Two Pointers → Opposite Direction → Compare Sum With Target**

---

## Status

- ✅ Brute Force completed
- ⭐ Optimal approach completed
- ✅ Pattern understood
- ✅ Problem solved