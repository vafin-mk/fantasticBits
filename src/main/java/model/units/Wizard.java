package model.units;

public class Wizard extends Unit {
  public static final int RADIUS = 400;
  public Wizard(int id, int x, int y, int vx, int vy, boolean grabbed) {
    super(id, x, y, vx, vy, grabbed);
  }

  @Override
  public String toString() {
    return "WiZARD" + super.toString();
  }
}
