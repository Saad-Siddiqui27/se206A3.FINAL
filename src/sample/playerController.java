package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.util.List;

public class playerController {


    @FXML
    private MediaView _view;
    @FXML
    private ListView _list;



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
        File fileUrl = new File("/afs/ec.auckland.ac.nz/users/m/s/msid633/unixhome/IdeaProjects/se206A3/Creations/"+_list.getSelectionModel().getSelectedItem().toString()+".mp4");
        Media video = new Media(fileUrl.toURI().toString());
        MediaPlayer player = new MediaPlayer(video);
        player.setAutoPlay(true);
        _view.setMediaPlayer(player);
    }

    public void play(){

        setMedia();
    }






}
