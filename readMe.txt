/**********************************
*@author = Lilita Yenew
*
*/
Player class
        * The mutator methods addCard, addAtIndex, and removeCard allow the player to modify his his hand(i.e the initially
         empty arraylist).These basically let the player function like a human player would. The remaining methods
         are used to keep track of how many tokens the layer has bet and won , and make instances variables accessible 
         across classes. 
Card class
        * In this class, the foundations of a card object are defined. A card object that takes in integer values of suit and 
        rank is created so that individual cards can be created. The class implements comparable to inorder to 
        compare the rank of these cards and return the difference which is used to sort the player's hand in another class.
        Because the integer representation of cards is not easily comprehensible to a human user, there is a toString method 
        the translates the suit and rank values used to create a card to their English equivalents. The variable "representation"
        then hold the concatenation of the string rank and string suit(for eg: 1 of spades) and is returned to the to String method.

        * methods getSuit and getRank are used to access the suit and rank from other classes.
Deck class
    * In this class, I created the string array possibleSuits and possibleRanks to hold all the possible suit values and 
     rank values respectively in a deck. I then used a for loop that creates 13 cards for each suit by using
    possibleSuits[i/13] which stays on one suit element for 13 iterations while possibleRanks[i%13] iterates through the 
    strRank array. After 13 cards of the first suit element are created, 14/13= 1 so it moves onto the next suit element.

    * All of the possible values are created and stored in a string Deck. I then went through all elements of the string deck, 
    parsing their suits and ranks into integers using valueOf and parseInt and passing them as parameters of Card to create Card 
    objects and add them to the deck.

    * The shuffle method shuffles cards and the deal method removes top card from the deck and adds it to 
    player's hand.
Game class 
    * This is the main class that creates the player, deals player a hand, and evaluates it to reward tokens accordingly.
     public Game(String[] testHand) is used to test thhe program by passing in arguments from the command line. charAt is used to 
     obtain the suit and substringing and parsing are used to convert the string rank into a integer. Both are passed as arguments 
     to card to create a hand which gets sorted. the deck is also shuffled.

     * In the play method, the player is given an initial bankroll of 50 tokens. A random hand is dealt to the player.The 
     player bets tokens and modifies their hand is they want. The final hand they choose to go with is evaluated to see whether
     it has any winning combos using checkHand. Individual methods that return 1 if conditions are met are used to check for 
     winning card combinations in the player's hand. checkHand checks if any of these methods returned 1 and rewards the user
     tokens accordingly.

     * sortHand uses collections.sort and the value from compare to sort the hand in ascending rank order.





   
