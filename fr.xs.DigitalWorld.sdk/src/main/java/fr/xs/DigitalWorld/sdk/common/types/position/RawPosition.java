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
import java.text.DecimalFormat;
import java.time.Instant;

import fr.xs.DigitalWorld.sdk.common.interfaces.position.DynamicPositionInterface;
import fr.xs.DigitalWorld.sdk.common.interfaces.position.OrientedPositionInterface;
import fr.xs.DigitalWorld.sdk.common.interfaces.position.PositionInterface;
import fr.xs.DigitalWorld.sdk.common.types.coordinates.GeoCoordinate;

public class RawPosition extends GeoCoordinate implements DynamicPositionInterface, Serializable {
	private static final long serialVersionUID = 1413100002L;

	int    		heading;

	double 		velocity_front;
	double 		velocity_right;
	double 		velocity_up;

	long 		timestamp;

	double[] 	position_covariance_matrix;
	double[] 	velocity_covariance_matrix;

	int    		flag;
	double 		hdop;
	double 		integrity_indicator;
	int    		message_id;
	int    		nb_sats;
	
	public RawPosition() {
		super(0.0, 0.0, 0.0);

		heading						= 0;

		velocity_front 				= 0.0;
		velocity_right 				= 0.0;
		velocity_up 				= 0.0;

		timestamp 					= Instant.now().toEpochMilli();

		position_covariance_matrix 	= null;
		velocity_covariance_matrix 	= null;

		flag 						= 0;
		hdop 						= 0.0;
		integrity_indicator 		= 0.0;
		message_id 					= 0;
		nb_sats 					= 0;
	}
	public RawPosition(double _lg, double _lt, double _alt) {
		super(_lg, _lt, _alt);
		heading						= 0;

		velocity_front 				= 0.0;
		velocity_right 				= 0.0;
		velocity_up 				= 0.0;

		timestamp 					= Instant.now().toEpochMilli();

		position_covariance_matrix 	= null;
		velocity_covariance_matrix 	= null;

		flag 						= 0;
		hdop 						= 0.0;
		integrity_indicator 		= 0.0;
		message_id 					= 0;
		nb_sats 					= 0;
	}
	public RawPosition(PositionInterface _p) {
		this(_p.getLongitude(), _p.getLatitude(), _p.getAltitude());
		heading 		= -1;

		velocity_front 	= -1;
		velocity_right 	= -1;
		velocity_up 	= -1;

		timestamp 		= -1;
	}
	public RawPosition(OrientedPositionInterface _p) {
		this((PositionInterface) _p);
		heading = _p.getHeading();
	}
	public RawPosition(DynamicPositionInterface _p) {
		this((OrientedPositionInterface) _p);
		velocity_front 	= _p.getVelocityFront();
		velocity_right 	= _p.getVelocityRight();
		velocity_up 	= _p.getVelocityUp();

		timestamp 		= _p.getTimestamp();
	}
	public RawPosition(GeoCoordinate _gc) {
		this(_gc.getLongitude(), _gc.getLatitude(), _gc.getAltitude());
	}
	public RawPosition(RawPosition _p) {
		this((DynamicPositionInterface) _p);
		position_covariance_matrix 	= _p.getPositionCovarianceMatrix();
		velocity_covariance_matrix 	= _p.getVelocityCovarianceMatrix();

		flag 						= _p.getFlag();
		hdop 						= _p.getHdop();
		integrity_indicator 		= _p.getIntegrityIndicator();
		message_id 					= _p.getMessageId();
		nb_sats 					= _p.getNbSatellites();
	}

	@Override
	public int getHeading() {
		return heading;
	}
	public void setHeading(int _heading) {
		heading = _heading;
	}

	@Override
	public double getVelocityFront() {
		return velocity_front;
	}
	@Override
	public double getVelocityRight() {
		return velocity_right;
	}
	@Override
	public double getVelocityUp() {
		return velocity_up;
	}
	public void setVelocityFront(double _vel) {
		velocity_front = _vel;
	}
	public void setVelocityRight(double _vel) {
		velocity_right = _vel;
	}
	public void setVelocityUp(double _vel) {
		velocity_up = _vel;
	}

	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long _timestamp) {
		timestamp = _timestamp;
	}

	public double[] getPositionCovarianceMatrix() {
		return position_covariance_matrix;
	}
	public double[] getVelocityCovarianceMatrix() {
		return velocity_covariance_matrix;
	}
	public void setPositionCovarianceMatrix(double[] _cov) {
		position_covariance_matrix = _cov;
	}
	public void setVelocityCovarianceMatrix(double[] _cov) {
		velocity_covariance_matrix = _cov;
	}

	public int getFlag() {
		return flag;
	}
	public void setFlag(int _flag) {
		flag = _flag;
	}

	public double getHdop() {
		return hdop;
	}
	public void setHdop(double _hdop) {
		hdop = _hdop;
	}

	public double getIntegrityIndicator() {
		return integrity_indicator;
	}
	public void setIntegrityIndicator(double _integrity_indicator) {
		integrity_indicator = _integrity_indicator;
	}

	public int getMessageId() {
		return message_id;
	}
	public void setMessageId(int _message_id) {
		message_id = _message_id;
	}

	public int getNbSatellites() {
		return nb_sats;
	}
	public void setNbSatellites(int _nb_sats) {
		nb_sats = _nb_sats;
	}

	public boolean equalsTo(RawPosition _p) {
		if(getLongitude() != _p.getLongitude())
			return false;
		if(getLatitude() != _p.getLatitude())
			return false;
		return true;
	}
	
	@Override
	public String toString() { 
		String ret = new String();

		DecimalFormat coord = new DecimalFormat("0.000000");
		
		ret += "long.: " + coord.format(getLongitude()) + ", lat.: " + coord.format(getLatitude());
		
		return ret;
	}

}
