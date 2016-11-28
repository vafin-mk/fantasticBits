package model.units;

public class Unit extends Vector{

  public int id;
  public boolean grabbed;

  Unit(int id, int x, int y, int vx, int vy, boolean grabbed) {
    super(x, y, vx, vy);
    this.id = id;
    this.grabbed = grabbed;
  }

  public void update(int id, int x, int y, int vx, int vy, boolean grabbed) {
    super.update(x, y, vx, vy);
    this.id = id;
    this.grabbed = grabbed;
  }
}
