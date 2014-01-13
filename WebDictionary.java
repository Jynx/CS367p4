///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            (Web Dictionary)
// Files:            (WebDictionary.java BSTSortedList.java Acronym.java
//					  BSTSortedListIterator.java)                
// Semester:         CS302 Spring 2013
//
// Author:           (Steven Volocyk Sevenex@gmail.com)
// CS Login:         (volocyk)
// Lecturer's Name:  (Jim Skrentny)
// Lab Section:      (Lecture 2, 204 Educational Sciences)
//                   
// Credits:          
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.*;
import java.io.*;

/**
 * The WebDictionary creates and manipulates a sorted list of internet acronyms. 
 * Author: Steven Volocyk 
 */
public class WebDictionary {
	// member variable used for temporary reference of an acronym object
	static Acronym mAcro; 
	
	// member variable used for temporary empty storage for lookup purposes.
	static String mTmp = "tmp";
	/**
	 * Helper method for the purposes of clean printing based on out being
	 * true or false. Used this for clean code.
	 * @param msg message to print
	 * @param out output type
	 * @param echo echo true or false
	 */
	static void print(String msg, PrintStream out, boolean echo) {		
	     if (echo) {
	    	 out.println(msg);	        
	     }
	     else {System.out.println(msg);}
	}
    /**
     * The main method provides the user interface as described in the program 
     * write-up.  You will need to add to the code given here.
     * 
     * @param args the command-line arguments that determine how input and 
     * output is done in the game:
     * <ul>
     *   <li>if there are no command-line arguments, then console input and 
     *   console output are used</li>
     *   <li>if there is one command-line argument, then it is treated as the
     *   name of the file from which to get input and output is sent to the
     *   console</li>
     *   <li>if there are two command-line arguments, then the first is the name
     *   of the file from which to get the input and the second is the name of 
     *   the file to which to sent the output</li>
     * </ul>
     * 
     * You may add additional static variables or methods as needed.
     */
	
    public static void main(String[] args) throws IOException {   	
        Scanner in = null;         // for input
        PrintStream out = null;    // for output
        SortedListADT<Acronym> dictionary = new BSTSortedList<Acronym>(); 
        String fileName, outFile = null; // for file reading/printing
        File inputFile = null; // for file reading
                                   // the acronym dictionary
        boolean echo = false;	   // whether or not to echo the user input
        
        // Set up where to send input and output
        switch (args.length) {
        case 0: 
        	in = new Scanner(System.in); //setting input/output
        	out = new PrintStream(System.out);
            break;

        case 1:
        	echo = true;
        	try {
        		fileName = args[0];       	
	        	// used to make sure filename argument is correct format
	        	if (!fileName.endsWith(".txt")) {
				fileName = fileName + ".txt";
				}
	        	// new file for scanner
	        	inputFile = new File(fileName);
	        	in = new Scanner(inputFile); // new scanner for reading file.
        	} catch (FileNotFoundException ex) {
    			System.err.println("Error: Cannot access input file.");
    			System.exit(0);
    		}	        	
        	out = new PrintStream(System.out);
        	
            break;

        case 2: 
        	echo = true;
        	try {
	        	fileName = args[0];
	        	outFile = args[1];
	        	// used to make sure filename argument is correct format
	        	if (!fileName.endsWith(".txt")) {
				fileName = fileName + ".txt";
				}
	        	if (!outFile.endsWith(".txt")) {
					outFile = outFile + ".txt";
					}
	        	// new file for scanner
	        	inputFile = new File(fileName);
	        	in = new Scanner(inputFile);// new scanner for reading file.
        	} catch (FileNotFoundException ex) {
        		System.err.println("Error: Cannot access input file.");
    			System.exit(0);
    		}	        	
        	out = new PrintStream(outFile);
            break;

        default:
            System.err.println("Invalid command-line arguments");
            System.exit(0);
        }

        boolean again = true;
        while (again) {        	
            print("enter choice (a, d, f, p, q): ", out, echo);
            out.flush();
            String input = in.nextLine();
            if (echo) out.println(input);
            if (input.length() == 0) {
                out.println("invalid input");
            }

            else {
                // We will have our program be case-insensitive, so we'll 
                // convert the first character to lower-case.
                char choice = input.substring(0, 1).toLowerCase().charAt(0);
                String remainder = "";  // used to hold the remainder of input
                // trim off any leading or trailing spaces
                remainder = input.substring(1).trim();

                switch (choice) {
                
                // add an address 
                // format: a name?email
                case 'a':  
                	// input check
	                if(!remainder.contains(":")) {              	
	                	print("Invalid Input.", out, echo);	                		                	
	                	break;
	                }
	                // for splitting at :
	                String[] twoInputs = remainder.split(":");	
	                String one,two;
	                //both one/two for creating Acronym Object
	                one = twoInputs[0];
	                two = twoInputs[1];
	                try {
	                mAcro = new Acronym(one,two); 	
	                } catch (IllegalArgumentException Ex) {
	                	print("Invalid Input.", out, echo);	
	                	break;
	                }
	                dictionary.insert(mAcro);
                    break;

                // delete an address
                // format: d name
                case 'd':
                	// creates a temporary Acronym object with an empty
                	// meaning for the sole purpose of lookup object comparison.
                	mAcro = new Acronym(remainder,mTmp);      
                	// boolean for successful delete.
                	boolean success = dictionary.delete(mAcro);
                	if(!success) {
                		print("Not Present.", out, echo);	                		
                	}               
                	break;
                    
                // find an address
                // format: f name
                case 'f':   
                	// creates a temporary Acronym object with an empty
                	// meaning for the sole purpose of lookup object comparison.
                	mAcro = new Acronym(remainder,mTmp);                	
                	Acronym lkup = dictionary.lookup(mAcro);
                	if(lkup == null) {
                		print("Not Present.", out, echo); 
                		break;
                	} 
                	print(lkup.toString(), out, echo);
                    break;
                // print the contents of the address book in sorted order
                // format: p
                case 'p':  
                	// new iterator for printing
                	Iterator<Acronym> itr = dictionary.iterator();
                    while(itr.hasNext()) {
                    	mAcro = itr.next();
                    	print(mAcro.toString(), out, echo);
                    }	
                    break;
                    
                // quit - this does not need to be changed
                // format: q
                case 'q':  
                    again = false;
                    out.println("done");
                    break;

                default:   // anything else is invalid
                    out.println("invalid choice");
                } // end switch
            } // end else
        } // end while
    } // end main
}
