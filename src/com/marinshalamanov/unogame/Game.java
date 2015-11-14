package com.marinshalamanov.unogame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.marinshalamanov.unogame.model.Card;
import com.marinshalamanov.unogame.model.CenterPile;
import com.marinshalamanov.unogame.model.Color;
import com.marinshalamanov.unogame.model.Deck;
import com.marinshalamanov.unogame.model.DeckBuilder;
import com.marinshalamanov.unogame.model.Value;

public class Game {

	private final static int NUM_PLAYERS = 2;

	private List<List<Card>> players;

	private CenterPile center;

	private Deck deck;

	private Scanner input;

	public Game() {
	}

	public void playGame() {
		init();

		int currPlayer = 0;

		while (!doesSomebodyWin()) {
			checkEmptyDeck();
			nextTurn(currPlayer);

			Card top = center.seeFirst();

			int nextPlayerIdx = getNextPlayerId(currPlayer);
			if (top.getValue().equals(Value.PLUS_2)) {
				giveCardsToPlayers(nextPlayerIdx, 2);
			} else if (top.getValue().equals(Value.PLUS_4)) {
				giveCardsToPlayers(nextPlayerIdx, 4);
			} else if (top.getValue().equals(Value.SKIP)) {
				nextPlayerIdx = getNextPlayerId(nextPlayerIdx);
			} else if (top.getValue().equals(Value.CHANGE_COLOR)) {
				changeColor();
			}

			currPlayer = nextPlayerIdx;
		}

		int playerWindsIdx = whoWins();
		System.out.println("Player " + playerWindsIdx + ", you win!");
	}

	private void checkEmptyDeck() {
		if(deck.isEmpty()) {
			Card top = center.getFirst();
			while(!center.isEmpty()) {
				deck.putCard(center.getFirst());
			}
			center.putCard(top);			
		}
		
	}

	private void changeColor() {
		Color[] colors = Color.values();
		
		System.out.println("Change the color to: " + Arrays.toString(colors));
		int colorIdx = input.nextInt();
		Color newColor = colors[colorIdx];
		
		center.setColorAtTop(newColor);
	}
	
	private void giveCardsToPlayers(int playerId, int numCards) {
		for (int i = 0; i < numCards; i++) {
			players.get(playerId).add(deck.getFirst());
		}
	}

	private int getNextPlayerId(int playerId) {
		return (playerId + 1) % NUM_PLAYERS;
	}

	private void init() {
		input = new Scanner(System.in);
		
		DeckBuilder deckBuilder = new DeckBuilder();
		
		center = new CenterPile();
		
		deck = deckBuilder.buildFullShuffledDeck();

		players = new ArrayList<List<Card>>();

		for (int i = 0; i < NUM_PLAYERS; i++) {
			players.add(new ArrayList<Card>());
		}

		for (int j = 0; j < players.size(); j++) {
			giveCardsToPlayers(j, 7);
		}
	}

	private void nextTurn(int player) {
		Card card;

		while (true) {
			if(!center.isEmpty()) {
				System.out.println("Card at top is: " + center.seeFirst() + " Color is: " + center.getColorAtTop());
			}
			
			System.out.println("Player " + player + " choose a card from: " + players.get(player));
			System.out.println("Or write T for taking a new card");
			if(input.hasNextInt()) {
				int cardIdx = input.nextInt();
				card = players.get(player).get(cardIdx);
	
				if (!isValidNextCard(card)) {
					System.out.println("Invalid next card");
				} else {
					players.get(player).remove(cardIdx);
					break;
				}
			} else {
				String in = input.next();
				if(in.equals("T")) {
					giveCardsToPlayers(player, 1);
				} else {
					System.out.println("Invalid next card");
				}
			}
		}

		center.putCard(card);
	}

	private boolean isValidNextCard(Card card) {
		if (center.isEmpty()) {
			return true;
		} else {
			Card top = center.seeFirst();
			boolean sameColor = center.getColorAtTop().equals(card.getColor());
			boolean sameValue = top.getValue().equals(card.getValue());

			return sameColor || sameValue;
		}
	}

	private boolean doesSomebodyWin() {
		return whoWins() != -1;
	}

	private int whoWins() {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isEmpty()) {
				return i;
			}
		}

		return -1;
	}
}
