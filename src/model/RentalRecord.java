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

import util.DateTime;

//This Class sets the parameters for Rental Record and overrides toString method
public class RentalRecord {
	private String id;
	private DateTime rentDate;
	private DateTime estimatedReturnDate;
	private DateTime actualReturnDate;
	private double rentalFee;
	private double lateFee;
	
	public RentalRecord(String vehicleId, String customerId, 
				DateTime rentDate, DateTime estimatedReturnDate, 
				DateTime actualReturnDate, double rentalFee, double lateFee) {
		this.id = vehicleId +"_"+ customerId +"_"+ rentDate.getEightDigitDate();
		this.rentDate = rentDate;
		this.estimatedReturnDate = estimatedReturnDate;
		this.actualReturnDate = actualReturnDate;
		this.rentalFee = rentalFee;
		this.lateFee = lateFee;
		
	}

	//override toString(); this return the rental record for a vehicle
	public String toString() {
		
		String temp_rentalFee;
		String temp_lateFee;
		String temp_actualReturnDate;
		String temp_estimatedReturnDate;
		String temp_rentalDate;
		
		
		DateTime temp_date = new DateTime(0,0,0);
		DateTime DiffDays = new DateTime();
		
		//setting up 'none' attributes for when variables are not initialized
		if (rentalFee == 0)
			temp_rentalFee = "none";
		else
			temp_rentalFee = String.valueOf(rentalFee);
		
		if (lateFee == 0)
			temp_lateFee = "none";
		else
			temp_lateFee = String.valueOf(lateFee);
		
		if (DiffDays.diffDays(temp_date,actualReturnDate) == 0)
			temp_actualReturnDate = "none";
		else
			temp_actualReturnDate = String.valueOf(actualReturnDate);
		
		if (DiffDays.diffDays(temp_date,estimatedReturnDate) == 0)
			temp_estimatedReturnDate = "none";
		else
			temp_estimatedReturnDate = String.valueOf(estimatedReturnDate);
		
		if (DiffDays.diffDays(temp_date,rentDate) == 0)
			temp_rentalDate = "none";
		else
			temp_rentalDate = String.valueOf(rentDate);

		
		return id+":"+temp_rentalDate+":"+temp_estimatedReturnDate+":"+
		temp_actualReturnDate+":"+temp_rentalFee+":"+temp_lateFee;
		
	}
	
	
	
}
