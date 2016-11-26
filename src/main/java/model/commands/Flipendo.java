package model.commands;

import model.units.Bludger;
import model.units.Snaffl;
import model.units.Wizard;

public class Flipendo extends Spell{

  private Flipendo(int target) {
    super(target);
  }

  public Flipendo(Bludger bludger) {
    this(bludger.id);
  }

  public Flipendo(Snaffl snaffl) {
    this(snaffl.id);
  }

  public Flipendo(Wizard wizard) {
    this(wizard.id);
  }

  @Override int cost() {
    return COST_FLIPENDO;
  }

  @Override int duration() {
    return DURATION_FLIPENDO;
  }

  @Override
  public String toString() {
    return String.format(FORMAT_FLIPENDO, target);
  }
}
