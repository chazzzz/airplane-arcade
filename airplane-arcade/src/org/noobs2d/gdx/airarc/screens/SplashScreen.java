package org.noobs2d.gdx.airarc.screens;

import org.noobs2d.gdx.airarc.Assets;
import org.noobs2d.gdx.airarc.utils.Loader;
import org.noobs2d.gdx.airarc.utils.TweenHelper;
import org.noobs2d.gdx.framework.DrawableRectangle;
import org.noobs2d.gdx.framework.tween.TweenTypes;
import org.noobs2d.gdx.framework.utils.AbstractLoader;
import org.noobs2d.gdx.framework.utils.RenderUtils;
import org.noobs2d.gdx.framework.utils.AbstractLoader.LoadTask;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Back;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreen implements Screen {
	private static final String	TAG	= "SPLASH_SCREEN";

	private DrawableRectangle	bg;
	private Sprite				chazzLogo;
	private Sprite				teamLogo;

	private SpriteBatch			batch;
	private OrthographicCamera	cam;

	private TweenManager		manager;

	public SplashScreen() {

		Gdx.app.log(TAG, "instance created");
		batch = RenderUtils.getBatch();
		cam = RenderUtils.getCam();
		manager = TweenHelper.inst().getTweenManager();

		bg = new DrawableRectangle(Color.WHITE);
		bg.bounds.set(0, 0, cam.viewportWidth, cam.viewportHeight);
		bg.fill.a = 0;

		Texture text = Assets.inst().get("data/screens/splash/chazz_logo.png", Texture.class);
		chazzLogo = new Sprite(text);
		chazzLogo.setPosition(RenderUtils.getCam().viewportWidth / 2 - chazzLogo.getWidth() / 2
				- 40, RenderUtils.getCam().viewportHeight / 2);

		teamLogo = new Sprite(Assets.inst().get("data/screens/splash/noobs2d_logo.png",
				Texture.class));

		text.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		teamLogo.getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		teamLogo.setPosition(-teamLogo.getWidth(), 0);
	}

	@Override
	public void dispose() {

		this.dispose();
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void render(float arg0) {

		manager.update(arg0);
		RenderUtils.clearScreen();

		cam.update();
		batch.setProjectionMatrix(cam.combined);

		bg.render(cam.combined);
		batch.begin();
		chazzLogo.draw(batch);
		teamLogo.draw(batch);
		batch.end();

	}

	@Override
	public void resize(int arg0, int arg1) {

	}

	@Override
	public void resume() {

	}

	@Override
	public void show() {

		Timeline t = Timeline.createSequence();
		t.setCallback(new TweenCallback() {

			@Override
			public void onEvent(int type, BaseTween<?> arg1) {

				if (type == TweenCallback.END) {
					// set next screen
					Loader.inst().startLoading(MainMenuScreen.getLoadTask(), MainMenuScreen.class);
				}
			}
		}).setCallbackTriggers(TweenCallback.END);

		startAnimation(t);
	}

	private void startAnimation(Timeline t) {

		t.beginSequence()
				.push(Tween.set(chazzLogo, TweenTypes.OPACITY).target(0))
				.push(Tween.to(bg, TweenTypes.OPACITY, .5f).target(1))
				.push(Tween.to(chazzLogo, TweenTypes.OPACITY, .5f).target(1))
				.push(Tween.set(teamLogo, TweenTypes.POS_XY).target(-teamLogo.getWidth(),
						RenderUtils.getCam().viewportHeight / 2 - 50f))
				.push(Tween.to(teamLogo, TweenTypes.POS_XY, 0.4f)
						.target(200f, RenderUtils.getCam().viewportHeight / 2 - 60f).ease(Back.OUT))
				.end().beginParallel()
				.push(Tween.to(teamLogo, TweenTypes.OPACITY, .5f).target(0).delay(1f))
				.push(Tween.to(chazzLogo, TweenTypes.OPACITY, .5f).target(0).delay(1f)).end()
				.push(Tween.to(bg, TweenTypes.OPACITY, .5f).target(0));

		t.start(manager);
	}

	public static AbstractLoader.LoadTask getLoadTask() {

		return new LoadTask() {

			@Override
			public void load() {

				Assets.inst().load("data/screens/splash/chazz_logo.png", Texture.class);
				Assets.inst().load("data/screens/splash/noobs2d_logo.png", Texture.class);
			}
		};
	}
}
