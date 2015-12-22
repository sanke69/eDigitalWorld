package fr.xs.DigitalWorld.sdk.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.xs.DigitalWorld.sdk.common.interfaces.map.SegmentInterface;
import fr.xs.DigitalWorld.sdk.common.interfaces.position.RoadPositionInterface;
import fr.xs.DigitalWorld.sdk.common.types.coordinates.CartesianCoordinate;
import fr.xs.DigitalWorld.sdk.common.types.coordinates.GeoCoordinate;
import fr.xs.DigitalWorld.sdk.common.types.coordinates.conversion.GeodesicConverter;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads.Direction;
import fr.xs.DigitalWorld.sdk.common.types.map.objects.RoadElement;
import fr.xs.DigitalWorld.sdk.common.types.position.MapMatchedPosition;
import fr.xs.DigitalWorld.sdk.common.types.position.RawPosition;
import fr.xs.jtk.tools.UUID;

//import fr.xs.eCar.core.facilities.interfaces.DigitalMapProviderInterface;

public class MapToolBox {

//	static DigitalMapProviderInterface m_SSP;
	
	/* Another way of defining a Singleton in java */
	static public final MapToolBox inst;
	static {
		inst = new MapToolBox();
	}
	private MapToolBox() {
		;
	}
	/**/
	
