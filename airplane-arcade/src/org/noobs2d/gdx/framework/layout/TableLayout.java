package org.noobs2d.gdx.framework.layout;

import org.noobs2d.gdx.framework.DynamicButton;
import org.noobs2d.gdx.framework.utils.PosUtils;
import org.noobs2d.gdx.framework.utils.RenderUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TableLayout extends Layout {

	public final String TAG = "TABLE_LAYOUT";
	private float	drawableWidth;
	private float	drawableHeight;
	private float	paddingX;
	private float	paddingY;
	private float	cols;
	private Vector2	lastIndex;

	@Override
	public void add(Sprite sprite) {

		Gdx.app.log(TAG, "added sprite: bounds = " +sprite.getBoundingRectangle().toString());
		Gdx.app.log(TAG, "X: " +"" + (lastIndex.x * this.drawableWidth + this.paddingX));
		sprite.setBounds(lastIndex.x * this.drawableWidth + this.paddingX + (lastIndex.x * this.paddingX), lastIndex.y * this.drawableHeight
				+ paddingY +  (lastIndex.y * this.paddingY), this.drawableWidth, this.drawableHeight);
		
		
		lastIndex.x = (lastIndex.x + 1) % cols;

		if (lastIndex.x == 0) {
			lastIndex.y++;
		}

		float y = PosUtils.flipY(sprite.getY(), RenderUtils.getCam().viewportHeight);
		float x = sprite.getX() + root.getX();
		sprite.setY( y- root.getY());
		sprite.setX( x);
		Gdx.app.log(TAG, "xU " + x);
		Gdx.app.log(TAG, "processed sprite: bounds = " +sprite.getBoundingRectangle().toString());
	}

	public TableLayout(float cols, float drawableWidth, float drawableHeight, float paddingX,
			float paddingY) {
		

		this.drawableWidth = drawableWidth;
		this.drawableHeight = drawableHeight;
		this.paddingX = paddingX;
		this.paddingY = paddingY;
		this.cols = cols;
		lastIndex = new Vector2(0, 0);
		
		Gdx.app.log(TAG, "Init Info: dW: " + drawableWidth + ", dH: " + drawableHeight + ", pX: " + this.paddingX + ", pY: " + this.paddingY );
	}
 
	// for unstretchable layout
	// public TableLayout(float layoutWidth, float layoutHeight){
	//
	// }

	@Override
	public void draw(SpriteBatch batch, Sprite sprite) {
		sprite.draw(batch);
	}

	@Override
	public void add(DynamicButton btn) {
		Gdx.app.log(TAG, "added btn: bounds = " +btn.getBoundingRectangle().toString());
		Gdx.app.log(TAG, "X: " +"" + (lastIndex.x * this.drawableWidth + this.paddingX));
		btn.setBounds(lastIndex.x * this.drawableWidth + this.paddingX + (lastIndex.x * this.paddingX), lastIndex.y * this.drawableHeight
				+ paddingY +  (lastIndex.y * this.paddingY), this.drawableWidth, this.drawableHeight);
		
		
		lastIndex.x = (lastIndex.x + 1) % cols;

		if (lastIndex.x == 0) {
			lastIndex.y++;
		}

		float y = PosUtils.flipY(btn.getY(), RenderUtils.getCam().viewportHeight);
		float x = btn.getX() + root.getX();
		btn.setY( y- root.getY());
		btn.setX( x);
		Gdx.app.log(TAG, "xU " + x);
		Gdx.app.log(TAG, "processed btn: bounds = " +btn.getBoundingRectangle().toString());
	}

}
