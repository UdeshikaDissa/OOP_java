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

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//This class opens another sub window to capture user inputs in relation to complete maintenance
//and passess back to main view to execute, display and save data
public class CompleteMainDialogBox {
	
	static String[] compMaintenanceData = new String[2]; 
	
	public static String[] display() {
		
		Stage CompMaintdialogBoxStage = new Stage();
		CompMaintdialogBoxStage.setTitle("Complete Vehicle Maintenance");
  		
  		// dialog components
  		TextField vehicleID = new TextField();
  		vehicleID.setPromptText("Vehicle ID");
  		vehicleID.setId("vehiid"); //---------------------------------
  	  
  		TextField compMaintDate = new TextField();
  		compMaintDate.setPromptText("Return Date (DD/MM/YYYY)");
  		compMaintDate.setId("returndate"); //---------------------------------

  		Button CompMaintdialogOKButton = new Button("OK");
  		Button CompMaintndialogCancelButton = new Button("Cancel");
  		
  		HBox CompMaindialogButtons = new HBox(10);
  		CompMaindialogButtons.getChildren().add(CompMaintdialogOKButton);
  		CompMaindialogButtons.getChildren().add(CompMaintndialogCancelButton);
  		CompMaindialogButtons.setAlignment(Pos.CENTER);

  		// layout the dialog components
  		VBox CompMaindialogVBox = new VBox(3);

  		CompMaindialogVBox.getChildren().add(vehicleID);
  		CompMaindialogVBox.getChildren().add(compMaintDate);
  		CompMaindialogVBox.getChildren().add(CompMaindialogButtons);

  		Scene CompMaintdialogScene = new Scene(CompMaindialogVBox, 300, 100);
  		
  		CompMaintdialogBoxStage.setScene(CompMaintdialogScene);
  		
  		//Activating Buttons
  		CompMaintdialogOKButton.setOnAction(
  				(e) -> {
  										
  					TextField vehiIDField = (TextField) CompMaintdialogScene.lookup("#vehiid");
  					TextField compMaintDateField = (TextField) CompMaintdialogScene.lookup("#returndate");
  					
  					compMaintenanceData[0] = vehiIDField.getText();
  					compMaintenanceData[1] = compMaintDateField.getText();
  				  	  					
  					CompMaintdialogBoxStage.close();
  					  					  					
  				}
  		);
  		
  		
  		CompMaintndialogCancelButton.setOnAction(
  				(e) -> {
  					compMaintenanceData[0] = "null";
  					compMaintenanceData[1] = "null";;
  					CompMaintdialogBoxStage.close();
  				}
  		);
  		
  		CompMaintdialogBoxStage.showAndWait();
  		return compMaintenanceData;
		
	}

	//Override the method with single input String parameter
	public static String[] display(String vehiID) {
		
		Stage CompMaintdialogBoxStage = new Stage();
		CompMaintdialogBoxStage.setTitle("Complete Vehicle Maintenance");
  		
  		TextField compMaintDate = new TextField();
  		compMaintDate.setPromptText("Return Date (DD/MM/YYYY)");
  		compMaintDate.setId("returndate"); //---------------------------------

  		Button CompMaintdialogOKButton = new Button("OK");
  		Button CompMaintndialogCancelButton = new Button("Cancel");
  		
  		HBox CompMaindialogButtons = new HBox(10);
  		CompMaindialogButtons.getChildren().add(CompMaintdialogOKButton);
  		CompMaindialogButtons.getChildren().add(CompMaintndialogCancelButton);
  		CompMaindialogButtons.setAlignment(Pos.CENTER);
  		
  		// layout the dialog components
  		VBox CompMaindialogVBox = new VBox(3);
  		
  		CompMaindialogVBox.getChildren().add(compMaintDate);
  		CompMaindialogVBox.getChildren().add(CompMaindialogButtons);

  		Scene CompMaintdialogScene = new Scene(CompMaindialogVBox, 300, 100);
  		
  		CompMaintdialogBoxStage.setScene(CompMaintdialogScene);
  		
  		CompMaintdialogOKButton.setOnAction(
  				(e) -> {
  					
  				  					
  					TextField vehiIDField = (TextField) CompMaintdialogScene.lookup("#vehiid");
  					TextField compMaintDateField = (TextField) CompMaintdialogScene.lookup("#returndate");
  					
  					compMaintenanceData[0] = vehiID;
  					compMaintenanceData[1] = compMaintDateField.getText();
  				  	  					
  					CompMaintdialogBoxStage.close();
  			}
  		);
  				
  		CompMaintndialogCancelButton.setOnAction(
  				(e) -> {
  					compMaintenanceData[0] = "null";
  					compMaintenanceData[1] = "null";;
  					CompMaintdialogBoxStage.close();
  				}
  		);
  		
  		CompMaintdialogBoxStage.showAndWait();
   		return compMaintenanceData;
		
	}

}


