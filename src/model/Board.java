package model;

import java.util.Random;


public class Board {
    
    private int sizeX;

    private int sizeY;

    private int snakeNum;

    private int laddersNum;

    private Node start;

    private Node end;

    private int LID;

    private String SList;

    private int LadderPos;

    private char Letter;

    private String linkId;


   private positionListS listS = new positionListS();

   private positionListL listL = new positionListL();

    Random rand = new Random();


    public Board(int rows, int columnas, int snakes, int ladders) {
        this.sizeX = columnas;
        this.sizeY = rows;
        this.snakeNum = snakes;
        this.laddersNum = ladders;
        this.LID = 1;
        this.LadderPos=0;
        this.SList = "abcdefghijklmnopqrstuvwxyz";
        this.start = null;
        createSPosition((snakes*2),(columnas*rows), 0);
        createLPosition((ladders*2), (columnas*rows), 0);
        createBoard((columnas*rows), 1);
        connectNodes((snakes*2), (ladders*2));
        boardPrint(columnas, 0);
    }

    private void createBoard(int boardLimit, int boardNodeCounter) {
        if(boardNodeCounter<=boardLimit){
            if(listS.searchNode(boardNodeCounter)==true){
                Snakes snake = new Snakes(boardNodeCounter);
                addNode(snake);
            } else if(listL.searchNode(boardNodeCounter)==true){
                Ladders ladder = new Ladders(boardNodeCounter);
                addNode(ladder);
            } else {
                Node node = new Node(boardNodeCounter);
                addNode(node);
            }
            createBoard(boardLimit, boardNodeCounter+1);
        }
        return;
    }

    private void addNode(Node node){
        if(node instanceof Ladders || node instanceof Snakes || node instanceof Node){
            if(start==null && end==null){
                start = node;
                end = node;
            } else{
                end.setNext(node);
                node.setPrevious(end);
                end = node;
            }
        }
    }

    private void createSPosition(int snake, int positions, int counter){
        if(snake==counter){
            return;
        }
        int upperbound = positions;
        int int_random = rand.nextInt(upperbound);
        if(int_random!=0 || int_random!=1 || int_random!=positions-1){
            if(listS.getHead()==null){
                if(int_random == 0 || int_random == 1){
                    createSPosition(snake, positions, counter);
                } else {
                    System.out.println(int_random);
                    listS.addLast(new Snakes(int_random));
                    createSPosition(snake, positions, counter+1);
                }
                return;
            }else {
                if(listS.searchNode(int_random)==false){
                    System.out.println(int_random);
                    listS.addLast(new Snakes(int_random));
                    createSPosition(snake, positions, counter+1);
                }else{
                    createSPosition(snake, positions, counter);
                }
                return;
            }
        }else {
            createSPosition(snake,positions,counter);
            return;
        }

    }

    private void createLPosition(int ladder, int positions, int counter){
        if(ladder==counter){
            return;
        }
        int upperbound = positions;
        int int_random = rand.nextInt(upperbound);
        if(int_random!=0 || int_random!=1 || int_random!=positions-1) {
            if (listS.searchNode(int_random) == false) {
                if (listL.searchNode(int_random) == false) {
                    if(int_random == 0 || int_random == 1){
                        createLPosition(ladder, positions, counter);
                    }else{
                        System.out.println(int_random);
                        listL.addLast(new Ladders(int_random));
                        createLPosition(ladder, positions, counter+1);
                    }
                }else {
                    createLPosition(ladder,positions,counter);
                }
            } else {
                createLPosition(ladder,positions,counter);
                return;
            }
        } else {
            createLPosition(ladder,positions,counter);
            return;
        }
    }

    public void boardPrint(int columns, int answer){
        switch (answer){
            case 0:
                if(columns%2==0){
                    boardPrint(end, columns, columns, "", false);
                } else {
                    boardPrint(end, columns, columns, "", true);
                }
                break;
            case 1:
                if(columns%2==0){
                    boardPrintSN(end, columns, columns, "", false);
                } else {
                    boardPrintSN(end, columns, columns, "", true);
                }
                break;
        }
        return;
    }
    private void boardPrint(Node current, int counter, int columns, String print, boolean condition){

        counter-=1;
        if(counter<0){
            System.out.println(print);
            print="";
            counter=columns-1;
        }
        if(current==null){
            return;
        }
        if(counter==columns-1){
            condition=!condition;
        }

        if(condition==false){
            String s = "["+current.getId()+getSavedString(current.getSavedNext(), "")+"]";
            print = s +" "+print;
        } else {
            String s = "["+current.getId()+getSavedString(current.getSavedNext(), "")+"]";
            print += s+" ";
        }

        boardPrint(current.getPrevious(), counter, columns, print, condition);
        if(current==start){
            return;
        }
    }

