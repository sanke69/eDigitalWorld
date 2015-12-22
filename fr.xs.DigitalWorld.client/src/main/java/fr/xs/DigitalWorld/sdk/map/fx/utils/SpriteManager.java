package fr.xs.DigitalWorld.sdk.map.fx.utils;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.ReplicateScaleFilter;
import java.util.Hashtable;

public class SpriteManager {

	static private Hashtable<String, Image> sprites = null;

	private static String[] sprite_files = {
		"msl5w.png", "msl15w.png", "msl30w.png", "msl45w.png", "msl50w.png", "msl70w.png", "msl90w.png", "msl110w.png", "msl130w.png",
		"msl5y.png", "msl15y.png", "msl30y.png", "msl45y.png", "msl50y.png", "msl70y.png", "msl90y.png", "msl110y.png", "msl130y.png",
		"gdw.png", "UTurnw.png", "UTurny.png", "WrongWayw.png", "WrongWayy.png",
		"car1.png", "car2.png", "car3.png", "car4.png"
	};

	/* Another way of defining a Singleton in java */
	static public final SpriteManager inst;
	static {
		inst = new SpriteManager();
		//inst.loadSprites();
	}
	private SpriteManager() {
		sprites = new Hashtable<String, Image>();
	}
	/**/

	public Image getSprite(String _name, int _w, int _h) {
		if(sprites.get(_name + "_" + _w + "x" + _h) == null)
			loadSpecificSprite(_name, _w, _h);
		return sprites.get(_name + "_" + _w + "x" + _h);
	}

	private void loadSprites() {
		Panel dummy = new Panel();

		sprites = new Hashtable<String, Image>();

		MediaTracker mt = null;
		mt = new MediaTracker(dummy);

		Image buffer   = null;
		Image buffer_1 = null;
		Image buffer_2 = null;

		for(int i = 0; i < sprite_files.length; i++) {
			try {
				buffer = Toolkit.getDefaultToolkit().createImage(SpriteManager.class.getClassLoader().getResource("rc/" + sprite_files[i]));
				mt.addImage(buffer, 0);
				mt.waitForID(0);

				ImageFilter replicate_1 = new ReplicateScaleFilter(256, 256);
				ImageProducer prod_1 = new FilteredImageSource(buffer.getSource(), replicate_1);
				buffer_1 = dummy.createImage(prod_1);
				mt.addImage(buffer_1, 1);
				mt.waitForID(1);

				ImageFilter replicate_2 = new ReplicateScaleFilter(96, 96);
				ImageProducer prod_2 = new FilteredImageSource(buffer.getSource(), replicate_2);
				buffer_2 = dummy.createImage(prod_2);
				mt.addImage(buffer_1, 2);
				mt.waitForID(2);

				mt.removeImage(buffer);
				mt.removeImage(buffer_1);
				mt.removeImage(buffer_2);

			 } catch(InterruptedException e) { e.printStackTrace(); }

			sprites.put(sprite_files[i] + "_512x512", buffer);
			sprites.put(sprite_files[i] + "_256x256", buffer_1);
			sprites.put(sprite_files[i] + "_96x96",   buffer_2);

		}

	}

	private void loadSpecificSprite(String _name, int _w, int _h) {
		System.out.println("Loading sprite ...");
		Panel dummy = new Panel();

		MediaTracker mt = null;
		mt = new MediaTracker(dummy);

		Image buffer   = null;
		Image buffer_1 = null;

		try {
			buffer = Toolkit.getDefaultToolkit().createImage(SpriteManager.class.getClassLoader().getResource("rc/" + _name));
			mt.addImage(buffer, 0);
			mt.waitForID(0);

			ImageFilter replicate_1 = new ReplicateScaleFilter(_w, _h);
			ImageProducer prod_1 = new FilteredImageSource(buffer.getSource(), replicate_1);
			buffer_1 = dummy.createImage(prod_1);
			mt.addImage(buffer_1, 1);
			mt.waitForID(1);

			mt.removeImage(buffer);
			mt.removeImage(buffer_1);
		 } catch(InterruptedException e) { ; }

		sprites.put(_name + "_" + _w + "x" + _h, buffer_1);
		System.out.println("Loading sprite done");

	}

}
