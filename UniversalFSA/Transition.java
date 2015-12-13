//Transition Object
public class Transition {
	//------------Object Attributes---------------
	public int currentState;
	public char input;
	public int nextState;
	
	//---------------Constructors------------------
	//Default constructor
	public Transition() {
		this.setCurrentState(0);;
		this.setInput(' ');
		this.setNextState(0);
	}
	//Constructor for a defined transition
	public Transition(int c, char i, int n) {
		this.setCurrentState(c);;
		this.setInput(i);
		this.setNextState(n);
	}
	//---------------Get Methods------------------
	public int getCurrentState(){
		return this.currentState;
	}
	public char getInput(){
		return this.input;
	}
	public int getNextState(){
		return this.nextState;
	}
	//---------------Set Methods------------------
	public boolean setCurrentState(int c){
		this.currentState = c;
		return true;
	}
	public boolean setInput(char i){
		this.input = i;
		return true;
	}
	public boolean setNextState(int n){
		this.nextState = n;
		return true;
	}
}
