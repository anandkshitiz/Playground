package com.akshitiz.playground.pojos;

import java.util.List;

public class Claim {

  List<Charge> chargeList;

  public List<Charge> getChargeList() {
    return chargeList;
  }

  public void setChargeList(List<Charge> chargeList) {
    this.chargeList = chargeList;
  }
}
