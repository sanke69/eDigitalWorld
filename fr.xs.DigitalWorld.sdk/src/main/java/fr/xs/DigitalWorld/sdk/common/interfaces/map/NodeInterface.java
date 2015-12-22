package fr.xs.DigitalWorld.sdk.common.interfaces.map;

import fr.xs.DigitalWorld.sdk.common.interfaces.position.PositionInterface;
import fr.xs.DigitalWorld.sdk.common.types.coordinates.GeoCoordinate;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.NodeType;
import fr.xs.jtk.tools.UUID;

public interface NodeInterface extends PositionInterface {

	public UUID          getId();
	public GeoCoordinate getCoordinates();

	public NodeType		 getType();

}
