package model;

public class Ladders extends Node{
    private String displaySN;

    private Node connect;

    private boolean connected;

    public Ladders(int value) {
        super(value);
        this.displaySN = "[ ]";
        this.connected = false;
    }

    public Node getConnect() {return connect;}

    public void setConnect(Node connect) {this.connect = connect;}

    public String getDisplaySN() {return displaySN;}

    public void setDisplaySN(String displaySN) {this.displaySN = displaySN;}

    public boolean isConnected() {return connected;}

    public void setConnected(boolean connected) {this.connected = connected;}

}
