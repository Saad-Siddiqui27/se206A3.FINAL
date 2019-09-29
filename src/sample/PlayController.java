package sample;

import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class PlayController {

    @FXML
    private MediaView _view;


    public void setMedia(String path){
        File fileUrl = new File(path);
        Media video = new Media(fileUrl.toURI().toString());
        MediaPlayer player = new MediaPlayer(video);
        player.setAutoPlay(true);
        _view.setMediaPlayer(player);
    }


}
