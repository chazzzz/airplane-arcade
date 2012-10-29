package org.noobs2d.gdx.airarc.screens;

import org.noobs2d.gdx.airarc.Assets;
import org.noobs2d.gdx.airarc.utils.TweenHelper;
import org.noobs2d.gdx.framework.Action;
import org.noobs2d.gdx.framework.Board;
import org.noobs2d.gdx.framework.DynamicButton;
import org.noobs2d.gdx.framework.Label;
import org.noobs2d.gdx.framework.layout.TableLayout;
import org.noobs2d.gdx.framework.tween.TweenTypes;
import org.noobs2d.gdx.framework.utils.AbstractLoader.LoadTask;
import org.noobs2d.gdx.framework.utils.RenderUtils;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class StageScreen implements Screen{
	
	private Board board;
	
	private Array<DynamicButton> stagesBtn;
	private Sprite bg;
			
	private SpriteBatch batch;
	private BitmapFont fnt;
	
	private OrthographicCamera cam;
	private TableLayout layout;
	
	public StageScreen() {
		cam = RenderUtils.getCam();
		cam.position.set(cam.viewportWidth/2, cam.viewportHeight/2, 0);
		layout = new TableLayout(4, 80, 80, 20, 20);
		board = new Board(layout);
		board.setPosition(40, 300);
		
		stagesBtn = new Array<DynamicButton>();
		Texture tex = Assets.inst().get(  "data/screens/stages/stagesscr.png", Texture.class);
		bg = new Sprite( new TextureRegion(tex));
		
		batch = RenderUtils.getBatch();
		Texture tex2 = Assets.inst().get("data/screens/stages/stgbtn.png",  Texture.class);
		
		fnt = Assets.inst().get("data/fonts/ab/arialblack.fnt", BitmapFont.class);

		RenderUtils.setTextureFilter(TextureFilter.Linear, TextureFilter.Linear, tex, tex2);
		
		//add stage buttons
		for(int x= 0; x<8; x++){
			final DynamicButton spr = new DynamicButton("STAGE_BTN #" + (x+1), new TextureRegion(tex2));
			spr.add(new Label(fnt, "" + (x+1), 20, 40));
			spr.setAction(new Action() {
				
				@Override
				public void doAction() {
					Timeline t = Timeline.createSequence()
							.push(Tween.to(spr, TweenTypes.SCALE_XY, .05f).target(1.2f, 1.2f))
							.push(Tween.to(spr, TweenTypes.SCALE_XY, .05f).target(1f, 1f));
					
					t.setCallback(new TweenCallback() {
						
						@Override
						public void onEvent(int arg0, BaseTween<?> arg1) {
						}
					}).setCallbackTriggers(TweenCallback.END);
					
					t.start(TweenHelper.inst().getTweenManager());
				}
			});
			
			stagesBtn.add(spr);
			board.add(spr);
		}
	}
	
	public static LoadTask getLoadTask(){
		return new LoadTask() {
			
			@Override
			public void load() {
				Assets.inst().load( "data/screens/stages/stagesscr.png", Texture.class);
				Assets.inst().load( "data/screens/stages/stgbtn.png", Texture.class);
				Assets.inst().load( "data/fonts/ab/arialblack.fnt", BitmapFont.class);
			}
		};
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
		

		TweenHelper.inst().getTweenManager().update(arg0);
		RenderUtils.clearScreen();
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		
		batch.begin();
		batch.disableBlending();
		bg.draw(batch);
		batch.enableBlending();
		board.render(batch);
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
}
