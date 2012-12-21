package newRacing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.BooleanControl.Type;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

public class MusicPlayer implements LineListener {

	private boolean paused = false;
	private boolean muted = false;
	private boolean mutable = true;
	private Clip clip;
	private List<File> tracks;
	private int trackIndex = 0;
	private int framePosition = 0;

	private BooleanControl muteControl = null;
	private FloatControl gainControl = null;

	public MusicPlayer() {
		try {
			clip = AudioSystem.getClip();
			initTracks();
			if (isMusicAvailable()) {
				next();
			} else {
				JOptionPane.showOptionDialog(null,
						"No supported music file in directory /data/audio",
						"Racing", JOptionPane.OK_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, new Object[] { "Ok" },
						"Ok");
			}
		} catch (final LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	private void initTracks() {
		tracks = new ArrayList<>();
		final File tracksDir = new File("data/audio");
		if (!tracksDir.exists() || !tracksDir.isDirectory()) {
			return;
		}

		for (final File f : tracksDir.listFiles()) {
			if (f.isDirectory()) {
				continue;
			}
			try {
				final AudioFileFormat aff = AudioSystem.getAudioFileFormat(f);
				if (AudioSystem.isFileTypeSupported(aff.getType())) {
					tracks.add(f);
				}
			} catch (UnsupportedAudioFileException | IOException e) {
				continue;
			}
		}
		Collections.shuffle(tracks);
	}

	private void setMuteControls() {
		if (clip.isControlSupported(BooleanControl.Type.MUTE)) {
			muteControl = (BooleanControl) clip.getControl(Type.MUTE);
		} else if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
			gainControl = (FloatControl) clip
					.getControl(FloatControl.Type.MASTER_GAIN);
		} else if (clip.isControlSupported(FloatControl.Type.VOLUME)) {
			gainControl = (FloatControl) clip
					.getControl(FloatControl.Type.VOLUME);
		} else {
			mutable = false;
		}
	}

	public void mute(final boolean mute) {
		if (muteControl != null) {
			muteControl.setValue(mute);
		} else if (gainControl != null) {
			gainControl.setValue(mute ? gainControl.getMinimum() : gainControl
					.getMaximum());
		}
		muted = mute;
	}

	public boolean isMuted() {
		return muted;
	}

	public boolean isMutable() {
		return mutable;
	}

	public boolean isMusicAvailable() {
		return tracks.size() > 0;
	}

	public void previous() {
		next(-1);
	}

	public void next() {
		next(1);
	}

	private void next(final int offset) {
		assert tracks != null && tracks.size() > 0;

		clip.removeLineListener(this);
		trackIndex += offset;
		if (trackIndex < 0) {
			trackIndex = tracks.size() - 1;
		} else if (trackIndex >= tracks.size()) {
			trackIndex = 0;
			Collections.shuffle(tracks);
		}

		final boolean initiallyPaused = paused;
		pause();
		try {
			if (clip.isOpen()) {
				clip.close();
			}
			final AudioInputStream audioIn = AudioSystem
					.getAudioInputStream(tracks.get(trackIndex));
			clip.open(audioIn);
			setMuteControls();
		} catch (UnsupportedAudioFileException | IOException
				| LineUnavailableException e) {
			e.printStackTrace();
		}
		framePosition = 0;
		clip.addLineListener(this);
		if (!initiallyPaused) {
			play();
		}
	}

	public void pause() {
		paused = true;
		if (clip.isRunning()) {
			clip.stop();
		}
		framePosition = clip.getFramePosition();
	}

	public void play() {
		paused = false;
		if (clip.isRunning()) {
			clip.stop();
		}
		clip.setFramePosition(framePosition);
		clip.loop(0);
	}

	public boolean isPaused() {
		return paused;
	}

	public void playGameSound() {
		pause();
		// TODO
		play();
	}

	@Override
	public void update(final LineEvent event) {
		if (LineEvent.Type.STOP.equals(event.getType())) {
			next();
		}
	}
}
