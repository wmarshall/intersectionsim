package sim.intersect.common;

import java.util.Random;

public class SenderTile extends Tile {

	SendOpts option = SendOpts.Null;

	public SenderTile(Orientation thisor, Tile upleft, Tile shadowupleft,
			Tile above, Tile shadowabove, Tile upright, Tile shadowupright,
			Tile left, Tile shadowleft, Tile right, Tile shadowright,
			Tile loleft, Tile shadowloleft, Tile below, Tile shadowbelow,
			Tile loright, Tile shadowloright, int x, int y) {
		super(thisor, upleft, shadowupleft, above, shadowabove, upright,
				shadowupright, left, shadowleft, right, shadowright, loleft,
				shadowloleft, below, shadowbelow, loright, shadowloright, x, y);

		// TODO Auto-generated constructor stub
	}

	@Override
	public String getShortname() {
		// TODO Auto-generated method stub
		return "Y";
	}

	@Override
	public void performAction() {
		if (approved) {
			switch (option) {
			case Right:
				sendContents(shadowupright, destination);
				break;
			default:
				sendContents(shadowabove, destination);
				break;
			}
		}
	}

	@Override
	public void OnRecieve(Tile t) {
		EvaluateAction();

	}

	@Override
	public void EvaluateAction() {
		// 0=turn right 1=turn left 2=straight
		if (option == SendOpts.Null) {
			int action = new Random().nextInt(3);
			switch (action) {
			case 0:
				option = SendOpts.Right;
				break;
			case 1:
				option = SendOpts.Left;
				break;
			case 2:
				option = SendOpts.Straight;
				break;
			}
			switch (option) {
			case Right:
				if (above.destination != upright.thisor) {
					approved = true;
				}
				break;
			default:
				if (upleft.destination != upright.thisor
						&& above.upleft.destination != upright.thisor) {
					approved = true;
				}
				break;

			}
		}
	}

}