    private void boardPrintSN(Node current, int counter, int columns, String print, boolean condition){

        counter-=1;
        if(counter<0){
            System.out.println(print);
            print="";
            counter=columns-1;
        }
        if(current==null){
            return;
        }
        if(counter==columns-1){
            condition=!condition;
        }

        if(condition==false){
            if(current instanceof Snakes){
                print = ((Snakes) current).getDisplaySN()+" "+print;
            } else if (current instanceof Ladders){
                print = ((Ladders) current).getDisplaySN()+" "+print;
            } else {
                print += "[ ]" + " ";
            }
        } else {
            if(current instanceof Snakes){
                print += ((Snakes) current).getDisplaySN()+" ";
            } else if (current instanceof Ladders){
                print += ((Ladders) current).getDisplaySN()+" ";
            } else {
                print += "[ ]" + " ";
            }
        }

        boardPrintSN(current.getPrevious(), counter, columns, print, condition);
        if(current==start){
            return;
        }
    }

    private String getSavedString(Node current, String msg){
        if(current==null){
            return msg;
        } else {
            if(current instanceof Player){
                msg += ((Player) current).getIcon();
                return getSavedString(current.getSavedNext(), msg);
            } else {
                return msg;
            }
        }
    }


    private void connectNodes(int snakes, int ladders){
        findNodeS(end, snakes);
        findNodeL(start, ladders);
    }
    private void findNodeS(Node current, int amount){
        if(amount==0){
            return;
        }
        if(current==null){
            return;
        }
        if(this.start == null && this.end == null){
            return;
        }
        if(current instanceof Snakes){
            if(((Snakes) current).isConnected()==false){
                Letter = SList.charAt(LadderPos);
                ((Snakes) current).setConnect(findNodeS2(current.getPrevious(), random(amount+1), 1));
                ((Snakes) current).setConnected(true);
                ((Snakes) current).setDisplaySN("["+ Letter +"]");
                LadderPos+=1;
                findNodeS(current.getPrevious(), amount-2);
            }
        }
        findNodeS(current.getPrevious(), amount);
        return;
    }

    private void findNodeL(Node current, int amount){
        if(amount==0){
            return;
        }
        if(current==null){
            return;
        }
        if(this.start == null && this.end == null){
            return;
        }
        if(current instanceof Ladders){
            if(((Ladders) current).isConnected()==false){
                ((Ladders) current).setConnect(findNodeL2(current.getNext(), random(amount+1), 1));
                ((Ladders) current).setConnected(true);
                ((Ladders) current).setDisplaySN("["+LID+"]");
                LID+=1;
                findNodeL(current.getNext(), amount-2);
            }
        }
        findNodeL(current.getNext(), amount);
        return;
    }

    private int random(int amount){
        int upperbound = amount-1;
        int int_random = rand.nextInt(upperbound);
        if(int_random != 0){
            return int_random;
        }
        return random(amount);
    }

    private Node findNodeS2(Node current, int snake, int counter){
        if(current==null){
            return null;
        }
        if(this.start == null && this.end == null){
            return null;
        }
        if(current instanceof Snakes){
            if(((Snakes) current).isConnected()==false){
                if(counter == snake){
                    ((Snakes) current).setConnected(true);
                    ((Snakes) current).setDisplaySN("["+Letter+"]");
                    return ((Snakes) current);
                } else{
                    return findNodeS2(current.getPrevious(), snake, counter+1);
                }
            }
        }
        return findNodeS2(current.getPrevious(), snake, counter);
    }

    private Node findNodeL2(Node current, int ladder, int counter){
        if(current==null){
            return null;
        }
        if(this.start == null && this.end == null){
            return null;
        }
        if(current instanceof Ladders){
            if(((Ladders) current).isConnected()==false){
                if(counter == ladder){
                    ((Ladders) current).setConnected(true);
                    ((Ladders) current).setDisplaySN("["+LID+"]");
                    return ((Ladders) current);
                }
                return findNodeL2(current.getNext(), ladder, counter+1);
            }
        }
        return findNodeL2(current.getNext(), ladder, counter);
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public int getSnakeNum() {
        return snakeNum;
    }

    public void setSnakeNum(int snakeNum) {
        this.snakeNum = snakeNum;
    }

    public int getLaddersNum() {
        return laddersNum;
    }

    public void setLaddersNum(int laddersNum) {
        this.laddersNum = laddersNum;
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getEnd() {
        return end;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

    public positionListS getListS() {
        return listS;
    }

    public void setListS(positionListS listS) {
        this.listS = listS;
    }

    public positionListL getListL() {
        return listL;
    }

    public void setListL(positionListL listL) {
        this.listL = listL;
    }

    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }

    public int getLID() {
        return LID;
    }

    public void setLID(int lID) {
        LID = lID;
    }

    public String getSList() {
        return SList;
    }

    public void setSList(String sList) {
        SList = sList;
    }

    public int getLadderPos() {
        return LadderPos;
    }

    public void setLadderPos(int ladderPos) {
        LadderPos = ladderPos;
    }

    public char getLetter() {
        return Letter;
    }

    public void setLetter(char letter) {
        Letter = letter;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }
}