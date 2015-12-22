package fr.xs.DigitalWorld.sdk.map;

import java.awt.Point;
import java.sql.ResultSet;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import fr.xs.DigitalWorld.sdk.common.interfaces.map.NodeInterface;
import fr.xs.DigitalWorld.sdk.common.interfaces.map.SegmentInterface;
import fr.xs.DigitalWorld.sdk.common.interfaces.position.PositionInterface;
import fr.xs.DigitalWorld.sdk.common.types.coordinates.GeoCoordinate;
import fr.xs.DigitalWorld.sdk.common.types.map.Area;
import fr.xs.DigitalWorld.sdk.common.types.map.Node;
import fr.xs.DigitalWorld.sdk.common.types.map.Segment;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.others.AdministrativeStatus;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads.Direction;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads.DrivingCondition;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads.RoadClass;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads.RoadCondition;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads.RoadDirection;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads.RoadFeatureType;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads.SlipCondition;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads.SpeedDistribution;
import fr.xs.DigitalWorld.sdk.common.types.map.objects.PointOfInterest;
import fr.xs.DigitalWorld.sdk.common.types.map.objects.RegionOfInterest;
import fr.xs.DigitalWorld.sdk.common.types.map.objects.Relief;
import fr.xs.DigitalWorld.sdk.common.types.map.objects.Road;
import fr.xs.DigitalWorld.sdk.common.types.map.objects.RoadElement;
import fr.xs.DigitalWorld.sdk.common.types.position.RawPosition;
import fr.xs.jtk.databases.DatabaseConnector;
import fr.xs.jtk.databases.DatabaseManager;
import fr.xs.jtk.databases.beans.DatabaseAccountBean;
import fr.xs.jtk.databases.beans.DatabaseBean;
import fr.xs.jtk.databases.beans.TableBean;
import fr.xs.jtk.helpers.TimeHelper;
import fr.xs.jtk.tools.UUID;

public class DigitalMap  {	
	// Accès Base de Données
	static final public String db_name           = "DigitalMap";
	// Accès Tables
	static final public String table_node        = "node";
	static final public String table_road        = "element";
	static final public String table_relief      = "relief";
	static final public String table_poi         = "poi";
	static final public String table_roi         = "roi";
	// Accès Tables Layer > 0
	static final public String table_speed       = "speed";
	// Accès table propriétés
	static final public String table_roadclass   = "roadclass";
	static final public String table_drvcond     = "drvcond";
	static final public String table_slipcond    = "slipcond";
	static final public String table_status      = "status";
	static final public String table_crosslvl    = "crosslvl";
	static final public String table_adminstatus = "adminstatus";

	private static DatabaseConnector conn     = null;
	private static DatabaseBean      database = null;
	private static TableBean         table    = null;

	public static boolean initialize(DatabaseAccountBean _user, DatabaseBean _database, TableBean _table) {
		database = _database;
		table    = _table;

		if(conn == null) {
			if( !conn.connectTo(_user) )
				System.err.println("Failed to connect to mySQL server");
		}

		DatabaseManager.useDatabase(conn, database);

		return false;
	}

	private static String convertToPoint(GeoCoordinate _geometry) {
		String geometry = "POINT(";
		geometry += _geometry.getLongitude() + " " + _geometry.getLatitude();
		geometry += ")";

		return geometry;
	}
	private static String convertToLineString(List<GeoCoordinate> _geometry) {
		String  geometry = "LINESTRING(";
		boolean first    = true;
		for(GeoCoordinate coord : _geometry)
			if(first) {
				geometry += coord.getLongitude() + " " + coord.getLatitude();
				first = false;
			} else
				geometry += ", " + coord.getLongitude() + " " + coord.getLatitude();
		geometry += ")";

		return geometry;
	}
	private static String convertToPolygon(GeoCoordinate... _geometry) {
		String        geometry = "POLYGON(";
		boolean       first    = true;
		GeoCoordinate last     = null;
		for(GeoCoordinate coord : _geometry)
			if(first) {
				geometry += coord.getLongitude() + " " + coord.getLatitude();
				first = false;
				last  = coord;
			} else
				geometry += ", " + coord.getLongitude() + " " + coord.getLatitude();
		geometry += ", " + last.getLongitude() + " " + last.getLatitude();
		geometry += ")";

		return geometry;
	}
	
	public boolean insertNode(NodeInterface _node, Instant _lastmodified) {
		return insertNode(_node.getId(), _node.getCoordinates(), _lastmodified);
	}
	public boolean insertNode(UUID _id, GeoCoordinate _coord, Instant _lastmodified) {
		String request	= "INSERT INTO " + table_node + " (id, coord, lastmodified)"
						+ " VALUES " 
						+ "(" + _id
						+ ", GeomFromText('" + convertToPoint(_coord) + ")')"
						+ ", '" + TimeHelper.format(_lastmodified, TimeHelper.sqlFormat) + "'"
						+ ");";

		try {
			return conn.update(request);
		} catch(Exception e) {
			return false;
		}
	}

