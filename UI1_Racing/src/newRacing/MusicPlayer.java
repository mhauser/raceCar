package newRacing;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {

	private boolean paused = false;
	private boolean muted = false;
	private Clip clip;
	private int framePosition = 0;

	public MusicPlayer() {
		try {
			final AudioInputStream audioIn = AudioSystem
					.getAudioInputStream(new File("data/audio/racing_0.wav"));
			clip = AudioSystem.getClip();
			clip.open(audioIn);
		} catch (UnsupportedAudioFileException | IOException
				| LineUnavailableException e) {
			e.printStackTrace();
		}
		play();
	}

	/**
	 * Until now equivalent to pause() respectively play()
	 * 
	 * @param mute
	 */
	public void mute(final boolean mute) {
		// TODO find solution for real mute?!
		if (mute) {
			pause();
		} else {
			play();
		}
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
		if (clip.isRunning()) {
			clip.stop();
		}
		framePosition = clip.getFramePosition();
		// TODO
	}

	public void play() {
		paused = false;
		if (clip.isRunning()) {
			clip.stop();
		}
		clip.setFramePosition(framePosition);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
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
