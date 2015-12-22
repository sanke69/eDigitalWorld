/**
 * Copyright (C) 2007-?
 * 
 * @author   <a href='mailto:steve.pechberti@gmail.com'> Steve PECHBERTI </a>
 *
 * @section license License
 *    [EN] This file is the intellectual property of Steve PECHBERTI.
 *         Any use, partial or complete copy, modification of the file
 *         without my approval is forbidden
 *    [FR] Ce fichier est la propriete intellectuelle de Steve PECHBERTI.
 *         Toute utilisation, copie partielle ou totale, modification
 *         du fichier sans mon autorisation est interdite
 *
 * @section disclaimer Disclaimer
 *    [EN] This program is distributed in the hope that it will be useful,
 *         but WITHOUT ANY WARRANTY; without even the implied warranty of
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *    [FR] Ce programme est distribué dans l'espoir qu'il sera utile,
 *         mais SANS AUCUNE GARANTIE, sans même la garantie implicite de
 *         VALEUR MARCHANDE ou FONCTIONNALITE POUR UN BUT PARTICULIER.
 *
 */
package fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

import fr.xs.DigitalWorld.sdk.common.interfaces.position.RoadPositionInterface;

public class SpeedDistribution {
	double               length;
	Collection<double[]> distributionD;
	Collection<double[]> distributionI;

	public SpeedDistribution(double _length, String _DIRECT, String _INDIRECT) {
		length = _length;
		distributionD = new ArrayList<double[]>();
		distributionI = new ArrayList<double[]>();

		String strD = _DIRECT;
		strD = strD.replace("\"", "");
		if(strD.equalsIgnoreCase("NONE") == false) {
			StringTokenizer tokensD = new StringTokenizer(strD, " ");
			while(tokensD.hasMoreElements()) {
				StringTokenizer distrib = new StringTokenizer(tokensD.nextToken(), ":");

				double beg   = Double.valueOf((String) distrib.nextElement());
				double end   = Double.valueOf((String) distrib.nextElement());
				double value = Double.valueOf((String) distrib.nextElement());
	
				distributionD.add(new double[] { beg, end, value });
			}
		}

		String strI = _INDIRECT;
		strI = strI.replace("\"", "");
		if(strI.equalsIgnoreCase("NONE") == false) {
			StringTokenizer tokensI = new StringTokenizer(strI, " ");
			while(tokensI.hasMoreElements()) {
				StringTokenizer distrib = new StringTokenizer(tokensI.nextToken(), ":");

				double beg   = Double.valueOf((String) distrib.nextElement());
				double end   = Double.valueOf((String) distrib.nextElement());
				double value = Double.valueOf((String) distrib.nextElement());

				distributionI.add(new double[] { beg, end, value });
			}
		}
	}
	
	public SpeedDistribution(double _length, Collection<double[]> _direct, Collection<double[]> _indirect) {
		length = _length;

		distributionD = new ArrayList<double[]>();
		if(_direct != null)
			distributionD.addAll(_direct);

		distributionI = new ArrayList<double[]>();
		if(_indirect != null)
			distributionI.addAll(_indirect);
	}

	public SpeedDistribution(double _length, double[] _direct, double[] _indirect) {
		length = _length;

		distributionD = new ArrayList<double[]>();
		if(_direct != null)
			distributionD.add(_direct);

		distributionI = new ArrayList<double[]>();
		if(_indirect != null)
			distributionI.add(_indirect);
	}

	public int getMandatorySpeedLimitFor(RoadPositionInterface _p, boolean _direct) {
		double s = _p.getCurvilinearAbscissa();

		if(_direct) {
			if(distributionD != null) {
				double s_p = s / length * 100.;

				for(double[] d : distributionD) {
					if(s_p >= d[0] && s_p <= d[1])
						return (int) d[2];
				}
			} else {
				return -1;
			}
		} else {
			if(distributionI != null) {
				double s_p = (1 - s / length) * 100.;
				
				for(double[] d : distributionI) {
					if(s_p >= d[0] && s_p <= d[1])
						return (int) d[2];
				}
			} else {
				return -1;
			}
		}

		return -1;
	}

	public String display() {
		String dist = "DIRECT:";
		for(double[] d : distributionD) {
			dist += d[0] + ":" + d[1] + ":" + d[2] + "-";
		}
		
		dist += "INDIRECT:";
		for(double[] d : distributionI) {
			dist += d[0] + ":" + d[1] + ":" + d[2] + "-";
		}
		
		return dist;
	}

}