	public boolean insertSegment(SegmentInterface _segment, Instant _lastmodified) {
		return insertSegment(_segment.getId(), _segment.getGeometry(), _lastmodified);
	}
	public boolean insertSegment(UUID _id, List<GeoCoordinate> _geometry, Instant _lastmodified) {		
		String request  = "INSERT INTO " + table_relief + " (id, geometry, lastmodified)"
						+ " VALUES " 
						+ "(" + _id
						+ ", GeomFromText('" + convertToLineString(_geometry) + "')"
						+ ", \"" + TimeHelper.format(_lastmodified, TimeHelper.sqlFormat) + "\""
						+ ");";

		try {
			return conn.update(request);
		} catch(Exception e) {
			return false;
		}
	}

	public boolean insertArea(Area _area, Instant _lastmodified) {
		return insertArea(_area.getId(), _area.getGeometry(), _lastmodified);
	}
	public boolean insertArea(UUID _id, List<GeoCoordinate> _geometry, Instant _lastmodified) {
		String request  = "INSERT INTO " + table_relief + " (id, geometry, lastmodified)"
						+ " VALUES " 
						+ "(" + _id
						+ ", GeomFromText('" + convertToLineString(_geometry) + "')"
						+ ", '" + TimeHelper.format(_lastmodified, TimeHelper.sqlFormat) + "'"
						+ ");";

		try {
			return conn.update(request);
		} catch(Exception e) {
			return false;
		}
	}

	public boolean insertRelief(Relief _relief, Instant _lastmodified) {
		return insertRelief(_relief.getId(), _relief.getName(), _relief.getNature(), _relief.getGeometry(), _lastmodified);
	}
	public boolean insertRelief(UUID _id, String _name, String _nature, List<GeoCoordinate> _geometry, Instant _lastmodified) {
		String request  = "INSERT INTO " + table_relief + " (id, name, nature, geometry, lastmodified)"
						+ " VALUES " 
						+ "(" + _id
						+ ", \"" + _name + "\""
						+ ", \"" + _nature + "\""
						+ ", GeomFromText('" + convertToLineString(_geometry) + "')"
						+ ", \"" + TimeHelper.format(_lastmodified, TimeHelper.sqlFormat) + "\""
						+ ");";

		try {
			return conn.update(request);
		} catch(Exception e) {
			return false;
		}
	}

	public boolean insertPOI(long _id, String _name, String _nature, String _coord, String _value, String _type, String _resource, String _lastmodified) {
		String request
			= "INSERT INTO " + table_poi + " (id, name, nature, coord, value, type, resource, lastmodified)"
			+ " VALUES " 
			+ "(" + _id
			+ ", '" + _name + "'"
			+ ", '" + _nature + "'"
			+ ", GeomFromText(' LINESTRING(" + _coord + ")')"
			+ ", '" + _value + "'"
			+ ", '" + _type + "'"
			+ ", '" + _resource + "'"
			+ ", '" + _lastmodified + "'"
			+ ");";

		try {
			return conn.update(request);
		} catch(Exception e) {
			return false;
		}
	}

	public boolean insertROI(UUID _id, String _name, String _nature, String _geometry, String _lastmodified) {
		String request
			= "INSERT INTO " + table_roi + " (id, name, nature, geometry, lastmodified)"
			+ " VALUES " 
			+ "(" + _id
			+ ", '" + _name + "'"
			+ ", '" + _nature + "'"
			+ ", GeomFromText(' LINESTRING(" + _geometry + ")')"
			+ ", '" + _lastmodified + "'"
			+ ");";

		try {
			return conn.update(request);
		} catch(Exception e) {
			return false;
		}
	}

