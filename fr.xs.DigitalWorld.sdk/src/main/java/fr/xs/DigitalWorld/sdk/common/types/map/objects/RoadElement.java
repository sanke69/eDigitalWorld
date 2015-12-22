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

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import fr.xs.DigitalWorld.sdk.common.types.coordinates.GeoCoordinate;
import fr.xs.DigitalWorld.sdk.common.types.map.Node;
import fr.xs.DigitalWorld.sdk.common.types.map.Segment;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.SegmentType;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.others.AdministrativeStatus;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads.Direction;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads.DrivingCondition;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads.RoadClass;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads.RoadCondition;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads.RoadFeatureType;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads.SlipCondition;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads.SpeedDistribution;
import fr.xs.DigitalWorld.sdk.common.types.position.MapMatchedPosition;
import fr.xs.jtk.tools.UUID;

public class RoadElement extends Segment implements Comparable<RoadElement>, Serializable {
	private static final long serialVersionUID = 1413100003L;

	// Essential parameters
	private UUID         			id;
    private Node					from, to;
    private List<GeoCoordinate>		geometry;
    private Direction 				direction;

    private double					width;
    private double        			length;

    private int						lanes;

    private String        			StdName;
    
    private SpeedDistribution		mandatorySpeedLimit = null;

	// Optionnal parameters - I
    private String        			RightName;
    private String        			LeftName;
    private String        			ItiName;
    private String        			InterName;

    // Optionnal parameters - II
    private RoadClass 				frclass;
	private DrivingCondition 		drivingcondition;
	private SlipCondition 			sliproad;
	private RoadCondition 			rcondition;
	private RoadFeatureType			crossing;
	private int						crossingLvl;

	// Optionnal parameters - III
	private AdministrativeStatus	adminstatus;
	private Date					inservice;


    public RoadElement() {
        type = SegmentType.RoadElement;
    }
    public RoadElement(RoadElement _re) {
		id 					= _re.id;
		from 				= _re.from;
		to 					= _re.to;
		geometry 			= _re.geometry;
		direction 			= _re.direction;
		width 				= _re.width;
		length 				= _re.length;
		lanes 				= _re.lanes;
		StdName 			= _re.StdName;
		mandatorySpeedLimit = _re.mandatorySpeedLimit;
		RightName 			= _re.RightName;
		LeftName 			= _re.LeftName;
		ItiName 			= _re.ItiName;
		InterName 			= _re.InterName;
		frclass 			= _re.frclass;
		drivingcondition 	= _re.drivingcondition;
		sliproad 			= _re.sliproad;
		rcondition 			= _re.rcondition;
		crossing 			= _re.crossing;
		crossingLvl 		= _re.crossingLvl;
		adminstatus 		= _re.adminstatus;
		inservice 			= _re.inservice;
    }

    public boolean equals(Object _o) {
        if(!(_o instanceof RoadElement))
            return false;

        return id == ((RoadElement) _o).id;
    }
    
    public int compareTo(RoadElement _re) {
        return 0; //ID - _re.ID;
    }

    ///                                    ///
    /// INHERITED METHOD FROM IRoadElement ///
    ///                                    ///
	public UUID getId() {
		return id;
	}
	public void setId(UUID _id) {
		id = _id;
	}

	public String getName() {
		return StdName;
	}
	public void setName(String _name) {
		StdName = _name;
	}

	public String getInternationalName() {
		if(InterName != null)
			return InterName;
		else 
			return StdName;
	}
	public void setInternationalName(String _name) {
		InterName = _name;
	}
	
	public String getItineraryName() {
		if(ItiName != null)
			return ItiName;
		else 
			return StdName;
	}
	public void setItineraryName(String _name) {
		ItiName = _name;
	}
	
	public String getName4RightSide() {
		if(RightName != null)
			return RightName;
		else 
			return StdName;
	}
	public void setName4RightSide(String _name) {
		RightName = _name;
    }

	public String getName4LeftSide() {
		if(LeftName != null)
			return LeftName;
		else 
			return StdName;
	}
	public void setName4LeftSide(String _name) {
		LeftName = _name;
    }

	public List<GeoCoordinate> getGeometry() {
		return geometry;
	}
	public void setGeometry(List<GeoCoordinate> _geom) {
		geometry = _geom;
	}
	
	public double getLength() {
		return length;
	}
	public void setLength(double _d) {
		length = _d;
	}
	
	public Node getFromNode() {
		return from;
	}
	public void setFromNode(Node _node) {
		from = _node;
	}

