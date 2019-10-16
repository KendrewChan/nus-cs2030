class Dice extends SideViewable {
  static public final int UP = 0;
  static public final int LEFT = 1;
  static public final int FRONT = 2;
  static public final int RIGHT = 3;
  static public final int DOWN = 4;
  static public final int BACK = 5;
  static public final char[] nameMap = { 'U', 'L', 'F', 'R', 'D', 'B' };

  public Dice() {
    super(new int[][][] { { { UP } }, { { LEFT } }, { { FRONT } }, { { RIGHT } }, { { DOWN } }, { { BACK } } });
  }

  public Dice(int[][][] grid) {
    super(grid);
  }

  @Override
  public Dice frontView() {
      return new Dice();
  }

  @Override
  public Dice upView() {
      return new Dice(super.upView().toGrid());
  }

  @Override
  public Dice backView() {
      return new Dice(super.backView().toGrid());
  }

  @Override
  public Dice downView() {
      return new Dice(super.downView().toGrid());
  }

  @Override
  public Dice rightView() {
      return new Dice(super.rightView().toGrid());
  }

  @Override
  public Dice leftView() {
      return new Dice(super.leftView().toGrid());
  }

  @Override
  public String toString() {
    int[][][] grid = super.toGrid();

    return String.format("\n%s\n%s%s%s%s\n   %s", nameMap[grid[UP][0][0]], nameMap[grid[FRONT][0][0]],
        nameMap[grid[RIGHT][0][0]], nameMap[grid[BACK][0][0]], nameMap[grid[LEFT][0][0]], nameMap[grid[DOWN][0][0]]);
  }
}