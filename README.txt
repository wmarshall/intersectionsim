SimIntersect
******************
This is my fail of an intersection simulator. It's in Java, sorry, it started
that way, it's going to end(eventually) that way. Right now, it can start and
sucessfully build an American 4-way intersection,count the number of Tiles
constructed(debug features ftw), display it, and then start failing. I built
a rotation althogoritm that attempts to shuffle where Tiles think their
neighbors are, but for some unfathomable reason(e.g. I suck), It fails
miserably.

Methodology (Why Poor Decisions Were Made)
******************
An intersection is rendered as a 2-dimensional array of Tiles. These Tiles
are aware of their surrounding tiles, and can get information from them.
When created, each Tile rotates its neighbor data to make the neighbor that
it faces fill the 'above'(North) slot. Therefore, I could code logic for
moving vehicles North, and be pretty much done with the movement logic. Each
Tile sends its contents to one of it's neighbors as an enum, vehicles aren't
actually Objects, just chunks of data. Therefore, all logic is done at the
Tile level. Each Tile that forms a part of the intersection has some unique
property, it may be unusable(NullTile), at the end of a lane leading out
(RemoverTile) feeding the end of a lane(FowardTile), shuffling vehicles in
the intersection(TransitTile), feeding TransitTiles(SenderTile), or spawning
vehicles at the start of a lane(SpawnerTile).

Compiling
******************
1. Have some form of a Java Development Kit installed(OpenJDK should work)
2. From the src directory:
javac sim/intersect/cli/Testintersection.java

Running
******************
From the src directory:
java sim.intersect.cli.Testintersection

