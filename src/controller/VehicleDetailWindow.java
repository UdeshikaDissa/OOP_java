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
import java.sql.SQLException;
import java.util.Arrays;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.ExceptionToMainMenu;
import model.InvalidIdException;
import model.MaintenanceException;
import model.RentException;
import model.ReturnException;
import model.ThriftyRentSystem;
import view.MainView;

//This class opens another sub window to show the detail view of the vehicle data
public class VehicleDetailWindow {
	
	//define static variables to exchange between main model and GUI
	static String[] rentData = new String[4]; 
	static String[] returnData = new String[2]; 
	static String[] maintenanceData = new String[1]; 
	static String[] compMaintenanceData = new String[2]; 
	
	//method define to display the new GUI - Detail Window view
	public static void display(String vehiID, String[][] recordData, ThriftyRentSystem mainApp,
			Button saveandRefresh) {
		
		String[] vehicleData = new String[7];
		vehicleData = vehiID.split(":");
		String VehicleID = vehicleData[0];
		String mainteReturn = "N/A";
		
		Stage VehicleDetailWindowStage = new Stage();
		VehicleDetailWindowStage.setTitle("Vehicle Details of "+vehicleData[0]);
  		
		//Main Pane of VBOx
		VBox MainvBox = new VBox(); 
  		MainvBox.setSpacing(10);
  		MainvBox.setAlignment(Pos.CENTER);

  		//adjust the maintance return date
  		if(VehicleID.charAt(0) == 'C'){
  			mainteReturn = "N/A";
  		}else {
  			mainteReturn = vehicleData[6];
	    }
  		
  		//Top Level Buttoms defining  		
  		Button refreshView = new Button("Refresh View");
  		Button rentVehicles = new Button("Rent Vehicle");
  		Button returnVehicles = new Button("Return Vehicle");
  		Button maintenance = new Button("Maintenance");
  		Button completeMainte = new Button("Complete Maintenance");
  		Button close = new Button("Close");
  		
  		HBox TopLevel = new HBox(10);
  		TopLevel.setAlignment(Pos.CENTER);
  		TopLevel.setPadding(new Insets(10, 10, 10, 10));

  		TopLevel.getChildren().addAll(refreshView, rentVehicles,
  				returnVehicles,maintenance,completeMainte, close);

  		//Meddle level HBox is define to show vehicle data
  		HBox MiddleHBox = new HBox(50); 
  		MiddleHBox.setMinSize(300, 200);
  		MiddleHBox.setMaxHeight(200);
  		MiddleHBox.setPadding(new Insets(10, 10, 10, 10));
  		MiddleHBox.setAlignment(Pos.CENTER);
  		
  		Label labelCarID = new Label("Rego: "+vehicleData[0]);
    	labelCarID.setTextFill(Color.web("#0076a3"));
    	labelCarID.setFont(Font.font("Cambria", 16));
    	
    	boolean check = new File("./Images/"+vehicleData[0]+".png").exists();
    	
    	if (check) {
    		//System.out.println("File exists");
    		Image image = new Image("File:Images/"+vehicleData[0]+".png");
    		labelCarID.setGraphic(new ImageView(image));
    	}else {
    		//System.out.println("File Not exists");
    		Image image = new Image("File:Images/NoImageAvailable.png");
    		labelCarID.setGraphic(new ImageView(image));
    	}
    	
    	Label labelCarYear = new Label("Year : "+vehicleData[1]);  
    	labelCarYear.setTextFill(Color.web("#0076a3"));
    	labelCarYear.setFont(Font.font("Cambria", 18));
    	
    	Label labelCarMake = new Label(vehicleData[2]);
    	labelCarMake.setTextFill(Color.web("#0076a3"));
    	labelCarMake.setFont(Font.font("Cambria", FontWeight.BOLD,  FontPosture.ITALIC, 24));
    	
    	Label labelCarModel = new Label(vehicleData[3]);
    	labelCarModel.setTextFill(Color.web("#0076a3"));
    	labelCarModel.setFont(Font.font("Cambria", 20));
    	
    	Label labelCarSeat = new Label("No Of Seats: " +vehicleData[4]);
    	labelCarSeat.setTextFill(Color.web("#0076a3"));
    	labelCarSeat.setFont(Font.font("Cambria", 18));
    	
    	Label labelCarMainReturn = new Label("Maintenance Return: " +mainteReturn);
    	labelCarMainReturn.setTextFill(Color.web("#0076a3"));
    	labelCarMainReturn.setFont(Font.font("Cambria", 18));
    	
    	
    	Label labelCarAvail = new Label(vehicleData[5]);
    	labelCarAvail.setTextFill(Color.BLUE);
    	labelCarAvail.setFont(Font.font("Cambria",  FontPosture.ITALIC, 24));

    	MiddleHBox.getChildren().add(labelCarID);
    	
        //define VBox to gather vehicle records to demonstrate 
        VBox insideVehiDetails = new VBox(); 
   		insideVehiDetails.setSpacing(3);
   		insideVehiDetails.setAlignment(Pos.CENTER_LEFT);
   		
   		insideVehiDetails.getChildren().add(labelCarMake);
  		insideVehiDetails.getChildren().add(labelCarModel);
  		insideVehiDetails.getChildren().add(labelCarYear);
  		insideVehiDetails.getChildren().add(labelCarSeat);
  		insideVehiDetails.getChildren().add(labelCarMainReturn);
  		
  		MiddleHBox.getChildren().add(insideVehiDetails);
  		MiddleHBox.getChildren().add(labelCarAvail);
  		
  		String[] columnNames = {"Record ID (VehicleID_CusID_RentDate)", "Rend Date", "Estimated Return Date", "Actual Return Date",
	    		"Rent Fee", "Late Fee"};
	    int[] columnWidth = {250, 150, 150, 150, 100, 100};
	    
	    //Table view to show vehicle rental records
	    ObservableList<String[]> ObserveRecords = FXCollections.observableArrayList();
	    ObserveRecords.addAll(Arrays.asList(recordData));
	    	    
	    TableView<String[]> tabelView = new TableView<>();
	    
	    for(int i=0; i < recordData[0].length; i++) {
	    	TableColumn tc = new TableColumn(recordData[0][i]);
	    	int colNo = i;
	    	
	    }
	    
	    for (int i = 0; i < recordData[0].length; i++) {
            TableColumn tc = new TableColumn(columnNames[i]);
            tc.setMinWidth(columnWidth[i]);
            final int colNo = i;
            tc.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
            tc.setPrefWidth(90);
            tabelView.getColumns().add(tc);
        }
  		
	    tabelView.setItems(ObserveRecords);
  		
  		MainvBox.getChildren().addAll(TopLevel,MiddleHBox,tabelView);
  		Scene VehicleDetailWindowScene = new Scene(MainvBox, 950, 500);
  		VehicleDetailWindowStage.setScene(VehicleDetailWindowScene);
  		
  		//Activates Buttons
  		refreshView.setOnAction(
  				e -> {
  					VehicleDetailWindowStage.close();
  					saveandRefresh.fire();
  				}
  				);
  		
  		
  		
  		
  		rentVehicles.setOnAction(
  				e -> {
  					
  					
  					rentData = RentDialogBox.display(VehicleID);
  					  					  					
						try {
							mainApp.rentVehicle(rentData);
						} catch (RentException e1) {
							MessageBox.display("Error", "Rent Exception");
							e1.printStackTrace();
						} catch (InvalidIdException e1) {
							MessageBox.display("Error", "Invalid ID");
							e1.printStackTrace();
						} catch (ExceptionToMainMenu e1) {
							MessageBox.display("Error", "Exception Occured. Enter Again");
							e1.printStackTrace();
						} catch (ReturnException e1) {
							MessageBox.display("Error", "Invalid Return Data");
							e1.printStackTrace();
						} catch (MaintenanceException e1) {
							MessageBox.display("Error", "Invalid Maintenance Data");
							e1.printStackTrace();
						} catch (SQLException e1) {
							MessageBox.display("Error", "Exception Occured. Enter Again");
							e1.printStackTrace();
						}
  						
  				}
  			);
  		
  		returnVehicles.setOnAction(
  				e -> {
  					
  					returnData = ReturnDialogBox.display(VehicleID);
  					  					
						try {
							mainApp.returnV(returnData);
						} catch (RentException e1) {
							MessageBox.display("Error", "Rent Exception");
							e1.printStackTrace();
						} catch (InvalidIdException e1) {
							MessageBox.display("Error", "Invalid ID");
							e1.printStackTrace();
						} catch (ExceptionToMainMenu e1) {
							MessageBox.display("Error", "Exception Occured. Enter Again");
							e1.printStackTrace();
						} catch (ReturnException e1) {
							MessageBox.display("Error", "Invalid Return Data");
							e1.printStackTrace();
						} catch (MaintenanceException e1) {
							MessageBox.display("Error", "Invalid Maintenance Data");
							e1.printStackTrace();
						} catch (SQLException e1) {
							MessageBox.display("Error", "Exception Occured. Enter Again");
							e1.printStackTrace();
						}
  					
  				}
  			);
  		
  		maintenance.setOnAction(
  				e -> {
  					
                    maintenanceData = MaintainanceDialogBox.display(VehicleID);
  					  					
						try {
							mainApp.pmaintainance(maintenanceData);
						} catch (RentException e1) {
							MessageBox.display("Error", "Rent Exception");
							e1.printStackTrace();
						} catch (InvalidIdException e1) {
							MessageBox.display("Error", "Invalid ID");
							e1.printStackTrace();
						} catch (ExceptionToMainMenu e1) {
							MessageBox.display("Error", "Exception Occured. Enter Again");
							e1.printStackTrace();
						} catch (ReturnException e1) {
							MessageBox.display("Error", "Invalid Return Data");
							e1.printStackTrace();
						} catch (MaintenanceException e1) {
							MessageBox.display("Error", "Invalid Maintenance Data");
							e1.printStackTrace();
						} catch (SQLException e1) {
							MessageBox.display("Error", "Exception Occured. Enter Again");
							e1.printStackTrace();
						}
  					
  				}
  			);
  		
  		completeMainte.setOnAction(
  				e -> {
  					
                    compMaintenanceData = CompleteMainDialogBox.display(VehicleID);
  					
						try {
							mainApp.cmaintainance(compMaintenanceData);
						} catch (RentException e1) {
							MessageBox.display("Error", "Rent Exception");
							e1.printStackTrace();
						} catch (InvalidIdException e1) {
							MessageBox.display("Error", "Invalid ID");
							e1.printStackTrace();
						} catch (ExceptionToMainMenu e1) {
							MessageBox.display("Error", "Exception Occured. Enter Again");
							e1.printStackTrace();
						} catch (ReturnException e1) {
							MessageBox.display("Error", "Invalid Return Data");
							e1.printStackTrace();
						} catch (MaintenanceException e1) {
							MessageBox.display("Error", "Invalid Maintenance Data");
							e1.printStackTrace();
						} catch (SQLException e1) {
							MessageBox.display("Error", "Exception Occured. Enter Again");
							e1.printStackTrace();
						}
  					
  				}
  			);
 
  				
  		close.setOnAction(
  				(e) -> {
  						VehicleDetailWindowStage.close();
  					}
  		);
  		
  		VehicleDetailWindowStage.showAndWait();
  		
	}

}