	public Node getToNode() {
		return to;
	}
	public void setToNode(Node _node) {
		to = _node;
	}
	
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction _d) {
		direction = _d;
	}
	
	public double getWidth() {
		return width;
	}
	public void setWidth(double _w) {
		width = _w;
	}
	
	public int getLaneNumber() {
		return lanes;
	}
	public void setLaneNumber(int _n) {
		lanes = _n;
	}



	public SpeedDistribution getMandatorySpeedDistribution() {
		return mandatorySpeedLimit;
	}
	public void setMandatorySpeedDistribution(SpeedDistribution _msl) {
    	mandatorySpeedLimit = _msl;
    }
	
	public int getMandatorySpeedLimitFor(MapMatchedPosition _p) {
		if(_p == null) return -1;

		//Direction way = RoadToolBox.inst.getDrivingWay(_p, this);

		if(direction == Direction.NONE)
			return -1;
/*
		if(direction == Direction.BOTH || way == direction)
			switch(getDrivingCondition()) {
				case PartOfMotorway				: return 130;
				case PartOfMultiCarriageWay		: return 110;
				case PartOfSingleCarriageWay	: return  90;
				case PartOfRoundAbout			: return  70;
				case PartOfParkingPlace			: return  50;
				case PartOfParkingGarage		: return  30;
				case UnstructureTrafficSquare   : return  50;
				case PartOfSlipRoad      	 	: return  90;
				case PartOfServiceRoad 			: return  70;
				case ExitEntranceCarPark		: return  50;
				case PartOfPedestrianZone       : return  15;
				case PartOfWalkWay				: return  15;
				case PartOfBicycleWay           : return  15;
				case SpecialTrafficFigures		: return  50;
				case RoadForAuthorities			: return  70;
				case NotApplicable				: return  15;
				default                         : return  15;
			}
*/
		if(direction == Direction.BOTH /*|| way == direction*/)
			switch(getFunctionnalRoadClass()) {
				case Motorway				: return 130;
				case MajorRoad				: return 110;
				case OtherMajorRoad	        : return  90;
				case SecondaryRoad			: return  70;
				case LocalConnectingRoad	: return  50;
				case LocalRoadLvl1			: return  45;
				case LocalRoadLvl2   		: return  30;
				case LocalRoadLvl3       	: return  15;
				case OtherRoad 				: return  15;
				case BicybleWay				: return  15;
				case WalkWay     		    : return  15;
				case Unknown				: return  15;
				default                     : return  15;
			}

		return -1;

	}



	public AdministrativeStatus getAdministrativeStatus() {
		if(adminstatus != null)
			return adminstatus;
		return AdministrativeStatus.Public;
	}
	public void setAdministrativeStatus(AdministrativeStatus _adminstatus) {
		adminstatus = _adminstatus;
	}

	public Date getInServiceDate() {
		return inservice;
	}
	public void setInServiceDate(Date _date) {
		inservice = _date;
	}

	public RoadClass getFunctionnalRoadClass() {
		if(frclass != null)
			return frclass;
		return RoadClass.Unknown;
	}
	public void setFunctionnalRoadClass(RoadClass _frc) {
		frclass = _frc;
	}
	
	public DrivingCondition getDrivingCondition() {
		if(drivingcondition != null)
			return drivingcondition;
		return DrivingCondition.NotApplicable;
	}
	public void setDrivingCondition(DrivingCondition _fow) {
		drivingcondition = _fow;
	}
	
	public SlipCondition getSlipRoad() {
		if(sliproad != null)
			return sliproad;
		return SlipCondition.NoSlipRoad;
	}
	public void setSlipRoad(SlipCondition _sr) {
		sliproad = _sr;
	}
	
	public RoadCondition getRoadCondition() {
		if(rcondition != null)
			return rcondition;
		return RoadCondition.NotApplicable;
	}
	public void setRoadCondition(RoadCondition _condition) {
		rcondition = _condition;
	}
	
	public RoadFeatureType getCrossing() {
		if(crossing != null)
			return crossing;
		return RoadFeatureType.Road;
	}
	public void setCrossing(RoadFeatureType _crossing) {
		crossing = _crossing;
	}
	
	public int getCrossingLevel() {
		return crossingLvl;
	}
	public void setCrossingLevel(int _lvl) {
		crossingLvl = _lvl;
	}

    public String toString() {
    	String ret = new String();
    	ret = id + ":" + " " + StdName + " {";
    	for(int i = 0; i < geometry.size(); i++)
    		ret += geometry.get(i).getLongitude() + " " + geometry.get(i).getLatitude() + (i < geometry.size() - 1 ? ", " : "");
    	ret += " }";
    	return ret;
    }

};
