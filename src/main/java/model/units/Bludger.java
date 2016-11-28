package model.units;

public class Bludger extends Unit {
  public static final int RADIUS = 200;
  public Bludger(int id, int x, int y, int vx, int vy) {
    super(id, x, y, vx, vy, false);
  }

  public void update(int id, int x, int y, int vx, int vy) {
    super.update(id, x, y, vx, vy, false);
  }

  @Override
  public String toString() {
    return "Bludger" + super.toString();
  }
}