	public Collection<RoadElement> buildOrientedRoadVector(RoadPositionInterface _p, double _r) {
		long    length = 0;
		boolean end    = false;

		Collection<Object> adjacent;

		UUID 		linkID	= _p.getRoadElementId();
		RoadElement	old		= null; //(RoadElement) m_SSP.getRoadElement(linkID);
		RoadElement	elt		= null; //(RoadElement) m_SSP.getRoadElement(linkID);

		ArrayList<RoadElement> omv = new ArrayList<RoadElement>();

		if(elt != null) {
			omv.add(elt);
			length += elt.getLength();

			boolean colin = getDrivingWay(_p, elt) == Direction.DIRECT ? true : false;// hasSameDirection(_p, elt);
			if(colin) adjacent = null; //m_SSP.getNextElements(elt);
			else 	  adjacent = null; //m_SSP.getPreviousElements(elt);

			if(adjacent == null)
				return omv;

			do {
				if(adjacent.size() != 1) {
					end = true;
				} else {
					old = elt;
					elt = (RoadElement) adjacent.toArray()[0];

					if(elt != null) {
						omv.add(elt);

						length += elt.getLength();
						
						try {
							if(hasSameOrientation(old, elt) == true && colin == true) {
								adjacent = null; //m_SSP.getNextElements(elt); //elt.getNexts();
							} else if(hasSameOrientation(old, elt) == true && colin == false) {
								adjacent = null; //m_SSP.getPreviousElements(elt); //elt.getPrevious();
							}else if(hasSameOrientation(old, elt) == false && colin == true) {
								adjacent = null; //m_SSP.getPreviousElements(elt); //elt.getPrevious();
							} else {
								adjacent = null; //m_SSP.getNextElements(elt); //elt.getNexts();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						end = true;
					}
				}
			} while(length < _r && end != true);
		}

		return omv;

	}
	public Collection<RoadElement> buildOrientedRoadTree(RoadPositionInterface _p, double _r) {
//		if(m_SSP == null)
//			throw new NullPointerException("SupportServiceProvider is not initialized !");

		UUID			linkID	= _p.getRoadElementId();
		RoadElement	elt		= (RoadElement) null; //m_SSP.getRoadElement(linkID);
		
		ArrayList<RoadElement> omv = new ArrayList<RoadElement>();

		if(elt != null) {
			omv.add(elt);
	
			Collection<RoadElement> buildMT = buildMapTree(elt, _r - elt.getLength(), getDrivingWay(_p, elt) == Direction.DIRECT ? true : false);//hasSameDirection(_p, elt));
			if(buildMT != null)
				omv.addAll(buildMT);
		}
		
		return omv;

	}
	public Collection<RoadElement> buildInfluenceArea(RoadPositionInterface _p, double _r) {
//		if(m_SSP == null)
//			throw new NullPointerException("SupportServiceProvider is not initialized !");

		UUID		linkID	= _p.getRoadElementId();
		RoadElement	elt		= null; //(RoadElement) m_SSP.getRoadElement(linkID);
		
		ArrayList<RoadElement> omv = new ArrayList<RoadElement>();

		if(elt != null) {
			omv.add(elt);
	
			Collection<RoadElement> buildMT = null;
			try {
				buildMT = buildMapTree(elt, _r - elt.getLength(), true);
				if(buildMT != null)
					omv.addAll(buildMT);
				buildMT = buildMapTree(elt, _r - elt.getLength(), false);
				if(buildMT != null)
					omv.addAll(buildMT);
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		return omv;

	}

	private Collection<RoadElement> buildMapTree(RoadElement _elt, double _r, boolean _withNext) {
		if(_elt == null || _r <= 0) return null;

		Collection<Object> adjacent;
		Collection<RoadElement> mt = new ArrayList<RoadElement>();

		if(_withNext)	adjacent = null; //m_SSP.getNextElements(_elt);
		else			adjacent = null; //m_SSP.getPreviousElements(_elt);

		double  range = _r - _elt.getLength();

		for(Object o : adjacent) {
			RoadElement elt = (RoadElement) o;
			
			if(!mt.contains(elt)) {
				mt.add(elt);
				if(range > 0) {
					Collection<RoadElement> buildMT = null;

					try {
						if(hasSameOrientation(_elt, elt) == true)
							buildMT = buildMapTree(elt, range, _withNext);
						else
							buildMT = buildMapTree(elt, range, !_withNext);
					} catch (Exception e) {
						e.printStackTrace();
					}

					if(buildMT != null)
						mt.addAll(buildMT);
				}
			}
		}

		return mt;

	}


	public boolean isInGeoArea(RawPosition _elt, Collection<GeoCoordinate> _area) {
		return false;
	}
	public boolean isInGeoArea(RoadPositionInterface _elt, Collection<GeoCoordinate> _area) {
		return false;
	}
	public boolean isInGeoArea(RoadElement _elt, Collection<GeoCoordinate> _area) {
		return false;
	}

	public boolean isInRoadArea(RawPosition _elt, Collection<RoadElement> _area) {
		return false;
	}
	public boolean isInRoadArea(RoadPositionInterface _elt, Collection<RoadElement> _area) {
		return false;
	}
	public boolean isInRoadArea(RoadElement _elt, Collection<RoadElement> _area) {
		return false;
	}

	public int getHeadingFromNorth(RawPosition _p0, RawPosition _p1) {
		CartesianCoordinate p0 = GeodesicConverter.inst.getCartesianCoordinateFor(new RawPosition(_p0));
		CartesianCoordinate p1 = GeodesicConverter.inst.getCartesianCoordinateFor(new RawPosition(_p1));
		
//		double H = Math.sqrt( Math.pow(_P1.X() - _P0.X(), 2.0) + Math.pow(_P1.Y() - _P0.Y(), 2.0) );
		double A = p1.Y() - p0.Y();
		double O = p1.X() - p0.X();
		
		int teta = (int) (90 - 180 * Math.atan2(A, O) / Math.PI);

		return teta;
	}
	public double[] getVelocity(RawPosition _p0, RawPosition _p1) {
		if(_p0 == null || _p1 == null)
			return new double[] { 0, 0, 0 };

		if((_p0.getLongitude() == _p1.getLongitude() && _p0.getLatitude() == _p1.getLatitude())) {
			return new double[] { _p0.getVelocityFront(), _p0.getVelocityRight(), _p0.getVelocityUp() };
		}		

		CartesianCoordinate p0 = GeodesicConverter.inst.getCartesianCoordinateFor(new RawPosition(_p0));
		CartesianCoordinate p1 = GeodesicConverter.inst.getCartesianCoordinateFor(new RawPosition(_p1));

		double ds = Math.sqrt( (p1.X()-p0.X())*(p1.X()-p0.X()) + (p1.Y()-p0.Y())*(p1.Y()-p0.Y()) );
		double dt = (_p1.getTimestamp() - _p0.getTimestamp()) / 1000;
		double v  = (ds / dt) * 3600;

		return new double[] { v, 0, 0 };
	}
	public double getDistanceBetween(RoadPositionInterface _p0, RoadPositionInterface _p1) {
		return 0;
	}

	public Direction getDrivingWay(RoadPositionInterface _p, SegmentInterface _e) {
		double[] P = getTheProjection(_p, _e);

		if(P != null) {
			int h_elt = (int) P[2] % 360;
			if(Math.abs(_p.getHeading() - h_elt) < 90)
				return Direction.DIRECT;
			else
				return Direction.INDIRECT;
		}

		return Direction.UNKNOWN;
	}

	public double[] getTheProjection(RoadPositionInterface _mmp, SegmentInterface _elt) {
		if(_mmp == null|| _elt == null)	return null;

		List<GeoCoordinate> SegmentList = _elt.getGeometry();
		CartesianCoordinate A = null, B = null, 
		P = GeodesicConverter.inst.getCartesianCoordinateFor(new MapMatchedPosition(_mmp));
		double   eps = 1e6;
		double[] R   = null;
		
		if(SegmentList != null) {
			int SegCount = SegmentList.size();

			while(SegCount-- > 1){
				A = GeodesicConverter.inst.getCartesianCoordinateFor(new RawPosition(SegmentList.get(SegCount - 1)));
				B = GeodesicConverter.inst.getCartesianCoordinateFor(new RawPosition(SegmentList.get(SegCount)));

				double dX = B.X() - A.X();
				double dY = B.Y() - A.Y();
				double a  = dY / dX;
				double b  = A.Y() - a * A.X();
//				double b2 = B.Y() - a * B.X();

				double Xh = (1 / (a*a + 1)) * (P.X() + a * P.Y() - a * b);
				double Yh = a * Xh + b;

				double d  = Math.sqrt( Math.pow(P.X() - Xh, 2.0) + Math.pow(P.Y() - Yh, 2.0) );

	    		if(d < eps) { 
	    			int heading = (int) (90 - 180 * Math.atan2(B.Y() - A.Y(), B.X() - A.X()) / Math.PI);
	    			eps = d;
	    			R   = new double[] { Xh, Yh, (double) heading, SegCount, d };
	    		}
			}
		}

		if(R != null)
			return R;
		else
			return new double[] { 0, 0, 0, -1, 1e7 };
	}

	public boolean hasSameOrientation(RoadElement _elt0, RoadElement _elt1) throws Exception {
		if(_elt1 == null) return true;

		List<GeoCoordinate> gd0 = _elt0.getGeometry();
		List<GeoCoordinate> gd1 = _elt1.getGeometry();

		if(   (gd0.get(0).getLongitude() == gd1.get(gd1.size() - 1).getLongitude() && gd0.get(0).getLatitude() == gd1.get(gd1.size() - 1).getLatitude())
		   || (gd0.get(gd0.size() - 1).getLongitude() == gd1.get(0).getLongitude() && gd0.get(gd0.size() - 1).getLatitude() == gd1.get(0).getLatitude())) {
			return true;
		} else if( (gd0.get(0).getLongitude() == gd1.get(0).getLongitude() && gd0.get(0).getLatitude() == gd1.get(0).getLatitude())
		   || (gd0.get(gd0.size() - 1).getLongitude() == gd1.get(gd1.size() - 1).getLongitude() && gd0.get(gd0.size() - 1).getLatitude() == gd1.get(gd1.size() - 1).getLatitude())) {
			return false;
		}

		throw new Exception("Elements are not adjacents");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public RoadPositionInterface getHead(RoadPositionInterface _p0, RoadPositionInterface _p1) {
		int cap  = _p0.getHeading();
		int cap2 = _p1.getHeading();
		if(Math.abs(cap - cap2) > 90) return null;
		
		RoadElement elt0 = null; //(RoadElement) m_SSP.getRoadElement(_p0.get_road_element_id());

		if(_p0.getRoadElementId() == _p1.getRoadElementId()) {
			if(getDrivingWay(_p0, elt0) == Direction.DIRECT)
				return _p0.getCurvilinearAbscissa() < _p1.getCurvilinearAbscissa() ? _p1 : _p0;
			else
				return _p0.getCurvilinearAbscissa() < _p1.getCurvilinearAbscissa() ? _p0 : _p1;
		} else {
			try {
				//Collection<IRoadElement> p = m_SSP.getPreviousElements(elt0);
				Collection<RoadElement> p = buildMapTree(elt0, 500, false);
				for(RoadElement e : p)
					if(_p1.getRoadElementId() == e.getId())
						return _p0;

				//Collection<IRoadElement> n = m_SSP.getNextElements(elt0);
				Collection<RoadElement> n = buildMapTree(elt0, 500, true);
				for(RoadElement e : n)
					if(_p1.getRoadElementId() == e.getId())
						return _p1;

			} catch (Exception e1) {
				e1.printStackTrace();
				return null;
			}
			
		}

		return null;
	}

	public RoadPositionInterface getTail(RoadPositionInterface _p0, RoadPositionInterface _p1) {
		int cap  = _p0.getHeading();
		int cap2 = _p1.getHeading();
		if(Math.abs(cap - cap2) > 90) return null;
		
		RoadElement elt0 = null; //(RoadElement) m_SSP.getRoadElement(_p0.get_road_element_id());

		if(_p0.getRoadElementId() == _p1.getRoadElementId()) {
			if(getDrivingWay(_p0, elt0) == Direction.DIRECT)
				return _p0.getCurvilinearAbscissa() > _p1.getCurvilinearAbscissa() ? _p1 : _p0;
			else
				return _p0.getCurvilinearAbscissa() > _p1.getCurvilinearAbscissa() ? _p0 : _p1;
		} else {
			try {
				//Collection<IRoadElement> p = m_SSP.getPreviousElements(elt0);
				Collection<RoadElement> p = buildMapTree(elt0, 500, false);
				for(RoadElement e : p)
					if(_p1.getRoadElementId() == e.getId())
						return _p1;

				//Collection<IRoadElement> n = m_SSP.getNextElements(elt0);
				Collection<RoadElement> n = buildMapTree(elt0, 500, true);
				for(RoadElement e : n)
					if(_p1.getRoadElementId() == e.getId())
						return _p0;

			} catch (Exception e1) {
				e1.printStackTrace();
				return null;
			}
			
		}

		return null;
	}

}
