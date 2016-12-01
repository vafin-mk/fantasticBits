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

  final Queue<Command> commandQueue = new PriorityQueue<>();
  final PriorityCalculator calculator;

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

    calculator = new PriorityCalculator(myGate, enemyGate, wizards, opponents, snaffls, bludgers);
  }

  public void start() {
    while (true) {
      updateWorld();
      wizards.values().forEach(wizard -> makeDecision(wizard).execute());
    }
  }

  private Command makeDecision(Wizard wizard) {
    buildQueue(wizard);
    Command command = commandQueue.poll();
    if (command instanceof Spell) {
      manaPool -= ((Spell) command).type.cost;
    }
    return command;
  }

  private void buildQueue(Wizard wizard) {
    commandQueue.clear();
    if (wizard.grabbed) {//todo more targets
      Vector target = enemyGate.closest(wizard);
      commandQueue.add(new Throw(target, 500, calculator.calculateThrow(wizard, target)));
    }

    if (manaPool > SpellType.ACCIO.cost) {
      snaffls.values().forEach(snaffl -> commandQueue.add(new Spell(SpellType.ACCIO, snaffl.id, calculator.calculateAccio(wizard, snaffl))));
      bludgers.values().forEach(bludger -> commandQueue.add(new Spell(SpellType.ACCIO, bludger.id, calculator.calculateAccio(wizard, bludger))));
    }

    if (manaPool > SpellType.FLIPENDO.cost) {
      snaffls.values().forEach(snaffl -> commandQueue.add(new Spell(SpellType.FLIPENDO, snaffl.id, calculator.calculateFlipendo(wizard, snaffl))));
      bludgers.values().forEach(bludger -> commandQueue.add(new Spell(SpellType.FLIPENDO, bludger.id, calculator.calculateFlipendo(wizard, bludger))));
      opponents.values().forEach(opponent -> commandQueue.add(new Spell(SpellType.FLIPENDO, opponent.id, calculator.calculateFlipendo(wizard, opponent))));
      //todo ally wizards?
    }

    if (manaPool > SpellType.PETRIFICUS.cost) {
      snaffls.values().forEach(snaffl -> commandQueue.add(new Spell(SpellType.PETRIFICUS, snaffl.id, calculator.calculatePetrificus(wizard, snaffl))));
      bludgers.values().forEach(bludger -> commandQueue.add(new Spell(SpellType.PETRIFICUS, bludger.id, calculator.calculatePetrificus(wizard, bludger))));
      opponents.values().forEach(opponent -> commandQueue.add(new Spell(SpellType.PETRIFICUS, opponent.id, calculator.calculatePetrificus(wizard, opponent))));
    }

    if (manaPool > SpellType.OBLIVIATE.cost) {
      bludgers.values().forEach(bludger -> commandQueue.add(new Spell(SpellType.OBLIVIATE, bludger.id, calculator.calculateObliviate(wizard, bludger))));
    }
    snaffls.values().forEach(snaffl -> commandQueue.add(new Move(snaffl, 150, calculator.calculateMove(wizard, snaffl))));
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
          wizards.putIfAbsent(entityId, new Wizard(entityId, x, y, vx, vy, grabbed,
              entityId % 2 == 0 ? WizardType.ATTACKER : WizardType.DEFENDER));
          wizards.get(entityId).update(entityId, x, y, vx, vy, grabbed);
          break;
        case "OPPONENT_WIZARD":
          opponents.putIfAbsent(entityId, new Wizard(entityId, x, y, vx, vy, grabbed, WizardType.ATTACKER));
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

    List<Vector> all = new ArrayList<>();
    all.addAll(wizards.values());
    all.addAll(opponents.values());
    all.addAll(snaffls.values());
    all.addAll(bludgers.values());

    all.forEach(vector -> {
      vector.distToMyGate = vector.dist(myGate.closest(vector));
      vector.distToEnemyGate = vector.dist(enemyGate.closest(vector));
    });


//    System.err.println(Arrays.toString(wizards.toArray()));
//    System.err.println(Arrays.toString(opponents.toArray()));
//    System.err.println(Arrays.toString(snaffls.toArray()));
//    System.err.println(Arrays.toString(bludgers.toArray()));
  }


}
