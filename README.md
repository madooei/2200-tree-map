# TreeMap

An AVL tree implementation of the `Map` ADT. The tree keeps its keys in search order and rebalances itself after every `put` and `remove`, so its height stays about $\log n$ and every operation is $O(\log n)$. A JUnit suite checks it against the `Map` contract, then checks that the keys come out in sorted order and that the tree keeps the balanced shapes the notes trace.

## Prerequisites

- JDK 17+
- The JUnit jar is already vendored in `lib/`; there is nothing to download.

## Repository layout

```plaintext
code/
  README.md
  .gitignore
  lib/
    junit-platform-console-standalone-6.1.0.jar
  src/
    main/
      map/
        Map.java                        # the Map ADT contract, with the nested Map.Entry
        TreeMap.java                    # AVL tree implementation of Map
        Main.java                       # demo entry point (the password example)
      stack/
        Stack.java                      # the Stack ADT, used by the iterator
        LinkedStack.java                # linked implementation of Stack
    test/
      map/
        MapTest.java                    # abstract: the Map contract suite
        TreeMapTest.java                # runs the suite against TreeMap, plus shape tests
  scripts/
    run.sh                              # compile and run the demo (map.Main)
    test.sh                             # compile and run every JUnit test
```

## How to compile and run

- `scripts/test.sh` — compiles everything and runs the full JUnit suite.
- `scripts/test.sh map.TreeMapTest` — compiles everything and runs only that test class.
- `scripts/run.sh` — compiles everything and runs the `Main` demo.

## What's here

- `map.Map<K, V>` — the Map contract from the map chapter: `put`, `get`, `remove`, `containsKey`, `size`, `iterator()` over the keys, and `entries()` over the key-value pairs.
- `map.TreeMap<K, V>` — the AVL tree implementation. Each node holds a key, a value, and the cached height of its subtree, and it is also the `Map.Entry` that `entries()` hands back. Both iterations visit the keys in ascending order.
- `map.Main` — a runnable demo of the password example. The usernames arrive in sorted order, and the iteration lists them in sorted order.
- `stack.Stack<T>`, `stack.LinkedStack<T>` — the Stack ADT and its linked implementation, copied so this code compiles on its own. The in-order iterator keeps the nodes still to visit on a stack.
- `map.MapTest` — the abstract contract suite from the map chapter.
- `map.TreeMapTest` — runs the contract suite against `TreeMap`, then checks the sorted iteration and, by reading the tree's private fields, the shape and balance of the tree after the rotations the notes trace.
