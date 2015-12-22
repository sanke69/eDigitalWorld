package fr.xs.DigitalWorld.sdk.osm;

import fr.xs.DigitalWorld.sdk.common.types.map.Node;
import fr.xs.DigitalWorld.sdk.common.types.map.Segment;
import fr.xs.jtk.helpers.MediaHelper;

public class TestOsmLocalMap {

	public static void main(String[] args) {
		OsmLocalMap osmLdm = new OsmLocalMap( MediaHelper.getContent(TestOsmLocalMap.class, "/samples/esbly.osm") );

		for(Node node : osmLdm.getNodes().values())
			System.out.println(node);

		for(Segment segment : osmLdm.getSegments().values())
			System.out.println(segment);

	    System.out.println("Done, nb Nodes=" + osmLdm.getNodes().size() + ", nb Segments=" + osmLdm.getSegments().size());
	}

}
