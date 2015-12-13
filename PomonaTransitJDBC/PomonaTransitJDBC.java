/**Name: Alexis Mendez
 * Date: 12/1/12
 * Class: CS435 - Salloum
 * Description: Lab 4, a Java database (JDBC) implementation of a city transit system
 */

import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.*;

public class PomonaTransitJDBC {
	
	static Scanner keyboard;
	static Statement stmt;
	static Connection conn;
	static ResultSet rs;
	
	//---------Menu Choice 1-------------
	public static void displayDayTrips() throws SQLException {
		//query parameters
		String startLocation, endLocation, date;
		stmt = conn.createStatement();
		//retrieve input from user
		System.out.println("\n---Display a day's Trips---");
		System.out.print("Start Location: ");
		startLocation = keyboard.next();
		System.out.print("End Location: ");
		endLocation = keyboard.next();
		System.out.print("Date: ");
		date = keyboard.next();
		//construct query string
		String query = "SELECT t.StartLocationName, t.DestinationName, o.Date," +
				" o.ScheduledStartTime, o.ScheduledArrivalTime, o.DriverName, o.BusID" +
				" FROM Trip t, TripOffering o" +
				" WHERE t.TripNumber=o.TripNumber" +
				" AND o.Date='" + date + "'" +
				" AND t.StartLocationName='" + startLocation + "'" +
				" AND t.DestinationName='" + endLocation + "'";
		//execute query
		try {
			rs = stmt.executeQuery(query);
			System.out.println("\nResults:");
			System.out.println("---------------------------------");
			while (rs.next()) {
				System.out.println(rs.getString("StartLocationName") + " " +
	               rs.getString("DestinationName") + " " +
	               rs.getString("Date") + " " +
	               rs.getString("ScheduledStartTime") + " " +
	               rs.getString("ScheduledArrivalTime") + " " +
	               rs.getString("DriverName") + " " +
	               rs.getString("BusID"));
			}
		}
		catch (SQLException sql) {
			System.out.println("\nQuery Failed");
		}
	}
	//---------Menu Choice 2-------------
	public static void deleteTripOffering() throws SQLException {
		//query parameters
		String tripNumber, date, scheduledStartTime;
		stmt = conn.createStatement();
		//retrieve input from user
		System.out.println("\n---Delete a Trip Offering---");
		System.out.print("Trip Number: ");
		tripNumber = keyboard.next();
		System.out.print("Date: ");
		date = keyboard.next();
		System.out.print("Scheduled Start Time: ");
		scheduledStartTime = keyboard.next();
		//construct query string
		String query = "DELETE FROM TripOffering" +
				" WHERE TripNumber=" + tripNumber +
				" AND Date='" + date + "'" +
				" AND ScheduledStartTime='" + scheduledStartTime + "'";
		//execute query
		try {
			stmt.execute(query);
			System.out.println("\nDelete Successful");
		}
		catch (SQLException sql) {
			System.out.println("\nDelete Failed");
		}
	}
	//---------Menu Choice 3-------------
	public static void addTripOffering() throws SQLException {
		//query parameters
		String tripNumber, date, scheduledStartTime, scheduledArrivalTime, driverName, busID;
		stmt = conn.createStatement();
		//retrieve input from user
		System.out.println("\n---Add a Trip Offering---");
		System.out.print("Trip Number: ");
		tripNumber = keyboard.next();
		System.out.print("Date: ");
		date = keyboard.next();
		System.out.print("Scheduled Start Time: ");
		scheduledStartTime = keyboard.next();
		System.out.print("Scheduled Arrival Time: ");
		scheduledArrivalTime = keyboard.next();
		System.out.print("Driver Name: ");
		driverName = keyboard.next();
		System.out.print("Bus ID Number: ");
		busID = keyboard.next();
		//construct query string
		String query = "INSERT INTO TripOffering" +
				" VALUES (" + tripNumber +
				", '" + date + "'" +
				", '" + scheduledStartTime + "'" +
				", '" + scheduledArrivalTime + "'" +
				", '" + driverName + "'" +
				", " + busID + ")";
		//execute query
		try {
			stmt.execute(query);
			System.out.println("\nAdd Successful");
		}
		catch (SQLException sql) {
			System.out.println("\nAdd Failed");
		}
	}
	//---------Menu Choice 4-------------
	public static void changeDriver() throws SQLException {
		//query parameters
		String tripNumber, date, scheduledStartTime, newDriver;
		stmt = conn.createStatement();
		//retrieve input from user
		System.out.println("\n---Change Driver---");
		System.out.print("Trip Number: ");
		tripNumber = keyboard.next();
		System.out.print("Date: ");
		date = keyboard.next();
		System.out.print("Scheduled Start Time: ");
		scheduledStartTime = keyboard.next();
		System.out.print("New Driver Name: ");
		newDriver = keyboard.next();
		//construct query string
		String query = "UPDATE TripOffering" +
				" SET DriverName='" + newDriver + "'" +
				" WHERE TripNumber=" + tripNumber +
				" AND Date='" + date + "'" +
				" AND ScheduledStartTime='" + scheduledStartTime + "'";
		//execute query
		try {
			stmt.execute(query);
			System.out.println("\nUpdate Successful");
		}
		catch (SQLException sql) {
			System.out.println("\nUpdate Failed");
		}
	}
	//---------Menu Choice 5-------------
	public static void changeBus() throws SQLException {
		String tripNumber, date, scheduledStartTime, newBus;
		stmt = conn.createStatement();
		//retrieve input from user
		System.out.println("\n---Change Bus---");
		System.out.print("Trip Number: ");
		tripNumber = keyboard.next();
		System.out.print("Date: ");
		date = keyboard.next();
		System.out.print("Scheduled Start Time: ");
		scheduledStartTime = keyboard.next();
		System.out.print("New Bus ID: ");
		newBus = keyboard.next();
		//construct query string
		String query = "UPDATE TripOffering" +
				" SET BusID=" + newBus +
				" WHERE TripNumber=" + tripNumber +
				" AND Date='" + date + "'" +
				" AND ScheduledStartTime='" + scheduledStartTime + "'";
		//execute query
		try {
			stmt.execute(query);
			System.out.println("\nUpdate Successful");
		}
		catch (SQLException sql) {
			System.out.println("\nUpdate Failed");
		}
	}
	//---------Menu Choice 6-------------
	public static void displayStops() throws SQLException {
		//query parameters
		String tripNumber, date, scheduledStartTime;
		stmt = conn.createStatement();
		//retrieve input from user
		System.out.println("\n---Display Stops---");
		System.out.print("Trip Number: ");
		tripNumber = keyboard.next();
		System.out.print("Date: ");
		date = keyboard.next();
		System.out.print("Scheduled Start Time: ");
		scheduledStartTime = keyboard.next();
		//construct query string
		String query = "SELECT i.TripNumber, i.StopNumber, s.StopAddress, i.SequenceNumber, i.DrivingTime" +
				" FROM TripOffering o, TripStopInfo i, Stop s" +
				" WHERE i.TripNumber=o.TripNumber" +
				" AND s.StopNumber=i.StopNumber" +
				" AND o.TripNumber=" + tripNumber +
				" AND o.Date='" + date + "'" +
				" AND o.ScheduledStartTime='" + scheduledStartTime + "'";
		//execute query
		try {
			rs = stmt.executeQuery(query);
			System.out.println("\nResults:");
			System.out.println("---------------------------------");
			while (rs.next()) {
				System.out.println(rs.getString("TripNumber") + " " +
	               rs.getString("StopNumber") + " " +
	               rs.getString("StopAddress") + " " +
	               rs.getString("SequenceNumber") + " " +
	               rs.getString("DrivingTime"));
			}
		}
		catch (SQLException sql) {
			System.out.println("\nQuery Failed");
		}
	}
	//---------Menu Choice 7-------------
	public static void displayDriverSchedule() throws SQLException {
		//query parameters
		String driverName, date;
		stmt = conn.createStatement();
		//retrieve input from user
		System.out.println("\n---Display Driver's Schedule---");
		System.out.print("Driver Name: ");
		driverName = keyboard.next();
		System.out.print("Date: ");
		date = keyboard.next();
		//construct query string
		String query = "SELECT o.TripNumber, o.Date, o.ScheduledStartTime, o.ScheduledArrivalTime" +
				" FROM TripOffering o, Driver d" +
				" WHERE o.DriverName=d.DriverName" +
				" AND d.DriverName='" + driverName + "'" +
				" AND o.Date<DATEADD(week, 1, '" + date + "')" +
				" AND o.Date>='" + date + "'";
		//execute query
		try {
			rs = stmt.executeQuery(query);
			System.out.println("\nResults:");
			System.out.println("---------------------------------");
			while (rs.next()) {
				System.out.println(rs.getString("TripNumber") + " " +
	               rs.getString("Date") + " " +
	               rs.getString("ScheduledStartTime") + " " +
	               rs.getString("ScheduledArrivalTime"));
			}
		}
		catch (SQLException sql) {
			System.out.println("\nQuery Failed");
		}
	}
	//---------Menu Choice 8-------------
	public static void addDriver() throws SQLException {
		//query parameters
		String driverName, driverNum;
		stmt = conn.createStatement();
		//retrieve input from user
		System.out.println("\n---Add Driver---");
		System.out.print("Driver Name: ");
		driverName = keyboard.next();
		System.out.print("Telephone Number: ");
		driverNum = keyboard.next();
		//construct query string
		String query = "INSERT INTO Driver" +
				" VALUES ('" + driverName + "'" +
				", '" + driverNum + "')";
		//execute query
		try {
			stmt.execute(query);
			System.out.println("\nAdd Successful");
		}
		catch (SQLException sql) {
			System.out.println("\nAdd Failed");
		}
	}
	//---------Menu Choice 9-------------
	public static void addBus() throws SQLException {
		//query parameters
		String busID, model, year;
		stmt = conn.createStatement();
		//retrieve input from user
		System.out.println("\n---Add Bus---");
		System.out.print("BusID: ");
		busID = keyboard.next();
		System.out.print("Model: ");
		model = keyboard.next();
		System.out.print("Year: ");
		year = keyboard.next();
		//construct query string
		String query = "INSERT INTO Bus" +
				" VALUES (" + busID +
				", '" + model + "'" +
				", " + year + ")";
		//execute query
		try {
			stmt.execute(query);
			System.out.println("\nAdd Successful");
		}
		catch (SQLException sql) {
			System.out.println("\nAdd Failed");
		}
	}
	//---------Menu Choice 10------------
	public static void deleteBus() throws SQLException {
		//query parameters
		String busID;
		stmt = conn.createStatement();
		//retrieve input from user
		System.out.println("\n---Delete Bus---");
		System.out.print("Bus ID: ");
		busID = keyboard.next();
		//construct query string
		String query = "DELETE FROM Bus" +
				" WHERE BusID=" + busID;
		//execute query
		try {
			stmt.execute(query);
			System.out.println("\nDelete Successful");
		}
		catch (SQLException sql) {
			System.out.println("\nDelete Failed");
		}
	}
	//---------Menu Choice 11------------
	public static void recordStopInfo() throws SQLException {
		//query parameters
		String tripNumber, date, scheduledStartTime,
		stopNumber, scheduledArrivalTime, actualStartTime,
		actualArrivalTime, numberOfPassengersIn, numberOfPassengersOut;
		stmt = conn.createStatement();
		//retrieve input from user
		System.out.println("\n---Record Stop Info---");
		System.out.print("Trip Number: ");
		tripNumber = keyboard.next();
		System.out.print("Date: ");
		date = keyboard.next();
		System.out.print("Scheduled Start Time: ");
		scheduledStartTime = keyboard.next();
		System.out.print("Stop Number: ");
		stopNumber = keyboard.next();
		System.out.print("Scheduled Arrival Time: ");
		scheduledArrivalTime = keyboard.next();
		System.out.print("Actual Start Time: ");
		actualStartTime = keyboard.next();
		System.out.print("Actual Arrival Time: ");
		actualArrivalTime = keyboard.next();
		System.out.print("Number of Passengers In: ");
		numberOfPassengersIn = keyboard.next();
		System.out.print("Number of Passengers Out: ");
		numberOfPassengersOut = keyboard.next();
		//construct query string
		String query = "INSERT INTO ActualStopInfo" +
				" VALUES (" + tripNumber +
				", '" + date + "'" +
				", '" + scheduledStartTime + "'" +
				", " + stopNumber +
				", '" + scheduledArrivalTime + "'" +
				", '" + actualStartTime + "'" +
				", '" + actualArrivalTime + "'" +
				", " + numberOfPassengersIn +
				", " + numberOfPassengersOut + ")";
		//execute query
		try {
			stmt.execute(query);
			System.out.println("\nAdd Successful");
		}
		catch (SQLException sql) {
			System.out.println("\nAdd Failed");
		}
	}
	//------------Main Menu------------
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		int choice = 0;
		keyboard = new Scanner(System.in);

