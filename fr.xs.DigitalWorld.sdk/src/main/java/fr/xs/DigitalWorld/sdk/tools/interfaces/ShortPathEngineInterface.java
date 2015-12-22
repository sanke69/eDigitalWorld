package fr.xs.DigitalWorld.sdk.tools.interfaces;

import java.util.Collection;

import fr.xs.DigitalWorld.sdk.common.interfaces.map.SegmentInterface;
import fr.xs.DigitalWorld.sdk.common.interfaces.position.RoadPositionInterface;

public interface ShortPathEngineInterface {

	public boolean getStatus();
	public void    getElementList();
	public void    getPoundMatrix();

	public void    buildMapTree(Collection<SegmentInterface> _elts);
	public void    clearMapTree();

	public double  getDistanceBetween(RoadPositionInterface _p0, RoadPositionInterface _p1);
	public String  getWayBetween(RoadPositionInterface _p0, RoadPositionInterface _p1);

}
