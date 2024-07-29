/**********************************
*@author Lilita Yenew
***********************************/

public class Card implements Comparable<Card>{
	
	private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
	
	public Card(int s, int r){
		//make a card with suit s and value v
		suit = s;
		rank = r;
	}
	
	public int compareTo(Card c){
		// use this method to compare cards so they 
		// may be easily sorted
		if (this.getRank()> c.getRank()){
			return 1;
		}
		else if (this.getRank() < c.getRank()){
			return -1;
		}
		else {
			return 0;
		}
	}
	
	public String toString(){
		// use this method to easily print a Card object
		String stringSuit="";
		String stringRank="";
		String representation;

		//Converts integer suit to string
		if(suit==1){
			stringSuit = "Clubs";
		}
		else if(suit==2){
			stringSuit = "Diamonds";
		}
		else if(suit==3){
			stringSuit = "Hearts";
		}
		else {
			stringSuit = "Spades";
		}

		//Converts int representation of ace and face cards to string
		//so that the cards can be presented in regular english
		if(rank==1){
			stringRank = "Ace";
		}
		else if(rank>1&rank<11){
			stringRank = "" + rank;
		}
		else if(rank==11){
			stringRank = "Jack";
		}
		else if(rank==12){
			stringRank = "Queen";
		}
		else if(rank==13){
			stringRank = "King";
		}
		else {        //card is neither an ace nor a face card
			//do nothing
		}
		representation =stringRank + "of" + stringSuit;
		return representation;	
	}
	// add some more methods here if needed

	//returns int rank
	public int getRank(){
		return rank;
	}

	//returns int suit representation
	public int getSuit(){
		return suit;
	}

}
