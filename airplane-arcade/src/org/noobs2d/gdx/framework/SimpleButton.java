package org.noobs2d.gdx.framework;

import org.noobs2d.gdx.framework.utils.RenderUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class SimpleButton extends Sprite {

	public TextureRegion	image;
	private Action			action;
	private String			name;

	// private boolean touched;

	public SimpleButton(String name, TextureRegion image) {

		super(image);
		this.name = name;
	}

	public void setAction(Action action) {

		this.action = action;
	}

	public void setPosition(float x, float y) {

		this.setX(x);
		this.setY(y);
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch) {
	
		super.draw(spriteBatch);
		update();
	}

	private void update() {

		Vector3 touchPos = null;
		if (Gdx.input.justTouched()) {
			touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			RenderUtils.getCam().unproject(touchPos);
			float x = touchPos.x;
			float y = touchPos.y;

			if ((x >= this.getX() && x <= this.getX() + this.getRegionWidth())
					&& (y >= this.getY() && y <= this.getY() + this.getRegionHeight())) {
				Gdx.app.log(name, "pressed ... ");
				if (this.action != null) this.action.doAction();
			}
		}
	}
}
