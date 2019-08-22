package com.akshitiz.playground.utilities;

import com.akshitiz.playground.pojos.Charge;
import com.akshitiz.playground.pojos.Claim;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class PlayWithListRemover {

  public void removeFromList() {
    Claim claim = new Claim();
    List<Charge> charges = new ArrayList<Charge>();
    charges.add(new Charge("123"));
    charges.add(new Charge("456"));
    charges.add(new Charge("789"));
    charges.add(new Charge("101112"));
    charges.add(new Charge("131415"));
    claim.setChargeList(charges);

    Charge chargeToRemove = new Charge("123");

    System.out.println("Claim before removing charge 123 ");
    printClaim(claim);
    // removeChargeUsingIterator(claim, chargeToRemove);
    // removeChargeUsingRemoveIf(claim, chargeToRemove);
    removeChargeUsingStreamFilter(claim, chargeToRemove);
    System.out.println("Claim after removing charge 123 ");
    printClaim(claim);
  }

  private void printClaim(Claim claim) {
    claim.getChargeList().forEach(charge -> System.out.println(charge.getProcedureCode()));
  }

  private void removeChargeUsingIterator(Claim claim, Charge chargeToRemove) {

    Iterator<Charge> chargeIterator = claim.getChargeList().iterator();
    while (chargeIterator.hasNext()) {
      if (chargeIterator.next().getProcedureCode().equals(chargeToRemove.getProcedureCode())) {
        chargeIterator.remove();
      }
    }
  }

  private void removeChargeUsingRemoveIf(Claim claim, final Charge chargeToRemove) {

    claim
        .getChargeList()
        .removeIf(charge -> charge.getProcedureCode().equals(chargeToRemove.getProcedureCode()));
  }

  private void removeChargeUsingStreamFilter(Claim claim, final Charge chargeToRemove) {

    claim
        .getChargeList()
        .removeAll(
            claim.getChargeList().stream()
                .filter(
                    charge -> charge.getProcedureCode().equals(chargeToRemove.getProcedureCode()))
                .collect(Collectors.toList()));
  }
}
