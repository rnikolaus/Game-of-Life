package rnikolaus.gameoflife;

import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author rapnik
 */
public class Shape {

    private static final Shape GLIDER = new Shape("Glider", new int[][]{{1, 0}, {2, 1}, {2, 2}, {1, 2}, {0, 2}});
    private static final Shape SMALLEXPL = new Shape("Small Exploder", new int[][]{{0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 3}, {2, 1}, {2, 2}});
    private static final Shape EXPLODER = new Shape("Exploder", new int[][]{{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {2, 0}, {2, 4}, {4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4}});
    private static final Shape CELL10 = new Shape("10 Cell Row", new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}});
    private static final Shape FISH = new Shape("Lightweight spaceship", new int[][]{{0, 1}, {0, 3}, {1, 0}, {2, 0}, {3, 0}, {3, 3}, {4, 0}, {4, 1}, {4, 2}});
    private static final Shape PUMP = new Shape("Tumbler", new int[][]{{0, 3}, {0, 4}, {0, 5}, {1, 0}, {1, 1}, {1, 5}, {2, 0}, {2, 1}, {2, 2}, {2, 3}, {2, 4}, {4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4}, {5, 0}, {5, 1}, {5, 5}, {6, 3}, {6, 4}, {6, 5}});
    private static final Shape SHOOTER = new Shape("Gosper Glider Gun", new int[][]{{0, 2}, {0, 3}, {1, 2}, {1, 3}, {8, 3}, {8, 4}, {9, 2}, {9, 4}, {10, 2}, {10, 3}, {16, 4}, {16, 5}, {16, 6}, {17, 4}, {18, 5}, {22, 1}, {22, 2}, {23, 0}, {23, 2}, {24, 0}, {24, 1}, {24, 12}, {24, 13}, {25, 12}, {25, 14}, {26, 12}, {34, 0}, {34, 1}, {35, 0}, {35, 1}, {35, 7}, {35, 8}, {35, 9}, {36, 7}, {37, 8}});

    private final String name;
    private final int[][] values;

    private Shape(String name, int[][] i) {
        this.name = name;
        values = i;
    }

    public static Shape[] getAll() {
        Shape[] result = new Shape[]{CELL10, EXPLODER, FISH, GLIDER, PUMP, SHOOTER, SMALLEXPL};
        return result;
    }

    public Collection<DimXY> getShapeAsDim() {
        HashSet<DimXY> result = new HashSet<>();
        for (int[] vals : values) {
            DimXY dim = new DimXY(vals[0], vals[1]);
            result.add(dim);
        }
        return result;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

}
