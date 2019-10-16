class Rubik extends SideViewable implements Cloneable {
    private final int[][][] grid;

    static public final int UP = 0;
    static public final int LEFT = 1;
    static public final int FRONT = 2;
    static public final int RIGHT = 3;
    static public final int DOWN = 4;
    static public final int BACK = 5;
  

    public Rubik(int[][][] grid) {
        super(grid);
        this.grid = grid;
    }

    public Rubik(Rubik rubik) {
        super(rubik.toIntArray());
        this.grid = rubik.toIntArray();
    }

    public Rubik left() {
        int[][][] newGrid = toIntArray();
        Face frontFace = new Face(this.grid[FRONT]);
        newGrid[FRONT] = frontFace.left().toIntArray();

        Face upFace = new Face(this.grid[UP]);
        Face leftFace = new Face(this.grid[LEFT]);
        Face rightFace = new Face(this.grid[RIGHT]);
        Face downFace = new Face(this.grid[DOWN]);

        newGrid[UP][2] = rightFace.getColumn(0);
        newGrid[LEFT] = leftFace.setColumn(2, upFace.getRowReversed(2)).toIntArray();
        newGrid[DOWN][0] = leftFace.getColumn(2);
        newGrid[RIGHT] = rightFace.setColumn(0, downFace.getRowReversed(0)).toIntArray();

        return new Rubik(newGrid);
    }

    public Rubik half() {
        return left().left();
    }

    public Rubik right() {
        return left().left().left();
    }

    public int[][][] toIntArray() {
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

    @Override
    public Rubik frontView() {
        return new Rubik(super.frontView().toGrid());
    }

    @Override
    public Rubik upView() {
        return new Rubik(super.upView().toGrid());
    }

    @Override
    public Rubik backView() {
        return new Rubik(super.backView().toGrid());
    }

    @Override
    public Rubik downView() {
        return new Rubik(super.downView().toGrid());
    }

    @Override
    public Rubik rightView() {
        return new Rubik(super.rightView().toGrid());
    }

    @Override
    public Rubik leftView() {
        return new Rubik(super.leftView().toGrid());
    }

    @Override
    public Rubik clone() {
        return new Rubik(toIntArray());
    }

    @Override
    public String toString() {
        String space = "......";
        String display = "\n";

        for (int i = 0; i < this.grid.length; i++) {
            // Skipping the first 2 faces of the middle row
            if (i == LEFT || i == FRONT) {
                continue;
            }

            Face face = new Face(this.grid[i]);

            for (int j = 0; j < this.grid[i].length; j++) {
                if (i == RIGHT) {
                    Face left = new Face(this.grid[LEFT]);
                    Face front = new Face(this.grid[FRONT]);
                    display += left.getGridRowString(j);
                    display += front.getGridRowString(j);
                    display += face.getGridRowString(j);
                } else {
                    // UP, DOWN, & BACK faces
                    display += space;
                    display += face.getGridRowString(j);
                    display += space;
                }

                display += "\n";
            }
        }

        return display;

    }
}
