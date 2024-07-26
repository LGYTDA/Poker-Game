/**************************
*@auhtor Lilita Yenew
*@uni lgy2104
*
**************************/
import java.util.ArrayList;
import java.util.Collections;
public class Deck {
	
	private Card[] cards;
	private int top; // the index of the top of the deck
	// add more instance variables if needed
	String[] strDeck; //a string array for cards before they're translated to objects
	String strRank;  
	int suit;
	int rank;
	
	//constructor
    public Deck(){
		// make a 52 card deck here
		top = 0;
        cards = new Card[52];
		strDeck = new String[52];
        String[] possibleSuits = {"1","2","3","4"};   //1=clubs, 2=diamonds
                                                      // , 3=hearts , 4=spades
        String[] possibleRanks = {"1","2","3","4","5","6","7",
								"8","9","10","11","12","13"};  //1=Ace , 11=Jack 
                                                            //, 12=Queen, 13=King
        
        //pairs each element of the first array with
        //the 13 elements of the second to form 
        // a string array deck
        for (int i=0 ; i<strDeck.length ; i++){
            strDeck[i] = possibleSuits[i/13]+possibleRanks[i%13];
        }

        //converts string cards to objects 
        //by translating the value of the rank and suit
        //and passing them through Card 
		for (int i=0 ; i<cards.length ; i++){
			strRank = strDeck[i].substring(1);
			suit = Integer.parseInt(String.valueOf(strDeck[i].charAt(0)));
			rank = Integer.parseInt(String.valueOf(strRank));

			Card piece = new Card(suit,rank);
			cards[i] = piece; 
		}	 
	}
	
	/**shuffles cards using algorithm previously
    *discussed in class.
    *@param= none
    *@return = nothing
    */
    public void shuffle(){
		// shuffle the deck here
		Card temp = new Card(suit,rank);
        for(int i=0 ; i<cards.length ; i++){
            int index = (int)(Math.random() * cards.length);
            temp = cards[i];
            cards[i] = cards[index];
            cards[index] = temp;
        }
	}
	
    /**deals the top card of the deck to player
    *@param= none
    *@return = dealt card (type: Card)
    */
	public Card deal(){
		// deal the top card in the deck
		Card dealtCard = cards[top];
		top++;  //moves to next card
		return dealtCard;
	}
	
	// add more methods here if needed

}