		//load SQL Server 2008 JDBC driver and instantiate connection
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection("jdbc:sqlserver://MASTER-PC\\SQLEXPRESS\\SQLEXPRESS:1433;databaseName=CS 435 Lab 4;integratedSecurity=true;");
		//main menu display
		System.out.println("Welcome to the Pomona Transit Management System");
		do {
			System.out.println("\nMain Menu:");
			System.out.println("---------------------------------");
			System.out.println("1: Display a day's Trips");
			System.out.println("2: Delete a Trip Offering");
			System.out.println("3: Add a Trip Offering");
			System.out.println("4: Change a Trip Driver");
			System.out.println("5: Change a Trip Bus");
			System.out.println("6: Display a Trip's Stops");
			System.out.println("7: Display a Driver's Weekly Schedule");
			System.out.println("8: Add a Driver to the System");
			System.out.println("9: Add a Bus to the System");
			System.out.println("10: Delete a Bus from the System");
			System.out.println("11: Record Actual Trip Info");
			System.out.println("12: Quit");
			System.out.println("---------------------------------\n");
			System.out.print("Your selection: ");
			//retrieve menu selection
			try {
				choice = keyboard.nextInt();
			}
			catch (InputMismatchException IME) {
				choice = 0;
				keyboard.next();
			}
			//execute menu selection, exit if selected
			switch (choice) {
				case 1:
					displayDayTrips();
					choice = 0;
					break;
				case 2:
					deleteTripOffering();
					choice = 0;
					break;
				case 3:
					addTripOffering();
					choice = 0;
					break;
				case 4:
					changeDriver();
					choice = 0;
					break;
				case 5:
					changeBus();
					choice = 0;
					break;
				case 6:
					displayStops();
					choice = 0;
					break;
				case 7:
					displayDriverSchedule();
					choice = 0;
					break;
				case 8:
					addDriver();
					choice = 0;
					break;
				case 9:
					addBus();
					choice = 0;
					break;
				case 10:
					deleteBus();
					choice = 0;
					break;
				case 11:
					recordStopInfo();
					choice = 0;
					break;
				case 12:
					try {
						conn.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
					}
					System.out.println("Goodbye!");
					break;
				default:
					System.out.println("Invalid Selection");
					break;
			}
		} while (choice != 12);
	}
}
