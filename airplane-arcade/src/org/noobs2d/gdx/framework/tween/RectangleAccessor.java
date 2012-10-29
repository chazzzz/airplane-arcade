package org.noobs2d.gdx.framework.tween;

import org.noobs2d.gdx.framework.DrawableRectangle;

import aurelienribon.tweenengine.TweenAccessor;

public class RectangleAccessor implements TweenAccessor<DrawableRectangle> {

	@Override
	public int getValues(DrawableRectangle target, int tweenType, float[] returnValues) {

		switch (tweenType) {
			case TweenTypes.OPACITY: {
				returnValues[0] = target.fill.a;
				return 1;
			}

			case TweenTypes.POS_X: {
				returnValues[0] = target.bounds.x;
				return 1;
			}

			case TweenTypes.POS_Y: {
				returnValues[0] = target.bounds.y;
				return 1;
			}
		}
		assert false;
		return 0;
	}

	@Override
	public void setValues(DrawableRectangle target, int tweenType, float[] values) {
		switch (tweenType) {
			case TweenTypes.OPACITY:{
				target.fill.a = values[0];
				break;
			}

			default:{
				break;
			}
		}
		
		
	}
}
