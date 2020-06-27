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

//This class opens dialog box to capture user data for Return vehicle
public class ReturnDialogBox {
	
	static String[] returnData = new String[2]; 
	
	public static String[] display() {
		
		Stage ReturndialogBoxStage = new Stage();
		ReturndialogBoxStage.setTitle("Return a Vehicle");
  		
  		// dialog components
  		TextField vehicleID = new TextField();
  		vehicleID.setPromptText("Vehicle ID");
  		vehicleID.setId("vehiid"); //---------------------------------
  	  
  		TextField returnDate = new TextField();
  		returnDate.setPromptText("Return Date (DD/MM/YYYY)");
  		returnDate.setId("returndate"); //---------------------------------

  		Button ReturndialogOKButton = new Button("OK");
  		Button ReturndialogCancelButton = new Button("Cancel");
  		
  		HBox ReturndialogButtons = new HBox(10);
  		ReturndialogButtons.getChildren().add(ReturndialogOKButton);
  		ReturndialogButtons.getChildren().add(ReturndialogCancelButton);
  		ReturndialogButtons.setAlignment(Pos.CENTER);
  		
  		// layout the dialog components
  		VBox ReturndialogVBox = new VBox(3);

  		ReturndialogVBox.getChildren().add(vehicleID);
  		ReturndialogVBox.getChildren().add(returnDate);
   		ReturndialogVBox.getChildren().add(ReturndialogButtons);

  		Scene RentdialogScene = new Scene(ReturndialogVBox, 300, 100);
  		
  		ReturndialogBoxStage.setScene(RentdialogScene);
  		
  		
  		//Activates the Buttons
  		ReturndialogOKButton.setOnAction(
  				(e) -> {
  									
  					TextField vehiIDField = (TextField) RentdialogScene.lookup("#vehiid");
  					TextField returnDateField = (TextField) RentdialogScene.lookup("#returndate");
  					  					
  					returnData[0] = vehiIDField.getText();
  					returnData[1] = returnDateField.getText();
  				  	  					  					
  					ReturndialogBoxStage.close();
  				}
  		);
  				
  		ReturndialogCancelButton.setOnAction(
  				(e) -> {
  					returnData[0] = "null";
  					returnData[1] = "null";
  					ReturndialogBoxStage.close();
  					
  				}
  		);
  		
  		ReturndialogBoxStage.showAndWait();
  		return returnData;
		
	}

	//Overrides the method with different input parameter
	public static String[] display(String vehiID) {
		
		Stage ReturndialogBoxStage = new Stage();
		ReturndialogBoxStage.setTitle("Return a Vehicle");
  		
  		TextField returnDate = new TextField();
  		returnDate.setPromptText("Return Date (DD/MM/YYYY)");
  		returnDate.setId("returndate"); //---------------------------------

  		Button ReturndialogOKButton = new Button("OK");
  		Button ReturndialogCancelButton = new Button("Cancel");
  		
  		HBox ReturndialogButtons = new HBox(10);
  		ReturndialogButtons.getChildren().add(ReturndialogOKButton);
  		ReturndialogButtons.getChildren().add(ReturndialogCancelButton);
  		ReturndialogButtons.setAlignment(Pos.CENTER);
  		
  		// layout the dialog components
  		VBox ReturndialogVBox = new VBox(3);

  		ReturndialogVBox.getChildren().add(returnDate);
   		ReturndialogVBox.getChildren().add(ReturndialogButtons);

  		Scene RentdialogScene = new Scene(ReturndialogVBox, 300, 100);
  		
  		ReturndialogBoxStage.setScene(RentdialogScene);
  		
  		
  		//Activates Buttons
  		ReturndialogOKButton.setOnAction(
  				(e) -> {
  				  				  					
  					//TextField vehiIDField = (TextField) RentdialogScene.lookup("#vehiid");
  					TextField returnDateField = (TextField) RentdialogScene.lookup("#returndate");
  					
  					returnData[0] = vehiID;
  					returnData[1] = returnDateField.getText();
  				  					  					
  					ReturndialogBoxStage.close();
  				}
  		);
  				
  		ReturndialogCancelButton.setOnAction(
  				(e) -> {
  					returnData[0] = "null";
  					returnData[1] = "null";
  					ReturndialogBoxStage.close();
  					
  				}
  		);
  		
  		ReturndialogBoxStage.showAndWait();
  		return returnData;
		
	}

}

