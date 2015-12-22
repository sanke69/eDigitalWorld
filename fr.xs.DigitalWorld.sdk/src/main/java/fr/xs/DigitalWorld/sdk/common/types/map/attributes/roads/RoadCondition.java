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

public enum RoadCondition {
		NotApplicable 		(-1, "Non défini"),
		PavedRoad 			( 1, "Route pavé"),
		UnpavedRoad 		( 4, "Route pavé"), // TODO:: 
		WorkInProgressRoad	( 2, "Route en travaux"),
		RoadInPoorCondition	( 3, "Route dégradée");
		
		private final int    c_value;
		private final String c_desc;
		
		private RoadCondition(int _value, String _desc) {
			c_value = _value;
			c_desc  = _desc;
		}
		
		public int getValue() {
			return this.c_value;
		}
		public String getDescription() {
	        return this.c_desc;
	    }

		public static RoadCondition getRoadConditionForNavTeq(int _value) {
	    	switch(_value) {
	    		case  0	: return NotApplicable;
	    		case  1	: return PavedRoad;
	    		case  2	: return UnpavedRoad;
	    		case  3	: return RoadInPoorCondition;
	    		default : return NotApplicable;
	    	}
	    }

	}