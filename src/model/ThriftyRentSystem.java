/*
 * --------------------------------------------------------------------
 * Developer Name 	: Udeshika Dissanayake
 * Subject			: COSC1295 Advanced Programming
 * Assignment		: Assignment 2 - Semester 1, 2019
 * Student Number	: s3400652
 * Date				: 01/06/2019 * 
 *--------------------------------------------------------------------
 */

package model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import controller.MessageBox;
import util.DateTime;
import view.MainView;
import model.AbstractVehicle;
import model.Car;
import model.RentalRecord;
import model.Van;
import model.Vehicle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//Main model class for ThriftyRentalSystem

public class ThriftyRentSystem {
	
	//declare constant for size of Vehicle Database and Size of Records Database
	public final static int vehicleDataSize = 20;
	public final static int recordDataSize = 80;
	
	//Define Vehicle Array List 
	private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
	
	//Declaration of String 2D Array for Vehicle and Record Data 
	private String[][] vehicleData = new String[vehicleDataSize][7]; 
	private String[][] recordData = new String[recordDataSize][6];

	//declaration of private variable to hold rent, return, maintainance and found vehcile ID status
	private boolean rentSuccess;
	private boolean returnSuccess;
	private boolean maintainanceSuccess;
	private boolean foundVehicleID = false;
	
	//Declation and initialization of counter to count number of vehicles
	int noOfVehicles = 0;
	
