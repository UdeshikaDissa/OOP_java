/*
 * --------------------------------------------------------------------
 * Developer Name 	: Udeshika Dissanayake
 * Subject			: COSC1295 Advanced Programming
 * Assignment		: Assignment 2 - Semester 1, 2019
 * Student Number	: s3400652
 * Date				: 01/06/2019 * 
 *--------------------------------------------------------------------
 */

package view;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import controller.AddVehicleDialogBox;
import controller.CompleteMainDialogBox;
import controller.ExportData;
import controller.MaintainanceDialogBox;
import controller.MessageBox;
import controller.RentDialogBox;
import controller.ReturnDialogBox;
import controller.ShowRecords;
import controller.VehicleDetailWindow;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.ExceptionToMainMenu;
import model.InvalidIdException;
import model.MaintenanceException;
import model.RentException;
import model.ReturnException;
import model.ThriftyRentSystem;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


//This is the main GUI View Class
public class MainView extends Application {
  
	//Initialize the Static Variable of String[] for passing data between main model and GUI
	static String[] rentData = new String[4];
	static String[] returnData = new String[2];
	static String[] maintenanceData = new String[1];
	static String[] compMaintenanceData = new String[2];
	static String[] addVehicleData = new String[6]; 
	
    public static void main(String[] args) throws RentException, InvalidIdException, 
    	ReturnException, MaintenanceException, ExceptionToMainMenu, SQLException{
    		Application.launch(args);
  	}

