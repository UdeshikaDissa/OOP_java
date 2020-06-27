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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//This Class is defined for JDBC database work
public class dataBaseTools{
   
   // name the Database and table names	
   final String DB_NAME = "ThriftyDB";
   final String TABLE_NAME1 = "VEHICLES";
   final String TABLE_NAME2 = "RENTAL_RECORDS";
   
   //Method define to establish the Connection of the JDBC database.
   public void ConnectionTest() {	
    	 
   	 try (Connection con = getConnection(DB_NAME)) {
   		  System.out.println("Connection to database "+ DB_NAME + " created successfully");
   		  con.close();
   	 } catch (Exception e) {
   		 System.out.println(e.getMessage());
   	 }
   }
    
    
   //get the connection of DriveManager of jdbc using standard U/N and P/W
    public static Connection getConnection(String dbName)
   				 throws SQLException, ClassNotFoundException {
   	 //Registering the HSQLDB JDBC driver
   	 Class.forName("org.hsqldb.jdbc.JDBCDriver");
   		 
   	 /* Database files will be created in the "database"
   	  * folder in the project. If no username or password is
   	  * specified, the default SA user and an empty password are used */
   	 Connection con = DriverManager.getConnection
   			 ("jdbc:hsqldb:file:database/" + dbName, "SA", "");
   	 //con.close();
   	 return con;
    }
    
    
	 //Method define to create Vehicle and Rental Record Tables
	 public void CreateTable() throws SQLException {
    	try (Connection con = getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		)
    	
    	{
			int result = stmt.executeUpdate("CREATE TABLE VEHICLES ("
					+ "vehicle_id VARCHAR(8) NOT NULL," 
					+ "year INT NOT NULL,"
					+ "make VARCHAR(20) NOT NULL,"
					+ "model VARCHAR(20) NOT NULL,"
					+ "no_of_seat INT NOT NULL,"
					+ "status VARCHAR(20) NOT NULL,"
					+ "mcomplete VARCHAR(20) NOT NULL,"
					+ "PRIMARY KEY (vehicle_id))");
			if(result == 0) {
				System.out.println("Table " + TABLE_NAME1 + " has been created successfully");
				stmt.close();
				con.close();
			} else {
				System.out.println("Table " + TABLE_NAME1 + " is not created");
				stmt.close();
				con.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
    	
    	
    	try (Connection con1 = getConnection(DB_NAME);
				Statement stmt1 = con1.createStatement();
		)
    	
    	{
			int result = stmt1.executeUpdate("CREATE TABLE RENTAL_RECORDS ("
					+ "record_id VARCHAR(40) NOT NULL," 
					+ "rent_date VARCHAR(20) NOT NULL,"
					+ "estimate_date VARCHAR(20) NOT NULL,"
					+ "actual_date VARCHAR(20) NOT NULL,"
					+ "rent_fee DOUBLE NOT NULL,"
					+ "late_fee DOUBLE NOT NULL,"
					+ "PRIMARY KEY (record_id))");
			if(result == 0) {
				System.out.println("Table " + TABLE_NAME2 + " has been created successfully");
				stmt1.close();
				con1.close();
			} else {
				System.out.println("Table " + TABLE_NAME2 + " is not created");
				stmt1.close();
				con1.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
   }
    
	 
	 //Take Vehicle ID, Year, Make, Model, Seat, Status, and MAintenance Complete data as
	 //inputs write a new row into Vehicle table
     public void InsertVehicleRow(String vehiId, String year, String make, String model, 
    		String seat, String status, String mcomplete) {
    	
    	try (Connection con1 = getConnection(DB_NAME);
				Statement stmt = con1.createStatement();
    	
		) {
			String query = "INSERT INTO " + TABLE_NAME1 + 
					" VALUES ('"+vehiId+"', '"+year+"', '"+make+"', '"+model+"', '"+seat+"', '"+status+"', '"+mcomplete+"')";
				
			int result = stmt.executeUpdate(query);
			con1.commit();
			//System.out.println("Insert into table " + TABLE_NAME1 + " executed successfully");
			//System.out.println(result + " row(s) affected");
			stmt.close();
			con1.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	
    }
    
     
     //This method is used to fill the Vehicle and Rental records in the first instance
     //when the program runs for the first time without any database records.
     public void fillInitData() {
    	
    	try (Connection con1 = getConnection(DB_NAME);
				Statement stmt1 = con1.createStatement();
    	
		) {
			String query = "INSERT INTO " + TABLE_NAME1 + 
					" VALUES ('C_AAA111', 2001, 'Mazda', 'Six', 4, 'Available', 'none'),"
					+ "('C_BBB222', 2002, 'Toyota', 'Corolla', 4, 'Available', 'none'),"
					+ "('C_CCC333', 2003, 'Honda', 'Civic', 4, 'Available', 'none'),"
					+ "('C_DDD444', 2004, 'Kia', 'Sorento', 4, 'Available', 'none'),"
			        + "('C_EEE555', 2005, 'BMW', 'M3', 4, 'Available', 'none'),"
			        + "('C_FFF666', 2015, 'Audi', 'A5', 4, 'Available', 'none'),"
			        + "('C_GGG777', 2016, 'BMW', 'X5', 7, 'Available', 'none'),"
			        + "('C_HHH888', 2017, 'Nissan', 'Magna', 4, 'Available', 'none'),"
			        + "('C_III999', 2018, 'Hyndai', 'i5', 4, 'Available', 'none'),"
			        + "('C_JJJ000', 2019, 'Toyota', 'Prado', 7, 'Available', 'none'),"
			        + "('V_ABC123', 2006, 'Toyota', 'Hiace', 15, 'Available', '05/05/2019'),"
			        + "('V_DEF456', 2007, 'Nissan', 'Caravan', 15, 'Available', 'none'),"
			        + "('V_GHI789', 2008, 'Merc', 'Sprinter', 15, 'Available', '05/05/2019'),"
			        + "('V_JKL012', 2009, 'Toyota', 'Hiace', 15, 'Available', '05/05/2019'),"
			        + "('V_MNO345', 2010, 'VW', 'Commuter', 15, 'Available', 'none')";
			    
	        int result = stmt1.executeUpdate(query);
	       	        
	        con1.commit();
			stmt1.close();
			con1.close();
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        	
       	try (Connection con2 = getConnection(DB_NAME);
    				Statement stmt2 = con2.createStatement();
        	
    		) {
    			String query1 = "INSERT INTO " + TABLE_NAME2 + 
    					" VALUES ('C_AAA111_C001_05052019', '05/05/2019', '10/05/2019', '20/05/2019', 390, 975),"
    					+ "('C_AAA111_C002_10052019', '10/05/2019', '20/05/2019', '15/05/2019', 390, 0),"
    					+ "('C_BBB222_C003_06062019', '06/06/2019', '10/06/2019', '10/06/2019', 390, 0),"
    					+ "('C_BBB222_C004_10062019', '10/06/2019', '20/06/2019', '15/06/2019', 480, 0),"
    					+ "('C_CCC333_C001_05052019', '05/05/2019', '15/05/2019', '20/05/2019', 480, 975),"
    					+ "('C_CCC333_C002_05052019', '05/05/2019', '15/05/2019', '20/05/2019', 480, 975),"
    					+ "('C_DDD444_C001_05052019', '05/05/2019', '15/05/2019', '20/05/2019', 480, 975),"
    					+ "('C_DDD444_C004_10062019', '10/06/2019', '20/06/2019', '15/06/2019', 480, 0),"
    					+ "('C_EEE555_C004_10062019', '10/06/2019', '20/06/2019', '15/06/2019', 480, 0),"
    					+ "('C_EEE555_C001_05052019', '05/05/2019', '15/05/2019', '20/05/2019', 480, 975),"
    					+ "('C_FFF666_C001_05052019', '05/05/2019', '15/05/2019', '20/05/2019', 480, 975),"
    					+ "('C_FFF666_C002_05052019', '05/05/2019', '15/05/2019', '20/05/2019', 480, 975),"
    					+ "('C_GGG777_C002_10052019', '10/05/2019', '20/05/2019', '15/05/2019', 390, 0),"
    					+ "('C_GGG777_C003_06062019', '06/06/2019', '10/06/2019', '10/06/2019', 390, 0),"
    					+ "('C_HHH888_C004_10062019', '10/06/2019', '20/06/2019', '15/06/2019', 480, 0),"
    					+ "('C_HHH888_C001_05052019', '05/05/2019', '15/05/2019', '20/05/2019', 480, 975),"
    					+ "('C_III999_C004_10062019', '10/06/2019', '20/06/2019', '15/06/2019', 480, 0),"
    					+ "('C_III999_C005_10062019', '10/06/2019', '20/06/2019', '15/06/2019', 480, 0),"
    					+ "('C_JJJ000_C002_05052019', '05/05/2019', '15/05/2019', '20/05/2019', 480, 975),"
    					+ "('C_JJJ000_C001_05052019', '05/05/2019', '15/05/2019', '20/05/2019', 480, 975),"
    					+ "('V_ABC123_C004_10062019', '10/06/2019', '20/06/2019', '15/06/2019', 480, 0),"
    					+ "('V_ABC123_C001_05052019', '05/05/2019', '15/05/2019', '20/05/2019', 480, 975),"
    					+ "('V_DEF456_C001_05052019', '05/05/2019', '15/05/2019', '20/05/2019', 480, 975),"
    					+ "('V_DEF456_C004_10062019', '10/06/2019', '20/06/2019', '15/06/2019', 480, 0),"
    					+ "('V_GHI789_C001_05052019', '05/05/2019', '15/05/2019', '20/05/2019', 480, 975),"
    					+ "('V_GHI789_C002_05052019', '05/05/2019', '15/05/2019', '20/05/2019', 480, 975),"
    					+ "('V_JKL012_C004_10062019', '10/06/2019', '20/06/2019', '15/06/2019', 480, 0),"
    					+ "('V_JKL012_C001_05052019', '05/05/2019', '15/05/2019', '20/05/2019', 480, 975),"
    					+ "('V_MNO345_C002_05052019', '05/05/2019', '15/05/2019', '20/05/2019', 480, 975),"
    					+ "('V_MNO345_C001_05052019', '05/05/2019', '15/05/2019', '20/05/2019', 480, 975)";
    					
    					    			
    			int result = stmt2.executeUpdate(query1);
    			con2.commit();
    			stmt2.close();
    			con2.close();

    		} catch (Exception e) {
    			System.out.println(e.getMessage());
    		}
    	
    }
   
    
     //This method shows all records in the Vehicle table if required
    public void SelectQuery() {
    	
    	try (Connection con2 = getConnection(DB_NAME);
				Statement stmt = con2.createStatement();
		) {
			String query = "SELECT * FROM " + TABLE_NAME1;
			
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while(resultSet.next()) {
					System.out.printf("Vehicle ID: %s | Year: %d | Make: %s | Model: %s | No of Seats: %d | Status: %s | Maint. Complete: %s\n",
							resultSet.getString("vehicle_id"), resultSet.getInt("year"), 
							resultSet.getString("make"), resultSet.getString("model"), resultSet.getInt("no_of_seat"),resultSet.getString("status"),resultSet.getString("mcomplete"));
					
					
					stmt.close();
					con2.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				stmt.close();
				con2.close();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
     }

    
    //This method returns all Vehicle records in the vehicle table.
    public String[][] readvehicleTableToArray() {
    	
    	//String Array is defined to hold Vehicle records.
    	//size of the table defined by vehicleDataSize
    	String[][] vehicleData = new String[ThriftyRentSystem.vehicleDataSize][7]; 
    	
    	int i = 0;
    	
    	try (Connection con2 = getConnection(DB_NAME);
				Statement stmt = con2.createStatement();
		) {
			String query = "SELECT * FROM " + TABLE_NAME1;
			
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while(resultSet.next()) {
					//System.out.printf("Vehicle ID: %s | Year: %d | Make: %s | Model: %s | No of Seats: %d | Status: %s\n",
					vehicleData[i][0] =  resultSet.getString("vehicle_id");
					vehicleData[i][1] =  resultSet.getString("year");
					vehicleData[i][2] =  resultSet.getString("make");
					vehicleData[i][3] =  resultSet.getString("model");
					vehicleData[i][4] =  resultSet.getString("no_of_seat");
					vehicleData[i][5] =  resultSet.getString("status");
					vehicleData[i][6] =  resultSet.getString("mcomplete");
											
					i++;
					stmt.close();
					con2.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				stmt.close();
				con2.close();
				return vehicleData;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return vehicleData;
			
		}
		return vehicleData;
    	
    }

   //This method return String 2D Array of all the Rental Records in the database 
   public String[][] readrecordTableToArray() {
    	
    	String[][] recordData = new String[ThriftyRentSystem.recordDataSize][6];
    	
    	int i = 0;
    	
    	try (Connection con2 = getConnection(DB_NAME);
				Statement stmt = con2.createStatement();
		) {
			String query = "SELECT * FROM " + TABLE_NAME2;
			
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while(resultSet.next()) {
					//System.out.printf("Vehicle ID: %s | Year: %d | Make: %s | Model: %s | No of Seats: %d | Status: %s\n",
					recordData[i][0] =  resultSet.getString("record_id");
					recordData[i][1] =  resultSet.getString("rent_date");
					recordData[i][2] =  resultSet.getString("estimate_date");
					recordData[i][3] =  resultSet.getString("actual_date");
					recordData[i][4] =  resultSet.getString("rent_fee");
					recordData[i][5] =  resultSet.getString("late_fee");
																
					i++;
					stmt.close();
					con2.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				stmt.close();
				con2.close();
				return recordData;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return recordData;
			
		}
		return recordData;
    	
    }


  //This method modifys existng Vehicle Records with new data
  public void modifyVehicleRow(String vehiId, String status, String mcomplete) {

		//use try-with-resources Statement
		try (Connection con = getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String query = "UPDATE " + TABLE_NAME1 +
					" SET status = '"+status+"', mcomplete = '"+mcomplete+"'" +
					" WHERE vehicle_id LIKE '"+vehiId+"'";
			
			int result = stmt.executeUpdate(query);
			
			System.out.println("Update table " + TABLE_NAME1 + " executed successfully");
			System.out.println(result + " row(s) affected");
			
			stmt.close();
			con.close();


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

  
  //This method inserts new row of REntal record table
  public void InsertRecordRow(String recId, String rentDate, String estReturnDate, String actualReturnDate, 
		String rentFee, String lateFee) {
	
	try (Connection con1 = getConnection(DB_NAME);
			Statement stmt = con1.createStatement();
	
	) {
		String query = "INSERT INTO " + TABLE_NAME2 + 
				" VALUES ('"+recId+"', '"+rentDate+"', '"+estReturnDate+"', '"+actualReturnDate+"', '"+rentFee+"', '"+lateFee+"')";
			
		int result = stmt.executeUpdate(query);
		
		con1.commit();
		stmt.close();
		con1.close();

	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
	
}

  //This method modifys the existing record of REntal record table
  public void modifyRecordRow(String recId, String actualReturnData, 
		String rentFee, String lateFee) {

	  //use try-with-resources Statement
	try (Connection con = getConnection(DB_NAME);
			Statement stmt = con.createStatement();
	) {
		String query = "UPDATE " + TABLE_NAME2 +
				" SET late_fee = '"+lateFee+"', rent_fee = '"+rentFee+"', actual_date = '"+actualReturnData+"'" +
				" WHERE record_id LIKE '"+recId+"'";
		
		int result = stmt.executeUpdate(query);
		
		stmt.close();
		con.close();


	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
}

}