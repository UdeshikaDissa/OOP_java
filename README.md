# OOP_java
A fully functional Rental Vehicle Management System using advanced OOP concepts in Java. Designed the GUI in JavaFX.


* --------------------------------------------------------------------
 * Developer Name 	: Udeshika Dissanayake
 * Subject		: COSC1295 Advanced Programming
 * Assignment		: Assignment 2 - Semester 1, 2019
 * Project NAme		: ThriftyRent Rental Vehicle Management System
 * Student Number	: s3400652
 * Date			: 01/06/2019 * 
 *--------------------------------------------------------------------


This project has been implemented using Java Standatrd Edition 8.0 (JDK1.8.0_141) and JavaFX to develop the GUI.

This Java Programme has four packages under src
	
	*) model - contains main inheritance classes, super and abstract classes for object handling, operational logic, custom exception classes, and database handling classes.
	*) controller - Contains GUI listners. These capture user inputs and return to the main GUI.
	*) view - this is the main GUI view of the programme.
	*) util - contains the DateTime classe to handle data and time inputs in the programme.


Additionally, the programme has below specific folders in the root directory:
	
	*) database - JDBC/HSQLDB database files are located here
	*) Images - Vehicle images in PNG format are saved here. 
        *) library - keeps the 'hsqldb' Jar file for database utility
	

### Running the Programme

The main() method is in MainView class under the view package. This class should 'Run' to start the programme.


### Data Generation and Persistance - 'dataBaseTools' Class

dataBaseTools class is responsible for managing the database aspect of this project.
It establishes connection to JDBC/HSQLDB database and read, write, update, and append tables accordingly.
Each time porgramme starts, the data is retrived from the database. Similary, each time the programme is closed, the data is written back to the database
Initial set of Vehicle and Rental data are generated from fillInitData() method. 





	
