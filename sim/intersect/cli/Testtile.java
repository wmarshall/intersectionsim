package sim.intersect.cli;

import sim.intersect.common.Defailt;
import sim.intersect.common.Orientation;
import sim.intersect.common.Tile;

public class Testtile {

	/**
	 * This thing can seriously go die in a hole. seriously. Anything in here is
	 * random testing crap
	 */
	public static void main(String[] args) {

		Tile[] array = new Defailt[16];
		for (int count = 0; count <= 15; count++) {
			array[count] = new Defailt();
		}
		Tile north = new Defailt(Orientation.North, array[0], array[1],
				array[2], array[3], array[4], array[5], array[6], array[7],
				array[8], array[9], array[10], array[11], array[12], array[13],
				array[14], array[15], 0, 0);
		Tile east = new Defailt(Orientation.East, array[0], array[1], array[2],
				array[3], array[4], array[5], array[6], array[7], array[8],
				array[9], array[10], array[11], array[12], array[13],
				array[14], array[15], 0, 0);
		Tile west = new Defailt(Orientation.West, array[0], array[1], array[2],
				array[3], array[4], array[5], array[6], array[7], array[8],
				array[9], array[10], array[11], array[12], array[13],
				array[14], array[15], 0, 0);
		Tile south = new Defailt(Orientation.South, array[0], array[1],
				array[2], array[3], array[4], array[5], array[6], array[7],
				array[8], array[9], array[10], array[11], array[12], array[13],
				array[14], array[15], 0, 0);

		System.out.println(north.getValue());

		north.performAction();
		System.out.println(north.getValue());

		north.performAction();
		System.out.println("Testtileworks!" + north.toString());
		// double b = (Double) null;
		boolean[] d = new boolean[5];
		// System.out.println(b);
		System.out.println(d[1]);
	}

}
