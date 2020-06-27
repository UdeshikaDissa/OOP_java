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

//This class opens dialog box to capture user data for Renting a vehicle
public class RentDialogBox {
	
		static String[] rentData = new String[4]; 
		
		public static String[] display() {
		Stage RentdialogBoxStage = new Stage();
		RentdialogBoxStage.setTitle("Rent a Vehicle");
  		
  		// dialog components
  		TextField vehicleID = new TextField();
  		vehicleID.setPromptText("Vehicle ID");
  		vehicleID.setId("vehiid"); //---------------------------------
  		
  		TextField customerID = new TextField();
  		customerID.setPromptText("Customer ID (Ex-C001)");
  		customerID.setId("cusid"); //---------------------------------

  		TextField rentDate = new TextField();
  		rentDate.setPromptText("Rent Date (DD/MM/YYYY)");
  		rentDate.setId("rentdate"); //---------------------------------

  		TextField noOfDays = new TextField();
  		noOfDays.setPromptText("Number of Days");
  		noOfDays.setId("noofdays"); //---------------------------------

  		Button RentdialogOKButton = new Button("OK");
  		Button RentdialogCancelButton = new Button("Cancel");
  		
  		HBox RentdialogButtons = new HBox(10);
  		RentdialogButtons.getChildren().add(RentdialogOKButton);
  		RentdialogButtons.getChildren().add(RentdialogCancelButton);
  		RentdialogButtons.setAlignment(Pos.CENTER);
  		
  		// layout the dialog components
  		VBox RentdialogVBox = new VBox(3);

  		RentdialogVBox.getChildren().add(vehicleID);
  		RentdialogVBox.getChildren().add(customerID);
  		RentdialogVBox.getChildren().add(rentDate);
  		RentdialogVBox.getChildren().add(noOfDays);
  		RentdialogVBox.getChildren().add(RentdialogButtons);

  		Scene RentdialogScene = new Scene(RentdialogVBox, 300, 200);
  		
  		RentdialogBoxStage.setScene(RentdialogScene);
  		
  		//Activates Buttons
  		RentdialogOKButton.setOnAction(
  				(e) -> {
  					 					
  					TextField vehiIDField = (TextField) RentdialogScene.lookup("#vehiid");
  					TextField cusIdField = (TextField) RentdialogScene.lookup("#cusid");
  					TextField rentDateField = (TextField) RentdialogScene.lookup("#rentdate");
  					TextField numOfdatysField = (TextField) RentdialogScene.lookup("#noofdays");
  					
  					rentData[0] = vehiIDField.getText();
  					rentData[1] = cusIdField.getText();
  					rentData[2] = rentDateField.getText();
  					rentData[3] = numOfdatysField.getText();
  					
  					RentdialogBoxStage.close();
  				}
  		);
  				
  		RentdialogCancelButton.setOnAction(
  				(e) -> {
  					rentData[0] = "null";
  					rentData[1] = "null";
  					rentData[2] = "null";
  					rentData[3] = "null";
  					RentdialogBoxStage.close();
  					
  				}
  		);
  		
  		RentdialogBoxStage.showAndWait();
  		return rentData;
		
	}

		//Overrides the method with different input parameter
		public static String[] display(String vehiID) {
			
			Stage RentdialogBoxStage = new Stage();
			RentdialogBoxStage.setTitle("Rent a Vehicle");
	  			  		
	  		TextField customerID = new TextField();
	  		customerID.setPromptText("Customer ID (Ex-C001)");
	  		customerID.setId("cusid"); //---------------------------------
	
	  		TextField rentDate = new TextField();
	  		rentDate.setPromptText("Rent Date (DD/MM/YYYY)");
	  		rentDate.setId("rentdate"); //---------------------------------
	
	  		TextField noOfDays = new TextField();
	  		noOfDays.setPromptText("Number of Days");
	  		noOfDays.setId("noofdays"); //---------------------------------
	
	  		Button RentdialogOKButton = new Button("OK");
	  		Button RentdialogCancelButton = new Button("Cancel");
	  		
	  		HBox RentdialogButtons = new HBox(10);
	  		RentdialogButtons.getChildren().add(RentdialogOKButton);
	  		RentdialogButtons.getChildren().add(RentdialogCancelButton);
	  		RentdialogButtons.setAlignment(Pos.CENTER);
		  		
	  		// layout the dialog components
	  		VBox RentdialogVBox = new VBox(3);
	
	   		RentdialogVBox.getChildren().add(customerID);
	  		RentdialogVBox.getChildren().add(rentDate);
	  		RentdialogVBox.getChildren().add(noOfDays);
	  		RentdialogVBox.getChildren().add(RentdialogButtons);
	
	  		Scene RentdialogScene = new Scene(RentdialogVBox, 300, 200);
	  		
	  		RentdialogBoxStage.setScene(RentdialogScene);
  		
	  	//ACtivates Buttons
	  		RentdialogOKButton.setOnAction(
  				(e) -> {
  					
  					TextField cusIdField = (TextField) RentdialogScene.lookup("#cusid");
  					TextField rentDateField = (TextField) RentdialogScene.lookup("#rentdate");
  					TextField numOfdatysField = (TextField) RentdialogScene.lookup("#noofdays");
  					
  					rentData[0] = vehiID;
  					rentData[1] = cusIdField.getText();
  					rentData[2] = rentDateField.getText();
  					rentData[3] = numOfdatysField.getText();
  			  					
  					RentdialogBoxStage.close();
  				}
  		);
  				
  		RentdialogCancelButton.setOnAction(
  				(e) -> {
  					rentData[0] = "null";
  					rentData[1] = "null";
  					rentData[2] = "null";
  					rentData[3] = "null";
  					RentdialogBoxStage.close();
  					
  				}
  		);
  		
  		RentdialogBoxStage.showAndWait();
  		return rentData;
		
	}

}
