package model;
public class Player extends Node {

    private char icon;
    private int score;

    private Node savedNext;

    private Node prevSaved;

    public Player(int value, char symbol) {
        super(value);
        this.icon = symbol;

    }

    public char getIcon() {return icon;}

    public void setIcon(char icon) {this.icon = icon;}

    public int getScore() {return score;}

    public void setScore(int score) {this.score = score;}

    public Node getPrevSaved() {return prevSaved;}

    public void setPrevSaved(Node prevSaved) {this.prevSaved = prevSaved;}

    public Node getSavedNext() {return savedNext;}

    public void setSavedNext(Node savedNext) {this.savedNext = savedNext;}
}