	//define addVehicle Method
	public void addVehicle(String[] addVehicleData) throws RentException, InvalidIdException, 
	ExceptionToMainMenu, ReturnException, MaintenanceException, SQLException{
		
		//define a flag to capture add vehicle status
		boolean done = false; 
		
		try {
		
		//Input String Array assign to local String variables;
		String type = addVehicleData[0];
		String vehiID = addVehicleData[1];
		String manuYear = addVehicleData[2];
		String carMake = addVehicleData[3];
		String carModel = addVehicleData[4];
		
		int temp_manuYear = Integer.parseInt(manuYear); //Cast String variable to Integer
		
		
		do { 
			try {
		         ++noOfVehicles;
		       	if (type.equals("Car")) {  //Adding a car if type is a Car
						vehicles.add(new Car());
						vehicles.get(noOfVehicles-1).setID("C_" + vehiID); 
					   	vehicles.get(noOfVehicles-1).setYear(temp_manuYear);
					   	vehicles.get(noOfVehicles-1).setMake(carMake);
					   	vehicles.get(noOfVehicles-1).setModel(carModel);
					   	
					    String carSeats = addVehicleData[5];
					    int temp_carSeats = Integer.parseInt(carSeats);
					     
					    int temp_seat = temp_carSeats;
					    
					    // validation - Checking whether the number of seats is 4 or 7 for Car 
					    if(temp_seat == 4 || temp_seat == 7) {
					    vehicles.get(noOfVehicles-1).setSeats(temp_seat);
					    }else {
						    	MessageBox.display("Error Seat Number", "Number of Seats should be either 4 or 7 for a Car");
						    	--noOfVehicles;
						    	return;
						}
		  
					    vehicles.get(noOfVehicles-1).setStatus(true);
		    
				    //a dummy temp DateTime variable define and initialize to pass to setRentalRecord method
				    DateTime temp_date = new DateTime(0,0,0);
				    vehicles.get(noOfVehicles-1).setRentalRecord(vehicles.get(noOfVehicles-1).getId(),
				    		"none",temp_date,temp_date,temp_date,0,0);
		    	} 
				else if (type.equals("Van")) { //Adding a Van if type is a Car
						vehicles.add(new Van());
						vehicles.get(noOfVehicles-1).setID("V_" + vehiID); //Provide prefix of V_ for vans
					    vehicles.get(noOfVehicles-1).setYear(temp_manuYear);
					    vehicles.get(noOfVehicles-1).setMake(carMake);
					    vehicles.get(noOfVehicles-1).setModel(carModel);
						vehicles.get(noOfVehicles-1).setSeats(15);
					    vehicles.get(noOfVehicles-1).setStatus(true);
			
					  //a dummy temp DateTime variable define and initialize to pass to setRentalRecord method
					    DateTime temp_date = new DateTime(0,0,0);
					    vehicles.get(noOfVehicles-1).setRentalRecord(vehicles.get(noOfVehicles-1).getId(),
					    		"none",temp_date,temp_date,temp_date,0,0);
						
					    vehicles.get(noOfVehicles-1).completeMaintenance(temp_date);
		    
				}else{
					    MessageBox.display("Error - Adding a Vehicle", "Invalid Entry...Try Again");
						noOfVehicles = noOfVehicles-1;
						return;
					  }
	        //Output GUI message
		    MessageBox.display("Adding a Vehicle - Success", "Vahicle Added Successfully");
		    System.out.println(vehicles.get(noOfVehicles-1).toString());
		    done = true;
		    }
		    
		    //catch(InputMismatchException ex) {
			catch(Exception e) {
				MessageBox.display("Error - Adding a Vehicle", "Invalid Entry...Try Again");
		    	noOfVehicles = noOfVehicles-1;
		    	return;
		    }
		}while (!done);  
		
	}
		
		catch(Exception e) {
			MessageBox.display("Error - Adding a Vehicle", "Invalid Entry...Try Again");
			return;
	    }
	}
	
	
	//rentVehicle() private method is defined to rent a Vehicle 
	//Take input from rentData String Array
	public void rentVehicle(String[] rentData) throws RentException, InvalidIdException, 
									ExceptionToMainMenu, ReturnException, MaintenanceException, SQLException{
		
		String temp_id = rentData[0];
		String CusID = rentData[1];
		String date_String = rentData[2];
		String rent_Duration = rentData[3];
		
		boolean done = false;
		
       try {
			
		do { 
			
		if (noOfVehicles==0) {
			throw new ExceptionToMainMenu("No Vehicles in Database to Rent");
		}
						
        int count = 0;
        boolean temp_vehiStatus = true;
		
		do { 
		//validating the availability of the vehicle below:
		if(temp_id.equals(vehicles.get(count).getId())) {
			foundVehicleID = true; //update the flag
			temp_vehiStatus = vehicles.get(count).getStatus();
		}
		
		count++;
		}while(count < noOfVehicles);
		
		if(foundVehicleID==false) {
			foundVehicleID = false;
			throw new InvalidIdException("ID Not Found");
			
		}else {
			foundVehicleID = false;
		}
		
		if(temp_vehiStatus == false) {
			throw new RentException("Vehicle " + temp_id + " could not be rented");
		}
				   
		String [] splitdate_String = date_String.split("/");
		
		//cast Sptring variables splitted from date input to integer in order to pass to DateTime() 
		int temp_RentDay = Integer.parseInt(splitdate_String[0]);
		int temp_RentMonth = Integer.parseInt(splitdate_String[1]);
		int temp_RentYear = Integer.parseInt(splitdate_String[2]);
		
		DateTime RentDate = new DateTime(temp_RentDay, temp_RentMonth, temp_RentYear);
		
		int temp_RentDuration = Integer.parseInt(rent_Duration);
		count = 0;
		rentSuccess = false; //setting the flag to default false;
		
		do { 
			//validating the availability of the vehicle below:
		if(temp_id.equals(vehicles.get(count).getId()) && vehicles.get(count).getStatus() == 
				true && vehicles.get(count).getMaintainanceStatus() == false) {
			rentSuccess = true; //update the flag
			vehicles.get(count).rent(CusID,RentDate,temp_RentDuration); //calling out the method in the object class
		}
		
		count++;
		}
		while(count < noOfVehicles);
		
		if(rentSuccess == false)
				MessageBox.display("Warning", "Vehicle " + temp_id + " could not be rented");
		done = true;
	 }while (!done);
		
		return;
		
  }	
	
		catch(InvalidIdException ex) {
	    	//System.out.println("/nInvalid Input...Enter Again");
	    	return;
	    }
		
       catch(ExceptionToMainMenu ex) {
	    	//System.out.println("/nInvalid Input...Enter Again");
	    	return;
	    }
		
		catch(RentException ex) {
	    	//System.out.println("/nInvalid Input...Enter Again");
	    	return;
	    }
		
		catch(Exception e) {
			MessageBox.display("Error", "Invalid Input. Enter Again");
	    	return;
	    }
		
			
}
	
