package org.noobs2d.gdx.airarc.utils;

import aurelienribon.tweenengine.TweenManager;

public class TweenHelper {
	private static TweenHelper	inst;
	private TweenManager manager;
	
	public static TweenHelper inst() {

		if (inst == null) {
			inst = new TweenHelper();
		}

		return inst;
	}
	
	private TweenHelper() {
		
	}
	
	public TweenManager getTweenManager(){
		if(manager == null){
			manager = new TweenManager();
		}
		
		return manager;
	}
}
