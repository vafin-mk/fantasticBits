package model.commands;

public class Move extends Command{

  public static final int MAX_THRUST = 150;
  final int x, y, thrust;
  public Move(int x, int y, int thrust) {
    this.x = x;
    this.y = y;
    this.thrust = Math.max(thrust, MAX_THRUST);
  }

  @Override
  public String toString() {
    return String.format(Command.FORMAT_MOVE, x, y, thrust);
  }
}
