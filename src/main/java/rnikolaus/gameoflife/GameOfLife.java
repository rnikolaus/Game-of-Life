package rnikolaus.gameoflife;

import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author rapnik
 */
public class GameOfLife {

    private final int sizeX;
    private final int sizeY;
    private Set<DimXY> currentGeneration;

    public GameOfLife(int x, int y) {
        this.sizeX = x;
        this.sizeY = y;
        currentGeneration = new HashSet<>();
    }

    public void randomize(int count) {
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            flipCell(r.nextInt(sizeX), r.nextInt(sizeY));
        }

    }

    public Collection<DimXY> getSurroundingPositions(DimXY dimXY) {
        int x = dimXY.getX();
        int y = dimXY.getY();
        List<DimXY> result = new ArrayList<>();
        for (int xx = x - 1; xx < x + 2; xx++) {
            for (int yy = y - 1; yy < y + 2; yy++) {
                if (xx == x && yy == y) {
                    continue;
                }
                result.add(new DimXY(handleOverflow(xx, sizeX), handleOverflow(yy, sizeY)));
            }
        }
        return result;
    }

    public boolean isAliveNextGeneration(DimXY dim) {
        boolean alive = currentGeneration.contains(dim);
        Collection<DimXY> neighbors = getSurroundingPositions(dim);
        neighbors.retainAll(currentGeneration);
//        return isAliveNextGeneration(alive, neighbors.size());
        return alive && (neighbors.size() == 2 || neighbors.size() == 3) || !alive && neighbors.size() == 3;
//        return (alive && (neighbors.size() == 3 || neighbors.size() == 4 || neighbors.size() > 5))
//                || (!alive && (neighbors.size() == 3 || neighbors.size() > 5));
    }

    public boolean isAliveNextGeneration(boolean alive, int neighbors) {
        if (alive) {
            return neighbors == 3
                    || neighbors == 4
                    || neighbors == 6
                    || neighbors == 7
                    || neighbors == 8;

        } else {
            return neighbors == 3
                    || neighbors == 6
                    || neighbors == 7
                    || neighbors == 8;
        }
    }

    public void nextGeneration() {
        HashSet<DimXY> nextGen = new HashSet<>();
        HashSet<DimXY> positionsToConsider = new HashSet<>();
        for (DimXY dim : currentGeneration) {
            positionsToConsider.add(dim);
            positionsToConsider.addAll(getSurroundingPositions(dim));
        }
        for (DimXY dim : positionsToConsider) {
            if (isAliveNextGeneration(dim)) {
                nextGen.add(dim);
            }
        }

        currentGeneration = nextGen;
    }

    private int handleOverflow(int value, int maxvalue) {

        if (value >= maxvalue) {
            return value - maxvalue;
        }
        if (value < 0) {
            return maxvalue + value;
        }
        return value;
    }

    public void flipCell(int x, int y) {
        if (x < 0 || x >= sizeX || y < 0 || y >= sizeY) {
            throw new IllegalArgumentException("x=" + x + ",y=" + y + " is out of range");
        }
        DimXY dimXY = new DimXY(x, y);
        if (currentGeneration.add(dimXY)) {
        } else {
            currentGeneration.remove(dimXY);
        }
    }

    public boolean isEmpty() {
        return currentGeneration.isEmpty();
    }
    public void reset(){
        currentGeneration.clear();
    }

    public boolean[][] getFieldArray() {
        boolean[][] result = new boolean[sizeX][sizeY];
        for (DimXY dim : currentGeneration) {
            result[dim.getX()][dim.getY()] = true;
        }
        return result;
    }

    public void print() {
        boolean[][] data = getFieldArray();
        System.out.println("----------------------------------");
        for (int y = 0; y < data[0].length; y++) {
            for (int x = 0; x < data.length; x++) {
                if (data[x][y]) {
                    System.out.print("X");
                } else {
                    System.out.print(" ");
                }

            }
            System.out.println("");
        }
        System.out.println("----------------------------------");

    }

    public void writeToRaster(WritableRaster wr) {
        final int[] white = new int[]{255, 255, 255};
        final int[] black = new int[]{0, 0, 0};
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                wr.setPixel(x, y, white);
            }
        }
        for (DimXY dim : currentGeneration) {
            wr.setPixel(dim.getX(), dim.getY(), black);
        }

    }

    public int size() {
        return currentGeneration.size();
    }

    public void positionBlinker(int x, int y) {
        for (int posX = 0; posX < 3; posX++) {
            flipCell(posX + x, y + 1);
        }
    }

    public void add(Collection<DimXY> dimlist) {
        currentGeneration.addAll(dimlist);
    }

    public void addAndTransform(Collection<DimXY> dimlist,int x, int y) {
        for (DimXY dim :dimlist){
            currentGeneration.add(new DimXY(handleOverflow(dim.getX()+x,sizeX), 
                    handleOverflow(dim.getY()+y,sizeY)));
        }
        
        
    }
}
