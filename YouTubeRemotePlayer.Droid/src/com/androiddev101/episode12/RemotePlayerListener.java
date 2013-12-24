package com.androiddev101.episode12;

public interface RemotePlayerListener {
	enum STATUS {
		SUCCESS, FAIL;
	}

	public STATUS start(String videoId, VideoCallback callback);

	public STATUS stop();

	public STATUS pause();

	public STATUS play();

	public STATUS seak(int seekTo);

}
