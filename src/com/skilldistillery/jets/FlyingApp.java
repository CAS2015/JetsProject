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
		boolean keepGoing = true;

		System.out.println(
				"Welcome to Hogwarts Stables! We have a great selection of brooms, magical creatures and flying vehicles");
		System.out.println();

		String[] mainMenu = { "List stable information", "Fly everything in stable", "View fastest flying object",
				"View flying object with best range", "Load all passenger vehicles", "Feed all magical creatures",
				"Play Quidditch", "Add a flying object to the stable", "Remove a flying object from the stable",
				"Quit" };

		while (keepGoing) {
			printMenu(mainMenu);
			System.out.print("What would you like to do? ");
			int selection = getUserSelection(sc, mainMenu.length);
			keepGoing = getMenuSelection(selection, list, sc, keepGoing);
		}

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

	public void printMenu(String[] menu) {
		System.out.println("              Menu                 ");
		System.out.println("-----------------------------------");
		for (int i = 0; i < menu.length; i++) {
			System.out.println((i + 1) + ") " + menu[i]);
		}
		System.out.println();
	}

	public void printMenu(AirField af) {
		for (int i = 0; i < af.getFlyingObjects().size(); i++) {
			System.out.println((i + 1) + ") " + af.getFlyingObjects().get(i));
		}
		System.out.println();
	}

	public int getUserSelection(Scanner sc, int length) {
		boolean gotValidInput = false;
		int i = 0;
		while (!gotValidInput) {
			try {
				i = sc.nextInt();
				sc.nextLine();
				if (i > 0 && i <= length) {
					gotValidInput = true;
				} else {
					System.out.println("Invalid Input: please type 1-" + length);
					sc.nextLine();
				}
			} catch (Exception e) {
				System.out.println("Invalid Input: please type 1-" + length);
				sc.nextLine();
			}
		}
		return i;
	}

	public boolean getMenuSelection(int selection, AirField af, Scanner sc, boolean keepGoing) {
		switch (selection) {
		case 1:
			System.out.println();
			System.out.println("Here's a list of everything in the stable: ");
			System.out.println("------------------------------------------");
			printMenu(af);
			break;
		case 2:
			System.out.println();
			System.out.println("Fly my pretties, fly!");
			System.out.println("----------------------");
			flyEverything(af);
			System.out.println();
			break;
		case 3:
			System.out.println();
			System.out.println("The fastest thing in the stable is:");
			System.out.println("-----------------------------------");
			System.out.println(af.getFastestObject());
			System.out.println();
			break;
		case 4:
			System.out.println();
			System.out.println("The thing with the longest range in the stable is:");
			System.out.println("--------------------------------------------------");
			System.out.println(af.getBiggestRangeObject());
			System.out.println();
			break;
		case 5:
			System.out.println();
			System.out.println("Loading all vehicles:");
			System.out.println("---------------------");
			loadPassengerVehicles(af);
			System.out.println();
			break;
		case 6:
			System.out.println();
			System.out.println("Feeding all of the magical creatures:");
			System.out.println("-------------------------------------");
			feedAllCreatures(af);
			System.out.println();
			break;
		case 7:
			System.out.println();
			System.out.println("Getting ready to play Quidditch:");
			System.out.println("--------------------------------");
			playQuidditch(af);
			System.out.println();
			break;
		case 8:
			addFlyingObject(af, sc);
			break;
		case 9:
			removeFlyingObject(af, sc);
			break;
		case 10:
			System.out.println("Thanks for visiting! Goodbye.");
			keepGoing = false;

		}
		return keepGoing;
	}

	private void removeFlyingObject(AirField af, Scanner sc) {
		System.out.println();
		System.out.println("List of available flying objects to remove");
		System.out.println("-------------------------------------------");
		printMenu(af);
		System.out.print("Which flying object would you like to remove? ");
		int i = getUserSelection(sc, af.getFlyingObjects().size());
		System.out.println(af.removeFlyingObjectByIndex(i - 1) + " removed!");
		System.out.println();
	}

	private void addFlyingObject(AirField af, Scanner sc) {
		String classification = "na";
		boolean invalidInput = true;

		System.out.println();
		System.out.print("What is the model or name of the flying object? ");
		String name = sc.nextLine();
		double speed = getAddedObjectSpeed(sc, invalidInput);
		int range = getAddedObjectRange(sc, invalidInput);
		long price = getAddedObjectPrice(sc, invalidInput);
		
		FlyingObject fo = createFlyingObject(name, speed, range, price, classification);
		af.addFlyingObject(fo);
		System.out.println(name + " added to the stables!");
		System.out.println();
	}

	private long getAddedObjectPrice(Scanner sc, boolean invalidInput) {
		long price = 0;
		while (invalidInput) {
			try {
				System.out.println("What is the price in galleons of your flying object? ");
				price = Long.parseLong(sc.nextLine());
				invalidInput = false;
			} catch (Exception e) {
				System.out.println("Invalid input, please type in a decimal number");
			}	
		}
		return price;
	}

	private int getAddedObjectRange(Scanner sc, boolean invalidInput) {
		int range = 0;
		while (invalidInput) {
			try {
				System.out.println("What is the range in miles of your flying object? ");
				range = Integer.parseInt(sc.nextLine());
				invalidInput =false;
			} catch (Exception e) {
				System.out.println("Invalid input, please type in a whole number");
			}	
		}
		return range;
	}

	private double getAddedObjectSpeed(Scanner sc, boolean invalidInput) {
		double speed = 0;
		while (invalidInput) {
			try {
				System.out.println("What is the speed in mph of your flying object? ");
				speed = Double.parseDouble(sc.nextLine());
				invalidInput = false;
			} catch (Exception e) {
				System.out.println("Invalid input, please type in a decimal number");
			}
		}
		return speed;
	}

	private void playQuidditch(AirField af) {
		for (FlyingObject fo : af.getFlyingObjects()) {
			if (fo instanceof Broom) {
				((Broom) fo).playQuidditch();
			}
		}

	}

	private void feedAllCreatures(AirField af) {
		for (FlyingObject fo : af.getFlyingObjects()) {
			if (fo instanceof MagicalCreature) {
				((MagicalCreature) fo).feed();
			}
		}

	}

	private void loadPassengerVehicles(AirField af) {
		for (FlyingObject fo : af.getFlyingObjects()) {
			if (fo instanceof PassengerVehicle) {
				((PassengerVehicle) fo).loadVehicle();
			}
		}
	}

	private void flyEverything(AirField af) {
		for (FlyingObject fo : af.getFlyingObjects()) {
			fo.fly();
		}
	}

}
