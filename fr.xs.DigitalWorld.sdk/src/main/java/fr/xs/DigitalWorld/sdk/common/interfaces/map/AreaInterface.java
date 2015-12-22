package fr.xs.DigitalWorld.sdk.common.interfaces.map;

import java.util.List;

import fr.xs.DigitalWorld.sdk.common.types.coordinates.GeoCoordinate;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.AreaType;
import fr.xs.jtk.tools.UUID;

public interface AreaInterface {

	public UUID                	getId();
	public List<GeoCoordinate> 	getGeometry();

	public AreaType				getType();

}
