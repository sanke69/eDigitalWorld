/**
 * Copyright (C) 2007-?
 * 
 * @author   <a href='mailto:steve.pechberti@gmail.com'> Steve PECHBERTI </a>
 *
 * @section license License
 *    [EN] This file is the intellectual property of Steve PECHBERTI.
 *         Any use, partial or complete copy, modification of the file
 *         without my approval is forbidden
 *    [FR] Ce fichier est la propriete intellectuelle de Steve PECHBERTI.
 *         Toute utilisation, copie partielle ou totale, modification
 *         du fichier sans mon autorisation est interdite
 *
 * @section disclaimer Disclaimer
 *    [EN] This program is distributed in the hope that it will be useful,
 *         but WITHOUT ANY WARRANTY; without even the implied warranty of
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *    [FR] Ce programme est distribué dans l'espoir qu'il sera utile,
 *         mais SANS AUCUNE GARANTIE, sans même la garantie implicite de
 *         VALEUR MARCHANDE ou FONCTIONNALITE POUR UN BUT PARTICULIER.
 *
 */
package fr.xs.DigitalWorld.sdk.common.types.map;

import java.util.List;

import fr.xs.DigitalWorld.sdk.common.interfaces.map.SegmentInterface;
import fr.xs.DigitalWorld.sdk.common.types.coordinates.GeoCoordinate;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.NodeType;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.SegmentType;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads.Direction;
import fr.xs.jtk.tools.UUID;

public class Segment implements SegmentInterface {
	protected UUID        	      id;
	protected List<GeoCoordinate> geometry;

	protected SegmentType 		  type;

    public Segment() {
    	super();
    	id       = new UUID();
    	geometry = null;
    	type  	 = SegmentType.UNKNOWN;
    }
    public Segment(UUID _id, List<GeoCoordinate> _geometry) {
    	this();
    	id       = _id;
    	geometry = _geometry;
    	type  	 = SegmentType.UNKNOWN;
    }
    public Segment(UUID _id, List<GeoCoordinate> _geometry, SegmentType _type) {
    	this();
    	id       = _id;
    	geometry = _geometry;
    	type  	 = _type;
    }
    public Segment(Segment _area) {
    	this(_area.id, _area.geometry);
    }

	@Override
    public UUID getId() {
		return id;
	}
	public void setID(UUID _id) {
		id = _id;
	}

	@Override
	public List<GeoCoordinate> getGeometry() {
		return geometry;
	}
	public void setGeometry(List<GeoCoordinate> _geom) {
		geometry = _geom;
	}

	@Override
	public SegmentType getType() {
		return type;
	}
	public void setType(SegmentType _type) {
		type = _type;
	}

	@Override
	public double getLength() {
		return 0;
	}

	@Override
	public Direction getDirection() {
		return null;
	}
	
	public String toString() {
		return "Segment: id=" + id + " " + geometry;
	}

}
