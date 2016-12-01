package model.commands;

import model.units.Vector;

public class Throw extends Command {
  public static final int MAX_POWER = 500;
  final int x, y, power;
  public Throw(Vector target, int power, int priority) {
    super(priority);
    this.x = target.x;
    this.y = target.y;
    this.power = Math.max(power, MAX_POWER);
  }

  @Override
  public String toString() {
    return String.format(Command.FORMAT_THROW, x, y, power, priority);
  }
}
