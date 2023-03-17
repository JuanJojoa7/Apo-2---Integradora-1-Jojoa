package model;
import java.util.Random;


public class GameController {

    Random rand = new Random();
    private double seconds;
    private boolean starter;
    private Thread timer;
    private Board board;
    private PlayerLST players;

    public void inGame(int option, int player){

    }

    public GameController(){
        this.starter = false;
        this.players = new PlayerLST();
        this.seconds = 0;


    }

    public void createBoard(int rows, int columns, int snakes, int ladders){
        this.board = new Board(rows, columns, snakes, ladders);
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
    
    public String createPlayer(String symbol){
        if(symbolPlayer(symbol.charAt(0),0)){
            Player newPlayer = new Player(symbol.charAt(0));
            newPlayer.setCurrentPosition(board.getHead());
            players.addPlayer(newPlayer);
            return "Jugador creado";
        }else{
            return "wtf bro?";
        }
    }

    public boolean symbolPlayer(char symbol, int i){
        String symbols = "!#$&@";
        if(i >= 5){
            return false;
        }else{
            if(symbols.charAt(i)==symbol){
                return !players.symbolPlayer(symbols.charAt(i));
            }else{
                return symbolPlayer(symbol, ++i);
            }
        }

    }


    
    
}