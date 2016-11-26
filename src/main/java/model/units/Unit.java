package model.units;

public class Unit extends Vector{

  public final int id;
  public final boolean grabbed;

  Unit(int id, int x, int y, int vx, int vy, boolean grabbed) {
    super(x, y, vx, vy);
    this.id = id;
    this.grabbed = grabbed;
  }
}
