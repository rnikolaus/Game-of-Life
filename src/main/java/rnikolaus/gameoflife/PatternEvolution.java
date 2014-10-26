package rnikolaus.gameoflife;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author RNIKO
 */
public class PatternEvolution {

    static Random random = new Random();

    public static void main(String[] args) {
        
        for (int x=0;x<100;x++){
        GameOfLife g = new GameOfLife(10, 10);
        Collection<DimXY> stuff = randomDim(4, 4);
        g.add(stuff);
        int i;
        for (i=0;i<10000;i++){
            if (g.isEmpty())break;
            g.nextGeneration();
        }
//        System.out.println(i);
        if (!g.isEmpty()){
            g=new GameOfLife(5,5);
            g.add(stuff);
            g.print();
        }
        
        }
        

    }

    public static Collection<DimXY> randomDim(int sizeX, int sizeY) {
        Set<DimXY> result = new HashSet<>();
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                if (random.nextBoolean()) {
                    result.add(new DimXY(x, y));
                }
            }
        }
        return result;
    }

}
