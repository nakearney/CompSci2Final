package application;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class AudioPlayer {
	
	private String file;
	private Media media;
	private MediaPlayer mp;
	private double startTime;
	
	public AudioPlayer(String file) {
		this.file = file;
		String path = getClass().getResource("/sounds/" + file).getPath();
		media = new Media(new File(path).toURI().toString());
		mp = new MediaPlayer(media);
	}
	
	public AudioPlayer(String file, double startTime) {
		this(file);
		this.startTime = startTime;
		mp.setStartTime(Duration.seconds(startTime));
		mp.setStopTime(media.getDuration());
	}
	
	public AudioPlayer(String file, double startTime, double endTime) {
		this(file);
		this.startTime = startTime;
		mp.setStartTime(Duration.seconds(startTime));
		mp.setStopTime(Duration.seconds(endTime));
	}

	public void playSound() {
		
		mp.play();
	}
	
	public void playTrack() {
		
		mp.setOnEndOfMedia(new Runnable() {

			@Override
			public void run() {
				mp.seek(Duration.seconds(startTime));
			}
			
		});
		
		mp.play();
	}
	
	public void stop() {
		mp.stop();
	}
}
