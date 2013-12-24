package com.androiddev101.episode12;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;


public class YouTubeIt extends YouTubeBaseActivity 
implements OnInitializedListener {
	public static final String EP1_ID = "b7pjiYr-Bpo";
	public static final String EP2_ID = "U4YR67ndqlc";
	public static final String EP3_ID = "VZEWqfjOZbU";
	
	
	ImageButton playButton;
	ImageButton pauseButton;
	
	
	YouTubeThumbnailView ep1View;	
	YouTubeThumbnailView ep2View;	
	YouTubeThumbnailView ep3View;
	
	ThumbnailListener thumbListner;
	
	private YouTubePlayerView playerView; 
	private YouTubePlayer playa;   //hollla
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_you_tube_it);
		
		playButton = (ImageButton)findViewById(R.id.play_button);
		playButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				pauseButton.setVisibility(View.VISIBLE);
				playButton.setVisibility(View.INVISIBLE);
				playa.play();
			}
		});
		pauseButton = (ImageButton)findViewById(R.id.pause_button);
		pauseButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				playButton.setVisibility(View.VISIBLE);
				pauseButton.setVisibility(View.INVISIBLE);
				playa.pause();
			}
		});
		
		ep1View = (YouTubeThumbnailView)findViewById(R.id.youtube_view1);
		ep1View.initialize(DeveloperKey.DEVELOPER_KEY, new ThumbnailListener(EP1_ID));
		ep2View = (YouTubeThumbnailView)findViewById(R.id.youtube_view2);
		ep2View.initialize(DeveloperKey.DEVELOPER_KEY, new ThumbnailListener(EP2_ID));
		ep3View = (YouTubeThumbnailView)findViewById(R.id.youtube_view3);
		ep3View.initialize(DeveloperKey.DEVELOPER_KEY, new ThumbnailListener(EP3_ID));
		
		playerView = (YouTubePlayerView) findViewById(R.id.youtube_player_view);
		playerView.initialize(DeveloperKey.DEVELOPER_KEY, this);
		playa = null;
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.you_tube_it, menu);
		return true;
	}

	/**
	 * YouTubePlayerView init listeners
	 */
	@Override
	public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult error)
	{
		Toast.makeText(YouTubeIt.this,
				"Oh dear, something has gone terribly wrong please try ejecting and re-inserting your diskette", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onInitializationSuccess(YouTubePlayer.Provider provider,
			YouTubePlayer player, boolean wasRestored) {
		playa = player;
		playa.setPlaybackEventListener(new MHPlaybackEventListener());
		playa.setPlayerStateChangeListener(new MHPlayerStateChangeListener());
		playa.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
	}
	/**
	 * Thumbnail Listener
	 * @author  <- .. -> squint hedgehog
	 *
	 */
	private class ThumbnailListener implements YouTubeThumbnailView.OnInitializedListener{
		
		String videoID;
		
		public ThumbnailListener(String vidId){
			videoID = vidId;
		}

		@Override
		public void onInitializationFailure(YouTubeThumbnailView thumbView,
				YouTubeInitializationResult initResult) {
			
		}

		@Override
		public void onInitializationSuccess(YouTubeThumbnailView thumbView,
				YouTubeThumbnailLoader thumbLoader) {
			thumbLoader.setVideo(videoID);
			thumbView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					playa.cueVideo(videoID);
				}
			});
		}
		
	}
	
	/**
	 * Listener classes
	 * @author Robot King Zed
	 *
	 */
	private class MHPlaybackEventListener implements PlaybackEventListener {

		@Override
		public void onBuffering(boolean arg0) {
			Toast.makeText(YouTubeIt.this, "onBuffering video...", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onPaused() {
			Toast.makeText(YouTubeIt.this, "onPaused video...", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onPlaying() {
			Toast.makeText(YouTubeIt.this, "onPlaying video...", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onSeekTo(int arg0) {
			Toast.makeText(YouTubeIt.this, "onSeekTo video...", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onStopped() {
			Toast.makeText(YouTubeIt.this, "onStopped video...", Toast.LENGTH_SHORT).show();
		}
	}

	private class MHPlayerStateChangeListener implements PlayerStateChangeListener  {

		@Override
		public void onAdStarted() {
			Toast.makeText(YouTubeIt.this, "onAdStarted video...", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onError(ErrorReason arg0) {
			Toast.makeText(YouTubeIt.this, "onError video...", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onLoaded(String arg0) {
			playa.play();
			pauseButton.setVisibility(View.VISIBLE);
			
		}

		@Override
		public void onLoading() {
			Toast.makeText(YouTubeIt.this, "onLoading video...", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onVideoEnded() {
			Toast.makeText(YouTubeIt.this, "onVideoEnded video...", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onVideoStarted() {
			Toast.makeText(YouTubeIt.this, "onVideoStarted video...", Toast.LENGTH_SHORT).show();
		}

	}

}
