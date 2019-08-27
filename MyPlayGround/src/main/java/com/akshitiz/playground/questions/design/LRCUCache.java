package com.akshitiz.playground.questions.design;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU - Least Recently Used Caching system removes the LRU element when the cache reaches its max
 * limit.
 *
 * @author akshitiz
 */
public class LRCUCache {

  static class Element {

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

  private Map<Integer, Element> mapOfCacheElements = new HashMap<>();
  private Element start = null;
  private Element end = null;
  private final int MAX_SIZE = 10;
  private int currentSize = 0;

  private void addElementAtStart(int key, int value) {
    Element newElement = new Element(key, value);
    start = newElement;
    end = newElement;
    currentSize++;
    mapOfCacheElements.put(key, start);
  }

  private void addElementInMiddle(int key, int value) {
    Element newElement = new Element(key, value);
    newElement.left = start.left;
    start = newElement;
    currentSize++;
    mapOfCacheElements.put(key, newElement);
  }

  private void removeLRUElement() {
    mapOfCacheElements.remove(end.key);
    end = end.right;
    end.right = null;
    currentSize--;
  }

  private void applyLRULogic(Element element) {
    if (element.right != null) {
      element.right.left = element.left;
      element.left = start;
      start = element;
      element.right = null;
    }
  }

  public int readFromCache(int key) {
    Element element = mapOfCacheElements.get(key);
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
      addElementInMiddle(key, value);
    } else {
      removeLRUElement();
      addElementInMiddle(key, value);
    }
  }
}