	//This method is defined to return a Vehicle 
	//Takes input from retunData String Array. Vehicle ID and Date
	public void returnV(String[] returnData) throws ReturnException, 
			InvalidIdException, RentException, MaintenanceException, ExceptionToMainMenu, SQLException{
		
		String temp_id = returnData[0];
		String date_String = returnData[1];
			
		try {
				if (noOfVehicles==0) {
				throw new ExceptionToMainMenu("No Vehicles in Database to Return");
			}	
		
        int count = 0;
		
		do { 
			//validating the availability of the vehicle below:
		if(temp_id.equals(vehicles.get(count).getId())) {
			foundVehicleID = true; //update the flag
		}
				count++;
		}
		while(count < noOfVehicles);
		
		if(foundVehicleID==false) {
			foundVehicleID = false;
			throw new InvalidIdException("ID Not Found");
			
		}else {
			foundVehicleID = false;
		}
		
		String [] splitdate_String = date_String.split("/");
		
		//cast Sptring variables splitted from date input to integer inorder to pass to DateTime()
		int temp_ReturnDay = Integer.parseInt(splitdate_String[0]);
		int temp_ReturnMonth = Integer.parseInt(splitdate_String[1]);
		int temp_ReturnYear = Integer.parseInt(splitdate_String[2]);
		
		DateTime ReturnDate = new DateTime(temp_ReturnDay, temp_ReturnMonth, temp_ReturnYear);
		
        count = 0;
        returnSuccess = false;
   	
		do { 
			//validating the possibility to return the vehicle below:
						
		if(temp_id.equals(vehicles.get(count).getId()) && vehicles.get(count).getStatus() == false) {
			vehicles.get(count).returnVehicle(ReturnDate);
			returnSuccess = true;
			MessageBox.display("Message", "Vehicle " + temp_id + " is now Returned");
		}
			count++;
		}
		while(count < noOfVehicles);
		
		if(returnSuccess == false) {
					throw new ReturnException("Vehicle " + temp_id + " could not be returned");
		}
		
		}
		
		catch(InvalidIdException ex) {
	    	//System.out.println("/nInvalid Input...Enter Again");
	    	return;
	    }
		
		catch(ExceptionToMainMenu ex) {
	    	//System.out.println("/nInvalid Input...Enter Again");
	    	return;
	    }
		
		catch(ReturnException ex) {
	    	//System.out.println("/nInvalid Input...Enter Again");
	    	 	return;
	    }
					
		catch(Exception e) {
			MessageBox.display("Error", "Invalid Input. Enter Again");
	 		return;
	    }
		
	}
	
	//this method is defined to register a vehicle for maintainace
	public void pmaintainance(String[] maintainanceData) throws MaintenanceException, InvalidIdException, 
				RentException, ReturnException, ExceptionToMainMenu, SQLException{
		
		String temp_id = maintainanceData[0];
		
		try {
			
			if (noOfVehicles==0) {
				throw new ExceptionToMainMenu("No Vehicles in Database for Maintenance");
			}	
						
        int count = 0;
		
		do { 
			//validating the availability of the vehicle below:
		if(temp_id.equals(vehicles.get(count).getId())) {
			foundVehicleID = true; //update the flag
		}
			count++;
		}
		while(count < noOfVehicles);
		
		if(foundVehicleID==false) {
			foundVehicleID = false;
			throw new InvalidIdException("ID Not Found");
		}else {
			foundVehicleID = false;
		}
		
		count = 0;
		maintainanceSuccess = false;
		
		do { 
			//validating the possibility to perform maintainace below:
		if(temp_id.equals(vehicles.get(count).getId()) && vehicles.get(count).getStatus() == true) {
			System.out.println("Vehicle " + temp_id + " is registered for Maintainance");
			maintainanceSuccess = true;
			vehicles.get(count).performMaintenance();
			MessageBox.display("Maintenance Status", "Vehicle " + temp_id + " is registered for Maintainance");
				}
			
		count++;
		}
		while(count < noOfVehicles);
		
		if(maintainanceSuccess == false)
			//System.out.println("Vehicle " + temp_id + " could not be registered for Maintainance");
			throw new MaintenanceException("Vehicle " + temp_id + " could not be registered for Maintainance");
	}
		
		catch(InvalidIdException ex) {
	    	//System.out.println("/nInvalid Input...Enter Again");
	    	return;
	    }
		
		catch(ExceptionToMainMenu ex) {
	    	//System.out.println("/nInvalid Input...Enter Again");
	    	return;
	    }
		
		catch(MaintenanceException ex) {
	    	//System.out.println("/nInvalid Input...Enter Again");
	    	return;
	    }
		
		catch(Exception e) {
	    	//System.out.println("/nInvalid Input...Enter Again");
	    	MessageBox.display("Error", "Invalid Input. Enter Again");
	    	return;
	    }
	}
		

