package model.commands;

import model.units.Bludger;

public class Obliviate extends Spell {

  private Obliviate(int target) {
    super(target);
  }

  public Obliviate(Bludger bludger) {
    this(bludger.id);
  }

  @Override int cost() {
    return COST_OBLIVIATE;
  }

  @Override int duration() {
    return DURATION_OBLIVIATE;
  }

  @Override
  public String toString() {
    return String.format(Command.FORMAT_OBLIVIATE, target);
  }
}
