package com.akshitiz.playground.interest.dynamic;

import java.util.*;

public class SubSetSumProblem {

  public void playSubSetSumProblem() {

    int[] array = {1, 2, 3, 4, 5};
    ArrayList<Integer> arrayList = new ArrayList();
    for (int i = 0; i < array.length; i++) {
      arrayList.add(array[i]);
    }
    int sum = 10;
    printSubSets(array, array.length - 1, new ArrayList<>(), sum);
  }

  private int sumOfSubSet(ArrayList<Integer> listOfNumbers) {
    int sum = 0;
    for (Integer listOfNumber : listOfNumbers) {
      sum = sum + listOfNumber;
    }
    return sum;
  }

  private void printElementsInList(ArrayList<Integer> listOfNumbers) {
    for (Integer listOfNumber : listOfNumbers) {
      System.out.print(listOfNumber + ",");
    }
    System.out.println();
  }

  private void printSubSets(int[] array, int curIndex, ArrayList<Integer> listOfNumbers, int sum) {
    if (curIndex > 0) {
      System.out.println("Calculating subset of " + array[curIndex]);
    }
    ArrayList<Integer> newSubSet = new ArrayList<>(listOfNumbers);
    if (curIndex < 0) {
      return;
    } else if (array[curIndex] + sumOfSubSet(listOfNumbers) < sum) {
      newSubSet.add(array[curIndex]);
      printSubSets(array, curIndex - 1, newSubSet, sum);
      printSubSets(array, curIndex - 1, listOfNumbers, sum);
    } else if (array[curIndex] + sumOfSubSet(listOfNumbers) == sum) {
      newSubSet.add(array[curIndex]);
      printElementsInList(newSubSet);
      printSubSets(array, curIndex - 1, listOfNumbers, sum);
    } else if (array[curIndex] + sumOfSubSet(listOfNumbers) > sum) {
      printSubSets(array, curIndex - 1, listOfNumbers, sum);
    }
  }
}
