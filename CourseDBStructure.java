package CSMC204_2024_Course_Database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Thienbao Ngo
 *
 Referred as CDS, Implements the CourseDBStructureInterface that is provided.
 You will be implementing a hash table with buckets. Each bucket will be an array of linked lists of
 CourseDBElements.
 
 Each CourseDBElement object will have a hash code that is calculate based on the CRN, since the CRN
 is unique for courses.
 
 Note that the CRN is an int, and the tests require the hashcode of a string,
 so you will need to coerce it to a String and take the hash code of the resulting string.
 
 The add method of CourseDBStructure will take a CourseDBElement object and add it to the
 data structure based on the calculated hashcode.
 
 If a linked list at the relevant hash code doesn’t exist (the bucket is empty),
 create a LinkedList with the first element being the CourseDBElement object
 and add it to the HashTable.
 
 If the LinkedList already exists, add the CourseDBElement object to the existing list.
 *
 */
public class CourseDBStructure implements CourseDBStructureInterface{
	
	private LinkedList<CourseDBElement>[] hashtable;
	
	private static final Double LOAD_FACTOR = 1.5; //Provided by assignnment.
	private int tableSize;

	/**
	 * The add method of CourseDBStructure will take a CourseDBElement object and add it to the
	 * data structure based on the calculated hashcode.
	 * 
	 * @param element The element that will be added to the entire structure.
	 */
	@Override
	public void add(CourseDBElement element) { //Complete.
		//This means CourseDBElements are created before being added to the Structure, not created in this method.
		int storagePosition = element.hashCode() % this.tableSize;
		
		if(hashtable[storagePosition] != null){ //if LinkedList is not null...
			//...then there is already a LinkedList stored at that position.
			
			//We need to create an iterator.
			Iterator<CourseDBElement> courseIterator = hashtable[storagePosition].iterator();
			
			//Create a flag.
			boolean hasSame = false;
			
			//Check for same object... throughout the entire list.
			while(courseIterator.hasNext()){
				//Checks all of them, because the iterator rests in between elements, and at the start before the first element.
				CourseDBElement compare = courseIterator.next();
				if(element.equals(compare)){ //Object type is the same.
					//Do nothing, do not add.
					
					//HOLD UP, we also have to check if the CRN is the same, but if everything else is different as well.
					if(this.shouldUpdateCourse(element, compare) == true) {
						//compare = element;
						//Does this work? No.
						
						compare.course_ID = element.course_ID;
						compare.instructorName = element.instructorName;
						compare.num_Of_Credits = element.num_Of_Credits;
						compare.roomNumber = element.roomNumber;
					}
					
					hasSame = true;
					break;
				}
				else {
					//If object is not the same. Don't do anything... yet.
					//Go through entire LinkedList first.
				}
			}
			
			if(hasSame != true){ //is false.
				hashtable[storagePosition].addLast(element); //Adds to end.
			}
			
		}
		else { //if LinkedList is null...
			//...then there is no LinkedList at this position, nor any items stored in it.
			
			hashtable[storagePosition] = new LinkedList<CourseDBElement>();
			//create new LinkedList with nothing in it.
			
			//if it is a new linkedlist then we do not have to check to see if there is another instance of the same object.
			
			//Add object.
			hashtable[storagePosition].addLast(element); //Adds to end.
		}
		
		//when added to the Structure.
	}

	/**
	 * The method that will be used to retrieve a CDE from the structure.
	 * @param crn The unique number that represents the course, and be used in order to find the associated CDE.
	 */
	@Override
	public CourseDBElement get(int crn) throws IOException { //PROVISIONALLY COMPLETED
		//Use this CRN in order to generate a hashCode to find where it is.
		int hashCodeFind = CourseDBElement.hashCode(crn);
		int findPosition = hashCodeFind % this.tableSize;
		
		CourseDBElement returned = null; //Must instantiate with something, null, otherwise red underline.
		
		if(hashtable[findPosition] != null){
			//Then there is a LinkedList in this position.
			
			//What do we know about where Elements are inserted? Using that, we can find out how to, conversely, retrive them.
			
			//Given we have a linked list, we should start at the end for our linkedlist contained within the position and work backwards. ---Why?
			boolean found = false;
			
			Iterator getElement = hashtable[findPosition].iterator();
			
			while(getElement.hasNext()){
				
				returned = (CourseDBElement)getElement.next();
				
				if( ((CourseDBElement)returned).getCRN() == crn ){
					found = true;
				}
				
			}
			
			if(found == false){ //if - not found. 
				throw new IOException();
			}
			
		}
		else { //At the calculated position there is no LinkedList, which is incorrect assuming the method is used correctly, there should be an element here.
			throw new IOException();
		}
		
		return returned;
	}

	/**
	 * This method is used to retrieve all the CDEs in the Structure and represent them as Strings within an ArrayList.
	 */
	@Override
	public ArrayList<String> showAll() { //PROVISIONALLY COMPLETED
	    ArrayList<String> answer = new ArrayList<>();
		
	    //for (LinkedList<CourseDBElement> bucket : hashtable) {   
	    
	    for (int i = hashtable.length - 1; i >= 0; i--) {
	    	LinkedList<CourseDBElement> bucket = hashtable[i];
	        // If the bucket is not null
	        if (bucket != null) { //Warning: I am unsure if it's null by default in the array or not.
	            // Iterate through each element in the bucket
	            for (CourseDBElement element : bucket) {
	                // Add the ID of the element to the courseList
	               
	                String added = "Course:" + element.getID() + " CRN:" + element.getCRN() + " Credits:" + element.getCredits() + " Instructor:" + element.getName() + " Room:" + element.getRoomNumber();
	                answer.add(added);
	                //Not right, the String is not just the course ID.
	                //Example: Course:CMSC500 CRN:39999 Credits:4 Instructor:Nobody InParticular Room:SC100
	            }
	        }
	    }
		return answer;
	}