  	@Override // Override the start method in the Application class
  	public void start(Stage primaryStage) throws RentException, InvalidIdException, 
  	ReturnException, MaintenanceException, ExceptionToMainMenu, SQLException {
  	
  		try {
  		//Create an Object of ThriftyRentalSystem Class and run the startApp method
  		ThriftyRentSystem mainApp = new ThriftyRentSystem(); 
  		mainApp.startApp();
  		
  		//Get all the Vehicle Data 
  		String[] allVehi = mainApp.showallvehicles();
  		String[][] vehicleData = new String[allVehi.length][7];
  		
  		int vehiCount = 0;
  		
  		//Split Vehicle Data using : separator
  		while(allVehi[vehiCount] != null){
  			vehicleData[vehiCount] = allVehi[vehiCount].split(":");
  			vehiCount++;
  		}
  		
  		int VehicleCount = vehiCount;
  		vehiCount = 0;
  		
  		//Declare an VBox for the mainview
  		VBox MainvBox = new VBox(); 
  		MainvBox.setSpacing(10);
  		MainvBox.setAlignment(Pos.CENTER);
  			  		
  		//Define Buttons in Main View
  		Button saveandRefresh = new Button("Refresh View");
  		Button addVehicles = new Button("Add Vehicle");
  		Button rentVehicles = new Button("Rent Vehicle");
  		Button returnVehicles = new Button("Return Vehicle");
  		Button maintenance = new Button("Maintenance");
  		Button completeMainte = new Button("Complete Maintenance");
  		Button showrecords = new Button("Show Records");
  		Button saveExit = new Button("Save & Exit");
  		
  		//Define HBox to get gather top level buttons
  		HBox TopLevel = new HBox(10);
  		TopLevel.setAlignment(Pos.CENTER);

  		TopLevel.getChildren().addAll(saveandRefresh,addVehicles,rentVehicles,
  				returnVehicles,maintenance,completeMainte, showrecords, saveExit);
  		
  		//Define Buttons for Bottom Level of the main view
  		Button quitButton = new Button("Quit");
  		Button impVehicles = new Button("Import Vehicle Data");
  		Button impVehiRecords = new Button("Import Vehicle Records");
  		Button export = new Button("Export");
  		Button initDatabase = new Button("Init Database");
  		
  		//Gather buttons in the bottom view
  		HBox BottomLevel = new HBox(10); 
  		BottomLevel.setAlignment(Pos.CENTER);
  		BottomLevel.getChildren().addAll(impVehicles,
  				impVehiRecords, export, initDatabase, quitButton);
  		
  		//Declare Scrollable Middle area in the main view
  		ScrollPane MiddleScrollArea = new ScrollPane();
  		MiddleScrollArea.setPrefViewportWidth(950);
  		MiddleScrollArea.setPrefViewportHeight(500);
  		
  		//Defne GridPane to get vehicle records into scrolable areas
  		GridPane vehicleGrid = new GridPane();
  		vehicleGrid.setMinSize(300, 300);
  		vehicleGrid.setPadding(new Insets(10, 10, 10, 10));
  		vehicleGrid.setVgap(50); 
        vehicleGrid.setHgap(50);
        vehicleGrid.setAlignment(Pos.CENTER);
        
        vehiCount = 0;
        
        //Declare Button Array for each of the vehicle 
        Button[] detailButtom = new Button[allVehi.length];
        
        //initialize Vehicle records in the scrolable area
        do {
        	Label labelCarID = new Label("Rego: "+vehicleData[vehiCount][0]);
        	labelCarID.setTextFill(Color.web("#0076a3"));
        	labelCarID.setFont(Font.font("Cambria", 16));
        	
        	boolean check = new File("./Images/"+vehicleData[vehiCount][0]+".png").exists();
        	
        	if (check) {
        		//System.out.println("File exists");
        		Image image = new Image("File:Images/"+vehicleData[vehiCount][0]+".png");
        		labelCarID.setGraphic(new ImageView(image));
        	}else {
        		//System.out.println("File Not exists");
        		Image image = new Image("File:Images/NoImageAvailable.png");
        		labelCarID.setGraphic(new ImageView(image));
        	}
            	
        	Label labelCarMake = new Label(vehicleData[vehiCount][2]);
        	labelCarMake.setTextFill(Color.web("#0076a3"));
        	labelCarMake.setFont(Font.font("Cambria", FontWeight.BOLD,  FontPosture.ITALIC, 24));
        	
        	Label labelCarModel = new Label(vehicleData[vehiCount][3]);
        	labelCarModel.setTextFill(Color.web("#0076a3"));
        	labelCarModel.setFont(Font.font("Cambria", 20));
              	
        	Label labelCarAvail = new Label(vehicleData[vehiCount][5]);
        	labelCarAvail.setTextFill(Color.BLUE);
        	labelCarAvail.setFont(Font.font("Cambria",  FontPosture.ITALIC, 24));
        	
        	
	        vehicleGrid.add(labelCarID, 0, vehiCount);
	        vehicleGrid.add(labelCarAvail,2, vehiCount);
	        
	        VBox insideVehiDetails = new VBox(); 
	  		insideVehiDetails.setSpacing(3);
	  		insideVehiDetails.setAlignment(Pos.CENTER_LEFT);
	  		
	  		
	  		detailButtom[vehiCount] = new Button("Details");
	  		detailButtom[vehiCount].setMaxWidth(150);
	  		detailButtom[vehiCount].setMaxHeight(50);
	  		detailButtom[vehiCount].setStyle("-fx-text-fill: #1F618D; -fx-font-size: 2em;"
	  				+ "-fx-border-color: #1F618D; -fx-border-width: 2px;");
  		  		
	  		insideVehiDetails.getChildren().add(labelCarMake);
	  		insideVehiDetails.getChildren().add(labelCarModel);
	  
	  		
	  		vehicleGrid.add(insideVehiDetails,1, vehiCount);
	  		vehicleGrid.add(detailButtom[vehiCount],3, vehiCount);
	        
	        vehiCount++;
        
	     }while (vehiCount < VehicleCount);
        
      
        //Configure File Menu
  		Menu fileMenu = new Menu("File");
  		
  		MenuItem addVehicleMenu = new MenuItem("Add Vehicle...");
  		addVehicleMenu.setOnAction(e -> addVehicles.fire());
  		fileMenu.getItems().add(addVehicleMenu);
  		fileMenu.getItems().add(new SeparatorMenuItem());
  		
  		MenuItem saveandRefreshMenu = new MenuItem("Save & Refresh");
  		saveandRefreshMenu.setOnAction(e -> saveandRefresh.fire());
  		fileMenu.getItems().add(saveandRefreshMenu);
  		fileMenu.getItems().add(new SeparatorMenuItem());
  		  		
  		MenuItem ImportMenu = new MenuItem("Import Vehicle Data...");
  		ImportMenu.setOnAction(e -> impVehicles.fire());
  		fileMenu.getItems().add(ImportMenu);
  		
  		MenuItem ImportRecMenu = new MenuItem("Import Vehicle Records...");
  		ImportRecMenu.setOnAction(e -> impVehiRecords.fire());
  		fileMenu.getItems().add(ImportRecMenu);
  		
  		MenuItem ExportMenu = new MenuItem("Export...");
  		ExportMenu.setOnAction(e -> export.fire());
  		fileMenu.getItems().add(ExportMenu);
  		fileMenu.getItems().add(new SeparatorMenuItem());
  		  		
  		MenuItem saveandExitMenu = new MenuItem("Save and Exit");
  		saveandExitMenu.setOnAction(e -> saveExit.fire());
  		fileMenu.getItems().add(saveandExitMenu);
  		
  		MenuItem quitMenu = new MenuItem("Quit");
  		quitMenu.setOnAction(e -> quitButton.fire());
  		fileMenu.getItems().add(quitMenu);
  		
  		//Configure Update Menu
  		Menu UpdateMenu = new Menu("Update");
  		  		
  		MenuItem rentVehiMenu = new MenuItem("Rent Vehicle...");
  		rentVehiMenu.setOnAction(e -> rentVehicles.fire());
  		UpdateMenu.getItems().add(rentVehiMenu);
  		
  		MenuItem returnVehiMenu = new MenuItem("Return Vehicle...");
  		returnVehiMenu.setOnAction(e -> returnVehicles.fire());
  		UpdateMenu.getItems().add(returnVehiMenu);
  		  		
  		MenuItem regMaintenanceMenu = new MenuItem("Register for Maintenance...");
  		regMaintenanceMenu.setOnAction(e -> maintenance.fire());
  		UpdateMenu.getItems().add(regMaintenanceMenu);
  		  		
  		MenuItem compMaintenanceMenu = new MenuItem("Complete Maintenance...");
  		compMaintenanceMenu.setOnAction(e -> completeMainte.fire());
  		UpdateMenu.getItems().add(compMaintenanceMenu);
  	
  		//Configure Dummy Windows Menu
  		Menu windowMenu = new Menu("Windows");
  		
  		windowMenu.getItems().add(new MenuItem("Test..."));
  		
  		//Configure Dummy Help Menu
  		Menu HelpMenu = new Menu("Help");
  		
  		HelpMenu.getItems().add(new MenuItem("Help..."));
  	
  		//Add menu to MenuBar  		
  		MenuBar menuBar = new MenuBar();
  		menuBar.getMenus().addAll(fileMenu, UpdateMenu, windowMenu, HelpMenu);
          		
  			
  		MiddleScrollArea.setContent(vehicleGrid);
  				
  		MainvBox.getChildren().add(TopLevel);
  	    MainvBox.getChildren().add(MiddleScrollArea);
  		MainvBox.getChildren().add(BottomLevel);
  		  		
  			//Activate Detail Buttons for each vehicles  	  		
  			for(int i=0; i < VehicleCount; i++) {
  				//String vehiID = vehicleData[i][0];
  				String vehiID = allVehi[i];
  				String [] splitallVehi = vehiID.split(":");
  		     detailButtom[i].setOnAction(
  	  			(e) -> {
  	  				    
  	  					String vehiRec = mainApp.getSingleVehicleRecordData(splitallVehi[0]);
  	  				    String [] splitvehiRec = vehiRec.split(",");
  	  				    String[][] recordData = new String[splitvehiRec.length][6];
  	  				    
  	  				int recordCount = 0;
  	  				    
  	  				do{
  			  			recordData[recordCount] = splitvehiRec[recordCount].split(":");
  			  			recordCount++;
  			  		}while(recordCount < splitvehiRec.length);

  	  				  VehicleDetailWindow.display(vehiID, recordData, mainApp, saveandRefresh);
  	  				  return;
  	  				}
  	  		);
  	}
  	
  		 
  			
  		//Activate Bottom Buttons
  		quitButton.setOnAction(
  			(e) -> {
  					System.out.println("bye bye!");
  					System.exit(0);
  				}
  		);
  		
  		initDatabase.setOnAction(
  				(e) -> {
  					try {
						mainApp.initDatabase();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
  					
  				}
  		);
  		
  		export.setOnAction(
  	  			(e) -> {
  	  					
  	  				System.out.println("Export test");
  	  					
  	  				try {
						mainApp.exportDetails(ExportData.display());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
  	  					return;
  	  				}
  	  		);
  	
  		
  		//ACtivate Top Buttonss
  		saveandRefresh.setOnAction(
  	  			(e) -> {
  	  					//System.out.println("Refresh View");
  	  				    primaryStage.close();
  	  				    mainApp.end();
  	  				try {
						start( new Stage() );
					} catch (RentException | InvalidIdException | ReturnException | MaintenanceException
							| ExceptionToMainMenu | SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
  	  			
					} 
  	  	);
  	  					
  		showrecords.setOnAction(
  				(e) -> {
	  					System.out.println("Show Records");
	  				    
	  					//Get Rental Record from ThriftyRentSystem Class
	  					String[] allrecods = mainApp.getRecordData();
	  			  		String[][] recordData = new String[allrecods.length][6];
	  			  		int recordCount = 0;
	  			  		
	  			  		while(allrecods[recordCount] != null){
	  			  			recordData[recordCount] = allrecods[recordCount].split(":");
	  			  			recordCount++;
	  			  		}
	  			  		
	  			  		int RecordCount = recordCount;
	  			  	    recordCount = 0;
	  			  	    
	  			  	ShowRecords.display(recordData);
		  			return;	
					} 
  		);
  		
  		saveExit.setOnAction(
  	  			(e) -> {
  	  			System.out.println("Save and Exiting");
  	  		    primaryStage.close();
			    mainApp.end();
			    System.exit(0);
  	  			}
  		);
  		
  		addVehicles.setOnAction(
  				e -> {
  					
  					addVehicleData = AddVehicleDialogBox.display();
  					try {
						mainApp.addVehicle(addVehicleData);
					} catch (RentException | InvalidIdException | ExceptionToMainMenu | ReturnException
							| MaintenanceException | SQLException e1) {
						MessageBox.display("Error", "Add Vehicle Exception");
						e1.printStackTrace();
					}
  				}
  		);
  		
  		rentVehicles.setOnAction(
  				e -> {
  					
  					rentData = RentDialogBox.display();
  					
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
  					
  					returnData = ReturnDialogBox.display();
  					//System.out.println("returnData passed "+returnData);
  					//System.out.println("return Vehicle ID Passed "+returnData[0]);
  					
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
  					
  					maintenanceData = MaintainanceDialogBox.display();
  					
  					System.out.println("maintainance Vehicle ID Passed "+maintenanceData[0]);
  					
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
  					
  					compMaintenanceData = CompleteMainDialogBox.display();
  					
  					System.out.println("Return Maintenance Vehicle ID Passed "+compMaintenanceData[0]);
  					
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
  		
  		
  		// set up the window components
  		BorderPane borderPane = new BorderPane();
  		borderPane.setCenter(MainvBox);
  		borderPane.setTop(menuBar);
  		BorderPane.setAlignment(MainvBox, Pos.CENTER);
  		
  		Scene scene = new Scene(borderPane, 1000, 650);
  		primaryStage.setTitle("Thrifty Rent System"); // Set the stage title
  		primaryStage.setScene(scene); // Place the scene in the stage
  		primaryStage.show(); // Display the stage
  	
  	}
  		catch(InvalidIdException ex) {
  			System.exit(0);
	    }
		
       catch(ExceptionToMainMenu ex) {
	    	System.exit(0);
	    }
		
		catch(RentException ex) {
			System.exit(0);
	    }
		
		catch(Exception e) {
			System.exit(0);
	    }
      
 
  }
}


   

   
