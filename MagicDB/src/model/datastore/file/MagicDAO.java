package model.datastore.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.Magic;
import model.IMagicDAO;

/**
 * @author Jordan Grimes
 * @version 20151023
 * 
 */
public class MagicDAO implements IMagicDAO {

	protected String fileName = null;
	protected final List<Magic> myList;

	public MagicDAO() {
		Properties props = new Properties();
		FileInputStream fis = null;

		// read the properties file
		try {
			fis = new FileInputStream("res/file/db.properties");
			props.load(fis);
			this.fileName = props.getProperty("DB_FILENAME");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.myList = new ArrayList<>();
		try {
			Files.createFile(Paths.get(fileName));
		} catch (FileAlreadyExistsException fae) {
			;
		} catch (IOException ioe) {
			System.out.println("Create file error with " + ioe.getMessage());
		}
		readList();
	}

	@Override
	public void createRecord(Magic card) {
		myList.add(card);
		writeList();
	}

	@Override
	public Magic retrieveRecordById(int id) {
		for (Magic card : myList) {
			if (card.getId() == id) {
				return card;
			}
		}
		return null;
	}

	@Override
	public List<Magic> retrieveAllRecords() {
		return myList;
	}

	@Override
	public void updateRecord(Magic updatedCard) {
		for (Magic card : myList) {
			if (card.getId() == updatedCard.getId()) {
			    card.setName(updatedCard.getName());
				card.setCardType(updatedCard.getCardType());
				card.setColor(updatedCard.getColor());
				card.setExpansion(updatedCard.getExpansion());
				card.setIndex(updatedCard.getIndex());
				break;
			}
		}
		writeList();
	}

	@Override
	public void deleteRecord(int id) {
		for (Magic card : myList) {
			if (card.getId() == id) {
				myList.remove(card);
				break;
			}
		}
		writeList();
	}

	@Override
	public void deleteRecord(Magic card) {
		myList.remove(card);
		writeList();
	}

	protected void readList() {
		Path path = Paths.get(fileName);
		try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				int id = Integer.parseInt(data[0]);
				String cardName = data[1];
				String type = data[2];
				String mana = data[3];
				String set = data[4];
				int num = Integer.parseInt(data[5]);
				Magic card = new Magic(id, cardName, type, mana, set, num);
				myList.add(card);
			}
		} catch (IOException ioe) {
			System.out.println("Read file error with " + ioe.getMessage());
		}
	}

	protected void writeList() {
		Path path = Paths.get(fileName);
		try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
			for (Magic card : myList) {
				writer.write(String.format("%d, %s, %s, %s, %s,%d\n",card.getId(), card.getName(), card.getCardType(),
						card.getColor(), card.getExpansion(), card.getIndex()));
			}
		} catch (IOException ioe) {
			System.out.println("Write file error with " + ioe.getMessage());
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Magic card : myList) {
			sb.append(String.format("%d, %s, %s, %s, %s, %d\n",card.getId(), card.getName(), card.getCardType(),
					card.getColor(), card.getExpansion(), card.getIndex()));
		}

		return sb.toString();
	}


	public static char[] sortCards(int choice2) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
