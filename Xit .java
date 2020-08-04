import java.io.*;
import java.util.concurrent.TimeUnit;

public class Xit {
	static int TIME = 2500;
	//Used to monitor the different loops
	static String play = null; // monitors the entire game loop
	static String menu = null; // monitors the menu loop
	static String name = null; //player name
	static String avatar [] = new String [7];
	static int decision = 0;
	static String decisionStr=null;
	static int love = 0;
	static int coins =0;
	static int health = 100;
	static int speed = 0;
	static int strength = 0;
	static int wskill = 0;
	static int fifthMemb = 0;
	static int pvpKey =0;
	static String start = null;
	static String text = null;
	static int x=0;
	static int y=0;
	static int tournament =0;
	static boolean valid;
	static String invSlotName [] = new String [5];
	static int invSlotQuant [] = new int [5];
	static int storeKey =1;
	
	static boolean valid1 = false;
	static boolean valid2=false;
	static boolean valid3=false;
	
	static String journalName[]=new String [5];
	static String journalClassType[]=new String [5];
	static int h=0;
	
	public static void main(String[] args) throws IOException, InterruptedException{
	BufferedReader in;
	in = new BufferedReader (new InputStreamReader (System.in));
	
		do {
			/*These variables change throughout the game
			 * Resetting variables to initial amount at start of game
			 */
			health =100;
			for (int k=2;k<5;k++) {
				invSlotName[k]="empty";
				invSlotQuant[k]=0;
			}
			speed=0;
			strength=0;
			wskill=0;
			coins =0;
			love =0;
			fifthMemb=0;
			pvpKey=0;
			tournament =0;
			storeKey=1;
			invSlotName[0]="Twin Daggers";
			invSlotQuant[0]=1;		
			invSlotName[1]="Journal";
			invSlotQuant[1] =1;
			
			/*Main Menu loop
			 * Loops through first interactive screen user sees
			 */
			do {
				menu = menu(menu);
				switch (menu) {
				case ("1"):
					rules();
					break;
				case ("2"):
					credits();
					break;	
				}
			}while(menu.compareTo("3")!=0);
			
			cutscene1();
			name = in.readLine();
			avatar[0] = eyeColour();
			avatar[1] = hairColour();
			avatar[2] = hairStyle();
			avatar[3] = makeup1();
			avatar[4] = height();
			avatar[5] = build();
			avatar[6] = clothing1();
			saveAvatarProfile();
			
			cutscene2();
			
			if (decision ==1) {
				decision = 0;
				love = love +2;
				ch1dc1();
				if (decision ==1) {
					decision = 0;
					ch2dc1();
				}else {
					decision = 0;
					ch2dc2();
				}
			}else {
				decision = 0;
				ch1dc2();
			}
			
			cutscene3();
			
			if (love>2) {
				l1p1();
			}else {
				l1p2();
			}
			x=1;
			y=19;
			cutscenes(); //Cutscene 4
			avatarProfile();
			x=20;
			y=22;
			cutscenes(); //Cutscene 5
			mapPvP();
			x=23;
			y=25;
			cutscenes(); //Cutscene 6
			inventory();
			x=26;
			y=61;
			cutscenes();
			decision= decision();
			do {
				if (decision == 1) {
					fifthMemb=1;					
				} else {
					fallholt1();
					decision= decision();					
				}
			} while (fifthMemb==0);
			x=62;
			y=110;
			cutscenes();
			decision= decision();			
			do {
				if (decision ==1) {
					ch3dc1(); 
					decision= decision();
				}else {
					pvpKey = 1;
				}
			}while (pvpKey==0);
			x=111;
			y=170;
			cutscenes();
			decision= decision();
			if (decision==1) {
				roomCami();
			}else {
				roomJovi();
			}
			mapFallholt();
			x=171;
			y=193;
			cutscenes();							//EVERYTHING WORKS UNTIL HERE
			do {
				int input;
				pvpmenu();
				input = Integer.valueOf(in.readLine()).intValue();
				switch (input) {
				case 1:
					avatarProfile();
					break;
				case 2:
					inventory();
					break;
				case 3:
					int menu=0;
					do {
						menu = journalMenu();
						switch(menu) {
						case 1:
							//allows user to enter or add squad members
							enterSquad();
							break;
						case 2:
							//allows user to modify an existing member
							modifySquad();
							break;
						case 3:
							//allows user to delete an existing member
							deleteSquad();
							break;
						case 4:
							//allows user to display all the shows
							displayAllSquad();
							break;
						}	
					}while (menu != 5); //Exits program if 8 is entered
					System.out.println("Exited Journal.");
					break;
				case 4:
					market();
					break;
				case 5:
					speedTraining();
					break;
				case 6:
					strengthTraining();
					break;
				case 7:
					wskillTraining();
					break;
				case 8:
					if ((speed>=20)&&(strength>=20)&&(wskill>=20)&&(health==100)&&(storeKey==0)) {
						preTournamentScene();
						tournamentGame();
						tournament =1;
					}else {
						System.out.println("You do not meet the minimum requirements. Must have atleast... \n\t>>Weapon Skill: 20 \n\t>>Strength: 20 \n\t>>Speed: 20 \n\t>>Health: 100\n\t>>Tournament Access Key");
						System.out.println("\nYou currently have: \n\t>>Weapon Skill: "+wskill+"\n\t>>Strength: "+strength+"\n\t>>Speed: "+speed+"\n\t>>Health:"+health);
					}
					break;
				default:
					System.out.println("ERROR. Enter a valid answer.");
					valid = false;
				}	
			} while ((tournament == 0)&&(health>0));
			
			if (health>0) {
				winGameCutscene();				
				if (love>4) {
					specialEnding();
				}
				winGame();
				System.out.println("Thanks for playing :)");
			}else {
				gameOver();
			}
			play = playAgain(play); //Asks user if they want to play the game again
		}while((play.compareToIgnoreCase("no")!=0)&&(health>0));
		
	}
public static void cutscenes() throws IOException, InterruptedException{
  //variable to keep track of blank lines
  	String line;
  	BufferedReader in;
  	//counter
  	in = new BufferedReader(new FileReader("cutscenes.txt"));
  	for (int z=0;z<(x-1);z++) {
  		line=in.readLine();
  	}
  	for (int i=x;i<y;i++) {
  		line = in.readLine();
  		Thread.sleep(TIME);
  		System.out.println(line);
  	}

}

public static void winGame() {
	System.out.println("\n\n\n                                                                                                                                 \r\n" + 
			"      ***** *    **         * ***         ***** *    **           ***** *    **   ***            *****  *      ***** *     **    \r\n" + 
			"   ******  *  *****       *  ****      ******  *  *****        ******  *  *****    ***        ******  *     ******  **    **** * \r\n" + 
			"  **   *  *     *****    *  *  ***    **   *  *     *****     **   *  *     *****   ***      **   *  *     **   *  * **    ****  \r\n" + 
			" *    *  **     * **    *  **   ***  *    *  **     * **     *    *  **     * **      **    *    *  *     *    *  *  **    * *   \r\n" + 
			"     *  ***     *      *  ***    ***     *  ***     *            *  ***     *         **        *  *          *  *    **   *     \r\n" + 
			"    **   **     *     **   **     **    **   **     *           **   **     *         **       ** **         ** **    **   *     \r\n" + 
			"    **   **     *     **   **     **    **   **     *           **   **     *         **       ** **         ** **     **  *     \r\n" + 
			"    **   **     *     **   **     **    **   **     *           **   **     *         **     **** **         ** **     **  *     \r\n" + 
			"    **   **     *     **   **     **    **   **     *           **   **     *         **    * *** **         ** **      ** *     \r\n" + 
			"    **   **     *     **   **     **    **   **     *           **   **     *         **       ** **         ** **      ** *     \r\n" + 
			"     **  **     *      **  **     **     **  **     *            **  **     *         **  **   ** **         *  **       ***     \r\n" + 
			"      ** *      *       ** *      *       ** *      *             ** *      *         *  ***   *  *             *        ***     \r\n" + 
			"       ***      *        ***     *         ***      *              ***      ***      *    ***    *          ****          **     \r\n" + 
			"        *********         *******           ********                ******** ********      ******          *  *****              \r\n" + 
			"          **** ***          ***               ****                    ****     ****          ***          *     **               \r\n" + 
			"                ***                                                                                       *                      \r\n" + 
			"    ********     ***                                                                                       **                    \r\n" + 
			"  *************  **                                                                                                              \r\n" + 
			" *           ****                                                                                                                \r\n" + 
			"                                                                                                                                 \n\n\n");
}
public static void gameOver(){
	System.out.println("\n\n\n\n ________  ________  _____ ______   _______           ________  ___      ___ _______   ________     \r\n" + 
			"|\\   ____\\|\\   __  \\|\\   _ \\  _   \\|\\  ___ \\         |\\   __  \\|\\  \\    /  /|\\  ___ \\ |\\   __  \\    \r\n" + 
			"\\ \\  \\___|\\ \\  \\|\\  \\ \\  \\\\\\__\\ \\  \\ \\   __/|        \\ \\  \\|\\  \\ \\  \\  /  / | \\   __/|\\ \\  \\|\\  \\   \r\n" + 
			" \\ \\  \\  __\\ \\   __  \\ \\  \\\\|__| \\  \\ \\  \\_|/__       \\ \\  \\\\\\  \\ \\  \\/  / / \\ \\  \\_|/_\\ \\   _  _\\  \r\n" + 
			"  \\ \\  \\|\\  \\ \\  \\ \\  \\ \\  \\    \\ \\  \\ \\  \\_|\\ \\       \\ \\  \\\\\\  \\ \\    / /   \\ \\  \\_|\\ \\ \\  \\\\  \\| \r\n" + 
			"   \\ \\_______\\ \\__\\ \\__\\ \\__\\    \\ \\__\\ \\_______\\       \\ \\_______\\ \\__/ /     \\ \\_______\\ \\__\\\\ _\\ \r\n" + 
			"    \\|_______|\\|__|\\|__|\\|__|     \\|__|\\|_______|        \\|_______|\\|__|/       \\|_______|\\|__|\\|__|\r\n" + 
			"                                                                                                    \n\n\n\n");
	System.out.println("Sorry but you lost :( Better luck next time!");
}
/***
 * Displays Main Menu option
 * @param menu1
 * @return
 * @throws IOException
 */
public static String menu(String menu1)throws IOException, InterruptedException {
	BufferedReader in;
	in = new BufferedReader(new InputStreamReader (System.in));
	System.out.println("\t___ __ ._`.*.'_._ ____ ____\r\n" + 
			"\t . +  * .o   o.* `.`. +.    .\r\n" + 
			"\t*  . ' ' |\\^/|  `. * .  * `\r\n" + 
			"\t          \\V/ . +\r\n" + 
			"\t          /_\\  .`.\r\n" + 
			"================ _/ \\_ ===============");
	System.out.println("\t__   __    ___ _    "); 
	Thread.sleep(TIME);
	System.out.println("\t\\ \\ / /   / (_) |");
	Thread.sleep(TIME);
	System.out.println("\t \\ V /   / / _| |_  ");
	Thread.sleep(TIME);
	System.out.println("\t /   \\  / / | | __| ");
	Thread.sleep(TIME);
	System.out.println("\t/ /^\\ \\/ /  | | |_  ");
	Thread.sleep(TIME);
	System.out.println("\t\\/   \\/_/   |_|\\__| "); 
	Thread.sleep(TIME);
	System.out.println("======================================");
	Thread.sleep(TIME);
	System.out.println("A Text-based Story Adventure Game by Isshana");
	Thread.sleep(5000);
	System.out.println("\n\tMAIN MENU");
	System.out.println("\t================");
	System.out.println("\t[1] Rules\n\t[2] Credits\n\t[3] Start Game");
	System.out.println("\t================");
	System.out.println("\tEnter Menu Option:");
	menu1 = in.readLine();
	return menu1;
}

public static void avatarProfile() {
	System.out.println(" _______________________________________________________________");
	System.out.println("| Avatar Profile -- "+name.toUpperCase()+"\n|");
	System.out.println("| Physical Appearance\t\t\tAvatar Statistics\n|");
	System.out.println("| Class: Assassin");
	System.out.println("| Eye Colour: "+avatar[0]+"\t\t\tHealth: "+health);
	System.out.println("| Hair Colour: "+avatar[1]+"\t\t\tSpeed: "+speed);
	System.out.println("| Hair Style: "+avatar[2]+ "\t\tStrength: "+strength);
	System.out.println("| Makeup: "+avatar[3]+"\t\t\tWeapon Skill: "+wskill);
	System.out.println("| Height: "+avatar[4]+"\t\t\tCoins: "+coins);
	System.out.println("| Build: "+avatar[5]);
	System.out.println("| Outfit: "+avatar[6]);
	System.out.println("|________________________________________________________________\n\n");
}


public static void mapPvP() {
	System.out.println("\n   \\         \r\n" + 
			"    \\        \r\n" + 
			"    \\\\  <---- Archensheen     \r\n" + 
			"     \\\\      \r\n" + 
			"      )`--') \r\n" + 
			"     <  o /  \r\n" + 
			"      `\\  /  \r\n" + 
			"       / /    \r\n" + 
			"     /_,, ~~  <--- YOUR LOCATION  \r\n" + 
			"    /   )       \r\n" + 
			"    |  /        \r\n" + 
			"   /  _> <---- Fallholt \r\n" + 
			"  /  /          \r\n" + 
			" /   |          \r\n" + 
			"|    /          \r\n" + 
			"\\___/           \n");
}

public static void mapArchensheen() {
	System.out.println("\n   \\         \r\n" + 
			"    \\        \r\n" + 
			"    \\\\  <---- YOUR LOCATION     \r\n" + 
			"     \\\\      \r\n" + 
			"      )`--') \r\n" + 
			"     <  o /  \r\n" + 
			"      `\\  /  \r\n" + 
			"       / /    \r\n" + 
			"     /_,, ~~  <--- PVP ZONE  \r\n" + 
			"    /   )       \r\n" + 
			"    |  /        \r\n" + 
			"   /  _> <---- Fallholt \r\n" + 
			"  /  /          \r\n" + 
			" /   |          \r\n" + 
			"|    /          \r\n" + 
			"\\___/           \n");
}

public static void mapFallholt() {
	System.out.println("\n   \\         \r\n" + 
			"    \\        \r\n" + 
			"    \\\\  <---- Archensheen     \r\n" + 
			"     \\\\      \r\n" + 
			"      )`--') \r\n" + 
			"     <  o /  \r\n" + 
			"      `\\  /  \r\n" + 
			"       / /    \r\n" + 
			"     /_,, ~~  <--- PVP ZONE  \r\n" + 
			"    /   )       \r\n" + 
			"    |  /        \r\n" + 
			"   /  _> <---- YOUR LOCATION \r\n" + 
			"  /  /          \r\n" + 
			" /   |          \r\n" + 
			"|    /          \r\n" + 
			"\\___/           \n");
}
/***
 * Method that displays the rules of the game
 * How to play
 * How to score points
 */
public static void rules() {
	System.out.println("\n\n.----. .-. .-..-.   .----. .----.\r\n" + 
			"| {}  }| { } || |   | {_  { {__  \r\n" + 
			"| .-. \\| {_} || `--.| {__ .-._} }\r\n" + 
			"`-' `-'`-----'`----'`----'`----' \n\n");
	System.out.println("X/it is a text-based story adventure game. It expresses the themes of VR (virtual reality), fantasy, adventure, and role-playing.");
	System.out.println("\nYou come home from another boring day of school and get right on your computer as you always do. Luckily it's Friday so \nyou've got all the time you need. Along with you friends, you launch yourselves into a video game with no way to leave. The \nonly way to leave is by winning the game.");
	System.out.println("\nHow to play:");
	System.out.println("_____________________");
	System.out.println("This is a text-based adventure game, which means that there aren't necessarily any controls. To play, you have to type in the \ncorresponding commands which are associated with the choice you desire to make.");
	System.out.println("\n\tFor example:");
	System.out.println("\t[1] Walk away from the wizard");
	System.out.println("\t[2] Talk to the wizard");
	System.out.println("\t    >>>2\n");
	System.out.println("If you type in the input 2, then your character will decide to talk to the wizard. Numbers will ONLY be accepted \n(unless specified to type characters).");
	System.out.println("\nYes or no questions typed in all lowercase.");
	System.out.println("\nIf you type in an invalid command then the game will prompt you to enter a valid command.");
	System.out.println("\nIn the mini-game/training phases, all inputs should be typed correctly. If you type an invalid command, it will be considered \na forfeit and you will take damage.");
	System.out.println("\nHow to Win:");
	System.out.println("_____________________");
	System.out.println("To win you simply just have to finish the game!");
	System.out.println("Defeat the remaining teams from the X/it Tournament and you will be released from the game.\n");
	System.out.println("Combat Rounds:");
	System.out.println("_____________________");
	System.out.println("There will be three training mini games that your avatar will have to train to increase their stats. Stats can be increased in speed, \nstrength and weaponskill. Successfully pass the training stages to aquire an increase in stats. After having over \n10 in a specific stat, you will be automatically set into hard mode.");
	System.out.println("\nLove Points:");
	System.out.println("_____________________");
	System.out.println("Throughout the story you will be able to make decisions. Depending on the decisions you make, you will acquire love points. If by \nthe end you require a certain amount (number not disclosed) of love points, you will unlock a special ending in addition to \nthe standard ending (assuming you compete the game).");
	System.out.println("\nTo increase your love score you are given the opportunity to purchase love potions in the market.\n\n\n");
}
/***
 * Method that displays the credits of the game
 * who made it
 * story behind the game
 */
public static void credits(){
	System.out.println("\n\n\t .---. .----. .----..----. .-. .---.  .----.\r\n" + 
			"\t/  ___}| {}  }| {_  | {}  \\| |{_   _}{ {__  \r\n" + 
			"\t\\     }| .-. \\| {__ |     /| |  | |  .-._} }\r\n" + 
			"\t `---' `-' `-'`----'`----' `-'  `-'  `----' \n");
	System.out.println("\n\tThis game is created by Isshana. ");
	System.out.println("\tReleased: November 17, 2019");
	System.out.println("\n\tFramework used: Java.");
	System.out.println("\tASCII Art: https://www.asciiart.eu");
	System.out.println("\tNovember 15 ,2019 - November 17,2019.\n\n");
}

/***
 * Created when player is designing their Avatar
 * This variable value does not change, remains static for the entirety of the game
 * @return
 * @throws IOException
 */
public static String eyeColour() throws IOException{
	BufferedReader in;
	in = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("CREATING YOUR AVATAR: ");
	System.out.println("Select your eye colour: ");
	System.out.println("[1] Brown \n[2] Black \n[3] Blue \n[4] Green \n[5] Grey \n[6] Hazel");
	boolean valid = false;
	while (valid==false) {
		avatar[0] = in.readLine();
		valid = true;
		switch(avatar[0]) {
		case "1":
			avatar[0] = "Brown";
			break;
		case "2":
			avatar[0] = "Black";
			break;
		case "3":
			avatar[0] = "Blue";
			break;
		case "4":
			avatar[0] = "Green";
			break;
		case "5":
			avatar[0] = "Grey";
			break;
		case "6":
			avatar[0] = "Hazel";
			break;	
		default:
			System.out.println("ERROR. Enter a valid answer.");
			valid = false;
		}
	}
	return avatar[0];
}

/***
 * Created when player is designing their Avatar
 * This variable value does not change, remains static for the entirety of the game
 * @return
 * @throws IOException
 */
public static String hairColour() throws IOException{
	BufferedReader in;
	in = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Select your hair colour: ");
	System.out.println("[1] Black \n[2] Brunette \n[3] Blonde \n[4] Red \n[5] Blue \n[6] Purple");
	boolean valid = false;
	while (valid==false) {
		avatar[1] = in.readLine();
		valid = true;
		switch(avatar[1]) {
		case "1":
			avatar[1] = "Black";
			break;
		case "2":
			avatar[1] = "Brunette";
			break;
		case "3":
			avatar[1] = "Blonde";
			break;
		case "4":
			avatar[1] = "Red";
			break;
		case "5":
			avatar[1] = "Blue";
			break;
		case "6":
			avatar[1] = "Purple";
			break;
		default:
			System.out.println("ERROR. Enter a valid answer.");
			valid = false;
		}
	}	
	return avatar[1];
}

/***
 * First hair style method in game
 * Created when player is creating Avatar for first time
 * @return
 * @throws IOException
 */
public static String hairStyle() throws IOException{
	BufferedReader in;
	in = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Select your hair style: ");
	System.out.println("[1] Short Straight \n[2] Short Curly \n[3] Medium Straight \n[4] Medium Curly \n[5] Long Straight \n[6] Long Curly");
	boolean valid = false;
	while (valid==false) {
		avatar[2] = in.readLine();
		valid = true;
		switch(avatar[2]) {
		case "1":
			avatar[2] = "Short Straight";
			break;
		case "2":
			avatar[2] = "Short Curly";
			break;
		case "3":
			avatar[2] = "Medium Straight";
			break;
		case "4":
			avatar[2] = "Medium Curly";
			break;
		case "5":
			avatar[2] = "Long Straight";
			break;
		case "6":
			avatar[2] = "Long Curly";
			break;
		default:
			System.out.println("ERROR. Enter a valid answer.");
			valid = false;
		}
	}	
	return avatar[2];
}

/***
 * First Makeup Method in game
 * Created when player is first creating the Avatar
 * can be changed later on in the game depending on event
 * @return
 * @throws IOException
 */
public static String makeup1() throws IOException{
	BufferedReader in;
	in = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Select your makeup: ");
	System.out.println("[1] Classical \n[2] Elegant \n[3] Smokey \n[4] Modern \n[5] Natural \n[6] Vintage");
	boolean valid = false;
	while (valid==false) {
		avatar[3] = in.readLine();
		valid = true;
		switch(avatar[3]) {
		case "1":
			avatar[3] = "Classical";
			break;
		case "2":
			avatar[3] = "Elegant";
			break;
		case "3":
			avatar[3] = "Smokey";
			break;
		case "4":
			avatar[3] = "Modern";
			break;
		case "5":
			avatar[3] = "Natural";
			break;
		case "6":
			avatar[3] = "Vintage";
			break;	
		default:
			System.out.println("ERROR. Enter a valid answer.");
			valid = false;	
		}
	}	
	return avatar[3];
}

/***
 * Created when player is designing their Avatar
 * This variable value does not change, remains static for the entirety of the game
 * Determines Avatar's Height
 * @return
 * @throws IOException
 */
public static String height() throws IOException{
	BufferedReader in;
	in = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Select your height: ");
	System.out.println("[1] Tall \n[2] Medium \n[3] Short");
	boolean valid = false;
	while (valid==false) {
		avatar[4] = in.readLine();
		valid = true;
		switch(avatar[4]) {
		case "1":
			avatar[4] = "5\'9\"";
			break;
		case "2":
			avatar[4] = "5\'4\"";
			break;
		case "3":
			avatar[4] = "4\'11\"";
			break;
		default:
			System.out.println("ERROR. Enter a valid answer.");
			valid = false;	
		}
	}	
	return avatar[4];
}

/***
 * Created when player is designing their Avatar
 * This variable value does not change, remains static for the entirety of the game
 * Determines Avatar's Body composition
 * @return
 * @throws IOException
 */
public static String build() throws IOException{
	BufferedReader in;
	in = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Select your body build: ");
	System.out.println("[1] Ectomorph \n[2] Mesomorph \n[3] Endomorph");
	boolean valid = false;
	while (valid==false) {
		avatar[5] = in.readLine();
		valid = true;
		switch(avatar[5]) {
		case "1":
			avatar[5] = "Ectomorph";
			break;
		case "2":
			avatar[5] = "Mesomorph";
			break;
		case "3":
			avatar[5] = "Endomorph";
			break;
		default:
			System.out.println("ERROR. Enter a valid answer.");
			valid = false;	
		}
	}	
	return avatar[5];
}

/***
 * First Clothing Method in game
 * Created when player is first creating the avatar
 * @return
 * @throws IOException
 */
public static String clothing1() throws IOException{
	BufferedReader in;
	in = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Select your Outfit: ");
	System.out.println("[1] Skin-tight Black Body suit \n[2] Black shorts and full-sleeve with combat boots \n[3] Black-long coat with black heeled combat boots");
	boolean valid = false;
	while (valid==false) {
		avatar[6] = in.readLine();
		valid = true;
		switch(avatar[6]) {
		case "1":
			avatar[6] = "Skin-tight Black Body suit";
			break;
		case "2":
			avatar[6] = "Black shorts and full-sleeve with combat boots";
			break;
		case "3":
			avatar[6] = "Black-long coat with black heeled combat boots";
			break;
		default:
			System.out.println("ERROR. Enter a valid answer.");
			valid = false;	
		}
	}	
	return avatar[6];
}
/***
 * Saves Character Data (Physical Appearance) to an Avatar Save File
 * Creates new file called: AvatarProfile.txt
 * @throws IOException
 */
public static void saveAvatarProfile() throws IOException{
	BufferedReader in;
	in = new BufferedReader(new InputStreamReader(System.in));
	
	PrintWriter out;
	out = new PrintWriter(new FileWriter("AvatarProfile.txt"));

	out.println("Character Profile: " + name.toUpperCase());
	out.println("________________________");
	out.println("Physical Appearance:\n");
	out.println("Eye Colour: " + avatar[0]);
	out.println("Hair Colour: "+ avatar[1]);
	out.println("Hair Style: "+ avatar[2]);
	out.println("Makeup: " + avatar[3]);
	out.println("Height: "+ avatar[4]);
	out.println("Build: " + avatar[5]);
	out.println("Outfit: " + avatar[6]);
	out.close();
	System.out.println("\t\t\t >>> AVATAR DETAILS SAVED. Stored in AvatarProfile.txt\n\n\n");
}

public static void market() throws IOException{
	String purchase;
	BufferedReader in;
	in = new BufferedReader (new InputStreamReader(System.in));
	System.out.println("                                                         \r\n" + 
			",--.   ,--.  ,---.  ,------. ,--. ,--.,------.,--------. \r\n" + 
			"|   `.'   | /  O  \\ |  .--. '|  .'   /|  .---''--.  .--' \r\n" + 
			"|  |'.'|  ||  .-.  ||  '--'.'|  .   ' |  `--,    |  |    \r\n" + 
			"|  |   |  ||  | |  ||  |\\  \\ |  |\\   \\|  `---.   |  |    \r\n" + 
			"`--'   `--'`--' `--'`--' '--'`--' '--'`------'   `--'    ");
	System.out.println("Welcome to the PvP Market! We sell all sorts of things. What are you looking for?\n");
	System.out.println("[1] Health Potion (2 coins) --> restores 50 health for a max of up to 100 |cannot be used during mini-games, can be used during X/it Tournament|");
	System.out.println("[2] Love Potion (5 coins) --> increases love score by 1 |Unlocks a special ending if enough love points are collected.|");
	System.out.println("[3] X/it Tournament Access Key (10 coins) --> grants access to the X/it Tournament");
	boolean buy = false;
	
	while (buy ==false) {
		purchase = in.readLine();
		buy = true;
		switch(purchase) {
		case "1":
			if (coins>=2) {
				coins = coins-2;
				for (int i=0;i<5;i++) {
					if (invSlotName[i].contains("Health Potion")) {
						invSlotQuant[i]=invSlotQuant[i]+1;
						System.out.println("Succesfully purchased 1 Health Potion!");
					}else {						
						if (invSlotName[i].contains("empty")&&valid1==false) {
							invSlotName[i] = "Health Potion";
							invSlotQuant[i] = 1;
							System.out.println("Succesfully purchased 1 Health Potion!!");
							valid1 = true;	
						}	
					}
				}
				
			} else {
				System.out.println("You do not have enough coins to purchase this item. Play more training games to earn coins.");
			}
			break;
		case "2":
			if (coins>=5) {
				coins = coins-5;
				for (int i=0;i<5;i++) {
					if (invSlotName[i].contains("Love Potion")) {
						invSlotQuant[i]++;
						System.out.println("Succesfully purchased 1 Love Potion!");
					}else {
						if (invSlotName[i].contains("empty")&&valid2==false) {
							invSlotName[i] = "Love Potion";
							invSlotQuant[i] = 1;
							valid2 = true;
						}	
					}
				}
			}else {
				System.out.println("You do not have enough coins to purchase this item. Play more training games to earn coins.");
			}
			break;
		case "3":
			if (storeKey>=1) {
				if (coins>9) {
					coins = coins -10;
					for (int i=0;i<5;i++) {
						if (invSlotName[i].contains("empty")&&valid3==false) {
							invSlotName[i] = "X/it Tournament Access Key";
							invSlotQuant[i] = 1;
							valid3 = true;
						}
					}
					System.out.println("You have successfully purchased the X/it Tournament Access Key!");
					storeKey= 0;
				}else {
					System.out.println("You do not have enough for this item. Play more training games to earn coins.");
				}
			} else {
				System.out.println("This item is no longer in stock. You have already purchased it.");
			}
			
			break;
		default:
			System.out.println("ERROR. Enter a valid answer.");
			buy = false;
		}
	}
	
}



public static void inventory() throws IOException{
	BufferedReader in;
	in = new BufferedReader (new InputStreamReader(System.in));
	String tempName;
	String search;
	int i,j,smallest,tempQuant =0;
	int flag =0;
	boolean found = false;
	System.out.println("\n\n _______________________________");
	System.out.println("|"+name.toUpperCase()+"'S INVENTORY");
	System.out.println("|1. "+invSlotName[0]+" - "+invSlotQuant[0]);
	System.out.println("|2. "+invSlotName[1]+" - "+invSlotQuant[1]);
	System.out.println("|3. "+invSlotName[2]+" - "+invSlotQuant[2]);
	System.out.println("|4. "+invSlotName[3]+" - "+invSlotQuant[3]);
	System.out.println("|5. "+invSlotName[4]+" - "+invSlotQuant[4]);
	System.out.println("|________________________________\n\n");
	do {
		System.out.println("What would you like to consume (Type item name)? (Type 'exit' to exit inventory)");
		search= (in.readLine()).toLowerCase();
		for (i=0;i<5;i++) {
			if (search.contains(invSlotName[i].toLowerCase())) {
				flag =i;
				found = true;
			}
		}
		if (found == false) {
			if (!search.contentEquals("exit")) {
				System.out.println("Item not found in inventory");
			}
		}else {
			switch (invSlotName[flag]) {
			case ("Twin Daggers"):
				System.out.println("This item is not consumable.");
				break;
			case ("Health Potion"):
				if (invSlotQuant[flag]>0) {
					invSlotQuant [flag]= invSlotQuant[flag]-1;
					health = health +50;
					if (health>100) {
						health = 100;
					}
				}else {
					System.out.println("You do not have this item. Try purchasing at the market.");
				}
				break;
			case ("Love Potion"):
				if (invSlotQuant[flag]>0) {
					invSlotQuant [flag]= invSlotQuant[flag]-1;
					love = love+1;
				}else {
					System.out.println("You do not have this item. Try purchasing at the market.");
				}
				break;
			case ("X/it Tournament Access Key"):
				if (invSlotQuant[flag]>0) {
					System.out.println("You cannot consume this item at this time.");
				}else {
					System.out.println("You do not have this item. Try purchasing at the market.");
				}
				break;
			case ("Journal"):
				System.out.println("This item is not consumable.");
			}
		}
		do {
			System.out.println("Would you like to sort your inventory by quantity (Type 'yes' if yes)? (Type 'exit' to exit inventory).");
			search= (in.readLine()).toLowerCase();
			if (search.contentEquals("yes")) {
				for (i=0;i<5;i++) {
					smallest = i;
					for (j=i;j<5;j++) {
						if(invSlotQuant[j]<invSlotQuant[smallest]) {
							smallest =j;
						}
					}
					tempQuant = invSlotQuant[i];
					tempName = invSlotName[i];
					invSlotQuant[i]=invSlotQuant[smallest];
					invSlotName[i]=invSlotName[smallest];
					invSlotQuant[smallest]= tempQuant;
					invSlotName[smallest]=tempName;
				}
				System.out.println("Sorted!");
				search = "exit";
			}else {
				if(!search.contentEquals("exit")) {
					System.out.println("Error. Invalid Input.");
				}
			}

		}while(!search.contentEquals("exit"));		
	}while (!search.contentEquals("exit"));		
}


public static int journalMenu() throws IOException {
	BufferedReader in;
	in = new BufferedReader(new InputStreamReader(System.in));

	System.out.println("\t _______________________________________");
	System.out.println("\t| JOURNAL \t\t\t\t|\n\t| 1. Enter a Squad Mate/Add a Squad Mate|\n\t| 2. Modify Squad Mate \t\t\t|");
	System.out.println("\t| 3. Delete Squad Mate \t\t\t|\n\t| 4. Display all Squad Mates\t\t|\n\t| 5. Exit Journal\t\t\t|"); 
	System.out.println("\t _______________________________________");
	System.out.println("\tSelect your choice: (Type the corresponding number)");
	//Takes users input from menu
	int menu =Integer.valueOf(in.readLine()).intValue(); 
	//Returns input from user in this variable
	return menu;
}
public static void enterSquad() throws IOException{
	BufferedReader in;
	in = new BufferedReader(new InputStreamReader(System.in));
	if (h<=5) {
		
	}else {
		System.out.println("You have reached the max amount of members. Modify or delete some to input new ones.");
	}
	do {
		System.out.println("Enter squad mate name: ");
		journalName [h] = in.readLine();
		System.out.println("Enter squad mate class type: ");
		journalClassType [h] = in.readLine();
		h++;
		System.out.println("\n TO Quit entering squad member information type 'quit'");
	} while ((in.readLine().compareTo("quit"))!=0);	
	System.out.println("Total number of squad members in journal: "+h);
}
/***
 * allows user to modify existing squad members
 * @throws IOException
 */
public static void modifySquad() throws IOException{
	BufferedReader in;
	in = new BufferedReader(new InputStreamReader(System.in));
	//holds user input
	String modShow;
	//flag
	int flag =0;
	//counter
	int i;
	boolean found = false;
	System.out.println("What is the squad member name you wish to modify?");
	modShow = in.readLine();
	for (i=0; i<=(h-1);i++) {
		if (modShow.compareTo(journalName[i])==0) {
			flag = i;
			found = true;	
		}
	}
	if (found == false) {
		System.out.println("Member not found");
	}else {
		System.out.println("Enter new squad member name: ");
		journalName [flag] = in.readLine();
		System.out.println("Enter new class type: ");
		journalClassType [flag] = in.readLine();

		}

	}
public static void deleteSquad() throws IOException{
	BufferedReader in;
	in = new BufferedReader(new InputStreamReader(System.in));
	//String to hold user input
	String deleteShow;
	//flag to hold position in array
	int flag=0;
	//counter
	int i;
	boolean found = false;
	//asks user for input
	System.out.println("What is the member's name you wish to delete?");
	deleteShow = in.readLine();
	
	/***
	 * loops through show (x)
	 * searching for show
	 */

	for (i=0;i<(h-1);i++) {
		if (deleteShow.compareTo(journalName[i])== 0) {
			flag = i;
			found = true;
		}
	}
	if(found == false) {
		System.out.println("Member not found");
	/***
	 * deletes that show
	 * shifts the array so that there is no empty spot	
	 */
	}else {		

		for (i=flag;i<=(h-2);i++) {
			journalName[i]=journalName[i+1];
			journalClassType[i] = journalClassType[i+1];
		}
		//updates new number of shows
		h=h-1;
		System.out.println("Deleted.");
	}
}
public static void displayAllSquad() {
	int i;
	System.out.println("\nAll Squad Members in Directory");
	System.out.println("Member Name \t\tClass Type\n");
	//Loops through members
	for (i = 0; i<=(h-1);i++) {
		System.out.println(journalName[i] + "\t\t\t"+journalClassType[i]);
	}
}

public static void fallholt1() throws InterruptedException {
	System.out.println("Me: \"I think we should go to Fallholt.\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"You heard the lady, lets go!\"\n");
	Thread.sleep(TIME);
	System.out.println("We have all been walking for about four hours already.");
	Thread.sleep(TIME);
	System.out.println("The four of us talked and enjoyed the trip, it was great.");
	Thread.sleep(TIME);
	System.out.println("Eventually, we reached the town\n");
	Thread.sleep(TIME);
	System.out.println("Lucas: \"Fallholt here we are!\"");
	Thread.sleep(TIME);
	System.out.println("Mateo: \"Let's start looking for a member, preferably a support.\"\n");
	Thread.sleep(TIME);
	System.out.println("We searched for hours and hours but there was no one to be found.\n");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"Looks like there isn't anyone here\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"I'm sorry guys. I led us to the wrong town.\"");
	Thread.sleep(TIME);
	System.out.println("Lucas: \"Don't apologize, we would've probably done the same to be honest. Let's check the other town!\"");
	Thread.sleep(TIME);
	System.out.println("I think we should go to... \n\t[1] Archensheen \n\t[2] Fallholt");
}

public static void cutscene1() throws InterruptedException {
	System.out.println("Three more minutes"); 
	Thread.sleep(TIME);
	System.out.println("Ugh, could this clock take any longer??");
	Thread.sleep(TIME);
	System.out.println("\nToday was yet another dull day at school.");
	Thread.sleep(TIME);
	System.out.println("Teachers yapping, tiresome lessons, and annoying students.");
	Thread.sleep(TIME);
	System.out.println("I absolutely hated this place.");
	Thread.sleep(TIME);
	System.out.println("Well thankfully, it was a Friday. So I was about to just go home and chill for the whole weekend.");
	Thread.sleep(TIME);
	System.out.println("\nThe school bell rang.");
	Thread.sleep(TIME);
	System.out.println("I shoved my books into my bag and bolted for the exit.\n\n");
	Thread.sleep(TIME);
	
	System.out.println("                                                 *******\r\n" + 
			"                                 ~             *---*******\r\n" + 
			"                                ~             *-----*******\r\n" + 
			"                         ~                   *-------*******\r\n" + 
			"                        __      _   _!__     *-------*******\r\n" + 
			"                   _   /  \\_  _/ \\  |::| ___ **-----********   ~\r\n" + 
			"                 _/ \\_/^    \\/   ^\\/|::|\\|:|  **---*****/^\\_\r\n" + 
			"              /\\/  ^ /  ^    / ^ ___|::|_|:|_/\\_******/  ^  \\\r\n" + 
			"             /  \\  _/ ^ ^   /    |::|--|:|---|  \\__/  ^     ^\\___\r\n" + 
			"           _/_^  \\/  ^    _/ ^   |::|::|:|-::| ^ /_  ^    ^  ^   \\_\r\n" + 
			"          /   \\^ /    /\\ /       |::|--|:|:--|  /  \\        ^      \\\r\n" + 
			"         /     \\/    /  /        |::|::|:|:-:| / ^  \\  ^      ^     \\\r\n" + 
			"   _Q   / _Q  _Q_Q  / _Q    _Q   |::|::|:|:::|/    ^ \\   _Q      ^\r\n" + 
			"  /_\\)   /_\\)/_/\\\\)  /_\\)  /_\\)  |::|::|:|:::|          /_\\)\r\n" + 
			"_O|/O___O|/O_OO|/O__O|/O__O|/O__________________________O|/O__________\r\n" + 
			"//////////////////////////////////////////////////////////////////////");
	Thread.sleep(TIME);
	System.out.println("\n\nAs soon as I got home, I rushed upstairs and hopped onto my computer.");
	Thread.sleep(TIME);
	System.out.println("My computer was like my happy place.");
	Thread.sleep(TIME);
	System.out.println("I could always explore new things and play video games.");
	Thread.sleep(TIME);
	System.out.println("I LOVED playing video games.");
	Thread.sleep(TIME);
	System.out.println("It was probably my most favourite thing to do.");
	Thread.sleep(TIME);
	System.out.println("I had tried pretty much played every game out there. Action, survival, FPS, RPG, MOBA anything you name it!\n\n");
	Thread.sleep(TIME);
	System.out.println("                                                 .------.------.    \r\n" + 
			"  +-------------+                     ___        |      |      |    \r\n" + 
			"  |             |                     \\ /]       |      |      |    \r\n" + 
			"  |             |    _____           _(_)        |      |      |    \r\n" + 
			"  |             |   / ___))         [  | \\___    |      |      |    \r\n" + 
			"  |             |  /  ) //o          | |     \\   |      |      |    \r\n" + 
			"  |             | |_ (_    >         | |      ]  |      |      |    \r\n" + 
			"  |          __ | (O)  \\__<          | | ____/   '------'------'    \r\n" + 
			"  |         /  o| [/] /   \\)        [__|/_                          \r\n" + 
			"  |             | [\\]|  ( \\         __/___\\_____                    \r\n" + 
			"  |             | [/]|   \\ \\__  ___|            |                   \r\n" + 
			"  |             | [\\]|    \\___E/%%/|____________|_____              \r\n" + 
			"  |             | [/]|=====__   (_____________________)             \r\n" + 
			"  |             | [\\] \\_____ \\    |                  |              \r\n" + 
			"  |             | [/========\\ |   |                  |              \r\n" + 
			"  |             | [\\]     []| |   |                  |              \r\n" + 
			"  |             | [/]     []| |_  |                  |              \r\n" + 
			"  |             | [\\]     []|___) |                  |    MEPH          \r\n" + 
			"====================================================================\r\n" + 
			"");
	System.out.println("\n\nI logged onto my computer and launched Gam3rCh4t (Gamer chat).");
	Thread.sleep(TIME);
	System.out.println("Gam3rCh4t was an online chat platform specifically designed for gamers.");
	Thread.sleep(TIME);
	System.out.println("You could literally meet people of all interests anywhere around the world. It was sick.");
	Thread.sleep(TIME);
	System.out.println("I had met some of the best people I know today through Gam3rCh4t that I have known for so many years.\n");
	Thread.sleep(TIME);
	System.out.println("I scrolled through my friends list and saw that Jovi was online.");
	Thread.sleep(TIME);
	System.out.println("Jovi was my best friend, I've known him for about six years now.");
	Thread.sleep(TIME);
	System.out.println("He was a couple of months older than me and lived in the United States.");
	Thread.sleep(TIME);
	System.out.println("We met through an online MOBA (Multiplayer online battle area) called Battle Legends, my all-time favourite game, and instantly clicked.");
	Thread.sleep(TIME);
	System.out.println("We liked the same games, movies, and understood each other like no one else.");
	Thread.sleep(TIME);
	System.out.println("Since then, we've been pretty inseparable.");
	Thread.sleep(TIME);
	System.out.println("I would always talk to him before and after school whenever we got the opportunity to.\n");
	Thread.sleep(TIME);
	System.out.println("I saw that he was in another group voice call with a couple of our other friends, Lucas and Mateo, the twins.");
	Thread.sleep(TIME);
	System.out.println("Me and Jovi had met them both in a PvP Blockcraft tournament.");
	Thread.sleep(TIME);
	System.out.println("The twins were always up for some friendly competition which Jovi and I loved.\n");
	Thread.sleep(TIME);
	System.out.println("It's been four years now that all of us have known each other and not a day goes by where something new happens.");
	Thread.sleep(TIME);
	System.out.println("I plugged in my headphones and joined their voice call.\n");
	Thread.sleep(TIME);
	System.out.println("Me: \"Heyy guys!\"");
	Thread.sleep(TIME);
	System.out.println("Mateo and Lucas: \"Hey!\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"Yo, what's up?\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"Just got back from school\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"School's such a bore, why even go?\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"Well I still have to get my highschool diploma\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"That makes two of us\"");
	Thread.sleep(TIME);
	System.out.println("Lucas: \"Three\"");
	Thread.sleep(TIME);
	System.out.println("Mateo: \"Four of us\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"Well whatever, I don't have any homework this weekend, finished it all at school. How about we play something?\"");
	Thread.sleep(TIME);
	System.out.println("Lucas: \"Yeah let's play that new VR game\"");
	Thread.sleep(TIME);
	System.out.println("Mateo: \"Righttt, the fantasy-RPG one, I heard that it's really good.\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"What's it called?\"");
	Thread.sleep(TIME);
	System.out.println("Mateo: \"X/it by Tree Games\" \n");
	Thread.sleep(TIME);
	System.out.println("I googled the game to see if I could find an online download.");
	Thread.sleep(TIME);
	System.out.println("I went onto their website and read the summary.");
	Thread.sleep(TIME);
	System.out.println("The game did seem really cool and the trailer looked epic.");
	Thread.sleep(TIME);
	System.out.println("I continued looking for the download and saw the price.");
	Thread.sleep(TIME);
	System.out.println("$350.. You've gotta be kidding me.\n");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"El, check your DMs (direct messages)\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"Alright\"\n");
	Thread.sleep(TIME);
	System.out.println("I opened my messages and saw one notification at the top of a message from Jovi.\n\n");
	Thread.sleep(TIME);
	System.out.println("  ____  __  __                   _            _ \r\n" + 
			" |  _ \\|  \\/  |___              | | _____   _(_)\r\n" + 
			" | | | | |\\/| / __|  _____   _  | |/ _ \\ \\ / / |\r\n" + 
			" | |_| | |  | \\__ \\ |_____| | |_| | (_) \\ V /| |\r\n" + 
			" |____/|_|  |_|___/          \\___/ \\___/ \\_/ |_|\r\n");
	Thread.sleep(TIME);
	System.out.println(" __________________________________________________________________________");
	System.out.println("| MESSAGES WITH JOVI                                                       |\n|                                                                          |");
	Thread.sleep(TIME);
	System.out.println("|Jovi - Hey El, I actually have a friend that gave me two copies of the    |\n|game for free. Neat isn't it? Use this download link, it's a free copy.   |\n|Don't worry about buying it.                                              |");
	Thread.sleep(TIME);
	System.out.println("|                                                                          |");
	System.out.println("|El - Are you serious J? This is so expensive! You can't give it to me.    |");
	Thread.sleep(TIME);
	System.out.println("|                                                                          |");
	System.out.println("|Jovi - I always got my bro's back ;)                                      |");
	Thread.sleep(TIME);
	System.out.println("|                                                                          |");
	System.out.println("|Me - Haha fine I'll allow it this time, I'm gonna get you something even  |\n|better for your birthday :)                                               |");
	Thread.sleep(TIME);
	System.out.println("|                                                                          |");
	System.out.println("|Jovi - lmao, we'll see about that                                         |");
	System.out.println(" __________________________________________________________________________");
	Thread.sleep(TIME);
	System.out.println("\n\nMe: \"Okay guys, let's try this game out!\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \" I think it's linked to our Tree profile so we already have each other added\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"That's perfect!\"");
	Thread.sleep(TIME);
	System.out.println("Mateo: \"I was reading the instructions and it seems that you have to create an avatar first and then enter a lobby to begin the game.\"");
	Thread.sleep(TIME);
	System.out.println("Lucas: \"Seems like the avatar is auto-synced to your VR headset too so we can probably only choose our costume.\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"Well let's hop in, I'm dying to play this game!\"\n");
	Thread.sleep(TIME);
	System.out.println("I was slightly worried about what was going to happen with the auto-synced avatars.");
	Thread.sleep(TIME);
	System.out.println("I never told anyone this before but I've been using a voice changer online to make my voice appear deeper than usual.");
	Thread.sleep(TIME);
	System.out.println("This lead to my friends and others thinking that I was a boy even though in real-life I'm a girl.");
	Thread.sleep(TIME);
	System.out.println("I found it more fun and easier to make friends being a guy.");
	Thread.sleep(TIME);
	System.out.println("People might think it's strange but they won't understand unless they've been a female in the gaming community for a long time.\n");
	Thread.sleep(TIME);
	System.out.println("I put on my headset and launched the game.");
	Thread.sleep(TIME);
	System.out.println("The screen was pitch black for about 30 seconds until some text appeared in front of me.\n\n");
	Thread.sleep(TIME);
	System.out.println(" _________________________ ");
	System.out.println("| ENTER YOUR AVATAR NAME: |");
	System.out.println(" _________________________");
	Thread.sleep(TIME);
}
public static int cutscene2() throws IOException, InterruptedException{
	BufferedReader in;
	in = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("My avatar was now complete and I was ready to take on this game.");
	Thread.sleep(TIME);
	System.out.println("I opened my friends list and it only showed Jovi in a lobby. ");
	Thread.sleep(TIME);
	System.out.println("The other two might still be creating their avatars.\n");
	Thread.sleep(TIME);
	System.out.println("I was nervous, I wasn't sure how Jovi and the twins were going to react.");
	Thread.sleep(TIME);
	System.out.println("I mean we've been so close with each other and now they might lose trust in me.");
	Thread.sleep(TIME);
	System.out.println("Now I kinda regret not telling them before. I really hope that they won't hate me.\n");
	Thread.sleep(TIME);
	System.out.println("I clicked on Jovi's avatar profile with my hands and transported to his lobby.\n");
	Thread.sleep(TIME);
	System.out.println("He was standing on the other side of the lobby gazing off in the distance.");
	Thread.sleep(TIME);
	System.out.println("Jovi was so tall, about 6'2 or 6'3.");
	Thread.sleep(TIME);
	System.out.println("His chestnut brown hair complemented his green eyes perfectly.");
	Thread.sleep(TIME);
	System.out.println("He was so handsome, I never expected Jovi to look like this.");
	Thread.sleep(TIME);
	System.out.println("He could honestly be a model with the way he looked.\n");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"Hey!  El?\"\n");
	Thread.sleep(TIME);
	System.out.println("His expression changed completely.");
	Thread.sleep(TIME);
	System.out.println("We stared at each other for a minute and then Jovi turned around and started walking towards the other side of the room.\n\n");
	Thread.sleep(TIME);
	System.out.println("Should I...");
	System.out.println("[1] Follow him and explain the situation.\n[2] Leave him alone.");	
	boolean valid = false;
	while (valid == false) {
		decisionStr = in.readLine();
		valid = true;
		switch (decisionStr) {
		case "1":
			decision = 1;	
			break;
		case "2":
			decision = 2;
			break;
		default:
			System.out.println("ERROR. Enter a valid answer.");
			valid = false;
		}
	}
	return decision;
}
public static int ch1dc1() throws IOException, InterruptedException{
	love = love +2;
	BufferedReader in;
	in = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("I ran after him and stopped him from walking away.\n");
	Thread.sleep(TIME);
	System.out.println("Me: \"Jovi..JOVI. Stop walking away!\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"Why do you have a female avatar? Did you bypass the auto-sync?\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"No I didn't. This is auto-sync. I'm sorry I didn't tell you earlier.\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"That literally makes no sense. I swear to god you sound like a dude online.\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"Voice changer\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"You've got to be kidding me\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"I'm really really sorry. I promise I'll do whatever you want to make it back up to you\"\n");
	Thread.sleep(TIME);
	System.out.println("His face turned slightly red and he looked away from me.");
	Thread.sleep(TIME);
	System.out.println("I think I really pissed him off.\n");
	Thread.sleep(TIME);
	System.out.println("I think I should...");
	System.out.println("[1] Give him a hug.\n[2] Stand there.");
	boolean valid = false;
	while (valid == false) {
		decisionStr = in.readLine();
		valid = true;
		switch (decisionStr) {
		case "1":
			decision = 1;	
			break;
		case "2":
			decision = 2;
			break;
		default:
			System.out.println("ERROR. Enter a valid answer.");
			valid = false;
		}
	}		 
	return decision;
}

public static void ch1dc2() throws InterruptedException{
	System.out.println("I decided to leave him alone.");
	Thread.sleep(TIME);
	System.out.println("We stood in opposite sides of the lobby silent for 5 minutes.");
	Thread.sleep(TIME);
	System.out.println("I felt so bad for lying to him.");
	Thread.sleep(TIME);
	System.out.println("I never wanted it to end up this bad.");
	Thread.sleep(TIME);
	System.out.println("I worked up the courage to approach him and tell him the truth.\n");
	Thread.sleep(TIME);
	System.out.println("Me: \"Jovi, yeah this is me. I'm a girl as you can see. I've been using a voice changer for the past few years. I'm really sorry for doing this I never wanted our friendship to end up like this. I just thought it would've been easier if I had been a guy because we might've bonded better.\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"I'm not mad, just upset because you didn't tell me this before. We're friends, you know you can be honest with me?\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"Yeah, I promise I'll tell you everything from now on\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"Promise?\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"Yup promise!\"");
	Thread.sleep(TIME);
	System.out.println("\nWe smiled at each other and started walking back.");
	Thread.sleep(TIME);
}

public static void ch2dc1() throws InterruptedException {
	love = love +2;
	System.out.println("I moved closer to him and gave him a hug.");
	Thread.sleep(TIME);
	System.out.println("Hopefully this would reassure him.");
	Thread.sleep(TIME);
	System.out.println("His body felt stiff but after a little while he returned the hug.");
	Thread.sleep(TIME);
	System.out.println("We stood with our arms embraced for a few minutes.\n");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"I'm not mad. Don't worry. I was just a bit shocked. We've been friends for six years and I'm only finding out now that you're a girl? Unbelievable...\"");
	Thread.sleep(TIME);
	System.out.println("\nWe pulled away and he smiled.");
	Thread.sleep(TIME);
	System.out.println("I could feel myself getting warm but I quickly shook it off.");
	Thread.sleep(TIME);
}

public static void ch2dc2() throws InterruptedException {
	love = love +1;
	System.out.println("I stood there and waited.\n");
	Thread.sleep(TIME);
	System.out.println("Me: \"Hey J I'm really sorry please don't be mad\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"El, I'm not mad just surprised. I never expected it. You got me good.\"\n");
	Thread.sleep(TIME);
	System.out.println("We looked into each other's eyes and smiled.");
	Thread.sleep(TIME);
}

public static void ch3dc1() throws InterruptedException{
	System.out.println("Me: \"I think we should go to Archensheen.\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"Looks like we aren't going anywhere. Let's check out the rest of this town.\"\n");
	Thread.sleep(TIME);
	System.out.println("We search for hours and hours but there is no key or clues to be found.");
	Thread.sleep(TIME);
	System.out.println("\nMe: \"Guys I don't think we're in the right place.\"");
	Thread.sleep(TIME);
	System.out.println("Cami: \"I don't think so too, let's try another town.\"\n");
	Thread.sleep(TIME);
	System.out.println("I think we should go to...\n[1]Archensheen\n[2]Fallholt");
}

public static void cutscene3() throws InterruptedException{
	System.out.println("Just as we were walking back to the spawn point, Mateo and Lucas entered the lobby.\n");
	Thread.sleep(TIME);
	System.out.println("Mateo: \"EL? IS THAT YOU?\"");
	Thread.sleep(TIME);
	System.out.println("Lucas: \"You're a girl? What in the world??\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"Voice changer\"");
	Thread.sleep(TIME);
	System.out.println("Mateo: \"You reaaaally got me\"");
	Thread.sleep(TIME);
	System.out.println("Lucas: \"Me too\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"Make that three\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"I'm really sorry about that guys. I just thought that it would be easier for us to be friends this way\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"No matter what you are, we would never treat you based on your physical appearance. We're only friends with you because you are being you\"");
	Thread.sleep(TIME);
	System.out.println("Mateo: \"You're pretty too, how could you be afraid??\"\n");
	Thread.sleep(TIME);
	System.out.println("I blushed.");
	Thread.sleep(TIME);
	System.out.println("That was totally unexpected.");
	Thread.sleep(TIME);
	System.out.println("I'm glad they accept me and aren't too mad about what I did.");
	Thread.sleep(TIME);
	System.out.println("They are truly the best friends I could've asked for.");
	Thread.sleep(TIME);
	System.out.println("I love them so much.\n");
	Thread.sleep(TIME);
	System.out.println("We were all ready now. It was time to enter the game.\n");
	Thread.sleep(TIME);
	System.out.println("Me: \"Let's start this game!\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"Yeah let's get into it!\"\n");
	Thread.sleep(TIME);
	System.out.println("All four of us confirmed the launch sequence and transported into a dark room.");
	Thread.sleep(TIME);
	System.out.println("A few moments later a spotlight appears directly above us.");
	Thread.sleep(TIME);
	System.out.println("A voice (similar to the American sportscaster Gus Johnson) starts speaking.\n");
	Thread.sleep(TIME);
	System.out.println("Voice: \"Welcome to X/it. A virtual-reality fantasy adventure game where you and five friends compete against other squads for a chance to save your lives.\"\n");
	Thread.sleep(TIME);
	System.out.println("Save our lives?");
	Thread.sleep(TIME);
	System.out.println("What does he mean by saving our lives?");
	Thread.sleep(TIME);
	System.out.println("It's just a video game.\n");
	Thread.sleep(TIME);
	System.out.println("Mateo: \"uh.. Literally or methaphorically?\"");
	Thread.sleep(TIME);
	System.out.println("Voice: \"Literally of course!\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"This is just a video game though, we could just log off if we need to.\"");
	Thread.sleep(TIME);
	System.out.println("Voice: \"Are you sure about that?\"");
	Thread.sleep(TIME);
	System.out.println("I quickly opened up my menu and started looking for the sign-out button.");
	Thread.sleep(TIME);
	System.out.println("There was none.");
	Thread.sleep(TIME);
	System.out.println("I started searching for a Force shut-down option'...nothing to be found.");
	Thread.sleep(TIME);
	System.out.println("I could not even reach the VR headset's personal dropdown menu.\n");
	Thread.sleep(TIME);
	System.out.println("Me: \"Are you serious?\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"El, I can't find it.\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"Neither can I.\"");
	Thread.sleep(TIME);
	System.out.println("\nTears started swelling up in my eyes.");
	Thread.sleep(TIME);
	System.out.println("I was afraid.");
	Thread.sleep(TIME);
	System.out.println("Our near future did not look so great.");
	Thread.sleep(TIME);
}

public static void l1p1() throws InterruptedException{
	System.out.println("I felt a hand wrap around me.");
	Thread.sleep(TIME);
	System.out.println("I looked up and it was Jovi.");
	Thread.sleep(TIME);
	System.out.println("He pulled me closer to him in a protective stance and gave me a hug.");
	Thread.sleep(TIME);
	System.out.println("He leaned in and whispered in my ear.");
	Thread.sleep(TIME);
	System.out.println("\nJovi: \"Don't be afraid El. I'll get us out of here safely.\"");
	Thread.sleep(TIME);
	System.out.println("\nHe smiled.");
	Thread.sleep(TIME);
	System.out.println("That was very reassuring.");
	Thread.sleep(TIME);
	System.out.println("I did feel more at ease remembering that he was at my side.\n");
	Thread.sleep(TIME);
}

public static void l1p2() throws InterruptedException{
	love = love+1;
	System.out.println("I felt a hand pat me on the head.");
	Thread.sleep(TIME);
	System.out.println("I looked up and it was Jovi's.");
	Thread.sleep(TIME);
	System.out.println("\nJovi: \"Don't be afraid El. I'll get us out of here safely\"");
	Thread.sleep(TIME);
	System.out.println("\nHe smiled.");
	Thread.sleep(TIME);
}
/***
 * Method that executes if user decides to go through path of rooming with Jovi
 * Gains +3 love points
 * @throws InterruptedException
 */
public static void roomJovi() throws InterruptedException{
	love = love+3;
	System.out.println("Me: \"I think I'll room with J.\"");
	Thread.sleep(TIME);
	System.out.println("Cami: \"Awwe.\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"I'm sorry Cami!\"");
	Thread.sleep(TIME);
	System.out.println("Cami: \"Don't worry about it! I'm really tired anyways so I wanna go to bed.\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"We\'ll see you tomorrow morning Cami.\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"Rest well!\"\n");
	Thread.sleep(TIME);
	System.out.println("Cami skipped off to her room with all of her stuff.");
	Thread.sleep(TIME);
	System.out.println("Now it was just me and Jovi in the hallway.\n");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"Well, shall we go?\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"Yup let's go!\"\n\n");
	Thread.sleep(TIME);
	System.out.println("o(=(=(=(=)=)=)=)o\r\n" + 
			" !!!!!!}!{!!!!!!                                                ___ \r\n" + 
			" !!!!!} | {!!!!!                                               /   \\\r\n" + 
			" !!!!}  |  {!!!!     _!_     ()              ()     _!_       | //  |\r\n" + 
			" !!!'   |   '!!!    |~@~|    ||______________||    |~@~|      |     |\r\n" + 
			" ~@~----+----~@~    |___|    |                |    |___|       \\___/\r\n" + 
			" !!!    |    !!!      |      |      ~@@~      |      |       _________\r\n" + 
			" !!!    |    !!!     ( )     |_______  _______|     ( )     |____-____|\r\n" + 
			" !!!____|____!!!  __(___)__  {__~@~__}{__~@~__}  __(___)__  |____-____|\r\n" + 
			" !!!=========!!!   |__-__|   %%%%%%%%%%%%%%%%%%   |__-__|   |____-____|\r\n" + 
			"_!!!_________!!!___|_____|_ %%%%%%%%%%%%%%%%%%%% _|_____|___|____-____|_\r\n" + 
			"                   |     | %%%%%%%%%%%%%%%%%%%%%% |     |   |/       \\|\r\n" + 
			"                          %%%%%%%%%%%%%%%%%%%%%%%%\r\n" + 
			"                         %%%%%%%%%%%%%%%%%%%%%%%%%%\r\n" + 
			"                        %%%%%%%%%%%%%%%%%%%%%%%%%%%%\r\n" + 
			"                       /!!!!!!!!!!!!!!!!!!!!!!!!!!!!\\\r\n" + 
			"                       !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!  \r\n" + 
			"                       !!!!!!!!!!!!!!!!!!!!!!!!!!lc!!\r\n" + 
			"                       `======~@~==========~@~======`\r\n" + 
			"                      `==============================`\r\n" + 
			"                     `====~@~==================~@~====`\r\n" + 
			"                    `==================================`\r\n" + 
			"                   `==~@~==========================~@~==`");
	System.out.println("\n\nThe two of us walked to our room.");
	Thread.sleep(TIME);
	System.out.println("The room was gorgeous.");
	Thread.sleep(TIME);
	System.out.println("The curtains were made of silk, the floor was covered in Persian rugs, and the tables were made of marble.");
	Thread.sleep(TIME);
	System.out.println("There was just one problem...");
	Thread.sleep(TIME);
	System.out.println("We only had one bed.\n");
	Thread.sleep(TIME);
	System.out.println("Me: \"J we only have one bed, should I go ask them to get another room?\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"No, leave it. I think we're fine.\"\n");
	Thread.sleep(TIME);
	System.out.println("I went to the bathroom and saw that there were two sleeping robes.");
	Thread.sleep(TIME);
	System.out.println("This is perfect! I thought to myself.");
	Thread.sleep(TIME);
	System.out.println("I changed into the robe and came back to the bedroom.\n");
	Thread.sleep(TIME);
	System.out.println("I jumped onto the bed a laid there flat.");
	Thread.sleep(TIME);
	System.out.println("Jovi was standing by the glass door looking outside.");
	Thread.sleep(TIME);
	System.out.println("He was shirtless.");
	Thread.sleep(TIME);
	System.out.println("From the back he was so muscular.");
	Thread.sleep(TIME);
	System.out.println("He had a nice build.");
	Thread.sleep(TIME);
	System.out.println("His biceps and triceps looked really good.");
	Thread.sleep(TIME);
	System.out.println("Jovi turned around and came towards me.");
	Thread.sleep(TIME);
	System.out.println("He climbed onto the bed and camped over me.");
	Thread.sleep(TIME);
	System.out.println("We were staring into each other's eyes.\n");
	Thread.sleep(TIME);
	System.out.println("Me: \"J-Jovi\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"Are you a girl or a boy?\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"I've told you a million times, I'm a girl.\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"Prove it to me.\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"Wh-what do you mean?\"\n");
	Thread.sleep(TIME);
	System.out.println("I got all flustered.");
	Thread.sleep(TIME);
	System.out.println("I could feel my whole body turn red.");
	Thread.sleep(TIME);
	System.out.println("Jovi kept staring into my eyes.");
	Thread.sleep(TIME);
	System.out.println("His eyes were so captivating.\n");
	Thread.sleep(TIME);
	System.out.println("At this moment I started to feel butterflies in my stomach.");
	Thread.sleep(TIME);
	System.out.println("Something about him made me feel this way.");
	Thread.sleep(TIME);
	System.out.println("I was frozen, I couldn't move from under him.");
	Thread.sleep(TIME);
	System.out.println("His stare paralyzed me.\n");
	Thread.sleep(TIME);
	System.out.println("Jovi soon moved beside me.");
	Thread.sleep(TIME);
	System.out.println("He was sitting up beside me while I was still lying down.");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"Where'd you get the robe from?\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"The bathroom.\"\n");
	Thread.sleep(TIME);
	System.out.println("Jovi got up and went to change into the other sleeping robe.");
	Thread.sleep(TIME);
	System.out.println("I couldn't stop thinking about him.");
	Thread.sleep(TIME);
	System.out.println("This was my best friend.");
	Thread.sleep(TIME);
	System.out.println("But now I think I'm starting to catch feelings for him.");
	Thread.sleep(TIME);
	System.out.println("How could this happen, we've been friends for so long and I have never felt these emotions towards him.\n");
	Thread.sleep(TIME);
	System.out.println("I sat up straight against the backboard of the bed.");
	Thread.sleep(TIME);
	System.out.println("Jovi came back and had a small bag.");
	Thread.sleep(TIME);
	System.out.println("He sat beside me on the bed and handed me the bag.\n");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"I got you a little something here.\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"What is it?\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"Open it.\"\n");
	Thread.sleep(TIME);
	System.out.println("He grinned at me while I opened the bag.");
	Thread.sleep(TIME);
	System.out.println("Inside was a small pink felt notepad and an ink pen.");
	Thread.sleep(TIME);
	System.out.println("I took the book and pen out of the bag.\n");
	Thread.sleep(TIME);
	System.out.println("Me: \"What's this for?\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"Just a little something for my favourite person. You can use it to write whatever you want. I would recommend writing information about our squad such as our abilities and classes to help keep track of everything.\"");
	Thread.sleep(TIME);
	System.out.println("\nThis was actually useful!");
	Thread.sleep(TIME);
	System.out.println("Now I would be able to remember everyone's abilities and classes much easier.\n");
	Thread.sleep(TIME);
	System.out.println("The two of us kept talking to each other for hours!");
	Thread.sleep(TIME);
	System.out.println("After a while we both fell asleep.\n\n");
	Thread.sleep(TIME);
	
	System.out.println("                        |\r\n" + 
			"                    .   |\r\n" + 
			"                        |\r\n" + 
			"          \\    *        |     *    .  /\r\n" + 
			"            \\        *  |  .        /\r\n" + 
			"         .    \\     ___---___     /    .  \r\n" + 
			"                \\.--         --./     \r\n" + 
			"     ~-_    *  ./               \\.   *   _-~\r\n" + 
			"        ~-_   /                   \\   _-~     *\r\n" + 
			"   *       ~-/                     \\-~        \r\n" + 
			"     .      |                       |      .\r\n" + 
			"         * |                         | *     \r\n" + 
			"-----------|                         |-----------\r\n" + 
			"  .        |                         |        .    \r\n" + 
			"        *   |                       | *\r\n" + 
			"           _-\\                     /-_    *\r\n" + 
			"     .  _-~ . \\                   /   ~-_     \r\n" + 
			"     _-~       `\\               /'*      ~-_  \r\n" + 
			"    ~           /`--___   ___--'\\           ~\r\n" + 
			"           *  /        ---     .  \\   jgs\r\n" + 
			"            /     *     |           \\\r\n" + 
			"          /             |   *         \\\r\n" + 
			"                     .  |        .\r\n" + 
			"                        |\r\n" + 
			"                        |");
	
	System.out.println("\n\nThe next morning I woke up to something surrounding me.");
	Thread.sleep(TIME);
	System.out.println("It was Jovi.");
	Thread.sleep(TIME);
	System.out.println("He was holding me in his arms.");
	Thread.sleep(TIME);
	System.out.println("I turned around and tried to face him.\n");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"Morning Beautiful.\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"J-J-Jovi.\"\n");
	Thread.sleep(TIME);
	System.out.println("Jovi broke into laughter.");
	Thread.sleep(TIME);
	System.out.println("I was embarrassed.\n");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"You are just so adorable.\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"Stooop.\"\n");
	Thread.sleep(TIME);
	System.out.println("We both got up and got ready to head out to the PvP Zone.");
	Thread.sleep(TIME);
	System.out.println("The rest were already waiting for us.\n");
	Thread.sleep(TIME);
	System.out.println("Me: \"Sorry for being late guys.\"");
	Thread.sleep(TIME);
	System.out.println("Lucas: \"Trouble getting up?\"\n");
	Thread.sleep(TIME);
	System.out.println("Lucas, Mateo, and Cami all snickered.");
	Thread.sleep(TIME);
	System.out.println("Jovi was smirking.");
	Thread.sleep(TIME);
	System.out.println("These people, I swear to god.");
	Thread.sleep(TIME);
	System.out.println("After talking for a bit we decided to start our journey to the PvP Zone.");
	Thread.sleep(TIME);
	System.out.println("We checked our map.");
	Thread.sleep(TIME);	
}

public static void roomCami() throws InterruptedException{
	System.out.println("Me: \"I think I'll room with Cami.\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"Whaat, leaving me alone?\"\n");
	Thread.sleep(TIME);
	System.out.println("Jovi was pouting.");
	Thread.sleep(TIME);
	System.out.println("He looked like a sad little puppy.");
	Thread.sleep(TIME);
	System.out.println("I regret not choosing him, I've known him for longer too!");
	Thread.sleep(TIME);
	System.out.println("I feel stupid.");
	Thread.sleep(TIME);
	System.out.println("I guess on the bright side, I can get to know Cami a little more.\n");
	Thread.sleep(TIME);
	System.out.println("Cami and I went into our room.");
	Thread.sleep(TIME);
	System.out.println("There were two single beds, one for me and one for her.");
	Thread.sleep(TIME);
	System.out.println("We showered and then talked for a bit.\n");
	Thread.sleep(TIME);
	System.out.println("Cami: \"Thank you for asking me to be on your team!\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"No, I should be thanking you. You really helped us with obtaining the PvP Keys.\"");
	Thread.sleep(TIME);
	System.out.println("Cami: \"That was no biggie. Hehe. I actually have something for you.\"\n");
	Thread.sleep(TIME);
	System.out.println("She reaches into her tophat and pulls out a small book and pen.'n");
	Thread.sleep(TIME);
	System.out.println("Cami: \"Use this to keep track of information on skills and members.\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"Thank you so much!\"");
	Thread.sleep(TIME);
	System.out.println("I put the book into my bag and put it beside my bed. ");
	Thread.sleep(TIME);
	System.out.println("It was about 23:30 by now and we were both tired.");
	Thread.sleep(TIME);
	System.out.println("After drinking some hot chocolate we both decided to go to bed.");
	Thread.sleep(TIME);
	System.out.println("\nThe next morning we got up and got ready to head to the PvP Zone.");
	Thread.sleep(TIME);
	System.out.println("The others were already at the front.");
	Thread.sleep(TIME);
	System.out.println("I glanced at Jovi and he seemed a bit down.");
	Thread.sleep(TIME);
	System.out.println("I guess he really was upset.");
	Thread.sleep(TIME);
	System.out.println("I'll make it up to him for sure!");
	Thread.sleep(TIME);
	System.out.println("We checked our map.");
	Thread.sleep(TIME);
}

public static void speedTraining() throws IOException, InterruptedException {
	BufferedReader in;
	in = new BufferedReader(new InputStreamReader(System.in));
	
	System.out.println("  /\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\/|\r\n" + 
			" |/\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\/ \r\n" + 
			"     ____  ____  _____ _____ ____    _____ ____      _    ___ _   _ ___ _   _  ____    \r\n" + 
			"    / ___||  _ \\| ____| ____|  _ \\  |_   _|  _ \\    / \\  |_ _| \\ | |_ _| \\ | |/ ___|   \r\n" + 
			"    \\___ \\| |_) |  _| |  _| | | | |   | | | |_) |  / _ \\  | ||  \\| || ||  \\| | |  _    \r\n" + 
			"     ___) |  __/| |___| |___| |_| |   | | |  _ <  / ___ \\ | || |\\  || || |\\  | |_| |   \r\n" + 
			"    |____/|_|   |_____|_____|____/    |_| |_| \\_\\/_/   \\_\\___|_| \\_|___|_| \\_|\\____|   \r\n" + 
			"  /\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\/|\r\n" + 
			" |/\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\/ ");
	Thread.sleep(5000);
	System.out.println("\n\nWelcome to Speed Training. This training lobby allows you to increase your speed... if you pass.\n\n");
	Thread.sleep(TIME);
	System.out.println(" _____              _          _____ _         _ ");
	System.out.println("|  |  |___ _ _ _   | |_ ___   |  _  | |___ _ _|_|\tTo play you must dodge the Pegasus' attacks. You can dodge up, down, left, and right. Indicate"); 
	System.out.println("|     | . | | | |  |  _| . |  |   __| | .'| | |_ \tyour option by typing the direction. If you spell the words incorrectly it will be assumed that you"); 
	System.out.println("|__|__|___|_____|  |_| |___|  |__|  |_|__,|_  |_|\tdid not dodge and will take damage. If you dodge seven attacks then you pass. If you pass, you "); 
	System.out.println("                                          |___|  \twill gain +4 speed and 3 coins. Goodluck! "); 
	System.out.println("                                                 \n\n\n");
	Thread.sleep(TIME);
	System.out.println(" _______   _______ _____   _____ _____ ___  ______ _____   _____ _____  ______ _____ _____ _____ _   _ \r\n" + 
			"|_   _\\ \\ / / ___ \\  ___| /  ___|_   _/ _ \\ | ___ \\_   _| |_   _|  _  | | ___ \\  ___|  __ \\_   _| \\ | |\r\n" + 
			"  | |  \\ V /| |_/ / |__   \\ `--.  | |/ /_\\ \\| |_/ / | |     | | | | | | | |_/ / |__ | |  \\/ | | |  \\| |\r\n" + 
			"  | |   \\ / |  __/|  __|   `--. \\ | ||  _  ||    /  | |     | | | | | | | ___ \\  __|| | __  | | | . ` |\r\n" + 
			"  | |   | | | |   | |___  /\\__/ / | || | | || |\\ \\  | |     | | \\ \\_/ / | |_/ / |___| |_\\ \\_| |_| |\\  |\r\n" + 
			"  \\_/   \\_/ \\_|   \\____/  \\____/  \\_/\\_| |_/\\_| \\_| \\_/     \\_/  \\___/  \\____/\\____/ \\____/\\___/\\_| \\_/\r\n" + 
			"                                                                                                       \r\n" + 
			"                                                                                                       ");
	do {
		start = in.readLine();
		if (start.compareToIgnoreCase("start")!=0) {
			System.out.println("ERROR: INVALID RESPONSE");
		}
	} while(start.compareToIgnoreCase("start")!=0);
	
	if (speed>10) {
		System.out.println("AVATAR'S SPEED STATS ARE ABOVE 10: HARD MODE");
		int dodgeCount = 0;
		do {
			System.out.println(name.toUpperCase()+"'S HEALTH: "+ health);
			System.out.println("NUMBER OF SUCCESSFUL DODGES: "+dodgeCount);
			System.out.println("\nWhich direction would you like to dodge?");
			String dodge = in.readLine();
			String pegAttackStr=null;
			int pegAttackInt = Math.toIntExact(Math.round(Math.random()*3));
			switch (pegAttackInt) {
			case 0:
				pegAttackStr = "up";
				break;
			case 1:
				pegAttackStr = "down";
				break;
			case 2:
				pegAttackStr = "left";
				break;
			case 3:	
				pegAttackStr = "right";
				break;
			}
			if (pegAttackStr.compareToIgnoreCase(dodge)==0) {
				health = health -14;
				System.out.println("You jumped infront of the Pegasus. -14 Health\n");
			}else{
				if (dodge.compareToIgnoreCase("up")==0) {
					System.out.println("Good job, you avoided the attack!\n");
					dodgeCount = dodgeCount+1;
				}else if (dodge.compareToIgnoreCase("down")==0){
					System.out.println("Amazing Dodge!! You avoided the attack!\n");
					dodgeCount = dodgeCount+1;
				}else if(dodge.compareToIgnoreCase("left")==0) {
					System.out.println("That was a clean dodge! You avoided the attack!\n");
					dodgeCount = dodgeCount+1;
				}else if (dodge.compareToIgnoreCase("right")==0) {
					System.out.println("Now that was an epic move! You avoided the attack!\n");
					dodgeCount = dodgeCount+1;
				}else {
					health = health-10;
					System.out.println("You spelt the word wrong. -10 Health.");
				}				
			}
		} while ((dodgeCount<7)&&(health>0));
		if (health<=0) {
			System.out.println("YOU DIED");
		} else {
			System.out.println("  __________  ___    _____   _______   ________   ____  ___   __________ __________ \r\n" + 
					" /_  __/ __ \\/   |  /  _/ | / /  _/ | / / ____/  / __ \\/   | / ___/ ___// ____/ __ \\\r\n" + 
					"  / / / /_/ / /| |  / //  |/ // //  |/ / / __   / /_/ / /| | \\__ \\\\__ \\/ __/ / / / /\r\n" + 
					" / / / _, _/ ___ |_/ // /|  // // /|  / /_/ /  / ____/ ___ |___/ /__/ / /___/ /_/ / \r\n" + 
					"/_/ /_/ |_/_/  |_/___/_/ |_/___/_/ |_/\\____/  /_/   /_/  |_/____/____/_____/_____/  \r\n" + 
					"                                                                                    ");
			System.out.println("+4 Speed and +3 coins");
			coins = coins+3;
			speed = speed+4;
		}
	}else {
		System.out.println("AVATAR'S SPEED STATS ARE BELOW 10: EASY MODE");
		int dodgeCount = 0;
		do {
			System.out.println(name.toUpperCase()+"'S HEALTH: "+ health);
			System.out.println("NUMBER OF SUCCESSFUL DODGES: "+dodgeCount);
			System.out.println("\nWhich direction would you like to dodge?");
			String dodge = in.readLine();
			String pegAttackStr=null;
			int pegAttackInt = Math.toIntExact(Math.round(Math.random()*3));
			switch (pegAttackInt) {
			case 0:
				pegAttackStr = "up";
				break;
			case 1:
				pegAttackStr = "down";
				break;
			case 2:
				pegAttackStr = "left";
				break;
			case 3:	
				pegAttackStr = "right";
				break;
			}
			if (pegAttackStr.compareToIgnoreCase(dodge)==0) {
				health = health -7;
				System.out.println("You jumped infront of the Pegasus. -7 Health\n");
			}else{
				if (dodge.compareToIgnoreCase("up")==0) {
					System.out.println("Good job, you avoided the attack!\n");
					dodgeCount = dodgeCount+1;
				}else if (dodge.compareToIgnoreCase("down")==0){
					System.out.println("Amazing Dodge!! You avoided the attack!\n");
					dodgeCount = dodgeCount+1;
				}else if(dodge.compareToIgnoreCase("left")==0) {
					System.out.println("That was a clean dodge! You avoided the attack!\n");
					dodgeCount = dodgeCount+1;
				}else if (dodge.compareToIgnoreCase("right")==0) {
					System.out.println("Now that was an epic move! You avoided the attack!\n");
					dodgeCount = dodgeCount+1;
				}else {
					health = health-5;
					System.out.println("You spelt the word wrong. -5 Health.");
				}				
			}
		} while ((dodgeCount<7)&&(health>0));
		if (health<=0) {
			System.out.println("YOU DIED");
		} else {
			System.out.println("  __________  ___    _____   _______   ________   ____  ___   __________ __________ \r\n" + 
					" /_  __/ __ \\/   |  /  _/ | / /  _/ | / / ____/  / __ \\/   | / ___/ ___// ____/ __ \\\r\n" + 
					"  / / / /_/ / /| |  / //  |/ // //  |/ / / __   / /_/ / /| | \\__ \\\\__ \\/ __/ / / / /\r\n" + 
					" / / / _, _/ ___ |_/ // /|  // // /|  / /_/ /  / ____/ ___ |___/ /__/ / /___/ /_/ / \r\n" + 
					"/_/ /_/ |_/_/  |_/___/_/ |_/___/_/ |_/\\____/  /_/   /_/  |_/____/____/_____/_____/  \r\n" + 
					"                                                                                    ");
			System.out.println("+4 Speed and +3 coins");
			coins = coins+3;
			speed = speed+4;
		}
		
		}
	}

public static void strengthTraining() throws IOException, InterruptedException {
	BufferedReader in;
	in = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("  /\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\/|\r\n" + 
			" |/\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\/ \r\n" + 
			"      ____ _____ ____  _____ _   _  ____ _____ _   _   _____ ____      _    ___ _   _ ___ _   _  ____    \r\n" + 
			"     / ___|_   _|  _ \\| ____| \\ | |/ ___|_   _| | | | |_   _|  _ \\    / \\  |_ _| \\ | |_ _| \\ | |/ ___|   \r\n" + 
			"     \\___ \\ | | | |_) |  _| |  \\| | |  _  | | | |_| |   | | | |_) |  / _ \\  | ||  \\| || ||  \\| | |  _    \r\n" + 
			"      ___) || | |  _ <| |___| |\\  | |_| | | | |  _  |   | | |  _ <  / ___ \\ | || |\\  || || |\\  | |_| |   \r\n" + 
			"     |____/ |_| |_| \\_\\_____|_| \\_|\\____| |_| |_| |_|   |_| |_| \\_\\/_/   \\_\\___|_| \\_|___|_| \\_|\\____|   \r\n" + 
			"  /\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\/|\r\n" + 
			" |/\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\/ ");
	Thread.sleep(6000);
	System.out.println("\n\nWelcome to Strength Training. This training lobby allows you to increase your strength... if you pass.\n\n");
	Thread.sleep(3000);
	System.out.println(" _____              _          _____ _         _ ");
	System.out.println("|  |  |___ _ _ _   | |_ ___   |  _  | |___ _ _|_|\tTo play you must have more health than the Gremlin. You can choose between three options, "); 
	System.out.println("|     | . | | | |  |  _| . |  |   __| | .'| | |_ \tpunch, slice, and kick. Indicate your option by typing the option. If you spell the words"); 
	System.out.println("|__|__|___|_____|  |_| |___|  |__|  |_|__,|_  |_|\tincorrectly, it will be assumed that you did not attack thus resulting in you taking damage. If you "); 
	System.out.println("                                          |___|  \tare able to kill the Gremlin you will pass and gain +4 strength and 3 coins. Goodluck! "); 
	System.out.println("                                                 \n\n\n");
	Thread.sleep(6000);
	System.out.println(" _______   _______ _____   _____ _____ ___  ______ _____   _____ _____  ______ _____ _____ _____ _   _ \r\n" + 
			"|_   _\\ \\ / / ___ \\  ___| /  ___|_   _/ _ \\ | ___ \\_   _| |_   _|  _  | | ___ \\  ___|  __ \\_   _| \\ | |\r\n" + 
			"  | |  \\ V /| |_/ / |__   \\ `--.  | |/ /_\\ \\| |_/ / | |     | | | | | | | |_/ / |__ | |  \\/ | | |  \\| |\r\n" + 
			"  | |   \\ / |  __/|  __|   `--. \\ | ||  _  ||    /  | |     | | | | | | | ___ \\  __|| | __  | | | . ` |\r\n" + 
			"  | |   | | | |   | |___  /\\__/ / | || | | || |\\ \\  | |     | | \\ \\_/ / | |_/ / |___| |_\\ \\_| |_| |\\  |\r\n" + 
			"  \\_/   \\_/ \\_|   \\____/  \\____/  \\_/\\_| |_/\\_| \\_| \\_/     \\_/  \\___/  \\____/\\____/ \\____/\\___/\\_| \\_/\r\n" + 
			"                                                                                                       \r\n" + 
			"                                                                                                       ");
	do {
		start = in.readLine();
		if (start.compareToIgnoreCase("start")!=0) {
			System.out.println("ERROR: INVALID RESPONSE");
		}
	} while(start.compareToIgnoreCase("start")!=0);
	
	if (strength>10) {
		System.out.println("AVATAR'S STRENGTH STATS ARE ABOVE 10: HARD MODE");
		System.out.println(" _____________________________________");
		System.out.println("|Punch --> beats kick | loses to slice|");
		System.out.println("|Slice --> beats punch| loses to kick |");
		System.out.println("|Kick --> beats slice | loses to punch|");
		System.out.println(" _____________________________________\n\n");
		int gremHealth = 100;
		do {
			System.out.println(name.toUpperCase()+"'S HEALTH: "+ health);
			System.out.println("GREMLIN'S HEALTH: "+gremHealth);
			System.out.println("\nWhat attack would you like to use? (Punch, slice, or kick)");
			String attack = in.readLine();
			String gremAttackStr=null;
			int gremAttackInt = Math.toIntExact(Math.round(Math.random()*2));
			if (attack.compareToIgnoreCase("punch")==0) {
				switch (gremAttackInt) {
				case 0:
					System.out.println("You both threw a punch! Attacks Cancelled out. No damage taken or done.");
					break;
				case 1:
					System.out.println("The Gremlin sliced you. You take damage. -5 Health.");
					health = health-5;
					break;
				case 2:	
					System.out.println("The Gremlin tried to kick you. You damaged him. Gremlin loses 15 health.");
					gremHealth = gremHealth -15;
					break;
				}
			}else if(attack.compareToIgnoreCase("slice")==0) {
				switch (gremAttackInt) {
				case 0:
					System.out.println("Gremlin threw a punch. You countered it with a slice. Gremlin loses 15 health.");
					gremHealth = gremHealth -15;
					break;
				case 1:
					System.out.println("You both used a slice on each other! Attacks Cancelled out. No net damage.");	
					break;
				case 2:	
					System.out.println("Gremlin successfully kicked you. You take damage. -5 Health.");
					health = health-5;
					break;
					
				}
			}else if (attack.compareToIgnoreCase("kick")==0) {
				switch (gremAttackInt) {
				case 0:
					System.out.println("Gremlin threw a punch. You take damage. -5 Health.");				
					health = health-5;	
					break;
				case 1:
					System.out.println("You sliced the Gremlin with no mercy! You land a fatal blow. Gremlin loses 15 health.");	
					gremHealth = gremHealth -15;
					break;
				case 2:	
					System.out.println("You both kicked each other. Attacks Cancelled out. No net damage.");
					break;
				}			
			}else {
				System.out.println("You spelt the word wrong. -6 Health.");
				health = health-6;
			}
		}while ((health>0)&&(gremHealth>0));
	}else {
		System.out.println("AVATAR'S STRENGTH STATS ARE BELOW 10: EASY MODE\n");
		System.out.println(" _____________________________________");
		System.out.println("|Punch --> beats kick | loses to slice|");
		System.out.println("|Slice --> beats punch| loses to kick |");
		System.out.println("|Kick --> beats slice | loses to punch|");
		System.out.println(" _____________________________________\n\n");
		int gremHealth = 100;
		do {
			System.out.println(name.toUpperCase()+"'S HEALTH: "+ health);
			System.out.println("GREMLIN'S HEALTH: "+gremHealth);
			System.out.println("\nWhat attack would you like to use? (Punch, slice, or kick)");
			String attack = in.readLine();
			String gremAttackStr=null;
			int gremAttackInt = Math.toIntExact(Math.round(Math.random()*2));
			if (attack.compareToIgnoreCase("punch")==0) {
				switch (gremAttackInt) {
				case 0:
					System.out.println("You both threw a punch! Attacks Cancelled out. No damage taken or done.");
					break;
				case 1:
					System.out.println("The Gremlin sliced you. You take damage. -5 Health.");
					health = health-5;
					break;
				case 2:	
					System.out.println("The Gremlin tried to kick you. You damaged him. Gremlin loses 25 health.");
					gremHealth = gremHealth -25;
					break;
				}
			}else if(attack.compareToIgnoreCase("slice")==0) {
				switch (gremAttackInt) {
				case 0:
					System.out.println("Gremlin threw a punch. You countered it with a slice. Gremlin loses 25 health.");
					gremHealth = gremHealth -25;
					break;
				case 1:
					System.out.println("You both used a slice on each other! Attacks Cancelled out. No net damage.");	
					break;
				case 2:	
					System.out.println("Gremlin successfully kicked you. You take damage. -5 Health.");
					health = health-5;
					break;
					
				}
			}else if (attack.compareToIgnoreCase("kick")==0) {
				switch (gremAttackInt) {
				case 0:
					System.out.println("Gremlin threw a punch. You take damage. -5 Health.");				
					health = health-5;	
					break;
				case 1:
					System.out.println("You sliced the Gremlin with no mercy! You land a fatal blow. Gremlin loses 25 health.");	
					gremHealth = gremHealth -25;
					break;
				case 2:	
					System.out.println("You both kicked each other. Attacks Cancelled out. No net damage.");
					break;
				}			
			}else {
				System.out.println("You spelt the word wrong. -6 Health.");
				health = health-6;
			}
		}while ((health>0)&&(gremHealth>0));			
	}
	if (health<=0) {
		System.out.println("YOU DIED");
	} else {
		System.out.println("Gremlin Died.");
		System.out.println("  __________  ___    _____   _______   ________   ____  ___   __________ __________ \r\n" + 
				" /_  __/ __ \\/   |  /  _/ | / /  _/ | / / ____/  / __ \\/   | / ___/ ___// ____/ __ \\\r\n" + 
				"  / / / /_/ / /| |  / //  |/ // //  |/ / / __   / /_/ / /| | \\__ \\\\__ \\/ __/ / / / /\r\n" + 
				" / / / _, _/ ___ |_/ // /|  // // /|  / /_/ /  / ____/ ___ |___/ /__/ / /___/ /_/ / \r\n" + 
				"/_/ /_/ |_/_/  |_/___/_/ |_/___/_/ |_/\\____/  /_/   /_/  |_/____/____/_____/_____/  \r\n" + 
				"                                                                                    ");
		System.out.println("+4 Strength and +3 coins");
		coins = coins+3;
		strength = strength+4;
	}
}

public static void wskillTraining() throws IOException, InterruptedException {
	BufferedReader in;
	in = new BufferedReader(new InputStreamReader(System.in));
	
	System.out.println("  /\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\/|\r\n" + 
			" |/\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\/ \r\n" + 
			"     __        _______    _    ____   ___  _   _   ____  _  _____ _     _       _____ ____      _    ___ _   _ ___ _   _  ____   \r\n" + 
			"     \\ \\      / / ____|  / \\  |  _ \\ / _ \\| \\ | | / ___|| |/ /_ _| |   | |     |_   _|  _ \\    / \\  |_ _| \\ | |_ _| \\ | |/ ___|  \r\n" + 
			"      \\ \\ /\\ / /|  _|   / _ \\ | |_) | | | |  \\| | \\___ \\| ' / | || |   | |       | | | |_) |  / _ \\  | ||  \\| || ||  \\| | |  _   \r\n" + 
			"       \\ V  V / | |___ / ___ \\|  __/| |_| | |\\  |  ___) | . \\ | || |___| |___    | | |  _ <  / ___ \\ | || |\\  || || |\\  | |_| |  \r\n" + 
			"        \\_/\\_/  |_____/_/   \\_\\_|    \\___/|_| \\_| |____/|_|\\_\\___|_____|_____|   |_| |_| \\_\\/_/   \\_\\___|_| \\_|___|_| \\_|\\____|  \r\n" + 
			"  /\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\/|\r\n" + 
			" |/\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\/ ");
	Thread.sleep(5000);
	System.out.println("\n\nWelcome to Strength Training. This training lobby allows you to increase your strength... if you pass.\n\n");
	Thread.sleep(2000);
	System.out.println(" _____              _          _____ _         _ \tTo play you must battle the wizard. You must launch three attacks using your weapon. If you");
	System.out.println("|  |  |___ _ _ _   | |_ ___   |  _  | |___ _ _|_|\tland two out of the three attacks you land a hit on the wizard. Indicate your option by typing the "); 
	System.out.println("|     | . | | | |  |  _| . |  |   __| | .'| | |_ \toption. If you type the numbers incorrectly, it will be assumed that you did not attack thus resulting "); 
	System.out.println("|__|__|___|_____|  |_| |___|  |__|  |_|__,|_  |_|\tin you taking damage. If you are able to land three (five for hard difficulty)  hits on the wizard you  "); 
	System.out.println("                                          |___|  \tpass and gain +4 weapon skill and 3 coins. Goodluck! "); 
	System.out.println("                                                 \n\n\n");
	Thread.sleep(6000);
	System.out.println(" _______   _______ _____   _____ _____ ___  ______ _____   _____ _____  ______ _____ _____ _____ _   _ \r\n" + 
			"|_   _\\ \\ / / ___ \\  ___| /  ___|_   _/ _ \\ | ___ \\_   _| |_   _|  _  | | ___ \\  ___|  __ \\_   _| \\ | |\r\n" + 
			"  | |  \\ V /| |_/ / |__   \\ `--.  | |/ /_\\ \\| |_/ / | |     | | | | | | | |_/ / |__ | |  \\/ | | |  \\| |\r\n" + 
			"  | |   \\ / |  __/|  __|   `--. \\ | ||  _  ||    /  | |     | | | | | | | ___ \\  __|| | __  | | | . ` |\r\n" + 
			"  | |   | | | |   | |___  /\\__/ / | || | | || |\\ \\  | |     | | \\ \\_/ / | |_/ / |___| |_\\ \\_| |_| |\\  |\r\n" + 
			"  \\_/   \\_/ \\_|   \\____/  \\____/  \\_/\\_| |_/\\_| \\_| \\_/     \\_/  \\___/  \\____/\\____/ \\____/\\___/\\_| \\_/\r\n" + 
			"                                                                                                       \r\n" + 
			"                                                                                                       ");
	do {
		start = in.readLine();
		if (start.compareToIgnoreCase("start")!=0) {
			System.out.println("ERROR: INVALID RESPONSE");
		}
	} while(start.compareToIgnoreCase("start")!=0);
	
	int target [] = new int [3];
	int wizAtk [] = new int [3];
	int hit []= new int [3];
	if (strength>10) {
		System.out.println("AVATAR'S WEAPON SKILL STATS ARE ABOVE 10: HARD MODE");
		int wizHits =5;
		do {
			int goodHits=0;
			int wizGoodHits=0;
			System.out.println(name.toUpperCase()+"'S HEALTH: "+ health);
			System.out.println("REMAINING LIVES FOR WIZARD: "+wizHits);
			for (int i=0;i<3;i++) {
				wizAtk [i] = Math.toIntExact(Math.round(Math.random()*2));
				target [i]= Math.toIntExact(Math.round(Math.random()*2));
			}
			System.out.println("What is the first attack you would like to use?(Flurry of daggers, blink stab, burst slice)");
			String input = (in.readLine()).toLowerCase();
			if (input.compareToIgnoreCase("flurry of daggers")==0) {
				hit[0] = 0;	
			} else if (input.compareToIgnoreCase("blink stab")==0) {
				hit[0] = 1;
			}else if (input.compareToIgnoreCase("burst slice")==0) {
				hit[0] = 2;
			}else {
				hit[0] = 3;
			}
			System.out.println("What is the second attack you would like to use?(Flurry of daggers, blink stab, burst slice)");
			input = (in.readLine()).toLowerCase();
			if (input.compareToIgnoreCase("flurry of daggers")==0) {
				hit[1] = 0;	
			} else if (input.compareToIgnoreCase("blink stab")==0) {
				hit[1] = 1;
			}else if (input.compareToIgnoreCase("burst slice")==0) {
				hit[1] = 2;
			}else {
				hit[1] = 3;
			}
			System.out.println("What is the third attack you would like to use? (Flurry of daggers, blink stab, burst slice)");
			input = (in.readLine()).toLowerCase();
			if (input.compareToIgnoreCase("flurry of daggers")==0) {
				hit[2] = 0;	
			} else if (input.compareToIgnoreCase("blink stab")==0) {
				hit[2] = 1;
			}else if (input.compareToIgnoreCase("burst slice")==0) {
				hit[2] = 2;
			}else {
				hit[2] = 3;
			}
			
			for (int i=0; i<3; i++) {
				System.out.println("Attack # "+(i+1));
				if (hit[i] == target[i]) {
					System.out.println("You hit a target");
					goodHits = goodHits+1;
				} else if (hit[i]>=3) {
					System.out.println("You spelt an ability wrong. Free hit for Wizard.");
					wizGoodHits = wizGoodHits+1;
				} else {
					System.out.println("Missed the target.");
				}
				if (wizAtk[i] == target[i]) {
					System.out.println("Wizard hit a target.");
					wizGoodHits=wizGoodHits+1;	
				}else {
					System.out.println("Wizard missed the target.");
				}
			}
			if (wizGoodHits>goodHits) {
				System.out.println("Wizard launched more successful attacks. You take damage. -5 Health.");
				health = health-5;
			} else if (wizGoodHits<goodHits) {
				System.out.println("You launched more successful attacks. Wizard loses a life.");
				wizHits = wizHits-1;
			} else {
				System.out.println("You both got the same amount of hits. No damage taken or dealt.");
			}
		}while ((wizHits>0)&&(health>0));
	}else {
		System.out.println("AVATAR'S WEAPON SKILL STATS ARE BELOW 25: EASY MODE");
		int wizHits =3;
		do {
			int goodHits=0;
			int wizGoodHits=0;
			System.out.println(name.toUpperCase()+"'S HEALTH: "+ health);
			System.out.println("REMAINING LIVES FOR WIZARD: "+wizHits);
			for (int i=0;i<3;i++) {
				wizAtk [i] = Math.toIntExact(Math.round(Math.random()*2));
				target [i]= Math.toIntExact(Math.round(Math.random()*2));
			}
			System.out.println("What is the first attack you would like to use? (flurry of daggers, blink stab or burst slice)");
			String input = (in.readLine()).toLowerCase();
			if (input.compareToIgnoreCase("flurry of daggers")==0) {
				hit[0] = 0;	
			} else if (input.compareToIgnoreCase("blink stab")==0) {
				hit[0] = 1;
			}else if (input.compareToIgnoreCase("burst slice")==0) {
				hit[0] = 2;
			}else {
				hit[0] = 3;
			}
			System.out.println("What is the second attack you would like to use?(flurry of daggers, blink stab or burst slice)");
			input = (in.readLine()).toLowerCase();
			if (input.compareToIgnoreCase("flurry of daggers")==0) {
				hit[1] = 0;	
			} else if (input.compareToIgnoreCase("blink stab")==0) {
				hit[1] = 1;
			}else if (input.compareToIgnoreCase("burst slice")==0) {
				hit[1] = 2;
			}else {
				hit[1] = 3;
			}
			System.out.println("What is the third attack you would like to use?(flurry of daggers, blink stab or burst slice)");
			input = (in.readLine()).toLowerCase();
			if (input.compareToIgnoreCase("flurry of daggers")==0) {
				hit[2] = 0;	
			} else if (input.compareToIgnoreCase("blink stab")==0) {
				hit[2] = 1;
			}else if (input.compareToIgnoreCase("burst slice")==0) {
				hit[2] = 2;
			}else {
				hit[2] = 3;
			}
			
			for (int i=0; i<3; i++) {
				System.out.println("Attack # "+(i+1));
				if (hit[i] == target[i]) {
					System.out.println("You hit a target");
					goodHits = goodHits+1;
				} else if (hit[i]>=3) {
					System.out.println("You spelt an ability wrong. Free hit for Wizard.");
					wizGoodHits = wizGoodHits+1;
				} else {
					System.out.println("Missed the target.");
				}
				if (wizAtk[i] == target[i]) {
					System.out.println("Wizard hit a target.");
					wizGoodHits=wizGoodHits+1;	
				}else {
					System.out.println("Wizard missed the target.");
				}
			}
			if (wizGoodHits>goodHits) {
				System.out.println("Wizard launched more successful attacks. You take damage. -5 Health.");
				health = health-5;
			} else if (wizGoodHits<goodHits) {
				System.out.println("You launched more successful attacks. Wizard loses a life.");
				wizHits = wizHits-1;
			} else {
				System.out.println("You both got the same amount of hits. No damage taken or dealt.");
			}
		}while ((wizHits>0)&&(health>0));
	}
	if (health<=0) {
		System.out.println("YOU DIED");
	}else {
		System.out.println("You killed the Wizard.");
		System.out.println("  __________  ___    _____   _______   ________   ____  ___   __________ __________ \r\n" + 
				" /_  __/ __ \\/   |  /  _/ | / /  _/ | / / ____/  / __ \\/   | / ___/ ___// ____/ __ \\\r\n" + 
				"  / / / /_/ / /| |  / //  |/ // //  |/ / / __   / /_/ / /| | \\__ \\\\__ \\/ __/ / / / /\r\n" + 
				" / / / _, _/ ___ |_/ // /|  // // /|  / /_/ /  / ____/ ___ |___/ /__/ / /___/ /_/ / \r\n" + 
				"/_/ /_/ |_/_/  |_/___/_/ |_/___/_/ |_/\\____/  /_/   /_/  |_/____/____/_____/_____/  \r\n" + 
				"                                                                                    ");
		System.out.println("+4 Weapon Skill and +3 coins");
		coins = coins+3;
		wskill = wskill+4;
	}
		

}

public static void pvpmenu() {
	System.out.println(" __________________________________________________________________________");
	System.out.println("|   ___         ___   _____   ___    __  __             __    __       \r\n" + 
			"|  / _ \\__   __/ _ \\ / _  /  /___\\/\\ \\ \\/__\\   /\\/\\    /__\\/\\ \\ \\/\\ /\\ \r\n" + 
			"| / /_)/\\ \\ / / /_)/ \\// /  //  //  \\/ /_\\    /    \\  /_\\ /  \\/ / / \\ \\\r\n" + 
			"|/ ___/  \\ V / ___/   / //\\/ \\_// /\\  //__   / /\\/\\ \\//__/ /\\  /\\ \\_/ /\r\n" + 
			"|\\/       \\_/\\/      /____/\\___/\\_\\ \\/\\__/   \\/    \\/\\__/\\_\\ \\/  \\___/ ");
	System.out.println("|What would you like to access: \n|[1] Avatar Stats \n|[2] Inventory \n|[3] Journal \n|[4] Market \n|[5] Speed \n|[6] Strength \n|[7] Weapon Skill \n|[8] X/it Tournament");
	System.out.println(" __________________________________________________________________________");

}
public static void tournamentGame() throws IOException, InterruptedException {
	BufferedReader in;
	in = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("\n\n #################################################################################");
	System.out.println("\n ####### ### #     #    #    #           #####  #######    #     #####  ####### \r\n" + 
			" #        #  ##    #   # #   #          #     #    #      # #   #     # #       \r\n" + 
			" #        #  # #   #  #   #  #          #          #     #   #  #       #       \r\n" + 
			" #####    #  #  #  # #     # #           #####     #    #     # #  #### #####   \r\n" + 
			" #        #  #   # # ####### #                #    #    ####### #     # #       \r\n" + 
			" #        #  #    ## #     # #          #     #    #    #     # #     # #       \r\n" + 
			" #       ### #     # #     # #######     #####     #    #     #  #####  ####### \n");
	System.out.println("#################################################################################\n\n\n");
	Thread.sleep(5000);
	System.out.println("\n\nWelcome to the Final Stage. This stage will determine if you win or lose the game.\n\n");
	Thread.sleep(TIME);
	System.out.println(" _____              _          _____ _         _ ");
	System.out.println("|  |  |___ _ _ _   | |_ ___   |  _  | |___ _ _|_|\tThis stage is very similar to the minigames you've been playing. Actually, it is exactly identical to "); 
	System.out.println("|     | . | | | |  |  _| . |  |   __| | .'| | |_ \tthe mini-games, except for one thing, it's randomized. You can dodge attacks (up, down, left,"); 
	System.out.println("|__|__|___|_____|  |_| |___|  |__|  |_|__,|_  |_|\tright). You can attack (punch, slice, kick). You can use your weapon to fight (Flurry of daggers,  "); 
	System.out.println("                                          |___|  \tblink stab, burst slice). In between each attack you have the option to consume a health potion  "); 
	System.out.println("                                                 \tto regenerate health, hopefully you stocked up! Now the rest is up to you, goodluck!\n\n\n");
	Thread.sleep(TIME);
	System.out.println(" _______   _______ _____   _____ _____ ___  ______ _____   _____ _____  ______ _____ _____ _____ _   _ \r\n" + 
			"|_   _\\ \\ / / ___ \\  ___| /  ___|_   _/ _ \\ | ___ \\_   _| |_   _|  _  | | ___ \\  ___|  __ \\_   _| \\ | |\r\n" + 
			"  | |  \\ V /| |_/ / |__   \\ `--.  | |/ /_\\ \\| |_/ / | |     | | | | | | | |_/ / |__ | |  \\/ | | |  \\| |\r\n" + 
			"  | |   \\ / |  __/|  __|   `--. \\ | ||  _  ||    /  | |     | | | | | | | ___ \\  __|| | __  | | | . ` |\r\n" + 
			"  | |   | | | |   | |___  /\\__/ / | || | | || |\\ \\  | |     | | \\ \\_/ / | |_/ / |___| |_\\ \\_| |_| |\\  |\r\n" + 
			"  \\_/   \\_/ \\_|   \\____/  \\____/  \\_/\\_| |_/\\_| \\_| \\_/     \\_/  \\___/  \\____/\\____/ \\____/\\___/\\_| \\_/\r\n" + 
			"                                                                                                       \r\n" + 
			"                                                                                                       ");
	
	do {
		start = in.readLine();
		if (start.compareToIgnoreCase("start")!=0) {
			System.out.println("ERROR: INVALID RESPONSE");
		}
	} while(start.compareToIgnoreCase("start")!=0);
	System.out.println("THERE ARE 10 ROUNDS.");
	int countRounds = 0;
	int randomRound=0;
	do {
		System.out.println("ROUND COUNTER: "+(countRounds+1));
		randomRound = Math.toIntExact(Math.round(Math.random()*2));
		switch(randomRound) {
		case 0:
			System.out.println("DODGE THE ATTACK.");
			int dodgeCount = 0;
			do {
				System.out.println(name.toUpperCase()+"'S HEALTH: "+ health);
				System.out.println("NUMBER OF SUCCESSFUL DODGES: "+dodgeCount);
				System.out.println("\nWhich direction would you like to dodge? (up, down, left, right)");
				String dodge = in.readLine();
				String pegAttackStr=null;
				int pegAttackInt = Math.toIntExact(Math.round(Math.random()*3));
				switch (pegAttackInt) {
				case 0:
					pegAttackStr = "up";
					break;
				case 1:
					pegAttackStr = "down";
					break;
				case 2:
					pegAttackStr = "left";
					break;
				case 3:	
					pegAttackStr = "right";
					break;
				}
				if (pegAttackStr.compareToIgnoreCase(dodge)==0) {
					health = health -7;
					System.out.println("You jumped infront of the opponent. -7 Health\n");
				}else{
					if (dodge.compareToIgnoreCase("up")==0) {
						System.out.println("Good job, you avoided the attack!\n");
						dodgeCount = dodgeCount+1;
					}else if (dodge.compareToIgnoreCase("down")==0){
						System.out.println("Amazing Dodge!! You avoided the attack!\n");
						dodgeCount = dodgeCount+1;
					}else if(dodge.compareToIgnoreCase("left")==0) {
						System.out.println("That was a clean dodge! You avoided the attack!\n");
						dodgeCount = dodgeCount+1;
					}else if (dodge.compareToIgnoreCase("right")==0) {
						System.out.println("Now that was an epic move! You avoided the attack!\n");
						dodgeCount = dodgeCount+1;
					}else {
						health = health-6;
						System.out.println("You spelt the word wrong. -6 Health.");
					}				
				}
			} while ((dodgeCount<4)&&(health>0));
			countRounds = countRounds+1;
			break;
		case 1:
			System.out.println("USE AN ATTACK.");
			System.out.println(" _____________________________________");
			System.out.println("|Punch --> beats kick | loses to slice|");
			System.out.println("|Slice --> beats punch| loses to kick |");
			System.out.println("|Kick --> beats slice | loses to punch|");
			System.out.println(" _____________________________________\n\n");
			int gremHealth = 100;
			do {
				System.out.println(name.toUpperCase()+"'S HEALTH: "+ health);
				System.out.println("OPPONENT'S HEALTH: "+gremHealth);
				System.out.println("\nWhat attack would you like to use? (Punch, slice, or kick)");
				String attack = in.readLine();
				int gremAttackInt = Math.toIntExact(Math.round(Math.random()*2));
				if (attack.compareToIgnoreCase("punch")==0) {
					switch (gremAttackInt) {
					case 0:
						System.out.println("You both threw a punch! Attacks Cancelled out. No damage taken or done.");
						break;
					case 1:
						System.out.println("The opponent sliced you. You take damage. -10 Health.");
						health = health-10;
						break;
					case 2:	
						System.out.println("The opponent tried to kick you. You damaged him. Opponent loses 25 health.");
						gremHealth = gremHealth -25;
						break;
					}
				}else if(attack.compareToIgnoreCase("slice")==0) {
					switch (gremAttackInt) {
					case 0:
						System.out.println("Opponent threw a punch. You countered it with a slice. Opponent loses 25 health.");
						gremHealth = gremHealth -25;
						break;
					case 1:
						System.out.println("You both used a slice on each other! Attacks Cancelled out. No net damage.");	
						break;
					case 2:	
						System.out.println("Opponent successfully kicked you. You take damage. -10 Health.");
						health = health-10;
						break;
						
					}
				}else if (attack.compareToIgnoreCase("kick")==0) {
					switch (gremAttackInt) {
					case 0:
						System.out.println("Opponent threw a punch. You take damage. -10 Health.");				
						health = health-10;	
						break;
					case 1:
						System.out.println("You sliced the opponent with no mercy! You land a fatal blow. Opponent loses 25 health.");	
						gremHealth = gremHealth -25;
						break;
					case 2:	
						System.out.println("You both kicked each other. Attacks Cancelled out. No net damage.");
						break;
					}			
				}else {
					System.out.println("You spelt the word wrong. -6 Health.");
					health = health-6;
				}
			}while ((health>0)&&(gremHealth>0));
			countRounds = countRounds+1;
			break;
		case 2: 
			System.out.println("USE YOUR WEAPON.");
			int target[] = new int[3];
			int wizAtk [] = new int [3];
			int hit []= new int [3];
			int wizHits =2;
			do {
				int goodHits=0;
				int wizGoodHits=0;
				System.out.println(name.toUpperCase()+"'S HEALTH: "+ health);
				System.out.println("REMAINING FATAL BLOW SPOTS FOR OPPONENT: "+wizHits);
				for (int i=0;i<3;i++) {
					wizAtk [i] = Math.toIntExact(Math.round(Math.random()*2));
					target [i]= Math.toIntExact(Math.round(Math.random()*2));
				}
				System.out.println("What is the first attack you would like to use?(Flurry of daggers, blink stab, burst slice)");
				String input = (in.readLine()).toLowerCase();
				if (input.compareToIgnoreCase("flurry of daggers")==0) {
					hit[0] = 0;	
				} else if (input.compareToIgnoreCase("blink stab")==0) {
					hit[0] = 1;
				}else if (input.compareToIgnoreCase("burst slice")==0) {
					hit[0] = 2;
				}else {
					hit[0] = 3;
				}
				System.out.println("What is the second attack you would like to use?(Flurry of daggers, blink stab, burst slice)");
				input = (in.readLine()).toLowerCase();
				if (input.compareToIgnoreCase("flurry of daggers")==0) {
					hit[1] = 0;	
				} else if (input.compareToIgnoreCase("blink stab")==0) {
					hit[1] = 1;
				}else if (input.compareToIgnoreCase("burst slice")==0) {
					hit[1] = 2;
				}else {
					hit[1] = 3;
				}
				System.out.println("What is the third attack you would like to use? (Flurry of daggers, blink stab, burst slice)");
				input = (in.readLine()).toLowerCase();
				if (input.compareToIgnoreCase("flurry of daggers")==0) {
					hit[2] = 0;	
				} else if (input.compareToIgnoreCase("blink stab")==0) {
					hit[2] = 1;
				}else if (input.compareToIgnoreCase("burst slice")==0) {
					hit[2] = 2;
				}else {
					hit[2] = 3;
				}
				
				for (int i=0; i<3; i++) {
					System.out.println("Attack # "+(i+1));
					if (hit[i] == target[i]) {
						System.out.println("You hit the opponent.");
						goodHits = goodHits+1;
					} else if (hit[i]>=3) {
						System.out.println("You spelt an ability wrong. You didn't move and the opponent hit you.");
						wizGoodHits = wizGoodHits+1;
					} else {
						System.out.println("Missed the target.");
					}
					if (wizAtk[i] == target[i]) {
						System.out.println("The opponent hit you.");
						wizGoodHits=wizGoodHits+1;	
					}else {
						System.out.println("The opponent hit you.");
					}
				}
				if (wizGoodHits>goodHits) {
					System.out.println("Opponent launched more successful attacks. You take damage. -15 Health.");
					health = health-15;
				} else if (wizGoodHits<goodHits) {
					System.out.println("You launched more successful attacks. Opponent takes fatal damage.");
					wizHits = wizHits-1;
				} else {
					System.out.println("You both got the same amount of hits. No damage taken or dealt.");
				}
			}while ((wizHits>0)&&(health>0));
			countRounds = countRounds+1;
			break;
		}	
		if (health>0) {
			int flag = -1;
			int check=-1;
			System.out.println("Would you like to use a health potion? (Type 'yes')");
			String search= (in.readLine()).toLowerCase();
			if (search.contentEquals("yes")) {
				do {
					String healthRe="Health Potion";
					for (int i=0;i<5;i++) {
						if (healthRe.contentEquals(invSlotName[i])) {
							flag =i;
							check=1;
						}
						}
						if ((check>=0)&&(invSlotQuant[flag]>0)) {
							if (invSlotQuant[flag]>0) {
								invSlotQuant[flag]=invSlotQuant[flag]-1;
								System.out.println("You consumed 1 health potion.");
								health = health + 50;
								if (health>100) {
									health = 100;
								}
								System.out.println(name+"'s Health is "+health);
								System.out.println("Health Potions remaining: "+invSlotQuant[flag]);
							}
							check=-1;
						}else {
							System.out.println("You do not have health potions.");
						}
					System.out.println("Would you like to use a health potion? (Type 'yes')");
					search= (in.readLine()).toLowerCase();
				}while (search.contentEquals("yes"));

			}
		}
	}while ((countRounds<10)&&(health>0));
}
public static int decision() throws IOException{
	BufferedReader in;
	in = new BufferedReader (new InputStreamReader (System.in));
	boolean valid = false;
	while (valid == false) {
		decisionStr = in.readLine();
		valid = true;
		switch (decisionStr) {
		case "1":
			decision = 1;	
			break;
		case "2":
			decision = 2;
			break;
		default:
			System.out.println("ERROR. Enter a valid answer.");
			valid = false;
		}
	}
	return decision;
}
/***
 * Cutscene that plays when user reaches the X/it tournament stage
 */
public static void preTournamentScene() throws InterruptedException{
	System.out.println("\n\nAfter many hard weeks of training be had built up enough stats.");
	Thread.sleep(TIME);
	System.out.println("We have become incomparably fast to our original speed.");
	Thread.sleep(TIME);
	System.out.println("Our strength had quadrupled.");
	Thread.sleep(TIME);
	System.out.println("And we have become extremely experienced with our weapons.\n");
	Thread.sleep(TIME);
	System.out.println("We were now unrecognizable.");
	Thread.sleep(TIME);
	System.out.println("The five of us lined up in front of a tall building.");
	Thread.sleep(TIME);
	System.out.println("There was a huge banner spanning over the doors.\n");
	Thread.sleep(TIME);
	System.out.println("Me: \"X/it Tournament 20XX.\"\n");
	Thread.sleep(TIME);
	System.out.println("We were ready to battle it out.");
	Thread.sleep(TIME);
	System.out.println("It was the battle go return to the real world.\n");
	Thread.sleep(TIME);
	System.out.println("Lucas: \"Let's get this!\"");
	Thread.sleep(TIME);
	System.out.println("Cami: \"Let's get out of this game.\"");
	Thread.sleep(TIME);
	System.out.println("Mateo: \"We're going to win it all!!\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"I'm ready to go back...\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"... to the real world.\"\n");
	Thread.sleep(TIME);
	System.out.println("Jovi, Lucas, Mateo, Cami, and I stepped into the tournament building.");
	Thread.sleep(TIME);
	System.out.println("It was time to fight.");
	Thread.sleep(TIME);
	System.out.println("We witnessed it all, bloody faces, broken bones, and even death.");
	Thread.sleep(TIME);
	System.out.println("The tournament was harsh.");
	Thread.sleep(TIME);
	System.out.println("Everyone had the same goal as us, we all wanted to get out.");
	Thread.sleep(TIME);
	System.out.println("But only the worthy squad will make it out.\n");
	Thread.sleep(TIME);
	System.out.println("After countless battles we had made it to the finals. ");
	Thread.sleep(TIME);
	System.out.println("It was hard but we made it.");
	Thread.sleep(TIME);
	System.out.println("Just one game left... ");
	Thread.sleep(TIME);
}

public static void winGameCutscene() throws IOException, InterruptedException {
	System.out.println("We did it.");
	Thread.sleep(TIME);
	System.out.println("We won the tournament.");
	Thread.sleep(TIME);
	System.out.println("This means that we could leave the game.");
	Thread.sleep(TIME);
	System.out.println("The other team was strong.");
	Thread.sleep(TIME);
	System.out.println("They were equally as strong as us.");
	Thread.sleep(TIME);
	System.out.println("However, we had one thing that they didn't.");
	Thread.sleep(TIME);
	System.out.println("A friendship bond stronger than diamond.\n");
	Thread.sleep(TIME);
	System.out.println("The announcer emerged from an underground platform.");
	Thread.sleep(TIME);
	System.out.println("It was a tall person hidden in a black cloak.");
	Thread.sleep(TIME);
	System.out.println("The person removed their cloak.");
	Thread.sleep(TIME);
	System.out.println("I recognized the man.");
	Thread.sleep(TIME);
	System.out.println("It was Axel Cullen, the CEO of Tree Games.");
	Thread.sleep(TIME);
	System.out.println("He was the creator of this and many other of my favourite games, even my all-time favourite, Battle Legends.\n");
	Thread.sleep(TIME);
	System.out.println("I was struck by awe.");
	Thread.sleep(TIME);
	System.out.println("My role model was standing in front of me.");
	Thread.sleep(TIME);
	System.out.println("How cool was that?\n");
	Thread.sleep(TIME);
	System.out.println("Axel: \"Congratulations Elle, Jovi, Mateo, Lucas, and Cami. You are the champions of X/it Tournament 20XX. As \n       promised, you will gain access to log-in and log-out of the game at your own free will along with one\n        million for each of you and a 3-night stay in Bali! You will now be released from the game.\"");
	Thread.sleep(TIME);
	System.out.println("\nMe: \"I can't believe we did it\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"You bet we did.\"");
	Thread.sleep(TIME);
	System.out.println("We celebrated for hours and eventually returned home.");
	Thread.sleep(TIME);
	System.out.println("We had a blast.");
	Thread.sleep(TIME);
	System.out.println("The experience was amazing but I'm glad to be back in the real world.\n");
	Thread.sleep(TIME);
	System.out.println("\n\n\n____\r\n" + 
			" \\\\  `.          \r\n" + 
			"  \\\\   `.\r\n" + 
			"   \\ \\   `.\r\n" + 
			"    \\\\01838`.\r\n" + 
			"    :. . . . `._______________________.-~|~~-._\r\n" + 
			"    \\                                 ---'-----`-._\r\n" + 
			"     /\"\"\"\"\"\"\"/             _...---------..         ~-._________\r\n" + 
			"    //     .`_________  .-`           \\ .-~           /\r\n" + 
			"   //    .'       ||__.~             .-~_____________/\r\n" + 
			"  //___.`           .~            .-~\r\n" + 
			"                  .~           .-~\r\n" + 
			"                 .~         _.-~\r\n" + 
			"                `-_____.-~'\n\n\n");
	Thread.sleep(3000);
	System.out.println("A few weeks after completing the game, the five of us decided to go on a trip to Bali.\n");
	Thread.sleep(TIME);
	System.out.println("Me: \"Hey Guys!\"");
	Thread.sleep(TIME);
	System.out.println("Lucas: \"Hey El!\"");
	Thread.sleep(TIME);
	System.out.println("Mateo: \"What's up El?\"");
	Thread.sleep(TIME);
	System.out.println("Cami: \"El!!!!!!\"\n");
	Thread.sleep(TIME);
	System.out.println("The three of them ran up to me and hugged me.");
	Thread.sleep(TIME);
	System.out.println("I missed these guys so much.");
	Thread.sleep(TIME);
	System.out.println("One was missing though.");
	Thread.sleep(TIME);
	System.out.println("Jovi where was he?\n");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"Ahem where's my hug?\"\n");
	Thread.sleep(TIME);
	System.out.println("There he was.");
	Thread.sleep(TIME);
	System.out.println("I ran over to him and gave him a hug.");
	Thread.sleep(TIME);
	System.out.println("His face turned bright red.");
	Thread.sleep(TIME);
	System.out.println("He was so cute.\n");
	Thread.sleep(TIME);
	System.out.println("We all celebrated together for the whole trip.");
	Thread.sleep(TIME);
	System.out.println("I loved being around my best friends.");
	Thread.sleep(TIME);
}

public static void specialEnding() throws  InterruptedException{
	System.out.println("3 days have passed by so quickly.");
	Thread.sleep(TIME);
	System.out.println("It was now the night before our flight back home.\n");
	Thread.sleep(TIME);
	System.out.println("I was sitting out on the dock by the ocean thinking about how great my life was going.");
	Thread.sleep(TIME);
	System.out.println("I was so lucky to be me.");
	Thread.sleep(TIME);
	System.out.println("Soon I heard footsteps approach me.");
	Thread.sleep(TIME);
	System.out.println("Before I could turn around to see who it was two hands covered me.");
	Thread.sleep(TIME);
	System.out.println("\nMe: \"Hey who are you??\"");
	Thread.sleep(TIME);
	System.out.println("Person: \"Guess who?\"");
	Thread.sleep(TIME);
	System.out.println("\nThat wasn't hard.");
	Thread.sleep(TIME);
	System.out.println("I knew very well who it was.");
	Thread.sleep(TIME);
	System.out.println("\nMe: \"Joviiii move you hands.\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"What?How'd you guess so fast?\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"How could I not recognize your voice?\"\n");
	Thread.sleep(TIME);
	System.out.println("He moved his hands off my eyes and picked me up.");
	Thread.sleep(TIME);
	System.out.println("He held me in a princess cradle.\n");
	Thread.sleep(TIME);
	System.out.println("Me:\"Hey-wait-J-Jovi what are you doing?\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"Nothing I just wanna take you inside.\"\n");
	Thread.sleep(TIME);
	System.out.println("He threw me over his shoulder and kept walking.");
	Thread.sleep(TIME);
	System.out.println("I couldn't see where we were going because I was facing behind.");
	Thread.sleep(TIME);
	System.out.println("After several minutes of walking he put me down onto a bed.");
	Thread.sleep(TIME);
	System.out.println("I got up and sat straight up.\n");
	Thread.sleep(TIME);
	System.out.println("I looked around me.");
	Thread.sleep(TIME);
	System.out.println("The room was breath-taking.");
	Thread.sleep(TIME);
	System.out.println("I was sitting on a waterbed in the middle of a pool.");
	Thread.sleep(TIME);
	System.out.println("Around the bed were flower petals and water-lilies in the water.");
	Thread.sleep(TIME);
	System.out.println("Above me was the beautiful night sky.");
	Thread.sleep(TIME);
	System.out.println("The walls were covered in a rainforest landscape and there were so many plants around us.");
	Thread.sleep(TIME);
	System.out.println("There were several lights that looked like the moon and stars all around the room.");
	Thread.sleep(TIME);
	System.out.println("I felt like I was in the middle of a pond in a tropical rainforest.\n");
	Thread.sleep(TIME);
	System.out.println("Me: \"This is so beautiful...\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"Just like you.\"\n");
	Thread.sleep(TIME);
	System.out.println("Him saying that made my heart flutter like a butterfly gracefully moving from one flower to another.");
	Thread.sleep(TIME);
	System.out.println("He got onto the bed and sat in front of me.");
	Thread.sleep(TIME);
	System.out.println("J took my hands and held them. ");
	Thread.sleep(TIME);
	System.out.println("He looked at me in the eyes and started to speak.\n");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"Elle's your name isn't it?\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"How did you know?\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"Once you sent me a game file for a game you created for school and you had that name in it. I \n       didn't think much of it back then but after these past few weeks it all made sense.\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"Ahh, I didn't think I would've revealed myself so easily.\"");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"That's not the reason I brought you here though.\"");
	Thread.sleep(TIME);
	System.out.println("Me: \"What is it then?\"\n");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"Ever since I found out that you were a girl, I could not take my mind off of you. Everything\n       about you made my heart skip a beat. At first I thought it was weird to be honest, because \n       to me you've been a guy these past six years, so how... how in five weeks have I fallen so madly\n       in love with you? I didn't understand it.\"");
	Thread.sleep(7000);
	System.out.println("\nMe: \"Jovi..\"\n");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"I just didn't but then it hit me. We've spent all this time together. Why would I even be friends\n       with you in the first place if I didn't like you. Already to me you were a perfect person.\n       Everything about you was absolute. My feelings became more than friends for you after I met you. I got to \n       see you every side of you and spend time with you. And don't even get me started with your expressions.\n       I'm head over heels for you.\"");
	Thread.sleep(TIME);
	System.out.println("The entire world stopped.");
	Thread.sleep(TIME);
	System.out.println("Suddenly, everything that had ever caused my pain faded away.");
	Thread.sleep(TIME);
	System.out.println("I was consumed in delirium.\n");
	Thread.sleep(TIME);
	System.out.println("Jovi: \"Especially your smiles, oh boy were they interesting but dangerous enough to make my heart race a \n       million miles. I'm crazy for you Elle. I love you.\"");
	Thread.sleep(TIME);
	System.out.println("\nMy head was in heaven.");
	Thread.sleep(TIME);
	System.out.println("I could not believe that the person I loved the most had the same feelings.");
	Thread.sleep(TIME);
	System.out.println("The universe around me burst with happiness.");
	Thread.sleep(TIME);
	System.out.println("I had discovered something that no one had every experienced before.");
	Thread.sleep(TIME);
	System.out.println("A love so unique that it couldn't be discovered in the trenches of the ocean.");		
	Thread.sleep(TIME);
	System.out.println("It was unreal.\n");
	Thread.sleep(TIME);
	System.out.println("Jovi moved his hands to my face.");
	Thread.sleep(TIME);
	System.out.println("He tucked my "+avatar[2]+" "+avatar[1]+" behind my ears.");
	Thread.sleep(TIME);
	System.out.println("Our eyes slowly closed and our heads moved closer together.");
	Thread.sleep(TIME);
	System.out.println("I could feel his warm breath near me.\n");
	Thread.sleep(TIME);
	System.out.println("His lips brushed mine.");
	Thread.sleep(TIME);
	System.out.println("Softly and delicately");
	Thread.sleep(TIME);
	System.out.println("Like swan wings, just long enough that I could feel the texture of his skin.");
	Thread.sleep(TIME);
	System.out.println("It felt like delicate rain on a summer evening.");
	Thread.sleep(TIME);
	System.out.println("The explosion of all the flowers in the universe all mingingling together at once creating the best \n       sensation known to human kind.");
	Thread.sleep(TIME);
	System.out.println("The taste of his lips lingered long after the kiss.\n");
	Thread.sleep(TIME);
	System.out.println("We pulled back at stared passionately into each other's eyes.");
	Thread.sleep(TIME);
	System.out.println("I saw the pine trees of the forest in his eyes.");
	Thread.sleep(TIME);
	System.out.println("Fresh baby leaves that have just begun to bloom in the spring.\n");
	Thread.sleep(TIME);
	System.out.println("We went back at it again and again. ");
	Thread.sleep(TIME);
	System.out.println("Eventually we both fell asleep on the waterbed surrounded by paradise.\n");
	Thread.sleep(TIME);
	System.out.println("A newfound love was blossoming.");
	Thread.sleep(TIME);
	System.out.println("On this beautiful spring day.");
	Thread.sleep(7000);
	System.out.println("                     .-~~~-\r\n" + 
			"                .-~~~_._~~~\\   \r\n" + 
			"                /~-~~   ~.  `._ \r\n" + 
			"               /    \\     \\  | ~~-_ \r\n" + 
			"       __     |      |     | |  /~\\|\r\n" + 
			"   _-~~  ~~-..|       ______||/__..-~~/\r\n" + 
			"    ~-.___     \\     /~\\_________.-~~\r\n" + 
			"         \\~~--._\\   |             /\r\n" + 
			"          ^-_    ~\\  \\          /^\r\n" + 
			"             ^~---|~~~~-.___.-~^\r\n" + 
			"               /~^| | | |^~\\\r\n" + 
			"              //~^`/ /_/ ^~\\\\\r\n" + 
			"              /   //~||      \\\r\n" + 
			"                 ~   ||\r\n" + 
			"          ___      -(||      __ ___ _\r\n" + 
			"         |\\|  \\       ||_.-~~ /|\\-  \\~-._\r\n" + 
			"         | -\\| |      ||/   /  | |\\- | |\\ \\\r\n" + 
			"          \\__-\\|______ ||  |    \\___\\|  \\_\\|\r\n" + 
			"    _____ _.-~/|\\     \\\\||  \\  |  /       ~-.\r\n" + 
			"  /'  --/|  / /|  \\    \\||    \\ /          |\\~-\r\n" + 
			" ' ---/| | |   |\\  |     ||                 \\__|\r\n" + 
			"| --/| | ;  \\ /|  /    -(||\r\n" + 
			"`./  |  /     \\|/        ||)-\r\n" + 
			"  `~^~^                  ||");
	Thread.sleep(1000);	
}
/***
 * Determines if user wants to play again
 * @param play1
 * @return
 * @throws IOException
 */
public static String playAgain(String play1)throws IOException {
	BufferedReader in;
	in = new BufferedReader(new InputStreamReader (System.in));
	
	System.out.println("Would you like to play again? (Type 'no' to exit)"); //First time that it asks the user if they would like to play again
	play1 = in.readLine();
	System.out.println("Are you sure you would like to play again?"); /*Second time that it asks the user if they would like to play again. 
																		used as a verification to ensure that user is making the correct decision that they want to*/
	play1 = in.readLine();
	return play1;
}

}
