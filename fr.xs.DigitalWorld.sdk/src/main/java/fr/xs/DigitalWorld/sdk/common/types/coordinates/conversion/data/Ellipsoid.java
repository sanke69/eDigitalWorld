package fr.xs.DigitalWorld.sdk.common.types.coordinates.conversion.data;

import fr.xs.DigitalWorld.sdk.common.types.coordinates.conversion.algorithms.Algebra;

public enum Ellipsoid {

	Clarke1880	(6378249.2, 
			   	6356515, 
			   	0, 
			   	Algebra.computeFirstExcentricity(6378249.2, 6356515), 
			   	100),
	IAG_GRS80	(6378137, 
			  	Algebra.computeHalfSmallAxe(6378137, 1/298.257222101), 
			  	1/298.257222101, 
			  	Algebra.computeFirstExcentricity(6378137, Algebra.computeHalfSmallAxe(6378137, 1/298.257222101)), 
			  	100),
	WGS84		(6378137, 
			  	Algebra.computeHalfSmallAxe(6378137, 1/298.257223563), 
			  	1/298.257223563, 
			  	Algebra.computeFirstExcentricity(6378137, Algebra.computeHalfSmallAxe(6378137, 1/298.257223563)), 
			  	100),
	GRS80		(6378137, 
			  	Algebra.computeHalfSmallAxe(6378137, 1/298.257222101), 
			  	1/298.257222101, 
			  	Algebra.computeFirstExcentricity(6378137, Algebra.computeHalfSmallAxe(6378137, 1/298.257222101)), 
			  	100),
	Hayford1909	(6378388, 
			  	Algebra.computeHalfSmallAxe(6378388, 1/297), 
			  	1/297, 
			  	Algebra.computeFirstExcentricity(6378388, Algebra.computeHalfSmallAxe(6378388, 1/297)), 
			  	100),
  	Inter1924	(6378388, 
			  	Algebra.computeHalfSmallAxe(6378388, 1/297), 
			  	1/297, 
			  	Algebra.computeFirstExcentricity(6378388, Algebra.computeHalfSmallAxe(6378388, 1/297)), 
			  	100);

	private double big_axe;					// 1/2 grand axe de l'ellipso�de (m�tre)
	private double small_axe;				// 1/2 petit axe de l'ellipso�de (m�tre) - Non utilis� directement
	private double flat_factor;				// Facteur d'aplatissement  - Non utilis� directement
	private double excentricity;			// Premi�re excentricit� de l'ellipso�de de r�f�rence
	private double ellipsoid_height;		// Hauteur au dessus de l'ellipso�de (en m�tre)

	private Ellipsoid(double _ba, double _sa, double _ff, double _ex, double _eh) {
		big_axe = _ba;
		small_axe = _sa;
		flat_factor = _ff;
		excentricity = _ex;
		ellipsoid_height = _eh;
	}

	public double getHalfBigAxe() 		{ return big_axe; }
	public double getHalfSmallAxe() 	{ return small_axe; }
	public double getFlatteringFactor()	{ return flat_factor; }
	public double getExcentricity() 	{ return excentricity; }
	public double getEllipsoidHeight() 	{ return ellipsoid_height; }

}
