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

	static List<Magic> sortCards(int choice) {
		// TODO Auto-generated method stub
		return null;
	}

}