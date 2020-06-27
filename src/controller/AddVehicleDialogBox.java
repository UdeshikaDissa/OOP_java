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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//This class opens Window to capture user inputs to create a new Vehicle in the system
//passes the details back to main model to create the Vehicle object and to write to database
public class AddVehicleDialogBox {
	
	static String[] addVehicleData = new String[6]; 
	
	public static String[] display() {
		
		Stage AddVehicleBoxStage = new Stage();
		AddVehicleBoxStage.setTitle("Add Vehicle");
  		
  		// dialog components
		ComboBox Vehicletype = new ComboBox();
		Vehicletype.getItems().add("Car");
		Vehicletype.getItems().add("Van");
		Vehicletype.setPromptText("Select Vehicle Type");
		Vehicletype.setId("vehitype"); 
		
  		TextField vehicleID = new TextField();
  		vehicleID.setPromptText("Vehicle ID");
  		vehicleID.setId("vehiid"); 
  		
  		TextField vehicleManufacYear = new TextField();
  		vehicleManufacYear.setPromptText("Year");
  		vehicleManufacYear.setId("year"); //---------------------------------

  		TextField carMake = new TextField();
  		carMake.setPromptText("Make");
  		carMake.setId("make"); //---------------------------------

  		TextField carModel = new TextField();
  		carModel.setPromptText("Model");
  		carModel.setId("model"); //---------------------------------
  		
  		TextField carSeat = new TextField();
  		carSeat.setPromptText("No of Seats (Vans leave Blank)");
  		carSeat.setId("seats"); //---------------------------------

  		Button AddcardialogOKButton = new Button("OK");
  		Button AddcardialogCancelButton = new Button("Cancel");
  		
  		HBox AddcardialogButtons = new HBox(10);
  		AddcardialogButtons.getChildren().add(AddcardialogOKButton);
  		AddcardialogButtons.getChildren().add(AddcardialogCancelButton);
  		AddcardialogButtons.setAlignment(Pos.CENTER);
  		
  		// layout the dialog components
  		VBox AddcardialogVBox = new VBox(3);

  		AddcardialogVBox.getChildren().add(Vehicletype);
  		AddcardialogVBox.getChildren().add(vehicleID);
  		AddcardialogVBox.getChildren().add(vehicleManufacYear);
  		AddcardialogVBox.getChildren().add(carMake);
  		AddcardialogVBox.getChildren().add(carModel);
  		AddcardialogVBox.getChildren().add(carSeat);
  		AddcardialogVBox.getChildren().add(AddcardialogButtons);

  		Scene AddvehicledialogScene = new Scene(AddcardialogVBox, 300, 200);
  		
  		AddVehicleBoxStage.setScene(AddvehicledialogScene);
  		
  		//Activates Buttons
  		AddcardialogOKButton.setOnAction(
  				(e) -> {
  					
  					ComboBox vehiTypeField = (ComboBox)	AddvehicledialogScene.lookup("#vehitype");			
  					TextField vehiIDField = (TextField) AddvehicledialogScene.lookup("#vehiid");
  					TextField cmanufacField = (TextField) AddvehicledialogScene.lookup("#year");
  					TextField makeField = (TextField) AddvehicledialogScene.lookup("#make");
  					TextField modelField = (TextField) AddvehicledialogScene.lookup("#model");
  					TextField seatsField = (TextField) AddvehicledialogScene.lookup("#seats");
  					  					
  					addVehicleData[0] = (String) vehiTypeField.getValue();
  					addVehicleData[1] = vehiIDField.getText();
  					addVehicleData[2] = cmanufacField.getText();
  					addVehicleData[3] = makeField.getText();
  					addVehicleData[4] = modelField.getText();
  					addVehicleData[5] = seatsField.getText(); 
  				    					
  					AddVehicleBoxStage.close();
  					}
  		);
  				
  		AddcardialogCancelButton.setOnAction(
  				(e) -> {
  					addVehicleData[0] = "null";
  					addVehicleData[1] = "null";
  					addVehicleData[2] = "null";
  					addVehicleData[3] = "null";
  					addVehicleData[4] = "null";
  					addVehicleData[5] = "null";
  					AddVehicleBoxStage.close();
  	 				}
  		);
  		
  		AddVehicleBoxStage.showAndWait();
  		return addVehicleData;
		
	}

}
