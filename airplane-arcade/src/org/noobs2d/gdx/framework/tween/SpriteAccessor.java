package org.noobs2d.gdx.framework.tween;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com
 */
public class SpriteAccessor implements TweenAccessor<Sprite> {
	
	@Override
	public int getValues(Sprite target, int tweenType, float[] returnValues) {
		switch (tweenType) {
			case TweenTypes.POS_XY:
				returnValues[0] = target.getX();
				returnValues[1] = target.getY();
				return 2;

			case TweenTypes.CPOS_XY:
				returnValues[0] = target.getX() + target.getWidth()/2;
				returnValues[1] = target.getY() + target.getHeight()/2;
				return 2;

			case TweenTypes.SCALE_XY:
				returnValues[0] = target.getScaleX();
				returnValues[1] = target.getScaleY();
				return 2;

			case TweenTypes.ROTATION: returnValues[0] = target.getRotation(); return 1;
			case TweenTypes.OPACITY: returnValues[0] = target.getColor().a; return 1;

			case TweenTypes.TINT:
				returnValues[0] = target.getColor().r;
				returnValues[1] = target.getColor().g;
				returnValues[2] = target.getColor().b;
				return 3;

			default: assert false; return -1;
		}
	}

	@Override
	public void setValues(Sprite target, int tweenType, float[] newValues) {
		switch (tweenType) {
			case TweenTypes.POS_XY: target.setPosition(newValues[0], newValues[1]); break;
			case TweenTypes.CPOS_XY: target.setPosition(newValues[0] - target.getWidth()/2, newValues[1] - target.getHeight()/2); break;
			case TweenTypes.SCALE_XY: target.setScale(newValues[0], newValues[1]); break;
			case TweenTypes.ROTATION: target.setRotation(newValues[0]); break;

			case TweenTypes.OPACITY:
				Color c = target.getColor();
				c.set(c.r, c.g, c.b, newValues[0]);
				target.setColor(c);
				break;

			case TweenTypes.TINT:
				c = target.getColor();
				c.set(newValues[0], newValues[1], newValues[2], c.a);
				target.setColor(c);
				break;

			default: assert false;
		}
	}
}
