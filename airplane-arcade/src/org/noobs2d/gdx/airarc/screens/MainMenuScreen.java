package org.noobs2d.gdx.airarc.screens;

import org.noobs2d.gdx.airarc.Assets;
import org.noobs2d.gdx.airarc.utils.Loader;
import org.noobs2d.gdx.airarc.utils.TweenHelper;
import org.noobs2d.gdx.framework.Action;
import org.noobs2d.gdx.framework.SimpleButton;
import org.noobs2d.gdx.framework.tween.TweenTypes;
import org.noobs2d.gdx.framework.utils.AbstractLoader;
import org.noobs2d.gdx.framework.utils.AbstractLoader.LoadTask;
import org.noobs2d.gdx.framework.utils.RenderUtils;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MainMenuScreen implements Screen {

	private SimpleButton			playBtn;
	private Sprite					bg;
	private static MainMenuScreen	inst;
	private SpriteBatch				batch;
	private TweenManager			manager;
	private OrthographicCamera		cam;

	public static MainMenuScreen inst() {

		if (inst == null) inst = new MainMenuScreen();

		return inst;
	}

	public MainMenuScreen() {

		Texture tex = Assets.inst().get("data/screens/mm/playbtn.png", Texture.class);
		playBtn = new SimpleButton("PLAY_BTN", new TextureRegion(tex));
		playBtn.setPosition(137.767f, 200);
		playBtn.setAction(new Action() {

			@Override
			public void doAction() {

				Timeline t = Timeline.createSequence()
						.push(Tween.to(playBtn, TweenTypes.SCALE_XY, .02f).target(1.2f, 1.2f))
						.push(Tween.to(playBtn, TweenTypes.SCALE_XY, .02f).target(1f, 1f));

				t.setCallback(new TweenCallback() {

					@Override
					public void onEvent(int arg0, BaseTween<?> arg1) {

						if (arg0 == TweenCallback.END) Loader.inst().startLoading(
								StageScreen.getLoadTask(), StageScreen.class);
					}
				}).setCallbackTriggers(TweenCallback.END);

				t.start(manager);

			}
		});
		playBtn.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Linear);

		Texture tex2 = Assets.inst().get("data/screens/mm/airarc-banner.png", Texture.class);
		RenderUtils.setTextureFilter(TextureFilter.Linear, TextureFilter.Linear, tex, tex2);
		bg = new Sprite(tex2);

		cam = RenderUtils.getCam();
		batch = RenderUtils.getBatch();
		manager = TweenHelper.inst().getTweenManager();
	}

	@Override
	public void dispose() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void render(float arg0) {

		RenderUtils.clearScreen();
		manager.update(arg0);
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		bg.draw(batch);
		playBtn.draw(batch);
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

	}

	public static AbstractLoader.LoadTask getLoadTask() {

		return new LoadTask() {

			@Override
			public void load() {

				Assets.inst().load("data/screens/mm/playbtn.png", Texture.class);
				Assets.inst().load("data/screens/mm/airarc-banner.png", Texture.class);
			}
		};
	}
}
