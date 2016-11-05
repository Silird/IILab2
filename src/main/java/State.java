public class State implements Comparable<State>{
    public final static int UP = 0;
    public final static int RIGHT = 1;
    public final static int DOWN = 2;
    public final static int LEFT = 3;

    private final static int SIZE = 3;

    public final static int EMPTY = 0;

    private int[][] vertex = new int[SIZE][SIZE];
    private State parent;
    private int deep;

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
    }

    public State(State state) {
        //vertex = cell.getVertex();

        int[][] tmp = state.getVertex();

        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(tmp[i], 0, vertex[i], 0, SIZE);
        }


        parent = state;
        deep = state.getDeep() + 1;
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

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * <p>
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     * <p>
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     * <p>
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     * <p>
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     * <p>
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    public int compareTo(State o) {
        //if (this.equals(o)) {
        //    return 0;
        //}

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
        return 0;
    }
}
