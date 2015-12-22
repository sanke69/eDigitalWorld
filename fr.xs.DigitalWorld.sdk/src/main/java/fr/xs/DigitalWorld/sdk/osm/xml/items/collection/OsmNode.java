package fr.xs.DigitalWorld.sdk.osm.xml.items.collection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.xs.jtk.tools.UUID;

import fr.xs.DigitalWorld.sdk.osm.xml.items.OsmItem;

public class OsmNode implements OsmItem {

	private UUID  	  id, 
					  uid;
	private double 	  longitude, 
					  latitude;
	private Date   	  timestamp;
	private List<OsmTag> tags;

	public OsmNode() {		
		id 			= new UUID();		
		uid 		= new UUID();
		longitude	= 0.0f;
		latitude	= 0.0f;
		timestamp 	= null;
		tags 		= new ArrayList<OsmTag>();
	}

	public UUID    		getId()        				{ return this.id; }
	public UUID    		getUId()       				{ return this.uid; }
	public double 		getLongitude() 				{ return this.longitude; }
	public double 		getLatitude()  				{ return this.latitude; }
	public Date   		getTimstamp()  				{ return this.timestamp; }
	public List<OsmTag>	getTags()  					{ return this.tags; }
	public OsmTag		getTag(int _i)  			{ return this.tags.get(_i); }

	public void 		setId(UUID _id)				{ this.id 			= _id; }
	public void    		setUId(UUID _uid)       	{ this.uid 			= _uid; }
	public void   		setTimestamp(Date _date)  	{ this.timestamp 	= _date; }
	public void 		setLongitude(double _long) 	{ this.longitude 	= _long; }
	public void 		setLatitude(double _lat)  	{ this.latitude 	= _lat; }
	public void 		addTag(OsmTag _tag)  		{ this.tags.add(_tag); }

	public String toString(){
		return new String("[Node] ")
				+ "(id= " + id
				+ ",uid= " + uid
				+ ",longitude= " + longitude
				+ ",latitude= " + latitude
				+ ")";
	}

}
