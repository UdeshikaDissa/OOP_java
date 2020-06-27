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
import model.RentalRecord;
import util.DateTime;

public class AbstractVehicle implements Vehicle {
	
	// define instance variables that are common in both Van and Car
	private String id;
	private String CusID;
	private int YEAR;
	private String make;
	private String model;
	private int seats;
	private boolean status;
	
	private DateTime rentDate;
	private DateTime estimatedReturnDate;
	private DateTime actualReturnDate;
	private DateTime MainCompDate;
	
	private boolean OnMaintainance;
	
	private double rentFee;
	private double lateFee;
	
	//each vehicle keep rental record upto 10
	private ArrayList<RentalRecord> RR = new ArrayList<RentalRecord>(10); 
	
	public AbstractVehicle() {
		
	}
	
	//Define abstract methods for both Car and Van Objects
	@Override
	public String getId() {
		return id;
	}
	
	public void setID(String id) {
		this.id=id;
	}
	
	public String getCusID() {
		return CusID;
	}
	
	public void setCusID(String id) {
		this.CusID=id;
	}
			
	@Override
	public int getYear() {
		return YEAR;
	}
	
	public void setYear(int year) {
		this.YEAR=year;
	}
	
	//This method adds Rental Record to the Vehicle object
	public void setRentalRecord(String vehicleId, String customerId,DateTime rentDate, 
			DateTime estimatedReturnDate, 
			DateTime actualReturnDate, double rentFee, double lateFee) {
		this.RR.add(new RentalRecord(vehicleId, customerId, rentDate, estimatedReturnDate, 
				actualReturnDate, rentFee, lateFee));
	}
	
	public String getRentalRecord() {
			return this.RR.get(RR.size()-1).toString();
	}
	
	public ArrayList<RentalRecord> getRentalRecord_raw() {
		return this.RR;
	}

	@Override
	public String getMake() {
		return make;
	}
	
	public void setMake(String make) {
		this.make=make;
	}
	
	@Override
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model=model;
	}
	
	public boolean getMaintainanceStatus()
	{
		return OnMaintainance;
	}
	
	public void setcompleteMaintainanceData(DateTime MainCompDate) {
		this.MainCompDate=MainCompDate;
	}
	
	public DateTime getcompleteMaintainanceData()
	{
		return MainCompDate;
	}

	@Override
	public int getSeats(){
		return seats;
	}
	
	public void setSeats(int seats) {
		this.seats=seats;
	}

	@Override
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status=status;
	}
	
	// this return the vehicle record with : seperator
	public String toString() {
		return id+":"+rentDate+":"+estimatedReturnDate+":"+actualReturnDate+":"+
	rentFee+":"+lateFee;
	}
	
	public void setRentDate(DateTime rentDate) {
		this.rentDate=rentDate;
	}
	
	public void setestimatedReturnDate(DateTime returnDate, int duration) {
		this.estimatedReturnDate= new DateTime(returnDate,duration);
	}
	
	public DateTime getestimatedReturnDate() {
		return this.estimatedReturnDate;
	}
	
	public void setRentalFee(double rentFee) {
		this.rentFee=rentFee;
	}
	
	public void setLateFee(double lateFee){
		this.lateFee=lateFee;
	}
	
	public double getRentalFee() {
		return this.rentFee;
	}
	
	public double getLateFee() {
		return this.lateFee;
	}
	
	public void setactualReturnDate(DateTime actualReturnDate) {
		this.actualReturnDate=actualReturnDate;
	}
	
    public DateTime getactualReturnDate() {
		return this.actualReturnDate;
	}
   
    public DateTime getRentDate() {
		return this.rentDate;
	}
	
    //Abstract rent method. takes input and set required parameters in the programme
	public void rent(String customerId, DateTime rentDate, int numOfRentDay) throws RentException{
		this.setStatus(false);
		this.setRentDate(rentDate);
		this.setestimatedReturnDate(rentDate,numOfRentDay);
		DateTime temp_date = new DateTime(0,0,0);
		this.setactualReturnDate(temp_date); //reset the actual Return date
	}
	
	public void returnVehicle(DateTime returnDate) throws ReturnException{
		this.setStatus(true);
		this.setactualReturnDate(returnDate);
	}
	
	public void performMaintenance() throws MaintenanceException{
		this.OnMaintainance = true;
		this.setStatus(false);
	}
	
	public void completeMaintenance(DateTime MainCompDate) throws MaintenanceException{
		this.OnMaintainance = false;
		this.setStatus(true);
		this.setcompleteMaintainanceData(MainCompDate);
	}
}
	


