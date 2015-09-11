/*
 * License: this is an example created by Daniel Scanteianu at Stony Brook University
 * Use, reuse, modify, etc. as you wish. 
 */
package fxmlguiexamplecomplete;

import static fxmlguiexamplecomplete.FXMLGuiExampleComplete.arrayHolder;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author Dan
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private TextArea displayArea;
    @FXML
    private Button editButton;
    @FXML
    private TextField removeIndex;
    @FXML
    private AnchorPane mainPage;
    private boolean hasRefreshed=false;
    /**
     * this gets executed when we click the add button.
     * you can name this whatever you like, but the params and return types must
     * be like this.
     * @param event you need to specify this action event param so fxml can find it
     * @throws IOException 
     */
    @FXML
    private void addClicked(ActionEvent event) throws IOException{
        FXMLGuiExampleComplete.objectEditor();
        FXMLGuiExampleComplete.isAdd=true;
        try
        {
        FXMLGuiExampleComplete.index=Integer.parseInt(removeIndex.getText());
        }
        catch(NumberFormatException e){
            FXMLGuiExampleComplete.index=-1;
        }
       // hasRefreshed=false;
        
        displayArea.setText(arrayHolder.toString());
        //mainPage.getChildren().setAll(oEC.getObjEditor());
        System.out.println("+");
    }
    /**
     * this piece of code refreshes the display of the objects in our array.
     * 
     */
    public void refresh()
    {
        displayArea.setText(arrayHolder.toString());
    
          /*if(!hasRefreshed)
          {
            hasRefreshed=true;
            displayArea.setText(arrayHolder.toString());
          }*/
          //hasRefreshed=false;
         //displayArea.appendText("refreshed");
         //editButton.setText("refreshed");
         //System.out.println("refreshed");
    }
    /**
     * This gets executed when the remove button is hit.
     * @param event 
     */
    @FXML
    private void removeClicked(ActionEvent event)
    {
        System.out.println("Rm");
        try{
            arrayHolder.removeObject(Integer.parseInt(removeIndex.getText()));
        }
        catch(Exception e)
        {
            System.out.println("Oh no.");
        }
        displayArea.setText(arrayHolder.toString());
        hasRefreshed=false;
    }
    /**
     * This gets called when the edit button is clicked.
     * @param event 
     */
    @FXML
    private void editClicked(ActionEvent event)
    {
        
        FXMLGuiExampleComplete.isAdd=false;
        try
        {
        FXMLGuiExampleComplete.index=Integer.parseInt(removeIndex.getText());
        }
        catch(NumberFormatException e){
            FXMLGuiExampleComplete.index=-1;
        }
        FXMLGuiExampleComplete.objectEditor();
        displayArea.setText(arrayHolder.toString());
        System.out.println("Edit");
        hasRefreshed=false;
    }
    /**
     * Default, currently not in use, didn't feel like deleting.
     * @param event 
     */
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    /**
     * basically like the constructor. Gets called once, on load.
     * Interesting to read the code, because it introduces the concept of a timer
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //below is a timer. this refreshes the array view text are 10 times a second
        Timeline timeline = new Timeline(new KeyFrame(
        Duration.millis(100),
        ae -> refresh()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        displayArea.setText(arrayHolder.toString());
        hasRefreshed=false;
    }    
    /*public class RefreshThread extends Thread {

        @Override
        public void run(){
           while(true)
           {
               try {
                   Thread.sleep(500);
               } catch (InterruptedException ex) {
                   Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
               }
               FXMLDocumentController.this.refresh();
               System.out.println(arrayHolder.toString());
              
           }
        }
      }*/
    
}
