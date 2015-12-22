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
import fr.xs.DigitalWorld.sdk.common.types.map.Area;
import fr.xs.jtk.tools.UUID;

public class Relief extends Area {
	private String name;
	private String nature;

	public Relief() {
		super();
		name   = "";
		nature = "";
	}
	public Relief(UUID _id, String _name, String _nature, List<GeoCoordinate> _geometry) {
		super(_id, _geometry);
		name   = _name;
		nature = _nature;
	}

	public String getName() {
		return name;
	}
	public void setName(String _name) {
		this.name = _name;
	}

	public String getNature() {
		return nature;
	}
	public void setNature(String _nature) {
		this.nature = _nature;
	}

}
