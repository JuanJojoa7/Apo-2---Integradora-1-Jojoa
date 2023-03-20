package model;
import java.util.Random;

public class GameController {
    public static Ranking ranking = new Ranking();

    Random rand = new Random();
    private double seconds;
    private boolean starter;
    private Thread timer;
    private Board board;
    boolean finishGame;
    

    private Player player1;

    private Player player2;

    private Player player3;

    public void inGame(int option, int player, Player playerData){

		switch(option) {

            case 1:
                board.movePlayer(diceRoll(), playerData);
                break;
            case 2:
                board.boardPrint(board.getSizeX(),1);
                break;
            default:
                System.out.println("\nHas seleccionado una opcion invalida, intenta nuevamente.");
                break;
		}

    }

    public Player checkGameEnd(){
        if(board.getEnd().getPlayer01()!=null){
            return board.getEnd().getPlayer01();
        } else  {
            return null;
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
                    seconds=0.10;
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
        });
        timer.start();
    }

    public double calculateScore(){

        double calculation = (600-seconds)/6;
        return calculation ;
    }

    public void stopTimer(){
        starter = false;
        timer.interrupt();
    }

    
    public boolean createPlayer(String symbol, String name){
        if(symbolPlayer(symbol)==false){
            if(board.getStart().getPlayer01()==null){
                board.getStart().setPlayer01(new Player(symbol, name));
            } else if(board.getStart().getPlayer02()==null){
                board.getStart().setPlayer02(new Player(symbol, name));
            } else if(board.getStart().getPlayer03()==null){
                board.getStart().setPlayer03(new Player(symbol, name));
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
            System.out.println("\nSacaste: "+ int_random);
            System.out.println();
            return int_random;
        } else {
            return diceRoll();
        }
    }

    public boolean symbolPlayer(String symbol){
        return symbolPlayer(symbol, board.getStart());
    }

    public boolean symbolPlayer(String symbol, Node current){
        if(current == null){
            return false;
        }
        if(current.getPlayer01()!=null){
            if(current.getPlayer01().getIcon().equals(symbol)){
                return true;
            }
        }else if (current.getPlayer02()!=null){
            if (current.getPlayer02().getIcon().equals(symbol)){
                return true;
            }
        }else if (current.getPlayer03()!=null){
            if (current.getPlayer03().getIcon().equals(symbol)){
                return true;
            }
        }
        return false;
    }

    public void insert(Player player){
        ranking.insert(player);
        ranking.inorder();
        return;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getPlayer3() {
        return player3;
    }

    public void setPlayer3(Player player3) {
        this.player3 = player3;
    }
}