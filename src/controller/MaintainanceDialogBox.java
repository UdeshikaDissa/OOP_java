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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//This opens seperate window to capture user inputs for maintenance records
public class MaintainanceDialogBox {
	
	static String[] maintenanceData = new String[1]; 
	
	public static String[] display() {
		
		Stage MaintenancedialogBoxStage = new Stage();
		MaintenancedialogBoxStage.setTitle("Register a Vehicle for Maintenance");
  		
  		// dialog components
  		TextField vehicleID = new TextField();
  		vehicleID.setPromptText("Vehicle ID");
  		vehicleID.setId("vehiid"); 
  	  
  		Button MaintenancedialogOKButton = new Button("OK");
  		Button MaintenancedialogCancelButton = new Button("Cancel");
  		
  		HBox MaintenancedialogButtons = new HBox(10);
  		MaintenancedialogButtons.getChildren().add(MaintenancedialogOKButton);
  		MaintenancedialogButtons.getChildren().add(MaintenancedialogCancelButton);
  		MaintenancedialogButtons.setAlignment(Pos.CENTER);

  		// layout the dialog components
  		VBox MaintenancedialogVBox = new VBox(3);

  		MaintenancedialogVBox.getChildren().add(vehicleID);
  		MaintenancedialogVBox.getChildren().add(MaintenancedialogButtons);

  		Scene MaintenancedialogScene = new Scene(MaintenancedialogVBox, 400, 100);
  		
  		MaintenancedialogBoxStage.setScene(MaintenancedialogScene);
  		
  		//Activating Buttons
  		MaintenancedialogOKButton.setOnAction(
  				(e) -> {
  				  					
  					TextField vehiIDField = (TextField) MaintenancedialogScene.lookup("#vehiid");
  					maintenanceData[0] = vehiIDField.getText();
  					MaintenancedialogBoxStage.close();
  				}
  		);
  				
  		MaintenancedialogCancelButton.setOnAction(
  				(e) -> {
  					maintenanceData[0] = "null";
  					MaintenancedialogBoxStage.close();
  				}
  		);
  		
  		MaintenancedialogBoxStage.showAndWait();
  		return maintenanceData;
	}
	
 //Method overrding using different Parameter inputs	
 public static String[] display(String vehiID) {
		
		Stage MaintenancedialogBoxStage = new Stage();
		MaintenancedialogBoxStage.setTitle("Register a Vehicle for Maintenance");
  	  
		Label labelMessage = new Label("Confirm Vehicle "+vehiID +"to register for maintenance");

  		Button MaintenancedialogOKButton = new Button("OK");
  		Button MaintenancedialogCancelButton = new Button("Cancel");
  		
  		HBox MaintenancedialogButtons = new HBox(10);
  		MaintenancedialogButtons.getChildren().add(MaintenancedialogOKButton);
  		MaintenancedialogButtons.getChildren().add(MaintenancedialogCancelButton);
  		MaintenancedialogButtons.setAlignment(Pos.CENTER);

  		// layout the dialog components
  		VBox MaintenancedialogVBox = new VBox(3);
  		MaintenancedialogVBox.setSpacing(10);
  		MaintenancedialogVBox.setAlignment(Pos.CENTER);

  		MaintenancedialogVBox.getChildren().add(labelMessage);
  		MaintenancedialogVBox.getChildren().add(MaintenancedialogButtons);

  		Scene MaintenancedialogScene = new Scene(MaintenancedialogVBox, 400, 100);
  		
  		MaintenancedialogBoxStage.setScene(MaintenancedialogScene);
  			
  		MaintenancedialogOKButton.setOnAction(
  				(e) -> {
  					maintenanceData[0] = vehiID;
  		        	MaintenancedialogBoxStage.close();
  				}
  		);
  				
  		MaintenancedialogCancelButton.setOnAction(
  				(e) -> {
  					maintenanceData[0] = "null";
  					MaintenancedialogBoxStage.close();
  				}
  		);
  		
  		MaintenancedialogBoxStage.showAndWait();
  		return maintenanceData;
		
	}

}


