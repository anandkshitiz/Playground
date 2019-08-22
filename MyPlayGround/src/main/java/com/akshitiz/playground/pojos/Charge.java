package com.akshitiz.playground.pojos;

public class Charge {
  private String procedureCode;

  public Charge(String procedureCode) {
    this.procedureCode = procedureCode;
  }

  public String getProcedureCode() {
    return procedureCode;
  }

  public void setProcedureCode(String procedureCode) {
    this.procedureCode = procedureCode;
  }
}
