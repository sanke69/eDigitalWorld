package fr.xs.DigitalWorld.sdk.common.types.coordinates.conversion.algorithms;

public class Algebra {
 
	static public double computeHalfSmallAxe(double _hba, double _flatf) {
		// {1/2 Small Axe} = {1/2 Big Axe} * (1 - {flat factor}) 
		return _hba * (1 - _flatf);
	}

	static public double computeFirstExcentricity(double _hba, double _hsa) {
		// {1st Excentricity} = sqrt ( ({1/2 Big Axe}^2 - {1/2 Small Axe}^2) / {1/2 Big Axe}^2 ) 
		return Math.sqrt( (Math.pow(_hba, 2) - Math.pow(_hsa, 2)) / Math.pow(_hba, 2) );
	}

	static public double Grade2Radian(double _rad) {
		return Math.PI * _rad / 200;
	}
	static public double Radian2Grade(double _grad) {
		return 200 * _grad / Math.PI;
	}

	static public double Degree2Radian(double _rad) {
		return Math.PI * _rad / 180;
	}
	static public double Radian2Degree(double _grad) {
		return 180 * _grad / Math.PI;
	}

	static public double DMS2Degree(int _degree, int _minute, double _second) {
		return _degree + _minute / 60 + _second / 3600;
	}
	static public double[] Degree2DMS(double _grad) { // TODO::
		double D = Math.floor(_grad);
		double M = Math.floor((_grad - D) * 60);
		double S = Math.floor((_grad - D - M/60) * 3600);

		return new double[] { D, M, S };
	}

	static public double DMS2Radian(int _degree, int _minute, double _second) {
		return Math.PI * (_degree + _minute / 60 + _second / 3600) / 180;
	}
	static public double Radian2DMS(double _grad) { // TODO::
		return -1;
	}

	static public double distanceInKm(double _Lg1, double _Lt1, double _Lg2, double _Lt2) {
		return 6371 * Math.acos(Math.cos(_Lt1) * Math.cos(_Lt2) * Math.cos(_Lg2 - _Lg1) + (Math.sin(_Lt1) * Math.sin(_Lt2)));
	}

}
