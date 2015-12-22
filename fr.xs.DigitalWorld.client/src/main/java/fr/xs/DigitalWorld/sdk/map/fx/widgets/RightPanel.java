package fr.xs.DigitalWorld.sdk.map.fx.widgets;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.text.TextAlignment;

public class RightPanel extends VBox {

    public Label title;

    public VBox poiDisplay;
    public ImageView poiDisplayView;

    private TitledPane  road;
    private VBox        roadAttributes;
    private Label       roadColorLabel;
    private ColorPicker roadColorPicker;
    private Label       roadWidthLabel;
    private Slider      roadWidthSlider;

    private TitledPane  building;
    private VBox        buildingAttributes;
    private Label       buildingColorLabel;
    private ColorPicker buildingColorPicker;
    private Label       buildingWidthLabel;
    private Slider      buildingWidthSlider;

    private TitledPane  relief;
    private VBox        reliefAttributes;
    private Label       reliefColorLabel;
    private ColorPicker reliefColorPicker;
    private Label       reliefWidthLabel;
    private Slider      reliefWidthSlider;

    private TitledPane  poi;
    private VBox        poiAttributes;
    private Label       poiColorLabel;
    private ColorPicker poiColorPicker;
    private Label       poiWidthLabel;
    private Slider      poiWidthSlider;

    private TitledPane  roi;
    private VBox        roiAttributes;
    private Label       roiColorLabel;
    private ColorPicker roiColorPicker;
    private Label       roiWidthLabel;
    private Slider      roiWidthSlider;

	public RightPanel() {
		super();

		setSpacing(20);

		title = new Label("Display Menu");
		title.setStyle("-fx-text-fill: #c4d8de; -fx-border-color-disabled:red; -fx-background-color-disabled: blue;");
		title.setTextAlignment(TextAlignment.RIGHT);
		title.prefWidthProperty().bind(widthProperty());

		poiDisplay = new VBox();
		poiDisplay.setSpacing(10);

			poiDisplayView = new ImageView();
			poiDisplayView.setFitWidth(202);
			poiDisplayView.setFitHeight(150);
			poiDisplayView.setImage(new Image("/panels/OSM.png"));

		poiDisplay.getChildren().addAll(poiDisplayView);


		buildRoadPanel();
		buildBuildingPanel();
		buildReliefPanel();
		buildPOIPanel();
		buildROIPanel();

		getChildren().addAll(title, poiDisplay, road, building, relief, poi, roi);

	}
	
