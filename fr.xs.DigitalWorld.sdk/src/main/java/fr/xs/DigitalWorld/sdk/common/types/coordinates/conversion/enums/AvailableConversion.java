package fr.xs.DigitalWorld.sdk.common.types.coordinates.conversion.enums;

import fr.xs.DigitalWorld.sdk.common.types.coordinates.conversion.data.Transformation;

public enum AvailableConversion {
	T00001(AvailableSCG.NTF_Lambert_I, AvailableSCG.NTF_Paris_Geo, 				true, null, false),
	T00002(AvailableSCG.NTF_Lambert_I, AvailableSCG.NTF_Lambert_II_Etendu, 		true, null, true),
	T00003(AvailableSCG.NTF_Lambert_I, AvailableSCG.RGF93_Geo, 					true, Transformation.NTFtoRGF93, false),
	T00004(AvailableSCG.NTF_Lambert_I, AvailableSCG.RGF93_Lambert_93, 			true, Transformation.NTFtoRGF93, false),
	T00005(AvailableSCG.NTF_Lambert_I, AvailableSCG.ED50_Geo, 					true, Transformation.NTFtoED50, false),
	T00006(AvailableSCG.NTF_Lambert_I, AvailableSCG.WGS84_Geo, 					true, Transformation.NTFtoWGS84, false),

	T00011(AvailableSCG.NTF_Lambert_II, AvailableSCG.NTF_Paris_Geo, 			true, null, false),
	T00012(AvailableSCG.NTF_Lambert_II, AvailableSCG.NTF_Lambert_II_Etendu, 	true, null, true),
	T00013(AvailableSCG.NTF_Lambert_II, AvailableSCG.RGF93_Geo, 				true, Transformation.NTFtoRGF93, false),
	T00014(AvailableSCG.NTF_Lambert_II, AvailableSCG.RGF93_Lambert_93, 			true, Transformation.NTFtoRGF93, false),
	T00015(AvailableSCG.NTF_Lambert_II, AvailableSCG.ED50_Geo, 					true, Transformation.NTFtoED50, false),
	T00016(AvailableSCG.NTF_Lambert_II, AvailableSCG.WGS84_Geo, 				true, Transformation.NTFtoWGS84, false),

	T00021(AvailableSCG.NTF_Lambert_III, AvailableSCG.NTF_Paris_Geo, 			true, null, false),
	T00022(AvailableSCG.NTF_Lambert_III, AvailableSCG.NTF_Lambert_II_Etendu, 	true, null, true),
	T00023(AvailableSCG.NTF_Lambert_III, AvailableSCG.RGF93_Geo, 				true, Transformation.NTFtoRGF93, false),
	T00024(AvailableSCG.NTF_Lambert_III, AvailableSCG.RGF93_Lambert_93, 		true, Transformation.NTFtoRGF93, false),
	T00025(AvailableSCG.NTF_Lambert_III, AvailableSCG.ED50_Geo, 				true, Transformation.NTFtoED50, false),
	T00026(AvailableSCG.NTF_Lambert_III, AvailableSCG.WGS84_Geo, 				true, Transformation.NTFtoWGS84, false),

	T00031(AvailableSCG.NTF_Lambert_IV, AvailableSCG.NTF_Paris_Geo, 			true, null, false),
	T00032(AvailableSCG.NTF_Lambert_IV, AvailableSCG.NTF_Lambert_II_Etendu, 	true, null, true),
	T00033(AvailableSCG.NTF_Lambert_IV, AvailableSCG.RGF93_Geo, 				true, Transformation.NTFtoRGF93, false),
	T00034(AvailableSCG.NTF_Lambert_IV, AvailableSCG.RGF93_Lambert_93, 			true, Transformation.NTFtoRGF93, false),
	T00035(AvailableSCG.NTF_Lambert_IV, AvailableSCG.ED50_Geo, 					true, Transformation.NTFtoED50, false),
	T00036(AvailableSCG.NTF_Lambert_IV, AvailableSCG.WGS84_Geo, 				true, Transformation.NTFtoWGS84, false),	

	T00041(AvailableSCG.NTF_Lambert_II_Etendu, AvailableSCG.NTF_Paris_Geo, 		true, null, false),
	T00042(AvailableSCG.NTF_Lambert_II_Etendu, AvailableSCG.RGF93_Geo, 			true, Transformation.NTFtoRGF93, false),
	T00043(AvailableSCG.NTF_Lambert_II_Etendu, AvailableSCG.RGF93_Lambert_93, 	true, Transformation.NTFtoRGF93, true),
	T00044(AvailableSCG.NTF_Lambert_II_Etendu, AvailableSCG.ED50_Geo, 			true, Transformation.NTFtoED50, false),
	T00045(AvailableSCG.NTF_Lambert_II_Etendu, AvailableSCG.WGS84_Geo, 			true, Transformation.NTFtoWGS84, false),

	T00051(AvailableSCG.NTF_Paris_Geo, AvailableSCG.NTF_Lambert_II_Etendu, 		false, null, true),
	T00052(AvailableSCG.NTF_Paris_Geo, AvailableSCG.RGF93_Geo, 					false, Transformation.NTFtoRGF93, false),
	T00053(AvailableSCG.NTF_Paris_Geo, AvailableSCG.RGF93_Lambert_93, 			false, Transformation.NTFtoRGF93, true),
	T00054(AvailableSCG.NTF_Paris_Geo, AvailableSCG.ED50_Geo, 					false, Transformation.NTFtoED50, false),
	T00055(AvailableSCG.NTF_Paris_Geo, AvailableSCG.WGS84_Geo, 					false, Transformation.NTFtoWGS84, false),

