package model.commands;

public abstract class Command {

  public static final String FORMAT_MOVE = "MOVE %s %s %s";
  public static final String FORMAT_THROW = "THROW %s %s %s";
  public static final String FORMAT_OBLIVIATE = "OBLIVIATE %s";
  public static final String FORMAT_PETRIFICUS = "PETRIFICUS %s";
  public static final String FORMAT_ACCIO = "ACCIO %s";
  public static final String FORMAT_FLIPENDO = "FLIPENDO %s";

  public final void execute() {
    System.out.println(this);
  }
}
