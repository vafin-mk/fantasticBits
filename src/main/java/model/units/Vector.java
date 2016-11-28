package model.units;

public class Vector {
  public int x, y, vx, vy;

  public Vector(int x, int y, int vx, int vy) {
    this.x = x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
  }

  public void update(int x, int y, int vx, int vy) {
    this.x = x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
  }

  public double dist(Vector other) {
    return StrictMath.hypot(other.x - x, other.y - y);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Vector vector = (Vector) o;

    if (x != vector.x) return false;
    return y == vector.y;

  }

  @Override
  public int hashCode() {
    int result = x;
    result = 31 * result + y;
    return result;
  }

  @Override
  public String toString() {
    return String.format("(x = %s; y = %s; vx = %s; vy = %s)", x, y, vx, vy);
  }
}
