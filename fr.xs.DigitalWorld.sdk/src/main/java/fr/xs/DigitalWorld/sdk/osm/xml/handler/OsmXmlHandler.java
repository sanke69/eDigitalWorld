package fr.xs.DigitalWorld.sdk.osm.xml.handler;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import fr.xs.jtk.tools.UUID;

import fr.xs.DigitalWorld.sdk.osm.xml.items.OsmItem;
import fr.xs.DigitalWorld.sdk.osm.xml.items.collection.OsmBounds;
import fr.xs.DigitalWorld.sdk.osm.xml.items.collection.OsmMember;
import fr.xs.DigitalWorld.sdk.osm.xml.items.collection.OsmNode;
import fr.xs.DigitalWorld.sdk.osm.xml.items.collection.OsmNodeRef;
import fr.xs.DigitalWorld.sdk.osm.xml.items.collection.OsmRelation;
import fr.xs.DigitalWorld.sdk.osm.xml.items.collection.OsmTag;
import fr.xs.DigitalWorld.sdk.osm.xml.items.collection.OsmWay;

public class OsmXmlHandler extends DefaultHandler {

	private   List<OsmItem> osm;
	private   OsmItem       item, item2;
	private   StringBuffer 	buffer;
	protected boolean 		inOSM, inBounds, inNode, inWay, inRelation, inTag, inNodeRef, inMember;

	// simple constructeur
	public OsmXmlHandler(){
		super();
		osm 		= null;
		item 		= null;
		item2 		= null;
		
		inOSM   	= false;
		inBounds   	= false;
		inNode   	= false;
		inWay   	= false;
		inRelation  = false;
		inTag   	= false;
		inNodeRef   = false;
		inMember   	= false;
		
		buffer		= null;
	}

	public List<OsmItem> getList() {
		return osm;
	}

	//détection d'ouverture de balise
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if(qName.equals("osm")) {
			osm   = new LinkedList<OsmItem>();
			inOSM = true;

		} else if(qName.equals("bounds")) {
			item = new OsmBounds();
			try {
				double minlat    = Double.parseDouble(attributes.getValue("minlat"));
				double minlon    = Double.parseDouble(attributes.getValue("minlon"));
				double maxlat    = Double.parseDouble(attributes.getValue("maxlat"));
				double maxlon    = Double.parseDouble(attributes.getValue("maxlon"));

				((OsmBounds) item).setMinLat(minlat);
				((OsmBounds) item).setMinLon(minlon);
				((OsmBounds) item).setMaxLat(maxlat);
				((OsmBounds) item).setMaxLon(maxlon);
			} catch(Exception e) {
				throw new SAXException(e);
			}
			inBounds = true;

		} else if(qName.equals("node")) {
			item = new OsmNode();
			try {
				UUID   id        = new UUID(attributes.getValue("id"));
				UUID   uid       = new UUID(attributes.getValue("uid"));
				double longitude = Double.parseDouble(attributes.getValue("lon"));
				double latitude  = Double.parseDouble(attributes.getValue("lat"));
				//String timestamp = attributes.getValue("timestamp");

				((OsmNode) item).setId(id);
				((OsmNode) item).setUId(uid);
				((OsmNode) item).setLongitude(longitude);
				((OsmNode) item).setLatitude(latitude);
				//((Node) item).setTimestamp(new Date(timestamp));
			} catch(Exception e) {
				throw new SAXException(e);
			}
			inNode = true;

		} else if(qName.equals("way")) {
			item = new OsmWay();
			try {
				UUID   id        = new UUID(attributes.getValue("id"));
//				String timestamp = attributes.getValue("timestamp");

				((OsmWay) item).setId(id);
				//((Way) item).setTimestamp(new Date(timestamp));
			} catch(Exception e) {
				throw new SAXException(e);
			}
			inWay = true;

		} else if(qName.equals("relation")) {
			item = new OsmRelation();
			try {
//				String timestamp = attributes.getValue("timestamp");

				((OsmRelation) item).setId(new UUID(attributes.getValue("id")));
				((OsmRelation) item).setUId(new UUID(attributes.getValue("uid")));
				//((Relation) item).setTimestamp(new Date(timestamp));
			} catch(Exception e) {
				throw new SAXException(e);
			}
			inRelation = true;

		} else if(qName.equals("tag")) {
			item2 = new OsmTag();
			try {
				String key   = attributes.getValue("k");
				String value = attributes.getValue("v");

				((OsmTag) item2).setKey(key);
				((OsmTag) item2).setValue(value);
			} catch(Exception e) {
				throw new SAXException(e);
			}
			inTag = true;

		} else if(qName.equals("nd")) {
			item2 = new OsmNodeRef();
			try {
				((OsmNodeRef) item2).setRef(new UUID(attributes.getValue("ref")));
			} catch(Exception e) {
				throw new SAXException(e);
			}
			inNodeRef = true;

		} else if(qName.equals("member")) {
			item2 = new OsmMember();
			try {
				String type = attributes.getValue("type");
				String role = attributes.getValue("role");

				((OsmMember) item2).setType(type);
				((OsmMember) item2).setRef(new UUID(attributes.getValue("ref")));
				((OsmMember) item2).setRole(role);
			} catch(Exception e) {
				throw new SAXException(e);
			}
			inMember = true;

		} else {
			throw new SAXException("Balise " + qName + " inconnue.");
		}
	}
	//détection fin de balise
	public void endElement(String uri, String localName, String qName) throws SAXException{
		if(qName.equals("osm")) {
			item  = null;
			item2 = null;
			inOSM = false;

		} else if(qName.equals("bounds")) {
			osm.add(item);
			item       = null;
			inBounds   = false;

		} else if(qName.equals("node")) {
			osm.add(item);
			item       = null;
			inNode     = false;

		} else if(qName.equals("way")) {
			osm.add(item);
			item       = null;
			inWay      = false;

		} else if(qName.equals("relation")) {
			osm.add(item);
			item       = null;
			inRelation = false;

		} else if(qName.equals("tag")) {
			if(inNode && item != null && item2 != null)
				((OsmNode) item).addTag(((OsmTag) item2));
			else if(inWay && item != null && item2 != null)
				((OsmWay) item).addTag(((OsmTag) item2));
			else if(inRelation && item != null && item2 != null)
				((OsmRelation) item).addTag(((OsmTag) item2));

			item2      = null;
			inTag      = false;

		} else if(qName.equals("nd")) {
			if(inWay && item != null && item2 != null)
				((OsmWay) item).addNodeRef(new OsmNodeRef((OsmNodeRef) item2));

			item2      = null;
			inNodeRef  = false;

		} else if(qName.equals("member")) {
			if(inRelation && item != null && item2 != null)
				((OsmRelation) item).addMember(((OsmMember) item2));

			item2      = null;
			inMember   = false;

		} else {
			throw new SAXException("Balise " + qName + " inconnue.");
		}          
	}
	//détection de caractères
	public void characters(char[] _ch, int _start, int _length) throws SAXException {
		String lecture = new String(_ch, _start, _length);
		if(buffer != null) buffer.append(lecture);       
	}
	//début du parsing
	public void startDocument() throws SAXException {
		//System.out.println("Début du parsing");
	}
	//fin du parsing
	public void endDocument() throws SAXException {
		//System.out.println("Fin du parsing");
		//System.out.println("Resultats du parsing");
		//for(OsmItem p : osm){
		//	System.out.println(p);
		//}
	}

}
