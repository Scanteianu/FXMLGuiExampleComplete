/*
 * License: this is an example created by Daniel Scanteianu at Stony Brook University
 * Use, reuse, modify, etc. as you wish. 
 */
package fxmlguiexamplecomplete;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Dan
 */
public class ObjectEditorController implements Initializable {
    @FXML
    private TextField nameField;
    @FXML
    private TextField indexField;
    @FXML
    private TextField valueField;
    @FXML
    private Button okButton;
    @FXML
    private Button cancelButton;
    @FXML
    private AnchorPane objEditor;
    /**
     * never gets used. I tried something that didn't work.
     * @return 
     */
    public AnchorPane getObjEditor() {
        return objEditor;
    }
    
    public void setObjEditor(AnchorPane objEditor) {
        this.objEditor = objEditor;
    }
    /**
     * Handles OK being clicked.
     * Checks input for format, and displays a message box if it's wrong.
     * Need to have Jdk 8 rev. 40 or better for this kind of message box.
     * @param event 
     */
    @FXML
    public void okClicked(ActionEvent event){
        ArrayObject toEdit;
        if(FXMLGuiExampleComplete.isAdd)
            toEdit=new ArrayObject();
        else
        {
            toEdit=FXMLGuiExampleComplete.arrayHolder.get(FXMLGuiExampleComplete.index);
           
        }
        try{
        toEdit.setName(nameField.getText());
        toEdit.setIndex(Integer.parseInt(indexField.getText()));
        toEdit.setValue(Double.parseDouble(valueField.getText()));
        if(FXMLGuiExampleComplete.isAdd)
        {
            if(FXMLGuiExampleComplete.index==-1)
                FXMLGuiExampleComplete.arrayHolder.addObject(toEdit);
            else
                FXMLGuiExampleComplete.arrayHolder.addObject(toEdit,FXMLGuiExampleComplete.index);
        }
        else
             FXMLGuiExampleComplete.arrayHolder.set(FXMLGuiExampleComplete.index,toEdit);
        refreshed=false;
        FXMLGuiExampleComplete.mainScreen();
        }
        catch(NumberFormatException e)
        {
            Alert alert = new Alert(AlertType.WARNING);
            
            alert.setTitle("Error");
            alert.setHeaderText("Error :One of the numeric fields you entered isn't a number.");
            alert.setContentText("Check number and index.");

            alert.showAndWait();
            
        }
        
    }
    
    @FXML
    public void cancelClicked(ActionEvent event){
        FXMLGuiExampleComplete.mainScreen();
        refreshed=false;
    }
    /**
     * This is a refresh for the stuff that comes in the text fields.
     * Includes a binary state machine (isadd vs !isadd) to switch modes between
     * adding and editing.
     * We can't refresh every .1 sec or something, because we have to let the 
     * user change the values and choose to save.
     */
    public void refresh()
    {
        ArrayObject toEdit;
         if(!FXMLGuiExampleComplete.isAdd&&(!refreshed||oldIndex!=FXMLGuiExampleComplete.index))
        {
            toEdit=FXMLGuiExampleComplete.arrayHolder.get(FXMLGuiExampleComplete.index);
            nameField.setText(toEdit.getName());
            indexField.setText(toEdit.getIndex()+"");
            valueField.setText(toEdit.getValue()+"");
            refreshed=true;
            oldIndex=FXMLGuiExampleComplete.index;
        }
         if(FXMLGuiExampleComplete.isAdd&&!refreshed)
         {
             nameField.setText("");
            indexField.setText("");
            valueField.setText("");
            refreshed=true;
         }
    }
    /**
     * Initializes the controller class.
     * Starts our timer, etc.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Timeline timeline = new Timeline(new KeyFrame(
        Duration.millis(100),
        ae -> refresh()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        
    }   
    private boolean refreshed=false;
    private int oldIndex=-2;
    
}
