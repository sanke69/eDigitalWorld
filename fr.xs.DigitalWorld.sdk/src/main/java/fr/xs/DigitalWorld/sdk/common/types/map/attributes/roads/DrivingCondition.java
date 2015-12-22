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

public enum DrivingCondition {
		PartOfMotorway				( 1, "Quasi-autoroute"),
		PartOfMultiCarriageWay		( 2, "Route à 2 chaussées"),
		PartOfSingleCarriageWay		( 3, "Route à 1 chaussée"),
		PartOfRoundAbout  			( 4, "Rond Point"),
		PartOfParkingPlace 			( 6, "Zone de parking"),
		PartOfParkingGarage			( 7, "Zone de garage"),
		UnstructureTrafficSquare	( 8, "Zone de trafic non structurée"),
		PartOfSlipRoad				(10, "Bretelle"),
		PartOfServiceRoad			(11, "Route de service"),
		ExitEntranceCarPark			(12, "Entrée de parking"),
		PartOfPedestrianZone 		(14, "Quasi-autoroute"),
		PartOfWalkWay				(15, "Chemin piéton"),
		PartOfBicycleWay			(16, "Piste cyclable"),
		SpecialTrafficFigures		(17, "Quasi-autoroute"),
		RoadForAuthorities			(20, "Route pour autorités"),
		NotApplicable				(-1, "Non défini");

		private final int    value;
		private final String description;
		
		private DrivingCondition(int _value, String _desc) {
			value = _value;
			description = _desc;
		}
		
		public int getValue() {
			return this.value;
		}
	    public String getDescription() {
	        return this.description;
	    }

		public static DrivingCondition getFormOfWayForNavTeq(int _value) {
	    	switch(_value) {
	    		case -1	: return NotApplicable;
	    		case  1	: return PartOfMotorway;
	    		case  2	: return PartOfMultiCarriageWay;
	    		case  3	: return PartOfSingleCarriageWay;
	    		case  4	: return PartOfRoundAbout;
	    		case  6	: return PartOfParkingPlace;
	    		case  7	: return PartOfParkingGarage;
	    		case  8	: return UnstructureTrafficSquare;
	    		case 10	: return PartOfSlipRoad;
	    		case 11	: return PartOfServiceRoad;
	    		case 12	: return ExitEntranceCarPark;
	    		case 14	: return PartOfPedestrianZone;
	    		case 15	: return PartOfWalkWay;
	    		case 17	: return SpecialTrafficFigures;
	    		case 20	: return RoadForAuthorities;
	    		default : return NotApplicable;
	    	}
	    }
		public static DrivingCondition getFormOfWayForMapInfo(String _value) {
			if(_value.equalsIgnoreCase("Autoroute")) {
	    		return PartOfMotorway;
	    	} else if(_value.equalsIgnoreCase("Quasi-autoroute")) {
	    		return PartOfMultiCarriageWay;
	    	} else if(_value.equalsIgnoreCase("Bretelle")) {
	    		return PartOfSlipRoad;
	    	} else if(_value.equalsIgnoreCase("Route à 2 chaussées")) {
	    		return PartOfMultiCarriageWay;
	    	} else if(_value.equalsIgnoreCase("Route à 1 chaussée")) {
	    		return PartOfSingleCarriageWay;
	    	} else if(_value.equalsIgnoreCase("Route empierrée")) {
	    		return PartOfPedestrianZone;
	    	} else if(_value.equalsIgnoreCase("Piste cyclable")) {
	    		return PartOfBicycleWay;
	    	} else if(_value.equalsIgnoreCase("Chemin")) {
	    		return PartOfWalkWay;
	    	} else if(_value.equalsIgnoreCase("Sentier")) {
	    		return PartOfWalkWay;
	    	} else if(_value.equalsIgnoreCase("Escalier ou passerelle")) {
	    		return PartOfWalkWay;
	    	} else {
	    		return NotApplicable;
	    	}
	    }

	}