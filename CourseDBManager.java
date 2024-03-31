package CSMC204_2024_Course_Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.stage.FileChooser;

/**
 @author Thienbao Ngo
 *
 Implements the CourseDBManagerInterface that is provided.
 The data manager allows the user to read the courses from a file or to enter the data by hand and
 uses an Alert to print out the database elements.
 
 The input is read from a file or read from the textfields and is added to the data structure through
 the add method. The add method uses the CDS ‘s add method.
 
 The CourseDBManager is also referred to as a CDM.

 */
public class CourseDBManager implements CourseDBManagerInterface{
	private FileChooser choose;
    private CourseDBStructure database;
    private static final int INITIAL_SIZE = 999;
	
	//private CourseDBManagerInterface dataMgr = new CourseDBManager();
	
	//We need a constructor.
	public CourseDBManager() {
		database = new CourseDBStructure(INITIAL_SIZE);
	}

	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		database.add(new CourseDBElement<String>(id,
				crn,
				credits,
				roomNum,
				instructor));
		
	}

	@Override
	public CourseDBElement get(int crn) {
		try {
			return database.get(crn);
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public void readFile(File input) throws FileNotFoundException {
		
	     try {
	    	 
	    	
			BufferedReader reader = new BufferedReader(new FileReader(input));
		    String currentLine;
	    	 
			while((currentLine = reader.readLine()) != null) {
			//So this line makes sure that it only tries to read a line as it still exists.
				
				//We must remove the white space at the end of the line.
				//Example: CMSC100 22344 2 SW217 Gloria E. Barron	 |
				
				//Removes white-space at the end of a String.
				while(Character.isLetterOrDigit( currentLine.charAt(currentLine.length() - 1)) != true){
					currentLine = currentLine.substring(0, currentLine.length() - 1);
				}
				
				//create a for-loop to go through.
				int whereSpace = 0;
				int[] splitUp = new int[5];
				splitUp[0] = whereSpace;
				int spacesFilled = 1;
				
				//Do something about "Distance Learning" vs "Distance-Learning"
				if(currentLine.contains("Distance")) {
					if(currentLine.charAt(19) != '-') {
						currentLine = currentLine.substring(0, 19) + "-" + currentLine.substring(20, currentLine.length());
					}
					else {
						//Do nothing.
					}
				}
				//---
				
				for(int i = 0; i < currentLine.length(); i++) {

					if(spacesFilled >= 5){
						break;
					}
					
					if(Character.isWhitespace(currentLine.charAt(i))) {
						whereSpace = i;

						splitUp[spacesFilled] = whereSpace;
						spacesFilled++;
					}
				}
				
				//Remember:
				/*
				String course_ID,
				int CRN,
				int num_Of_Credits,
				String roomNumber,
				String instructorName
				 */
				
				String course_ID = currentLine.substring(splitUp[0], splitUp[1]);
				
				int CRN = Integer.parseInt(currentLine.substring(splitUp[1]+1, splitUp[2]));
				
				int credits = Integer.parseInt(currentLine.substring(splitUp[2]+1, splitUp[3]));
				
				String roomNumber = currentLine.substring(splitUp[3]+1, splitUp[4]);
				 
				String instructorName = currentLine.substring(splitUp[4]+1, currentLine.length());
				
				this.database.add(new CourseDBElement<String>(course_ID, CRN, credits, roomNumber, instructorName));
				
			//Test:
			  //System.out.println(currentLine);
			  }

		      reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
		
	}

	@Override
	public ArrayList<String> showAll() {
		return this.database.showAll();
	}

}
