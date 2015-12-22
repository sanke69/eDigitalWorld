package fr.xs.DigitalWorld.sdk.osm.xml.tags;

import fr.xs.DigitalWorld.sdk.map.DigitalMap;
import fr.xs.DigitalWorld.sdk.osm.xml.items.collection.OsmRelation;
import fr.xs.DigitalWorld.sdk.osm.xml.items.collection.OsmTag;

public class RelationTags {

	public void enrichDatabase(OsmRelation _relation, DigitalMap _map) {

		for(OsmTag tag : _relation.getTags())
			switch(tag.getKey()) {
			case "addr:housenumber" :	 	
										break;
			case "addr:street" :	 	
										break;
			case "addr:city" :	 	
										break;
			case "addr:postcode" :	 	
										break;
			case "amenity" :	 	
										break;
			case "building" :	 	
										break;
			case "historic" :	 	
										break;
			case "line:SNCF" :	 	
										break;
			case "name" :	 	
										break;
			case "name:fr" :	 	
										break;
			case "name:en" :	 	
										break;
			case "name:hr" :	 	
										break;
			case "name:pt" :	 	
										break;
			case "name:de" :	 	
										break;
			case "name:it" :	 	
										break;
			case "name:ru" :	 	
										break;
			case "network" :	 	
										break;
			case "note" :	 	
										break;
			case "ref" :	 	
										break;
			case "route" :	 	
										break;
			case "school:FR" :	 	
										break;
			case "site" :	 	
										break;
			case "source" :	 	
										break;
			case "type" :	 			switch(tag.getValue()) {
										case "associatedStreet" : 	
																	break;
										case "boundary" : 			
																	break;
										case "enforcement" : 		
																	break;
										case "route" : 				
																	break;
										case "route_master" : 		
																	break;
										case "multipolygon" : 		
																	break;
										case "public_transport" : 	
																	break;
										case "restriction" : 		
																	break;
										case "site" : 				
																	break;
										default :					System.err.println("unknown relation type " + tag.getValue());
																	break;
										}
										break;
			case "url" :	 			
										break;
			default :	 				System.out.println("R-tag: key=" + tag.getKey() + ", value=" + tag.getValue());
										break;
			}

	}
}
