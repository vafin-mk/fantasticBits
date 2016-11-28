package model.units;

public class Vector {
  public int x, y, vx, vy;

  public Vector(int x, int y) {
    this(x, y, 0, 0);
  }

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

  public double angle(Vector other) {
    double angle = StrictMath.atan2(other.y - y, other.x - x);
    while (angle > StrictMath.PI) {
      angle -= 2.0D * StrictMath.PI;
    }

    while (angle < -StrictMath.PI) {
      angle += 2.0D * StrictMath.PI;
    }

    return angle;
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
