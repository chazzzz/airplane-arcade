package org.noobs2d.gdx.airarc;

import com.badlogic.gdx.assets.AssetManager;

public class Assets extends AssetManager{
	private static Assets inst;
	
	
	//singleton
	public static Assets inst(){
		if( inst == null )
			inst = new Assets();
		
		return inst;
	}
	
	//prevent init
	private Assets(){
	}
	
}
