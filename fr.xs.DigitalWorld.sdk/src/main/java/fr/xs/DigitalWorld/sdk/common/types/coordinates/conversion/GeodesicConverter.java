/**
 * Copyright (C) 2007-2010 CVIS
 *
 * @file     GeodesicConverter.java
 * @version  1.0.0.0
 * @date     2007/01/15
 *  
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 * 
 * See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * implemented by LCPC/LIVIC
 */
package fr.xs.DigitalWorld.sdk.common.types.coordinates.conversion;

import fr.xs.DigitalWorld.sdk.common.types.coordinates.CartesianCoordinate;
import fr.xs.DigitalWorld.sdk.common.types.coordinates.GeoCoordinate;
import fr.xs.DigitalWorld.sdk.common.types.coordinates.PlaneCoordinate;

/**
 * colonnes du tableau des conversions autoris�es
 * ----------------------------------------------
 *
 * Abr�viations :
 *  - PLA = Coordonn�es planes d'une projection (X et Y en m�tre) - Lambert seulement ici
 *  - GEO = Coordonn�es g�ographiques d'un syst�me g�od�sique (lambda et phi en radian)
 *  - CAR = Coordonn�es cart�siennes d'un syst�me g�od�sique
 *
 * Sch�mas de conversion :
 *  1) Passage de coordonn�es cartographiques planes d'une projection d'un syst�me
 *     g�od�sique vers les coordonn�es d'une projection d'un autre syst�me g�od�sique
 *     - PLA_1 -> GEO_1 -> CAR_1 -> CAR_2 -> GEO_2 -> PLA_2
 *     - Entr�e/Sortie (E/S) : X_1 et Y_1 -> X_2 et Y_2 (en m�tres)
 *     - Exemple : Lambert_I (NTF) vers Lambert93 (RGF93)
 *
 *  2) Passage de coordonn�es cartographiques planes d *une projection d *un syst�me
 *     g�od�sique vers les coordonn�es g�ographiques d *un autre syst�me g�od�sique
 *     - PLA_1 -> GEO_1 -> CAR_1 -> CAR_2 -> GEO_2
 *     - E/S :  X_1 et Y_1 (en m�tres) -> lambda_2 et phi_2 (angles en radians avec phi = latitude et lambda = longitude)
 *     - Exemple : Lambert_I vers WGS84 (syst�me utilis� par les GPS)
 *
 *  3) Passage de coordonn�es cartographiques planes d'une projection vers coordonn�es
 *     cartographiques planes d'une autre projection d'un m�me syst�me g�od�sique
 *     - PLA_1 -> GEO -> PLA_2
 *     - E/S : X_1 et Y_1 -> X_2 et Y_2 (en m�tres)
 *     - Exemple : Lambert_I vers Lambert_II_�tendu
 *
 *  4) Passage de coordonn�es g�ographiques d'un syst�me g�od�sique vers
 *     les coordonn�es cartographiques planes d'une projection d'un autre syst�me g�od�sique
 *     - GEO_1 -> CAR_1 -> CAR_2 -> GEO_2 -> PLA_2
 *     - E/S : lambda_1 et phi_1 (en radian) -> X_2 et Y_2 (en m�tres)
 *     - Exemple : WGS84 vers Lambert93
 *
 *  5) Passage de coordonn�es g�ographiques d'un syst�me g�od�sique vers
 *     les coordonn�es g�ographiques d'un autre syst�me g�od�sique
 *     - GEO_1 -> CAR_1 -> CAR_2 -> GEO_2
 *     - E/S : lambda_1 et phi_1 -> lambda_2 et phi_2 (en radian)
 *     - Exemple : WGS84 vers ED50
 *
 * Les �tapes possibles sont donc :
 *  1) PLA -> GEO     Fonction Alg04 - Passage d'une proj. Lambert vers coordonn�es g�ographiques
 *  2) GEO1 -> GEO2   Fonction Alg09 - Passage de coordonn�es g�ographiques � des coord. cart�siennes
 *                    Fonction Alg13 - Transformation d'un syst�me g�od�sique � l'autre par coord. cart�siennes
 *                    Fonction Alg12 - Passage de coordonn�es cart�siennes � des coord. g�ographiques
 *  3) GEO -> PLA     Fonction Alg03 - Passage de coord. g�ographiques en projection conique conforme de Lambert
 *
 * Remarque :
 * - il est possible de d�finir d'autres transformations en utilisant un syst�me
 *   comme pivot si on ne connait pas la transformation directe
 *   CAR_1 -> CAR_1bis (PIVOT) -> CAR_2
 *
 */
