package com.marinshalamanov.unogame.model;

import java.util.Stack;

public class Deck {
	private Stack<Card> deck;
	
	/*default */ Deck () {
		deck = new Stack<Card>();
	}
	
	public Card getFirst() {
		Card first = deck.pop();
		return first;
	}
	
	public Card seeFirst() {
		Card first = deck.peek();
		return first;
	}
	
	public void putCard(Card card) {
		deck.push(card);
	}
	
	public void reverse() {
		Stack<Card> deck2 = new Stack<Card>();
		while(!deck.empty()) {
			deck2.push(deck.pop());
		}
		deck = deck2;
	}
	
	public boolean isEmpty() {
		return deck.isEmpty();
	}
	
	public String toString() {
		String str = new String();
		for(Card c : deck) {
			str += c.toString() + " ";
		}
		return str;
	}
}
