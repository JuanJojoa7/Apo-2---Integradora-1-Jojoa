package model;
import java.util.Random;
public class GameController {

    Random rand = new Random();
    private double seconds;
    private boolean starter;
    private Thread timer;
    private Board board;
    boolean finishGame;

    private String player1;

    private String player2;

    private String player3;

    public void inGame(int option, int player, String icon){

		switch(option) {

            case 1:
                board.movePlayer(diceRoll(), icon);
                break;
            case 2:
                board.boardPrint(board.getSizeX(),1);
                break;
            default:
                System.out.println("\nHas seleccionado una opcion invalida, intenta nuevamente.");
                break;
		}

    }

    public GameController(){
        this.starter = false;
        this.seconds = 0;
        this.finishGame = false;

    }

    public void createBoard(int rows, int columns, int snakes, int ladders){
        this.board = new Board(rows, columns, snakes, ladders);
    }

    public void printBoard(int columns){
        board.boardPrint(columns, 0);
    }

    public void Timer(){
        this.seconds = 600;
        starter = true;
        timer = new Thread(()->{
            while(starter){
                try{
                    Thread.sleep(10);
                    seconds=-0.010;
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        });
        timer.start();
    }

    public void stopTimer(){
        starter = false;
        timer.interrupt();
    }

    public boolean gameFinish(){
        return finishGame;
    }


    
    public boolean createPlayer(String symbol){
        if(symbolPlayer(symbol)==false){
            if(board.getStart().getPlayer01()==null){
                board.getStart().setPlayer01(symbol);
            } else if(board.getStart().getPlayer02()==null){
                board.getStart().setPlayer02(symbol);
            } else if(board.getStart().getPlayer03()==null){
                board.getStart().setPlayer03(symbol);
            }
            return true;
        }else{
            return false;
        }
    }

    public int diceRoll(){
        Random rand = new Random();
        int upperbound = 6;
        int int_random = rand.nextInt(upperbound);
        if(int_random!=0){
            System.out.println(int_random);
            return int_random;
        } else {
            return diceRoll();
        }
    }

    public boolean symbolPlayer(String symbol){
        return symbolPlayer(symbol, board.getStart() , 0);
    }

    public boolean symbolPlayer(String symbol, Node current, int i){
        if(current == null){
            return false;
        }
        if(current.getPlayer01()!=null){
            if(current.getPlayer01().equals(symbol)){
                return true;
            }
        }else if (current.getPlayer02()!=null){
            if (current.getPlayer02().equals(symbol)){
                return true;
            }
        }else if (current.getPlayer03()!=null){
            if (current.getPlayer03().equals(symbol)){
                return true;
            }
        }
        return false;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getPlayer3() {
        return player3;
    }

    public void setPlayer3(String player3) {
        this.player3 = player3;
    }
}