package sim.intersect.common;

public class Defailt extends Tile {
    public Defailt() {
        super(null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, 0, 0);
    }

    public Defailt(Orientation thisor, Tile upleft, Tile shadowupleft, Tile above,
            Tile shadowabove, Tile upright, Tile shadowupright, Tile left,
            Tile shadowleft, Tile right, Tile shadowright, Tile loleft,
            Tile shadowloleft, Tile below, Tile shadowbelow, Tile loright,
            Tile shadowloright, int x, int y) {
        super(thisor, upleft, shadowupleft, above, shadowabove, upright,
                shadowupright, left, shadowleft, right, shadowright, loleft,
                shadowloleft, below, shadowbelow, loright, shadowloright, x, y);

    }

    @Override
    public void applyRotation() {
        try {
            super.applyRotation();
        } catch (Exception e) {

        }
    }

    @Override
    public void performAction() {

    }

    @Override
    public String getShortname() {
        // TODO Auto-generated method stub
        return "";
    }

    @Override
    public void OnRecieve(Tile t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void EvaluateAction() {
        // TODO Auto-generated method stub

    }

}
