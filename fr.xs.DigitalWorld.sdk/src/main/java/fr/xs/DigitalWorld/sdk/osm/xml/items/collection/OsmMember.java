package fr.xs.DigitalWorld.sdk.osm.xml.items.collection;

import fr.xs.jtk.tools.UUID;

import fr.xs.DigitalWorld.sdk.osm.xml.items.OsmItem;

public class OsmMember implements OsmItem {
	
	private String type;
	private UUID   ref;
	private String role;

	public OsmMember() { 
		type = null;
		ref  = UUID.UNDEF;
		role = null;
	}

	public String    		getType()        				{ return type; }
	public UUID   			getRef()  						{ return ref; }
	public String			getRole()  						{ return role; }
	
	public void 			setType(String _type)			{ this.type = _type; }
	public void   			setRef(UUID _ref)  			{ this.ref 	= _ref; }
	public void 			setRole(String _role)  			{ this.role = _role; }

	public String toString(){
		return new String("[Member] ")
				+ "(type= " + type
				+ ",ref= " + ref
				+ ",role= " + role + ")";
	}

}
