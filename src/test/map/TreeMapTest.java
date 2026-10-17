package map;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Runs the Map contract suite against TreeMap, then checks what TreeMap
 * promises beyond the contract: keys come out in sorted order, and the tree
 * stays balanced, with the shapes the notes trace.
 */
public class TreeMapTest extends MapTest {

  @Override
  protected Map<String, Integer> createMap() {
    return new TreeMap<>();
  }

  @Test
  public void iteratorVisitsKeysInSortedOrder() {
    TreeMap<Integer, String> tree = treeOf(50, 30, 70, 20, 40, 60, 80);
    assertEquals(listOf(20, 30, 40, 50, 60, 70, 80), keysOf(tree));
  }

  @Test
  public void entriesVisitPairsInSortedKeyOrder() {
    TreeMap<Integer, String> tree = treeOf(50, 30, 70, 20, 40, 60, 80);
    List<String> pairs = new ArrayList<>();
    for (Map.Entry<Integer, String> entry : tree.entries()) {
      pairs.add(entry.key() + "=" + entry.value());
    }
    assertEquals(List.of("20=v20", "30=v30", "40=v40", "50=v50", "60=v60", "70=v70", "80=v80"),
        pairs);
  }

  @Test
  public void ascendingPutsBuildABalancedTree() {
    TreeMap<Integer, String> tree = treeOf(1, 2, 3, 4, 5, 6, 7);
    assertEquals(listOf(4, 2, 1, 3, 6, 5, 7), preOrder(rootOf(tree)));
    assertEquals(2, heightOf(rootOf(tree)));
    assertBalanced(rootOf(tree));
  }

  @Test
  public void descendingPutsBuildABalancedTree() {
    TreeMap<Integer, String> tree = treeOf(7, 6, 5, 4, 3, 2, 1);
    assertEquals(listOf(4, 2, 1, 3, 6, 5, 7), preOrder(rootOf(tree)));
    assertBalanced(rootOf(tree));
  }

  @Test
  public void putRotatesRightAtTheFirstUnbalancedNode() {
    TreeMap<Integer, String> tree = treeOf(14, 11, 17, 19, 7);
    tree.put(4, "v4");
    assertEquals(listOf(14, 7, 4, 11, 17, 19), preOrder(rootOf(tree)));
    assertBalanced(rootOf(tree));
  }

  @Test
  public void putRotationHandsOverTheInBetweenSubtree() {
    TreeMap<Integer, String> tree = treeOf(14, 7, 17, 4, 11);
    tree.put(3, "v3");
    assertEquals(listOf(7, 4, 3, 14, 11, 17), preOrder(rootOf(tree)));
    assertBalanced(rootOf(tree));
  }

  @Test
  public void putRotatesLeftAtTheFirstUnbalancedNode() {
    TreeMap<Integer, String> tree = treeOf(14, 11, 17, 7, 19);
    tree.put(21, "v21");
    assertEquals(listOf(14, 11, 7, 19, 17, 21), preOrder(rootOf(tree)));
    assertBalanced(rootOf(tree));
  }

  @Test
  public void putDoesARightLeftDoubleRotation() {
    TreeMap<Integer, String> tree = treeOf(10, 5, 20, 3, 7, 15, 25, 22, 30);
    tree.put(23, "v23");
    assertEquals(listOf(10, 5, 3, 7, 22, 20, 15, 25, 23, 30), preOrder(rootOf(tree)));
    assertBalanced(rootOf(tree));
  }

  @Test
  public void overwritingAKeyDoesNotChangeTheShape() {
    TreeMap<Integer, String> tree = treeOf(50, 30, 70, 20, 40, 60, 80);
    List<Integer> before = preOrder(rootOf(tree));
    tree.put(40, "new");
    assertEquals(before, preOrder(rootOf(tree)));
    assertEquals("new", tree.get(40));
  }

  @Test
  public void removeRotatesRight() {
    TreeMap<Integer, String> tree = treeOf(14, 7, 17, 4, 11, 19, 3);
    tree.remove(11);
    assertEquals(listOf(14, 4, 3, 7, 17, 19), preOrder(rootOf(tree)));
    assertBalanced(rootOf(tree));
  }

  @Test
  public void removeDoesALeftRightDoubleRotation() {
    TreeMap<Integer, String> tree = treeOf(12, 9, 13, 8, 10, 14, 11);
    tree.remove(13);
    assertEquals(listOf(10, 9, 8, 12, 11, 14), preOrder(rootOf(tree)));
    assertBalanced(rootOf(tree));
  }

