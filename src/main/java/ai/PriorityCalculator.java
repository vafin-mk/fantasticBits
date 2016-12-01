package ai;

import model.Gate;
import model.Pair;
import model.units.Bludger;
import model.units.Snaffl;
import model.units.Vector;
import model.units.Wizard;
import java.util.Map;

public class PriorityCalculator {

  private final static double MAX_DIST = 18357;

  public int calculateThrow(Wizard wizard, Vector target) {
    return 100_500;
  }

  public int calculateMove(Wizard wizard, Vector target) {
    return (int) (MAX_DIST - wizard.dist(target));
  }

  public int calculateAccio(Wizard wizard, Snaffl target) {
    return 0;
  }

  public int calculateAccio(Wizard wizard, Bludger target) {
    return 0;
  }

  public int calculateFlipendo(Wizard wizard, Snaffl target) {
    return 0;
  }

  public int calculateFlipendo(Wizard wizard, Bludger target) {
    return 0;
  }

  public int calculateFlipendo(Wizard wizard, Wizard target) {
    return 0;
  }

  public int calculatePetrificus(Wizard wizard, Bludger target) {
    return 0;
  }

  public int calculatePetrificus(Wizard wizard, Snaffl target) {
    return 0;
  }

  public int calculatePetrificus(Wizard wizard, Wizard target) {
    return 0;
  }

  public int calculateObliviate(Wizard wizard, Bludger target) {
    return 0;
  }

  final Map<Integer, Wizard> wizards;
  final Map<Integer, Wizard> opponents;
  final Map<Integer, Snaffl> snaffls;
  final Map<Integer, Bludger> bludgers;

  final Gate myGate;
  final Gate enemyGate;

  PriorityCalculator(Gate myGate, Gate enemyGate, Map<Integer, Wizard> wizards, Map<Integer,
      Wizard> opponents, Map<Integer, Snaffl> snaffls, Map<Integer, Bludger> bludgers) {
    this.myGate = myGate;
    this.enemyGate = enemyGate;
    this.wizards = wizards;
    this.opponents = opponents;
    this.snaffls = snaffls;
    this.bludgers = bludgers;
  }
}
