package sim.intersect.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import sim.intersect.common.Defailt;
import sim.intersect.common.Orientation;
import sim.intersect.common.Tile;

public class RotationTest {

    @Test
    public void testNorth() {
        Tile[] array = new Defailt[16];
        for (int count = 0; count <= 15; count++) {
            array[count] = new Defailt();
        }
        Tile[] compared = { array[0], array[2], array[4], array[6], array[8],
                array[10], array[12], array[14] };

        Tile north = new Defailt(Orientation.North, array[0], array[1], array[2],
                array[3], array[4], array[5], array[6], array[7], array[8],
                array[9], array[10], array[11], array[12], array[13], array[14],
                array[15], 0, 0);
        Tile[] nieghbors = north.getNieghbors();
        boolean match = true;
        for (int i = 0; i <= 7; i++) {
            if (!compared[i].equals(nieghbors[i])) {
                match = false;
            }
        }
        assertTrue(match);
    }

    @Test
    public void testEast() {
        Tile[] array = new Defailt[16];
        for (int count = 0; count <= 15; count++) {
            array[count] = new Defailt();
        }
        Tile[] compared = { array[4], array[8], array[14], array[2], array[12],
                array[0], array[6], array[10] };

        Tile east = new Defailt(Orientation.East, array[0], array[1], array[2],
                array[3], array[4], array[5], array[6], array[7], array[8],
                array[9], array[10], array[11], array[12], array[13], array[14],
                array[15], 0, 0);
        Tile[] neighbors = east.getNieghbors();
        boolean match = true;
        for (int i = 0; i <= 7; i++) {
            if (!compared[i].equals(neighbors[i])) {
                match = false;
                System.out.println("failed on Tile" + (i + 1));
            }
        }
        assertTrue(match);
    }

    @Test
    public void testSouth() {
        Tile[] array = new Defailt[16];
        for (int count = 0; count <= 15; count++) {
            array[count] = new Defailt();
        }
        Tile[] compared = { array[14], array[12], array[10], array[8], array[6],
                array[4], array[2], array[0] };

        Tile south = new Defailt(Orientation.South, array[0], array[1], array[2],
                array[3], array[4], array[5], array[6], array[7], array[8],
                array[9], array[10], array[11], array[12], array[13], array[14],
                array[15], 0, 0);
        Tile[] neighbors = south.getNieghbors();
        boolean match = true;
        for (int i = 0; i <= 7; i++) {
            if (!compared[i].equals(neighbors[i])) {
                match = false;
                System.out.println("failed on Tile" + (i + 1));
            }
        }
        assertTrue(match);
    }

    @Test
    public void testWest() {
        Tile[] array = new Defailt[16];
        for (int count = 0; count <= 15; count++) {
            array[count] = new Defailt();
        }
        Tile[] compared = { array[10], array[6], array[0], array[12], array[2],
                array[14], array[8], array[4] };

        Tile west = new Defailt(Orientation.West, array[0], array[1], array[2],
                array[3], array[4], array[5], array[6], array[7], array[8],
                array[9], array[10], array[11], array[12], array[13], array[14],
                array[15], 0, 0);
        Tile[] neighbors = west.getNieghbors();
        boolean match = true;
        for (int i = 0; i <= 7; i++) {
            if (!compared[i].equals(neighbors[i])) {
                match = false;
                System.out.println("failed on Tile" + (i + 1));
            }
        }
        assertTrue(match);
    }
}
