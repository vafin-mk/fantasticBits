package model.commands;

public abstract class Command implements Comparable<Command> {

  public final int priority;

  public Command(int priority) {
    this.priority = priority;
  }

  @Override
  public int compareTo(Command o) {
    return Integer.compare(o.priority, priority);
  }

  public static final String FORMAT_MOVE = "MOVE %1$s %2$s %3$s MOVE(%1$s,%2$s-%3$s)[%4$s]";
  public static final String FORMAT_THROW = "THROW %1$s %2$s %3$s THROW((%1$s,%2$s-%3$s)[%4$s]";
  public static final String FORMAT_SPELL = "%1$s %2$s %1$s(%2$s)[%3$s]";

  public final void execute() {
    System.out.println(this);
  }
}
