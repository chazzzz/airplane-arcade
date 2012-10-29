package org.noobs2d.gdx.framework.utils;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class PosUtils {
	public static void flipY(Sprite target, float y, float camHeight){
		target.setY( Math.abs( y - camHeight) - target.getHeight());
	}
	
	public static float flipY(float y){
		return Math.abs(y - RenderUtils.getCam().viewportHeight);
	}
	
	public static float flipY(float y, float containerHeight){
		return Math.abs(y - containerHeight);
	}
}
