package model.commands;

public enum SpellType {

  OBLIVIATE(5, 3), PETRIFICUS(10, 1), ACCIO(20, 6), FLIPENDO(20, 3);

  public final int cost, duration;
  SpellType(int cost, int duration) {
    this.cost = cost;
    this.duration = duration;
  }


}
