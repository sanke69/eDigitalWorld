package fr.xs.DigitalWorld.sdk.map;

import java.util.HashMap;

import fr.xs.DigitalWorld.sdk.common.types.map.Area;
import fr.xs.DigitalWorld.sdk.common.types.map.Node;
import fr.xs.DigitalWorld.sdk.common.types.map.Segment;
import fr.xs.jtk.tools.UUID;

public class LocalDynamicMap {

	protected HashMap<UUID, Node>		nodes;
	protected HashMap<UUID, Segment> 	segments;
	protected HashMap<UUID, Area> 		areas;

	public LocalDynamicMap() {
		nodes    = new HashMap<UUID, Node>();
		segments = new HashMap<UUID, Segment>();
		areas    = new HashMap<UUID, Area>(); 
	}

	public HashMap<UUID, Node> getNodes() {
		return nodes;
	}
	public HashMap<UUID, Segment> getSegments() {
		return segments;
	}
	public HashMap<UUID, Area> getAreas() {
		return areas;
	}

	public Node getNode(UUID _id) {
		return nodes.get(_id);
	}
	public Segment getSegment(UUID _id) {
		return segments.get(_id);
	}
	public Area getArea(UUID _id) {
		return areas.get(_id);
	}

	public void putNode(UUID _id, Node _node) {
		nodes.put(_id, _node);
	}
	public void putSegment(UUID _id, Segment _segment) {
		segments.put(_id, _segment);
	}
	public void putArea(UUID _id, Area _area) {
		areas.put(_id, _area);
	}

}
