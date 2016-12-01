package model.commands;

public class Spell extends Command{

  public final SpellType type;
  final int target;
  public Spell(SpellType type, int target, int priority) {
    super(priority);
    this.type = type;
    this.target = target;
  }

  @Override
  public String toString() {
    return String.format(FORMAT_SPELL, type, target, priority);
  }
}