	//this method is defined to complete maintainace
	public void cmaintainance(String[] compMaintenanceData) throws RentException, 
			InvalidIdException, ReturnException, MaintenanceException, ExceptionToMainMenu, SQLException {
		
		String temp_id = compMaintenanceData[0];
		String date_String = compMaintenanceData[1];
		
       try {
			if (noOfVehicles==0) {
				throw new ExceptionToMainMenu("No Vehicles in Database for Maintenance Return");
			}	
		
        int count = 0;
		
		do { 
			//validating the availability of the vehicle below:
		if(temp_id.equals(vehicles.get(count).getId())) {
			foundVehicleID = true; //update the flag
		}
			count++;
		}
		while(count < noOfVehicles);
		
		if(foundVehicleID==false) {
			throw new InvalidIdException("ID Not Found");
		}else {
			foundVehicleID = false;
		}
	    
		String [] splitdate_String = date_String.split("/");
		
		//cast Sptring variables splitted from date input to integer inorder to pass to DateTime()
		int temp_ReturnDay = Integer.parseInt(splitdate_String[0]);
		int temp_ReturnMonth = Integer.parseInt(splitdate_String[1]);
		int temp_ReturnYear = Integer.parseInt(splitdate_String[2]);
		
		DateTime MainCompDate = new DateTime(temp_ReturnDay, temp_ReturnMonth, temp_ReturnYear);
		
		count = 0;
		maintainanceSuccess = false;
		
		do { 
			//validating the possibility to perform maintainace below:
		if(temp_id.equals(vehicles.get(count).getId()) && 
				vehicles.get(count).getMaintainanceStatus() == true) {
			MessageBox.display("Message", "Vehicle " + temp_id + " is now completing Maintainance");
			maintainanceSuccess = true;
			vehicles.get(count).completeMaintenance(MainCompDate);
				}
			
		count++;
		}
		while(count < noOfVehicles);
		
		if(maintainanceSuccess == false)
			throw new MaintenanceException("Vehicle " + temp_id + " is not currenty registered for Maintainance");
       }
       
       catch(InvalidIdException ex) {
	    	//System.out.println("/nInvalid Input...Enter Again");
    	   return;
	    }
       
       catch(MaintenanceException ex) {
	    	//System.out.println("/nInvalid Input...Enter Again");
    	   return;
	    }
       
       catch(Exception e) {
    	    MessageBox.display("Error", "Invalid Input. Enter Again");
	    	return;
	    	
	    }
     }
  
	
	//This method passes the all vehicle data as String Array to MainView Class for GUI	
	public String[] showallvehicles() {
	
		System.out.println("\nVehicles in the Database");
		System.out.println("--------------------------");
		int count = 0;
		
		String[] allVehi = new String[vehicleDataSize];
		
		do { 
			System.out.println(vehicles.get(count).toString());
			allVehi[count] = vehicles.get(count).toString();
			count++;
			}
		while(count < noOfVehicles);
				
		return allVehi;
	}
	

	//This method passes all the Rental Record dat as String Array to MainView for GUI
	public String[] getRecordData() {
		
		int count = 0;
		int index = 0;
		
		String[] allRecord = new String[recordDataSize];
				
		do { 
			ArrayList<RentalRecord> RR_read = vehicles.get(count).getRentalRecord_raw();
			
			int count2 = RR_read.size();
			int limit = 0;
			
			/*to show only recent 10 records*/
			if(RR_read.size() > 10)
				limit = RR_read.size() - 10;
			
			do {
				String vehicleRentalRecord2 = RR_read.get(count2-1).toString();
				allRecord[index] = vehicleRentalRecord2;
				count2--;
				index++;
			}while(count2 > limit);
			
		count++;
		}
		while(count < noOfVehicles);
			
		return allRecord;
	}

	
	//This method take input of Vehicle ID and find the Vehicle Record of that Vehicle. 
	//Then retun the vehicle data as a String
	public String getSingleVehicleRecordData(String vehiID) {
			int count = 0;
			int index = 0;
			
			do {
				if(vehiID.equals(vehicles.get(count).getId())){
				index = count;
				}
				count++;
			}while(count<noOfVehicles);
		
			String StringRec = vehicles.get(index).getRentalRecord_raw().toString();
		    return StringRec;
	}


