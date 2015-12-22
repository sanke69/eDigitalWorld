package fr.xs.DigitalWorld.sdk.osm.xml;

import fr.xs.DigitalWorld.sdk.osm.xml.items.OsmItem;
import fr.xs.DigitalWorld.sdk.osm.xml.items.collection.OsmNode;
import fr.xs.DigitalWorld.sdk.osm.xml.items.collection.OsmRelation;
import fr.xs.DigitalWorld.sdk.osm.xml.items.collection.OsmWay;
import fr.xs.jtk.helpers.MediaHelper;

public class TestOsmXmlParser {

	public static void main(String args[]) {
		OsmXmlParser xml = new OsmXmlParser();
		xml.parse(MediaHelper.getContent(TestOsmXmlParser.class, "/samples/esbly.osm"));

	    if(xml.getList() != null) {
	    	for(OsmItem item : xml.getList()) {
	    		System.out.println(item);
	    		
	    		switch(item.getClass().getSimpleName()) {
	    		case "OsmNode" : 		for(OsmItem tag : ((OsmNode) item).getTags())
	    									System.out.println("\t" + tag);
		    							break;
	    		case "OsmWay" : 		for(OsmItem nd : ((OsmWay) item).getNodeRefs())
						    				System.out.println("\t" + nd);
						    			for(OsmItem tag : ((OsmWay) item).getTags())
						    				System.out.println("\t" + tag);
										break;
	    		case "OsmRelation" : 	for(OsmItem member : ((OsmRelation) item).getMembers())
						    				System.out.println("\t" + member);
						    			for(OsmItem tag : ((OsmRelation) item).getTags())
						    				System.out.println("\t" + tag);
										break;
				default :				System.err.println("Unknown item: " + item.getClass().getSimpleName());
	    		}
	    	}
	    }
	}
 
}
