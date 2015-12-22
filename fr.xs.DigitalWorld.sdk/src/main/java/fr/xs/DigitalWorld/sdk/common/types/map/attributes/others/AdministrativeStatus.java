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
package fr.xs.DigitalWorld.sdk.common.types.map.attributes.others;

public enum AdministrativeStatus {
	Public	 (1, "Public"),
	Private	 (2, "Privé");

	private final int    value;
	private final String description;

    private AdministrativeStatus(int _value, String _desc) {
    	value       = _value;
    	description = _desc;
    }

    public int getValue() {
        return this.value;
    }
	public String getDescription() {
        return this.description;
    }

    static public AdministrativeStatus valueOfOther(String _label) {
    	switch(_label) {
    	case "Nationale" : 	return Public;
    	default :			return Private;			
    	}
    }
   
}