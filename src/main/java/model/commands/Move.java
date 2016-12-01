package model.commands;

import model.units.Vector;

public class Move extends Command{

  public static final int MAX_THRUST = 150;
  final int x, y, thrust;
  public Move(Vector target, int thrust, int priority) {
    super(priority);
    this.x = target.x;
    this.y = target.y;
    this.thrust = Math.max(thrust, MAX_THRUST);
  }

  @Override
  public String toString() {
    return String.format(Command.FORMAT_MOVE, x, y, thrust, priority);
  }
}
