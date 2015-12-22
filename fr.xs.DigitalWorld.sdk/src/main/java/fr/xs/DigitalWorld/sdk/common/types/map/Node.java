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

import fr.xs.DigitalWorld.sdk.common.interfaces.map.NodeInterface;
import fr.xs.DigitalWorld.sdk.common.types.coordinates.GeoCoordinate;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.AreaType;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.NodeType;
import fr.xs.jtk.tools.UUID;

public class Node extends GeoCoordinate implements NodeInterface {
	protected UUID id;

	protected NodeType type;

    public Node() {
    	super();
    	id    = UUID.UNDEF;
    	type  = NodeType.UNKNOWN;
    }
    public Node(UUID _id, double _longitude, double _latitude) {
    	super(_longitude, _latitude);
    	id    = _id;
    	type  = NodeType.UNKNOWN;
    }
    public Node(UUID _id, double _longitude, double _latitude, NodeType _type) {
    	super(_longitude, _latitude);
    	id    = _id;
    	type  = _type;
    }
    public Node(UUID _id, GeoCoordinate _coord) {
    	super(_coord);
    	id    = _id;
    	type  = NodeType.UNKNOWN;
    }
    public Node(UUID _id, GeoCoordinate _coord, NodeType _type) {
    	super(_coord);
    	id    = _id;
    	type  = _type;
    }

  	public UUID getId() {
		return id;
	}
	public void setId(UUID _id) {
		id = _id;
	}

	public GeoCoordinate getCoordinates() {
		return this;
	}

	public NodeType getType() {
		return type;
	}
	public void setType(NodeType _type) {
		type = _type;
	}

	@Override
	public String toString() {
		return "Node: id=" + id + ", coords=(" + lambda + ", " + phi + ")";
	}

}
