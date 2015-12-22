package fr.xs.DigitalWorld.sdk.osm.xml.items.collection;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import fr.xs.jtk.tools.UUID;

import fr.xs.DigitalWorld.sdk.osm.xml.items.OsmItem;

public class OsmRelation implements OsmItem {

	private UUID 			id;
	private UUID 			uid;
	private Instant			timestamp;
	private List<OsmMember> members;
	private List<OsmTag> 	tags;

	public OsmRelation() { 
		id 			= UUID.UNDEF;
		uid 		= UUID.UNDEF;
		timestamp	= null;
		members 	= new ArrayList<OsmMember>();
		tags 		= new ArrayList<OsmTag>();
	}
	
	public UUID    			getId()        					{ return id; }
	public UUID    			getUId()        				{ return uid; }
	public Instant 			getTimstamp()  					{ return timestamp; }
	public List<OsmMember>	getMembers()  					{ return this.members; }
	public OsmMember		getMember(int _i)  				{ return this.members.get(_i); }
	public List<OsmTag>		getTags()  						{ return this.tags; }
	public OsmTag			getTag(int _i)	  				{ return this.tags.get(_i); }
	
	public void 			setId(UUID _id)					{ this.id 			= _id; }
	public void 			setUId(UUID _uid)				{ this.uid 			= _uid; }
	public void   			setTimestamp(Instant _date)  	{ this.timestamp 	= _date; }
	public void 			addMember(OsmMember _member)  	{ this.members.add(_member); }
	public void 			addTag(OsmTag _tag)  			{ this.tags.add(_tag); }

	public String toString(){
		return new String("[Relation] ")
				+ "(id= " + id
				+ ",uid= " + uid
				+ ")";
	}

}