//http://www.ign.fr/telechargement/MPro/geodesie/CIRCE/transfo.pdf
//http://www.ign.fr/telechargement/MPro/geodesie/CIRCE/NTG_80.pdf
//http://www.ign.fr/telechargement/MPro/geodesie/CIRCE/NTG_71.pdf
public class GeodesicConverter {

	/* Another way of defining a Singleton in java */
	static public final GeodesicConverter inst;
	static {
		inst = new GeodesicConverter();
	}
	private GeodesicConverter() {
		;
	}
	/**/

	public final static CartesianCoordinate convert2LambertIIe(GeoCoordinate _p) {
		double[] L2e = WGS84ToLambert2e(_p.getLongitude(), _p.getLatitude(), 0.0);
		return new CartesianCoordinate(L2e[0], L2e[1]);
	}
	public final static GeoCoordinate convert2RAWPosition(CartesianCoordinate _p) {
		double[] WGS84 = Lambert2eToWGS84(_p.X(), _p.Y(), _p.Z());
		return new GeoCoordinate(WGS84[0], WGS84[1], 0, null);
	}
	
	public final static CartesianCoordinate WGS84ToLambert2e(GeoCoordinate _p) {
		double[] L2e = WGS84ToLambert2e(_p.getLongitude(), _p.getLatitude(), 0.0);
		return new CartesianCoordinate(L2e[0], L2e[1]);
	}
	public final static GeoCoordinate Lambert2eToWGS84(CartesianCoordinate _p) {
		double[] WGS84 = Lambert2eToWGS84(_p.X(), _p.Y(), _p.Z());
		return new GeoCoordinate(WGS84[0], WGS84[1]);
	}

    public final static double[] WGS84ToLambert2e(double _Lg, double _Lt, double _He) {
        double lambda_wgs84, phi_wgs84;
        
        /**
         * 0) Conversion WGS84 : degree -> radian
         */
        lambda_wgs84 = _Lg * Math.PI/180;
        //if(_Lg_o == 'W') 
        //	lambda_wgs84 = -1 * lambda_wgs84;

        phi_wgs84 = _Lt * Math.PI/180;
        //if(_Lt_o == 'S') 
        //	phi_wgs84 = -1 * phi_wgs84;

        /**
         * 1) Conversion WGS84 : geographic (phi, lambda) -> cartesian (X, Y, Z)
         */
        double a_wgs84  = 6378137.0;
        double b_wgs84  = 6356752.314;
        double e2_wgs84 = (a_wgs84 * a_wgs84 - b_wgs84 * b_wgs84) / (a_wgs84 * a_wgs84);

        double N_wgs84 = a_wgs84 / Math.sqrt(1 - e2_wgs84 * Math.pow(Math.sin(phi_wgs84), 2));

        double X_wgs84 = N_wgs84 * Math.cos(phi_wgs84) * Math.cos(lambda_wgs84);
        double Y_wgs84 = N_wgs84 * Math.cos(phi_wgs84) * Math.sin(lambda_wgs84);
        double Z_wgs84 = N_wgs84 * (1-e2_wgs84) * Math.sin(phi_wgs84);
        
        /**
         * 2) Conversion : cartesian WGS84 (X, Y, Z) -> cartesian NTF (X, Y, Z)
         */
        double dX =   168.0;
        double dY =    60.0;
        double dZ = - 320.0;

        double X_ntf = X_wgs84 + dX;
        double Y_ntf = Y_wgs84 + dY;
        double Z_ntf = Z_wgs84 + dZ;
        
        /**
         * 3) Conversion :  cartesian NTF (X, Y, Z) -> geographic NTF (phi, lambda)
         */
        double a_ntf  = 6378249.2;
        double b_ntf  = 6356515.0;
        double e2_ntf = (a_ntf * a_ntf - b_ntf * b_ntf) / (a_ntf * a_ntf);

        double epsilon = 1e-9;
     
        double p0 = Math.atan(Z_ntf / Math.sqrt(X_ntf * X_ntf + Y_ntf * Y_ntf) * (1 - (a_ntf * e2_ntf) / (Math.sqrt(X_ntf * X_ntf + Y_ntf * Y_ntf + Z_ntf * Z_ntf))));
        double p1 = Math.atan((Z_ntf / Math.sqrt(X_ntf * X_ntf + Y_ntf * Y_ntf)) / (1 - (a_ntf * e2_ntf * Math.cos(p0)) / (Math.sqrt((X_ntf * X_ntf + Y_ntf * Y_ntf) * (1 - e2_ntf * Math.pow(Math.sin(p0), 2))))));
                
        while(!(Math.abs(p1 - p0) < epsilon)) {
            p0 = p1;
            p1 = Math.atan((Z_ntf / Math.sqrt(X_ntf * X_ntf + Y_ntf * Y_ntf))/(1 - (a_ntf * e2_ntf * Math.cos(p0)) / (Math.sqrt((X_ntf * X_ntf + Y_ntf * Y_ntf) * (1 - e2_ntf * Math.pow(Math.sin(p0), 2)))))); 
        }
        
        double phi_ntf = p1;
        double lambda_ntf = Math.atan(Y_ntf / X_ntf);
        
        /**
         * 4) Conversion :  geographic NTF (phi,lambda)  -> Lambert II �tendu (X_l2e, Y_l2e)
         */
        double n  = 0.7289686274;
        double c  = 11745793.39;
        double Xs = 600000.0;
        double Ys = 8199695.768;
            
        double e_n = Math.sqrt(e2_ntf);
        double lambda0 = 0.04079234433198;   //correspond � la longitude en radian de Paris (2�20'14.025" E) par rapport � Greenwich
        
        /** puis on calcule la latitude isom�trique */
        double L = Math.log(Math.tan(Math.PI / 4 + phi_ntf / 2) * Math.pow(((1 - e_n * Math.sin(phi_ntf)) / (1 + e_n * Math.sin(phi_ntf))), (e_n / 2)));

        /** Lambert II Etendu */
        double X_l2e = Xs + c * Math.exp((- n * L)) * Math.sin(n * (lambda_ntf - lambda0));
        double Y_l2e = Ys - c * Math.exp((- n * L)) * Math.cos(n * (lambda_ntf - lambda0));
        
        return new double[] { X_l2e, Y_l2e, 0 };
    }

