package Practical2M;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * CSCU9T4 Java strings and files exercise.
 *
 */
public class FilesInOutM {
// could possibly use a if statement to check for the input of -u format and adjust args within if appropriately
    public static void main(String[] args) {
        int count = 0; // counts how many personel there are
        int index; // index position for loops
        String indexStore = ""; // stores the current indexes string
        String outputStore;// current output for that line
        LinkedList<String> output = new LinkedList<>(); //output that goes to the new file
        char firstLetter, firstLetterUpper; // changes forename and surname with help of firstLetter2
        String firstLetterStringUpper, firstLetterString;
        String day, month, year, DOB; // for DOB
        String temp = args[0];
        if(args[0].equals("-u")) // if the user inputs -u it is moved from index 0 to index 2
        {
            // change -u to the last index
            args[0] = args[2];
            args[2] = temp;
            // rearrange position of first and second file to be in the correct order
            temp = args[0];
            args[0] = args[1];
            args[1] = temp;
        }
        // creating input file
        // creating scanner to read input file
        try 
        {                                              // may want to enclose the file creation within a if statement to ensure the -u isn't taken incorrectly into either file creation  
            File inputFile = new File("C:\\Users\\edunn\\Documents\\GitHub\\CSCUT4Practical2\\" + args[0]);
            Scanner input = new Scanner(inputFile); // voodoo
            
            while (input.hasNextLine()) 
            {
                count++;
                index = 0;
                String details = input.nextLine(); // checking if theres another line of data
                var detailsSplit = details.split(" "); // splits the data into 3 or 4 parts
                outputStore = ""; // resets after each personel
                while(index < detailsSplit.length)
                {   
                    // check if the string part is the DOB or not and acts accordingly
                   if(!Character.isDigit(detailsSplit[index].charAt(0)))
                   {
                       // this if else looks for the users choice of wanting to have all uppercase
                       if(args.length == 2)
                       {
                            firstLetter = detailsSplit[index].charAt(0); // find first letter of fore/sur names
                            firstLetterUpper = Character.toUpperCase(firstLetter);
                            firstLetterString = String.valueOf(firstLetter); // conversion char to string
                            firstLetterStringUpper = String.valueOf(firstLetterUpper);
                            indexStore = detailsSplit[index].replaceFirst(firstLetterString, firstLetterStringUpper); // changing to TitleCase
                       }
                       else // assumes the user has entered -u
                       {
                           indexStore = detailsSplit[index].toUpperCase();
                       }
                       
                       // looking to see if there is a middle initial and adding fullstop to it if so
                       if(detailsSplit.length == 4 && index == 1)    
                       {
                                indexStore = indexStore.concat(".");
                       }
                    }
                   else
                   {
                       day = detailsSplit[index].substring(0, 2);
                       month = detailsSplit[index].substring(2, 4);
                       year = detailsSplit[index].substring(4);
                       DOB = day + "/" + month + "/" + year;
                       indexStore = detailsSplit[index].replaceFirst(detailsSplit[index], DOB);  // changing to DOB format                    
                   }
                   
                   outputStore = outputStore + " " + String.join(detailsSplit[index], indexStore); // concatenates parts together
                   System.out.println(outputStore);
                   index++;
                }
                output.add(outputStore); // Stores each personal data to its own index in a LinkedList, magical
                System.out.println(output);
            }
            input.close();
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(FilesInOutM.class.getName()).log(Level.SEVERE, null, ex);
            System.out.printf("Error:  %s \n", ex);
        }
        
        // creating output file
        try 
        {
            FileWriter outputFileWriter = new FileWriter("C:\\Users\\edunn\\Documents\\GitHub\\CSCUT4Practical2\\practical2\\" + args[1]); //temp file
            PrintWriter outputFile = new PrintWriter(outputFileWriter); 
            index = 0; // used now for outputFile
            outputStore = ""; // used now for outputFile
            while(count != 0)
            {
                count--;
                outputStore = output.get(index); // get the first personel but with updated formate
                index++;
                outputFile.write(outputStore + "\n"); // writes to new file with same whitespace as old
            }
            outputFile.close();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(FilesInOutM.class.getName()).log(Level.SEVERE, null, ex);
            System.out.printf("Error: %s \n", ex);
        }
        System.out.println(args[0] + "  " + args[1]);
    }
} // main

// FilesInOut