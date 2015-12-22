package fr.xs.DigitalWorld.sdk.map.fx;

import java.util.concurrent.CountDownLatch;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DigitalMapScene extends Application {
	final static DigitalMapViewer mw = new DigitalMapViewer();

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(mw);
        
        stage.setTitle("Test MainWindow");
        stage.setScene(scene);        
        stage.show();
    }

    public static void main(String[] args) {
		new Thread( () -> DigitalMapScene.launch(DigitalMapScene.class) ).start();

		while(true) {
			Platform.runLater( () -> initScene2D() );
			try { Thread.sleep(40); } catch(InterruptedException e) { e.printStackTrace(); }
		}
    }

    static void initScene2D() {
		mw.scene2D.canvas().setWidth(mw.drawingZone.getWidth());
		mw.scene2D.canvas().setHeight(mw.drawingZone.getHeight());

		mw.scene2D.clear();
		double xa = 0.0, xb = 0.0, ya = 0.0, yb = 0.0;
		for(int i = 0; i < 1; ++i) {
			xa = 0;
			ya = 0;
			xb = 2;
			yb = 2;
			mw.scene2D.strokeLine(xa, ya, xb, yb);
		}
	}
	/*
	 * SINGLETON DEFINITION
	 */
	public static final CountDownLatch latch = new CountDownLatch(1);
	public static DigitalMapScene instance = null;
	
	public DigitalMapScene() {
		setInstance(this);
//		waitForInstance();
	}

	public static DigitalMapScene instance() {
		if(instance == null)
			try { Thread.sleep(25); } catch(InterruptedException e) { }
		return instance;
	}

	public static DigitalMapScene waitForInstance() {
		try { latch.await(); } catch(InterruptedException e) { }
		return instance;
	}

	public void setInstance(DigitalMapScene _inst) {
		instance = _inst;
		latch.countDown();
	}

}
