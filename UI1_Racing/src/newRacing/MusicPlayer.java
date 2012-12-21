package newRacing;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.BooleanControl.Type;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {

	private boolean paused = false;
	private boolean muted = false;
	private boolean mutable = true;
	private Clip clip;
	private int framePosition = 0;

	private BooleanControl muteControl = null;

	public MusicPlayer() {
		try {
			final AudioInputStream audioIn = AudioSystem
					.getAudioInputStream(new File("data/audio/racing_0.wav"));
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			muteControl = (BooleanControl) clip.getControl(Type.MUTE);
		} catch (UnsupportedAudioFileException | IOException
				| LineUnavailableException e) {
			e.printStackTrace();
		} catch (final IllegalArgumentException e) {
			mutable = false;
		}
		play();
	}

	/**
	 * Until now equivalent to pause() respectively play()
	 * 
	 * @param mute
	 */
	public void mute(final boolean mute) {
		if (muteControl != null) {
			muteControl.setValue(mute);
		}
		muted = mute;
	}

	public boolean isMuted() {
		return muted;
	}

	public boolean isMutable() {
		return mutable;
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
