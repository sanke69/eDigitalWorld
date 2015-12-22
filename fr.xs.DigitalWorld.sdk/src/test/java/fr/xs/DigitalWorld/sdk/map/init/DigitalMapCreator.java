package fr.xs.DigitalWorld.sdk.map.init;

import fr.xs.jtk.databases.DatabaseManager;
import fr.xs.jtk.databases.beans.DatabaseAccountBean;
import fr.xs.jtk.databases.beans.DatabaseBean;
import fr.xs.jtk.helpers.MediaHelper;

public class DigitalMapCreator {

	public static void main(String[] args) {
//		DatabaseAccountBean      root     = new DatabaseAccountBean("192.168.0.253", "root", "-");
//		DatabaseAccountBean      user     = new DatabaseAccountBean("%", "spwebuser", "sp-web.fr"); // change % to localhost

		DatabaseAccountBean      root     = new DatabaseAccountBean("192.168.0.169", "root", "pi");
		DatabaseAccountBean      user     = new DatabaseAccountBean(            "%",   "pi", "pi"); // change % to localhost

		DatabaseBean             database = DatabaseManager.loadModelsFromJSON(
												MediaHelper.getContent(DigitalMapCreator.class, "json/ldm_database.json")).iterator().next();
		DatabaseBean             db_main  = DatabaseManager.loadModelFromJSON(
												MediaHelper.getContent(DigitalMapCreator.class, "json/DigitalMap.json"));
		DatabaseBean             db_extra = DatabaseManager.loadModelFromJSON(
												MediaHelper.getContent(DigitalMapCreator.class, "json/DigitalMap.json"));

		System.out.println(db_main);
		System.out.println(db_extra);
		DatabaseManager.create(root, user, database);
	}

}
