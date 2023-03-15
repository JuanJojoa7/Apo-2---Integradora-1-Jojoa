package model;

public class Slot extends Node {
    private int value;
    private int moves;
    private String idlink;


    public Slot(int value) {
        super(value);
        this.value = value;
        this.moves = 0;
        this.idlink = null;
        
    }


    public int getValue() {
        return value;
    }


    public void setValue(int value) {
        this.value = value;
    }


    public int getMoves() {
        return moves;
    }


    public void setMoves(int moves) {
        this.moves = moves;
    }


    public String getIdlink() {
        return idlink;
    }


    public void setIdlink(String idlink) {
        this.idlink = idlink;
    }
    

}
