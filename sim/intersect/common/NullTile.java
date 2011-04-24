package sim.intersect.common;

/**
 * This is a dummy tile, it's only functionality is as an Exception shield.
 * 
 * @author gridbug
 * 
 */
public class NullTile extends Tile {

	public NullTile(Orientation thisor, Tile upleft, Tile shadowupleft,
			Tile above, Tile shadowabove, Tile upright, Tile shadowupright,
			Tile left, Tile shadowleft, Tile right, Tile shadowright,
			Tile loleft, Tile shadowloleft, Tile below, Tile shadowbelow,
			Tile loright, Tile shadowloright, int x, int y) {
		super(thisor, upleft, shadowupleft, above, shadowabove, upright,
				shadowupright, left, shadowleft, right, shadowright, loleft,
				shadowloleft, below, shadowbelow, loright, shadowloright, x, y);
	}

	@Override
	public void EvaluateAction() {
		System.out.println("NONONO! NullTiles cannot accept any data. ");

	}

	@Override
	public String getShortname() {

		return "*";
	}

	@Override
	public void performAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void applyRotation() {
		try {
			super.applyRotation();

		} catch (Exception e) {

		} finally {

		}

	}

	@Override
	public void OnRecieve(Tile origin) {
		System.out.println("NONONO! NullTiles(" + x + "," + y
				+ " cannot accept any data. Especially from " + (origin.x)
				+ "," + (origin.y));

	}
}