  @Test
  public void removeRebalancesTwoAncestors() {
    TreeMap<Integer, String> tree = treeOf(45, 30, 60, 20, 40, 55, 65, 15, 25, 35, 50, 10);
    tree.remove(65);
    assertEquals(listOf(30, 20, 15, 10, 25, 45, 40, 35, 55, 50, 60), preOrder(rootOf(tree)));
    assertBalanced(rootOf(tree));
  }

  @Test
  public void removeDoesADoubleRotationAtAnAncestor() {
    TreeMap<Integer, String> tree = treeOf(30, 20, 50, 10, 40, 60, 35);
    tree.remove(10);
    assertEquals(listOf(40, 30, 20, 35, 50, 60), preOrder(rootOf(tree)));
    assertBalanced(rootOf(tree));
  }

  @Test
  public void removeOfATwoChildNodeKeepsItsSuccessorsValue() {
    TreeMap<Integer, String> tree = treeOf(50, 30, 70, 20, 40, 60, 80);
    tree.remove(50);
    assertEquals("v60", tree.get(60));
    assertEquals(listOf(20, 30, 40, 60, 70, 80), keysOf(tree));
    assertBalanced(rootOf(tree));
  }

  @Test
  public void manyPutsKeepTheTreeBalanced() {
    TreeMap<Integer, String> tree = scrambledTree();
    assertBalanced(rootOf(tree));
  }

  @Test
  public void manyRemovesKeepTheTreeBalanced() {
    TreeMap<Integer, String> tree = scrambledTree();
    for (int key = 0; key < 101; key += 3) {
      tree.remove(key);
    }
    assertBalanced(rootOf(tree));
  }

  // Puts the keys 0 to 100 in a scrambled order, with some keys put twice.
  private static TreeMap<Integer, String> scrambledTree() {
    TreeMap<Integer, String> tree = new TreeMap<>();
    for (int i = 0; i < 200; i++) {
      int key = (i * 37) % 101;
      tree.put(key, "v" + key);
    }
    return tree;
  }

  // Builds a TreeMap by putting each key, with value "v" + key, in order.
  private static TreeMap<Integer, String> treeOf(int... keys) {
    TreeMap<Integer, String> tree = new TreeMap<>();
    for (int key : keys) {
      tree.put(key, "v" + key);
    }
    return tree;
  }

  private static List<Integer> listOf(int... values) {
    List<Integer> list = new ArrayList<>();
    for (int value : values) {
      list.add(value);
    }
    return list;
  }

  private static List<Integer> keysOf(TreeMap<Integer, String> tree) {
    List<Integer> keys = new ArrayList<>();
    for (int key : tree) {
      keys.add(key);
    }
    return keys;
  }

  // Reads a private field of an object by reflection.
  private static Object readField(Object object, String name) {
    try {
      Field field = object.getClass().getDeclaredField(name);
      field.setAccessible(true);
      return field.get(object);
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException(e);
    }
  }

  private static Object rootOf(TreeMap<Integer, String> tree) {
    return readField(tree, "root");
  }

  // The keys of the subtree rooted at node, in pre-order.
  private static List<Integer> preOrder(Object node) {
    List<Integer> keys = new ArrayList<>();
    preOrder(node, keys);
    return keys;
  }

  private static void preOrder(Object node, List<Integer> keys) {
    if (node == null) {
      return;
    }
    keys.add((Integer) readField(node, "key"));
    preOrder(readField(node, "left"), keys);
    preOrder(readField(node, "right"), keys);
  }

  // The cached height of node. The empty tree has height -1.
  private static int heightOf(Object node) {
    if (node == null) {
      return -1;
    }
    return (Integer) readField(node, "height");
  }

  // Checks that every node in the subtree rooted at node is balanced and
  // caches its correct height.
  private static void assertBalanced(Object node) {
    if (node == null) {
      return;
    }
    Object left = readField(node, "left");
    Object right = readField(node, "right");
    Object key = readField(node, "key");
    assertTrue(Math.abs(heightOf(left) - heightOf(right)) <= 1, "unbalanced at " + key);
    assertEquals(1 + Math.max(heightOf(left), heightOf(right)), heightOf(node),
        "stale height at " + key);
    assertBalanced(left);
    assertBalanced(right);
  }
}
