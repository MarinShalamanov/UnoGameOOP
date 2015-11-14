package com.marinshalamanov.unogame.model;

public class CenterPile extends Deck {
	private Color colorAtTop;
	
	private boolean differentColorAtTop;
	
	public CenterPile() {
		super();
		differentColorAtTop = false;
	}
	
	public Color getColorAtTop() {
		if(differentColorAtTop) {
			return colorAtTop;
		} else {
			return seeFirst().getColor();
		}
	}
	
	public void setColorAtTop(Color newColor) {
		differentColorAtTop = false;
		colorAtTop = newColor;
	}
	
	public void putCard(Card card) {
		super.putCard(card);
		differentColorAtTop = false;
	}
}
