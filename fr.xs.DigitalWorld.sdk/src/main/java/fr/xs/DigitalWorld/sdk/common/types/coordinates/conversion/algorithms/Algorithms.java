package fr.xs.DigitalWorld.sdk.common.types.coordinates.conversion.algorithms;

public class Algorithms {

    /// CALCUL DE LA LATITUDE ISOMETRIQUE A PARTIR DE LA LATITUDE [cf. ALG0003, ALG00054]
    private static double ALG0001(double _Lt, double _e) {
    	return Math.log( Math.tan(Math.PI / 4.0 + _Lt / 2.0) * Math.pow((1 - _e * Math.sin(_Lt)) / (1 + _e * Math.sin(_Lt)), _e / 2.0) );
    }

	/// CALCUL DE LA LATITUDE A PARTIR DE LA LATITUDE ISOMETRIQUE [cf. ALG0004]
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

}
