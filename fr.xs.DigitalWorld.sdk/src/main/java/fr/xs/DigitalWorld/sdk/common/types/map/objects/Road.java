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
package fr.xs.DigitalWorld.sdk.common.types.map.objects;

import java.util.List;

import fr.xs.DigitalWorld.sdk.common.types.coordinates.GeoCoordinate;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.others.AdministrativeStatus;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads.DrivingCondition;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads.RoadClass;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads.RoadCondition;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads.RoadDirection;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads.RoadFeatureType;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads.SlipCondition;

public class Road {
	private int						id;
	private String					name;
	private String					nature;
	private RoadDirection			ways;
	private String					ref;
	private List<GeoCoordinate>		geometry;
	private RoadClass				roadclass;
	private DrivingCondition		drvcond;
	private SlipCondition			slipcond;
	private RoadCondition			status;
	private RoadFeatureType			crosslvl;
	private AdministrativeStatus	adminstatus;
	private List<Integer>			common;
	private List<Integer>			nodes_id;
	
	public Road(int    			   _id,
				  String 			   _name,
				  String 			   _nature,
				  RoadDirection 	   _ways,
				  String 			   _ref,
				  List<GeoCoordinate>       _geometry,
				  RoadClass 		   _roadclass,
				  DrivingCondition     _drvcond,
				  SlipCondition        _slipcond,
				  RoadCondition 	   _status,
				  RoadFeatureType        _crosslvl,
				  AdministrativeStatus _adminstatus,
				  List<Integer> 	   _common,
				  List<Integer> 	   _nodes_id) {
		id          = _id;
		name        = _name;
		nature      = _nature;
		ways        = _ways;
		ref         = _ref;
		geometry    = _geometry;
		roadclass   = _roadclass;
		drvcond     = _drvcond;
		slipcond    = _slipcond;
		status      = _status;
		crosslvl    = _crosslvl;
		adminstatus = _adminstatus;
		common      = _common;
		nodes_id    = _nodes_id;
	}

}