	/**
	 * This method is used to get the size of the table from the Structure Object.
	 */
	@Override
	public int getTableSize() { //Completed
		//I am assuming that it wants me to return the number of buckets the hashmap is holding, not just...
		//I don't know what the hell I was talking about.
		
		return this.tableSize;
	}
	
	
	//Constructors
	
	/*
	Two constructors for the CourseDBStructure will be required,
	one that takes in an integer that is the estimated number of courses,
	the other is used for testing purposes.
	
	The comments in the CourseDBStructureInterface (provided) should help you figure out how to
	set the length of the hash table.
	
	Note: In hash table structure with buckets the load factor can be larger than one and represents
	the average number of elements stored in each list, assuming that the hash function distributes
	elements uniformly over all positions. For this assignment use a load factor of 1.5.
	
	This class has two constructors:
		1)	A constructor that takes in an integer n which represents the estimated number of 
	courses and determines the size of the hash table by finding a 4K+3 prime just greater than n /loading factor.
	Example:  if n is 500 courses, then 500/1.5 = 333, The next 4K+3 prime over 333 is 347.  So, you would set the table a length to 347.

		2)	A Constructor for testing purposes. This constructor will take a string “Testing” and an
	int for the hashtable size.  This is used only for testing.

	 */
	
		/**
		 * @param n An primitive data type of int that represents the estimated number of courses.
		 */
		CourseDBStructure(int n){ //PROVISIONALLY COMPLETED

			Double biggerThen = n / LOAD_FACTOR; //Should result in a double, if any bugs occur, test this. (Why would any bugs occur?)
			
			//"determines the size of the hash table by finding a 4K+3 prime just greater than n /loading factor."
			
			int start = (int) Math.ceil(biggerThen); //Possible class cast exception? *crosses fingers.*
			
			boolean foundSize = false;
			
			//While loop.
			while(foundSize == false) {
				
				if( this.isPrimeNumber( start ) ) {
					if( this.is4KPlus3Prime( start ) ) {
						
						//"just greater than n /loading factor."
						//I don't know what 'just greater' means, but I can reasonably infer this to mean that it cannot equal n/loading factor.
						
						if( (biggerThen.equals(1.0*start) == false) ) {
							foundSize = true;
							break; //foundSize never used?
						}
					}
				}
				start++;
			}
			
			//Now we have found our size.
			
			//Create an array of type LinkedList
			this.hashtable = new LinkedList[start];
			//Potential type mismatch problem.
			//Without specifiying, that means it will just be a LinkedList of Object, I will have to class cast at some point later in the process.
			
			//Wait, is it hashtable or called hashmap? Hashtable.
			
			this.tableSize = start;
		}
		
		//For testing?
		/**
		 * This constructor is only used by the test class to test other methods without relying on the other constructor.
		 * @param testing This String is not used for anything.
		 * @param hashtableSize This int variable is used to set the size, nothing more, nothing less.
		 */
		CourseDBStructure(String testing, int hashtableSize){ //PROVISIONALLY COMPLETED
			
			//Not sure what this will do yet.
			//Ask professor how to implement.
			
			this.hashtable = new LinkedList[hashtableSize];
			this.tableSize = hashtableSize;
		}
		
		//For me:
		/**
		 * This method is used by the other classes to check if a number, check, is in 4KPlus3 form.
		 * @param check The number that is "checked."
		 * @return A boolean, true or false, whether the number is already in the right form or not.
		 */
		private static boolean is4KPlus3Prime(int check) { //PROVISIONALLY COMPLETED
			
			if((check % 4) == 3) {
				return true;
			}
			
			return false;
			
		}
		/**
		 * This method uses a for loop to calculate if check is a prime number or not.
		 * @param check The number that is checked if it is a prime number or not.
		 * @return A boolean that returns true/false depending on if check is prime or not.
		 */
		private static boolean isPrimeNumber(int check){ //PROVISIONALLY COMPLETED
			//Remember: A prime number is a number only divisible by itself and one.
			boolean answer = true;
			//i starts at 2 because all prime numbers are divisible by one.
			//check/2 because anything over half will not evenly divide.
			for(int i = 2; i <= check/2; i++) {
				if(check % i == 0) {
					answer = false;
				}
			}
			
			return answer;
		}
	
		/**
		 * This method exists to update a course in hashtable that is otherwise considered to be equal, and therefore should be updated or not with new course details.
		 * @param insert The CDE that is compared to the one that already exists in the structure.
		 * @param original The original CDE that exists in the structure and is now being compared.
		 */
		private boolean shouldUpdateCourse(CourseDBElement insert, CourseDBElement original){
			
			//Check if the courses are the same course.
			if(insert.equals(original)){
				//Does nothing.
			}
			else {
				return false; //Stops code.
			}
			
			//Check to see if anything else is different.
			if(original.course_ID == insert.course_ID) {
				//Do nothing.
			}
			else {
				return true;
			}
			if(original.instructorName == insert.instructorName) {
				//Do nothing.
			}
			else {
				return true;
			}
			if(original.num_Of_Credits == insert.num_Of_Credits) {
				//Do nothing.
			}
			else {
				return true;
			}
			if(original.roomNumber == insert.roomNumber) {
				//Do nothing.
			}
			else {
				return true;
			}
			
			//If it makes it to the end with no differences then it should return false.
			return false;
			
		}
	
}
