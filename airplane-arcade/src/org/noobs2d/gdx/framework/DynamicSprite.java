package org.noobs2d.gdx.framework;

import org.noobs2d.gdx.framework.utils.PosUtils;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class DynamicSprite extends Sprite {
	
	private Array<Label> fonts;
	private Array<Sprite> sprites;
	
	public DynamicSprite() {
		super();
		fonts = new Array<Label>();
		sprites = new Array<Sprite>();
	}
	
	public DynamicSprite(TextureRegion tex){
		super(tex);
		fonts = new Array<Label>();
		sprites = new Array<Sprite>();
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch) {
	
		super.draw(spriteBatch);
		for(Label label: fonts){
			label.draw(spriteBatch , label.x + this.getX(), label.y + this.getY());
		}
		
//		for(Sprite sprite: sprites){
//			
//			sprite.draw(spriteBatch);
//		}
	}
	
	public void add(Label label){
//		float y = PosUtils.flipY( label.y, this.getHeight());
//		label.y = y;
		fonts.add(label);
	}
	
	public void add(Sprite sprite){
		float y = PosUtils.flipY( sprite.getY());
		y -= this.getY();
		sprite.setY(y);
		sprites.add(sprite);
	}
}
