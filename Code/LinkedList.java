public class LinkedList {
	public ListNode head;
	private ListNode tail;
	private int count;

	public LinkedList() {
		head = null;
		tail = null;
		count = 0;
	}

	public boolean isEmpty() {
		return (head==null);
	}

	public int getCount() {
		return count;
	}
	
	
	public void addToHead(int item) {
		count++;
		if (isEmpty()) {
			head = tail = new ListNode(item);
		} else {
			head = new ListNode(item, head);
		}
	}

	public void addToTail(int item) {
		count++;
		if (isEmpty()) {
			head = tail = new ListNode(item);
		} else {
			tail.next = new ListNode(item);
			tail =  tail.next;
		}
	}

	public int removeFromHead() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException();
		} 
		count--;
		int item = head.data;
		if (head == tail) // there's only one single node
			head = tail = null;
		else
			head = head.next;
		return item;
	}

	public int removeFromTail() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException();
		} 
		count--;
		int item = tail.data;
		if (head == tail) {   // there is only one node
			head = tail = null;
			return item;
		}
		// search for the second last node 
		ListNode current = head;
		while (current.next != tail)
			current = current.next;
		// set second last node as new tail
		tail = current;
		tail.next = null;
		return item;
	}

	//swap joker a under the next card
	public void swapJokerA() {
		if(getIndexOfItem(27)==27) {
			removeFromTail();
			addToHead(27);
		}
		else {
			int pos = getIndexOfItem(27);
			int item = removeItemAt(pos);
			addItemAt(item, pos+1);
		}
			
	}
	//swap joker b(28) after under next 2 card
	public void swapJokerB() {
		if(getIndexOfItem(28)==27) {
			removeFromTail();
			addItemAt(28, 1);
		}
		else if(getIndexOfItem(28)==26) {
			removeItemAt(getIndexOfItem(28));
			addToHead(28);
		}
		else {
			int pos = getIndexOfItem(28);
			int item = removeItemAt(pos);
			addItemAt(item, pos+2);
			
		}
	}
	
    public LinkedList tripleCut() {				//combine all the three sublists together into a new list

        LinkedList sub1 = addToSublist1();  //put the fisrt cut of deck into sub1
        LinkedList sub2 = addToSublist2();  //put the middle cut of deck into sub2
        LinkedList sub3 = addToSublist3();  //put the last cut of deck into sub3
        LinkedList sub4 = new LinkedList();

        for (ListNode current = sub3.head; current != null; current = current.next) {
            sub4.addToTail(current.data);
        }
        for (ListNode current = sub2.head; current != null; current = current.next) {
            sub4.addToTail(current.data);
        }
        for (ListNode current = sub1.head; current != null; current = current.next) {
            sub4.addToTail(current.data);
        }
        return sub4;
    }

    public LinkedList addToSublist1() {            //cut the first part of three into the first sublist
        LinkedList sub1 = new LinkedList();
        ListNode current = head;
        while (current != null) {
            if (current.data == 27 || current.data == 28) {
                break;
            }
            sub1.addToTail(current.data);
            current = current.next;
        }

        return sub1;
    }

    public LinkedList addToSublist2() {    //cut the middle part of three into the middle sublist
        LinkedList sub3 = new LinkedList();
        ListNode newCurrent;
        if (getIndexOfItem(27) < getIndexOfItem(28)) {
            for (ListNode current = head; current != null; current = current.next) {
                if (current.data == 27) {
                    newCurrent = current;
                    while (newCurrent != null) {
                        sub3.addToTail(newCurrent.data);
                        if (newCurrent.data == 28) {
                            break;
                        }
                        newCurrent = newCurrent.next;
                    }
                }
            }
        } else {
            for (ListNode current = head; current != null; current = current.next) {
                if (current.data == 28) {
                    newCurrent = current;
                    while (newCurrent != null) {
                        sub3.addToTail(newCurrent.data);
                        if (newCurrent.data == 27) {
                            break;
                        }
                        newCurrent = newCurrent.next;
                    }
                }
            }
        }

        return sub3;
    }

    public LinkedList addToSublist3() {           //cut the last part of three into the last sublist
        LinkedList sub2 = new LinkedList();
        if (getIndexOfItem(27) < getIndexOfItem(28)) {
            for (ListNode current = head; current != null; current = current.next) {
                if (current.data == 28) {
                    for (ListNode newCurrent = current.next; newCurrent != null; newCurrent = newCurrent.next) {
                        sub2.addToTail(newCurrent.data);
                    }
                }

            }
        } else {
            for (ListNode current = head; current != null; current = current.next) {
                if (current.data == 27) {
                    for (ListNode newCurrent = current.next; newCurrent != null; newCurrent = newCurrent.next) {
                        sub2.addToTail(newCurrent.data);
                    }
                }

            }
        }

        return sub2;
    }
    
    //step 4: remove the index item (last one) first then remove the relative number of item from head.
    public void countCut() {
    	int lastItemIndex = tail.data;
    	int i=0;
    	if(lastItemIndex<27) {
    		int item = removeFromTail();
    		for(ListNode current=head;current.next!=null&&i<lastItemIndex;current = current.next) {
    			int swapItem = removeFromHead();
    			addToTail(swapItem);
    			i++;
    		}
    		addToTail(item);
    	}
    }
    
	//gen the key
    public int keyGen() {
    	int key;
    	int indexItem = head.data;
    	if(indexItem>=27) return tail.data;
    	else {
    		return (getItemAt(indexItem));
    	}
    	
    }
    

    
    //generate the keystream 
    public LinkedList keystream(String message, LinkedList l) {
    	LinkedList keylist = new LinkedList();
    	int key,j=0;
    	int uselessCount=0;
    	for (int i = 0; i < message.length(); i++) {
            if (!Character.isLetter(message.charAt(i))) {
            	uselessCount++;
                continue;
            }
        }
    	while(j<message.length()-uselessCount) {

            l.swapJokerA();
            System.out.println("S1: " + l);
            l.swapJokerB();
            System.out.println("S2: " + l);
            l = l.tripleCut();
            System.out.println("S3: " + l);
            l.countCut();
            System.out.println("S4: " + l);
            
            if(l.keyGen()==27||l.keyGen()==28) {
            	System.out.println("Joker: Key skipped");
				j--;
            }else {
            	System.out.println("Key " + (j + 1) + ":" + l.keyGen());
                keylist.addToTail( l.keyGen());
            }
            j++;
           
    	}
    	if(keylist.isEmpty()) {
    		System.out.println("There are no key can gen.");
    	}
    	
    	 return keylist;
    }
    
    public LinkedList invisibleStep(String message, LinkedList l) {			//gen a invisible step keys for decrypt and encrypt
    	LinkedList keylist = new LinkedList();
    	int key,j=0;
    	int uselessCount=0;
    	for (int i = 0; i < message.length(); i++) {
            if (!Character.isLetter(message.charAt(i))) {
            	uselessCount++;
                continue;
            }
        }
    	while(j<message.length()-uselessCount) {

            l.swapJokerA();
           
            l.swapJokerB();
           
            l = l.tripleCut();
           
            l.countCut();
           
            
            if(l.keyGen()==27||l.keyGen()==28) {
            	
				j--;
            }else {
            	
                keylist.addToTail( l.keyGen());
            }
            j++;
           
    	}
    	if(keylist.isEmpty()) {
    		System.out.println("There are no key can gen.");
    	}
    	
    	 return keylist;
    }
	
    public void decrypt(LinkedList keystream, String message) {
        String msg = "";
        LinkedList newKey = new LinkedList();
           for (int i = 0; i < message.length(); i++) {
               if (!Character.isLetter(message.charAt(i))) {
                   continue;
               }
               char plainText = Character.toUpperCase(message.charAt(i));
               int c = plainText - 'A' + 1;
               int key = keystream.removeFromHead();
               newKey.addToHead(key);	//for isempty checking
               int code = c - key;
               if (code <= 0) {
                   code += 26;
               }
               
               char cipher = (char) (code - 1 + 'A');
               msg += cipher;
               System.out.println(plainText + "\t" + c + "\t" + key + "\t" + code + "\t" + cipher);
           }
           	   if(!newKey.isEmpty()) {
           		 System.out.println("Decrypted message: " + msg);
           	   }
           	   else {
           		   System.out.print("No valid string that can decrypt");
           	   }                                    
   }
    
    public void encrypt(LinkedList keystream, String message) {
        String msg = "";
        LinkedList newKey = new LinkedList();
           for (int i = 0; i < message.length(); i++) {
               if (!Character.isLetter(message.charAt(i))) {
                   continue;
               }
               char plainText = Character.toUpperCase(message.charAt(i));
               int c = plainText - 'A' + 1;
               int key = keystream.removeFromHead();
               newKey.addToHead(key);   //for isempty checking
               int code = c + key;
               if (code >26) {
                   code -= 26;
               }
               
               char cipher = (char) (code - 1 + 'A');
               msg += cipher;
               System.out.println(plainText + "\t" + c + "\t" + key + "\t" + code + "\t" + cipher);
           }
           	   if(!newKey.isEmpty()) {
           		 System.out.println("Encrypted message: " + msg);
           	   }
           	   else {
           		   System.out.print("No valid string that can encrypt");
           	   }                                    
   }
    
    
	//get an item from input a index ( the range is 0-27)
	
	public int getItemAt(int n) {
		if (n < 0 || n >= count)
			throw new IndexOutOfBoundsException();
		int currentPos=0;
		ListNode current=head;
		while (currentPos < n) {
			current=current.next;
			currentPos++;
		}
		return current.data;
	}
	
	//get the card position of inputing a cardno
	
	public int getIndexOfItem(int cardNo) {
		int pos =0;
		for(ListNode current = head;current.next!= null&&current.data!=cardNo;current = current.next) {
			pos++;
				
		}
		return pos;
	}

	public int removeItemAt(int n) {
		if (n < 0 || n >= count)
			throw new IndexOutOfBoundsException();
		if (n==0) {
			return (removeFromHead());
		}
		
		int currentPos=0;
		ListNode current=head;
		while (currentPos < (n-1)) { // locate the node before the one to be removed
			current=current.next;
			currentPos++;
		}
		int item = current.next.data;
		current.next = current.next.next;
		count--;
		return item;
	}


	public void addItemAt(int item, int n) {
		if (isEmpty() || n==0) {
			addToHead(item);
			return;
		}
		if (n >= count) {
			addToTail(item);
			return;
		}

		int currentPos=0;
		ListNode current=head;
		while (currentPos < (n-1)) {  // locate the node before the insertion point
			current=current.next;
			currentPos++;
		}
		ListNode newNode = new ListNode(item);
		newNode.next = current.next;
		current.next = newNode;
		count++;
	}


	public String toString() {
		String s = "[ ";

		// traverse the list from head towards tail
		ListNode current = head;
		while (current != null) {
			s += current.data + " ";
			current = current.next;
		}
		return s + "]";
	}
}





