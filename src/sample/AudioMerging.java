package sample;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class AudioMerging {

    @FXML private Pane _creation;
    @FXML private ListView _list;
    @FXML private TextField _numpics;
    @FXML private TextField _merged;




    @FXML
    public void initialize() {


        String s =pbuilder.getInstance().getTerm();
        s = s.substring(1,s.length()-1);
        pbuilder.getInstance().probuild2("cd "+s+".au");


        _list.getItems().clear();
        pbuilder pro = pbuilder.getInstance();
        pro.probuild2("ls *.au 2> /dev/null");
        List<String> str = pro.getStd();

        for (int i = 0; i < str.size(); i++) {
            _list.getItems().add(str.get(i).substring(0, str.get(i).length() - 4));
        }
        _list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


    }







        public void merge() {


        StringBuilder files = new StringBuilder();
        List<String> Files = _list.getSelectionModel().getSelectedItems();

        if (Files.size() < 2 ) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Please select two or more files to merge");
            alert.setTitle("Merging Files");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                }

            });
        } else {
            for (int i = 0; i < Files.size(); i++) {


                files.append(Files.get(i) + ".wav ");

            }
            String s =pbuilder.getInstance().getTerm();
            s = s.substring(1,s.length()-1);
            pbuilder.getInstance().probuild2("cd "+s+".au;pwd");

            pbuilder.getInstance().probuild("cd "+s+".au ; sox " + files.toString() + "" + _merged.getText() + ".wav");

            pbuilder.getInstance().probuild("rm " + files.toString());
            initialize();


        }


    }


//    public void makeVideo(){
//
//    num = _textfield2.getText();
//    number = Integer.parseInt(num);
//        if (number > 10) {
//        number = 10;
//    }
//        if (number <= 0) {
//        number = 1;
//    }
//
//        try {
//        String apiKey = getAPIKey("apiKey");
//        String sharedSecret = getAPIKey("sharedSecret");
//
//        Flickr flickr = new Flickr(apiKey, sharedSecret, new REST());
//
//        String query = term;
//        int resultsPerPage = 10;
//        int page = 0;
//
//        PhotosInterface photos = flickr.getPhotosInterface();
//        SearchParameters params = new SearchParameters();
//        params.setSort(SearchParameters.RELEVANCE);
//        params.setMedia("photos");
//        params.setText(query);
//
//        PhotoList<Photo> results = photos.search(params, resultsPerPage, page);
//        System.out.println("Retrieving " + results.size() + " results");
//        int i = 1;
//
//        for (Photo photo : results) {
//            if (i <= number) {
//                try {
//
//                    BufferedImage image = photos.getImage(photo, Size.SMALL);
//                    String filename = term + Integer.toString(i) + ".jpg";
//                    File outputfile = new File(filename);
//                    ImageIO.write(image, "jpg", outputfile);
//                    System.out.println("Downloaded " + filename);
//                    i = i + 1;
//
//                } catch (FlickrException | IOException fe) {
//                    System.err.println("Ignoring image " + photo.getId() + ": " + fe.getMessage());
//                }
//            } else {
//                break;
//            }
//        }
//
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//
//    File audio = new File(_textfield.getText());
//        if(! audio.exists()) {
//        String cmd5 = "mkdir " + _textfield.getText() + "; mv *.jpg " + _textfield.getText();
//        pbuilder.getInstance().probuild(cmd5);
//    }
//    String cmd5 = "mv *.jpg " + _textfield.getText();
//        pbuilder.getInstance().probuild(cmd5);
//
//
//        System.out.println("\nDone");
//

//}
//
//
//    //Henry's Method
//    private static String getAPIKey(String key) throws Exception {
//        // TODO fix the following based on where you will have your config file stored
//
//        String config = System.getProperty("user.dir")
//                + System.getProperty("file.separator") + "flickr-api-keys.txt";
//
////		String config = System.getProperty("user.home")
////				+ System.getProperty("file.separator")+ "bin"
////				+ System.getProperty("file.separator")+ "flickr-api-keys.txt";
//        File file = new File(config);
//        BufferedReader br = new BufferedReader(new FileReader(file));
//
//        String line;
//        while ((line = br.readLine()) != null) {
//            if (line.trim().startsWith(key)) {
//                br.close();
//                return line.substring(line.indexOf("=") + 1).trim();
//            }
//        }
//        br.close();
//        throw new RuntimeException("Couldn't find " + key + " in config file " + file.getName());

////finish
//    }




        public void switchToMain() {


        SwitchScenes sw = new SwitchScenes(_creation);

        try {
            sw.switchScenes("MainMenu.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void switchBack() {


        SwitchScenes sw = new SwitchScenes(_creation);

        try {
            sw.switchScenes("Directoy.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
