package comp1551.cwk4;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

import java.text.SimpleDateFormat;

/**
 * A list of earthquakes acquired from a USGS data feed.
 *
 * (See http://earthquake.usgs.gov/eathquakes/feed/v1.0/csv.php)
 *
 * @author Marcus Daniel McFarlane
 */
class QuakeList
{
  // Template for data feed URLs

  private static final String URL_TEMPLATE =
   "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/%s_%s.csv";

  // Names of base image and HTML template used for map generation

  private static final String MAP_IMAGE_FILE = "map-base.png";
  private static final String MAP_TEMPLATE_FILE = "map-template.html";

  // HTML map template - static, so shared by all QuakeList objects

  private static Template mapTemplate;

  // Fields

  private String level;
  private String period;
  private ArrayList<Quake> data;

  /**
   * Creates a QuakeList from a data feed specified by the given parameters.
   *
   * @param level Minimum level of quake to consider
   * @param period Time period to consider
   * @throws IOException If data could not be acquired for some reason
   */
  public QuakeList(String level, String period) throws IOException
  {
    this.level = level;
    this.period = period;
    data = new ArrayList<Quake>();
    acquireData();
    loadMapTemplate();
 
  }

  
  private void acquireData() throws IOException
  {
    // Connect to the relevant USGS data feed

    URL url = new URL(String.format(URL_TEMPLATE, level, period));
    URLConnection connection = url.openConnection();

    // TO DO (Basic Solution):
    //
    // 1. Create a Scanner to parse the downloaded data
    //
    // 2. If there are lines available, read (and ignore) first line
    //    (because it consists of column headings)
    //
    // 3. While lines are available, read each line, use it to create a
    //    Quake object and add that Quake object to the data list
  
	// Calling getInputStream method on connection object, and 
	// assigning the result to variable quakeData..
	InputStream quakeData = getInputStream(connection);
  
	// Creating a Scanner object to read the downloaded quakeData variable.
	//File inputFile = new File(quakeData.toString());
	try {
		Scanner input = new Scanner(quakeData);
		// Reading the first line (skipping the headers).
		input.nextLine();	
	
		// Fetch lines for as long as we can..
		String line;
		while (input.hasNextLine()) {
			line = input.nextLine();
			//System.out.println(line);
			
			// Creating a quake object.
			Quake earthquake1 = new Quake(line);
			
			// Adding the Quake object to the data list.
			data.add(earthquake1);
		}
	}catch (Exception error) {
		  System.err.println("Empty result");
		  System.exit(1);
		} 
  
  }

	private InputStream getInputStream(URLConnection connection) throws IOException {
		InputStream quakeData = connection.getInputStream();
		return quakeData;
	}
    

  private void loadMapTemplate() throws IOException
  {
    if (mapTemplate == null) {
      // First QuakeList object to be created will
      // take care of loading the HTML template

      FileReader source = new FileReader(MAP_TEMPLATE_FILE);
      mapTemplate = Mustache.compiler().compile(source);
    }
  }

  //---------- Method getSize()
  // Creating a Method 'size' which returns the number of stored earthquakes.
  // this is just counting the array elements for the earthquake objects.
  /** This method finds the quantity of the earthquakes in the earthquake list. 
   * @return		The object list quantity
   * */
  
  public int getSize() {
	  return data.size();
  }
  
  //---------- Method Print()
  // Creating a Method 'print' which prints out the details of each 
  // earthquake, one quake per line.
  /** This method prints the list of earthquakes out to the console. 
   */
  
  public void print() {
	  
//	  String print = System.out.println();
	  Quake print1;
	  for (int i = 0; i < getSize(); i++) {
		  print1 = data.get(i);
		  System.out.println(print1.toString());
	  }
	  	  
  }
  
  //------------ Main Test Program
  //Simple test program in main that creates a quakeList object and calls its print method.
  public static void main (String[] args) {
	  try {
		  QuakeList ql = new QuakeList("4.5","week");
		  ql.print();
		  
	  } catch (IOException e) {
		  e.printStackTrace();
	  }		
	
  }
  
