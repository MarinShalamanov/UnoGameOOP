package com.marinshalamanov.unogame.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckBuilder {

	public DeckBuilder() {
	}

	public Deck buildEmptyDeck() {
		Deck unoDeck = new Deck();
		return unoDeck;
	}

	private List<Card> allCardsOrdered() {
		List<Card> allCardsOrdered = new ArrayList<Card>();
		
		Color[] colors = Color.values();
		Value[] values = Value.values();
		for(Color c : colors) {
			if(c.equals(Color.BLACK)) {
				allCardsOrdered.add(new Card(Color.BLACK, Value.PLUS_4));
				allCardsOrdered.add(new Card(Color.BLACK, Value.CHANGE_COLOR));
			}else {
				for(Value v : values) {
					Card currCard = new Card(c, v);
					allCardsOrdered.add(currCard);
				}
			}
		}
		
		return allCardsOrdered;
	}
	
	public Deck buildFullOrderedDeck() {
		Deck deck = new Deck();
		
		List<Card> allCardsOrdered = allCardsOrdered();
		for(Card c : allCardsOrdered) {
			deck.putCard(c);
		}
		
		return deck;
	}

	public Deck buildFullShuffledDeck() {
		Deck deck = new Deck();
		
		List<Card> allCards = allCardsOrdered();
		Collections.shuffle(allCards);
		
		for(Card c : allCards) {
			deck.putCard(c);
		}
		
		return deck;
	}

}
