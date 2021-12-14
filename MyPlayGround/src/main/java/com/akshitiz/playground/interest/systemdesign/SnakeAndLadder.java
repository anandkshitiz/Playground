package com.akshitiz.playground.interest.systemdesign;

public class SnakeAndLadder {

  class PositionCoordinate {
    int x;
    int y;

    PositionCoordinate(int x, int y) {
      this.x = x;
      this.y = y;
    }

    int getX() {
      return x;
    }

    void setX(int x) {
      this.x = x;
    }

    int getY() {
      return y;
    }

    public void setY(int y) {
      this.y = y;
    }
  }

  class Square {

    PositionCoordinate positionCoordinate;
    boolean isSnakeHead;
    boolean isSnakeTail;
    boolean isLadderStart;
    boolean isLadderEnd;
    int xSnakeOtherEnd;
    int ySnakeOtherEnd;
    int xLadderOtherEnd;
    int yLadderOtherEnd;
  }

  class Player {

    String colour;
    PositionCoordinate currentPosition;
    PositionCoordinate previousPosition;

    Player(String colour) {
      this.colour = colour;
      currentPosition = new PositionCoordinate(-1, -1);
    }

    public String getKey() {
      return colour;
    }

    public void setKey(String key) {
      this.colour = key;
    }

    PositionCoordinate getCurrentPosition() {
      return currentPosition;
    }

    void setCurrentPosition(PositionCoordinate currentPosition) {
      this.currentPosition = currentPosition;
    }

    PositionCoordinate getPreviousPosition() {
      return previousPosition;
    }

    void setPreviousPosition(PositionCoordinate previousPosition) {
      this.previousPosition = previousPosition;
    }
  }

  private PositionCoordinate calculatePositionFromIndex(int index) {
    if (index <= 0 || index > 100) {
      return null;
    } else if (index == 100) {
      return new PositionCoordinate(0, 0);
    } else if (index == 1) {
      return new PositionCoordinate(99, 0);
    } else {
      return new PositionCoordinate(9 - index / 10, 9 - index / 10 + 1);
    }
  }

  private int calculateIndexFromPosition(PositionCoordinate positionCoordinate) {
    if (positionCoordinate.getX() > 9
        || positionCoordinate.getX() < 0
        || positionCoordinate.getY() > 9
        || positionCoordinate.getY() < 0) {
      return -1;
    } else {
      return 9 * (positionCoordinate.getX() + 1) + (9 * positionCoordinate.getY());
    }
  }

  private boolean isAValidRollOfDice(Player playerRollingTheDice, int diceScore) {
    return diceScore >= 0
        && diceScore <= 6
        && calculateIndexFromPosition(playerRollingTheDice.getCurrentPosition()) + diceScore <= 100;
  }

  private void rollADice(Player player, int diceScore) {
    if (isAValidRollOfDice(player, diceScore)) {

      player.setPreviousPosition(player.getCurrentPosition());

      player.setCurrentPosition(
          calculatePositionFromIndex(
              calculateIndexFromPosition(player.getCurrentPosition()) + diceScore));
    }
  }

  Square board[][] = new Square[100][100];

  private void setUpInitialBoard() {
    // Logic to setup snakes and ladders
  }

  public void play() {}
}
