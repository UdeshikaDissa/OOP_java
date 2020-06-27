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

import controller.MessageBox;
import util.DateTime;

public class Van extends AbstractVehicle {
	
	//declare and initialize constants for van objects
	final double DAILYRATE = 235.00;
	final double LATEFEE = 299.00;
	final int MAINTAINANCE_INT = 12;
	
	//Define Constructor calling super class
	public Van() {
		super();
	}
	
	//override toString(); this return the vehicle record for a van
	public String toString() {
		
		String temp_status = "Not Available";
		
        DateTime temp_date = new DateTime(0,0,0);
		DateTime DiffDays = new DateTime();
      
		if(this.getStatus() == true) {
			temp_status = "Available";
		}
		
		String temp_maintainaceDate;	
		
		if (DiffDays.diffDays(temp_date,this.getcompleteMaintainanceData()) == 0)
			temp_maintainaceDate = "none";
		else
			temp_maintainaceDate = String.valueOf(this.getcompleteMaintainanceData());
		
		return this.getId()+":"+this.getYear()+":"+this.getMake()+":"+this.getModel()+":"
				+ ""+this.getSeats()+":"+temp_status+":"+temp_maintainaceDate;
	}
	
	//method overridden for rent() for van objects
	public void rent(String customerId, DateTime rentDate, int numOfRentDay) throws RentException{
		super.rent(customerId, rentDate, numOfRentDay);
		
		//compare the actual retun date Vs estimated for rent and latefee calculations
		DateTime temp_day = new DateTime();
		int diff = temp_day.diffDays(this.getestimatedReturnDate(), this.getRentDate());
		
		//define int variable to check to maintainace requirement for a van
		int diff_for_maintain_check = temp_day.diffDays(this.getRentDate(), 
				this.getcompleteMaintainanceData());
		
		this.setCusID(customerId);
		
		String temp_nameofRentDate = rentDate.getNameOfDay();
					
		if( diff >= 1 ){	//checking the minimum booking duration of 1 day for a van
			MessageBox.display("Message","Rent Duration comply with min duration condition for a Van"); 
		}else {
			this.setStatus(true);
			throw new RentException("Rent Duration DO NOT comply with min_max duration ");
		}
	
	//checking for maintainance interval requirement for a van
	if( diff_for_maintain_check < MAINTAINANCE_INT ){	
		System.out.println("Maintainance operation requirement DO comply");
		}else {
			this.setStatus(true);
			throw new RentException("Maintainance operation requirement DO NOT comply..");
		}
		
	//setting up LateFee and RentalFee to zero (until the actual vehicle return happence.
	//Also write update the rental record
	this.setLateFee(0);	
	this.setRentalFee(0);
	this.setRentalRecord(this.getId(),customerId,rentDate,this.getestimatedReturnDate(), 
			this.getactualReturnDate(), this.getRentalFee(),this.getLateFee());
	MessageBox.display("Message","Rent Successfully");
	//System.out.println("\nPrinting Recent Rental Records...");
	//System.out.println(this.getRentalRecord()); //show rental record at command prompt
	return;	
	}

	//method overridden for returnVehicle() for van objects
public void returnVehicle(DateTime returnDate) throws ReturnException{
		
		super.returnVehicle(returnDate);
		
		DateTime temp_day = new DateTime();
		//compare the actual retun date Vs estimated for rent and latefee calculations
		int diff = temp_day.diffDays(this.getestimatedReturnDate(), this.getRentDate());
		int diff1 = temp_day.diffDays(this.getactualReturnDate(), this.getestimatedReturnDate());
		int diff2 = temp_day.diffDays(this.getactualReturnDate(), this.getRentDate());
		
		
		DateTime rentDate = new DateTime();
		rentDate = this.getRentDate();
		
		System.out.println("\nEstimated Rented Duration (Number of Days) - " + diff);
		System.out.println("Actual Rented Duration (Number of Days) - " + diff2);
		
		if(diff2<0) {
			this.setStatus(false);
			throw new ReturnException("Invalid Return Date");
		}
		
		//checking whether a late retun or not. accordingly setting up the Fees	
		if(diff1 <= 0) { 
			this.setLateFee(0);	
			MessageBox.display("Message","No Late Fee as the Vehicle returned on "  
					+ "or before due date");
			this.setRentalFee(DAILYRATE*diff2);
		}else {
			MessageBox.display("Message","Late Fee applies as the Vehicle returned after the due date");
			this.setLateFee(LATEFEE*diff1);
			this.setRentalFee(DAILYRATE*diff);
			MessageBox.display("Message","Late Duration - Number of Days " + diff1);
		}
			
		this.setRentalRecord(this.getId(),this.getCusID(),rentDate,this.getestimatedReturnDate(), 
				this.getactualReturnDate(), this.getRentalFee(),this.getLateFee());
	}
	
}
