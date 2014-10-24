package rnikolaus.gameoflife;

/**
 *
 * @author rapnik
 */
public class DimXY {
    private final int x;
    private final int y;

    public DimXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.x;
        hash = 71 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DimXY other = (DimXY) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DimXY{" + "x=" + x + ", y=" + y + '}';
    }

    
    
    
}
