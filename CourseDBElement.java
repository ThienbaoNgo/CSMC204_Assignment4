package CSMC204_2024_Course_Database;

//Almost done.

/**
 * @author Thienbao Ngo
 * 
 * CourseDBElement implements Comparable interface and consists of five attributes:
 * the Course ID (a String),
 * the CRN (an int),
 * the number of credits (an int),
 * the room number (a String),
 * and the instructor name (a String).   
 * 
 * Normally the CourseDBElement will be an object consisting of these five attributes
 * and is referred to as a CDE.
 *
 */
public class CourseDBElement<String> implements Comparable<String>{
	//Assuming the Type will be String, am not for sure.
	
	String course_ID;
	int CRN;
	int num_Of_Credits;
	String roomNumber;
	String instructorName;
	
	//Here's an example of the order of the elements for the constructor.
	//dataMgr.add("CMSC203",30504,4,"SC450","Joey Bag-O-Donuts");
	
	//So let us create our constructor based on that.
	//Check to see if it's really public later. TODO
	
	//Constructor.
	
	/**
	 * @param course_ID A string that represents 
	 * @param CRN The int which also acts as a unique signifier for the course.
	 * @param num_Of_Credits The number of credits received upon the completion of this course.
	 * @param roomNumber The room where this class will be held.
	 * @param instructorName The instructor that is teaching this course.
	 */
	public CourseDBElement(  //PROVISIONALLY COMPLETED
			String course_ID,
			int CRN,
			int num_Of_Credits,
			String roomNumber,
			String instructorName ) {
		//Check to see if these instances of String are really the same object.
		
		this.course_ID = course_ID;
		this.CRN = CRN;
		this.num_Of_Credits = num_Of_Credits;
		this.roomNumber = roomNumber;
		this.instructorName = instructorName;
		
	}
	
	//Look at the code to determine how to compare the information.

	/**
	 * @param o The String that will be compared.
	 * 
	 * Unfinished. 
	 */
	@Override
	public int compareTo(String o) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * This method returns the CRN of any given CourseDBElement.
	 * @return CRN The course number identifier that is used to give each class a unique signifier.
	 */
	public int getCRN() { //Completed.
		// TODO Auto-generated method stub
		return this.CRN;
	}

	/**
	 * This method returns the course_ID of any given CourseDBElement.
	 * @return course_ID The short hand of any given class.
	 */
	public String getID() {  //PROVISIONALLY COMPLETED
		return this.course_ID;
	}
	
	/**
	 * This method returns the number of credits associated with the given CourseDBElement Object.
	 * @return num_Of_Credits The number of credits that will be received upon the completion of each course.
	 */
	public int getCredits(){ //Completed.
		return this.num_Of_Credits;
	}
	
	/**
	 * This method returns the String containing the information of where the class will be taught.
	 * @return roomNumber The room number that the course will be taught in.
	 */
	public String getRoomNumber(){ //Completed.
		return this.roomNumber;
	}
	
	/**
	 * This method returns a String containing the name of the instructor teaching the course associated with the CourseDBElement.
	 * @return instructorName The name of the instructor that will be teaching this course.
	 */
	public String getName() { //Completed.
		return this.instructorName;
	}
	
	/**
	 * This method returns the hashcode generated from this CDE's CRN number.
	 * @return hashCode The Hashcode generated from this CourseDBElement's CRN number.
	 */
	@Override
	public int hashCode() { //Completed.
		
		return (Integer.toString(this.CRN)).hashCode(); //Hopefully, this makes sense.
		
	}
	
	//Create a static version of the Hashcode maker.
	
	/**
	 * A static version of the method that creates a hashcode.
	 * @param crn The int that will be used in order to create a hashcode.
	 * @return A int primitive variable, which is also the hashcode.
	 */
	public static int hashCode(int crn) {
		return (Integer.toString(crn)).hashCode();
	}
	/**
	 * This method checks to see if two CDEs are equal, which it does by comparing it's unique number and checking if they are the same.
	 * @param element The element that is being compared to THIS element.
	 * @return A boolean depending whether or not the two CDEs can be considered equal or not.
	 */
	public boolean equals(CourseDBElement element) {
		return (this.CRN == element.CRN);
	}

	/**
	 * Just another method for getting the room number String from the CDE Object. Calls upon the other getRoomNumber to do so.
	 * @return A String representing the room number.
	 */
	public String getRoomNum() {
		// TODO Auto-generated method stub
		return this.getRoomNumber();
	}

}
