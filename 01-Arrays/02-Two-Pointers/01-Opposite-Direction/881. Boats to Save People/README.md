# 881. Boats to Save People

## Pattern

**Two Pointers → Opposite Direction**

## Difficulty

**Medium**

## Problem

Given an array `people` where `people[i]` represents the weight of a person, and a boat can carry at most two people with a maximum weight of `limit`, return the **minimum number of boats** needed to carry everyone.

A boat can carry two people only when:

```text
people[i] + people[j] <= limit
```

---

## Approaches

| # | Approach | Time | Space | Status |
|---|---|---|---|---|
| 1 | Brute Force + Used Array | O(n²) | O(n) | ✅ Completed |
| 2 | Sort + Two Pointers | O(n log n) | O(1) | ⭐ Completed |

---

## Approach 1 — Brute Force

### Idea

Use a `boolean[] used` array to remember which people have already been placed in a boat.

For every unused person:

1. Look for another unused person.
2. Check if both people can fit in one boat.
3. If they can fit, put both in one boat and mark them as used.
4. If no partner is found, the person goes alone.

### Example

Input:

```text
people = [3, 2, 2, 1]
limit = 3
```

Possible pairing:

```text
(2, 1) → 1 boat
(2)    → 1 boat
(3)    → 1 boat
```

Answer:

```text
3 boats
```

### Key Code Idea

```java
boolean[] used = new boolean[people.length];
```

When two people are paired:

```java
used[i] = true;
used[j] = true;
boat++;
```

If a person cannot be paired:

```java
used[i] = true;
boat++;
```

### Complexity

- Time: **O(n²)**
- Space: **O(n)**

### Key Learning

Brute force checks possible partners using nested loops.

The `used[]` array is necessary so that the same person is not placed into multiple boats.

### Important Limitation

This brute-force approach pairs an unused person with the **first available person that fits**.

That does **not guarantee the minimum number of boats for every possible input**.

This is why the sorted two-pointer approach is needed.

---

## Approach 2 — Optimal

### Pattern

**Sort + Two Pointers — Opposite Direction**

### Idea

Sort the people by weight.

Then use two pointers:

```text
left  → lightest person
right → heaviest person
```

Example:

```text
[1, 2, 2, 3]
 ↑        ↑
left     right
```

Try to put the lightest and heaviest people together.

### Pointer Movement

If they can fit:

```text
people[left] + people[right] <= limit
```

Both people can share one boat.

```text
left++
right--
boat++
```

If they cannot fit:

```text
people[left] + people[right] > limit
```

The heaviest person cannot fit with even the lightest person.

Therefore, the heaviest person must go alone.

```text
right--
boat++
```

### Why Can We Move `right` When They Cannot Fit?

Suppose:

```text
[1, 2, 2, 3]
 ↑        ↑
left     right
```

and:

```text
1 + 3 > limit
```

`1` is the **lightest** person.

If even the lightest person cannot fit with `3`, then nobody else can fit with `3`, because everyone else weighs at least `1`.

Therefore:

```text
3 → must go alone
```

So we eliminate `right`.

This is **pointer elimination**.

---

## Dry Run

Input:

```text
people = [3, 2, 2, 1]
limit = 3
```

After sorting:

```text
[1, 2, 2, 3]
```

| Left | Right | Sum | Action | Boats |
|---:|---:|---:|---|---:|
| 1 | 3 | 4 | `3` goes alone → `right--` | 1 |
| 1 | 2 | 3 | `(1,2)` share → `left++`, `right--` | 2 |
| 2 | 2 | 4 | `2` goes alone → `right--` | 3 |

Final:

```text
3 boats
```

---

## Another Example

Input:

```text
people = [1, 2]
limit = 3
```

Sorted:

```text
[1, 2]
 ↑  ↑
 L  R
```

Check:

```text
1 + 2 = 3
```

Since:

```text
3 <= 3
```

they can share one boat.

```text
left++
right--
boat++
```

Answer:

```text
1
```

---

## Complexity

### Sorting

```text
O(n log n)
```

### Two Pointer Traversal

```text
O(n)
```

### Overall

- Time: **O(n log n)**
- Space: **O(1)** extra space for the algorithm

The two pointers only move toward each other, so the traversal itself is linear.

---

## Mistake I Made

Initially, I tried using nested loops for the optimal solution and changed `i` and `j` inside the loops.

I also initially wrote:

```java
people[left];
people[right];
```

These statements do not move the pointers.

The correct pointer movements are:

```java
left++;
right--;
```

when both people share a boat, and:

```java
right--;
```

when the heaviest person must go alone.

### Lesson

Do not move pointers automatically.

First determine:

```text
Can lightest + heaviest fit?
```

Then decide which pointer can be eliminated.

---

## Pointer Elimination

### Case 1 — Both Can Fit

```text
lightest + heaviest <= limit
```

Both people are successfully placed.

```text
left++
right--
```

### Case 2 — They Cannot Fit

```text
lightest + heaviest > limit
```

The heaviest person cannot pair with anyone.

```text
right--
```

### Golden Rule

**If the heaviest person cannot fit with the lightest person, the heaviest person must go alone.**

---

## Final Pattern

**Sort → Two Pointers → Lightest + Heaviest → If Fit Move Both → Otherwise Move Heaviest → Count Boats**

---

## Status

- ✅ Brute Force completed
- ⭐ Optimal approach completed
- ✅ Sorting understood
- ✅ Pointer movement understood
- ✅ Pointer elimination understood
- ✅ Problem solved