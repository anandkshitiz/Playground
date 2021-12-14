/*
 * Copyright (c) 2019 athenahealth, Inc. All Rights Reserved.
 */
package com.akshitiz.playground.interest.arrays;

/**
 * @author akshitiz
 * @since 02/05/20
 */
public class Problems {

  public static int findMaxSumOfSubArray(int[] array, int k) {

    if (k > array.length) {
      return -1;
    }

    int startIndex = 0;
    int endIndex = startIndex + k - 1;

    int sumOfKElements = 0;
    for (int i = 0; i <= endIndex; i++) {
      sumOfKElements += array[i];
    }
    int maxSum = 0;

    for (startIndex = 1; startIndex <= array.length - k; startIndex++) {
      endIndex = startIndex + k - 1;
      sumOfKElements = sumOfKElements - array[startIndex - 1] + array[endIndex];
      if (sumOfKElements > maxSum) {
        maxSum = sumOfKElements;
      }
    }
    return maxSum;
  }
}
