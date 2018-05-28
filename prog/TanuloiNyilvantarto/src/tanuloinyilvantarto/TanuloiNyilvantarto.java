
package tanuloinyilvantarto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class TanuloiNyilvantarto extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
         Scene scene = new Scene(root);
        String css1= TanuloiNyilvantarto.class.getResource("Design.css").toExternalForm();
         String css2= TanuloiNyilvantarto.class.getResource("Table.css").toExternalForm();
        scene.getStylesheets().addAll(css1,css2);
       
        
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Tanulói nyilvántartó");
        Image icon = new Image(getClass().getResourceAsStream("logo.jpg"));
        stage.getIcons().add(icon);
        stage.setResizable(false);
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
