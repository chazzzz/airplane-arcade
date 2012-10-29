package org.noobs2d.gdx.airarc.utils;

import org.noobs2d.gdx.airarc.AirplaneArcade;
import org.noobs2d.gdx.airarc.Assets;
import org.noobs2d.gdx.framework.utils.AbstractLoader;
import org.noobs2d.gdx.framework.utils.RenderUtils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Loader extends AbstractLoader {
	public static final String		TAG	= "LOADER";

	private static AbstractLoader	inst;
	private TextureRegion			bg;

	public static AbstractLoader inst() {

		if (inst == null) {
			inst = new Loader();
		}

		return inst;
	} // singleton implementation

	public Loader() {
 
		super(AirplaneArcade.inst(), Assets.inst());
	}

	@Override
	public void draw() {

		RenderUtils.clearScreen();

		cam.update();
		cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);
		batch.setProjectionMatrix(cam.combined);

		float progress = Assets.inst().getProgress();

		batch.disableBlending();
		batch.begin();
		batch.draw(bg, 0, 0, bg.getRegionWidth(), bg.getRegionHeight());
		batch.enableBlending();
		batch.end();
		shapeR.setProjectionMatrix(cam.combined);
		shapeR.begin(ShapeType.FilledRectangle);
		shapeR.setColor(Color.WHITE);
		shapeR.filledRect(20, 20, 440 * progress, 40);
		shapeR.end();

	}

	@Override
	protected void loadScreenAssets() {
		manager.load("data/screens/loading/pack/loading.pack", TextureAtlas.class);
	}

	@Override
	protected void initRenderFields() {

		TextureAtlas atlas = manager.get("data/screens/loading/pack/loading.pack",
				TextureAtlas.class);
		bg = atlas.findRegion("loading_screen");
	}
}
