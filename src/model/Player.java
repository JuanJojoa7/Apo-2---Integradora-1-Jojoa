package model;
public class Player{

    private String icon;
    private int score;


    public Player(String symbol) {
        this.icon = symbol;

    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getScore() {return score;}

    public void setScore(int score) {this.score = score;}

}