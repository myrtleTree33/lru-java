package org.joeltong;

import java.util.HashMap;
import java.util.Map;

public class Main {

  public static void ll(String s) {
    System.out.println(s);
  }

  static class Node {

    Node next = null, prev = null;
    int val;
    int key;

    Node(int key, int val) {
      this.val = val;
      this.key = key;
    }

    @Override
    public String toString() {
      return val + "";
    }

  }

  static class Lru {

    private Map<Integer, Node> nodeMap = new HashMap<>();
    private Node first;
    private Node last;
    private int SIZE = 0;

    Lru(int size) {
      this.SIZE = size;
    }

    int get(int key) {
      Node n = nodeMap.get(key);
      if (n == null) {
        return -1;
      }
      if (first == n) {
        return n.val;
      }

      if (last == n) {
        Node secondLast = n.next;
        secondLast.prev = null;
        first.next = n;
        n.prev = first;
        first = n;
        return n.val;

      } else {
        Node n1 = n.next;
        Node n2 = n.prev;
        n1.prev = n2;
        n2.next = n1;
        first.next = n;
        n.next = null;
        n.prev = first;
        first = n;
        return n.val;
      }

    }

    void put(int key, int val) {
      if (nodeMap.containsKey(key)) {
        return;
      }
      Node n = new Node(key, val);
      if (nodeMap.isEmpty()) {
        nodeMap.put(key, n);
        first = n;
        last = n;
        return;
      }
      nodeMap.put(key, n);
      if (nodeMap.size() > SIZE) {
        nodeMap.remove(last.key);
        Node secondLast = last.next;
        secondLast.prev = null;
        last = secondLast;
      }
      first.next = n;
      n.prev = first;
      first = n;
      nodeMap.put(key, n);
    }

    void print() {
      if (first == null) {
        ll("[]");
        return;
      }
      StringBuffer sb = new StringBuffer();
      sb.append("[");
      Node curr = first;
      while (curr != null) {
        sb.append(curr.key + "");
        sb.append(" ");
        curr = curr.prev;
      }
      sb.append("]");
      ll(sb.toString());
    }
  }

  public static void main(String[] args) {
    Lru lru = new Lru(3);
    lru.put(1, 1);
    lru.print();
    lru.put(2, 2);
    lru.print();
    lru.put(3, 3);
    lru.print();
    lru.put(4, 4);
    lru.print();
    lru.get(2);
    lru.print();
    lru.get(4);
    lru.print();
    lru.get(4);
    lru.print();

  }
}
