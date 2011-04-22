/**
 * TODO IF this works right, I should have an infinitely configurable Tile.
 */
package sim.intersect.cli;

public abstract class Tile {
	int x, y;

	public Tile(Orientation thisor, Tile upleft, Tile shadowupleft, Tile above,
			Tile shadowabove, Tile upright, Tile shadowupright, Tile left,
			Tile shadowleft, Tile right, Tile shadowright, Tile loleft,
			Tile shadowloleft, Tile below, Tile shadowbelow, Tile loright,
			Tile shadowloright, int x, int y) {

		this.thisor = thisor;
		this.right = right;
		this.left = left;
		this.below = below;
		this.above = above;
		this.upright = upright;
		this.upleft = upleft;
		this.loright = loright;
		this.loleft = loleft;
		this.shadowright = shadowright;
		this.shadowleft = shadowleft;
		this.shadowabove = shadowabove;
		this.shadowbelow = shadowbelow;
		this.shadowupright = shadowupright;
		this.shadowupleft = shadowupleft;
		this.shadowloright = shadowloright;
		this.shadowloleft = shadowloleft;
		this.x = x;
		this.y = y;
		applyRotation();
	}

	/**
	 * Some of the methods assume that this tile is part of an array with a
	 * perfect square sidelength. It's weird. Intersection probably is your best
	 * bet for using this. It's a lot less confusing, and can be abused less.
	 */
	Orientation thisor = Orientation.North;

	public Orientation getThisor() {
		return thisor;
	}

	Tile right, left, below, above, upright, upleft, loright, loleft,
			shadowright, shadowleft, shadowabove, shadowbelow, shadowupright,
			shadowupleft, shadowloright, shadowloleft;
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
		System.out.println(times++);
		Tile bufferTile;

		switch (thisor) {
		case North:
			break;
		case East:
			bufferTile = loright;
			loright = upright;
			upright = upleft;
			upleft = loleft;
			loleft = bufferTile;
			bufferTile = right;
			right = above;
			above = left;
			left = below;
			below = bufferTile;

			bufferTile = shadowloright;
			shadowloright = shadowupright;
			shadowupright = shadowupleft;
			shadowupleft = shadowloleft;
			shadowloleft = bufferTile;
			bufferTile = shadowright;
			shadowright = shadowabove;
			shadowabove = shadowleft;
			shadowleft = shadowbelow;
			shadowbelow = bufferTile;
			break;
		case South:
			bufferTile = loright;
			loright = upleft;
			upleft = bufferTile;
			bufferTile = upright;
			upright = loleft;
			loleft = bufferTile;
			bufferTile = above;
			above = below;
			below = bufferTile;
			bufferTile = right;
			right = left;
			left = bufferTile;

			bufferTile = shadowloright;
			shadowloright = shadowupleft;
			shadowupleft = bufferTile;
			bufferTile = shadowupright;
			shadowupright = shadowloleft;
			shadowloleft = bufferTile;
			bufferTile = shadowabove;
			shadowabove = shadowbelow;
			shadowbelow = bufferTile;
			bufferTile = shadowright;
			shadowright = shadowleft;
			shadowleft = bufferTile;
			break;
		case West:
			bufferTile = shadowloleft;
			shadowloleft = shadowupleft;
			shadowupleft = shadowupright;
			shadowupright = shadowloright;
			shadowloright = bufferTile;
			bufferTile = shadowleft;
			shadowleft = shadowabove;
			shadowabove = shadowright;
			shadowright = shadowbelow;
			shadowbelow = bufferTile;

			bufferTile = loleft;
			loleft = upleft;
			upleft = upright;
			upright = loright;
			loright = bufferTile;
			bufferTile = left;
			left = above;
			above = right;
			right = below;
			below = bufferTile;
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

}