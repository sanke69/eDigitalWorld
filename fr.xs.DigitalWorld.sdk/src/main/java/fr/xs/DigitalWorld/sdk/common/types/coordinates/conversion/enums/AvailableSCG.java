package fr.xs.DigitalWorld.sdk.common.types.coordinates.conversion.enums;

public enum AvailableSCG {
	BD72_Lambert72(0),          // Projection Lambert 72 - Ellipsoïde Hayford 1924 - Belgique
	BD72_Geo(1),
	ETRS89_Lambert08(2),        // Projection Lambert 2008 - Ellipsoïde GRS80 - Belgique
	ETRS89_Geo(3),              // SG ETRS89 - Ellipsoïde GRS80 - Belgique
	ED50_Geo(4),                // SG European Datum 1950 - Ellipsoïde Hayford 1909
	NTF_Lambert_I(5),           // Projection Lambert I - Ellipsoïde NTF : Clarke 1880 - France
	NTF_Lambert_II(6),          // Projection Lambert II - Ellipsoïde NTF : Clarke 1880 - France
	NTF_Lambert_III(7),         // Projection Lambert III - Ellipsoïde NTF : Clarke 1880 - France
	NTF_Lambert_IV(8),          // Projection Lambert IV - Ellipsoïde NTF : Clarke 1880 - France
	NTF_Lambert_II_Etendu(9),   // Projection Lambert pour la France
	NTF_Paris_Geo(10),          // SG NTF : Nouvelle triangulation de la France (méridien Paris)
	RGF93_Geo(11),              // SG Réseau géodésique français - Ellipsoïde RGF : IAG GRS 80
	RGF93_Lambert_93(12),       // Projection Lambert 93 - RGF : Réseau géodésique Français 1993
	WGS84_Geo(13);              // SG World Geodetic System 1984

	private int ID;
	
	AvailableSCG(int _id) { ID = _id; }
	
	public int getID() { return ID; }

}
