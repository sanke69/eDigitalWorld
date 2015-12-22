package fr.xs.DigitalWorld.sdk.common.types.coordinates.conversion.data;

import fr.xs.DigitalWorld.sdk.common.types.coordinates.conversion.algorithms.Algebra;

public enum Projection {
	LambertI	(0.7604059656,
				11603796.98,
				600000, 5657616.674,
				Algebra.Grade2Radian(55),
				Algebra.DMS2Radian(2, 20, 14.025),	// Paris = 2�20'14,025 E Greenwich
				Algebra.DMS2Radian(48, 35, 54.682),
				Algebra.DMS2Radian(50, 23, 45.282),
				Ellipsoid.Clarke1880),
	LambertII	(0.7289686274,
				11745793.39,
				600000, 6199695.768,
				Algebra.Grade2Radian(52),
				Algebra.DMS2Radian(2, 20, 14.025),	// Paris = 2�20'14,025 E Greenwich
				Algebra.DMS2Radian(45, 53, 56.108),
				Algebra.DMS2Radian(47, 41, 45.652),
				Ellipsoid.Clarke1880),
	LambertIII	(0.6959127966,
				11947992.52,
				600000, 6791905.085,
				Algebra.Grade2Radian(49),
				Algebra.DMS2Radian(2, 20, 14.025),	// Paris = 2�20'14,025 E Greenwich
				Algebra.DMS2Radian(43, 11, 57.449),
				Algebra.DMS2Radian(44, 59, 45.938),
				Ellipsoid.Clarke1880),
	LambertIV	(0.6712679322,
				12136281.99,
				234.358, 7239161.542,
				Algebra.Grade2Radian(46.85),
				Algebra.DMS2Radian(2, 20, 14.025),	// Paris = 2�20'14,025 E Greenwich
				Algebra.DMS2Radian(41, 33, 37.396),
				Algebra.DMS2Radian(42, 46, 3.588),
				Ellipsoid.Clarke1880),
	LambertIIe	(0.7289686274,
				11745793.39,
				600000, 8199695.768,
				Algebra.Grade2Radian(52),
				Algebra.DMS2Radian(2, 20, 14.025),	// Paris = 2�20'14,025 E Greenwich
				Algebra.DMS2Radian(45, 53, 56.108),
				Algebra.DMS2Radian(47, 41, 45.652),
				Ellipsoid.Clarke1880),
	Lambert93	(0.725607765,
				11754255.426,
				700000, 12655612.05,
				Algebra.DMS2Radian(46, 30, 0.0),
				Algebra.Degree2Radian(3),
				Algebra.Degree2Radian(44),
				Algebra.Degree2Radian(49),
				Ellipsoid.Clarke1880),
	Lambert72	(0.0, // Utilis� ALG00054 avec pour Lg0 et Lt0 : 150000.013, 5400088.438
				0.0,
				0.0, 0.0,
				Algebra.Degree2Radian(90),
				Algebra.DMS2Radian(4, 22, 2.952),
				Algebra.DMS2Radian(49, 50, 0.00204),
				Algebra.DMS2Radian(51, 10, 0.00204),
				Ellipsoid.Clarke1880),
	Lambert08	(0.0, // Utilis� ALG00054 avec pour Lg0 et Lt0 : 150000.013, 5400088.438
				0.0,
				0.0, 0.0,
				Algebra.DMS2Radian(50, 47, 52.134),
				Algebra.DMS2Radian(4, 21, 33.177),
				Algebra.DMS2Radian(49, 50, 0.0),
				Algebra.DMS2Radian(51, 10, 0.0),
				Ellipsoid.Clarke1880);			

	private double exposant;				// Exposant de la projection
	private double constant;				// Constante de la projection
	private double X_pole, Y_pole;			// Coordonn�es en projection du p�le
	private double phi0;					// Latitude du m�ridien d'origine (radian)
	private double lambda0;					// Longitude du m�ridien d'origine (ou central ?) (radian)
	private double phi1;					// Longitude du 1er parall�le autom�co�que (radian)
	private double phi2;					// Longitude du 2�me parall�le autom�co�que (radian)
	private Ellipsoid associated_ellipsoid;	// Param�tres de l'ellipso�de associ�e
	
	private Projection(double _exp, double _c, double _xp, double _yp, double _phi0, double _lambda0, double _phi1, double _phi2, Ellipsoid _ellipsoid) {
		exposant             = _exp;
		constant             = _c;
		X_pole               = _xp;
		Y_pole               = _yp;
		phi0                 = _phi0;
		lambda0              = _lambda0;
		phi1                 = _phi1;
		phi2                 = _phi2;
		associated_ellipsoid = _ellipsoid;
		
	}
	
}
