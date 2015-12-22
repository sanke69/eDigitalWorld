package fr.xs.DigitalWorld.sdk.map.fx.widgets;

import java.util.List;

import fr.xs.DigitalWorld.sdk.common.types.coordinates.GeoCoordinate;
import fr.xs.DigitalWorld.sdk.common.types.map.Segment;
import fr.xs.DigitalWorld.sdk.map.LocalDynamicMap;
import fr.xs.jtk.graphics.fx2d.BoundedScene2D;
import javafx.scene.paint.Color;

public class LocalDynamicMapDrawer {
	LocalDynamicMap ldm;
	BoundedScene2D  scene2D;

	public LocalDynamicMapDrawer() {
		super();
	}
	public LocalDynamicMapDrawer(final LocalDynamicMap _ldm, final BoundedScene2D _scene2D) {
		super();
		ldm     = _ldm;
		scene2D = _scene2D;
	}
	
	public void redraw() {
		scene2D.clear();
		scene2D.setStroke(Color.BLACK);

//		for(Node node : ldm.getNodes().values())
//			scene2D.strokeRect(node.getLongitude()-1, node.getLatitude()-1, node.getLongitude()+1, node.getLatitude()+1);

		for(Segment segment : ldm.getSegments().values()) {
			List<GeoCoordinate> coords = segment.getGeometry();
			int n = coords.size();
			
			for(int i = 0; i < n - 1; ++i)
				scene2D.strokeLine(coords.get(i).getLongitude(), coords.get(i).getLatitude(), coords.get(i+1).getLongitude(), coords.get(i+1).getLatitude());
		}

	}

}
