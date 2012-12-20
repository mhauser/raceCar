package newRacing;

public class MusicPlayer {

	private boolean paused;
	private boolean muted;

	public MusicPlayer() {
	}

	public void mute(final boolean mute) {
		// TODO
		muted = mute;
	}

	public boolean isMuted() {
		return muted;
	}

	public void previous() {
		// TODO
	}

	public void next() {
		// TODO
	}

	public void pause() {
		paused = true;
		// TODO
	}

	public void play() {
		paused = false;
		// TODO
	}

	public boolean isPaused() {
		return paused;
	}

	public void checkpointSignal() {
		pause();
		// TODO
		play();
	}
}
