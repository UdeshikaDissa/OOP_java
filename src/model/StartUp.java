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

import java.sql.SQLException;

//This is a legacy class used in for Assignment 1 during console view menu
public class StartUp {

	public static void main(String[] args) throws RentException, InvalidIdException, ReturnException, MaintenanceException, ExceptionToMainMenu, SQLException{
	
		ThriftyRentSystem mainApp = new ThriftyRentSystem(); 
		mainApp.startApp();

	}

}