	public boolean insertRoadElement(RoadElement _element, String _lastmodified) {
		return insertRoadElement(_element.getId(), _element.getFromNode(), _element.getToNode(), _element.getGeometry(), _element.getDirection(),
									_element.getWidth(), _element.getLength(), _element.getLaneNumber(), _element.getName(), _element.getMandatorySpeedDistribution(),
									_element.getName4RightSide(), _element.getName4LeftSide(), _element.getItineraryName(), _element.getInternationalName(),
									_element.getFunctionnalRoadClass(), _element.getDrivingCondition(), _element.getSlipRoad(), _element.getRoadCondition(),
									_element.getCrossing(), _element.getCrossingLevel(),
									_element.getAdministrativeStatus(), _element.getInServiceDate());
	}
	public boolean insertRoadElement(UUID _id, Node _from, Node _to, List<GeoCoordinate>	_geometry, Direction _direction,
									double _width, double _length, int _lanes, String _StdName, SpeedDistribution _mandatorySpeedLimit,
									String _RightName, String _LeftName, String _ItiName, String _InterName,
									RoadClass _frclass, DrivingCondition _drivingcondition, SlipCondition _sliproad, RoadCondition _rcondition,
									RoadFeatureType _crossing, int _crossingLvl,
									AdministrativeStatus _adminstatus, Date _inservice) {
		String request 	= "INSERT INTO " + table_road + " (id, name, nature, ways, ref, geometry, roadclass, drvcond, slipcond, status, crosslvl, adminstatus, common, nodes, lastmodified)"
						+ " VALUES " 
						+ "(" + _id
//						+ ", '" + _name + "'"
//						+ ", '" + _nature + "'"
//						+ ", '" + _ways + "'"
//						+ ", '" + _ref + "'"
						+ ", GeomFromText('" + convertToLineString(_geometry) + "')"
//						+ ", '" + _roadclass + "'"
//						+ ", '" + _drvcond + "'"
//						+ ", '" + _slipcond + "'"
//						+ ", '" + _status + "'"
//						+ ", '" + _crosslvl + "'"
//						+ ", '" + _adminstatus + "'"
//						+ ", '" + _common + "'"
//						+ ", '" + _nodes + "'"
//						+ ", '" + _lastmodified + "'"
						+ ");";

		try {
			return conn.update(request);
		} catch(Exception e) {
			return false;
		}
	}


