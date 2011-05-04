package sim.intersect.common;

public class SpawnerTile extends Tile {

	public SpawnerTile(Orientation thisor, Tile upleft, Tile shadowupleft,
			Tile above, Tile shadowabove, Tile upright, Tile shadowupright,
			Tile left, Tile shadowleft, Tile right, Tile shadowright,
			Tile loleft, Tile shadowloleft, Tile below, Tile shadowbelow,
			Tile loright, Tile shadowloright, int x, int y) {
		super(thisor, upleft, shadowupleft, above, shadowabove, upright,
				shadowupright, left, shadowleft, right, shadowright, loleft,
				shadowloleft, below, shadowbelow, loright, shadowloright, x, y);

	}

	@Override
	public String getShortname() {

		return "S";
	}

	@Override
	public void performAction() {
		if (this.contents == Value.Empty) {
			Spawner s = new Spawner();
			this.contents = s.setval();
			OnRecieve(this);
		} else {
			if (approved == true) {
				sendContents(shadowAbove, destination);
			}
		}
	}

	@Override
	public void OnRecieve(Tile t) {

		destination = Orientation.Herp;
		EvaluateAction();

	}

	@Override
	public void EvaluateAction() {
		if (above.contents == Value.Empty) {
			this.approved = true;
		} else {
			this.approved = false;
		}

	}

}
