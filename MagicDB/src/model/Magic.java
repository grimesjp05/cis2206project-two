package model;

/**
 * The Magic class represents a single card.
 * 
 * @author Jordan Grimes
 * @version 20151023
 *
 */
public class Magic {

	private String name;
	private String cardType;
	private String color;
	private String expansion;
	private int id, index;

	public Magic() {
		id = 0;
		name = "";
		cardType = "";
		color = "";
		expansion = "";
		index = 0;
	}

	public Magic(int id, String name, String cardType, String color, String expansion, int index) {
		this.id = id;
		this.name = name;
		this.cardType = cardType;
		this.color = color;
		this.expansion = expansion;
		this.index = index;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}

	public String getExpansion() {
		return expansion;
	}

	public void setExpansion(String expansion) {
		this.expansion = expansion;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return String.format("\n%d, %s, %s, %s, %s, %d",this.getId(), this.getName(), this.getCardType(),
				this.getColor(), this.getExpansion(), this.getIndex());
	}
}