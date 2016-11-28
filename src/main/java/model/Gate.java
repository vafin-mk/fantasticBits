package model;

import model.units.Vector;

import java.util.ArrayList;
import java.util.List;

public class Gate {

  public static final int GATE_CENTER = 3750;
  public static final int GATE_LENGTH = 2000;
  public static final int POLE_RADIUS = 300;

  public static final int TOP_POLE_Y = GATE_CENTER + GATE_LENGTH / 2 - POLE_RADIUS;
  public static final int BOTTOM_POLE_Y = GATE_CENTER - GATE_LENGTH / 2 + POLE_RADIUS;

  private static final int GATE_POINT_STEP = 50;

  final List<Vector> gatePoints = new ArrayList<>();
  public final int x;
  public Gate(boolean left) {
     x = left ? 0 : 16000;

    for (int y = BOTTOM_POLE_Y; y < TOP_POLE_Y; y += GATE_POINT_STEP) {
      gatePoints.add(new Vector(x, y));
    }
  }

  public Vector closest(Vector vector) {
    Vector closest = gatePoints.get(0);
    double closestDist = 100_500;
    for (Vector gatePoint : gatePoints) {
      double dist = gatePoint.dist(vector);
      if (dist < closestDist) {
        closest = gatePoint;
        closestDist = dist;
      }
    }
    return closest;
  }
}
