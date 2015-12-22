package fr.xs.DigitalWorld.sdk.tools.defaults.mapmatching;

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
import fr.xs.jtk.tools.UUID;

public class MapMatchEngine {
	
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

	public MapMatchEngine() { ; }

	public MapMatchedPosition getMapMatchedPosition(PositionInterface _position, Collection<SegmentInterface> _elts) {
		List<MapMatchedResult> result = new ArrayList<MapMatchedResult>();

		MapMatchedPosition mmp = null;
		if(_elts != null) {
			for(SegmentInterface elt : _elts)
				result.add(getTheProjection(_position, elt));
			Collections.sort(result);
		} else
			return null;

		if(result.size() > 0) {
			mmp = new MapMatchedPosition(result.get(0).projection);
			mmp.setRoadElementId(result.get(0).id);
			mmp.setCurvilinearAbscissa(result.get(0).s);
		}

		return mmp;
	}

	private MapMatchedResult getTheProjection(PositionInterface _p, SegmentInterface _elt) {
		if(_p == null || _elt == null) return null;

		List<GeoCoordinate> 	SegmentList = _elt.getGeometry();
		CartesianCoordinate 	A   = null, 
								B   = null, 
								P   = GeodesicConverter.inst.getCartesianCoordinateFor(new GeoCoordinate(_p.getLongitude(), _p.getLatitude(), _p.getAltitude()));
		double   				eps = 10, // TODO:: Augmenter le champs
								dAB	= 0,
								dAH	= 0,
								dBH	= 0,
								dPH	= 0,
								dPA	= 0,
								dPB	= 0,
								S	= 0;
		MapMatchedResult		R	= this.new MapMatchedResult();

		R.distance = 1e6;
		if(SegmentList != null) {
			int SegCount = SegmentList.size();

			while(SegCount-- > 1) {
				//A = GeodesicConverter.inst.getCartesianCoordinateFor(new RawPosition(SegmentList[SegCount - 1]));
				//B = GeodesicConverter.inst.getCartesianCoordinateFor(new RawPosition(SegmentList[SegCount]));
				A = GeodesicConverter.inst.getCartesianCoordinateFor(SegmentList.get(SegCount - 1));
				B = GeodesicConverter.inst.getCartesianCoordinateFor(SegmentList.get(SegCount));

				double a  = (B.Y() - A.Y()) / (B.X() - A.X());
				double b  = A.Y() - a * A.X();

				double Hx = (1 / (a*a + 1)) * (P.X() + a * P.Y() - a * b);
				double Hy = a * Hx + b;
				double Hz = 0.0;

				dPH = Math.sqrt( Math.pow(P.X() - Hx, 2.0) + Math.pow(P.Y() - Hy, 2.0) );
				dAH = Math.sqrt( Math.pow(A.X() - Hx, 2.0) + Math.pow(A.Y() - Hy, 2.0) );
				dAB = Math.sqrt( Math.pow(A.X() - B.X(), 2.0) + Math.pow(A.Y() - B.Y(), 2.0) );

				if( !((Hx < Math.min(A.X(), B.X()) || Hx > Math.max(A.X(), B.X())) 
				    ||(Hy < Math.min(A.Y(), B.Y()) || Hy > Math.max(A.Y(), B.Y()))) ) {
		    		if(dPH < eps) { 
		    			eps = dPH;
		    			R.projection = new RawPosition(GeodesicConverter.inst.getGeoCoordinateFor(new CartesianCoordinate(Hx, Hy, Hz, null)));
		    			R.projection.setHeading(getHeadingFromNorth(A, B));
		    			R.distance   = dPH;
		    			R.s_inv 	 = S + dAB - dAH; // S + dBH
		    		}
				}
	    		S += dAB;
			}

		}

		R.id = _elt.getId();
		R.s  = S - R.s_inv;

		return R;
	}

	private int getHeadingFromNorth(CartesianCoordinate _a, CartesianCoordinate _b) {
//		double H = Math.sqrt( Math.pow(_P1.X() - _P0.X(), 2.0) + Math.pow(_P1.Y() - _P0.Y(), 2.0) );
		double A = _b.Y() - _a.Y();
		double O = _b.X() - _a.X();

		int teta = (int) (90 - 180 * Math.atan2(A, O) / Math.PI);

		return teta > 0 ? teta : 360 + teta;
	}

}

