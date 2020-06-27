/*
 * --------------------------------------------------------------------
 * Developer Name 	: Udeshika Dissanayake
 * Subject			: COSC1295 Advanced Programming
 * Assignment		: Assignment 2 - Semester 1, 2019
 * Student Number	: s3400652
 * Date				: 01/06/2019 * 
 *--------------------------------------------------------------------
 */

package controller;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

//This class is defined to export record data to specific location determined by FileChooser
public class ExportData {
	
	static String filename; 
		
	public static String display() {
		
		Stage ExportStage = new Stage();
		ExportStage.setTitle("Export Vehicle Records");
  		
		Label label = new Label("Export Thrifty Vehicle and Record Data to Text File");

  		Button ExportOKButton = new Button("OK");
  		Button ExportCancelButton = new Button("Cancel");
  		
  		HBox ExportButtons = new HBox(10);
  		ExportButtons.getChildren().add(ExportOKButton);
  		ExportButtons.getChildren().add(ExportCancelButton);
  		ExportButtons.setAlignment(Pos.CENTER);

  		// layout the dialog components
  		VBox ExportVBox = new VBox(10);
  		ExportVBox.setPadding(new Insets(30, 30, 30, 30));
  		ExportVBox.getChildren().add(label);
  		ExportVBox.getChildren().add(ExportButtons);

  		Scene ExportScene = new Scene(ExportVBox, 350, 110);
  		ExportStage.setScene(ExportScene);
  		
  		//Activating Buttons
  		ExportOKButton.setOnAction(
  				(e) -> {
  										
  					FileChooser filechooser = new FileChooser();
  					filechooser.setTitle("Export Thrifty Data to Text File");
  					filechooser.setInitialDirectory(new File(System.getProperty("user.home")));
  					
  					filechooser.setInitialFileName("ThriftyData");
  				    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
  			        filechooser.getExtensionFilters().add(extFilter);
  					
  			        File file = filechooser.showSaveDialog(ExportStage);
  			        
  			        if (file != null) {
  			        	MessageBox.display("Export Thrifty Data", "Saved at"+file);
  			        }else {
  			        	MessageBox.display("Export Thrifty Data", "File could not be saved");
  			        }
  			        
  			      filename = file.toString();
  			  ExportStage.close(); 
  			}
  		);
  				
  		ExportCancelButton.setOnAction(
  				(e) -> {
  						ExportStage.close();
  				}
  		);
  		
  		ExportStage.showAndWait();
  		return filename;
	}

}

