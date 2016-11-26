package model.commands;

public class Throw extends Command {
  public static final int MAX_POWER = 500;
  final int x, y, power;
  public Throw(int x, int y, int power) {
    this.x = x;
    this.y = y;
    this.power = Math.max(power, MAX_POWER);
  }

  @Override
  public String toString() {
    return String.format(Command.FORMAT_THROW, x, y, power);
  }
}
