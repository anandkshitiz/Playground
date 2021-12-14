package com.akshitiz.playground.interest.systemdesign;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU - Least Recently Used Caching system removes the LRU element when the cache reaches its max
 * limit.
 *
 * @author akshitiz
 */
public class ThreadSafeLRUCache {

  private static class Element {

    int key;
    int value;
    Element left;
    Element right;

    Element(int key, int value) {
      this.key = key;
      this.value = value;
      left = null;
      right = null;
    }
  }

  private Map<Integer, Element> concurrentMapOfCache = new HashMap<>();
  private Element start = null;
  private Element end = null;
  private final int MAX_SIZE = 10;
  private int currentSize = 0;

  private synchronized void addElementAtStart(int key, int value) {
    Element newElement = new Element(key, value);
    start = newElement;
    end = newElement;
    currentSize++;
    concurrentMapOfCache.put(key, start);
  }

  private synchronized void addElementAtMiddle(int key, int value) {
    Element newElement = new Element(key, value);
    newElement.left = start.left;
    start = newElement;
    currentSize++;
    concurrentMapOfCache.put(key, newElement);
  }

  private synchronized void removeLRUElement() {
    concurrentMapOfCache.remove(end.key);
    end = end.right;
    end.right = null;
    currentSize--;
  }

  private synchronized void applyLRULogic(Element element) {
    if (element.right != null) {
      element.right.left = element.left;
      element.left = start;
      start = element;
      element.right = null;
    }
  }

  public int readFromCache(int key) {
    Element element = concurrentMapOfCache.get(key);
    if (null != element) {
      applyLRULogic(element);
      return element.value;
    } else {
      return -1;
    }
  }

  public void addToCache(int key, int value) {
    if (0 == currentSize) {
      addElementAtStart(key, value);
    } else if (currentSize <= MAX_SIZE) {
      addElementAtMiddle(key, value);
    } else {
      removeLRUElement();
      addElementAtMiddle(key, value);
    }
  }
}
