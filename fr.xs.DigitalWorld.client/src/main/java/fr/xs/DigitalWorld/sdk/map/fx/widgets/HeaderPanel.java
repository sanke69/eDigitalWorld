package fr.xs.DigitalWorld.sdk.map.fx.widgets;

import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class HeaderPanel extends HBox {

	public Label    activateObjects;
	public CheckBox enableRoad;
	public CheckBox enableBuilding;
	public CheckBox enablePOI;
	public CheckBox enableROI;
	public CheckBox enableRelief;
	public ProgressBar progressBar;
    
    public HeaderPanel() {
		super();
		setAlignment(Pos.TOP_RIGHT);
		setSpacing(5);
		
			HBox menu = new HBox();
			menu.setSpacing(20);

				activateObjects = new Label("Display");

				enableRoad = new CheckBox("Road");
				enableRoad.setMnemonicParsing(false);
				enableRoad.setTextFill(Paint.valueOf("#dadada"));
				enableRoad.setSelected(true);

				enableBuilding = new CheckBox("Building");
				enableBuilding.setMnemonicParsing(false);
				enableBuilding.setTextFill(Paint.valueOf("#dadada"));
				enableBuilding.setSelected(true);

				enablePOI = new CheckBox("POI");
				enablePOI.setMnemonicParsing(false);
				enablePOI.setTextFill(Paint.valueOf("#dadada"));
				enablePOI.setSelected(false);

				enableROI = new CheckBox("ROI");
				enableROI.setMnemonicParsing(false);
				enableROI.setTextFill(Paint.valueOf("#dadada"));
				enableROI.setSelected(false); 

				enableRelief = new CheckBox("Relief");
				enableRelief.setMnemonicParsing(false);
				enableRelief.setTextFill(Paint.valueOf("#dadada"));
				enableRelief.setSelected(false); 

				menu.getChildren().addAll(activateObjects, enableRelief, enableRoad, enableBuilding, enablePOI, enableROI);

		getChildren().addAll(menu);

    }
}
