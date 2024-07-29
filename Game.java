/**********************************
*@author Lilita Yenew
*
***********************************/
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.lang.Integer;

public class Game {
	
	private Player p;
	private Deck cards;
	// you'll probably need some more here
    private ArrayList<Card> hand;
	private double bet;
	private double reward;  // tokens won per hand
	public int initialToken;

	
	
	public Game(String[] testHand){
		// This constructor is to help test your code.
		// use the contents of testHand to
		// make a hand for the player
		// use the following encoding for cards
		// c = clubs
		// d = diamonds
		// h = hearts
		// s = spades
		// 1-13 correspond to ace-king
		// example: s1 = ace of spades
		// example: testhand = {s1, s13, s12, s11, s10} = royal flush

		p = new Player();
		cards = new Deck();
		Card card;
		char letter;
		int rank = 1;   //placeholder rank value
		int suit = 0;   //place holder suit value
		for (int i=0; i<5 ; i++){ //goes through 5 testhand cards input by user
			letter = testHand[i].charAt(0);
			
			String rankStr = testHand[i].substring(1);  //the string version of rank
			rank = Integer.parseInt(rankStr);  
			if (letter == 'c'){  // 1=clubs, 2=diamonds , 3=hearts , 4=spades 
				suit = 1;
			}
			else if(letter== 'd'){
				suit = 2;
			}
			else if(letter=='h'){
				suit = 3;
			}
			else if (letter=='s'){
				suit = 4;
			}
			else {
				System.out.println("Invalid suit input");
			}
			card = new Card(suit,rank);  //individual card created
			p.addCard(card);    		
		}
		cards.shuffle();
		hand = new ArrayList<Card>(p.getHand());  //make new hand using input cards
		hand = sortHand(hand);
		System.out.println(checkHand(hand));  //prints result of checkhand/winning combo
		
	}
	
	public Game(){
		// This no-argument constructor is to actually play a normal game
		p = new Player() ;
        cards = new Deck();
        cards.shuffle();
		for(int i = 0 ; i<5 ; i++){
			p.addCard(cards.deal()) ;
		}   
			hand = new ArrayList<Card> (p.getHand());
			hand = sortHand(hand);  
		}

		
	/**main method that plays the game 
    *@param = none 
    *@return = void
    */
    public void play(){
		// this method should play the game	

		Scanner input = new Scanner(System.in);
		int initialToken = 50;
		double bankRoll = p.initialBankroll(initialToken);  
		String winningHand; //string result of checkHand
		int number = 0;  //number of cards to be swapped out
		int out= 0; //position of card to be removed
		int prevOut=0;  //position of card previously chosen to be removed

		
		System.out.println("**************************");
		System.out.println("Welcome to Video Poker!");
		System.out.println("You will be dealt 5 cards and 50 initial tokens");
		System.out.println("If you wish to alter your hand,replace"
							+ " all or some of your cards with those from the top of the deck");
		System.out.println("You can bet 1-5 tokens at a time");
		System.out.println("Ready? Let's go!");
		System.out.println("**************************");

		
		System.out.println("How many tokens do you want to bet?");
		bet = (double)input.nextDouble();
		p.bets(bet);  //updates bankroll of player
		System.out.println("You have been dealt : ");
		for (Card element : hand){   //element = individual card
			System.out.println("" + element);
		}
		System.out.println("Do you wish to alter your hand? Enter Y/N");
		if (input.next().equals("y"))
		{
			System.out.println("How many cards do you want to swap out?");
			number = input.nextInt();
			if (number>5){   //prevents user from removing more cards than dealt
				System.out.println("You can't remove more than 5!");
				System.out.println("How many cards do you want to swap out?");
			}
			
			//swaps the first hand, sorts hand and stores index of re,oved card
            System.out.println("Enter position of card you want to reject(1-5)");
			out = input.nextInt();
			p.removeCard(hand.get(out-1));
			Card newCard = cards.deal();
			System.out.println("The new card is " +newCard);
			p.addAtIndex(out-1,newCard);
			this.hand = p.getHand();
			this.hand = sortHand(hand);
			prevOut= hand.indexOf(newCard);
			

			//executed if user chooses to swap more than 1 card 
			for(int i = 0; i< number-1; i++){
				System.out.println("Your new hand is :");
				for (Card piece : hand){  
					System.out.println("" + piece);}
				System.out.println("Enter position of next card you want to reject(1-5)");
				out = input.nextInt();
				if(out - 1 == prevOut){     //error checking to see if card is new
					boolean error = true;
					while (error){
						System.out.println("Sorry, that's a new card.You can't remove it");
						System.out.println("Choose another card : ");
						out = input.nextInt();
						if(out != prevOut){
							error = false;
						}
					}
				}
				p.removeCard(hand.get(out-1));
				p.addAtIndex(out-1,cards.deal());
				this.hand = p.getHand();
				this.hand = sortHand(hand);
				prevOut= hand.indexOf(newCard);
		    }
			this.hand = p.getHand();
			this.hand = sortHand(hand);
				
		}
		System.out.println("Your new hand is :");
		for (Card piece : hand){  
			System.out.println("" + piece);
		}


		//Evaluates hand and displays results 
		winningHand = checkHand(hand);
		System.out.println("Your hand has "+ winningHand);
		System.out.println("You bet"+bet+"tokens.So you have won"+reward+"tokens!");
		System.out.println("You have"+p.getBankroll()+"tokens left");
		System.out.println("Thank you for playing!");
	}

