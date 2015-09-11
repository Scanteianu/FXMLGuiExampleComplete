/*
 * License: this is an example created by Daniel Scanteianu at Stony Brook University
 * Use, reuse, modify, etc. as you wish. 
 */
package fxmlguiexamplecomplete;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main class of the program, which loads the FXML files, switches between them, 
 * and holds the instance of the data structure we are modifying.
 * @author Dan Scanteianu
 */
public class FXMLGuiExampleComplete extends Application {
    /**
     * Comes with default main, starts running the gui. Here's where we
     * choose the fxml files we will be using. I made them instance fields
     * so they're always ready so we can switch between them.
     * @param stage default parameter
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {
         root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
          second =  FXMLLoader.load(getClass().getResource("ObjectEditor.fxml"));
        rScene=new Scene(root);
        sScene=new Scene(second);
        this.stage=stage;
        mainScreen();
        //stage.wait(1000);
        
      
    }
    /**
     * this switches the content of the window (primary stage) to the Object Editor
     * FXML File
     */
    public static void objectEditor()
    {
      // Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
       // Parent second =  FXMLLoader.load(getClass().getResource("ObjectEditor.fxml"));
        stage.setTitle("Object Editor");
        stage.setScene(sScene);
        stage.show();
         
    }
    /**
     * this switches the window back to the main screen (held in the default
     * FXMLDocument)
     */
    public static void mainScreen()
    {
        stage.setTitle("Array Browser");
        stage.setScene(rScene);
        stage.show();
        
    }
    /** The main launches the program.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        arrayHolder=new ArrayHolder();
        launch(args);
        
    }
    //the data structure we're using, public so both controllers can access
    //we should probably do getters and setters, but I didn't feel like it.
    public static ArrayHolder arrayHolder;
    //instance fields for fxml documents
    static Stage stage;
    static Scene rScene;
    static Scene sScene;
    static Parent root;
    static Parent second;
    //variables accessed by the controllers to pass data from one to another
    //again, we should totally have getters and setters, but I was super lazy.
    public static int index;
    public static boolean isAdd;
}
