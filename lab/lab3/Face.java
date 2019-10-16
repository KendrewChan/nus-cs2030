class Face implements Cloneable {
    private final int[][] grid;

    Face(int[][] grid) {
        this.grid = grid;
    }

    public int[] getRow(int rowIndex) {
        return toIntArray()[rowIndex];
    }

    public int[] getRowReversed(int rowIndex) {
        int[] row = getRow(rowIndex);

        for (int i = 0; i < Math.floorDiv(row.length, 2); i++) {
            int current = row[i];
            row[i] = row[row.length - i - 1];
            row[row.length - i - 1] = current;
        }

        return row;
    }

    public int[] getColumn(int colIndex) {
        int[] col = new int[this.grid[0].length];

        for (int i = 0; i < this.grid.length; i++) {
            col[i] = this.grid[i][colIndex];
        }

        return col;
    }

    public Face setColumn(int colIndex, int[] col) {
        int[][] newGrid = toIntArray();

        for (int i = 0; i < this.grid.length; i++) {
            newGrid[i][colIndex] = col[i];
        }

        return new Face(newGrid);
    }

    public String getGridRowString(int rowIndex) {
        String rowString = "";
        for (int cell : this.grid[rowIndex]) {
            rowString += String.format("%02d", cell);
        }
        return rowString;
    }

    public String getPlainString() {
        String ans = "";
        for (int i = 0; i < this.grid.length; i++) {
            ans += getGridRowString(i);

            if (i != this.grid.length - 1) {
                ans += "\n";
            }
        }
        return ans;
    }

    public int[][] toIntArray() {
        int[][] newGrid = new int[this.grid.length][this.grid[0].length];

        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                newGrid[i][j] = this.grid[i][j];
            }
        }

        return newGrid;
    }

    public Face left() {
        int[][] newGrid = new int[this.grid.length][this.grid[0].length];

        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                newGrid[i][j] = this.grid[j][this.grid.length - 1 - i];
            }
        }

        return new Face(newGrid);
    }

    public Face flip() {
        return left().left();
    }

    public Face right() {
        return left().left().left();
    }

    public Face half() {
        int[][] newGrid = new int[this.grid.length][this.grid[0].length];

        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                newGrid[i][j] = this.grid[this.grid.length - 1 - i][this.grid.length - 1 - j];
            }
        }

        return new Face(newGrid);
    }

    @Override
    public Face clone() {
        return new Face(toIntArray());
    }

    @Override
    public String toString() {
        String ans = "\n";

        ans += getPlainString();
        ans += "\n";

        return ans;
    }
}
