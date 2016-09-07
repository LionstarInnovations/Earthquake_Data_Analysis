package comp1551.cwk4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;
import java.util.Arrays;


/**
* A Quake class containing method calculations for magnitude, depth, latitude, longitude.
*
* <p>The depth, latitude, longitude is represented in double numbers</p>
* 
* The class contains a constructor that splits a string into an array, and converts the string by parsing to
* The required data type for an array element.
*
* @author Marcus McFarlane
*/
public class Quake {
	
	// Private fields representing for a single earthquake.
	// Private field called radius.
	private String record;
	private double magnitude, depth, latitude, longitude;
	
	
	// Constructor initialising the fields.
	/** 
	* Creates an Earthquake object.
	*
	*/
	public Quake(String rec) {
		
		record = rec;
		
		/* Using array split to split string into array elements
		according to the comma delimiter of the string. This is
		required for the toString format only!*/
		
		String[] array = record.split(",");
		
		String time = array[0];
		latitude = Double.parseDouble(array[1]);
		longitude = Double.parseDouble(array[2]);
		depth = Double.parseDouble(array[3]);
		magnitude = Double.parseDouble(array[4]);
				
	}
	
	// Getters which return the magnitude, depth, latitude, and longitude of the earthquake.
	public double getMagnitude() {return magnitude;}
	
	public double getDepth() {return depth;}
	
	public double getLatitude() {return latitude;}
	
	public double getLongitude() {return longitude;}
	

	// toString, which returns a string representation e.g. [magnitude=m5, depth=7.73, latitude=25.42, longitude=30.00] 
	//@Override
	/** This method converts an object to a string format. 
	   * @return	string format
	 */
	public String toString() {
		return String.format("Quake [magnitude = %s, depth = %s,latitude = %s, longitude = %s]", magnitude, depth, latitude, longitude);
	}
	
	/** This method converts an object to a string format. 
	   * @return	string format
	 */
	// toStringMag, which returns a string representation e.g. [magnitude=m5, latitude=25.42, longitude=30.00] 
	//@Override
	public String toStringMag() {
		return String.format("magnitude = M%s at (%s, %s)", magnitude, latitude, longitude);
	}
	
	/** This method converts an object to a string format. 
	   * @return	string format
	 */
	// toStringDep, which returns a string representation e.g. [depth=7.73, latitude=25.42, longitude=30.00] 
	//@Override
	public String toStringDep() {
		return String.format("depth = %s km at (%s, %s)", depth, latitude, longitude);
	}
	
	/*//Test Quake Class!!
	public static void main (String[] args) {
		String test = "2015-04-06T10:58:33.910Z,1.8403,-97.0712,21.36,4.9,mb,,186,14.31,0.81,us,us10001ub5,2015-04-06T11:15:33.579Z,\"West of the Galapagos Islands\",earthquake";
		
		Quake q1 = new Quake(test);
		
		System.out.println(q1.getDepth());
	
	}*/
	
}