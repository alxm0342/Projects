public class JobList {
	private Job head;
	private int size;
	
	//Default constructor
	JobList () {
		//Creates the dummy head
		setHead(new Job());
		setSize(0);
	}
	
	//GET METHODS
	public Job getHead() {
		return head;
	}
	public int getSize() {
		return size;
	}

	//SET METHODS
	public void setHead(Job head) {
		this.head = head;
	}
	private void setSize(int newSize) {
		this.size = newSize;
	}
	
	//FUNCTIONAL METHODS
	//Add a new Node to the list
	public void add(Job newJob) {
      
      //Checks if list is empty, adds Node to the ends of the list
      if (this.head.getNext() != null) {
          Job tmp = this.head.getNext();
          while (tmp.getNext() != null) {
                  tmp = tmp.getNext();
              }
          tmp.setNext(newJob);
          this.size ++;
      }
      
      //Adds the Node directly after the dummy head of an empty list
      else {
          head.setNext(newJob);
          this.size ++;
      }
	}
	
	//Delete a Job from the list
	public void delete(Job s) {       
       //Checks for an empty list
       if (head.getNext() != null) {
           //Checks if the Node is in the list
           if (this.contains(s)) {
               //The size of the list is one
               if (this.size == 1){
                   head.setNext(null);
                   size --;
               }
               else {
                   Job tmp = head;
                   Job checkMe = head.getNext();
                   while (checkMe != null) {
                       if (checkMe.equals(s)) {
                           tmp.setNext(checkMe.getNext());
                           size --;
                       }
                       tmp = checkMe;
                       checkMe = checkMe.getNext();                        
                   }
               }
           }
       }
       else {
           System.out.println("The list is empty.");
       }
	}
	
	//Checks if a Job is in the list
	public boolean contains(Job s) {
      boolean isContained = false;
      
      if (this.head.getNext() != null) {
          Job tmp = this.head.getNext();
          
          //Traverses list and checks each Node for equality
          //Stops check at end of list or if Node is found
          while ((tmp != null) && (isContained == false)) {
              if (s.equals(tmp)){
                  isContained = true;
              }
              tmp = tmp.getNext();
          }
      }
      return isContained;
	}
	
	//Returns true is the list is empty
	public boolean isEmpty() {
		if (this.head.getNext() == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//Return a copy of this Job List
	public JobList copy() {
		JobList theCopy = new JobList();
		Job tmp = this.head.getNext();
		Job tmpCopy;
		while (tmp!=null) {
			tmpCopy = tmp.copy();
			theCopy.add(tmpCopy);
			tmp = tmp.getNext();
		}
		return theCopy;
	}
	
	//Return the contents of the string
   public String toString() {
       //The return string
       String theString = "[";
       //Temporary node to traverse the list
       Job tmp;
       
       //Checks if the list is null
       if (head.getNext() != null) {
           //Set the state to the first node after the dummy head
           tmp = head.getNext();
           //Traverse the list and print each element to the return string
           theString = theString + "{" + tmp.toString() + "}";
           for (int i=this.size-1; i>0; i--) {
               tmp = tmp.getNext();
               theString = theString + ", {" + tmp.toString() + "}";
           }
       }
       theString += "]";
       //Return the return String
       return (theString);
   }
}
