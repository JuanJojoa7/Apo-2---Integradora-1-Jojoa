package model; 

public class Node{

	private int id;
    private String display;
	private Node next;
	private Node previous;

	private String player01;

	private String player02;

	private String player03;

	public Node(int value) {
		super();
		this.id = value;
        this.display = "[" + value + "]" ;
		this.next = null;
		this.previous = null;
	}

	public String getDisplay() {return display;}



	public void setDisplay(String display) {this.display = display;}

	public int getId() {return id;}

	public void setId(int id) {this.id = id;}

	public Node getNext() {return next;}

	public void setNext(Node next) {this.next = next;}

	public Node getPrevious() {return previous;}

	public void setPrevious(Node previous) {this.previous = previous;}

	public String getPlayer01() {
		return player01;
	}

	public void setPlayer01(String player01) {
		this.player01 = player01;
	}


	public String getPlayer02() {
		return player02;
	}

	public void setPlayer02(String player02) {
		this.player02 = player02;
	}

	public String getPlayer03() {
		return player03;
	}

	public void setPlayer03(String player03) {
		this.player03 = player03;
	}

	@Override
	public String toString() {
		return "Node{" +
				"id=" + id +
				'}';
	}
}
