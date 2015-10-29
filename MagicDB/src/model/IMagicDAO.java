package model;

import java.util.List;

/**
 * @author Jordan Grimes
 * @version 20151009
 *
 */
public interface IMagicDAO {

	void createRecord(Magic card);

	Magic retrieveRecordById(int id);

	List<Magic> retrieveAllRecords();

	void updateRecord(Magic updatedCard);

	void deleteRecord(int id);

	void deleteRecord(Magic card);

	String toString();

	List<Magic> sortCards(int choice2);
	
	List<Magic> createDeck(int choice3);

}