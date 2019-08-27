package com.akshitiz.playground.config;

import com.akshitiz.playground.questions.collections.PlayWithListRemover;
import com.akshitiz.playground.questions.dynamic.SubSetSumProblem;

public class Main {

  public static void main(String[] args) {

    // 1. Play with different List removal techniques in Java
    PlayWithListRemover playWithListRemover = new PlayWithListRemover();
    //playWithListRemover.removeFromList();

    SubSetSumProblem subSetSumProblem = new SubSetSumProblem();
    subSetSumProblem.playSubSetSumProblem();
  }
}
