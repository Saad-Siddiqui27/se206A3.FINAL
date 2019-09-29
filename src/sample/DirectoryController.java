package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.Pane;

import java.util.List;


public class DirectoryController {

@FXML private Pane _Audio;
@FXML private ListView _directory;




    @FXML
    public void initialize() {


        _directory.getItems().clear();
        pbuilder pro = pbuilder.getInstance();
        pro.probuild2("ls -d *.au 2> /dev/null");
        List<String> str = pro.getStd();

        for (int i = 0; i < str.size(); i++) {
            _directory.getItems().add(str.get(i).substring(0, str.get(i).length() - 3));
        }
        _directory.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


    }

   public void goInAudioMerge() {


        pbuilder.getInstance().saveTerm(_directory.getSelectionModel().getSelectedItem().toString());
        SwitchScenes sw = new SwitchScenes(_Audio);
        try {
            sw.switchScenes("AudioMerging.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goInVideoCreate() {


        pbuilder.getInstance().saveTerm(_directory.getSelectionModel().getSelectedItem().toString());
        SwitchScenes sw = new SwitchScenes(_Audio);
        try {
            sw.switchScenes("VideoCreation.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void switchToMain() {


        SwitchScenes sw = new SwitchScenes(_Audio);

        try {
            sw.switchScenes("MainMenu.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
