package viewui;

import java.util.Scanner;

import model.Magic;
import model.IMagicDAO;
import model.datastore.mysql.MagicDAO;

/**
 * @author Jordan Grimes
 * @version 20151023
 * 
 */
public class MagicApp {

	IMagicDAO cardList = new MagicDAO();
	Scanner sc = new Scanner(System.in);

	public MagicApp() {
		menuLoop();
	}

	private void menuLoop() {
		String cardName, type, mana, set;
		int id, num;
		String choice = "1";
		while (!choice.equals("0")) {
			System.out.println("\nMagic App");
			System.out.println("0 = Quit");
			System.out.println("1 = List All Cards");
			System.out.println("2 = Create New Card");
			System.out.println("3 = Retrieve Card");
			System.out.println("4 = Update Card");
			System.out.println("5 = Delete Card");
			System.out.println("6 = Sort Cards");
			System.out.println("7 = Create deck");
			choice = Validator.getLine(sc, "Number of choice: ", "^[0-7]$");

			switch (choice) {
			case "1":
				System.out.println(cardList.retrieveAllRecords());
				break;
			case "2":
				id = Validator.getInt(sc, "New card id: ");
				cardName = Validator.getLine(sc, "New card name: ");
				type = Validator.getLine(sc, "Card type: ");
				mana = Validator.getLine(sc, "Color: ");
				set = Validator.getLine(sc, "Expansion: ");
				num = Validator.getInt(sc, "Index: ");
				cardList.createRecord(new Magic(id, cardName, type, mana, set, num));
				break;
			case "3":
				id = Validator.getInt(sc, "Card id to retrieve: ");
				System.out.println(cardList.retrieveRecordById(id));
				break;
			case "4":
				id = Validator.getInt(sc, "Card id to update: ");
				cardName = Validator.getLine(sc, "Card name:"); 
				type = Validator.getLine(sc, "Card type: ");
				mana = Validator.getLine(sc, "Color: ");
				set = Validator.getLine(sc, "Expansion: ");
				num = Validator.getInt(sc, "Index: ");
				cardList.updateRecord(new Magic(id, cardName, type, mana, set, num));
				break;
			case "5":
				id = Validator.getInt(sc, "Card id to delete: ");
				System.out.println(cardList.retrieveRecordById(id));
				String ok = Validator.getLine(sc, "Delete this record? (y/n) ", "^[yYnN]$");
				if (ok.equalsIgnoreCase("Y")) {
					cardList.deleteRecord(id);
				}
				break;
			case "6":
				int choice2 = 0;
				System.out.println("\nChoose what to sort by:");
				System.out.println("1 = CardType");
				System.out.println("2 = Color");
				System.out.println("3 = Expansion");
				choice2 = sc.nextInt();
				System.out.println(cardList.sortCards(choice2));
				break;
			case "7":
				int choice3 = 0;
				System.out.println("\nChoose a color:");
				System.out.println("1 = Green");
				System.out.println("2 = Black");
				System.out.println("3 = Red");
				System.out.println("4 = White");
				System.out.println("5 = Blue");
				choice3 = sc.nextInt();
				System.out.println(cardList.createDeck(choice3));
				break;
			}
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	 
	public static void main(String[] args) {
		new MagicApp();
	}
}
