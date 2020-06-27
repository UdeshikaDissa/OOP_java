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

//Custom Exception Class to capture Exception during recording for maintenance
public class MaintenanceException extends Exception{

	public MaintenanceException(String errMsg) {
		super(errMsg);
		MessageBox.display("Error", errMsg);
	}
	
}
