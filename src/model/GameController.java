package model;
import java.util.Random;
public class GameController {

    Random rand = new Random();
    private double seconds;
    private boolean starter;
    private Thread timer;
    private Board board;
    boolean finishGame;

    public void inGame(int option, int player){

		switch(option){

			case 1-> diceRoll();

			case 2-> board.boardPrint(board.getSizeX(),1);
						
			default-> System.out.println("\nHas seleccionado una opcion invalida, intenta nuevamente.");
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
    
    public String createPlayer(char symbol){
        if(symbolPlayer(symbol)==false){
            Player newPlayer = new Player(0, symbol);
            if(board.getStart().getSavedNext()!=null){
                newPlayer.setPrevSaved(getLastPlayer(board.getStart().getSavedNext()));
                getLastPlayer(board.getStart().getSavedNext()).setSavedNext(newPlayer);
                return "Jugador creado";
            } else {
                board.getStart().setSavedNext(newPlayer);
                newPlayer.setPrevSaved(board.getStart());
                return "Jugador creado";
            }
        }else{
            return "wtf bro?";
        }
    }

    private Node getLastPlayer(Node current){
        if(current.getSavedNext()!=null){
            return getLastPlayer(current.getSavedNext());
        } else {
            return current;
        }
    }

    public int diceRoll(){
        Random rand = new Random();
        int upperbound = 6;
        int int_random = rand.nextInt(upperbound);
        if(int_random!=0){
            return int_random;
        } else {
            return diceRoll();
        }
    }

    public boolean symbolPlayer(char symbol){
        return symbolPlayer(symbol, board.getStart().getSavedNext() , 0);
    }

    public boolean symbolPlayer(char symbol, Node currentPlayer, int i){
        if(currentPlayer == null){
            return false;
        }
        if(i == 3){
            return false;
        }
        if(currentPlayer instanceof Player){
            if(((Player) currentPlayer).getIcon()==symbol){
                return true;
            }else{
                return symbolPlayer(symbol, currentPlayer.getSavedNext(), ++i);
            }
        } else {
            return false;
        }
    }


    public Node movePlayer(Node node, int steps){
       if(node == board.getStart() && steps <= 0){
            return node;
        }
        if(node == board.getEnd()){
            return node;
        }
        if(steps ==0){
           if(board.getLinkId() == null){
                return node;
            }else{
                if(node.getSteps() == 0){
                    return node;
                }else{
                    return movePlayer(node, node.getSteps());
                }
            }
        }
        
        if(steps > 0){
            return movePlayer((Node)node.getPrevious(), ++steps);
        }else{
            return movePlayer((Node)node.getNext(), --steps);
        }
        
    }

}