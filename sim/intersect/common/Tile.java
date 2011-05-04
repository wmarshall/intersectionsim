/**
 * TODO IF this works right, I should have an infinitely configurable Tile.
 */
package sim.intersect.common;

public abstract class Tile {
    int xLoc, yLoc;

    public Tile(Orientation thisor, Tile upleft, Tile shadowupleft, Tile above,
            Tile shadowabove, Tile upright, Tile shadowupright, Tile left,
            Tile shadowleft, Tile right, Tile shadowright, Tile loleft,
            Tile shadowloleft, Tile below, Tile shadowbelow, Tile loright,
            Tile shadowloright, int x, int y) {

        this.thisOr = thisor;
        this.right = right;
        this.left = left;
        this.below = below;
        this.above = above;
        this.upRight = upright;
        this.upLeft = upleft;
        this.lowRight = loright;
        this.lowLeft = loleft;
        this.shadowRight = shadowright;
        this.shadowLeft = shadowleft;
        this.shadowAbove = shadowabove;
        this.shadowBelow = shadowbelow;
        this.shadowUpRight = shadowupright;
        this.shadowUpLeft = shadowupleft;
        this.shadowLowRight = shadowloright;
        this.shadowLowLeft = shadowloleft;
        this.xLoc = x;
        this.yLoc = y;
        applyRotation();
    }

    /**
     * Some of the methods assume that this tile is part of an array with a
     * perfect square sidelength. It's weird. Intersection probably is your best
     * bet for using this. It's a lot less confusing, and can be abused less.
     */
    Orientation thisOr = Orientation.North;

    public Orientation getThisor() {
        return thisOr;
    }

    Tile right, left, below, above, upRight, upLeft, lowRight, lowLeft, shadowRight,
            shadowLeft, shadowAbove, shadowBelow, shadowUpRight, shadowUpLeft,
            shadowLowRight, shadowLowLeft;
    Value contents = Value.Empty;
    Orientation destination;
    boolean approved = false;

    public void setDestination(Orientation destination) {
        this.destination = destination;
    }

    protected abstract String getShortname();

    public String getValue() {
        return contents.toString();
    }

    public void setValueofTile(Value v) {
        this.contents = v;
    }

    public String getValuesname() {
        String sname = "Switch did not run.";
        switch (contents) {
        case Empty:
            sname = getShortname();
            break;
        case Carval1:
            sname = "1";
            break;
        case PubTrans:
            sname = "p";
            break;
        case Carval2:
            sname = "2";
            break;
        case Emergencyveh:
            sname = "e";
            break;
        case Commercial:
            sname = "c";
            break;
        case GovtVeh:
            sname = "g";
            break;
        }
        return sname;
    }

    static int times = 1;

    public void applyRotation() {
        // System.out.println(times++);
        Tile bufferTile;

        switch (thisOr) {
        case North:
            break;
        case East:
            bufferTile = lowLeft;
            lowLeft = upLeft;
            upLeft = upRight;
            upRight = lowRight;
            lowRight = bufferTile;
            bufferTile = below;
            below = left;
            left = above;
            above = right;
            right = bufferTile;

            bufferTile = shadowLowLeft;
            shadowLowLeft = shadowUpLeft;
            shadowUpLeft = shadowUpRight;
            shadowUpRight = shadowLowRight;
            shadowLowRight = bufferTile;
            bufferTile = shadowBelow;
            shadowBelow = shadowLeft;
            shadowLeft = shadowAbove;
            shadowAbove = shadowRight;
            shadowRight = bufferTile;
            break;
        case South:
            bufferTile = lowRight;
            lowRight = upLeft;
            upLeft = bufferTile;
            bufferTile = upRight;
            upRight = lowLeft;
            lowLeft = bufferTile;
            bufferTile = above;
            above = below;
            below = bufferTile;
            bufferTile = right;
            right = left;
            left = bufferTile;

            bufferTile = shadowLowRight;
            shadowLowRight = shadowUpLeft;
            shadowUpLeft = bufferTile;
            bufferTile = shadowUpRight;
            shadowUpRight = shadowLowLeft;
            shadowLowLeft = bufferTile;
            bufferTile = shadowAbove;
            shadowAbove = shadowBelow;
            shadowBelow = bufferTile;
            bufferTile = shadowRight;
            shadowRight = shadowLeft;
            shadowLeft = bufferTile;
            break;
        case West:
            bufferTile = above;
            above = left;
            left = below;
            below = right;
            right = bufferTile;
            bufferTile = lowRight;
            lowRight = upRight;
            upRight = upLeft;
            upLeft = lowLeft;
            lowLeft = bufferTile;

            bufferTile = shadowAbove;
            shadowAbove = shadowLeft;
            shadowLeft = shadowBelow;
            shadowBelow = shadowRight;
            shadowRight = bufferTile;
            bufferTile = shadowLowRight;
            shadowLowRight = shadowUpRight;
            shadowUpRight = shadowUpLeft;
            shadowUpLeft = shadowLowLeft;
            shadowLowLeft = bufferTile;
            break;
        }
    }

    public abstract void EvaluateAction();

    public abstract void performAction();

    public abstract void OnRecieve(Tile origin);

    public void sendContents(Tile t, Orientation dest) {
        if (t.canRecieve() == true) {
            t.setValueofTile(contents);
            t.setDestination(dest);
            contents = Value.Empty;
            t.OnRecieve(this);
        }
    }

    boolean willBeHere = true;

    public boolean canRecieve() {
        if (contents == Value.Empty || willBeHere == false) {
            return true;
        }
        return false;

    }

    public Tile[] getNieghbors() {
        Tile[] nieghbors = { upLeft, above, upRight, left, right, lowLeft, below,
                lowRight };
        return nieghbors;
    }

    /**
     * Updates nieghbors to clear residual null values caused by iterative
     * Intersection construction
     * 
     * @param active
     *            array this Tile is in
     * @param shadow
     *            Shadow Array
     */
    public void updateNieghbors(Tile[][] active, Tile[][] shadow) {
        if (!(this.getClass() == NullTile.class)) {
            upLeft = active[xLoc - 1][yLoc - 1];
            above = active[xLoc][yLoc - 1];
            upRight = active[xLoc + 1][yLoc - 1];
            left = active[xLoc - 1][yLoc];
            right = active[xLoc + 1][yLoc];
            lowLeft = active[xLoc - 1][yLoc + 1];
            below = active[xLoc][yLoc + 1];
            lowRight = active[xLoc + 1][yLoc + 1];

            shadowUpLeft = shadow[xLoc - 1][yLoc - 1];
            shadowAbove = shadow[xLoc][yLoc - 1];
            shadowUpRight = shadow[xLoc + 1][yLoc - 1];
            shadowLeft = shadow[xLoc - 1][yLoc];
            shadowRight = shadow[xLoc + 1][yLoc];
            shadowLowLeft = shadow[xLoc - 1][yLoc + 1];
            shadowBelow = shadow[xLoc][yLoc + 1];
            shadowLowRight = shadow[xLoc + 1][yLoc + 1];
            applyRotation();
        }
    }
}