    public static double[] Lambert2eToWGS84(double x, double y, double z) {    
        double epsilon = 1e-11;

        double n   = 0.7289686274;
        double c   = 11745793.39;
        double Xs  = 600000;
        double Ys  = 8199695.768;
        double LgC = 0.04079234433; // pour greenwich

        double e = 0.08248325676;   // ( premi�re excentricit� de l�ellipso�de Clarke 1880 fran�ais)

        double he = 100;            // l'Altitude
        double a  = 6378249.2;

        double Tx = -168;
        double Ty = -60;
        double Tz = +320;
        double D  = 0,
               Rx = 0,
               Ry = 0,
               Rz = 0;


        double[] Coords = new double[3];
        Coords = ALG0004(n, e, c, LgC, Xs, Ys, x, y, epsilon);
        Coords = ALG0009(Coords[0], Coords[1], he, a, e);
        Coords = ALG0013(Tx, Ty, Tz, D, Rx, Ry, Rz, Coords[0], Coords[1], Coords[2]);
        
        a = 6378137.0;    // ellipso�des WGS84
        double f = 1 / 298.257223563;
        double b = a * (1 - f);
        e = Math.sqrt((a*a - b*b) / (a*a));

        double X = Coords[0];
        double Y = Coords[1];
        double Z = Coords[2];
        Coords = ALG0012(X, Y, Z, e, a, epsilon);
        
        Coords[0] *= 180 / Math.PI;
        Coords[1] *= 180 / Math.PI;

        return new double[] { Coords[0], Coords[1], Coords[2] };
    }
    
    /// CALCUL DE LA LATITUDE ISOMETRIQUE
    private static double ALG0001(double _Lt, double _e) {
    	return Math.log( Math.tan(Math.PI / 4.0 + _Lt / 2.0) * Math.pow((1 - _e * Math.sin(_Lt)) / (1 + _e * Math.sin(_Lt)), _e / 2.0) );
    }
    
	/// CALCUL DE LA LATITUDE A PARTIR DE LA LATITUDE ISOMETRIQUE
    private static double ALG0002(double _Lt_iso, double _e, double _epsilon) {
    	double phi0 = 0;
    	double phi1 = 2 * Math.atan(Math.exp(_Lt_iso)) - (Math.PI / 2.0);
	
        do {
            phi0 = phi1;
            phi1 = 2 * Math.atan( (Math.pow( (1 + _e * Math.sin(phi0)) / (1 - _e * Math.sin(phi0) ) , _e / 2.0) * Math.exp(_Lt_iso)) ) - (Math.PI / 2.0);
        } while(Math.abs(phi1 - phi0) > _epsilon);
        
        return phi1;
    }
    
