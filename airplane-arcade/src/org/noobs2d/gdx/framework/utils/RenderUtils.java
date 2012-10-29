package org.noobs2d.gdx.framework.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class RenderUtils {
	
	private static SpriteBatch batch;
	private static OrthographicCamera cam;
	private static ShapeRenderer shapeR;
	
	
	public static void clearScreen(){
		GLCommon gl = Gdx.gl;
		gl.glClearColor(0, 0, 0, 0);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
	}

	public static ShapeRenderer getShapeRenderer(){
		if(shapeR == null){
			shapeR = new ShapeRenderer();
		}
		
		return shapeR;
	}
	public static void clearScreen(Color color){
		GLCommon gl = Gdx.gl;
		gl.glClearColor( color.r, color.g, color.b, color.a);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public static SpriteBatch getBatch() {
		if(batch == null){
			batch = new SpriteBatch();
		}
		return batch;
	}
	
	public static OrthographicCamera getCam(){
		if(cam == null){
			cam = new OrthographicCamera();
		}
		
		return cam;
	}
	
	public static void setTextureFilter(TextureFilter min, TextureFilter max, Texture... textures){
		for(Texture tex: textures){
			tex.setFilter(min, max);
		}
	}
}