	private void buildRoadPanel() {
		roadAttributes = new VBox();

		roadColorLabel = new Label("Color");
		roadColorLabel.setTextFill(Paint.valueOf("#bfbfbf"));

		roadColorPicker = new ColorPicker();
		roadColorPicker.setValue(Color.BLACK);

		roadWidthLabel = new Label("Pixel Width : ");
		roadWidthLabel.setAlignment(Pos.CENTER);
		roadWidthLabel.setPrefSize(202, 17.0); 
		roadWidthLabel.setTextFill(Paint.valueOf("#e8e5e5"));

		roadWidthSlider = new Slider();
		roadWidthSlider.setBlockIncrement(3);
		roadWidthSlider.setPrefSize(202, 5.0); 

		roadAttributes.getChildren().addAll(roadColorLabel, roadColorPicker, roadWidthLabel, roadWidthSlider);

    	road = new TitledPane("Road", roadAttributes);
    	road.setStyle("-fx-background-color: rgba(0,0,0,0.85);");
    	road.prefWidthProperty().bind(widthProperty());
    	road.setExpanded(false);

        roadWidthSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
        	roadWidthLabel.setText("Pixel Width : " + t1.intValue());
        });
		
	}
	private void buildBuildingPanel() {
		buildingAttributes = new VBox();

		buildingColorLabel = new Label("Color");
		buildingColorLabel.setTextFill(Paint.valueOf("#bfbfbf"));

		buildingColorPicker = new ColorPicker();
		buildingColorPicker.setValue(Color.BLACK);

		buildingWidthLabel = new Label("Pixel Width : ");
		buildingWidthLabel.setAlignment(Pos.CENTER);
		buildingWidthLabel.setPrefSize(202, 17.0); 
		buildingWidthLabel.setTextFill(Paint.valueOf("#e8e5e5"));

		buildingWidthSlider = new Slider();
		buildingWidthSlider.setBlockIncrement(3);
		buildingWidthSlider.setPrefSize(202, 5.0); 

		buildingAttributes.getChildren().addAll(buildingColorLabel, buildingColorPicker, buildingWidthLabel, buildingWidthSlider);

    	building = new TitledPane("Buildings", buildingAttributes);
    	building.setStyle("-fx-background-color: rgba(0,0,0,0.85);");
    	building.prefWidthProperty().bind(widthProperty());
    	building.setExpanded(false);

    	buildingWidthSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
        	buildingWidthLabel.setText("Pixel Width : " + t1.intValue());
        });
	}
	private void buildReliefPanel() {
		reliefAttributes = new VBox();

		reliefColorLabel = new Label("Color");
		reliefColorLabel.setTextFill(Paint.valueOf("#bfbfbf"));

		reliefColorPicker = new ColorPicker();
		reliefColorPicker.setValue(Color.BLACK);

		reliefWidthLabel = new Label("Pixel Width : ");
		reliefWidthLabel.setAlignment(Pos.CENTER);
		reliefWidthLabel.setPrefSize(202, 17.0); 
		reliefWidthLabel.setTextFill(Paint.valueOf("#e8e5e5"));

		reliefWidthSlider = new Slider();
		reliefWidthSlider.setBlockIncrement(3);
		reliefWidthSlider.setPrefSize(202, 5.0); 

		reliefAttributes.getChildren().addAll(reliefColorLabel, reliefColorPicker, reliefWidthLabel, reliefWidthSlider);

    	relief = new TitledPane("Reliefs", reliefAttributes);
    	relief.setStyle("-fx-background-color: rgba(0,0,0,0.85);");
    	relief.prefWidthProperty().bind(widthProperty());
    	relief.setExpanded(false);

    	reliefWidthSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
    		reliefWidthLabel.setText("Pixel Width : " + t1.intValue());
        });
	}
	private void buildPOIPanel() {
		poiAttributes = new VBox();

		poiColorLabel = new Label("Color");
		poiColorLabel.setTextFill(Paint.valueOf("#bfbfbf"));

		poiColorPicker = new ColorPicker();
		poiColorPicker.setValue(Color.BLACK);

		poiWidthLabel = new Label("Pixel Width : ");
		poiWidthLabel.setAlignment(Pos.CENTER);
		poiWidthLabel.setPrefSize(202, 17.0); 
		poiWidthLabel.setTextFill(Paint.valueOf("#e8e5e5"));

		poiWidthSlider = new Slider();
		poiWidthSlider.setBlockIncrement(3);
		poiWidthSlider.setPrefSize(202, 5.0); 

		poiAttributes.getChildren().addAll(poiColorLabel, poiColorPicker, poiWidthLabel, poiWidthSlider);

    	poi = new TitledPane("POI", poiAttributes);
    	poi.setStyle("-fx-background-color: rgba(0,0,0,0.85);");
    	poi.prefWidthProperty().bind(widthProperty());
    	poi.setExpanded(false);

    	poiWidthSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
    		poiWidthLabel.setText("Pixel Width : " + t1.intValue());
        });
	}
	private void buildROIPanel() {
		roiAttributes = new VBox();

		roiColorLabel = new Label("Color");
		roiColorLabel.setTextFill(Paint.valueOf("#bfbfbf"));

		roiColorPicker = new ColorPicker();
		roiColorPicker.setValue(Color.BLACK);

		roiWidthLabel = new Label("Pixel Width : ");
		roiWidthLabel.setAlignment(Pos.CENTER);
		roiWidthLabel.setPrefSize(202, 17.0); 
		roiWidthLabel.setTextFill(Paint.valueOf("#e8e5e5"));

		roiWidthSlider = new Slider();
		roiWidthSlider.setBlockIncrement(3);
		roiWidthSlider.setPrefSize(202, 5.0); 

		roiAttributes.getChildren().addAll(roiColorLabel, roiColorPicker, roiWidthLabel, roiWidthSlider);

    	roi = new TitledPane("ROI", roiAttributes);
    	roi.setStyle("-fx-background-color: rgba(0,0,0,0.85);");
    	roi.prefWidthProperty().bind(widthProperty());
    	roi.setExpanded(false);

    	roiWidthSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
    		roiWidthLabel.setText("Pixel Width : " + t1.intValue());
        });
	}
	
	
	
	
	
	
}
