package sim.intersect.common;

public class RemoverTile extends Tile {

	public RemoverTile(Orientation thisor, Tile upleft, Tile shadowupleft,
			Tile above, Tile shadowabove, Tile upright, Tile shadowupright,
			Tile left, Tile shadowleft, Tile right, Tile shadowright,
			Tile loleft, Tile shadowloleft, Tile below, Tile shadowbelow,
			Tile loright, Tile shadowloright, int x, int y) {
		super(thisor, upleft, shadowupleft, above, shadowabove, upright,
				shadowupright, left, shadowleft, right, shadowright, loleft,
				shadowloleft, below, shadowbelow, loright, shadowloright, x, y);

	}

	@Override
	public void performAction() {
		setValueofTile(Value.Empty);

	}

	@Override
	public String getShortname() {

		return "R";
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
