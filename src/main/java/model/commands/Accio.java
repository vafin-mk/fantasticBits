package model.commands;

import model.units.Bludger;
import model.units.Snaffl;

public class Accio extends Spell{

  private Accio(int target) {
    super(target);
  }

  public Accio(Bludger bludger) {
    this(bludger.id);
  }

  public Accio(Snaffl snaffl) {
    this(snaffl.id);
  }

  @Override int cost() {
    return COST_ACCIO;
  }

  @Override int duration() {
    return DURATION_ACCIO;
  }

  @Override
  public String toString() {
    return String.format(FORMAT_ACCIO, target);
  }
}
