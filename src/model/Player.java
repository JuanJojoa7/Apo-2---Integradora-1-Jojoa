package model;
public class Player{

    private String icon;

    private String name;
    private int score;


    public Player(String symbol, String name) {
        this.icon = symbol;
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getScore() {return score;}

    public void setScore(int score) {this.score = score;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}