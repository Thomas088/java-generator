import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {

    public static void main(String args[]) throws FileNotFoundException 
   {
 
        //creating File instance to reference text file in Java
        File sql = new File("PATH TO .SQL FILE");
     
        //Creating Scanner instance to read File in Java
        Scanner scnr = new Scanner(sql);
     
        //Reading each line of the file using Scanner class
        int lineNumber = 1;
        while(scnr.hasNextLine())
        {
            String strCurrentLine = scnr.nextLine();
            lineNumber++;
            
            if (strCurrentLine.startsWith("CREATE TABLE"))
            {
                System.out.println("line " + lineNumber + " :" + strCurrentLine);

            }
            else 
            	{
            	strCurrentLine = "";
            	}
        }			
    }      
 }  
