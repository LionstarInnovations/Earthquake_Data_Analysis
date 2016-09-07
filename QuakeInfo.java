package comp1551.cwk4;

import java.io.IOException;

import javax.swing.JOptionPane;

/**
* QuakeInfo class
*
* @author Marcus McFarlane
*/
public class QuakeInfo {
	
	//------------- Main Test Program  
	//Simple program in main that creates a quakeList object and calls its print method.
	//Test Quake Class!!
	  public static void main (String[] args) {
		  try {
			  			  
			  QuakeList quakeObj = new QuakeList(args[0],args[1]);
			  
			  // Number of quakes analyzed.
			  System.out.println(quakeObj.getSize() + " Quakes Analyzed");
			   
		      
			  //----------- Need to return the object and then the getLatitude() method on the strongest() as multiple methods.
			  
			  //---- Strongest Method used finding the strongest earthquake.
			  String maxMag = quakeObj.strongest(); 
			  			 
			  System.out.println("Strongest: " + maxMag.toString());
			  
			  //---- Shallowest Method used finding the shallowest earthquake
			  String minDep = quakeObj.shallowest();		  
			  System.out.println("Shallowest: " + minDep.toString());
			  
			  //---- Deepest Method used finding the deepest earthquake
			  String maxDep = quakeObj.deepest();		  
			  System.out.println("Deepest: " + maxDep.toString());
			  
		  } catch (Exception error) {
              JOptionPane.showMessageDialog (null, "Error: Missing command line argument/s");
			  System.err.println("Error: " + error);
			  System.exit(1);
			}
	  }
}
