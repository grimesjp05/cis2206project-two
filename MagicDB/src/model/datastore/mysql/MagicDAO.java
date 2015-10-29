package model.datastore.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Magic;
import model.IMagicDAO;

/**
 * @author Jordan Grimes
 * @version 20151023
 *
 */
public class MagicDAO implements IMagicDAO {
	
	protected final static boolean DEBUG = true;

	@Override
	public void createRecord(Magic card) {
		final String QUERY = "insert into card (id, name, cardType, color, expansion, cIndex) VALUES (null, ?, ?, ?, ?, ?)";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY);) {
			stmt.setString(1, card.getName());
			stmt.setString(2, card.getCardType());
			stmt.setString(3, card.getColor());
			stmt.setString(4, card.getExpansion());
			stmt.setInt(5, card.getIndex());
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();

		} catch (SQLException ex) {
			System.out.println("createRecord SQLException: " + ex.getMessage());
		}
	}

	@Override
	public Magic retrieveRecordById(int id) {
		final String QUERY = "select id, name, cardType, color, expansion, cIndex from card where name = " + id;
		// final String QUERY = "select id, name, cardType, color, expansion,
		// index from card where id = ?";
		Magic card = null;

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			// stmt.setInt(1, cardName);
			if(DEBUG) System.out.println(stmt.toString());
			ResultSet rs = stmt.executeQuery(QUERY);

			if (rs.next()) {
				card = new Magic(rs.getInt("id"), rs.getString("name"), rs.getString("cardType"), rs.getString("color"),
						rs.getString("expansion"), rs.getInt("index"));
			}
		} catch (SQLException ex) {
			System.out.println("retrieveRecordById SQLException: " + ex.getMessage());
		}

		return card;
	}

	@Override
	public List<Magic> retrieveAllRecords() {
		final List<Magic> myList = new ArrayList<>();
		final String QUERY = "select id, name, cardType, color, expansion, cIndex from card";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			if(DEBUG) System.out.println(stmt.toString());
			ResultSet rs = stmt.executeQuery(QUERY);
			
			while (rs.next()) {
				myList.add(new Magic(rs.getInt("id"), rs.getString("name"), rs.getString("cardType"), rs.getString("color"),
						rs.getString("expansion"), rs.getInt("cIndex")));
			}
		} catch (SQLException ex) {
			System.out.println("retrieveAllRecords SQLException: " + ex.getMessage());
		}

		return myList;
	}

	@Override
	public void updateRecord(Magic updatedCard) {
		final String QUERY = "update card set name=?, cardType=?, color=?, expansion=?, cIndex=? where id=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setString(1, updatedCard.getName());
			stmt.setString(2, updatedCard.getCardType());
			stmt.setString(3, updatedCard.getColor());
			stmt.setString(4, updatedCard.getExpansion());
			stmt.setInt(5, updatedCard.getIndex());
			stmt.setInt(6, updatedCard.getId());
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("updateRecord SQLException: " + ex.getMessage());
		}
	}

	@Override
	public void deleteRecord(int id) {
		final String QUERY = "delete from card where id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setInt(1, id);
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("deleteRecord SQLException: " + ex.getMessage());
		}
	}

	@Override
	public void deleteRecord(Magic card) {
		final String QUERY = "delete from card where cardName = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setInt(1, card.getId());
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("deleteRecord SQLException: " + ex.getMessage());
		}
	}
	@Override
	public List<Magic> sortCards(int choice2) {
		String QUERY = "";
		final List<Magic> cards = new ArrayList<>();
		if (choice2 == 1 ) {
			QUERY = "select * from card order by cardType";
		} else if (choice2 == 2) {
			QUERY = "select * from card order by color";
		} else if (choice2 == 3) {
			QUERY = "select * from card order by expansion";
		} else {
			System.out.println("Something Went Wrong");
		}
		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			if(DEBUG) System.out.println(stmt.toString());
			ResultSet rs = stmt.executeQuery(QUERY);
			
			while (rs.next()) {
				cards.add(new Magic(rs.getInt("id"), rs.getString("name"), rs.getString("cardType"), rs.getString("color"),
						rs.getString("expansion"), rs.getInt("cIndex")));
			}
		} catch (SQLException ex) {
			System.out.println("sortCards SQLException: " + ex.getMessage());
		}
		return cards;
	}
	
	@Override 
	public List<Magic> createDeck(int choice3) {
		String QUERY = "";
		final List<Magic> cards = new ArrayList<>();
		if (choice3 == 1) {
			QUERY = "select * from card where color = Green and cardType = Land";
			QUERY = "select * from card where color = Green and cardType <> Land";
		}
	}
 
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Magic card : retrieveAllRecords()) {
			sb.append(card.toString() + "\n");
		}

		return sb.toString();
	}
}
