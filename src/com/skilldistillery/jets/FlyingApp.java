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
		AirField af = readFile(fileName);
		boolean keepGoing = true;
		String[] mainMenu = { "List stable information", "Fly everything in stable", "View fastest flying object",
				"View flying object with best range", "Load all passenger vehicles", "Feed all magical creatures",
				"Play Quidditch", "Add a flying object to the stable", "Remove a flying object from the stable",
				"Fly something from the stable", "Quit" };

		System.out.println(
				"Welcome to Hogwarts Stables! We have a great selection of brooms, magical creatures and flying vehicles");
		System.out.println();

		while (keepGoing) {
			System.out.println("              Menu                 ");
			System.out.println("-----------------------------------");
			printMenu(mainMenu);
			System.out.print("What would you like to do? ");
			int selection = getUserSelection(sc, mainMenu.length);
			keepGoing = getMenuSelection(selection, af, sc, keepGoing);
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
				String handler = arr[5];

				FlyingObject fo = createFlyingObject(model, speed, range, price, classification, handler);

				if (fo != null) {
					list.addFlyingObject(fo);
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		}

		return list;
	}

	public FlyingObject createFlyingObject(String model, double speed, int range, long price, String classification, String handler) {
		// Create flying object to be added to the list based on what type of object it
		// is.
		FlyingObject fo;

		switch (classification) {
		case "Creature":
			fo = createCreature(model, speed, range, price, handler);
			break;
		case "Broom":
			fo = new Broom(model, speed, range, price, handler);
			break;
		case "Vehicle":
			fo = new PassengerVehicle(model, speed, range, price, handler);
			break;
		default:
			fo = new FlyingObjectImpl(model, speed, range, price, handler);
		}

		return fo;
	}

	public FlyingObject createCreature(String model, double speed, int range, long price, String handler) {
		FlyingObject fo;

		switch (model) {
		case "Hippogriff":
			fo = new Hippogriff(model, speed, range, price, handler);
			break;
		case "Thestral":
			fo = new Thestral(model, speed, range, price, handler);
			break;
		case "Abraxan":
			fo = new Abraxan(model, speed, range, price, handler);
			break;
		default:
			fo = new MagicalCreature(model, speed, range, price, handler);
			break;
		}

		return fo;
	}

	public void printMenu(String[] menu) {
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
					System.out.println();
					System.out.print("What would you like to do? ");
				}
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("Invalid Input: please type 1-" + length);
				System.out.println();
				System.out.print("What would you like to do? ");
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
			System.out.println();
			System.out.println("Available things to fly:");
			System.out.println("------------------------");
			printMenu(af);
			int i = getUserSelection(sc, af.getFlyingObjects().size());
			af.getFlyingObjects().get(i-1).fly();
			break;
		case 11:
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
		System.out.print(
				"Which flying object(s) would you like to remove? (separate multiple objects to be removed by a comma) ");
		String[] userSelections = sc.nextLine().split(",");
		for (int i = 0; i < userSelections.length; i++) {
			userSelections[i] = userSelections[i].trim();
		}
		FlyingObject[] fos = new FlyingObject[userSelections.length];
		try {
			for (int i = 0; i < fos.length; i++) {
				fos[i] = af.getFlyingObjects().get(Integer.parseInt(userSelections[i]) - 1);
			}
			for (FlyingObject flyingObject : fos) {
				System.out.println(af.removeFlyingObjectByModel(flyingObject) + " removed!");
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println(
					"Sorry one of the numbers wasn't a valid selection. Nothing was removed, please try again.");
		} catch (Exception e) {
			for (String string : userSelections) {
				for (FlyingObject fo : af.getFlyingObjects()) {
					if (fo.getModel().equals(string)) {
						System.out.println(af.removeFlyingObjectByModel(fo) + " removed!");
					}
				}
			}
		}

//		int i = getUserSelection(sc, af.getFlyingObjects().size()); //Old code for only removing 1 object
//		System.out.println(af.removeFlyingObjectByIndex(i - 1) + " removed!"); //Old code for only removing 1 object
		System.out.println();
	}

	private void addFlyingObject(AirField af, Scanner sc) {
		boolean invalidInput = true;
		String[] classes = { "Creature", "Broom", "Vehicle", "General Flying Object" };
		String[] creatures = { "Hippogriff", "Thestral", "Abraxan", "Other Creature" };

		System.out.println();
		System.out.println("List of available flying objects to add");
		System.out.println("----------------------------------------");
		printMenu(classes);
		System.out.println();
		System.out.print("Which type of flying object would you like to add? ");
		String classification = classes[getUserSelection(sc, classes.length) - 1];
		String name;
		if (classification.equals("Creature")) {
			System.out.println();
			System.out.println("List of available creatures to add");
			System.out.println("----------------------------------");
			printMenu(creatures);
			System.out.println();
			System.out.print("Which type of creature would you like to add? ");
			name = creatures[getUserSelection(sc, creatures.length) - 1];
			if (name.equals("Other Creature")) {
				System.out.print("What is the type of creature you would like to add? ");
				name = sc.nextLine();
			}
		} else {
			System.out.println();
			System.out.print("What is the model or name of the flying object? ");
			name = sc.nextLine();
		}

		double speed = getAddedObjectSpeed(sc, invalidInput);
		int range = getAddedObjectRange(sc, invalidInput);
		long price = getAddedObjectPrice(sc, invalidInput);
		String handler = "";
		

		FlyingObject fo = createFlyingObject(name, speed, range, price, classification, handler);
		fo.setHandler();
		af.addFlyingObject(fo);
		System.out.println(name + " added to the stables with " + fo.getHandler() + " as its handler!");
		System.out.println();
	}

	private long getAddedObjectPrice(Scanner sc, boolean invalidInput) {
		long price = 0;
		while (invalidInput) {
			try {
				System.out.print("What is the price in galleons of your flying object? ");
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
				System.out.print("What is the range in miles of your flying object? ");
				range = Integer.parseInt(sc.nextLine());
				invalidInput = false;
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
				System.out.print("What is the speed in mph of your flying object? ");
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
