package Util;

public class State implements Comparable<State>{
    public final static int UP = 0;
    public final static int RIGHT = 1;
    public final static int DOWN = 2;
    public final static int LEFT = 3;

    public final static int SIZE = 3;

    public final static int EMPTY = 0;

    private int[][] vertex = new int[SIZE][SIZE];
    private State parent;
    private int deep;
    private Integer cost;

    public State(int c00, int c01, int c02,
                 int c10, int c11, int c12,
                 int c20, int c21, int c22) {
        vertex[0][0] = c00;
        vertex[0][1] = c01;
        vertex[0][2] = c02;
        vertex[1][0] = c10;
        vertex[1][1] = c11;
        vertex[1][2] = c12;
        vertex[2][0] = c20;
        vertex[2][1] = c21;
        vertex[2][2] = c22;

        parent = null;
        deep = 0;
        cost = 0;
    }

    public State(State state) {
        //vertex = cell.getVertex();

        int[][] tmp = state.getVertex();

        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(tmp[i], 0, vertex[i], 0, SIZE);
        }


        parent = state;
        deep = state.getDeep() + 1;
        cost = 0;
    }

    public State Move(int direction) {
        State result = new State(this);
        Position empty = this.getEmpty();
        switch (direction) {
            case (UP) : {
                if (empty.getY() == 0) {
                    return null;
                }
                result.getVertex()[empty.getY()][empty.getX()] = result.getVertex()[empty.getY() - 1][empty.getX()];
                result.getVertex()[empty.getY() - 1][empty.getX()] = EMPTY;
                break;
            }
            case (RIGHT) : {
                if (empty.getX() == (SIZE - 1)) {
                    return null;
                }
                result.getVertex()[empty.getY()][empty.getX()] = result.getVertex()[empty.getY()][empty.getX() + 1];
                result.getVertex()[empty.getY()][empty.getX() + 1] = EMPTY;
                break;
            }
            case (DOWN) : {
                if (empty.getY() == (SIZE - 1)) {
                    return null;
                }
                result.getVertex()[empty.getY()][empty.getX()] = result.getVertex()[empty.getY() + 1][empty.getX()];
                result.getVertex()[empty.getY() + 1][empty.getX()] = EMPTY;
                break;
            }
            case (LEFT) : {
                if (empty.getX() == 0) {
                    return null;
                }
                result.getVertex()[empty.getY()][empty.getX()] = result.getVertex()[empty.getY()][empty.getX() - 1];
                result.getVertex()[empty.getY()][empty.getX() - 1] = EMPTY;
                break;
            }
        }
        return result;
    }

    public void Print() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(vertex[i][j] + "   ");
            }
            System.out.print("\n");
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                result += vertex[i][j] + "   ";
            }
            result += "\n";
        }
        return result;
    }

    public Position getEmpty() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (vertex[i][j] == EMPTY) {
                    return new Position(j, i);
                }
            }
        }

        return null;
    }

    public State getParent() {
        return parent;
    }

    public int[][] getVertex() {
        return vertex;
    }

    public int getDeep() {
        return deep;
    }

    public boolean equals(Object object) {
        /*
        if (this == object) {
            return true;
        }
        */
        if ((object == null) || (this.getClass() != object.getClass())) {
            return false;
        }

        int[][] eqInCell = ((State) object).getVertex();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (vertex[i][j] != eqInCell[i][j]) {
                    return false;
                }
            }
        }

        /*
        System.out.println("=============================================");
        this.Print();
        System.out.println();
        ((Cell) object).Print();
        System.out.println("=============================================");
        */

        return true;
    }
    public int compareTo(State o) {
        int result = cost.compareTo(o.getCost());
        if (result == 0) {
            int[][] eqInCell = o.getVertex();

            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (vertex[i][j] < eqInCell[i][j]) {
                        return -1;
                    }
                    if (vertex[i][j] > eqInCell[i][j]) {
                        return 1;
                    }
                }
            }
        }
        return result;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
