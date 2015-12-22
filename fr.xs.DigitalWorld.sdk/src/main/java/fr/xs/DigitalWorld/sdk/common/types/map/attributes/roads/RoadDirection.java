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
package fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads;

public enum RoadDirection {
		UNKNOWN  ((byte) 78, "'U' Non défini"),
	    NONE     ((byte) 78, "'N' Impraticable"),
	    DIRECT   ((byte) 64, "'D' Sens Direct"),
	    INDIRECT ((byte) 73, "'I' Sens Indirect"),
	    BOTH     ((byte) 66, "'B' Double sens");
	
	    private final byte   value;
	    private final String desc;
	
	    private RoadDirection(byte _value, String _desc) {
	    	value = _value;
	    	desc  = _desc;
	    }
	
	    public byte getValue() {
	        return this.value;
	    }
	    public char getValueAsChar() {
	        return (char) (value & 0xff); 
	    }
	    public String getDescription() {
	        return this.desc;
	    }

	}