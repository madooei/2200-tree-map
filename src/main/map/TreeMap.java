package map;

import java.util.Iterator;
import java.util.NoSuchElementException;
import stack.LinkedStack;
import stack.Stack;

/**
 * An AVL tree implementation of the Map ADT.
 *
 * @param <K> the type of keys in this map.
 * @param <V> the type of values in this map.
 */
public class TreeMap<K extends Comparable<K>, V> implements Map<K, V> {

  private Node<K, V> root;
  private int size;

  private static class Node<K, V> implements Map.Entry<K, V> {
    K key;
    V value;
    Node<K, V> left;
    Node<K, V> right;
    int height;

    Node(K key, V value) {
      this.key = key;
      this.value = value;
      height = 0;  // a new node is a leaf
    }

    @Override
    public K key() {
      return key;
    }

    @Override
    public V value() {
      return value;
    }
  }

  public TreeMap() {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public V get(K key) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public boolean containsKey(K key) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Returns the node holding key in the subtree rooted at node, or null if
  // key is not there.
  private Node<K, V> find(Node<K, V> node, K key) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public boolean put(K key, V value) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Puts key and value in the subtree rooted at node and returns that
  // subtree's new root. Replaces the value if key is already there.
  private Node<K, V> put(Node<K, V> node, K key, V value) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public boolean remove(K key) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Removes key from the subtree rooted at node and returns that subtree's
  // new root. Assumes key is in the subtree.
  private Node<K, V> remove(Node<K, V> node, K key) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Assumes node is not null. Returns the node with the smallest key in the
  // subtree rooted at node.
  private Node<K, V> minNode(Node<K, V> node) {
    while (node.left != null) {
      node = node.left;
    }
    return node;
  }

  @Override
  public int size() {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Returns the cached height of node. The empty tree has height -1.
  private int height(Node<K, V> node) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Assumes node is not null.
  private int balanceFactor(Node<K, V> node) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Assumes node is not null and its children's cached heights are correct.
  private void updateHeight(Node<K, V> node) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Assumes node is not null, its children are balanced, and its cached
  // height is correct. Returns the root of the subtree after rebalancing.
  private Node<K, V> rebalance(Node<K, V> node) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Assumes root.left is not null. Returns the new root of the subtree.
  private Node<K, V> rightRotation(Node<K, V> root) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Assumes root.right is not null. Returns the new root of the subtree.
  private Node<K, V> leftRotation(Node<K, V> root) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Assumes root.left.right is not null. Returns the new root of the subtree.
  private Node<K, V> leftRightRotation(Node<K, V> root) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Assumes root.right.left is not null. Returns the new root of the subtree.
  private Node<K, V> rightLeftRotation(Node<K, V> root) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public Iterator<K> iterator() {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public Iterable<Map.Entry<K, V>> entries() {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Walks the tree in-order, returning each node's key.
  private class KeyIterator implements Iterator<K> {
    private Iterator<Map.Entry<K, V>> nodes = new EntryIterator();

    @Override
    public boolean hasNext() {
      // TODO: Implement me
      throw new UnsupportedOperationException("TODO: Implement me");
    }

    @Override
    public K next() {
      // TODO: Implement me
      throw new UnsupportedOperationException("TODO: Implement me");
    }
  }

  // The Iterable that entries() returns. Each call to iterator() starts a new traversal.
  private class Entries implements Iterable<Map.Entry<K, V>> {
    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
      // TODO: Implement me
      throw new UnsupportedOperationException("TODO: Implement me");
    }
  }

  // Walks the tree in-order, returning each node as a read-only entry. Keeps
  // the nodes still to visit on a stack.
  private class EntryIterator implements Iterator<Map.Entry<K, V>> {
    private Stack<Node<K, V>> stack = new LinkedStack<>();

    EntryIterator() {
      // TODO: Implement me
      throw new UnsupportedOperationException("TODO: Implement me");
    }

    // Pushes node and every node on the path of left children below it.
    private void pushLeftSpine(Node<K, V> node) {
      // TODO: Implement me
      throw new UnsupportedOperationException("TODO: Implement me");
    }

    @Override
    public boolean hasNext() {
      // TODO: Implement me
      throw new UnsupportedOperationException("TODO: Implement me");
    }

    @Override
    public Map.Entry<K, V> next() {
      // TODO: Implement me
      throw new UnsupportedOperationException("TODO: Implement me");
    }
  }
}
