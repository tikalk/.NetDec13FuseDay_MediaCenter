package com.androiddev101.episode12;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

@SuppressLint("NewApi")
public class RemotePlayer extends YouTubeBaseActivity implements
OnInitializedListener, RemotePlayerListener {

	private YouTubePlayer player;
	private YouTubePlayerView playerView;
	private VideoCallback videoCallback;
	private String videoId;
	private WebView leWeb;
	private WebSettings leWebSettings;
	private Toaster leToaster;
	public class Toaster{
		Context mContext;

		/** Instantiate the interface and set the context */
		Toaster(Context c) {
			mContext = c;
		}

		/** Show a toast from the web page */
		@JavascriptInterface
		public void showToast(String toast) {
			Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
		}
	}

	public class YouTubeBridge {

		AndroidVideoCallback videoCallback;
		RemotePlayerListener listener;

		public YouTubeBridge(RemotePlayerListener listener) {
			this.videoCallback = new AndroidVideoCallback(leWeb);
			this.listener = listener;
		}

		@JavascriptInterface
		public void start(final String videoId) {
			listener.start(videoId, videoCallback);
		}

		@JavascriptInterface
		public void play() {
			listener.play();
		}

		@JavascriptInterface
		public void pause() {
			listener.pause();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remote_player);

		leWeb = (WebView)findViewById(R.id.webView);
		leWeb.loadUrl("http://tikal-fuze.cloudapp.net/#/player");
		leWebSettings = leWeb.getSettings();
		leWebSettings.setJavaScriptEnabled(true);
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			WebView.setWebContentsDebuggingEnabled(true);
		}
		leToaster = new Toaster(this);
		leWeb.addJavascriptInterface(leToaster, "Toaster");
		playerView = (YouTubePlayerView) findViewById(R.id.youtube_player_view);
		playerView.initialize(DeveloperKey.DEVELOPER_KEY, this);
//		playerView.animate().alpha(0).setDuration(3000).rotationX(100).rotationY(100).start();
		player = null;       
		YouTubeBridge bridge = new YouTubeBridge(this);
		leWeb.addJavascriptInterface(bridge, "JBridge");
		leWeb.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url){
				leWeb.loadUrl("http://tikal-fuze.cloudapp.net/#/player");
				return false; 
			}
		});

	}

	@Override
	public STATUS start(String videoId, VideoCallback callback) {
		this.videoCallback = callback;
		player.cueVideo(videoId);
		this.videoId = videoId;
		return STATUS.SUCCESS;
	}

	@Override
	public STATUS stop() {
		player.pause();
				AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);
	alphaAnimation.setDuration(3000);
	alphaAnimation.setFillAfter(true);
	playerView.startAnimation(alphaAnimation);
		return STATUS.SUCCESS;
	}

	@Override
	public STATUS pause() {
		player.pause();
	AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);
	alphaAnimation.setDuration(3000);
	alphaAnimation.setFillAfter(true);
	playerView.startAnimation(alphaAnimation);
		return STATUS.SUCCESS;
	}

	@Override
	public STATUS play() {
		player.play();
		AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
		alphaAnimation.setDuration(3000);
		alphaAnimation.setFillAfter(true);
		playerView.startAnimation(alphaAnimation);
		return STATUS.SUCCESS;
	}


	@Override
	public STATUS seak(int seekTo) {
		player.seekToMillis(seekTo);
		return STATUS.SUCCESS;
	}

	@Override
	public void onInitializationFailure(Provider arg0,
			YouTubeInitializationResult arg1) {

	}

	@Override
	public void onInitializationSuccess(Provider arg0, YouTubePlayer player,
			boolean arg2) {
		this.player = player;
		this.player.setPlaybackEventListener(new MHPlaybackEventListener());
		this.player
		.setPlayerStateChangeListener(new MHPlayerStateChangeListener());
		this.player.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);

	}

	private class MHPlaybackEventListener implements PlaybackEventListener {

		@Override
		public void onBuffering(boolean arg0) {
			Toast.makeText(RemotePlayer.this, "onBuffering video...",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onPaused() {
			Toast.makeText(RemotePlayer.this, "onPaused video...",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onPlaying() {
			Toast.makeText(RemotePlayer.this, "onPlaying video...",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onSeekTo(int arg0) {
			Toast.makeText(RemotePlayer.this, "onSeekTo video...",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onStopped() {

			Toast.makeText(RemotePlayer.this, "onStopped video...",
					Toast.LENGTH_SHORT).show();
		}
	}

	private class MHPlayerStateChangeListener implements
	PlayerStateChangeListener {

		@Override
		public void onAdStarted() {
			Toast.makeText(RemotePlayer.this, "onAdStarted video...",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onError(ErrorReason arg0) {
			Toast.makeText(RemotePlayer.this, "onError video...",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onLoaded(String arg0) {

			//start animation
			AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
			alphaAnimation.setDuration(3000);
			alphaAnimation.setFillAfter(true);
			playerView.startAnimation(alphaAnimation);
			player.play();
			if (videoCallback != null){
				videoCallback.onVideoLoaded(videoId);
			}
		}

		@Override
		public void onLoading() {
			Toast.makeText(RemotePlayer.this, "onLoading video...",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onVideoEnded() {
			Toast.makeText(RemotePlayer.this, "onVideoEnded video...",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onVideoStarted() {
			Toast.makeText(RemotePlayer.this, "onVideoStarted video...",
					Toast.LENGTH_SHORT).show();
		}

	}

}
