
import java.util.*;
import java.io.*;

/**
 *
 * @author mimic
 */
public class ReadFile {

    private String fileName;
    private LinkedList ll;

    public ReadFile() {

        fileName = "";
        ll = null;
    }

    public ReadFile(String fileName, LinkedList ll) throws FileNotFoundException{

        this.fileName = fileName;
        this.ll = ll;
    }
    public void checkText(String filename) throws FileNotFoundException {
    	 Scanner check = new Scanner(new File(filename));
    	 String line="";
    	 
    	 while (check.hasNextLine()) {
 			line = check.nextLine(); 			 			
    	 }
    	 String [ ] list = line.split(" ");
    	 try {
    		 for(int i=0 ;i<list.length;i++) {
        		 int item = Integer.parseInt(list[i]);
        	 } 
    	 }
    	 catch (NumberFormatException e) {    		 
    		 throw e;
    	 }
    	 
    }
    public void processFile(String filename, LinkedList ll) throws FileNotFoundException {
        Scanner fin = new Scanner(new File(filename));
        int number=0;
        while (fin.hasNextInt()) {
            number = fin.nextInt();
            ll.addToTail(number);
        }
    }
}
