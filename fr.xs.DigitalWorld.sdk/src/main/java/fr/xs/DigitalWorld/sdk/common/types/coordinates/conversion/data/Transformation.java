package fr.xs.DigitalWorld.sdk.common.types.coordinates.conversion.data;

import fr.xs.DigitalWorld.sdk.common.types.coordinates.conversion.algorithms.Algebra;

public enum Transformation {
	NTFtoRGF93(-168, -60, 320, 0, 0, 0, 1),
	NTFtoED50(-84, 37, 437, 0, 0, 0, 1),
	NTFtoWGS84(-168, -60, 320, 0, 0, 0, 1),
	RGF93toNTF(168, 60, -320, 0, 0, 0, 1),
	RGF93toED50(84, 97, 117, 0, 0, 0, 1),
	RGF93toWGS84(-1, -1, -1, -1, -1, -1, -1),
	ED50toNTF(84, -37, -437, 0, 0, 0, 1),
	ED50toRGF93(-84, -97, -117, 0, 0, 0, 1),
	ED50toWGS84(-84, -97, -117, 0, 0, 0, 1),
	WGS84toNTF(168, 60, -320, 0, 0, 0, 1),
	WGS84toRGF93(-1, -1, -1, -1, -1, -1, -1),
	WGS84toED50(84, 97, 117, 0, 0, 0, 1),
	ETRS89toBD72(106.868628, -52.297783, 103.723893, Algebra.DMS2Radian(0, 0, 0.33657), Algebra.DMS2Radian(0, 0, 0.456955), Algebra.DMS2Radian(0, 0, 1.842183), 1 + 0.0000012747),
	BD72toETRS89(-106.868628, 52.297783, -103.723893, - Algebra.DMS2Radian(0, 0, 0.33657), - Algebra.DMS2Radian(0, 0, 0.456955), - Algebra.DMS2Radian(0, 0, 1.842183), 1 / (1 + 0.0000012747));

	double Tx, Ty, Tz;
	double Rx, Ry, Rz;
	double Scale;

	private Transformation(double _tx, double _ty, double _tz, double _rx, double _ry, double _rz, double _scale) {
		Tx = _tx;
		Ty = _ty;
		Tz = _tz;
		Rx = _rx;
		Ry = _ry;
		Rz = _rz;
		Scale = _scale;
	}

}
