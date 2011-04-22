package sim.intersect.cli;

public class TransitTile extends Tile {
	Tile tiledest;

	public TransitTile(Orientation thisor, Tile upleft, Tile shadowupleft,
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
		if (destination == this.thisor) {
			approved = true;
			tiledest = shadowabove;
		} else if (destination == right.thisor) {
			approved = true;
			tiledest = shadowright;
		} else if ((destination == above.thisor && above.destination != this.thisor)) {
			approved = true;
			tiledest = shadowupleft;
		}

	}

	@Override
	public void OnRecieve(Tile t) {
		EvaluateAction();

	}

	@Override
	public String getShortname() {
		// TODO Auto-generated method stub
		return "T";
	}

	@Override
	public void performAction() {
		if (approved)
			sendContents(tiledest, destination);
	}

}
