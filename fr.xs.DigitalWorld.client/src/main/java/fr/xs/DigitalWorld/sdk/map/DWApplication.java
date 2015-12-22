package fr.xs.DigitalWorld.sdk.map;

import fr.xs.DigitalWorld.sdk.map.fx.DigitalMapViewer;
import fr.xs.DigitalWorld.sdk.map.fx.DigitalMapScene;
import fr.xs.DigitalWorld.sdk.map.fx.widgets.LocalDynamicMapDrawer;
import fr.xs.DigitalWorld.sdk.osm.OsmLocalMap;
import fr.xs.jtk.helpers.MediaHelper;
import fr.xs.jtk.math.type.BoundaryBox;
import fr.xs.jtk.math.type.geom.Point2D;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DWApplication extends Application {
	static OsmLocalMap           osmLdm;
	static DigitalMapViewer      display;
	static LocalDynamicMapDrawer drawer;

    @Override
    public void start(Stage stage) throws Exception {
		display = new DigitalMapViewer();
        Scene scene = new Scene(display);
        
        stage.setTitle("DigitalWorld 1.0");
        stage.setScene(scene);        
        stage.show();
    }

    public static void main(String[] args) {
    	/** VERSAILLES TEST * /
    	String  OSM_File = "/samples/versailles.osm";
    	Point2D coords   = new Point2D(2.14f, 48.805f);
    	/*/ // ESBLY TEST
    	String  OSM_File = "/samples/esbly.osm";
    	Point2D coords   = new Point2D(2.81596307f, 48.90353727f);
    	/**/
   
    	Thread ui = new Thread( () -> DigitalMapScene.launch(DWApplication.class) ); ui.start();
		try { Thread.sleep(1500); } catch(InterruptedException e) { e.printStackTrace(); }

		osmLdm = new OsmLocalMap( MediaHelper.getContent(DigitalMap.class, OSM_File) );
		drawer = new LocalDynamicMapDrawer(osmLdm, display.scene2D());

		display.scene2D().referential().setBoundBox2DMax(new BoundaryBox(-180.0, -90.0, 360.0, 180.0));
		display.scene2D().referential().setScaleMax(100000);

		display.scene2D().referential().setScale(10000);
		display.scene2D().referential().moveTo(coords);

		while(true) {
			Platform.runLater( () -> redraw() );
			if(!ui.isAlive())
				System.exit(0);
			try { Thread.sleep(40); } catch(InterruptedException e) { e.printStackTrace(); }
		}
    }

    static void redraw() {
    	display.scene2D().canvas().setWidth(display.drawingZone().getWidth());
    	display.scene2D().canvas().setHeight(display.drawingZone().getHeight());
    	drawer.redraw();
    }

}
