package fr.xs.DigitalWorld.sdk.osm.xml;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

import fr.xs.DigitalWorld.sdk.osm.xml.handler.*;
import fr.xs.DigitalWorld.sdk.osm.xml.items.OsmItem;

public class OsmXmlParser {
	List<OsmItem>	osm_item;

	public List<OsmItem> getList() { return osm_item; }

	public OsmXmlParser() {
		osm_item = null;
	}

	public void parse(InputStream _stream) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		OsmXmlHandler    handler = new OsmXmlHandler();
		try {
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(_stream, (DefaultHandler) handler);
		} catch (Exception e) {
			System.err.println("File Read Error: " + e);
			e.printStackTrace();
		}
		
		osm_item = handler.getList();
	}

}
