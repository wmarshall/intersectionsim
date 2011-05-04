package sim.intersect.common;

import static java.lang.Math.sqrt;
import static java.lang.System.out;

import java.util.ArrayList;

public class Intersection {

    // TODO this class should create an array of Tiles, and a buffer array for
    // copying.
    /**
     * WARNING. right now, all intersections are 6x6(really 8x8)
     * 
     * creates two arrays of tiles and alternates between them. This is probably
     * more efficient than a buffer tile system, because values are only sent
     * once per cycle, rather than twice. default is 6by 6 array.
     * 
     */
    int sidelength, realsidelength;
    Tile[][] TileArray, ShadowTiles, Active;
    ArrayList<Tile> nonNullTiles = new ArrayList<Tile>();
    ArrayList<Tile> shadowNonNullTiles = new ArrayList<Tile>();
//    ArrayList<Integer> blah=new ArrayList<Integer>(1);

    public Intersection(int x) {
        if ((int) sqrt((double) x) % 1 != 0) {
            System.exit(1);
        }
        sidelength = (int) sqrt((double) x);
        realsidelength = sidelength + 2;
        out.println(" sidelength set to " + sidelength);
        TileArray = new Tile[8][8];
        ShadowTiles = new Tile[8][8];
        Simulate();
    }

    public void Simulate() {
        // build the intersection, first fill w/ Nulltiles, then populate w/ the
        // others
        for (int countx = 0; countx <= 7; countx++) {
            for (int county = 0; county <= 7; county++) {
                TileArray[countx][county] = new NullTile(null, null, null, null,
                        null, null, null, null, null, null, null, null, null, null,
                        null, null, null, countx, county);
                ShadowTiles[countx][county] = new NullTile(null, null, null, null,
                        null, null, null, null, null, null, null, null, null, null,
                        null, null, null, countx, county);
            }
        }
        System.out.println("Arrays filled with NullTiles");

        // I've noticed that when creadted, the Tile constructor doesn't make a
        // reference to the
        // Array that is given, the Objects seem to be resolved at runtime.
        // Implementing updateNeighbors() to solve
        fillBothSpawn(TileArray, ShadowTiles, Orientation.South, 3, 1);
        fillBothSpawn(TileArray, ShadowTiles, Orientation.East, 1, 4);
        fillBothSpawn(TileArray, ShadowTiles, Orientation.West, 6, 3);
        fillBothSpawn(TileArray, ShadowTiles, Orientation.North, 4, 6);

        fillBothSend(TileArray, ShadowTiles, Orientation.South, 3, 2);
        fillBothSend(TileArray, ShadowTiles, Orientation.East, 2, 4);
        fillBothSend(TileArray, ShadowTiles, Orientation.West, 5, 3);
        fillBothSend(TileArray, ShadowTiles, Orientation.North, 4, 5);

        fillBothTrans(TileArray, ShadowTiles, Orientation.South, 3, 3);
        fillBothTrans(TileArray, ShadowTiles, Orientation.East, 3, 4);
        fillBothTrans(TileArray, ShadowTiles, Orientation.West, 4, 3);
        fillBothTrans(TileArray, ShadowTiles, Orientation.North, 4, 4);

        fillBothFow(TileArray, ShadowTiles, Orientation.South, 3, 5);
        fillBothFow(TileArray, ShadowTiles, Orientation.East, 2, 3);
        fillBothFow(TileArray, ShadowTiles, Orientation.West, 4, 2);
        fillBothFow(TileArray, ShadowTiles, Orientation.North, 5, 4);

        fillBothRem(TileArray, ShadowTiles, Orientation.South, 3, 6);
        fillBothRem(TileArray, ShadowTiles, Orientation.East, 6, 4);
        fillBothRem(TileArray, ShadowTiles, Orientation.West, 1, 3);
        fillBothRem(TileArray, ShadowTiles, Orientation.North, 4, 1);

        // for (int xcount = 0; xcount <= 7; xcount++) {
        // for (int ycount = 0; ycount <= 7; ycount++) {
        // TileArray[xcount][ycount].updateNieghbors(TileArray, ShadowTiles);
        // ShadowTiles[xcount][ycount].updateNieghbors(ShadowTiles, TileArray);
        //
        // }
        // }

        System.out.println("NullTile References cleared.");
        Active = TileArray;
        getIntersection();
        Active = ShadowTiles;
        getIntersection();
        out.println("Initial states");
        Active = TileArray;
        for (int ycount = 0; ycount <= 7; ycount++) {
            for (int xcount = 0; xcount <= 7; xcount++) {
                Active[xcount][ycount].performAction();
            }
        }
        getIntersection();
    }

    public String getRow(int y, Tile[][] t) {
        String assembled = "";
        for (int count = 0; count <= 7; count++) {
            assembled = assembled + t[count][y].getValuesname() + " ";
        }
        return assembled;
    }

    public void getIntersection() {
        for (int count = 0; count <= 7; count++) {
            out.println(getRow(count, Active));

        }
        out.println();
    }

