package fr.xs.DigitalWorld.sdk.osm.xml.items.collection;

import fr.xs.DigitalWorld.sdk.osm.xml.items.OsmItem;

public class OsmTag implements OsmItem {
	
	private String key, value;

	public OsmTag() { 
		key 	= null;
		value 	= null;
	}
	public OsmTag(String _key, String _value) { 
		key 	= _key; 
		value 	= _value;
	}

	public String getKey()   { return key; }
	public String getValue() { return value; }

	public void setKey(String _key)   { key = _key; }
	public void setValue(String _value) { value = _value; }

	public String toString() {
		return new String("[Tag] ")
				+ "(key= " + key
				+ ",value= " + value
				+ ")";
	}

}
