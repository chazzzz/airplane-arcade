package org.noobs2d.gdx.framework;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;


public class DynamicButton extends SimpleButton {

	private Array<Label> fonts;
	public DynamicButton(String name, TextureRegion image) {

		super(name, image);
		fonts = new Array<Label>();
	}


	@Override
	public void draw(SpriteBatch spriteBatch) {
	
		super.draw(spriteBatch);
		for(Label label: fonts){
			label.draw(spriteBatch , label.x + this.getX(), label.y + this.getY());
		}
	}
		
	
	public void add(Label label){
//		float y = PosUtils.flipY( label.y, this.getHeight());
//		label.y = y;
		fonts.add(label);
	}
}
