package com.skilldistillery.jets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class FlyingApp {

	public static void main(String[] args) {
		FlyingApp fa = new FlyingApp();

		fa.run();
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		String fileName = "jets.txt";
		AirField list = readFile(fileName);
		
		System.out.println("Welcome to Hogwarts Stables! We have a great selection of brooms, magical creatures and flying vehicles");
		
		printMenu();
		int selection = getUserSelection(sc);
		getMenuSelection(selection, list, sc);

	}

	public AirField readFile(String fileName) {
		// Read the file and populate the list of flying objects.
		AirField list = new AirField();

		try (BufferedReader bf = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = bf.readLine()) != null) {
				String[] arr = line.split(",");
				String model = arr[0];
				double speed = Double.parseDouble(arr[1]);
				int range = Integer.parseInt(arr[2]);
				long price = Long.parseLong(arr[3]);
				String classification = arr[4];

				FlyingObject fo = createFlyingObject(model, speed, range, price, classification);

				if (fo != null) {
					list.addFlyingObject(fo);
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		}

		return list;
	}

	public FlyingObject createFlyingObject(String model, double speed, int range, long price, String classification) {
		// Create flying object to be added to the list based on what type of object it
		// is.
		FlyingObject fo;

		switch (classification) {
		case "Creature":
			fo = new MagicalCreature(model, speed, range, price);
			break;
		case "Broom":
			fo = new Broom(model, speed, range, price);
			break;
		case "Vehicle":
			fo = new PassengerVehicle(model, speed, range, price);
			break;
		default:
			fo = new FlyingObjectImpl(model, speed, range, price);
		}

		return fo;
	}

	public void printMenu() {
		System.out.println("__________________________________________");
		System.out.println("|                   Menu                  |");
		System.out.println("|-----------------------------------------|");
		System.out.println("|                                         |");
		System.out.println("| 1) List stable information              |");
		System.out.println("| 2) Fly everything in stable             |");
		System.out.println("| 3) View fastest flying object           |");
		System.out.println("| 4) View flying object with best range   |");
		System.out.println("| 5) Load all passenger vehicles          |");
		System.out.println("| 6) Feed all magical creatures           |");
		System.out.println("| 7) Play Quidditch                       |");
		System.out.println("| 8) Add a flying object to the stable    |");
		System.out.println("| 9) Remove a flying object to the stable |");
		System.out.println("| 10) Quit                                |");
		System.out.println("|_________________________________________|");
		System.out.println();
	}

	public int getUserSelection(Scanner sc) {
		boolean gotValidInput = false;
		int i=0;

		while (!gotValidInput) {
			try {
				System.out.print("What would you like to do? ");
				i = sc.nextInt();
				if(i<= 10 && i > 0) {
					gotValidInput = true;	
				}
				else {
				System.out.println("Invalid Input: please type 1-10");
				sc.nextLine();
				}
			} catch (Exception e) {
				System.out.println("Invalid Input: please type 1-10");
				sc.nextLine();
			}
		}

		return i;
	}

	public void getMenuSelection(int selection, AirField af, Scanner sc) {
		switch (selection) {
		case 1:
			printStableInfo(af);
			break;
		case 2:
			flyEverything(af);
			break;
		case 3: 
			System.out.println(af.getFastestObject());
			break;
		case 4:
			System.out.println(af.getBiggestRangeObject());
			break;
		case 5:
			loadPassengerVehicles(af);
			break;
		case 6:
			feedAllCreatures(af);
			break;
		case 7:
			playQuidditch(af);
			break;
		case 8:
			addFlyingObject(af, sc);
			break;
		case 9:
			removeFlyingObject(af, sc);
		case 10:
			//exit program loop
			
		}
		
	}
	
	private void removeFlyingObject(AirField af, Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	private void addFlyingObject(AirField af, Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	private void playQuidditch(AirField af) {
		// TODO Auto-generated method stub
		
	}

	private void feedAllCreatures(AirField af) {
		// TODO Auto-generated method stub
		
	}

	private void loadPassengerVehicles(AirField af) {
		// TODO Auto-generated method stub
		
	}


	private void flyEverything(AirField af) {
		// TODO Auto-generated method stub
		
	}

	public void printStableInfo(AirField af) {
		System.out.println();
		System.out.println();
		System.out.println("Here's a list of everything in the stable: ");
		System.out.println("------------------------------------------");
		for (FlyingObject flyingObject : af.getFlyingObjects()) {
			System.out.println(flyingObject.toString());
		}
	}
}
