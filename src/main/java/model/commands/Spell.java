package model.commands;

public abstract class Spell extends Command{

  public static final int COST_OBLIVIATE = 5;
  public static final int COST_PETRIFICUS = 10;
  public static final int COST_ACCIO = 20;
  public static final int COST_FLIPENDO = 20;

  public static final int DURATION_OBLIVIATE = 5;
  public static final int DURATION_PETRIFICUS = 1;
  public static final int DURATION_ACCIO = 6;
  public static final int DURATION_FLIPENDO = 3;

  final int target;
  public Spell(int target) {
    this.target = target;
  }

  abstract int cost();
  abstract int duration();
}
