package com.androiddev101.episode12;

import android.webkit.WebView;


public class AndroidVideoCallback implements VideoCallback {

	WebView view;
	
	public AndroidVideoCallback(WebView view) {
		super();
		this.view = view;
	}

	@Override
	public void onVideoLoaded(String videoId) {
		view.loadUrl("javascript:playLoaded(" + videoId  + ")");
	}

}
