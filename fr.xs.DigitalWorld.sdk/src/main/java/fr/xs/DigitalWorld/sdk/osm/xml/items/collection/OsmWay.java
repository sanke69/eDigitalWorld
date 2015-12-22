 package fr.xs.DigitalWorld.sdk.osm.xml.items.collection;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import fr.xs.jtk.tools.UUID;

import fr.xs.DigitalWorld.sdk.osm.xml.items.OsmItem;

public class OsmWay implements OsmItem {

	private UUID 				id;
	private Instant				timestamp;
	private List<OsmNodeRef> 	refs;
	private List<OsmTag> 	 	tags;

	public OsmWay() { 
		id 			= UUID.UNDEF;
		timestamp 	= null;
		refs 		= new ArrayList<OsmNodeRef>();
		tags 		= new ArrayList<OsmTag>();
	}
	
	public UUID   			getId()        				{ return id; }
	public Instant   		getTimstamp()  				{ return timestamp; }
	public List<OsmNodeRef>	getNodeRefs()  				{ return this.refs; }
	public OsmNodeRef		getNodeRef(int _i)  		{ return this.refs.get(_i); }
	public List<OsmTag>		getTags()  					{ return this.tags; }
	public OsmTag			getTag(int _i)  			{ return this.tags.get(_i); }
	
	public void 			setId(UUID _id)			{ this.id 		 = _id; }
	public void   			setTimestamp(Instant _date) { this.timestamp = _date; }
	public void 			addNodeRef(OsmNodeRef _ref) { this.refs.add(_ref); }
	public void 			addTag(OsmTag _tag)  		{ this.tags.add(_tag); }

	public String toString() {
		return new String("[Way] ")
				+ "(id= " + id
				+ ")";
	}

}
