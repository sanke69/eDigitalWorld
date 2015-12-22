package fr.xs.DigitalWorld.sdk.map.fx.utils;

import fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads.DrivingCondition;
import fr.xs.DigitalWorld.sdk.common.types.map.attributes.roads.RoadClass;
import javafx.scene.paint.Color;

public class MapEnlightment {

	public static final Color MAP_COLOR_PRIMARYROAD   = Color.color(190, 86, 86);
	public static final Color MAP_COLOR_SECONDARYROAD = Color.color(70, 70, 255);
	public static final Color MAP_COLOR_THIRDROAD     = Color.color(255, 165, 0);
	public static final Color MAP_COLOR_FOURTHROAD    = Color.color(255, 255, 255);
	public static final Color MAP_COLOR_OTHERROAD     = Color.color(70, 255, 70);
	public static final Color MAP_COLOR_BACKGROUND    = Color.color(242, 219, 177);
	public static final Color MAP_COLOR_BACKGROUND2   = Color.color(180, 253, 200);
	public static final Color MA_COLOR_WATER          = Color.color(149, 184, 235);
	public static final Color MAP_COLOR_FOREST        = Color.color(138, 187, 138); //new Color(22, 139, 22)

	public static Color getColorFor(RoadClass _frc, DrivingCondition _fow) {
		switch(_frc) {
			case Motorway				:
				switch(_fow) {
					case PartOfMotorway				: 
					case PartOfMultiCarriageWay		: 
					case PartOfSingleCarriageWay	: 
					case PartOfRoundAbout			: 
					case PartOfParkingPlace			: 
					case PartOfParkingGarage		: 
					case UnstructureTrafficSquare   : 
					case PartOfSlipRoad      	 	: 
					case PartOfServiceRoad 			: 
					case ExitEntranceCarPark		: 
					case PartOfBicycleWay 			: 
					case PartOfPedestrianZone       : 
					case PartOfWalkWay				: 
					case SpecialTrafficFigures		: 
					case RoadForAuthorities			: 
					case NotApplicable				: return MAP_COLOR_PRIMARYROAD;
				}
				break;
			case MajorRoad				: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return MAP_COLOR_SECONDARYROAD;
			}
			break;
			case OtherMajorRoad			: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return MAP_COLOR_THIRDROAD;
			}
			break;
			case SecondaryRoad			: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return MAP_COLOR_THIRDROAD;
			}
			break;
			case LocalConnectingRoad	: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return MAP_COLOR_FOURTHROAD;
			}
			break;
			case LocalRoadLvl1			: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return MAP_COLOR_FOURTHROAD;
			}
			break;
			case LocalRoadLvl2   		: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return MAP_COLOR_FOURTHROAD;
			}
			break;
			case LocalRoadLvl3      	: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return MAP_COLOR_FOURTHROAD;
			}
			break;
			case OtherRoad 				: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return MAP_COLOR_FOURTHROAD;
			}
			break;
			case BicybleWay				: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return MAP_COLOR_OTHERROAD;
			}
			break;
			case WalkWay 				: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return MAP_COLOR_OTHERROAD;
			}
			break;
			case Unknown       			: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return MAP_COLOR_OTHERROAD;
			}
			break;
		}

		return Color.BLACK;
	}

	public static int getThicknessFor(RoadClass _frc, DrivingCondition _fow) {
		switch(_frc) {
			case Motorway				:
				switch(_fow) {
					case PartOfMotorway				: 
					case PartOfMultiCarriageWay		: 
					case PartOfSingleCarriageWay	: 
					case PartOfRoundAbout			: 
					case PartOfParkingPlace			: 
					case PartOfParkingGarage		: 
					case UnstructureTrafficSquare   : 
					case PartOfSlipRoad      	 	: 
					case PartOfServiceRoad 			: 
					case ExitEntranceCarPark		: 
					case PartOfBicycleWay 			: 
					case PartOfPedestrianZone       : 
					case PartOfWalkWay				: 
					case SpecialTrafficFigures		: 
					case RoadForAuthorities			: 
					case NotApplicable				: return 4;
				}
				break;
			case MajorRoad				: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return 3;
			}
			break;
			case OtherMajorRoad			: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return 3;
			}
			break;
			case SecondaryRoad			: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return 2;
			}
			break;
			case LocalConnectingRoad	: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return 2;
			}
			break;
			case LocalRoadLvl1			: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return 2;
			}
			break;
			case LocalRoadLvl2   		: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return 2;
			}
			break;
			case LocalRoadLvl3      	: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return 2;
			}
			break;
			case OtherRoad 				: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return 2;
			}
			break;
			case BicybleWay				: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return 1;
			}
			break;
			case WalkWay 				: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return 1;
			}
			break;
			case Unknown       			: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return 1;
			}
			break;
		}

		return 1;
	}

	public static void getStyleFor(RoadClass _frc, DrivingCondition _fow) {
		switch(_frc) {
			case Motorway				:
				switch(_fow) {
					case PartOfMotorway				: 
					case PartOfMultiCarriageWay		: 
					case PartOfSingleCarriageWay	: 
					case PartOfRoundAbout			: 
					case PartOfParkingPlace			: 
					case PartOfParkingGarage		: 
					case UnstructureTrafficSquare   : 
					case PartOfSlipRoad      	 	: 
					case PartOfServiceRoad 			: 
					case ExitEntranceCarPark		: 
					case PartOfBicycleWay 			: 
					case PartOfPedestrianZone       : 
					case PartOfWalkWay				: 
					case SpecialTrafficFigures		: 
					case RoadForAuthorities			: 
					case NotApplicable				: return ;
				}
				break;
			case MajorRoad				: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return ;
			}
			break;
			case OtherMajorRoad			: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return ;
			}
			break;
			case SecondaryRoad			: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return ;
			}
			break;
			case LocalConnectingRoad	: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return ;
			}
			break;
			case LocalRoadLvl1			: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return ;
			}
			break;
			case LocalRoadLvl2   		: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return ;
			}
			break;
			case LocalRoadLvl3      	: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return ;
			}
			break;
			case OtherRoad 				: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return ;
			}
			break;
			case BicybleWay				: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return ;
			}
			break;
			case WalkWay 				: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return ;
			}
			break;
			case Unknown       			: 
				switch(_fow) {
				case PartOfMotorway				: 
				case PartOfMultiCarriageWay		: 
				case PartOfSingleCarriageWay	: 
				case PartOfRoundAbout			: 
				case PartOfParkingPlace			: 
				case PartOfParkingGarage		: 
				case UnstructureTrafficSquare   : 
				case PartOfSlipRoad      	 	: 
				case PartOfServiceRoad 			: 
				case ExitEntranceCarPark		: 
				case PartOfBicycleWay 			: 
				case PartOfPedestrianZone       : 
				case PartOfWalkWay				: 
				case SpecialTrafficFigures		: 
				case RoadForAuthorities			: 
				case NotApplicable				: return ;
			}
			break;
		}

		return ;
	}
	
}
