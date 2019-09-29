package sample;

import com.sun.xml.internal.ws.addressing.WsaActionUtil;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;
import java.util.StringTokenizer;



//Henry Imports

import java.io.*;
import com.flickr4java.flickr.*;
import com.flickr4java.flickr.photos.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;


public class CreateController {

    @FXML
    private Pane _create;
    @FXML
    private TextField _textfield;
    @FXML
    private TextArea textArea1;
    @FXML
    private TextArea textArea2;
    @FXML
    private MenuButton _menubutton;
    @FXML
    private CheckMenuItem _voice1;
    @FXML
    private CheckMenuItem _voice2;
    @FXML
    private TextField _audioName;
    @FXML
    private TextField _textfield2;






    String term;


    //Henry part;
    String num;
    int number;
    //done


    public void search() {
        term = _textfield.getText();

        if (term.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Please enter a valid term that you want to search");
            alert.setTitle("Empty term name");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                }
            });
        } else {

            String command = "wikit " + term + "| sed -e :1 -e 's/\\([.?!]\\)[[:blank:]]\\{1,\\}\\([^[:blank:]]\\)/\\1\\\n" +
                    "\\2/;t1' ";
//        pbuild(command);

            try {
                ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);

                Process process = pb.start();

                BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
                BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

                int exitStatus = process.waitFor();

                StringBuilder sb = new StringBuilder();
                if (exitStatus == 0) {
                    String line;
                    int count = 1;
                    while ((line = stdout.readLine()) != null) {
                        sb.append(count++ + ". " + line).append("\n");

                    }
                } else {
                    String line;
                    while ((line = stderr.readLine()) != null) {
                        System.err.println(line);
                    }
                }

                textArea1.setText(sb.toString());

                stderr.close();
                stdout.close();

            } catch (Exception e) {
                e.printStackTrace();
            }


        }


        //Henry's part
//        num = _textfield2.getText();
//        number = Integer.parseInt(num);
//        if (number > 10) {
//            number = 10;
//        }
//        if (number <= 0) {
//            number = 1;
//        }
//
//        try {
//            String apiKey = getAPIKey("apiKey");
//            String sharedSecret = getAPIKey("sharedSecret");
//
//            Flickr flickr = new Flickr(apiKey, sharedSecret, new REST());
//
//            String query = term;
//            int resultsPerPage = 10;
//            int page = 0;
//
//            PhotosInterface photos = flickr.getPhotosInterface();
//            SearchParameters params = new SearchParameters();
//            params.setSort(SearchParameters.RELEVANCE);
//            params.setMedia("photos");
//            params.setText(query);
//
//            PhotoList<Photo> results = photos.search(params, resultsPerPage, page);
//            System.out.println("Retrieving " + results.size() + " results");
//            int i = 1;
//
//            for (Photo photo : results) {
//                if (i <= number) {
//                    try {
//
//                        BufferedImage image = photos.getImage(photo, Size.SMALL);
//                        String filename = term + Integer.toString(i) + ".jpg";
//                        File outputfile = new File(filename);
//                        ImageIO.write(image, "jpg", outputfile);
//                        System.out.println("Downloaded " + filename);
//                        i = i + 1;
//
//                    } catch (FlickrException | IOException fe) {
//                        System.err.println("Ignoring image " + photo.getId() + ": " + fe.getMessage());
//                    }
//                } else {
//                    break;
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        File audio = new File(_textfield.getText());
//        if(! audio.exists()) {
//            String cmd5 = "mkdir " + _textfield.getText() + "; mv *.jpg " + _textfield.getText();
//            pbuilder.getInstance().probuild(cmd5);
//        }
//        String cmd5 = "mv *.jpg " + _textfield.getText();
//        pbuilder.getInstance().probuild(cmd5);
//
//
//        System.out.println("\nDone");
//
//
//}
//
//
////Henry's Method
//private static String getAPIKey(String key) throws Exception {
//    // TODO fix the following based on where you will have your config file stored
//
//    String config = System.getProperty("user.dir")
//            + System.getProperty("file.separator") + "flickr-api-keys.txt";
//
////		String config = System.getProperty("user.home")
////				+ System.getProperty("file.separator")+ "bin"
////				+ System.getProperty("file.separator")+ "flickr-api-keys.txt";
//    File file = new File(config);
//    BufferedReader br = new BufferedReader(new FileReader(file));
//
//    String line;
//    while ((line = br.readLine()) != null) {
//        if (line.trim().startsWith(key)) {
//            br.close();
//            return line.substring(line.indexOf("=") + 1).trim();
//        }
//    }
//    br.close();
//    throw new RuntimeException("Couldn't find " + key + " in config file " + file.getName());
//
////finish
//
//    }
    }