	/// TRANSFORMATION DE COORDONNEES
    private static double[] ALG0003(double _e, double _n, double _c, double _LgC, double _Xs, double _Ys, double _Lg, double _Lt) {
    	double Lt_iso = ALG0001(_Lt, _e);

    	double X = _Xs + _c * Math.exp(- _n * Lt_iso) * Math.sin(_n * (_Lg - _LgC));
    	double Y = _Ys - _c * Math.exp(- _n * Lt_iso) * Math.cos(_n * (_Lg - _LgC));

        return new double[] { X, Y };
    }

	/// TRANSFORMATION DE COORDONNEES
    private static double[] ALG0004(double _n, double _e, double _c, double _LgC, double _Xs, double _Ys, double _X, double _Y, double _epsilon) {
    	double R     = Math.sqrt(((_X - _Xs) * (_X - _Xs)) + ((_Y - _Ys) * (_Y - _Ys)));
        double Gamma = Math.atan((_X - _Xs) / (_Ys - _Y));

        double Lg      = _LgC + Gamma / _n;
        double Lt_iso  = (- 1 / _n) * Math.log(Math.abs(R / _c));
        double Lt      = ALG0002(Lt_iso, _e, _epsilon);

        return new double[] { Lg, Lt };
    }
    
    private static double[] ALG0009(double _Lg, double _Lt, double _He, double _a, double _e) { //_he := Altitude
        double N = ALG0021(_Lt, _e, _a);
        
        double X = (N + _He) * Math.cos(_Lt) * Math.cos(_Lg);
        double Y = (N + _He) * Math.cos(_Lt) * Math.sin(_Lg);
        double Z = (N * (1 - _e * _e) + _He) * Math.sin(_Lt);

        return new double[] { X, Y, Z };
    }
    
    private static double[] ALG0012(double _X, double _Y, double _Z, double _e, double _a, double _epsilon) {
        double Lg = Math.atan(_Y / _X);

        double P    = Math.sqrt(_X * _X + _Y * _Y);

        double phi0 = 0;
        double phi1 = Math.atan(_Z / (P * (1 - ( (_a * _e * _e)  / Math.sqrt(_X * _X + _Y * _Y + _Z * _Z) ) ) ) );

        do {
        	phi0 = phi1;
            double tmp =  1 / (1 - ( _a * _e * _e * Math.cos(phi0) / ( P * Math.sqrt(1 - _e * _e * Math.sin(phi0) * Math.sin(phi0)))));
            phi1 = Math.atan( tmp * _Z / P );
        } while (Math.abs(phi1 - phi0) >= _epsilon);

        double He = (P / Math.cos(phi1)) - (_a / Math.sqrt(1 - _e * _e * Math.sin(phi1) * Math.sin(phi1)));

        return new double[] { Lg, phi1, He };
    }
    
    private static double[] ALG0013(double _Tx, double _Ty, double _Tz, double _D, double _Rx, double _Ry, double _Rz, double _Ux, double _Uy, double _Uz) {
        double Vx = _Tx + _Ux * (1 + _D) + _Uz * _Ry - _Uy * _Rz;
        double Vy = _Ty + _Uy * (1 + _D) + _Ux * _Rz - _Uz * _Rx;
        double Vz = _Tz + _Uz * (1 + _D) + _Uy * _Rx - _Ux * _Ry;

        return new double[] { Vx, Vy, Vz };
    }
    
    private static double[] ALG0019(double _a, double _e, double _Lg0, double _Lt0, double _k0, double _X0, double _Y0) {
        double LgC = _Lg0;
        double n   = Math.sin(_Lt0);
        double C   = _k0 * ALG0021(_Lt0, _a, _e) * Math.tan(Math.PI / 2.0 - _Lt0) * Math.exp(n * ALG0001(_Lt0, _e));
        double Xs  = _X0;
        double Ys  = _Y0 + _k0 * ALG0021(_Lt0, _a, _e) * Math.tan(Math.PI / 2.0 - _Lt0) ;

        return new double[] { LgC, n, C, Xs, Ys };
    }

    private static double ALG0021(double _Lt, double _e, double _a) {
    	return _a / Math.sqrt(1 - _e * _e * Math.sin(_Lt) * Math.sin(_Lt)); // N
    }
    
