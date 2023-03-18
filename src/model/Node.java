package model; 

public class Node{

	private int id;
    private String display;

	private Node savedNext;
	private Node next;
	private Node previous;
	private int steps;

	public Node(int value) {
		super();
		this.id = value;
        this.display = "[" + value + "]" ;
		this.next = null;
		this.previous = null;
		this.savedNext = null;
	}

	public String getDisplay() {return display;}



	public void setDisplay(String display) {this.display = display;}

	public int getId() {return id;}

	public void setId(int id) {this.id = id;}

	public Node getNext() {return next;}

	public void setNext(Node next) {this.next = next;}

	public Node getPrevious() {return previous;}

	public void setPrevious(Node previous) {this.previous = previous;}

	public Node getSavedNext() {return savedNext;}

	public void setSavedNext(Node savedNext) {this.savedNext = savedNext;}

	public int getSteps() {return steps;}

	public void setSteps(int steps) {this.steps = steps;}

	@Override
	public String toString(){
		return "My value is: " + id; 
	}

	

}
