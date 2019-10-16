class SideViewable {
  public final int[][][] grid;

  static public final int UP = 0;
  static public final int LEFT = 1;
  static public final int FRONT = 2;
  static public final int RIGHT = 3;
  static public final int DOWN = 4;
  static public final int BACK = 5;

  SideViewable(int[][][] grid) {
    this.grid = grid;
  }

  public Face[] toFaces() {
    Face[] faces = new Face[grid.length];
    for (int i = 0; i < grid.length; i++) {
      faces[i] = new Face(grid[i]);
    }

    return faces;
  }

  public int[][][] toGrid() {
    int[][][] newGrid = new int[this.grid.length][this.grid[0].length][this.grid[0][0].length];

    for (int i = 0; i < this.grid.length; i++) {
      for (int j = 0; j < this.grid[i].length; j++) {
        for (int k = 0; k < this.grid[i][j].length; k++) {
          newGrid[i][j][k] = this.grid[i][j][k];
        }
      }
    }

    return newGrid;
  }

  private int[][][] facesToGrid(Face[] faces) {
    int[][][] newGrid = new int[grid.length][grid[0].length][grid[0][0].length];

    for (int i = 0; i < faces.length; i++) {
      newGrid[i] = faces[i].toIntArray();
    }

    return newGrid;
  }

  public SideViewable frontView() {
    return new SideViewable(toGrid());
  }

  public SideViewable upView() {
    Face[] oldFaces = toFaces();
    Face[] newFaces = toFaces();

    newFaces[UP] = oldFaces[BACK];
    newFaces[LEFT] = oldFaces[LEFT].right();
    newFaces[FRONT] = oldFaces[UP];
    newFaces[RIGHT] = oldFaces[RIGHT].left();
    newFaces[DOWN] = oldFaces[FRONT];
    newFaces[BACK] = oldFaces[DOWN];

    return new SideViewable(facesToGrid(newFaces));
  }

  public SideViewable rightView() {
    Face[] oldFaces = toFaces();
    Face[] newFaces = toFaces();

    newFaces[UP] = oldFaces[UP].right();
    newFaces[LEFT] = oldFaces[FRONT];
    newFaces[FRONT] = oldFaces[RIGHT];
    newFaces[RIGHT] = oldFaces[BACK].flip();
    newFaces[DOWN] = oldFaces[DOWN].left();
    newFaces[BACK] = oldFaces[LEFT].flip();

    return new SideViewable(facesToGrid(newFaces));
  }

  public SideViewable leftView() {
    return rightView().rightView().rightView();
  }

  public SideViewable downView() {
    return upView().upView().upView();
  }

  public SideViewable backView() {
    Face[] oldFaces = toFaces();
    Face[] newFaces = toFaces();

    newFaces[UP] = oldFaces[UP].flip();
    newFaces[LEFT] = oldFaces[RIGHT];
    newFaces[FRONT] = oldFaces[BACK].flip();
    newFaces[RIGHT] = oldFaces[LEFT];
    newFaces[DOWN] = oldFaces[DOWN].flip();
    newFaces[BACK] = oldFaces[FRONT].flip();

    return new SideViewable(facesToGrid(newFaces));
  }
}