public String select(){
        String str = textArea1.getSelectedText();
        textArea2.appendText(str);

        return str;
}

    File f = new File("voice1.scm");



public void play(){
        String str = textArea1.getSelectedText();


    try {
        if(_voice1.isSelected()) {

            FileWriter fw = new FileWriter(f);
            fw.write("(voice_akl_nz_jdt_diphone) ;; select Jono" + " \n(SayText \"" + str + "\")");
            fw.close();
            String cmd3 = "festival -b " + f;
            pbuild(cmd3);
        }else if(_voice2.isSelected()){
            FileWriter fw = new FileWriter(f);
            fw.write("(voice_kal_diphone) ;; select Jono" + " \n(SayText \"" + str + "\")");
            fw.close();
            String cmd3 = "festival -b " + f;
            pbuild(cmd3);

        }
    }catch(Exception e){
        e.printStackTrace();
    }


}

public void Save() {

    StringTokenizer tokens = new StringTokenizer(textArea2.getText());
    int i = tokens.countTokens();


    if (i > 40) {

        //To do Alerts;
    } else {

        try {
            String str2 = textArea2.getText();


            if (_voice1.isSelected()) {
                helpSave(str2, "(voice_akl_nz_jdt_diphone) ");
            } else if (_voice2.isSelected()) {
                helpSave(str2, "(voice_kal_diphone) ");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

    public void helpSave(String str2, String s) throws IOException {
        File f2 = new File("text");
        FileWriter fw2 = new FileWriter(f2);
        fw2.write(str2);
        fw2.close();
        FileWriter fw = new FileWriter(f);
        fw.write(s);
        fw.close();
        File audio = new File(_textfield.getText());
        if(! audio.exists()) {
            String cmd4 = "mkdir " + _textfield.getText()+".au";
            pbuilder.getInstance().probuild(cmd4);
        }
        String cmd3 = "text2wave -o "+_textfield.getText()+".au/"+_audioName.getText()+".wav "+f2+" -eval "+f;
        pbuild(cmd3);
        System.out.println(_audioName.getText() + " saved");

    }


    public void pbuild(String cmd){
        try {
            ProcessBuilder pb = new ProcessBuilder("bash", "-c", cmd);

            Process process = pb.start();



        } catch(Exception e){
            e.printStackTrace();
        }


    }





    public void switchScenes(String fxml) throws IOException {

        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = (Stage) _create.getScene().getWindow();

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.sizeToScene();
    }

    public void MainMenu(){
        try {
            switchScenes("MainMenu.fxml");
        }catch(IOException e){
            e.printStackTrace();
        }

    }







    //Henry new code.

//
//    public void CombineVideo() throws IOException {
//        System.out.println("Video");
//        File f = new File("input.txt");
//        FileWriter fw = new FileWriter(f);
//        int i = 1;
//        while(i<=number){
//            String cmd = "ffmpeg -loop 1 -i " + term + i + ".jpg -c:v libx264 -t 3 -pix_fmt yuv420p " + term + "temp" + i + ".mkv";
//            pbuild(cmd);
//            fw.write("file " + term + "temp" + i + ".mkv\n");
//            i = i + 1;
//        }
//        fw.close();
//        String cmd2 = "ffmpeg -f concat -safe 0 -i "+f+" -c copy " + term + ".mkv";
//        pbuild(cmd2);
//        String cmd3 = "rm *temp.mkv";
//        pbuild(cmd3);
//
//        String cmd5 = "ffmpeg -i " + term + ".mkv -vf drawtext=\"fontfile=/path/to/font.ttf: \\ text='" + term + "': fontcolor=white: fontsize=24: box=1: boxcolor=black@0.5: " + "\\" + " boxborderw=5: x=(w-text_w)/2: y=(h-text_h)/2\" -codec:a copy " + term + num + ".mkv";
//        pbuild(cmd5);
////        String cmd6 = "rm " + term + ".mkv";
////        pbuild(cmd6);
//        String cmd4 = "rm input.txt";
////        pbuild(cmd4);
//    }
//


}
