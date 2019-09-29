package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.util.List;

public class playerController {



    @FXML
    private Pane _Play;
    @FXML
    private MediaView _view;
    @FXML
    private ListView _list;
    @FXML
    private Button _pause;
    @FXML
    private Button _mute;

    MediaPlayer player;

//    File file = new File("/afs/ec.auckland.ac.nz/users/m/s/msid633/unixhome/IdeaProjects/se206A3/car.au/car.mkv");



    @FXML
    public void initialize(){
        _list.getItems().clear();
        pbuilder pro = pbuilder.getInstance();
        pro.probuild2("cd Creations; ls *.mp4 2> /dev/null");
        List<String> str = pro.getStd();

        for (int i = 0; i < str.size(); i++) {
            _list.getItems().add(str.get(i).substring(0, str.get(i).length() - 4));
        }
        _list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void setMedia(){
        File fileUrl = new File("Creations/"+_list.getSelectionModel().getSelectedItem().toString()+".mp4");
        Media video = new Media(fileUrl.toURI().toString());
        player = new MediaPlayer(video);
        player.setAutoPlay(true);
        _view.setMediaPlayer(player);
    }

    public void play(){
        Thread object1 = new Thread(new Multi1());
        object1.start();
    }


    public void MainMenu(){
        Platform.runLater(new Multi() {
            @Override
            public void run() {
                SwitchScenes sw = new SwitchScenes(_Play);

                try {
                    sw.switchScenes("MainMenu.fxml");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }


    public class Multi implements Runnable {

        @Override
        public void run() {

        }
    }

    public class Multi1 implements Runnable{

        @Override
        public void run() {
            setMedia();
        }
    }

    public void pause() {
        MediaPlayer.Status status = player.getStatus();
        if(MediaPlayer.Status.PLAYING == status){
            player.pause();
            _pause.setText(">");
        } else {
            player.play();
            _pause.setText("||");
        }
    }

    public void mute(){
        if(player.getVolume() == 0){
            player.setVolume(50);
            _mute.setText("Mute");
        }else{
            player.setVolume(0);
            _mute.setText("Voice");
        }
    }

}
