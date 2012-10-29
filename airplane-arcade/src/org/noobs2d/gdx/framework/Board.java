package org.noobs2d.gdx.framework;

import org.noobs2d.gdx.framework.layout.Layout;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Board extends Sprite{
	
	private Layout layout;
	private Array<Sprite> drawables;
	public Board(Layout layout){
		this.layout = layout;
		layout.setRoot(this);
		drawables = new Array<Sprite>();
	}
	
	public void add(Sprite sprite){
		drawables.add(sprite);
		layout.add(sprite);
	}
	
	public void render(SpriteBatch batch){
		for(Sprite drawable: drawables){
			this.layout.draw(batch, drawable);
		}
	}
}
