/**************************
*@author Lilita Yenew
*
**************************/
import java.util.ArrayList;
public class Player {
	
		
	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
    private double bet;
	// you may choose to use more instance variables
		
	public Player(){		
	    // create a player here
        hand = new ArrayList<Card>();
        bet = 0;  //nothing has been bet at the start of the game
    }


	public void addCard(Card c){
	    // add the card c to the player's hand
        hand.add(c);
	}

	public void removeCard(Card c){
	    // remove the card c from the player's hand
        hand.remove(c);  
    }
		
    public void bets(double amt){
        // player makes a bet
        bankroll -= amt;
    }

    public void winnings(double odds){
        //	adjust bankroll if player wins
        bankroll += odds;
    }

    public double getBankroll(){
        // return current balance of bankroll
        return bankroll;
    }

    // you may wish to use more methods here

    //sets starting bankroll to 50
    public double initialBankroll(double initialToken){
        bankroll = initialToken;
        return bankroll;
    } 

    //returns hand
    public ArrayList<Card> getHand(){
        return hand;
    }

    //adds card to a specific position
    public void addAtIndex(int i, Card c){
        hand.add(i,c);
    }
}


