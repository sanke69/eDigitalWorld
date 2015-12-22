package fr.xs.DigitalWorld.sdk.tools.interfaces;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import fr.xs.DigitalWorld.sdk.common.interfaces.map.SegmentInterface;
import fr.xs.DigitalWorld.sdk.common.interfaces.position.PositionInterface;
import fr.xs.DigitalWorld.sdk.common.types.coordinates.CartesianCoordinate;
import fr.xs.DigitalWorld.sdk.common.types.coordinates.GeoCoordinate;
import fr.xs.DigitalWorld.sdk.common.types.coordinates.conversion.GeodesicConverter;
import fr.xs.DigitalWorld.sdk.common.types.position.MapMatchedPosition;
import fr.xs.DigitalWorld.sdk.common.types.position.RawPosition;
import fr.xs.DigitalWorld.sdk.tools.defaults.mapmatching.MapMatchEngine.MapMatchedResult;
import fr.xs.jtk.tools.UUID;

public interface MapMatchEngineInterface {

	public class MapMatchedResult implements Comparable<MapMatchedResult> {
		RawPosition 	projection;
		double      	distance;

		UUID			id;
		double      	s, s_inv;

		MapMatchedResult() {
			projection = new RawPosition();
			distance   = 1e6;
		}

		public int compareTo(MapMatchedResult o) {
			MapMatchedResult O = (MapMatchedResult) o;

			if(distance > O.distance) return 1;
			else if(distance < O.distance) return -1;
			else return 0;
		}
		
		public String toString() {
			String ret = new String();

			DecimalFormat dist = new DecimalFormat("0.000 m");
			
			ret = "[d: " + dist.format(distance) + "] " + projection.toString() + " " + id + " " + s;
			
			return ret;
		}

	}

	public MapMatchedPosition getMapMatchedPosition(PositionInterface _position, Collection<SegmentInterface> _elts);
//	public MapMatchedResult getTheProjection(PositionInterface _p, SegmentInterface _elt);
//	public int getHeadingFromNorth(CartesianCoordinate _a, CartesianCoordinate _b);

}

