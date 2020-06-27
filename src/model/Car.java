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

public class Car extends AbstractVehicle{
	
	//declare and initialize constants for car objects
	final double DAILYRATE_4Seat = 78.00;
	final double DAILYRATE_7Seat = 113.00;
	final double LATERATE = 1.25;
	
	//Define Constructor calling super class
	public Car() {
		super();
	}
	
	//override toString(); this return the vehicle record for a car
	public String toString() {
		
		String temp_status = "Not Available";
		
		if(this.getStatus() == true) {
			temp_status = "Available";
		}
		
		return this.getId()+":"+this.getYear()+":"+this.getMake()+":"+
		this.getModel()+":"+this.getSeats()+":"+temp_status;
	}


	//method overridden for rent() for car objects
	public void rent(String customerId, DateTime rentDate, int numOfRentDay) throws RentException{
		super.rent(customerId, rentDate, numOfRentDay);
	
		//compare the actual retun date Vs estimated for rent and latefee calculations
		DateTime temp_day = new DateTime();
		int diff = temp_day.diffDays(this.getestimatedReturnDate(), this.getRentDate());
	
		this.setCusID(customerId);
		
		String temp_nameofRentDate = rentDate.getNameOfDay();
				
		//checking the minimum and Max booking duration for given rent start day	
		if((temp_nameofRentDate.equals("Friday") || temp_nameofRentDate.equals("Saturday")) 
				&& (diff >= 3 && diff < 15)){	
				MessageBox.display("Message","Rent Duration comply with min_max "
						+ " \n" +" duration condition for Friday OR Saturday");
		}else if ((temp_nameofRentDate.equals("Sunday") || temp_nameofRentDate.equals("Monday") || 
				temp_nameofRentDate.equals("Tuesday") || temp_nameofRentDate.equals("Wednesday") ||
				temp_nameofRentDate.equals("Thursday")) && (diff >= 2 && diff < 15)) {
			MessageBox.display("Message","Rent Duration comply with min_max duration condition for"
					+ " \n" +" start day between Sunday and Thursday");
		}else {
			this.setStatus(true);
			throw new RentException("Max Duration = 15days, Min Duration = 2days (Friday OR Saturday Start = 3days Min)");
		}
	
	//setting up LateFee and RentalFee to zero (until the actual vehicle return happence. 
	//Also write update the rental record
	this.setStatus(false);
	this.setLateFee(0);	
	this.setRentalFee(0);
	this.setRentalRecord(this.getId(),customerId,rentDate,this.getestimatedReturnDate(), 
			this.getactualReturnDate(), this.getRentalFee(),this.getLateFee());
	MessageBox.display("Message","Rent Successfully");
	return;
}
	
	//method overridden for returnVehicle() for car objects
	public void returnVehicle(DateTime returnDate) throws ReturnException{
		
		super.returnVehicle(returnDate);
		
		//System.out.println("\nestimated Return date - " + this.getestimatedReturnDate());
		
		DateTime temp_day = new DateTime();
		//compare the actual retun date Vs estimated for rent and latefee calculations
		int diff = temp_day.diffDays(this.getestimatedReturnDate(), this.getRentDate());
		int diff1 = temp_day.diffDays(this.getactualReturnDate(), 
				this.getestimatedReturnDate());
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
									
			if(this.getSeats() == 4) {
				this.setRentalFee(DAILYRATE_4Seat*diff2);
			}else if(this.getSeats() == 7) {
				this.setRentalFee(DAILYRATE_7Seat*diff2);
					}
			else {
				this.setStatus(true);
				throw new ReturnException("Car should be either 4 or 7 seats...");
			}
				
		}else {
			
			MessageBox.display("Message","Late Fee applies as the Vehicle returned after the due date");
			if(this.getSeats() == 4) {
				this.setLateFee(1.25*DAILYRATE_4Seat*diff1);
				this.setRentalFee(DAILYRATE_4Seat*diff);
			}else if(this.getSeats() == 7) {
				this.setLateFee(1.25*DAILYRATE_7Seat*diff1);
				this.setRentalFee(DAILYRATE_7Seat*diff);
			}
			
			MessageBox.display("Message","Late Duration - Number of Days " + diff1);

		}

		
		this.setRentalRecord(this.getId(),this.getCusID(),rentDate,
				this.getestimatedReturnDate(), this.getactualReturnDate(), 
				this.getRentalFee(),this.getLateFee());

		
	}
	
}
