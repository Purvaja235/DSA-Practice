# 1877. Minimize Maximum Pair Sum in Array

## Pattern

**Two Pointers → Opposite Direction**

## Difficulty

**Medium**

## Problem

Given an array of even length, pair up all elements so that every element is used exactly once.

The goal is to **minimize the maximum pair sum**.

For each pair:

```text
pair sum = nums[i] + nums[j]
```

Among all pair sums, we want the smallest possible maximum.

---

## Approaches

| # | Approach | Time | Space | Status |
|---|---|---|---|---|
| 1 | Sorting + Brute Force Pairing | O(n²) | O(n) | ✅ Completed |
| 2 | Sorting + Two Pointers | O(n log n) | O(1) | ⭐ Optimal |

---

## Approach 1 — Brute Force

### Idea

First sort the array.

Then use a `boolean[] used` array to remember which elements have already been paired.

For every unused element:

1. Find another unused element.
2. Pair it with that element.
3. Mark both elements as used.
4. Calculate their pair sum.
5. Keep track of the maximum pair sum.

### Example

Input:

```text
[3, 5, 2, 3]
```

After sorting:

```text
[2, 3, 3, 5]
```

The pairing becomes:

```text
(2, 5) → 7
(3, 3) → 6
```

Maximum pair sum:

```text
max(7, 6) = 7
```

### Key Code Idea

```java
boolean[] used = new boolean[nums.length];
```

When two elements are paired:

```java
used[i] = true;
used[j] = true;
```

Then calculate:

```java
int sum = nums[i] + nums[j];
maxSum = Math.max(maxSum, sum);
```

### Complexity

- Time: **O(n²)**
- Space: **O(n)**

### Key Learning

The `used[]` array prevents the same element from being used in multiple pairs.

The nested loop searches for an available partner.

---

## Approach 2 — Optimal

### Pattern

**Sort + Two Pointers — Opposite Direction**

### Idea

Sort the array first.

After sorting:

```text
[2, 3, 3, 5]
 ↑        ↑
left     right
```

Pair the:

```text
smallest + largest
```

Then move both pointers toward the center.

```text
left++
right--
```

For every pair, calculate its sum and keep the maximum pair sum.

### Why Smallest + Largest?

The goal is to minimize the **largest pair sum**.

The largest number is the biggest risk of creating a large pair sum.

So we give the largest number the **smallest possible partner**.

Example:

```text
[2, 3, 3, 5]
```

Pair:

```text
5 + 2 = 7
```

instead of:

```text
5 + 3 = 8
```

This keeps the maximum pair sum as small as possible.

### Pointer Movement

```text
left →        ← right

pair:
nums[left] + nums[right]

then:

left++
right--
```

Both elements have now been used, so both pointers move.

---

## Dry Run

Input:

```text
nums = [3, 5, 2, 3]
```

### Step 1 — Sort

```text
[2, 3, 3, 5]
```

### Step 2 — Two Pointers

| Left | Right | Pair | Sum | Max Sum |
|---:|---:|---|---:|---:|
| 0 | 3 | (2, 5) | 7 | 7 |
| 1 | 2 | (3, 3) | 6 | 7 |

Final:

```text
maxSum = 7
```

---

## Example 2

Input:

```text
nums = [3, 5, 4, 2, 4, 6]
```

After sorting:

```text
[2, 3, 4, 4, 5, 6]
```

Pair:

```text
(2, 6) → 8
(3, 5) → 8
(4, 4) → 8
```

Maximum pair sum:

```text
8
```

Answer:

```text
8
```

---

## Why Does This Minimize the Maximum?

Suppose:

```text
[2, 3, 3, 5]
```

The largest element is `5`.

Its smallest possible partner is `2`.

Therefore:

```text
5 + 2 = 7
```

No pairing can make the pair containing `5` smaller than `7`.

So the maximum pair sum must be at least `7`.

Our pairing achieves:

```text
(2,5) → 7
(3,3) → 6
```

Therefore the minimum possible maximum is exactly:

```text
7
```

### Key Learning

**Pair the smallest with the largest to balance the pair sums and minimize the largest pair sum.**

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

Each iteration uses exactly one pair.

Both pointers move toward the center:

```text
left  → → →
           ← ← ←  right
```

Each element is processed once.

Therefore the two-pointer part is:

```text
O(n)
```

---

## Mistake I Made

Initially, I tried using nested loops to calculate pair sums without ensuring that each element was used exactly once.

### Lesson

The problem is not asking for the largest sum among **any two elements**.

It asks us to:

1. Create complete pairs.
2. Use every element exactly once.
3. Find the maximum sum among those pairs.
4. Minimize that maximum.

For the optimal solution, sorting makes the correct pairing pattern clear:

```text
smallest + largest
```

---

## Final Pattern

**Sort → Two Pointers → Pair Smallest + Largest → Track Maximum Pair Sum → Move Both Pointers**

---

## Status

- ✅ Brute Force completed
- ⭐ Optimal approach completed
- ✅ Sorting understood
- ✅ Smallest + Largest pairing understood
- ✅ Maximum pair sum understood
- ✅ Pointer movement understood
- ✅ Problem solved