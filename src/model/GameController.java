package model;
import java.util.Random;


public class GameController {

    Random rand = new Random();
    int diceRoll = rand.nextInt(6)+1;
    private double seconds;
    private boolean starter;
    private Thread timer;
    private Board board;
    private PlayerLST players;

    public void inGame(int option, int player){

    }

    public GameController(){
        this.starter = false;


    }

    public void createBoard(int rows, int columns, int snakes, int ladders){
        Board board = new Board(rows, columns, snakes, ladders);

        return;
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
            return "\nJugador creado con exito";
        }else{
            return "wtf bro?";
        }
    }

    public boolean symbolPlayer(char symbol, int i){
        String symbols = "!#$&@";
        if(i == 9){
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