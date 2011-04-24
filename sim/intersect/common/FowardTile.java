package sim.intersect.common;

public class FowardTile extends Tile {

	public FowardTile(Orientation thisor, Tile upleft, Tile shadowupleft,
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

	}

	@Override
	public void OnRecieve(Tile origin) {
		EvaluateAction();
	}

	@Override
	public String getShortname() {
		// TODO Auto-generated method stub
		return "F";
	}

	@Override
	public void performAction() {
		sendContents(shadowabove, destination);

	}

}
