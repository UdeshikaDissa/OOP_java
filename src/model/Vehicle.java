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
import java.io.*;
import util.DateTime;

//interface super class for Abstract vehicles; 
//declare public methods which will be defined and overriden at child class
public interface Vehicle {

	String getId();
	public void setID(String id);
	
	int getYear();
	public void setYear(int year);
	
	String getMake();
	public void setMake(String make);
	
	String getModel();
	public void setModel(String model);
	
	int getSeats();
	public void setSeats(int seats);
	
	String getCusID();
	public void setCusID(String id);
	
	boolean getStatus();
	public void setStatus(boolean status);
	
	boolean getMaintainanceStatus();
		
	public double getRentalFee();
	public void setRentalFee(double rentFee);
	
	public double getLateFee();
	public void setLateFee(double lateFee);
	
	public DateTime getRentDate();
	public void setRentDate(DateTime rentDate);
				
	public void setestimatedReturnDate(DateTime returnDate, int duration);
	public DateTime getestimatedReturnDate();
		
	public DateTime getactualReturnDate();
	public void setactualReturnDate(DateTime actualReturnDate);
	
	public ArrayList<RentalRecord> getRentalRecord_raw();
	
	public DateTime getcompleteMaintainanceData();
	public void setcompleteMaintainanceData(DateTime MainCompDate);
		
	public void rent(String customerId, DateTime rentDate, int numOfRentDay) throws RentException;
	public void returnVehicle(DateTime returnDate) throws ReturnException;
	public void performMaintenance() throws MaintenanceException;
	public void completeMaintenance(DateTime MainCompDate) throws MaintenanceException;
	
		
	public void setRentalRecord(String vehicleId, String customerId, DateTime rentDate, 
			DateTime estimatedReturnDate, DateTime actualReturnDate, double rentFee, double lateFee);
	public String getRentalRecord();
}
