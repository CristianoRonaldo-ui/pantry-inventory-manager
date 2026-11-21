# Pantry Inventory Manager

A small Java program that tracks pantry stock using a `HashMap`. You can add items, use items (with validation), print the full inventory, and get a list of items that are running low.

## Features

- **Add items**: adding the same item again accumulates the quantity
- **Use items**: throws `IllegalArgumentException` for invalid or insufficient quantities; the stock is left unchanged on failure
- **Auto-remove**: an item is removed from the map when its quantity reaches 0
- **Inventory report**: iterates over the map with `entrySet()`
- **Low-stock warning**: returns all items at or below a given threshold
- **Manual tests**: 3 PASS/FAIL tests run at the end of `main`

## Project Structure

```
pantry-inventory-manager/
├── .gitignore
├── README.md
└── src/
    ├── Pantry.java       # Pantry class: HashMap stock + all operations
    └── PantryApp.java    # main() demo + runTests()
```

## How to Run

Requires a JDK (Java 11+). From the project root:

```bash
javac src/*.java
java -cp src PantryApp
```

## Sample Output

```
rice: 8
eggs: 12
milk: 0
eggs after using 4: 8
Error: Not enough rice: have 8, need 20
=== Pantry Inventory ===
eggs: 8
rice: 8
Low stock (<= 3): [salt]

=== Running tests ===
Test 1 PASS: addItem accumulates quantity
Test 2 PASS: item removed when quantity reaches 0
Test 3 PASS: exception thrown and stock unchanged
```

## Complexity Analysis

| Method | Time | Why |
|---|---|---|
| `addItem` | O(1) average | One `getOrDefault` + one `put` on a hash map |
| `useItem` | O(1) average | One lookup + one `put` or `remove` |
| `getQuantity` | O(1) average | One hash map lookup |
| `printInventory` | O(n) | Visits every entry once |
| `getLowStockItems` | O(n) | Visits every entry once to filter |

`n` = number of distinct items in the pantry.

## What I Learned

- **HashMap is Java's version of Python's `dict`**: `put` = `d[k] = v`, `getOrDefault` = `d.get(k, default)`, `remove` = `del d[k]`, `entrySet()` = `d.items()`.
- **Generics need wrapper types**: `HashMap<String, Integer>` uses `Integer`, not `int`, because collections can only hold objects. Java auto-unboxes `Integer` to `int` for me.
- **HashMap does not keep insertion order**: I added `rice` before `eggs`, but the report printed `eggs` first. Entries are stored in buckets based on their hash, unlike Python 3.7+ dicts. `LinkedHashMap` keeps insertion order and `TreeMap` keeps keys sorted.
- **Removing vs. storing 0**: `getQuantity` returns 0 both for a removed key and for a key stored with value 0, so Test 2 checks the map itself via `getLowStockItems(0)` to tell the two cases apart.
- **A good test must be able to fail**: my first version of Test 3 only printed PASS inside `catch`, so it would have printed nothing if no exception was thrown. Using a `boolean threw` flag and checking the stock afterwards makes the test report FAIL loudly when the code is wrong.
- **Unchecked vs. checked exceptions**: `IllegalArgumentException` extends `RuntimeException`, so `useItem` does not need a `throws` clause.