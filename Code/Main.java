
import java.io.FileNotFoundException;


public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		LinkedList ll = new LinkedList();
		LinkedList checkLinkedList = new LinkedList();
		
		try {
			String option=args[0];
			String fileName=args[1];
			String inputChar=args[2];
			
			
			ReadFile r = new ReadFile(fileName, ll);
	        r.checkText(fileName);
			r.processFile(fileName, ll);
	        
	        
	        checkLinkedList = ll;
	        checkFile(checkLinkedList);
	        
	        if(option.equals("keygen")){        
	        	System.out.println("Keystream values: "+ll.keystream(inputChar, ll));
	        }
	        else if(option.equals("de")){
				LinkedList list = new LinkedList();
				list = ll.invisibleStep(inputChar, ll);
	        	ll.decrypt(list, inputChar);
	        }
	        else if(option.equals("en")){
				LinkedList list = new LinkedList();
				list = ll.invisibleStep(inputChar, ll);
	        	ll.encrypt(list, inputChar);
	        }
	        else throw new ArrayIndexOutOfBoundsException();
		}
		catch (NumberFormatException e) {
	   		 System.out.print("The text file contains non numeric character.");
	   		 
	   	 }
		catch (InvalidCardException e) {
			System.out.print(e);
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Please enter valid format: [Options][FIle Name] [Characters]");
		}
		catch (FileNotFoundException e) {
			System.out.println("Please enter the valid file name.");
		}
		catch (EmptyListException e) {
			System.out.println(e);
		}
		catch (TooLargeFileException e) {
			System.out.print(e);
		}

		catch (NotFullCardException e) {
			System.out.print(e);
		}
		
		catch (CardRedundantException e) {
			System.out.print(e);
		}
		
        
    }
	public static void checkFile(LinkedList link) throws NotFullCardException, TooLargeFileException{
		int length = 0;
		int[] checkNum=new int[28];
		length= link.getCount();		
		if(length>28)
			throw new TooLargeFileException();				//throw exception if the number of card >28
		if(length<28)
			throw new NotFullCardException();				//throw exception if the number of card <28
		for(ListNode current = link.head;current.next!=null;current= current.next) {
			int item=current.data;
			int count=0;
			if(current.data>28)
				throw new InvalidCardException();					//throw exception if the card number >28
						
			for(ListNode curListNode = link.head;curListNode.next!=null;curListNode = curListNode.next) {
				if(curListNode.data==item)
					count++;
			}
			
			if(count>1) {
				throw new CardRedundantException();				////throw exception if the card number repeat more than on time
			}
		}
	}


}
