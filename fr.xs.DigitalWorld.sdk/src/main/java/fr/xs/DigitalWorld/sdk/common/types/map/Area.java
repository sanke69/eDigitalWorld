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

import fr.xs.DigitalWorld.sdk.common.interfaces.map.AreaInterface;
import fr.xs.DigitalWorld.sdk.common.types.coordinates.GeoCoordinate;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.AreaType;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.NodeType;
import fr.xs.jtk.tools.UUID;

public class Area implements AreaInterface {
	protected UUID        	      id;
	protected List<GeoCoordinate> geometry;

	protected AreaType      	  type;

    public Area() {
    	super();
    	id       = UUID.UNDEF;
    	geometry = null;
    	type     = AreaType.UNKNOWN;
    }
    public Area(UUID _id, List<GeoCoordinate> _geometry) {
    	this();
    	id       = _id;
    	geometry = _geometry;
    	type     = AreaType.UNKNOWN;
    }
    public Area(UUID _id, List<GeoCoordinate> _geometry, AreaType _type) {
    	this();
    	id       = _id;
    	geometry = _geometry;
    	type     = _type;
    }
    public Area(Area _area) {
    	this(_area.id, _area.geometry, _area.type);
    }
    
    public UUID getId() {
		return id;
	}
	public void setID(UUID _id) {
		id = _id;
	}

	public List<GeoCoordinate> getGeometry() {
		return geometry;
	}
	public void setGeometry(List<GeoCoordinate> _geom) {
		geometry = _geom;
	}

	public AreaType getType() {
		return type;
	}
	public void setType(AreaType _type) {
		type = _type;
	}

}