    /*
     * public void Simulate(){ TileArray = new Tile[sidelength]; for (int count
     * = 0; count <= sidelength - 1; count++){ TileArray[count] = new Tile();
     * TileArray[count].flipIsanarray(); TileArray[count].SetArrayNum(count);
     * TileArray[count].SetArrayL(sidelength);
     * TileArray[count].setTypeofTile(TypeofTile.Transit);
     * 
     * }
     */
    public void fillBothSend(Tile[][] t, Tile[][] s, Orientation or, int x, int y) {
        t[x][y] = new SenderTile(or, t[x - 1][y - 1], s[x - 1][y - 1], t[x][y - 1],
                s[x][y - 1], t[x + 1][y - 1], s[x + 1][y - 1], t[x - 1][y],
                s[x - 1][y], t[x + 1][y], s[x + 1][y], t[x - 1][y + 1],
                s[x - 1][y + 1], t[x][y + 1], s[x][y + 1], t[x + 1][y + 1],
                s[x + 1][y + 1], x, y);
        s[x][y] = new SenderTile(or, t[x - 1][y - 1], s[x - 1][y - 1], t[x][y - 1],
                s[x][y - 1], t[x + 1][y - 1], s[x + 1][y - 1], t[x - 1][y],
                s[x - 1][y], t[x + 1][y], s[x + 1][y], t[x - 1][y + 1],
                s[x - 1][y + 1], t[x][y + 1], s[x][y + 1], t[x + 1][y + 1],
                s[x + 1][y + 1], x, y);
        shadowNonNullTiles.add(s[x][y]);
        nonNullTiles.add(t[x][y]);
    }

    public void fillBothSpawn(Tile[][] t, Tile[][] s, Orientation or, int x, int y) {
        t[x][y] = new SpawnerTile(or, t[x - 1][y - 1], s[x - 1][y - 1], t[x][y - 1],
                s[x][y - 1], t[x + 1][y - 1], s[x + 1][y - 1], t[x - 1][y],
                s[x - 1][y], t[x + 1][y], s[x + 1][y], t[x - 1][y + 1],
                s[x - 1][y + 1], t[x][y + 1], s[x][y + 1], t[x + 1][y + 1],
                s[x + 1][y + 1], x, y);
        s[x][y] = new SpawnerTile(or, t[x - 1][y - 1], s[x - 1][y - 1], t[x][y - 1],
                s[x][y - 1], t[x + 1][y - 1], s[x + 1][y - 1], t[x - 1][y],
                s[x - 1][y], t[x + 1][y], s[x + 1][y], t[x - 1][y + 1],
                s[x - 1][y + 1], t[x][y + 1], s[x][y + 1], t[x + 1][y + 1],
                s[x + 1][y + 1], x, y);
        shadowNonNullTiles.add(s[x][y]);
        nonNullTiles.add(t[x][y]);

    }

    public void fillBothTrans(Tile[][] t, Tile[][] s, Orientation or, int x, int y) {
        t[x][y] = new TransitTile(or, t[x - 1][y - 1], s[x - 1][y - 1], t[x][y - 1],
                s[x][y - 1], t[x + 1][y - 1], s[x + 1][y - 1], t[x - 1][y],
                s[x - 1][y], t[x + 1][y], s[x + 1][y], t[x - 1][y + 1],
                s[x - 1][y + 1], t[x][y + 1], s[x][y + 1], t[x + 1][y + 1],
                s[x + 1][y + 1], x, y);
        s[x][y] = new TransitTile(or, t[x - 1][y - 1], s[x - 1][y - 1], t[x][y - 1],
                s[x][y - 1], t[x + 1][y - 1], s[x + 1][y - 1], t[x - 1][y],
                s[x - 1][y], t[x + 1][y], s[x + 1][y], t[x - 1][y + 1],
                s[x - 1][y + 1], t[x][y + 1], s[x][y + 1], t[x + 1][y + 1],
                s[x + 1][y + 1], x, y);
        shadowNonNullTiles.add(s[x][y]);
        nonNullTiles.add(t[x][y]);

    }

    public void fillBothFow(Tile[][] t, Tile[][] s, Orientation or, int x, int y) {
        t[x][y] = new FowardTile(or, t[x - 1][y - 1], s[x - 1][y - 1], t[x][y - 1],
                s[x][y - 1], t[x + 1][y - 1], s[x + 1][y - 1], t[x - 1][y],
                s[x - 1][y], t[x + 1][y], s[x + 1][y], t[x - 1][y + 1],
                s[x - 1][y + 1], t[x][y + 1], s[x][y + 1], t[x + 1][y + 1],
                s[x + 1][y + 1], x, y);
        s[x][y] = new FowardTile(or, t[x - 1][y - 1], s[x - 1][y - 1], t[x][y - 1],
                s[x][y - 1], t[x + 1][y - 1], s[x + 1][y - 1], t[x - 1][y],
                s[x - 1][y], t[x + 1][y], s[x + 1][y], t[x - 1][y + 1],
                s[x - 1][y + 1], t[x][y + 1], s[x][y + 1], t[x + 1][y + 1],
                s[x + 1][y + 1], x, y);
        shadowNonNullTiles.add(s[x][y]);
        nonNullTiles.add(t[x][y]);

    }

    public void fillBothRem(Tile[][] t, Tile[][] s, Orientation or, int x, int y) {
        t[x][y] = new RemoverTile(or, t[x - 1][y - 1], s[x - 1][y - 1], t[x][y - 1],
                s[x][y - 1], t[x + 1][y - 1], s[x + 1][y - 1], t[x - 1][y],
                s[x - 1][y], t[x + 1][y], s[x + 1][y], t[x - 1][y + 1],
                s[x - 1][y + 1], t[x][y + 1], s[x][y + 1], t[x + 1][y + 1],
                s[x + 1][y + 1], x, y);
        s[x][y] = new RemoverTile(or, t[x - 1][y - 1], s[x - 1][y - 1], t[x][y - 1],
                s[x][y - 1], t[x + 1][y - 1], s[x + 1][y - 1], t[x - 1][y],
                s[x - 1][y], t[x + 1][y], s[x + 1][y], t[x - 1][y + 1],
                s[x - 1][y + 1], t[x][y + 1], s[x][y + 1], t[x + 1][y + 1],
                s[x + 1][y + 1], x, y);
        shadowNonNullTiles.add(s[x][y]);
        nonNullTiles.add(t[x][y]);

    }
    public void fillBoth<Tile t>{
        
    }
}
