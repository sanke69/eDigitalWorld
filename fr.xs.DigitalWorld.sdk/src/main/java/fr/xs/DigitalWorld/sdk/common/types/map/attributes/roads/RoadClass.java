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

public enum RoadClass {
		Motorway			( 0, "Autoroute"),
		MajorRoad			( 1, "Route Nationale"),
		OtherMajorRoad		( 2, "Route Départementale"),
		SecondaryRoad     	( 3, "Route InterCommunale"),
		LocalConnectingRoad ( 4, "Route Communale"),
		LocalRoadLvl1		( 5, "Route Locale de Niveau 1"),
		LocalRoadLvl2		( 6, "Route Locale de Niveau 2"),
		LocalRoadLvl3		( 7, "Route Locale de Niveau 3"),
		OtherRoad			( 8, "Autre"),
		BicybleWay          ( 9, "Piste cyclable"),
		WalkWay             (10, "Sentier piéton"),
		Unknown				(-1, "Non défini");

		private final int    value;
		private final String desc;

	    private RoadClass(int _value, String _desc) {
	    	value = _value;
	    	desc  = _desc;
	    }

	    public int getValue() {
	        return this.value;
	    }
	    public String getDescription() {
	        return this.desc;
	    }

	    public static RoadClass getFunctionnalRoadClassForTeleAtlas(int _value) {
	    	switch(_value) {
	    		case 0	: return Motorway;
	    		case 1	: return MajorRoad;
	    		case 2	: return OtherMajorRoad;
	    		case 3	: return SecondaryRoad;
	    		case 4	: return LocalConnectingRoad;
	    		case 5	: return LocalRoadLvl1;
	    		case 6	: return LocalRoadLvl2;
	    		case 7	: return LocalRoadLvl3;
	    		case 8	: return OtherRoad;
	    		default : return Unknown;
	    	}
	    }
	    
	    public static RoadClass getFunctionnalRoadClassForNavTeq(int _value) {
	    	switch(_value) {
	    		case 0	: return Motorway;
	    		case 1	: return MajorRoad;
	    		case 2	: return OtherMajorRoad;
	    		case 3	: return SecondaryRoad;
	    		case 4	: return LocalConnectingRoad;
	    		default : return Unknown;
	    	}
	    }
	    
	    public static RoadClass getFunctionnalRoadClassForMapInfo(String _value) {
			if(_value.equalsIgnoreCase("Autoroute")) {
	    		return Motorway;
	    	} else if(_value.equalsIgnoreCase("Quasi-autoroute")) {
	    		return MajorRoad;
	    	} else if(_value.equalsIgnoreCase("Bretelle")) {
	    		return OtherMajorRoad;
	    	} else if(_value.equalsIgnoreCase("Route à 2 chaussées")) {
	    		return OtherMajorRoad;
	    	} else if(_value.equalsIgnoreCase("Route à 1 chaussée")) {
	    		return SecondaryRoad;
	    	} else if(_value.equalsIgnoreCase("Route empierrée")) {
	    		return OtherRoad;
	    	} else if(_value.equalsIgnoreCase("Piste cyclable")) {
	    		return BicybleWay;
	    	} else if(_value.equalsIgnoreCase("Chemin")) {
	    		return WalkWay;
	    	} else if(_value.equalsIgnoreCase("Sentier")) {
	    		return WalkWay;
	    	} else if(_value.equalsIgnoreCase("Escalier ou passerelle")) {
	    		return WalkWay;
	    	} else {
	    		return Unknown;
	    	}
	    }

	}