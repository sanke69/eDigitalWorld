package fr.xs.DigitalWorld.sdk.osm;

import java.io.InputStream;
import java.util.ArrayList;

import fr.xs.jtk.tools.UUID;

import fr.xs.DigitalWorld.sdk.common.types.coordinates.GeoCoordinate;
import fr.xs.DigitalWorld.sdk.common.types.map.Node;
import fr.xs.DigitalWorld.sdk.common.types.map.Segment;
import fr.xs.DigitalWorld.sdk.map.LocalDynamicMap;
import fr.xs.DigitalWorld.sdk.osm.xml.OsmXmlParser;
import fr.xs.DigitalWorld.sdk.osm.xml.items.OsmItem;
import fr.xs.DigitalWorld.sdk.osm.xml.items.collection.OsmMember;
import fr.xs.DigitalWorld.sdk.osm.xml.items.collection.OsmNode;
import fr.xs.DigitalWorld.sdk.osm.xml.items.collection.OsmNodeRef;
import fr.xs.DigitalWorld.sdk.osm.xml.items.collection.OsmRelation;
import fr.xs.DigitalWorld.sdk.osm.xml.items.collection.OsmTag;
import fr.xs.DigitalWorld.sdk.osm.xml.items.collection.OsmWay;
import fr.xs.DigitalWorld.sdk.osm.xml.tags.NodeTags;
import fr.xs.DigitalWorld.sdk.osm.xml.tags.WayTags;

public class OsmLocalMap extends LocalDynamicMap {

	public OsmLocalMap() {
		super();
	}
	public OsmLocalMap(InputStream _is) {
		this();
		addData(_is);
	}
	
	public void addData(InputStream _is) {
		OsmXmlParser xml = new OsmXmlParser();
		xml.parse(_is);

    	for(OsmItem item : xml.getList()) {
    		switch(item.getClass().getSimpleName()) {
    		case "OsmBounds" : 		break;
    		case "OsmNode" : 		OsmNode inode = (OsmNode) item;
    								Node    node  = convertFromOSM(inode);

    								nodes.put(node.getId(), node);
				    				break;
    		case "OsmWay" : 		OsmWay  iway    = (OsmWay) item;
									Segment segment = convertFromOSM(iway);

    								segments.put(segment.getId(), segment);
				    				break;
    		case "OsmRelation" : 	//OsmRelation irelation = (OsmRelation) item;
//	    							convertFromOSM(irelation);

				    				break;
		    default :				System.err.println("Unknow " + item.getClass().getName() + " as OSM Class Name");
		    						break;
    		}
    	}

	}

	public Node    convertFromOSM(OsmNode _node) {
		Node node = new Node(_node.getId(), new GeoCoordinate(_node.getLongitude(), _node.getLatitude()));

		for(OsmTag tag : _node.getTags())
			NodeTags.enrichNode(tag, node);

		return node;
	}
	public Segment convertFromOSM(OsmWay _way) {
		ArrayList<GeoCoordinate> coords = new ArrayList<GeoCoordinate>();
		for(OsmNodeRef node : _way.getNodeRefs())
			coords.add(nodes.get(node.getRef()));

		Segment segment = new Segment(_way.getId(), coords);

		for(OsmTag tag : _way.getTags())
			WayTags.enrichSegment(tag, segment);

		return segment;
	}
	public void    convertFromOSM(OsmRelation _relation) {
//		UUID            id     = _relation.getId();
		ArrayList<UUID> refs = new ArrayList<UUID>();

		for(OsmMember member : _relation.getMembers())
			refs.add(member.getRef());
/*
		for(OsmTag tag : _relation.getTags())
			RelationTags.enrichDatabase(tag, null);
*/
		return ;
	}

}
