package model.units;

public class Snaffl extends Unit {
  public static final int RADIUS = 150;

  public Snaffl(int id, int x, int y, int vx, int vy, boolean grabbed) {
    super(id, x, y, vx, vy, grabbed);
  }

  @Override
  public String toString() {
    return "Snaff" + super.toString();
  }
}