  //----------- Method Strongest
  // Strongest method returning the quake object with the highest magnitude.
  /* This is done by using a for loop iterator to loop through the objects a 
   * number of times, ending when it reaches the quantity size of the objects.
   * Also in the for loop setting the current max variable to zero to start 
   * off, for the if statement when finding the value that is higher then the 
   * current max! Then using an if statement in the for loop iterator, which 
   * has a condition with variables that state if the current value is greater 
   * then the current max, then the block of code will be executed that updates 
   * the current max variable to equal the current value of the object element. 
   * The loop and if statement is repeated until the current value is not 
   * greater then the current max. In which case the current max will then be 
   * the maximum value element of that list of objects. At this point, the 
   * object that the iterator is on which contains the highest value, has the 
   * toString() method called on it and the result is inputted into a variable.
   * That variable is then returned e.g. return strongestMag.
   */  
   /** This method finds the strongest earthquake magnitude from the data within field data. 
   * @return		The object with the maximum value of the magnitude
   * */
    
  public String strongest() {
	  
	  Quake quakeObj = data.get(0); 
	  
	  double maxMag = 0.0;
	  
	  String strongestMag = null;
	  	  	  
	  for (Quake counter: data) // can only iterate over array!  
	  {
		  
		  quakeObj = counter;
		  
		  double Mags = quakeObj.getMagnitude();
		  
		  if (Mags > maxMag)
		  {
    		// This should put the current object into the variable!		  
			quakeObj = counter;  
			
			strongestMag = quakeObj.toStringMag();
			
			maxMag = Mags;
						
		  }
		  
	  }
	  
	  /*//-------Test
	  String max = Double.toString(maxMag); 
	  System.out.println("mag" + max);*/
	  	  	  
	  return strongestMag; 
  }

  
//------------- Method Shallowest  
//   'shallowest', returning the Quake object with smallest depth
/* This method is made with a similar concept to the strongest method, except
 * the method returns the minimum rather than the maximum. To do this all that
 * has to be changed is the value of the min value variable that is initialised 
 * at the start of the for loop, needs to be set at a value which is higher 
 * than all of the current values. Also the if stateemnt condition operator to 
 * less than (<) so that if the current value variable is less than the curent 
 * min value variable than the min value variable will be updated to equal the 
 * current depth value variable. This is repeated until current depth value 
 * variable is not less than the current min depth value variable. loops down 
 * to the minimum value 
 */ 
 /** This method finds the shallowest earthquake depth from the data within field data. 
 * @return		The object with the minimum value of the depth
 * */
  
  public String shallowest() {
	  
	  Quake quakeObj = data.get(0); 
	  
	  double minDepth = 1000.0;
	  
	  String shallowestDepth = null;
	  
	  for (Quake counter: data) // can only iterate over array!  
	  {

		  quakeObj = counter;
		  
		  double Depths = quakeObj.getDepth();

		  if (Depths < minDepth)
		  {
			  
			minDepth = Depths;
			
			shallowestDepth = quakeObj.toStringDep();
			
		  }
	  }
	  
	  /*//----Test
	  String min = Double.toString(minDepth); 
	  System.out.println("Depth" + min);*/
	  
	  return shallowestDepth; 
  }

  //--------------- Method Deepest()
  //   'deepest', returning Quake object with the largest depth
  /* Same concept as the strongest method, except your calling a different 
   * method on the relevant object
  */ 
   /** This method finds the deepest earthquake depth from the data within field data. 
   * @return		The object with the maximum value of the depth
   */
  
  public String deepest() {
	  
	  Quake quakeObj = data.get(0); 
	  
	  double maxDepth = 0.0;
	  
	  String deepestDepth = null;
	  
	  for (Quake counter: data) // can only iterate over array! 
	  {
		  
		  quakeObj = counter;
		  
		  double Mags = quakeObj.getDepth();

		  if (Mags > maxDepth)
		  {
			  
			maxDepth = quakeObj.getDepth();
			
			deepestDepth = quakeObj.toStringDep();
			
		  }
	  }
	  
	  /*//----Test
	  String max = Double.toString(maxDepth); 
	  System.out.println("Depth" + max);*/
	  
	  return deepestDepth; 
  }

}