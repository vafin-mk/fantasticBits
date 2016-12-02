package ai;

import model.Gate;
import model.Pair;
import model.units.*;

import java.util.Map;

public class PriorityCalculator {

  private final static double MAX_DIST = 18357;

  public int calculateThrow(Wizard wizard, Vector target) {
    return 100_500;
  }

  public int calculateMove(Wizard wizard, Vector target) {
//    if (wizard.type == WizardType.ATTACKER)
      return (int) (MAX_DIST - wizard.dist(target));
//    return (int) (MAX_DIST - target.distToMyGate);
  }

  public int calculateAccio(Wizard wizard, Snaffl target) {
    if (wizard.distToMyGate < target.distToMyGate) return -1;
    double dist = wizard.dist(target);
    if (dist < 200 || dist > 4000) return -1;
    return (int) (MAX_DIST - dist);
  }

  public int calculateAccio(Wizard wizard, Bludger target) {
    return 0;
  }

  public int calculateFlipendo(Wizard wizard, Snaffl target) {
    double dist = wizard.dist(target);
    if (dist > 5000) return -1;
    double angleToSnaffl = wizard.angle(target);
    Pair<Double, Double> angleToEnemyGate = enemyGate.angle(wizard);
    boolean shooting = angleToEnemyGate.first <= angleToSnaffl  && angleToSnaffl <= angleToEnemyGate.second;
    if (!shooting) return -1;//todo reflect from edges
    return (int) (MAX_DIST - dist) * 4;
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

  //MIN( 6000 / ( Dist / 1000 )^2, 1000 ) - flip power
}
