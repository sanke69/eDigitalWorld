package fr.xs.DigitalWorld.sdk.osm.xml.tags;

import fr.xs.DigitalWorld.sdk.common.types.map.Segment;
import fr.xs.DigitalWorld.sdk.osm.xml.items.collection.OsmTag;

public class WayTags {

	public static void enrichSegment(OsmTag _tag, Segment _enrich) {
		switch(_tag.getKey()) {
		case "addr:city" : 			
									break;
		case "addr:country" : 		
									break;
		case "addr:housenumber" : 	
									break;
		case "addr:postcode" : 		
									break;
		case "addr:state" : 		
									break;
		case "addr:street" : 		
									break;
		case "amenity" : 			
									break;
		case "barrier" : 			
									break;
		case "capacity:disabled" : 	
									break;
		case "crossing" : 			
									break;
		case "crossing:bollard" : 	
									break;
		case "disused:amenity" : 	
									break;
		case "drive_through" : 		
									break;
		case "highway" : 			
									break;
		case "name" : 				
									break;
		case "layer" : 			
									break;
		case "leisure" : 			
									break;
		case "office" : 			
									break;
		case "operator" : 			
									break;
		case "parking" : 			
									break;
		case "phone" : 				
									break;
		case "ref:UAI" : 				
									break;
		case "school" : 				
									break;
		case "school:FR" : 				
									break;
		case "smoking" : 				
									break;
		case "shop" : 				
									break;
		case "source" : 			
									break;
		case "tactile_paving" : 	
									break;
		case "takeaway" : 	
									break;
		case "tourism" : 			
									break;
		case "tobacco" : 			
									break;
		case "website" : 		
									break;
		case "wheelchair" : 		
									break;
		default :	 				System.out.println("W-tag: key=" + _tag.getKey() + ", value=" + _tag.getValue());
									break;
		}
	}

}
