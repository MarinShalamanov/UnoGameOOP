package com.marinshalamanov.unogame;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		while(true) {
			System.out.println("Game starts");
			
			Game game = new Game();
			game.playGame();
			
			System.out.println("Start a new game? (y / n)");
			String in = input.next();
			
			if(in.equals("n")) {
				break;
			}
		}
		
		input.close();
	}
}
