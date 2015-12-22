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
package fr.xs.DigitalWorld.sdk.common.types.position;

import java.io.Serializable;

import fr.xs.DigitalWorld.sdk.common.interfaces.position.RoadPositionInterface;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads.Direction;
import fr.xs.jtk.tools.UUID;

public class MapMatchedPosition extends RawPosition implements RoadPositionInterface, Serializable {
	private static final long serialVersionUID = 1413100001L;

	UUID 		roadEltId;
	double 		roadEltAbsCurv;
	int 		roadEltLane;
	Direction 	roadEltDirection;
	
	public MapMatchedPosition() {
		super();
		roadEltId   		= new UUID();
		roadEltAbsCurv 		= 0.0;
		roadEltLane    		= 0;
		roadEltDirection	= Direction.UNKNOWN;
	}
	public MapMatchedPosition(UUID _id, double _s, int _l, Direction _d) {
		super();
		roadEltId   		= _id;
		roadEltAbsCurv 		= _s;
		roadEltLane    		= _l;
		roadEltDirection	= _d;
	}
	public MapMatchedPosition(RoadPositionInterface _mp) {
		super();
		roadEltId 			= _mp.getRoadElementId();
		roadEltAbsCurv 		= _mp.getCurvilinearAbscissa();
		roadEltLane 		= _mp.getLaneId();
		roadEltDirection 	= _mp.getDirection();
	}
	public MapMatchedPosition(RawPosition _rp) {
		super(_rp);
		roadEltId   		= new UUID();
		roadEltAbsCurv 		= 0.0;
		roadEltLane    		= 0;
		roadEltDirection	= Direction.UNKNOWN;
	}
	public MapMatchedPosition(MapMatchedPosition _rp) {
		super(_rp);
		roadEltId   		= _rp.getRoadElementId();
		roadEltAbsCurv 		= _rp.getCurvilinearAbscissa();
		roadEltLane    		= _rp.getLaneId();
		roadEltDirection	= _rp.getDirection();
	}

	@Override
	public UUID getRoadElementId() {
		return roadEltId;
	}
	public void setRoadElementId(UUID _id) {
		roadEltId = _id;
	}

	public double getCurvilinearAbscissa() {
		return roadEltAbsCurv;
	}
	public void setCurvilinearAbscissa(double _s) {
		roadEltAbsCurv = _s;
	}

	@Override
	public int getLaneId() {
		return roadEltLane;
	}
	public void setLaneId(int _n) {
		roadEltLane = _n;
	}

	@Override
	public Direction getDirection() {
		return roadEltDirection;
	}
	public void setDirection(byte _direction) {
		roadEltDirection = Direction.valueForOpenStreetMap(_direction);
	}

	public String toString() {
		String ret = super.toString();

		ret += " [ID: " + roadEltId + ", s: " + roadEltAbsCurv + "]";
		
		return ret;
	}

}
