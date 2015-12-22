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

/**
 * @enum Direction
 * @brief define the all way to travel a road element
 * 
 * This enum is not directly used, for convenience, we redefine
 * the four way directly as constant in IRoadElement
 *
 */
public enum Direction {
	UNKNOWN  ((byte) 78), /// 'U' ???
    NONE     ((byte) 78), /// 'N' impracticable
    DIRECT   ((byte) 64), /// 'D' only the direct way
    INDIRECT ((byte) 73), /// 'I' only the indirect way
    BOTH     ((byte) 66); /// 'B' practicable in both way

    private final byte value;

    private Direction(byte _value) {
    	value = _value;
    }

    public byte getValue() {
        return this.value;
    }
    public char getValueAsChar() {
        return (char) (value & 0xff); 
    }

    public static Direction valueForOpenStreetMap(byte _value) {
    	switch(_value) {
    	case 64 : return DIRECT;
    	case 73 : return INDIRECT;
    	case 66 : return BOTH;
    	case 78 : return NONE;
    	default : return UNKNOWN;
    	}
    }
    public static Direction valueForNavteq(String _value) {
    	switch(_value) {
    	case "FT" : return DIRECT;
    	case "N"  : return NONE;
    	case "TF" : return INDIRECT;
    	default   : return BOTH;
    	}
    }

}