	/**evaluates for winning combo and 
    *rewards accordingly.
    *@param= hand (type: Arraylist<Card>)
    *@return= String
    */	
	public String checkHand(ArrayList<Card> hand){
		// this method should take an ArrayList of cards
		// as input and then determine what evaluates to and
		// return that as a String

		if (royalFlush(hand)==1){
			reward = 250 * bet;
			p.winnings(reward);
			return "Royal flush with 250 token rewards per token bet";
		}
		else if (straightFlush(hand)== 1){
			reward = 50 * bet;
			p.winnings(reward);
			return "Straight flush with 50 token rewards per token bet";
		}
		else if (fourofaKind(hand)== 1){
			reward = 25 * bet;
			p.winnings(reward);
			return "Four of a kind with 25 tokens rewards per token bet";
		}
		else if (fullHouse(hand)== 1){
			reward = 6 * bet;
			p.winnings(reward);
			return "Full house with 6 token rewards per token bet";
		}
		else if (flush(hand)== 1){
			reward = 5 * bet;
			p.winnings(reward);
			return "Flush with 5 token rewards per token bet";
		}
		else if (straight(hand)== 1){
			reward = 4 * bet;
			p.winnings(reward);
			return "Straight with 4 token rewards per token bet";
		}
		else if (threeofaKind(hand)== 1){
			reward = 3 * bet;
			p.winnings(reward);
			return "Three of a kind with 3 token rewards per token bet";
		}
		else if (twoPair(hand)== 1){
			reward = 2 * bet;
			p.winnings(reward);
			return "Two pairs with 2 token rewards per token bet";
		}
		else if (onePair(hand)== 1){
			reward = 1 * bet;
			p.winnings(reward);
			return "one pair with 1 token reward per token bet";
		}
		else {
			reward = 0;
			return "no pairs";
		}
	}
	
	
	// you will likely want many more methods here
	// per discussion in class


	//pair counter method
	public int pair(ArrayList<Card> hand){  
		int couple = 0 ;
		for(int i=0; i<4 ; i++){
			if ((hand.get(i)).getRank()==(hand.get(i+1)).getRank()){
				couple++;
			}
		}
		return couple;
	}

	//Defining each winning hand

		//One Pair
		public int onePair(ArrayList<Card> hand){
			if (pair(hand)==1){
				return 1;
			}
			else{
				return 0;
			}
		}

		//Two Pairs
		public int twoPair(ArrayList<Card> hand){
			if (pair(hand)== 2){
				return 1;
			}
			else{
				return 0;
			}
		}

		//Three of a kind
		public int threeofaKind(ArrayList<Card> hand){
			if ((hand.get(0)).getRank() == (hand.get(2)).getRank() || 
			(hand.get(1)).getRank() == (hand.get(3)).getRank() || 
			(hand.get(2)).getRank() == (hand.get(4)).getRank()){
				return 1;
			}
			else {
				return 0;
			}
		}

		//Straight
		public int straight (ArrayList<Card> hand){
			int difference = (hand.get(4)).getRank()-(hand.get(0)).getRank(); //difference between 5th and 1st element
			if (difference == 4 || difference == 12){   // 4 for all straight cases and 12 for Ace,10,J,Q,K
				return 1;
			}
			else {
				return 0;
			}
		}

		//Flush
		public int flush (ArrayList<Card> hand){
			if (pair(hand) > 0){  //the pair would necessarily be of different suites
				return 0;
			}
			else if ((hand.get(0)).getSuit()== (hand.get(1)).getSuit() 
					 && (hand.get(1)).getSuit()==(hand.get(2)).getSuit()
					 && (hand.get(2)).getSuit()==(hand.get(3)).getSuit()
					 && (hand.get(3)).getSuit()==(hand.get(4)).getSuit()){

					return 1;
			}
			else {
				return 0;
			}			
		}

		//Full House 
		public int fullHouse(ArrayList<Card> hand){
			if (threeofaKind(hand)==1 
				&& ((hand.get(0)).getRank())==((hand.get(1)).getRank())
			    ||threeofaKind(hand)==1 
				&&((hand.get(3)).getRank())==((hand.get(4)).getRank())){

					return 1;	
			}
			else{
				return 0;
			}
		}

		//Four of a kind
		public int fourofaKind(ArrayList<Card> hand){
			if ((hand.get(0)).getRank()==(hand.get(3)).getRank()
			    || (hand.get(1)).getRank()==(hand.get(4)).getRank()){           
			    
				return 1;
			}
			else{
				return 0;
			}
		}

		//Straight Flush
		public int straightFlush(ArrayList<Card> hand){
			if (straight(hand)==1 && flush(hand) == 1){
				return 1;
			}
			else {
				return 0;
			}
		}

		//Royal Flush
		public int royalFlush (ArrayList<Card> hand){
			int difference = (hand.get(4)).getRank() - (hand.get(0)).getRank();
			if (straightFlush(hand)==1 && difference == 12){
				return 1;
			}
			else{
				return 0;
			}
		} 


	//sorts hand in ascending order
	public ArrayList<Card> sortHand(ArrayList<Card> hand){
		Collections.sort(hand);
		return hand;
	}

}

