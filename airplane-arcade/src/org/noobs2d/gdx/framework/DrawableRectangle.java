package org.noobs2d.gdx.framework;

import org.noobs2d.gdx.framework.utils.RenderUtils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;

public class DrawableRectangle {

	public Rectangle		bounds;
	private ShapeRenderer	shapeR;
	public Color			fill;

	public DrawableRectangle(Color color) {

		shapeR = RenderUtils.getShapeRenderer();
		bounds = new Rectangle();
		fill = color;
		shapeR.setColor( fill);
	}
	
	public void render() {

		shapeR.begin(ShapeType.FilledRectangle);
		shapeR.setColor(fill);
		shapeR.filledRect(bounds.x, bounds.y, bounds.width, bounds.height);
		shapeR.end();
	}

	public void render(Matrix4 projectionMatrix) {
		shapeR.setColor(fill);
		shapeR.setProjectionMatrix(projectionMatrix);
		shapeR.begin(ShapeType.FilledRectangle);
		shapeR.filledRect(bounds.x, bounds.y, bounds.width, bounds.height);
		shapeR.end();
	}
	
}
