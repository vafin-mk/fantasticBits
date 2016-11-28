package ai;

import model.Gate;
import model.commands.*;
import model.units.*;
import model.units.Vector;

import java.util.*;

public class AI {

  final int TEAM;
  final Scanner scanner;

  final Map<Integer, Wizard> wizards = new HashMap<>();
  final Map<Integer, Wizard> opponents = new HashMap<>();
  final Map<Integer, Snaffl> snaffls = new HashMap<>();
  final Map<Integer, Bludger> bludgers = new HashMap<>();

  final Gate myGate;
  final Gate enemyGate;

  private int manaPool = 0;

  public AI(Scanner scanner) {
    this.scanner = scanner;
    TEAM = scanner.nextInt();
    if (TEAM == 0) {
      myGate = new Gate(true);
      enemyGate = new Gate(false);
    } else {
      myGate = new Gate(false);
      enemyGate = new Gate(true);
    }
  }

  public void start() {
    while (true) {
      updateWorld();
      wizards.values().forEach(wizard -> makeDecision(wizard).execute());
    }
  }

  private Command makeDecision(Wizard wizard) {
    if (wizard.grabbed) {
      Vector target = enemyGate.closest(wizard);
      return new Throw(target.x, target.y, 500);
    }

    Snaffl target = findTarget(wizard);
    if (target == null) {
      target = closestTo(wizard);
    }
    return new Move(target.x, target.y, 150);
  }

  private Snaffl findTarget(Wizard wizard) {
    return null;
  }

  private Snaffl closestTo(Unit unit) {
    Snaffl closest = null;
    double closestDist = 100_500;
    for (Snaffl snaffl : snaffls.values()) {
      if (closest == null) closest = snaffl;
      double dist = snaffl.dist(unit);
      if (dist < closestDist) {
        closest = snaffl;
        closestDist = dist;
      }
    }
    return closest;
  }

  private void updateWorld() {
    manaPool++;
    if (manaPool > 100) {
      manaPool = 100;
    }
    List<Integer> snafflIds = new ArrayList<>();
    int entities = scanner.nextInt();
    for (int i = 0; i < entities; i++) {// number of entities still in game
      int entityId = scanner.nextInt(); // entity identifier
      String type = scanner.next();// "WIZARD", "OPPONENT_WIZARD" or "SNAFFLE" (or "BLUDGER" after first league)
      int x = scanner.nextInt(); // position
      int y = scanner.nextInt(); // position
      int vx = scanner.nextInt(); // velocity
      int vy = scanner.nextInt(); // velocity
      boolean grabbed = scanner.nextInt() == 1; // 1 if the wizard is holding a Snaffle, 0 otherwise
      switch (type) {
        case "WIZARD":
          wizards.putIfAbsent(entityId, new Wizard(entityId, x, y, vx, vy, grabbed));
          wizards.get(entityId).update(entityId, x, y, vx, vy, grabbed);
          break;
        case "OPPONENT_WIZARD":
          opponents.putIfAbsent(entityId, new Wizard(entityId, x, y, vx, vy, grabbed));
          opponents.get(entityId).update(entityId, x, y, vx, vy, grabbed);
          break;
        case "SNAFFLE":
          snafflIds.add(entityId);
          snaffls.putIfAbsent(entityId, new Snaffl(entityId, x, y, vx, vy, grabbed));
          snaffls.get(entityId).update(entityId, x, y, vx, vy, grabbed);
          break;
        case "BLUDGER":
          bludgers.putIfAbsent(entityId, new Bludger(entityId, x, y, vx, vy));
          bludgers.get(entityId).update(entityId, x, y, vx, vy);
          break;
        default:
          System.err.println("UNKNOWN:" + type);
          break;
      }
    }
    List<Integer> toRemove = new ArrayList<>();
    toRemove.addAll(snaffls.keySet());
    toRemove.removeAll(snafflIds);
    toRemove.forEach(snaffls::remove);
//    System.err.println(Arrays.toString(wizards.toArray()));
//    System.err.println(Arrays.toString(opponents.toArray()));
//    System.err.println(Arrays.toString(snaffls.toArray()));
//    System.err.println(Arrays.toString(bludgers.toArray()));
  }


}
