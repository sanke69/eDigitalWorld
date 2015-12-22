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

public enum SlipCondition {
	NoSlipRoad 							( 0, "Pas de croisement"),
	ParallelRoad 						( 1, "Pas de croisement"),
	SlipRoadOfAGradeSeparatedCrossing	( 2, "Bretelle d'accès avec changement de niveau gnd"),
	SlipRoadOfACrossingAtGrade			( 3, "Bretelle d'accès sans changement de niveau gnd"),
	MajorMinorSlipRoad					(18, "Bretelle d'accès");

	private final int    value;
	private final String desc;
	
	private SlipCondition(int _value, String _desc) {
		value = _value;
		desc  = _desc;
	}
	
	public int getValue() {
		return this.value;
	}
	public String getDescription() {
        return this.desc;
    }

	public static SlipCondition valueForNavTeq(int _value) {
    	switch(_value) {
    		case  0	: return NoSlipRoad;
    		case  1	: return ParallelRoad;
    		case  2	: return SlipRoadOfAGradeSeparatedCrossing;
    		case  3	: return SlipRoadOfACrossingAtGrade;
    		case 18	: return MajorMinorSlipRoad;
    		default : return NoSlipRoad;
    	}
    }

}