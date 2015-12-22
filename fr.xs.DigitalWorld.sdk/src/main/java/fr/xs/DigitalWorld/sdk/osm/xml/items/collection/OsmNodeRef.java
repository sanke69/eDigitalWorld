package fr.xs.DigitalWorld.sdk.osm.xml.items.collection;

import fr.xs.jtk.tools.UUID;

import fr.xs.DigitalWorld.sdk.osm.xml.items.OsmItem;

public class OsmNodeRef implements OsmItem {

	private UUID ref;

	public OsmNodeRef() { 
		ref = UUID.UNDEF; 
	}
	public OsmNodeRef(UUID _ref) { 
		ref = _ref; 
	}
	public OsmNodeRef(OsmNodeRef _node) { 
		ref = _node.ref; 
	}
	
	public UUID getRef() { return ref; }
	
	public void setRef(UUID _ref) { ref = _ref; }

	public String toString(){
		return new String("[NodeRef] ")
				+ "(ref= " + ref
				+ ")";
	}

}
