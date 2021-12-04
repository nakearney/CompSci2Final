package application;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class AudioPlayer {
	
	String file;
	MediaPlayer mp;
	int startTime = 0;
	
	public AudioPlayer(String file) {
		this.file = file;
		String path = getClass().getResource("/sounds/" + file).getPath();
		Media media = new Media(new File(path).toURI().toString());
		mp = new MediaPlayer(media);
	}
	
	public AudioPlayer(String file, int startTime) {
		this(file);
		this.startTime = startTime;
	}

	public void playSound() {
		
		mp.setStartTime(Duration.millis(startTime));
		mp.play();
	}
	
	public void playTrack() {
		
		mp.setStartTime(Duration.millis(startTime));
		
		mp.setOnEndOfMedia(new Runnable() {

			@Override
			public void run() {
				mp.seek(Duration.millis(startTime));
			}
			
		});
		
		mp.play();
	}
	
	public void stop() {
		mp.stop();
	}
}
