package fr.xs.DigitalWorld.sdk.map.fx;

import fr.xs.DigitalWorld.sdk.map.fx.widgets.HeaderPanel;
import fr.xs.DigitalWorld.sdk.map.fx.widgets.RightPanel;
import fr.xs.jtk.graphics.fx2d.BoundedScene2D;
import fr.xs.jtk.graphics.fx2d.controls.BoundedScene2DController;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class DigitalMapViewer extends AnchorPane {

    HeaderPanel 		Hpanel;
    RightPanel  		Rpanel;

    StackPane			drawingZone;
	    BoundedScene2D    			scene2D;
	    BoundedScene2DController   	scene2D_HUD;

	public DigitalMapViewer() {
		super();

		setPrefSize(1280, 768);
		setStyle("-fx-background-color: rgba(0,0,0,0.85);");

		Hpanel = new HeaderPanel();
		AnchorPane.setTopAnchor(Hpanel, 5.0);
		AnchorPane.setBottomAnchor(Hpanel, 10.0);
		AnchorPane.setLeftAnchor(Hpanel, 10.0);
		AnchorPane.setRightAnchor(Hpanel, 225.0);

		Rpanel = new RightPanel();
		AnchorPane.setTopAnchor(Rpanel, 50.0);
		AnchorPane.setBottomAnchor(Rpanel, 10.0);
		AnchorPane.setRightAnchor(Rpanel, 15.0);

		drawingZone = new StackPane();
		AnchorPane.setTopAnchor(drawingZone, 50.0);
		AnchorPane.setBottomAnchor(drawingZone, 10.0);
		AnchorPane.setLeftAnchor(drawingZone, 10.0);
		AnchorPane.setRightAnchor(drawingZone, 225.0);

		getChildren().addAll(Hpanel, drawingZone, Rpanel);

		// SCENE 2D
		scene2D     = new BoundedScene2D(800, 600);
		scene2D_HUD = new BoundedScene2DController(scene2D);

		//scene2D.referential().setFX();

		drawingZone.getChildren().addAll(scene2D, scene2D_HUD);
	}
	
	public final BoundedScene2D scene2D() {
		return scene2D;
	}
	public final StackPane drawingZone() {
		return drawingZone;
	}

}