    private static double[] ALG0054(double _Lg0, double _Lt0, double _X0, double _Y0, double _Lt1, double _Lt2, double _a, double _e) {
        double LgC = _Lg0;
        double n   = ( Math.log( ( ALG0021(_Lt2, _e, _a) * Math.cos(_Lt2) ) / ( ALG0021(_Lt1, _e, _a) * Math.cos(_Lt1) ) ) / ( ALG0001(_Lt1, _e) - ALG0001(_Lt2, _e) ) );
        double C   = ((ALG0021(_Lt1, _e, _a) * Math.cos(_Lt1)) / n) * Math.exp(n * ALG0001(_Lt1, _e));

        double Xs = _X0;
        double Ys = _Y0;

        if(_Lt0 == Math.PI / 2.0) {
            Xs = _X0;
            Ys = _Y0;
        } else {
            Xs = _X0;
            Ys = _Y0 + C * Math.exp(-1 * n * ALG0001(_Lt0, _e));
        }

        return new double[] { Xs, Ys, LgC, n, C };

    }

	public CartesianCoordinate getCartesianCoordinateFor(GeoCoordinate _p) {
		return GeodesicConverter.convert2LambertIIe(_p);
	}

	public CartesianCoordinate getCartesianCoordinateFor(PlaneCoordinate _p) {
		// TODO Auto-generated method stub
		return null;
	}

	public GeoCoordinate getGeoCoordinateFor(CartesianCoordinate _p) {
		return GeodesicConverter.convert2RAWPosition(_p);
	}

	public GeoCoordinate getGeoCoordinateFor(PlaneCoordinate _p) {
		// TODO Auto-generated method stub
		return null;
	}

	public PlaneCoordinate getPlaneCoordinateFor(GeoCoordinate _p) {
		// TODO Auto-generated method stub
		return null;
	}

	public PlaneCoordinate getPlaneCoordinateFor(CartesianCoordinate _p) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main2(String[] argv) {
		GeoCoordinate       geo = new GeoCoordinate(2.1, 48.8, 0, null);
		CartesianCoordinate car = GeodesicConverter.inst.getCartesianCoordinateFor(geo);
		
		System.out.println(geo + " -->> " + car);
		
		CartesianCoordinate cart = new CartesianCoordinate(car);
		GeoCoordinate       geog = GeodesicConverter.inst.getGeoCoordinateFor(cart);
		
		System.out.println(cart + " -->> " + geog);
	}
	
    public static void main(String[] args) {
    	// Battery 1 :
    	// WGS84 : Lg = 02.136819�W ( 002�08'12.5484" )    ==> Lambert II Extended : E(m) =  271480.709
    	//         Lt = 48.815356�N ( 048�48'55.2816" )    ==>                       N(m) = 2433461.075
    	double[] 		WGS84     = new double[] { 02.136819, 48.815356};
    	GeoCoordinate   WGS84_pos = new GeoCoordinate(WGS84[0], WGS84[1]);

    	double[] Result       = WGS84ToLambert2e(WGS84[0], WGS84[1], 0.0);
    	CartesianCoordinate Result_pos = WGS84ToLambert2e(WGS84_pos);

    	double[] Result2     = Lambert2eToWGS84(Result[0], Result[1], 0.0);
    	GeoCoordinate    Result2_pos = Lambert2eToWGS84(Result_pos);
    	
    	/**
    	System.out.println("WGS84toL2e E: " + WGS84[0] + " " + WGS84[1]);
    	System.out.println("WGS84toL2e S: " + Result[0] + " " + Result[1]);

    	System.out.println("L2etoWGS84 E: " + Result[0] + " " + Result[1]);
    	System.out.println("L2etoWGS84 S: " + Result2[0] + " " + Result2[0]);
    	/*/
    	System.out.println("WGS84toL2e E: " + WGS84_pos.getLongitude() + " " + WGS84_pos.getLatitude());
    	System.out.println("WGS84toL2e S: " + Result_pos.X() + " " + Result_pos.Y());

    	System.out.println("L2etoWGS84 E: " + Result_pos.X() + " " + Result_pos.Y());
    	System.out.println("L2etoWGS84 S: " + Result2_pos.getLongitude() + " " + Result2_pos.getLatitude());
    	/**/

    }

	public static double GPS2DecimalDegree(double _gps) {
		int    deg = (int) (_gps / 100.0f);
		double min = _gps - deg * 100.0f;
		double dec = deg + min / 60.0f;
		
		return dec;
	}
	public static String asDMS(double _gps) {
		int    deg = (int) (_gps / 100.0f);
		int    min = (int) Math.floor((_gps - deg * 100.0f) * 100);
		double sec = (_gps - deg * 100.0f - min) * 60;
		
		return deg + "°" + min + "'" + sec + "\"";
	}

}