	public List<Node> getNodeIn(Point _center, double _range) {
		double  latmin = _center.y - _range,
				latmax = _center.y + _range,
				lonmin = _center.x - _range,
				lonmax = _center.x + _range;

		String attributes  = table_node + ".id" + "," + "AsText(" + table_node + ".coord)";
		String clause      = "MBRIntersects(GeomFromText('" 
						   + convertToPolygon(	new GeoCoordinate(lonmin, latmin),
												new GeoCoordinate(lonmax, latmin),
												new GeoCoordinate(lonmax, latmax),
												new GeoCoordinate(lonmin, latmax)  )
						   + "'),geometry)";

		List<Node> result  = new ArrayList<Node>(); 
		try {
			ResultSet res = conn.query("SELECT " + attributes + " FROM " + table_node +  " WHERE " + clause);
			res.beforeFirst();
			while(res.next()) {
				UUID  id    = new UUID(res.getString(1));
				String coord = res.getString(2);

				System.out.println("node: id=" + id + ", coord=" + coord + "\n");

				double lon = Double.parseDouble(coord),
				       lat = Double.parseDouble(coord.substring(coord.indexOf(" ")));
				result.add(new Node(id, new GeoCoordinate(lon, lat)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	   	
		System.out.println("nbre elements trouvés " + result.size());	
		return result;
	}

	public List<Segment> getSegmentIn(Point _center, double _range) {
		double  latmin = _center.y - _range,
				latmax = _center.y + _range,
				lonmin = _center.x - _range,
				lonmax = _center.x + _range;

		String attributes  = table_node + ".id" + "," + "AsText(" + table_node + ".coord)";
		String clause      = "MBRIntersects(GeomFromText('" 
						   + convertToPolygon(	new GeoCoordinate(lonmin, latmin),
												new GeoCoordinate(lonmax, latmin),
												new GeoCoordinate(lonmax, latmax),
												new GeoCoordinate(lonmin, latmax)  )
						   + "'),geometry)";

		List<Segment> result  = new ArrayList<Segment>(); 
		try {
			ResultSet res = conn.query("SELECT " + attributes + " FROM " + table_node +  " WHERE " + clause);
			res.beforeFirst();
			while(res.next()) {
				UUID   id    = new UUID(res.getString(1));
				String coord = res.getString(2);

				System.out.println("segment "            + id        + ":\n"
								 + "  + coord = "     + coord      + "\n");

				double lon = Double.parseDouble(coord),
				       lat = Double.parseDouble(coord.substring(coord.indexOf(" ")));
				
				List<GeoCoordinate> coords = new ArrayList<GeoCoordinate>();
				coords.add(new GeoCoordinate(lon, lat));

				result.add(new Segment(id, coords));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	   	
		System.out.println("nbre elements trouvés " + result.size());	
		return result;
	}
	
	
	
	
	
	
	/*
	
	public static RoadElement get(HashTag _hashtag) {
		String query = "SELECT " + "uuid, ipv4, ipv6, count, first, last" 
					 + " FROM " + table.getName()
					 + " WHERE " + "uuid='" + _hashtag.toString() + "';";

		if(conn == null) {
			System.out.println(query);
			return null;
		}

		ResultSet result = conn.query(query);

		RoadElement bean = new RoadElement();

		try {
			if(result != null && result.next()) {
				bean.setHashtag(new HashTag(result.getInt(1)))
					.setIPv4(result.getString(2))
					.setVisitCount(result.getInt(4))
					.setFirstVisit(TimeUtil.parse(result.getString(5), TimeUtil.sqlFormat))
					.setLastVisit(TimeUtil.parse(result.getString(6), TimeUtil.sqlFormat));
			}
		} catch(SQLException e) {
			bean = null;
		}

		return bean;
	}

	public static boolean insert(RoadElement _bean) {
		String request = "INSERT INTO " + table.getName() + "(uuid, ipv4, ipv6, count, first, last)"
					   + "VALUES ('" + _bean.getHashtag() + "', '" + _bean.getIPv4() + "', '" + _bean.getIPv6() + "', '" + _bean.getVisitCount() + "', '" + _bean.getFirstVisit() + "', '" + _bean.getLastVisit() + "');"; 

		if(conn == null) {
			System.out.println(request);
			return false;
		}

		try {
			return conn.update(request);
		} catch(Exception e) {
			return false;
		}
	}

	public static boolean update(RoadElement _bean) {
		String request = "UPDATE " + table.getName()
					   + "SET " + 
					   		"count=" + _bean.getVisitCount() + ", " +
					   		"last="  + _bean.getLastVisit()
					   + "WHERE " + "uuid="  + _bean.getHashtag() + ";"; 

		if(conn == null) {
			System.out.println(request);
			return false;
		}

		try {
			return conn.update(request);
		} catch(Exception e) {
			return false;
		}
	}

	
	*/
	
	
	
	
	
	
	public List<Relief> GetReliefIn(Point _center, double _range) {
		String    attributes;
		String    table;
		String    clause;

		ResultSet res      = null;
		String    id       = "";
		String    name     = "";
		String    nature   = "";
		String    geometry = "";
		
		List<Relief>
			getReliefIn = new ArrayList<Relief>(); 

		//r�cup�ration de tous les  segments
		attributes = table_road + ".id,"
		           + table_road + ".name,"
		           + table_road + ".nature,"
		           + "AsText(" + table_road + ".geometry))";
		table      = table_road;

		double latmin = _center.y - _range;
		double latmax = _center.y + _range;
		double lonmin = _center.x - _range;
		double lonmax = _center.x + _range;
		clause = "MBRIntersects(GeomFromText('POLYGON((" + 
				 latmin + " " + lonmin + "," + 
				 latmin + " " + lonmax + "," + 
				 latmax + " " + lonmax + "," +
				 latmax + " " + lonmin + "," + 
				 latmin + " " + lonmin + "))'),geometry)";

		try {
//			ldm.sql().executeQuery("SELECT " + attributes + " FROM " + table +  " WHERE " + clause);
//			res = ldm.sql().getResultSet();
			res.beforeFirst();
			while(res.next()) {
				id       = res.getString(1);
				name     = res.getString(2);
				nature   = res.getString(3);
				geometry = res.getString(4);

				System.out.println("segment "         + id        + ":\n"
								 + "  + name = "      + name      + "\n"
								 + "  + nature = "    + nature    + "\n"
								 + "  + geometry = "  + geometry  + "\n");

// while		double lon = Double.parseDouble(geometry),
// geometry            lat = Double.parseDouble(geometry.substring(geometry.indexOf(" ")));
				getReliefIn.add(new Relief(new UUID(id), name, nature, null));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	   	
		System.out.println("nbre elements trouv�s " + getReliefIn.size());	
		return getReliefIn;
	}
	public List<Road>    GetRoadElementIn(Point _center, double _range) {
		String    attributes;
		String    table;
		String    clause;

		ResultSet res           = null;
		String    id            = "";
		String 	  name          = "";
		String    nature        = "";
		String    ways          = "";
		String    ref           = "";
		String    geometry      = "";
		String    roadclass     = "";
		String    drvcond       = "";
		String    slipcond      = "";
		String    status        = "";
		String    crosslvl      = "";
		String    adminstatus	= "";
		String    common        = "";
		String    nodes         = "";

		List<Road>
			getRoadElementIn = new ArrayList<Road>(); 

		//r�cup�ration de tous les  segments
		attributes = table_road + ".id,"
				   + table_road + ".name,"
				   + table_road + ".nature,"
				   + table_road + ".ways,"
				   + table_road + ".ref,"
			       + "AsText(" + table_road + ".geometry)),"
				   + table_road + ".roadclass,"
				   + table_road + ".drvcond,"
				   + table_road + ".slipcond,"
				   + table_road + ".status,"
				   + table_road + ".crosslvl,"
				   + table_road + ".adminstatus,"
				   + table_road + ".common,"
				   + table_road + ".nodes";
		table      = table_road;

		double latmin = _center.y - _range;
		double latmax = _center.y + _range;
		double lonmin = _center.x - _range;
		double lonmax = _center.x + _range;
		clause = "MBRIntersects(GeomFromText('POLYGON((" + 
				 latmin + " " + lonmin + "," + 
				 latmin + " " + lonmax + "," + 
				 latmax + " " + lonmax + "," +
				 latmax + " " + lonmin + "," + 
				 latmin + " " + lonmin + "))'),geometry)";

		try {
//			ldm.sql().executeQuery("SELECT " + attributes + " FROM " + table +  " WHERE " + clause);
//			res = ldm.sql().getResultSet();
			res.beforeFirst();
			while(res.next()) {
				id          = res.getString(1);
				name        = res.getString(2);
				nature      = res.getString(3);
				ways        = res.getString(4);
				ref         = res.getString(5);
				geometry    = res.getString(6);
				roadclass   = res.getString(7);
				drvcond     = res.getString(8);
				slipcond    = res.getString(9);
				status      = res.getString(10);
				crosslvl    = res.getString(11);
				adminstatus	= res.getString(12);
				common      = res.getString(13);
				nodes       = res.getString(14);

				System.out.println("roadway"            + id          + ":\n"
								 + "  + name = "        + name        + "\n"
								 + "  + nature = "      + nature      + "\n"
								 + "  + ways = "        + ways        + "\n"
								 + "  + ref = "         + ref         + "\n"
								 + "  + geometry = "    + geometry    + "\n"
								 + "  + roadclass = "   + roadclass   + "\n"
								 + "  + drvcond = "     + drvcond     + "\n"
								 + "  + slipcond = "    + slipcond    + "\n"
								 + "  + status = "      + status      + "\n"
								 + "  + crosslvl = "    + crosslvl    + "\n"
								 + "  + adminstatus = " + adminstatus + "\n"
								 + "  + common = "      + common      + "\n"
								 + "  + nodes = "       + nodes       + "\n");
				
				int                  iid          = Integer.parseInt(id);
				RoadDirection        iway         = null;
				List<GeoCoordinate>       igeometry    = null;
// while		double lon = Double.parseDouble(geometry),
// geometry            lat = Double.parseDouble(geometry.substring(geometry.indexOf(" ")));
				RoadClass            iroadclass   = null;
				DrivingCondition     idrvcond     = null;
				SlipCondition        islipcond    = null;
				RoadCondition 	     istatus      = null;
				RoadFeatureType        icrosslvl    = null;
				AdministrativeStatus iadminstatus = null;
				List<Integer> 	     icommon      = null;
				List<Integer> 	     inodes_id    = null;

				getRoadElementIn.add(new Road(iid,
											    name,
											    nature,
											    iway,
											    ref,
											    igeometry,
											    iroadclass,
											    idrvcond,
											    islipcond,
											    istatus,
											    icrosslvl,
											    iadminstatus,
											    icommon,
											    inodes_id));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	   	
		System.out.println("nbre elements trouv�s " + getRoadElementIn.size());	
		return getRoadElementIn;
	}

	public synchronized Collection<SegmentInterface> getRoadElementsIn(PositionInterface _beg, PositionInterface _end) {
		String table        = "roadelement";
		String attributes   = "id,geom,length,name,lanes,routenum,fc,f_jnctid,t_jnctid,df,fw,sliprd";
		String where_clause = "intersects(GeometryFromText('POLYGON((" + 
								_beg.getLongitude() + " " + 
								_beg.getLatitude()  + "," + 
								_end.getLongitude() + " " + 
								_end.getLatitude()  + "))',4632),GEOM)";

		try {
			/*
			if(this.m_ldmInstance.query(table, attributes, where_clause)) {
				this.m_ldmInstance.first();
				do {
					String ID     = this.m_ldmInstance.value(0);
					String GEOM   = this.m_ldmInstance.value(1);
					String LENGTH = this.m_ldmInstance.value(2);
					String NAME   = this.m_ldmInstance.value(3);
					String LANES  = this.m_ldmInstance.value(4);
					String NAMEID = this.m_ldmInstance.value(5);
					String FC     = this.m_ldmInstance.value(6);
					String FROM   = this.m_ldmInstance.value(7);
					String TO     = this.m_ldmInstance.value(8);
					String DF     = this.m_ldmInstance.value(9);
					String FW     = this.m_ldmInstance.value(10);
					String SLIPRD = this.m_ldmInstance.value(11);

					RoadElement elt = new RoadElement();
					elt.setID(ID);
					elt.setGeometry(extractGeometry(GEOM));
					elt.setLength(Double.valueOf(LENGTH));
					elt.setName(NAME);
					elt.setLaneNumber(Integer.valueOf(LANES));
					elt.setInternationalName(NAMEID);
					elt.setFromNode(new RoadNode(FROM));
					elt.setToNode(new RoadNode(TO));
					elt.setDirection(IRoadElement.Direction.getDirectionForNavteq(DF));
					elt.setFunctionnalRoadClass(IRoadElement.FunctionnalRoadClass.getFunctionnalRoadClassForNavTeq(Integer.valueOf(FC)));
					elt.setDrivingCondition(IRoadElement.DrivingCondition.getFormOfWayForNavTeq(Integer.valueOf(FW)));
					elt.setSlipRoad(IRoadElement.SlipCondition.getSlipRoadForNavTeq(Integer.valueOf(SLIPRD)));
					
					m_Elements.put(elt.getID(), elt);
				} while(this.m_ldmInstance.next());
			}
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public synchronized Collection<SegmentInterface> getRoadNodesIn(PositionInterface _beg, PositionInterface _end) {
		String table        = "roadelement";
		String attributes   = "id,geom,length,name,lanes,routenum,fc,f_jnctid,t_jnctid,df,fw,sliprd";
//		String where_clause = "intersects(GeometryFromText('POLYGON((" + 
//								_beg.get_long() + " " + 
//								_beg.get_lat()  + "," + 
//								_end.get_long() + " " + 
//								_end.get_lat()  + "))',4632),GEOM)";
		String where_clause = "intersects(GeometryFromText('POLYGON((" + 
								_beg.getLongitude()  + " " + _beg.getLatitude() + "," + 
								_beg.getLongitude()  + " " + _end.getLatitude() + "," + 
								_end.getLongitude()  + " " + _end.getLatitude() + "," + 
								_end.getLongitude()  + " " + _beg.getLatitude() + "," + 
								_beg.getLongitude()  + " " + _beg.getLatitude() + "," + "))',4632),GEOM)";

		try {
			/*
			if(this.m_ldmInstance.query(table, attributes, where_clause)) {
				System.out.println("Areas count " + m_ldmInstance.size());
				this.m_ldmInstance.first();
				do {
					String ID     = this.m_ldmInstance.value(0);
					String GEOM   = this.m_ldmInstance.value(1);
					String LENGTH = this.m_ldmInstance.value(2);
					String NAME   = this.m_ldmInstance.value(3);
					String LANES  = this.m_ldmInstance.value(4);
					String NAMEID = this.m_ldmInstance.value(5);
					String FC     = this.m_ldmInstance.value(6);
					String FROM   = this.m_ldmInstance.value(7);
					String TO     = this.m_ldmInstance.value(8);
					String DF     = this.m_ldmInstance.value(9);
					String FW     = this.m_ldmInstance.value(10);
					String SLIPRD = this.m_ldmInstance.value(11);

					RoadElement elt = new RoadElement();
					elt.setID(ID);
					elt.setGeometry(extractGeometry(GEOM));
					elt.setLength(Double.valueOf(LENGTH));
					elt.setName(NAME);
					elt.setLaneNumber(Integer.valueOf(LANES));
					elt.setInternationalName(NAMEID);
					elt.setFromNode(new RoadNode(FROM));
					elt.setToNode(new RoadNode(TO));
					elt.setDirection(IRoadElement.Direction.getDirectionForNavteq(DF));
					elt.setFunctionnalRoadClass(IRoadElement.FunctionnalRoadClass.getFunctionnalRoadClassForNavTeq(Integer.valueOf(FC)));
					elt.setDrivingCondition(IRoadElement.DrivingCondition.getFormOfWayForNavTeq(Integer.valueOf(FW)));
					elt.setSlipRoad(IRoadElement.SlipCondition.getSlipRoadForNavTeq(Integer.valueOf(SLIPRD)));
					
					m_Elements.put(elt.getID(), elt);
				} while(this.m_ldmInstance.next());
			}
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public synchronized Collection<Object> getMapAreaIn(PositionInterface _beg, PositionInterface _end) {
		String table        = "arealandmarks";
		String attributes   = "id,geom,feattyp,road_element_id";
		String where_clause = "intersects(GeometryFromText('POLYGON((" + 
								_beg.getLongitude()  + " " + _beg.getLatitude() + "," + 
								_beg.getLongitude()  + " " + _end.getLatitude() + "," + 
								_end.getLongitude()  + " " + _end.getLatitude() + "," + 
								_end.getLongitude()  + " " + _beg.getLatitude() + "," + 
								_beg.getLongitude()  + " " + _beg.getLatitude() + "," + "))',4632),GEOM)";

		try {
			/*
			if(this.m_ldmInstance.query(table, attributes, where_clause)) {
				System.out.println("Areas count " + m_ldmInstance.size());
				this.m_ldmInstance.first();
				do {
					String ID     = this.m_ldmInstance.value(0);
					String GEOM   = this.m_ldmInstance.value(1);
					String FT     = this.m_ldmInstance.value(2);
					String CTID   = this.m_ldmInstance.value(3);

					MapArea area = new MapArea();
					area.setID(ID);
					area.setGeometry(extractGeometry(GEOM));
					area.setFeatureType(IMapArea.FeatureType.getFeatureTypeForNavteq(Integer.valueOf(FT)));
					area.setConnectedToID(CTID);

					m_Area.put(area.getID(), area);
				} while(this.m_ldmInstance.next());
			}
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<PointOfInterest>    GetPOIIn(Point _center, double _range) {
		String    attributes;
		String    table;
		String    clause;

		ResultSet res      = null;
		String    id       = "";
		String    name     = "";
		String    nature   = "";
        String    coord    = "";
        String    value    = "";
        String    type     = "";
        String    resource = "";
		
		List<PointOfInterest>
			getPOIIn = new ArrayList<PointOfInterest>(); 

		//r�cup�ration de tous les  segments
		attributes = table_node + ".id,"
			       + "AsText(" + table_node + ".coord))";
		table      = table_node;

		double latmin = _center.y - _range;
		double latmax = _center.y + _range;
		double lonmin = _center.x - _range;
		double lonmax = _center.x + _range;
		clause = "MBRIntersects(GeomFromText('POLYGON((" + 
				 latmin + " " + lonmin + "," + 
				 latmin + " " + lonmax + "," + 
				 latmax + " " + lonmax + "," +
				 latmax + " " + lonmin + "," + 
				 latmin + " " + lonmin + "))'),geometry)";

		try {
//			ldm.sql().executeQuery("SELECT " + attributes + " FROM " + table +  " WHERE " + clause);
//			res = ldm.sql().getResultSet();
			res.beforeFirst();
			while(res.next()) {
				id       = res.getString(1);
				name     = res.getString(2);
				nature   = res.getString(3);
		        coord    = res.getString(4);
		        value    = res.getString(5);
		        type     = res.getString(6);
		        resource = res.getString(7);

				System.out.println("poi "            + id        + ":\n"
								 + "  + name = "     + name      + "\n"
								 + "  + nature = "   + nature    + "\n"
								 + "  + coord = "    + coord     + "\n"
								 + "  + value = "    + value     + "\n"
								 + "  + type = "     + type      + "\n"
								 + "  + resource = " + resource  + "\n");
				
				int    iid  = Integer.parseInt(id);
				double lon = Double.parseDouble(coord),
				       lat = Double.parseDouble(coord.substring(coord.indexOf(" ")));
				//getPOIIn.add(new LdmPOI(iid, new LdmPoint(lon, lat)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	   	
		System.out.println("nbre elements trouvés " + getPOIIn.size());	
		return getPOIIn;
	}
	public List<RegionOfInterest>    GetROIIn(Point _center, double _range) {
		return null;
	}

	public void UpdateRelief(int _id, Relief _newdata) {
		String request = "";
/*
			= "UPDATE " + LDMParameter.table_relief 
			+ " SET name = \""     + _newdata.name + "\""
			+ ",nature = \""       + _newdata.nature + "\""
			+ ",geometry = \""     + _newdata.geometry + "\""
			+ ",lastmodified = \"" + _newdata.lastmodified + "\""
			+ " WHERE ( id = "     + _id + ");";
*/

		
	}
	public void UpdateRoadNode(int _id, Node _newdata) {
		;
	}
	public void UpdateRoadElement(int _id, Road _newdata) {
		;
	}
	public void UpdatePOI(int _id, PointOfInterest _newdata) {
		;
	}
	public void UpdateROI(int _id, RegionOfInterest _newdata) {
		;
	}

/*
	public void getRoadElementsIn(double[] _pt, double _rayon) {
		
		//Date eDate=new Date();
		String attributes;
		String table;
		String innerjoin;
		String on1;
		String on2;
		String clause;
		String order;
		ResultSet res;
		String ID = "";
		int i=0;
		double LATITUDE;
		double LONGITUDE;
		double SPEED;
		double ABSCURDEBUT;
		double ABSCURFIN;
		StringTokenizer tokenizer;
		
		//r�cup�ration de tous les  segments
		attributes   = "segment.id_seg,segment.name,segment.nature,segment.id_J1,segment.id_J2";
		table   = "segment";
		innerjoin   = "junction";
		on1   = "segment.id_J1=junction.id_j";
		on2  = "segment.id_J2=junction.id_j";
		double latmin=_pt[0]- _rayon;
		double latmax=_pt[0]+ _rayon;
		double lonmin=_pt[1]- _rayon;
		double lonmax=_pt[1]+ _rayon;
		clause = "MBRContains(GeomFromText('POLYGON((" + 
				 latmin + " " + lonmin + "," + 
				 latmin + " " + lonmax + "," + 
				 latmax + " " + lonmax + "," +
				 latmax + " " + lonmin + "," + 
				 latmin + " " + lonmin + "))'),latlon)";
		try {
			this.s.executeQuery("SELECT " + attributes + " FROM " + table + " INNER JOIN " + innerjoin + " ON " + on1 + " WHERE " + clause
			+ " UNION " + "SELECT " + attributes + " FROM " + table + " INNER JOIN " + innerjoin + " ON " + on2 + " WHERE " + clause);
			res =s.getResultSet();
			res.beforeFirst();
			while (res.next()) {
				ID     = res.getString(1);
				System.out.println("segment "+ID );	
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//r�cup�ration g�om�trie
		attributes   = "AsText(latlon)";
		table   = "geometry";
		clause = "(id_seg LIKE "+ ID + ")";
		order="id_geo";
				
		try{
			this.s.executeQuery("SELECT " + attributes + " FROM " + table + " WHERE " + clause+ " ORDER BY " + order);
			res =s.getResultSet();
			res.last();
			res.beforeFirst();
			while (res.next()) {
				String slatlon = res.getString(1);
				tokenizer = new StringTokenizer(slatlon);
				LATITUDE   = Double.valueOf(tokenizer.nextToken().substring(6));
				String titi=tokenizer.nextToken();
				System.out.println("t "+titi );
				String tito=titi.substring(0, titi.length()-1);
				System.out.println("t "+tito );
				LONGITUDE = Double.valueOf(titi.substring(0, titi.length()-1));
				System.out.println("lat "+LATITUDE + "lon " +LONGITUDE);
			} 
		
		} catch (Exception e) {
			e.printStackTrace();
		}
   
	   	
		System.out.println("nbre elements trouv�s "+i );	
		
	}

	public void getSegmentsCommuns(String _road) {
		//Date eDate=new Date();
		String attributes;
		String table;
		String clause;
		ResultSet res;
		String ID = "";
		int i=0;
		String firstGeo="";
		
		attributes   = "AsText(geometry)";
		table   = "segment";
		
		clause = "segment.id_seg LIKE " + _road;
		try {
			this.s.executeQuery("SELECT " + attributes + " FROM " + table +  " WHERE " + clause);
			res =s.getResultSet();
			res.beforeFirst();
			while (res.next()) {
				firstGeo=res.getString(1);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//r�cup�ration de tous les  segments
		attributes   = "segment.id_seg";
		table   = "segment";
		clause = "MBROverlaps(GeomFromText('" + firstGeo + "'),geometry)";
		try {
			this.s.executeQuery("SELECT " + attributes + " FROM " + table +  " WHERE " + clause);
			res =s.getResultSet();
			res.beforeFirst();
			while (res.next()) {
				ID     = res.getString(1);
				System.out.println("segment "+ID );	
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	   	
		System.out.println("nbre elements trouv�s "+i );	
		
	}
*/
	

	private PositionInterface[] extractGeometry(String _str) {
		PositionInterface[] geoms = null;
		double Long, Lat;
		int i = 0;

		if(_str.contains("LINESTRING")) {

			_str = _str.substring(_str.indexOf("LINESTRING(") + 11, _str.indexOf(")") - 1);

			StringTokenizer positions = new StringTokenizer(_str, ",");

			geoms = new RawPosition[positions.countTokens()];

			while(positions.hasMoreTokens()) {
				String			Coordinate = positions.nextToken();
				StringTokenizer coordinate = new StringTokenizer(Coordinate, " ");
				
				if(coordinate.countTokens() < 2) {
					int ipt = Coordinate.indexOf(".");

					String Longitude = Coordinate.substring(0, ipt + 7);
					String Latitude  = Coordinate.substring(ipt + 7, Coordinate.length());
					
					Long = Double.valueOf(Longitude);
					Lat  = Double.valueOf(Latitude);
				} else {
					Long = Double.valueOf(coordinate.nextToken());
					Lat  = Double.valueOf(coordinate.nextToken());
				}
				
				geoms[i++] = new RawPosition(Long, Lat, 0.0);
			}

		}
		
		return geoms;
	}

}
