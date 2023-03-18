<<<<<<< HEAD
package ui;
import java.util.Scanner;
import model.*;


public class Main {
    private Scanner reader;
	private GameController controller;
	

	public Main() {
		reader = new Scanner(System.in); 
        controller = new GameController();
	}

	public static void main(String[] args) {
			Main main = new Main();

			int option = -1; 
			do{
				option = main.getOptionShowMenu(); 
				main.executeOption(option);

			}while(option != 0);

		}

	public int getOptionShowMenu(){
			int option = 0; 
			printMenu();

			option = validateIntegerOption(); 

			return option; 
	}

	public void printMenu(){
			System.out.print(
                "\n<<<<< Welcome to the Game >>>>>\n"+	
                "1. Jugar\n"+
				"0. Exit. \n"+
				"Opcion: ");  
	}

		public void executeOption(int option){

			switch(option){
				case 1-> init();

				case 0-> System.out.print("\nHasta luego, esperamos verte nuevamente.");
						
				default-> System.out.print("\nLo sentimos has introducido una opcion invalida, intenta nuevamente.");
			}
		}
	
	/**
	 * @param: Option that gives the user
	 * @return: Validates the option and if the user gives a number that doesnt exist will give (Invalid Option) or even with letters (Invalid Option)
	 */	
	public int validateIntegerOption(){
		int option = 0; 

		if(reader.hasNextInt()){
			option = reader.nextInt(); 
		}
		else{
			reader.nextLine(); 
			option = -1; 
		}

		return option; 
	}

	public int validateIntegerOption2(){
		int option = 0; 

		if(reader.hasNextInt()){
			option = reader.nextInt(); 
		}
		else{
			reader.nextLine(); 
			option = -1; 
		}

		return option; 
	}

	public void init(){
		System.out.print("\nDime que la cantidad de filas que deseas para el tablero: ");
		int rows = reader.nextInt();
		reader.nextLine();

		System.out.print("\nDime la cantidad de columnas que deseas para el tablero: ");
		int columns = reader.nextInt();
		reader.nextLine();

		System.out.print("\nDime cuantos toboganes deseas para este juego: ");
		int snakes = reader.nextInt();
		reader.nextLine();

		System.out.print("\nDime cuantas escaleras deseas: ");
		int ladders = reader.nextInt();
		reader.nextLine();

		System.out.print("\nCuantos jugadores van a haber en el juego: ");
		int player = reader.nextInt();

		controller.createBoard(rows, columns, snakes, ladders);
		createPlayers(player,0);
		controller.printBoard(columns);

		playMenu(0, player);
	}

	public int playMenu(int player, int numPlayers){
		if(player>numPlayers-1){
			player=0;
		}
		System.out.print("\nJugador"); 
		System.out.print("\nElije una opcion:\n"+
				"1. Tirar dado\n"+
				"2. Ver escaleras y serpientes\n"+
				"Opcion: ");
		
		int option2 = reader.nextInt();

		controller.inGame(option2, player);
		if(option2==2){
			return playMenu(player, numPlayers);
		}
		return playMenu(player+1, numPlayers);
	}

	public void createPlayers(int players, int counter){
		if(counter < players){
			System.out.print("\nElige un simbolo que te represente jugador "+(counter+1));
			System.out.print("\nSimbolos Validos: !#$&@");
			System.out.print("\nOpcion: ");
			char icon = reader.next().charAt(0);
			if(controller.createPlayer(icon).equals("Jugador creado")){
				createPlayers(players, ++counter);
			}else{
				System.out.println("\nLo sentimos, has introducido un simbolo invalido o ya otro jugador lo esta usando, intenta nuevamente.");
				createPlayers(players, counter);
			}
		}
	}
=======
package ui;
import java.util.Scanner;
import model.*;


public class Main {
    private Scanner reader;
	private GameController controller;
	

	public Main() {
		reader = new Scanner(System.in); 
        controller = new GameController();
	}

	public static void main(String[] args) {
			Main main = new Main();

			int option = -1; 
			do{
				option = main.getOptionShowMenu(); 
				main.executeOption(option);

			}while(option != 0);

		}

	public int getOptionShowMenu(){
			int option = 0; 
			printMenu();

			option = validateIntegerOption(); 

			return option; 
	}

	public void printMenu(){
			System.out.print(
                "\n<<<<< Welcome to the Game >>>>>\n"+	
                "1. Jugar\n"+
				"0. Exit. \n"+
				"Opcion: ");  
	}

		public void executeOption(int option){

			switch(option){
				case 1-> init();

				case 0-> System.out.print("\nHasta luego, esperamos verte nuevamente.");
						
				default-> System.out.print("\nLo sentimos has introducido una opcion invalida, intenta nuevamente.");
			}
		}
	
	/**
	 * @param: Option that gives the user
	 * @return: Validates the option and if the user gives a number that doesnt exist will give (Invalid Option) or even with letters (Invalid Option)
	 */	
	public int validateIntegerOption(){
		int option = 0; 

		if(reader.hasNextInt()){
			option = reader.nextInt(); 
		}
		else{
			reader.nextLine(); 
			option = -1; 
		}

		return option; 
	}

	public void init(){
		System.out.print("\nDime que la cantidad de filas que deseas para el tablero: ");
		int rows = reader.nextInt();
		reader.nextLine();

		System.out.print("\nDime la cantidad de columnas que deseas para el tablero: ");
		int columns = reader.nextInt();
		reader.nextLine();

		System.out.print("\nDime cuantos toboganes deseas para este juego: ");
		int snakes = reader.nextInt();
		reader.nextLine();

		System.out.print("\nDime cuantas escaleras deseas: ");
		int ladders = reader.nextInt();
		reader.nextLine();

		System.out.print("\nCuantos jugadores van a haber en el juego: ");
		int player = reader.nextInt();

		controller.createBoard(rows, columns, snakes, ladders);
		createPlayers(player,0);
		controller.printBoard(columns);

		playMenu(0, player);
	}

	public int playMenu(int player, int numPlayers){
		if(player>numPlayers-1){
			player=0;
		}
		System.out.print("\nJugador"); 
		System.out.print("\nElije una opcion:\n"+
				"1. Tirar dado\n"+
				"2. Ver escaleras y serpientes\n"+
				"Opcion: ");
		
		int option = reader.nextInt();
		controller.inGame(option, player);
		if(option==2){
			return playMenu(player, numPlayers);
		}
		return playMenu(player+1, numPlayers);
	}

	public void createPlayers(int players, int counter){
		if(counter < players){
			System.out.print("\nElige un simbolo que te represente jugador "+(counter+1));
			System.out.print("\nSimbolos Validos: !#$&@");
			System.out.print("\nOpcion: ");
			char icon = reader.next().charAt(0);
			if(controller.createPlayer(icon).equals("Jugador creado")){
				createPlayers(players, ++counter);
			}else{
				System.out.println("\nLo sentimos, has introducido un simbolo invalido o ya otro jugador lo esta usando, intenta nuevamente.");
				createPlayers(players, counter);
			}
		}
	}

>>>>>>> 3797bd1fd08560a55cde550f8a57631559720808
}