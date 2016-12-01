package model.units;

public class Wizard extends Unit {
  public static final int RADIUS = 400;
  public final WizardType type;
  public Wizard(int id, int x, int y, int vx, int vy, boolean grabbed, WizardType wizardType) {
    super(id, x, y, vx, vy, grabbed);
    this.type = wizardType;
  }

  @Override
  public String toString() {
    return "WiZARD" + super.toString();
  }
}
