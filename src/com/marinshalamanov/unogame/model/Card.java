package com.marinshalamanov.unogame.model;

public class Card {
	private Color color;
	
	private Value value;
	
	public Card(Color color, Value value) {
		this.color = color;
		this.value = value;
	}
	
	public Color getColor() {
		return color;
	}
	
	public Value getValue() {
		return value;
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (color != other.color)
			return false;
		if (value != other.value)
			return false;
		return true;
	}	
	
	public String toString() {
		return String.format("(%s, %s) " , color.name(), value.name());
	}
	
}
