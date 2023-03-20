package ui;
import java.util.Scanner;
import model.*;
import java.time.LocalTime;


public class Main {
    private Scanner reader;
	private GameController controller;
	
	

	public Main() {
		reader = new Scanner(System.in); 
        controller = new GameController();
	}

	public static void main(String[] args) throws InterruptedException {
			Main main = new Main();

			int option = -1; 
			do{
				option = main.getOptionShowMenu(); 
				main.executeOption(option);

			}while(option != 0);

		}

	public int getOptionShowMenu() throws InterruptedException{
			int option = 0; 
			printMenu();

			option = validateIntegerOption(); 

			return option; 
	}

	public void printMenu() throws InterruptedException{

		String art = """
			███████╗███╗   ██╗ █████╗ ██╗  ██╗███████╗     █████╗ ███╗   ██╗██████╗     ██╗      █████╗ ██████╗ ██████╗ ███████╗██████╗ ███████╗
			██╔════╝████╗  ██║██╔══██╗██║ ██╔╝██╔════╝    ██╔══██╗████╗  ██║██╔══██╗    ██║     ██╔══██╗██╔══██╗██╔══██╗██╔════╝██╔══██╗██╔════╝
			███████╗██╔██╗ ██║███████║█████╔╝ █████╗      ███████║██╔██╗ ██║██║  ██║    ██║     ███████║██║  ██║██║  ██║█████╗  ██████╔╝███████╗
			╚════██║██║╚██╗██║██╔══██║██╔═██╗ ██╔══╝      ██╔══██║██║╚██╗██║██║  ██║    ██║     ██╔══██║██║  ██║██║  ██║██╔══╝  ██╔══██╗╚════██║
			███████║██║ ╚████║██║  ██║██║  ██╗███████╗    ██║  ██║██║ ╚████║██████╔╝    ███████╗██║  ██║██████╔╝██████╔╝███████╗██║  ██║███████║
			╚══════╝╚═╝  ╚═══╝╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝    ╚═╝  ╚═╝╚═╝  ╚═══╝╚═════╝     ╚══════╝╚═╝  ╚═╝╚═════╝ ╚═════╝ ╚══════╝╚═╝  ╚═╝╚══════╝																																																	   
				""";

			for(int i = 0; i < art.length(); i++){
				System.out.print(art.charAt(i));
			}	

			System.out.print(
                "\n<<<<<Bienvenido al juego de serpientes y escaleras >>>>>\n"+	
                "1. Jugar\n"+
				"0. Salir. \n"+
				"Opcion: ");  
	}

	public void executeOption(int option){

		switch(option){
			case 1:

				System.out.print("\nDigita la cantidad de filas que deseas para el tablero: ");
				int rows = reader.nextInt();
				reader.nextLine();

				if(rows == 0){
					System.out.println("\nLo sentimos, no puedes crear un tablero con 0 filas, intenta nuevamente.\n");

				}else if(rows > 50){
					System.out.println("\nLo sentimos, el maximo de filas es 50, intenta nuevamente.\n");

				}else{

					System.out.print("\nDigita la cantidad de columnas que deseas para el tablero: ");
					int columns = reader.nextInt();
					reader.nextLine();

					if(columns == 0){
						System.out.println("\nLo sentimos, no puedes crear un tablero con 0 columnas, intenta nuevamente.\n");

					}else if(columns > 50){
						System.out.println("\nLo sentimos, el maximo de columnas es 50, intenta nuevamente.\n");

					}else{

						System.out.print("\nDigita cuantos toboganes deseas para este juego: ");
						int snakes = reader.nextInt();
						reader.nextLine();
				
						System.out.print("\nDigita cuantas escaleras deseas: ");
						int ladders = reader.nextInt();
						reader.nextLine();
				
						System.out.print("\nCuantos jugadores van a haber en el juego: ");
						int player = reader.nextInt();

						if(player > 3){
							System.out.println("\nLo sentimos, el juego tiene un limite de 3 jugadores, intenta nuevamente.\n");

						}else{

							controller.createBoard(rows, columns, snakes, ladders);
							createPlayers(player,0);
							controller.printBoard(columns);
							controller.Timer();
							LocalTime horaActual = LocalTime.now();


							// playMenu plays the game with the number of players and returns the winner which we save in a variable for
							// future use
							Player winner = playMenu(0, player);
							winner.setScore(controller.calculateScore());
							controller.insert(winner);
						}
					}
				}
		
				break;

			case 0: 

				System.out.print("\nHasta luego, esperamos verte nuevamente.");

				break;
						
			default: 

				System.out.print("\nLo sentimos has introducido una opcion invalida, intenta nuevamente.");

				break;
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

	public Player playMenu(int player, int numPlayers){
		Player playerData = new Player("","");
		if(player>numPlayers-1){
			player=0;
		}
		switch(player) {
			case 0:
				playerData = controller.getPlayer1();
				break;
			case 1:
				playerData = controller.getPlayer2();
				break;
			case 2:
				playerData = controller.getPlayer3();
				break;
		}
		System.out.print("\nJugador, elije alguna de las siguientes opciones: "); 
		System.out.print("\nElije una opcion:\n"+
				"1. Tirar dado\n"+
				"2. Ver escaleras y serpientes\n"+
				"Opcion: ");
		
		int option = reader.nextInt();

		controller.inGame(option, player, playerData);

		if (controller.checkGameEnd()!=null) {
			return controller.checkGameEnd();
		}

		if(option==1){
			return playMenu(player+1, numPlayers);
		} else {
			return playMenu(player, numPlayers);
		}

		
	}

	public void createPlayers(int players, int counter){
		if(counter < players){
			System.out.print("\nElige un simbolo que te represente jugador " +(counter+1));
			System.out.print("\nSimbolos Validos: !#$&@");
			System.out.print("\nOpcion: ");
			String icon = reader.next();
			System.out.print("\nDigita tu nombre: ");
			String name = reader.next();
			if(controller.createPlayer(icon, name)==true){
				switch (counter){
					case 0:
						controller.setPlayer1(new Player(icon, name));
					case 1:
						controller.setPlayer2(new Player(icon, name));
					case 2:
						controller.setPlayer3(new Player(icon, name));
				}
				createPlayers(players, ++counter);
			}else{
				System.out.println("\nLo sentimos, has introducido un simbolo invalido o ya otro jugador lo esta usando, intenta nuevamente.");
				createPlayers(players, counter);
			}
		}
	}
}