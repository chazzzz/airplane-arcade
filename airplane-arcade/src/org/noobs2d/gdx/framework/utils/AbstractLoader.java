package org.noobs2d.gdx.framework.utils;

import org.noobs2d.gdx.airarc.Assets;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class AbstractLoader implements Screen {

	private final static String			TAG	= "ABSTRACT_LOADER";

	private boolean						primaryAssetsLoaded;
	private boolean						fieldsInitialized;

	protected SpriteBatch				batch;
	protected OrthographicCamera		cam;
	protected ShapeRenderer				shapeR;

	protected Class<? extends Screen>	nextScreen;

	protected LoadTask					loadTask;
	protected AssetManager				manager;
	private Game						root;

	protected abstract void loadScreenAssets(); // load loading screen assets
												// first

	public abstract void draw(); // drawing for loading screen

	protected AbstractLoader(Game root, AssetManager manager) {

		nextScreen = null;
		this.root = root;
		this.manager = manager;

		primaryAssetsLoaded = false;
		fieldsInitialized = false;
		shapeR = new ShapeRenderer();
		cam = RenderUtils.getCam();

		batch = new SpriteBatch();

		loadScreenAssets();
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
	public void render(float deltaTime) {

		if (!primaryAssetsLoaded) {
			if (Assets.inst().getProgress() < 1) {
				Assets.inst().update();
			} else {
				primaryAssetsLoaded = true;
			}

		} else {
			if (!fieldsInitialized) {
				initRenderFields();
				fieldsInitialized = true;
				if (loadTask == null) {
					Gdx.app.error(TAG, "Load task is null. Exiting...");
					Gdx.app.exit();
				}
				this.loadTask.load();
				loadTask = null;
			}

			draw();
			update();
		}
	}

	private void update() {

		float progress = manager.getProgress();
		if (progress == 1) {
			if (nextScreen == null) {
				Gdx.app.error(TAG, "Next Screen hasn't been set yet. Exiting...");
				Gdx.app.exit();
			}

		} else {
			manager.update();
		}
	}

	public void setScreen() {

		if (nextScreen == null) {
			Gdx.app.error(TAG, "NextScreen is null. Screen could not be set");
			return;
		}

		try {
			Screen screen = nextScreen.newInstance();
			root.setScreen(screen);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		nextScreen = null;
	}

	public void setLoadTask(LoadTask task) {

		loadTask = task;
	}

	protected abstract void initRenderFields();

	@Override
	public void resize(int arg0, int arg1) {

	}

	@Override
	public void resume() {

	}

	@Override
	public void show() {

		Gdx.app.log(TAG, "screen showed");
	}

	public void setNextScreen(Class<Screen> cls) {

		this.nextScreen = cls;
	}

	public interface LoadTask {
		public void load();
	}

	public void showScreenAndStartLoading() {

		root.setScreen(this);
	}

	public void startLoading(LoadTask loadTask, Class<? extends Screen> cls) {

		this.loadTask = loadTask;
		this.nextScreen = cls;
		this.startLoading();
	}

	public void startLoading() {

		loadTask.load();
		float progress = manager.getProgress();
		while (progress<1) {
			manager.update();
			progress = manager.getProgress();
		}

		loadTask = null;
		setScreen();
	}

	public void showScreenAndStartLoading(LoadTask loadTask, Class<? extends Screen> cls) {

		this.loadTask = loadTask;
		this.nextScreen = cls;

		setScreen();
	}

}