	T00061(AvailableSCG.RGF93_Geo, AvailableSCG.NTF_Lambert_II_Etendu, 			false, Transformation.RGF93toNTF, true),
	T00062(AvailableSCG.RGF93_Geo, AvailableSCG.NTF_Paris_Geo, 					false, Transformation.RGF93toNTF, false),
	T00063(AvailableSCG.RGF93_Geo, AvailableSCG.RGF93_Lambert_93, 				false, null, true),
	T00064(AvailableSCG.RGF93_Geo, AvailableSCG.ED50_Geo, 						false, Transformation.RGF93toED50, false),
	T00065(AvailableSCG.RGF93_Geo, AvailableSCG.WGS84_Geo, 						false, Transformation.RGF93toWGS84, false),	

	T00071(AvailableSCG.RGF93_Lambert_93, AvailableSCG.NTF_Paris_Geo, 			true, Transformation.RGF93toNTF, false),
	T00072(AvailableSCG.RGF93_Lambert_93, AvailableSCG.NTF_Lambert_II_Etendu, 	true, Transformation.RGF93toNTF, true),
	T00073(AvailableSCG.RGF93_Lambert_93, AvailableSCG.RGF93_Geo, 				true, null, false),
	T00074(AvailableSCG.RGF93_Lambert_93, AvailableSCG.ED50_Geo, 				true, Transformation.RGF93toED50, false),
	T00075(AvailableSCG.RGF93_Lambert_93, AvailableSCG.WGS84_Geo, 				true, Transformation.RGF93toWGS84, false),

	T00081(AvailableSCG.ED50_Geo, AvailableSCG.NTF_Paris_Geo, 					false, Transformation.ED50toNTF, false),
	T00082(AvailableSCG.ED50_Geo, AvailableSCG.NTF_Lambert_II_Etendu, 			false, Transformation.ED50toNTF, true),
	T00083(AvailableSCG.ED50_Geo, AvailableSCG.RGF93_Geo, 						false, Transformation.ED50toRGF93, false),
	T00084(AvailableSCG.ED50_Geo, AvailableSCG.RGF93_Lambert_93, 				false, Transformation.ED50toRGF93, true),
	T00085(AvailableSCG.ED50_Geo, AvailableSCG.WGS84_Geo, 						false, Transformation.ED50toWGS84, false),

	T00091(AvailableSCG.WGS84_Geo, AvailableSCG.NTF_Paris_Geo, 					false, Transformation.WGS84toNTF, false),
	T00092(AvailableSCG.WGS84_Geo, AvailableSCG.NTF_Lambert_II_Etendu, 			false, Transformation.WGS84toNTF, true),
	T00093(AvailableSCG.WGS84_Geo, AvailableSCG.RGF93_Geo, 						false, Transformation.WGS84toRGF93, false),
	T00094(AvailableSCG.WGS84_Geo, AvailableSCG.RGF93_Lambert_93, 				false, Transformation.WGS84toRGF93, true),
	T00095(AvailableSCG.WGS84_Geo, AvailableSCG.ED50_Geo,						false, Transformation.WGS84toED50, false),

	T000A1(AvailableSCG.BD72_Lambert72, AvailableSCG.ETRS89_Lambert08, 			true, Transformation.BD72toETRS89, true),
	T000A2(AvailableSCG.BD72_Lambert72, AvailableSCG.BD72_Geo, 					true, null, false),
	T000A3(AvailableSCG.BD72_Lambert72, AvailableSCG.ETRS89_Geo, 				true, Transformation.BD72toETRS89, false),

	T000B4(AvailableSCG.BD72_Geo, AvailableSCG.ETRS89_Geo, 						false, Transformation.BD72toETRS89, false),

	T000C1(AvailableSCG.ETRS89_Lambert08, AvailableSCG.BD72_Lambert72, 			true, Transformation.ETRS89toBD72, true),
	T000C2(AvailableSCG.ETRS89_Lambert08, AvailableSCG.BD72_Geo, 				true, Transformation.ETRS89toBD72, false),
	T000C3(AvailableSCG.ETRS89_Lambert08, AvailableSCG.ETRS89_Geo, 				true, null, false),

	T000D1(AvailableSCG.ETRS89_Geo, AvailableSCG.BD72_Lambert72, 				false, Transformation.ETRS89toBD72, true),
	T000D2(AvailableSCG.ETRS89_Geo, AvailableSCG.BD72_Geo, 						false, Transformation.ETRS89toBD72, false),
	T000D3(AvailableSCG.ETRS89_Geo, AvailableSCG.ETRS89_Lambert08, 				false, null, true);

	private AvailableSCG    from;
	private AvailableSCG    to;
	// Algorithm used
	private boolean 		Plan2Geo;
	private Transformation 	tranformation;
	private boolean 		Geo2Plan;

	private AvailableConversion(AvailableSCG _from, AvailableSCG _to, boolean _p2g, Transformation _t, boolean _g2p) {
		from 			= _from;
		to 				= _to;
		Plan2Geo 		= _p2g;
		tranformation 	= _t;
		Geo2Plan 		= _g2p;
	}

}
