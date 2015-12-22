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

import fr.xs.DigitalWorld.sdk.common.types.coordinates.conversion.enums.AvailableSCG;

public class CartesianCoordinate {
	protected double x, y, z;
	private AvailableSCG scg;

	public CartesianCoordinate() {
		x   = 0.0;
		y   = 0.0;
		z   = 0.0;
		scg = null;
	}
	public CartesianCoordinate(double _x, double _y) {
		this();
		x   = _x;
		y   = _y;
	}
	public CartesianCoordinate(double _x, double _y, double _z) {
		this(_x, _y);
		z   = _z;
	}
	public CartesianCoordinate(double _x, double _y, double _z, AvailableSCG _scg) {
		this(_x, _y, _z);
		scg = _scg;
	}
	public CartesianCoordinate(CartesianCoordinate _c) {
		this(_c.x, _c.y, _c.z, _c.scg);
	}

	public double X() {
		return x;
	}
	public double Y() {
		return y;
	}
	public double Z() {
		return z;
	}

	@Override
	public String toString() {
		return "{ " + x + ", " + y + ", " + z + " }";
	}
	
}
