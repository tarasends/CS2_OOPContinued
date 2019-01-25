// Tara McKaskle

import java.util.ArrayList;
import java.util.Collections;


public class PlayingWithStacks {

	//In essence, there are four stacks.
	static LinkedStack<Integer> pONEHand = new LinkedStack<>();
	static LinkedStack<Integer> pTWOHand = new LinkedStack<>();
	static LinkedStack<Integer> pONEWins = new LinkedStack<>();
	static LinkedStack<Integer> pTWOWins = new LinkedStack<>();
	static int p1total = 0;
	static int p2total = 0; 

	//this is created for the shuffled cards to use as method,
	//if not in the method the shuffled Cards isn't needed
	//and the iteration can be a for loop with .get(index) rather than a for : each. 
	static LinkedStack<Integer> shuffledCards = new LinkedStack<>();
	static ArrayList<Integer> deck = new ArrayList<>();

	public static void main(String[] args) {

		//shuffles a deck of 52 cards
		for( int i = 0; i < 52; i++) {
			deck.add(i);
		}
		Collections.shuffle(deck);
		for (Integer deck : deck) {
			shuffledCards.push(deck);
		}

		System.out.println("Dealer's Shuffled Cards " + deck + "\n");

		dealerShuffles();
		System.out.println();
		compareCards();
		System.out.println();
		winnerTakesALL();
	}

	//and hands them out to two players randomly (26 each)
	//There are 26 rounds, since each player has a full stack of cards.
	public static void dealerShuffles() {
		System.out.print("Player one hand : \n"); 
		for(int i = 0; i < 26; i ++) {
			if (!shuffledCards.isEmpty()){
				pONEHand.push(shuffledCards.pop());
				System.out.print(pONEHand.peek() + " ");
			}
		}
		System.out.println("\n");

		//Each player has one stack of cards they are playing
		//with and one stack of cards they have won.
		System.out.print("Player two hand : \n");
		for(int i = 0; i < 26; i ++) {
			if (!shuffledCards.isEmpty()){
				pTWOHand.push(shuffledCards.pop());
				System.out.print(pTWOHand.peek() + " ");
			}
		}
		System.out.println("\n");
	}


	//where, at each turn, both players take the card from the top of their pile
	//and the player with the higher card wins that round. 
	//The winner of that round takes both cards and adds it to a stack of won cards.
	public static void compareCards() {
		while (!pONEHand.isEmpty() && !pTWOHand.isEmpty()) {
			if (pONEHand.peek() > pTWOHand.peek()) {
				pONEWins.push(pTWOHand.pop());
				pONEWins.push(pONEHand.pop());
				System.out.println("\n" + pONEWins.peek() + " dded to player ONE's winning Stack");
				++p1total;
			} else {
				pTWOWins.push(pONEHand.pop());
				pTWOWins.push(pTWOHand.pop());
				System.out.println("\n" + pTWOWins.peek() + " added to player TWO's winning Stack");
				++p2total;
			}
		}
	}

	//At the end of the game, the player with the most cards in their “won” pile wins.
	private static void winnerTakesALL() {
		if (pONEWins.peek() > pTWOWins.peek()) {
			System.out.println("Player ONE Takes ALL!");
		} else {
			System.out.println("Player TWO Takes ALL!");
		}
	}
}
