package fr.xs.DigitalWorld.sdk.common.types.coordinates.conversion.data;


public enum GeodesicSystem {
	BD72		("Belgian Datum 72", 							Ellipsoid.Inter1924),
	ETRS89		("European Terrestrial Reference System 1989", 	Ellipsoid.GRS80),
	ED50		("European Datum 1950", 						Ellipsoid.Hayford1909),
	NTF			("Nouvelle Triangulation Francaise", 			Ellipsoid.Clarke1880),
	RGF93		("Réseau Géodésique Français 1993", 			Ellipsoid.IAG_GRS80),
	WGS84		("World Geodesic System 1984", 					Ellipsoid.WGS84);

	private String    label;
	private Ellipsoid ellipsoid;
	
	private GeodesicSystem(String _label, Ellipsoid _ellipsoid) {
		label = _label;
		ellipsoid = _ellipsoid;
	}

}
