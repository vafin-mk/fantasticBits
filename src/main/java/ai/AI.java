package ai;

import model.commands.Command;
import model.commands.Move;
import model.commands.Throw;
import model.units.*;
import model.units.Vector;

import java.util.*;

public class AI {

  final int TEAM;
  final Scanner scanner;

  final List<Wizard> wizards = new ArrayList<>();
  final List<Wizard> opponents = new ArrayList<>();
  final List<Snaffl> snaffls = new ArrayList<>();
  final List<Bludger> bludgers = new ArrayList<>();

  final Vector enemyGoal;

  private int manaPool = 0;

  public AI(Scanner scanner) {
    this.scanner = scanner;
    TEAM = scanner.nextInt();
    if (TEAM == 0) {
      enemyGoal = new Vector(16000, 3750, 0, 0);
    } else {
      enemyGoal = new Vector(0, 3750, 0, 0);
    }
  }

  public void start() {
    while (true) {
      updateWorld();
      wizards.forEach(wizard -> makeDecision(wizard).execute());
    }
  }

  private Command makeDecision(Wizard wizard) {
    if (wizard.grabbed) {
      return new Throw(enemyGoal.x, enemyGoal.y, 500);
    }

    Snaffl target = closestTo(wizard);
    return new Move(target.x, target.y, 150);
  }

  private Snaffl closestTo(Unit unit) {
    return snaffls.stream()
        .sorted((sn1, sn2) -> Double.compare(sn1.dist(unit), sn2.dist(unit)))
        .findFirst().get();
  }

  private void updateWorld() {
    manaPool++;
    if (manaPool > 100) {
      manaPool = 100;
    }
    wizards.clear();
    opponents.clear();
    snaffls.clear();
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
          wizards.add(new Wizard(entityId, x, y, vx, vy, grabbed));
          break;
        case "OPPONENT_WIZARD":
          opponents.add(new Wizard(entityId, x, y, vx, vy, grabbed));
          break;
        case "SNAFFLE":
          snaffls.add(new Snaffl(entityId, x, y, vx, vy, grabbed));
          break;
        case "BLUDGER":
          bludgers.add(new Bludger(entityId, x, y, vx, vy));
          break;
        default:
          System.err.println("UNKNOWN:" + type);
          break;
      }

    }
//    System.err.println(Arrays.toString(wizards.toArray()));
//    System.err.println(Arrays.toString(opponents.toArray()));
//    System.err.println(Arrays.toString(snaffls.toArray()));
//    System.err.println(Arrays.toString(bludgers.toArray()));
  }


}