	//Method define to initialize the JDBC database	
	public void initDatabase() throws SQLException {
		
		/*Create an object of dataBaseTools Class
		 * Connect to Database 
		 * Create VEHICLE  and RENTAL RECORD Tables
		 * Fill initial Data
		 * Show Database records in console for varification
	    */
		dataBaseTools dbT1 = new dataBaseTools();
		dbT1.ConnectionTest();
		dbT1.CreateTable();
		dbT1.fillInitData();
		dbT1.SelectQuery();
		MessageBox.display("Initializing Database","Databases have been configured Successfully");
}
	

	
	//This method get file location as input from GUI and Export Record data to Txt File
	public void exportDetails(String filename) throws IOException {
		
		int count = 0;
		
		  FileWriter fstream = new FileWriter(filename);
		  BufferedWriter out = new BufferedWriter(fstream);
		  out.write("Export Vehicle Details and Vehicle Records");
		  out.write(System.lineSeparator());
		  out.write("===========================================");
		  out.write(System.lineSeparator());
		  out.write(System.lineSeparator());
				
		do { 
		
			String vehicleRecord = vehicles.get(count).toString();
			
			String [] splitRecord_Veh = vehicleRecord.split(":");
			
			//System.out.println("\n=======================================");
			//System.out.println("VEHICLE RECORD");
						
			//System.out.println("Vehicle ID:					" + splitRecord_Veh[0]);
			//System.out.println("Year:						" + splitRecord_Veh[1]);
			//System.out.println("Make:						" + splitRecord_Veh[2]);
			//System.out.println("Model:						" + splitRecord_Veh[3]);
			//System.out.println("No of Seats:					" + splitRecord_Veh[4]);
			//System.out.println("Status :					" + splitRecord_Veh[5]);
			
			//System.out.println("");
			//System.out.println("RENTAL RECORD");
			
			ArrayList<RentalRecord> RR_read = vehicles.get(count).getRentalRecord_raw();
			
			int count2 = RR_read.size();
			int limit = 0;
			
			/*to show only recent 10 records*/
			if(RR_read.size() > 10)
				limit = RR_read.size() - 10;
			
			out.write(vehicleRecord);
			out.write(":"+splitRecord_Veh[0]+".PNG");
			out.write(System.lineSeparator());
			
			do {
				
				String vehicleRentalRecord2 = RR_read.get(count2-1).toString();
				//String [] splitvehicleRentalRecord2 = vehicleRentalRecord2.split(":");
				
				//System.out.println("Record ID   :				" + splitvehicleRentalRecord2[0]);
				//System.out.println("Rend Day    :				" + splitvehicleRentalRecord2[1]);
				//System.out.println("Estimated Return Date :			" + splitvehicleRentalRecord2[2]);
				//System.out.println("Actual Return Date :			" + splitvehicleRentalRecord2[3]);
				//System.out.println("Rental Fee ($):				" + splitvehicleRentalRecord2[4]);
				//System.out.println("Late Fee ($):				" + splitvehicleRentalRecord2[5]);
				//System.out.println("---------------------------------------------------------------");
				
				count2--;
				
				out.write(vehicleRentalRecord2);
				out.write(System.lineSeparator());
				
			}while(count2 > limit);
			
			out.write(System.lineSeparator());
			
		count++;
		}
		while(count < noOfVehicles);
		
		out.flush();
		out.close();
	}
	

