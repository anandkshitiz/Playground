package com.akshitiz.playground.interest.systemdesign.typeahead;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/** @author akshitiz */
public class Trie {

  static class TopSuggestions {
    String suggestion;
    int weight;
  }

  private static Comparator<TopSuggestions> weightComparator =
      Comparator.comparingInt(suggestions -> suggestions.weight);

  static class Node {

    Node[] children = new Node[26];
    boolean isEndOfAWord;
    int weight;
    List<String> topSuggestions = new ArrayList<>();
    PriorityQueue<TopSuggestions> topSuggestionsPriorityQueue =
        new PriorityQueue<>(weightComparator);
  }

  private Node root = new Node();

  public void insertNew(String key) {
    int index;
    key = key.toLowerCase();
    Node crawl = root;
    for (int i = 0; i < key.length(); i++) {
      index = key.charAt(i) - 'a';
      if (crawl.children[index] == null) {
        crawl.children[index] = new Node();
      }
      crawl = crawl.children[index];
    }
    crawl.isEndOfAWord = true;
    crawl.weight = 1;
  }

  /**
   * @param prefix prefix to be auto-suggested
   * @return top suggestions for the prefix
   */
  public List findAutoSuggestions(String prefix) {
    int index;
    Node crawl = root;
    for (int i = 0; i < prefix.length(); i++) {
      index = prefix.charAt(i) - 'a';
      if (crawl.children[index] == null) return new ArrayList();
      crawl = crawl.children[index];
    }
    return crawl.topSuggestions;
  }

  private void print(Node node) {
    for (int i = 0; i < 26; i++) {
      Node child = node.children[i];
      if (child != null) {
        int c = 'a' + i;
        char charSoFar = (char) c;
        System.out.println("Top Suggestion of " + charSoFar);
        for (String s : child.topSuggestions) {
          System.out.println(s);
        }
        System.out.println("------");
        print(child);
      }
    }
  }

  public void index() {
    index(root, "");
  }

  public void print() {
    print(root);
  }

  private void index(Node node, String stringSoFar) {

    for (int i = 0; i < 26; i++) {
      Node child = node.children[i];
      if (child != null) {
        int c = 'a' + i;
        char charSoFar = (char) c;
        if (child.isEndOfAWord) {
          if (child.topSuggestions.size() < 5) {
            child.topSuggestions.add(stringSoFar + Character.toString(charSoFar));
          }
        } else {
          index(child, stringSoFar + Character.toString(charSoFar));
        }
        if (node.topSuggestions.size() < 5) {
          node.topSuggestions.addAll(child.topSuggestions);
        }
      }
    }
  }
}
