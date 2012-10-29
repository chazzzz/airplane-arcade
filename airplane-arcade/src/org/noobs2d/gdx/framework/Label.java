package org.noobs2d.gdx.framework;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Label {
	private final String TAG = "LABEL";
	public float x;
	public float y;
	public BitmapFont fnt;
	public String text;
	
	public Label(BitmapFont fnt, String text, float x, float y) {
		this.fnt = fnt;
		this.text = text;
		this.x = x;
		this.y = y;
		
	}
	
	public void draw(SpriteBatch batch){
		fnt.draw(batch, text, x, y);
	}
	
	public void draw(SpriteBatch batch, float x, float y){
		fnt.draw(batch, text, x, y);
		Gdx.app.log(TAG, "text rendered" );
	}
}
