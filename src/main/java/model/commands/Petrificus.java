package model.commands;

import model.units.Bludger;
import model.units.Snaffl;
import model.units.Wizard;

public class Petrificus extends Spell {

  private Petrificus(int target) {
    super(target);
  }

  public Petrificus(Bludger bludger) {
    this(bludger.id);
  }

  public Petrificus(Snaffl snaffl) {
    this(snaffl.id);
  }

  public Petrificus(Wizard wizard) {
    this(wizard.id);
  }

  @Override int cost() {
    return COST_PETRIFICUS;
  }

  @Override int duration() {
    return DURATION_PETRIFICUS;
  }

  @Override
  public String toString() {
    return String.format(FORMAT_PETRIFICUS, target);
  }
}