	/*Start method of the programme.
	 * this get the vehicle records and rental records from HSQLDB databases
	*/
	public void startApp() throws RentException, InvalidIdException, 
				ReturnException, MaintenanceException, ExceptionToMainMenu, SQLException{
        
		//Create object of the class dataBaseTools and retreive data from Vehicle 
		//and Rental Records data tables.
		dataBaseTools dbT2 = new dataBaseTools();
		vehicleData = dbT2.readvehicleTableToArray();
		recordData =  dbT2.readrecordTableToArray();
		
		int i = 0;
				
		while(vehicleData[i][0] != null) {
			
			noOfVehicles++;
			
			//if Vehicle ID starts with C then create a Car object and do the settings accrodingly
			if(vehicleData[i][0].charAt(0) == 'C') {
				vehicles.add(new Car());
				vehicles.get(noOfVehicles-1).setID(vehicleData[i][0]);
				vehicles.get(noOfVehicles-1).setYear(Integer.parseInt(vehicleData[i][1]));
				vehicles.get(noOfVehicles-1).setMake(vehicleData[i][2]);
				vehicles.get(noOfVehicles-1).setModel(vehicleData[i][3]);
				vehicles.get(noOfVehicles-1).setSeats(Integer.parseInt(vehicleData[i][4]));
				  if(vehicleData[i][5].equals("Available")) {
					  vehicles.get(noOfVehicles-1).setStatus(true);
				  } else if(vehicleData[i][5].equals("Not Available")) {
					  vehicles.get(noOfVehicles-1).setStatus(false);
					  vehicles.get(noOfVehicles-1).performMaintenance(); 
					  
				  }
				  
				  //set first Rental Record
				  DateTime temp_date = new DateTime(0,0,0);
				    vehicles.get(noOfVehicles-1).setRentalRecord(vehicles.get(noOfVehicles-1).getId(),
				    		"none",temp_date,temp_date,temp_date,0,0);
			
		    //if Vehicle ID starts with V then create a Van object and do the settings accrodingly	    
			} else if(vehicleData[i][0].charAt(0) == 'V'){
				vehicles.add(new Van());
				vehicles.get(noOfVehicles-1).setID(vehicleData[i][0]);
				vehicles.get(noOfVehicles-1).setYear(Integer.parseInt(vehicleData[i][1]));
				vehicles.get(noOfVehicles-1).setMake(vehicleData[i][2]);
				vehicles.get(noOfVehicles-1).setModel(vehicleData[i][3]);
				vehicles.get(noOfVehicles-1).setSeats(Integer.parseInt(vehicleData[i][4]));
				  if(vehicleData[i][5].equals("Available")) {
					  vehicles.get(noOfVehicles-1).setStatus(true);
				  } else if(vehicleData[i][5].equals("Not Available")) {
					  vehicles.get(noOfVehicles-1).setStatus(false);
					  vehicles.get(noOfVehicles-1).performMaintenance(); 
					  //
				  }
				  
				//set first Rental Record
				  DateTime temp_date = new DateTime(0,0,0);
				    vehicles.get(noOfVehicles-1).setRentalRecord(vehicles.get(noOfVehicles-1).getId(),
				    		"none",temp_date,temp_date,temp_date,0,0);
				  
				  if(vehicleData[i][6].equals("none")) {
					  	    vehicles.get(noOfVehicles-1).setcompleteMaintainanceData(temp_date);
				  }else  {
					  
					  String date_String = vehicleData[i][6];
					  String [] splitdate_String = date_String.split("/");
					  
					  int temp_RentDay = Integer.parseInt(splitdate_String[0]);
					  int temp_RentMonth = Integer.parseInt(splitdate_String[1]);
					  int temp_RentYear = Integer.parseInt(splitdate_String[2]);
					  
					  DateTime ComptMainDate = new DateTime(temp_RentDay, temp_RentMonth, temp_RentYear);
					  
					  vehicles.get(noOfVehicles-1).setcompleteMaintainanceData(ComptMainDate);
						  
				  }
			}
			    		i++;
	}
		
		System.out.println("Number of Vehicles Added - "+noOfVehicles);
		int vehi_count = 0;
		int record_count = 0;
		
		//Compare Vehicle ID with Record ID and set Rental Record of the vehicle accordingly
		do { 
			while (vehicleData[vehi_count][0] != null && recordData[record_count][0] != null) {
				
		       if(vehicleData[vehi_count][0].equals(recordData[record_count][0].substring(0,8))) {
			      //System.out.println("Vehicle ID " + vehicles.get(vehi_count).getId());
			      //System.out.println("Record ID " + recordData[record_count][0]);
			      
			      String rentdate_String = recordData[record_count][1];
				  String [] splitRentdate_String = rentdate_String.split("/");
				  
				  int temp_RentDay = Integer.parseInt(splitRentdate_String[0]);
				  int temp_RentMonth = Integer.parseInt(splitRentdate_String[1]);
				  int temp_RentYear = Integer.parseInt(splitRentdate_String[2]);
				  
				  DateTime rentDate = new DateTime(temp_RentDay, temp_RentMonth, temp_RentYear);
				  
				  String forecastdate_String = recordData[record_count][2];
				  String [] forecastRentdate_String = forecastdate_String.split("/");
				  
				  int temp_forecastDay = Integer.parseInt(forecastRentdate_String[0]);
				  int temp_forecastMonth = Integer.parseInt(forecastRentdate_String[1]);
				  int temp_forecastYear = Integer.parseInt(forecastRentdate_String[2]);
				  
				  DateTime forecastDate = new DateTime(temp_forecastDay, temp_forecastMonth, temp_forecastYear);
				  
				  
				  String actualdate_String = recordData[record_count][3];
				  
				  int temp_actualDay;
				  int temp_actualMonth;
				  int temp_actualYear;
				  
				  if(actualdate_String.equals("none")) {
					  
					  temp_actualDay = 0;
					  temp_actualMonth = 0;
					  temp_actualYear = 0;
					  
					  DateTime temp_day = new DateTime();
					  
					  vehicles.get(vehi_count).setRentDate(rentDate);
					  vehicles.get(vehi_count).setestimatedReturnDate(rentDate,temp_day.diffDays(forecastDate,rentDate));
					  vehicles.get(vehi_count).setCusID(recordData[record_count][0].substring(9, 13));
				      //System.out.println("Estimated Return Date" + vehicles.get(vehi_count).getestimatedReturnDate());
					  
				  } else {
				  
					  String [] actualRentdate_String = actualdate_String.split("/");
					  temp_actualDay = Integer.parseInt(actualRentdate_String[0]);
					  temp_actualMonth = Integer.parseInt(actualRentdate_String[1]);
					  temp_actualYear = Integer.parseInt(actualRentdate_String[2]);
				  }
				  
				  DateTime actualDate = new DateTime(temp_actualDay, temp_actualMonth, temp_actualYear);
			       
			      vehicles.get(vehi_count).setRentalRecord(vehicleData[vehi_count][0],recordData[record_count][0].substring(9, 13),
			    		  rentDate,forecastDate,actualDate,Double.parseDouble(recordData[record_count][4]),
			    		  Double.parseDouble(recordData[record_count][5]));
		       	  }
			  	
		       vehi_count++;
		}
			
		record_count++;
		vehi_count = 0;
		
		}
		
		
		while(record_count < recordData.length);
		
		//If no records in the Tables, initializing the Database through initDatabase()
		if(noOfVehicles == 0) {
			MessageBox.display("Warning","No Vehicle Data avaiable. Initialize the Database...");
			initDatabase();
			MessageBox.display("Thrifty Rental Systems ","Program Closes...Restart Again.");
			end();
		} else 
		{
			
		return;
		}
	}
	
	
	/*This method runs each time the programme is closed
	Take all the Vehicle and Rental Record data and write them back to HSQLDB database
	*/
	public void end() {
		
		int vehi_count = 0;
			
		if(noOfVehicles != 0) {
		
		do {
            String vehicleRecord = vehicles.get(vehi_count).toString();
            String [] splitRecord_Veh = vehicleRecord.split(":");
			
          //Update Vehicle Records - for existing vehicles
			if(splitRecord_Veh[0].equals(vehicleData[vehi_count][0]) && vehicleData[vehi_count][0] != null) {
				   vehicleData[vehi_count][5] = splitRecord_Veh[5];
				      if(splitRecord_Veh[0].charAt(0) == 'V') {
				           vehicleData[vehi_count][6] = splitRecord_Veh[6];
				      }
				   
				      //For Van and Car do differently due to Mantainance Date detail
				      dataBaseTools dbT2 = new dataBaseTools();
				      if(splitRecord_Veh[0].charAt(0) == 'V') { 
				      dbT2.modifyVehicleRow(splitRecord_Veh[0],splitRecord_Veh[5],splitRecord_Veh[6]);
				      }else if(splitRecord_Veh[0].charAt(0) == 'C') {
				    	  dbT2.modifyVehicleRow(splitRecord_Veh[0],splitRecord_Veh[5],"none");
				      }
			}
			 
			//Add new Vehicle Records - If new Vehicle is added during the programme Run time
			if(!splitRecord_Veh[0].equals(vehicleData[vehi_count][0])) {
				   //System.out.println("new Vehicle found is "+splitRecord_Veh[0]);
				   //append a new entry to the vehicle database
				   dataBaseTools dbT1 = new dataBaseTools();
				   
				 //For Van and Car do differently due to Mantainance Date detail
				   if(splitRecord_Veh[0].charAt(0) == 'V') { 
				   dbT1.InsertVehicleRow(splitRecord_Veh[0],splitRecord_Veh[1],splitRecord_Veh[2],
						   splitRecord_Veh[3],splitRecord_Veh[4],splitRecord_Veh[5],
								   splitRecord_Veh[6]);
				   }else if(splitRecord_Veh[0].charAt(0) == 'C') {
					   dbT1.InsertVehicleRow(splitRecord_Veh[0],splitRecord_Veh[1],splitRecord_Veh[2],
							   splitRecord_Veh[3],splitRecord_Veh[4],splitRecord_Veh[5],
									   "none"); 
				   }
			   }
			   
			   //Get all the Rental REcord DAta to RentalRecord Array List
			   ArrayList<RentalRecord> RR_read = vehicles.get(vehi_count).getRentalRecord_raw();
			   
	           int RRrec_count1 = 0;
	           int record_count1 = 0;
	           boolean found = false;
	               
	           while(RRrec_count1<RR_read.size()) {
	        	   
	            	   String vehicleRentalRecord2 = RR_read.get(RRrec_count1).toString();
	            	   String [] splitvehicleRentalRecord2 = vehicleRentalRecord2.split(":");
	            	   
	               while(recordData[record_count1][0] != null) {
	            	  	            	   
	            	   if(splitvehicleRentalRecord2[0].equals(recordData[record_count1][0])) {
	            				           				           				
	           				dataBaseTools dbT2 = new dataBaseTools();
	           					           				           				
	           				if(splitvehicleRentalRecord2[4].equals("none")) {
	           				dbT2.modifyRecordRow(splitvehicleRentalRecord2[0],splitvehicleRentalRecord2[3],
	           						"0", "0");
	           				} else if (splitvehicleRentalRecord2[5].equals("none")) {
	           					dbT2.modifyRecordRow(splitvehicleRentalRecord2[0],splitvehicleRentalRecord2[3],
	           							splitvehicleRentalRecord2[4], "0");
	           				} else {
	           					dbT2.modifyRecordRow(splitvehicleRentalRecord2[0],splitvehicleRentalRecord2[3],
	           							splitvehicleRentalRecord2[4], splitvehicleRentalRecord2[5]);
	           				}
	           				
	           				found = true;
	            	   } 
	            	      
	            	   record_count1++;
	               }
	               
	               RRrec_count1++;
	                 
	               if(found == false && !splitvehicleRentalRecord2[0].substring(9,13).equals("none")) {
	          		  dataBaseTools dbT3 = new dataBaseTools();
	          		 
	          		if(splitvehicleRentalRecord2[3].equals("none")) {
	          			 dbT3.InsertRecordRow(splitvehicleRentalRecord2[0],splitvehicleRentalRecord2[1],
	 	          				splitvehicleRentalRecord2[2],splitvehicleRentalRecord2[3],
	 	          				"0","0");
	          		}else if (splitvehicleRentalRecord2[5].equals("none")) {
	          			dbT3.InsertRecordRow(splitvehicleRentalRecord2[0],splitvehicleRentalRecord2[1],
	 	          				splitvehicleRentalRecord2[2],splitvehicleRentalRecord2[3],
	 	          				splitvehicleRentalRecord2[4],"0");
	          		} else {
	          			dbT3.InsertRecordRow(splitvehicleRentalRecord2[0],splitvehicleRentalRecord2[1],
	 	          				splitvehicleRentalRecord2[2],splitvehicleRentalRecord2[3],
	 	          				splitvehicleRentalRecord2[4],splitvehicleRentalRecord2[5]);
	          		}
	          		 
	          		recordData[record_count1][0] = splitvehicleRentalRecord2[0];
	          		recordData[record_count1][1] = splitvehicleRentalRecord2[1];
	          		recordData[record_count1][2] = splitvehicleRentalRecord2[2];
	          		recordData[record_count1][3] = splitvehicleRentalRecord2[3];
	          		recordData[record_count1][4] = splitvehicleRentalRecord2[4];
	          		recordData[record_count1][5] = splitvehicleRentalRecord2[5];
	          		
	               
	             }
	             record_count1 = 0;
	             found = false;
          	   }
			   
			   vehi_count++; 
			   
						
		}while(vehi_count < noOfVehicles);
		
		} 
		
		return;
}

}
