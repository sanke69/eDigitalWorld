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
package fr.xs.DigitalWorld.sdk.common.types.coordinates;

import fr.xs.DigitalWorld.sdk.common.interfaces.position.PositionInterface;
import fr.xs.DigitalWorld.sdk.common.types.coordinates.conversion.enums.AvailableSCG;

public class GeoCoordinate implements PositionInterface {
	protected double lambda, phi, height;
	private AvailableSCG scg;

	public GeoCoordinate() {
		lambda = 0.0f;
		phi    = 0.0f;
		height = 0.0f;
		scg    = null;
	}
	public GeoCoordinate(double _lambda, double _phi) {
		this();
		lambda = _lambda;
		phi    = _phi;
	}
	public GeoCoordinate(double _lambda, double _phi, double _height) {
		this(_lambda, _phi);
		height = _height;
	}
	public GeoCoordinate(double _lambda, double _phi, double _height, AvailableSCG _scg) {
		this(_lambda, _phi, _height);
		scg    = _scg;
	}
	public GeoCoordinate(GeoCoordinate _coord) {
		this(_coord.lambda, _coord.phi, _coord.height);
		scg    = _coord.scg;
	}

	public double Lambda() { return lambda; }
	public double Phi()    { return phi; }
	public double Height() { return height; }

	public void setCoordinate(double _lambda, double _phi) {
		lambda = _lambda;
		phi    = _phi;
	}
	public void setCoordinate(double _lambda, double _phi, double _height) {
		lambda = _lambda;
		phi    = _phi;
		height = _height;
		
	}

	public double getLongitude() {
		return lambda;
	}
	public void setLongitude(double _lambda) {
		lambda = _lambda;
	}

	public double getLatitude() {
		return phi;
	}
	public void setLatitude(double _phi) {
		phi = _phi;
	}

	public double getAltitude() {
		return height;
	}
	public void setAltitude(double _height) {
		height = _height;
	}

	public String toString() {
		return "(" + lambda + ", " + phi + ", " + height + ")";
	}

}
