package Practical2;

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
public class FilesInOut {

    public static void main(String[] args) {
        int count = 0; // counts how many personel there are
        int index; // index position for loops
        String indexStore = ""; // stores the current indexes string
        String outputStore;// current output for that line
        LinkedList<String> output = new LinkedList<>(); //output that goes to the new file
        char firstLetter, firstLetterUpper; // changes forename and surname with help of firstLetter2
        String firstLetterStringUpper, firstLetterString;
        String day, month, year, DOB; // for DOB
        // creating input file
        // creating scanner to read input file
        try 
        {
            File inputFile = new File("C:\\Users\\edunn\\Documents\\GitHub\\CSCUT4Practical2\\" + args[0]);
            Scanner input = new Scanner(inputFile); // voodoo
            
            while (input.hasNextLine()) 
            {
                count++;
                index = 0;
                String details = input.nextLine(); // checking if theres another line of data
                var detailsSplit = details.split(" "); // splits the data into 3 parts
                outputStore = ""; // resets after each personel
                while(index < detailsSplit.length)
                {   
                    // check if the string part is the DOB or not and acts accordingly
                   if(!Character.isDigit(detailsSplit[index].charAt(0)))
                   {
                        firstLetter = detailsSplit[index].charAt(0); // find first letter of fore/sur names
                        firstLetterUpper = Character.toUpperCase(firstLetter);
                        firstLetterString = String.valueOf(firstLetter); // conversion char to string
                        firstLetterStringUpper = String.valueOf(firstLetterUpper);
                        indexStore = detailsSplit[index].replaceFirst(firstLetterString, firstLetterStringUpper); // changing to TitleCase
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
            Logger.getLogger(FilesInOut.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(FilesInOut.class.getName()).log(Level.SEVERE, null, ex);
            System.out.printf("Error: %s \n", ex);
        }
    }
    // Finally, add code to read the filenames as arguments from the command line
} // main

// FilesInOut

