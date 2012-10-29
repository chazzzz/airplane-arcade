package org.noobs2d.gdx.airarc;

import org.noobs2d.gdx.airarc.screens.SplashScreen;
import org.noobs2d.gdx.airarc.utils.Loader;
import org.noobs2d.gdx.framework.DrawableRectangle;
import org.noobs2d.gdx.framework.tween.RectangleAccessor;
import org.noobs2d.gdx.framework.tween.SpriteAccessor;
import org.noobs2d.gdx.framework.utils.RenderUtils;

import aurelienribon.tweenengine.Tween;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class AirplaneArcade extends Game {

	private static AirplaneArcade	inst;

	public static AirplaneArcade inst() {

		if (inst == null) {
			inst = new AirplaneArcade();
		}

		return inst;
	}

	@Override
	public void create() {	
		//register accessors
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		Tween.registerAccessor(DrawableRectangle.class, new RectangleAccessor());
		
		//initialize render objects
		RenderUtils.getBatch(); 
		OrthographicCamera cam = RenderUtils.getCam();
		cam.setToOrtho( false, 480, 800);
		cam.position.set( cam.viewportWidth/2 , cam.viewportHeight/2, 0);
		
		//start loading next screen
		Loader.inst().startLoading( SplashScreen.getLoadTask(), SplashScreen.class);
//		Loader.inst().startLoading( MainMenuScreen.getLoadTask(), MainMenuScreen.class);
//		Loader.inst().startLoading(StageScreen.getLoadTask(), StageScreen.class);
	}

	//prohibit external initiation
	private AirplaneArcade() {

	}

}
