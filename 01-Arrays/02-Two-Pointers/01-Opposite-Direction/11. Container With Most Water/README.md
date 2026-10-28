# 11. Container With Most Water

## Pattern

**Two Pointers → Opposite Direction**

## Difficulty

**Medium**

## Problem

Given an array where each value represents the height of a vertical line, choose two lines that form a container with the x-axis and hold the maximum amount of water.

The water is calculated as:

```text
width × shorter height
```

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
2. Select every possible pair of lines.
3. Calculate the width between them.
4. Find the shorter height.
5. Calculate the area.
6. Keep the maximum area.

### Formula

```text
width = j - i
height = min(height[i], height[j])
area = width × height
```

### Complexity

- Time: **O(n²)**
- Space: **O(1)**

### Key Learning

Brute force checks **every possible pair**, so no pointer-elimination condition is needed.

The nested loops automatically move `i` and `j`.

---

## Approach 2 — Optimal

### Pattern

**Two Pointers — Opposite Direction**

### Idea

Start with the widest possible container:

```text
left →                 ← right
[1, 8, 6, 2, 5, 4, 8, 3, 7]
```

For every pair:

```text
width = right - left
height = min(height[left], height[right])
area = width × height
```

Then compare the two heights.

### Pointer Movement

```text
left height < right height
        ↓
    left++

right height < left height
        ↓
    right--

heights are equal
        ↓
    move either pointer
```

### Why Move the Shorter Height?

The amount of water is limited by the **shorter line**.

If the left line is shorter:

```text
height[left] < height[right]
```

moving `right` inward makes the width smaller, while the left height is still the limiting height.

So the current `left` can be eliminated.

Therefore:

```text
left++
```

Similarly, if the right line is shorter:

```text
right--
```

This is the **pointer elimination** idea.

---

## Dry Run

Input:

```text
height = [1,8,6,2,5,4,8,3,7]
```

| Left | Right | Width | Shorter Height | Area | Action |
|---:|---:|---:|---:|---:|---|
| 0 | 8 | 8 | 1 | 8 | `left++` |
| 1 | 8 | 7 | 7 | 49 | `right--` |
| 1 | 7 | 6 | 3 | 18 | `right--` |
| 1 | 6 | 5 | 8 | 40 | `right--` |
| 1 | 5 | 4 | 4 | 16 | `right--` |
| 1 | 4 | 3 | 5 | 15 | `right--` |
| 1 | 3 | 2 | 2 | 4 | `right--` |
| 1 | 2 | 1 | 6 | 6 | `right--` |

Maximum area:

```text
49
```

---

## Complexity

- Time: **O(n)**
- Space: **O(1)**

### Why O(n)?

Both pointers only move toward each other.

```text
left  → → →
             ← ← ←  right
```

Each pointer moves at most `n` times.

---

## Mistake I Made

Initially, I used:

```text
width = i - j
```

instead of:

```text
width = j - i
```

### Lesson

`i` is the left index and `j` is the right index.

Therefore:

```text
width = right - left
      = j - i
```

---

## Final Pattern

**Two Pointers → Opposite Direction → Area Depends on Width × Shorter Height → Move the Shorter Pointer**

---

## Status

- ✅ Brute Force completed
- ⭐ Optimal approach completed
- ✅ Pointer elimination understood
- ✅ Pattern understood
- ✅ Problem solved