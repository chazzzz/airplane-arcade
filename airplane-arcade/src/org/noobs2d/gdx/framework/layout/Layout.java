package org.noobs2d.gdx.framework.layout;

import org.noobs2d.gdx.framework.DynamicButton;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Layout {
	public abstract void add(Sprite sprite);
	public abstract void add(DynamicButton btn);
	protected Sprite root;
	public void draw(SpriteBatch batch, Sprite sprite){
		
		sprite.draw(batch);
	}
	

	public void setRoot(Sprite sprite) {
		this.root = sprite;
	}
}
