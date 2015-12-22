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

public enum RoadFeatureType {
	Road				( 0, "Route"),
	ServiceRoad			( 1, "Route de service"),
	EmergencyLane		( 2, "Bande d'arrêt d'urgence"),
	PhysicalDivider		( 3, "PhysicalDivider"),
	BusBay				( 4, "Stationnement bus"),
	WaitingBay			( 5, "Dépose bus"),
	BusStop				( 6, "Arrêt de bus"),
	Parking				( 7, "Parking"),
	UTurnAcroossDivider	( 8, "Rond-Point"),
	SlipRoad			( 9, "Rond-Point"),
	BicybleStopArea		(10, "Stationnement deux roues"),
	Bridge				(11, "Pont"),
	Viaduct				(12, "Viaduc"),
	Aqueduct			(13, "Aqueduc"),
	Tunnel				(14, "Tunnel"),
	TrafficIsland		(15, "Rond-Point"),
	RoadSide			(16, "Rocade"),
	Sidewalk			(17, "Chemin de randonnée"),
	BicyclePath			(18, "Piste cyclable"),
	NotDefined			(19, "Non défini");

	private final int    value;
	private final String desc;

    private RoadFeatureType(int _value, String _desc) {
    	value = _value;
    	desc  = _desc;
    }

    public int getValue() {
        return this.value;
    }
	public String getDescription() {
        return this.desc;
    }

    static public RoadFeatureType valueFrom(String _label) {
    	if(_label.equalsIgnoreCase("Sol")) {
    		return Road;
    	} else if(_label.equalsIgnoreCase("Pont")) {
    		return Bridge;
    	} else {
    		return Tunnel;
    	}
    }

}