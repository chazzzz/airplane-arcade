package launcher;

import org.noobs2d.gdx.airarc.AirplaneArcade;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 320;
		config.height = 480;
		config.resizable = false;
		config.useGL20 = true;

		new LwjglApplication( AirplaneArcade.inst() , config);
	}
}
