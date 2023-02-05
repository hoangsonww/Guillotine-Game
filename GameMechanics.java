import javafx.scene.control.Button;
import javafx.scene.paint.Color;

/**
 * A class that contains methods and functions that helps with the Guillotine game
 * @author David Nguyen
 * @since  12/11/2022
 */
public class GameMechanics {
  
  // Stores the number of points Player 1 has
  int player1points = 0;
  
  // Stores the number of points Player 2 has
  int player2points = 0;

  // Stores the initial list of person cards for the game
  LinkedList<String> personCards = new LinkedList<String>();
  
  // Stores the new list of person cards for the game - which will actually be use by the game to play
  // This is done to differentiate this new list with the old person cards list, as the game may or may not use all
  // the available cards to play.
  LinkedList<String> newPersonList = new LinkedList<String>();
  
  // Stores the list of collected people for Player 1
  LinkedList<String> player1Collect = new LinkedList<String>();
  
  // Stores the list of collected people for Player 2
  LinkedList<String> player2Collect = new LinkedList<String>();
  
  // Stores an array of buttons that will display the person cards
  private Button[][] cards;
  
  // Stores an array of buttons that will display the available options for Player 1
  private Button[][] player1Options;
  
  // Stores an array of buttons that will display the available options for Player 2
  private Button[][] player2Options;
  
  // Stores an array of buttons that will display the people collected for Player 1
  private Button[][] player1Collected;
  
  // Stores an array of buttons that will display the people collected for Player 2
  private Button[][] player2Collected;

  // Stores true if it is Player1's turn. false if it is Player2's turn
  private boolean player1Turn = true;
  
  // Stores the winner
  private String winner;
  
  // Stores the number of person cards, initialized to 20
  private int numPersonCards = 20;
  
  /**
   * A method that gets the number of person cards
   * @return numPersonCards the number of person cards that the players wish to play with in the game
   */
  public int getNumCards() {
    return numPersonCards;
  }
  
  /**
   * A method that gets the number of player 1's points
   * @return the number of points this player has
   */
  public int getPlayer1Points() {
    return player1points;
  }
  
  /**
   * A method that gets the number of player 1's points
   * @param n the number of points this player is set to
   */
  public void setPlayer1Points(int n) {
    this.player1points = n;
  }
  
  /**
   * A method that gets the number of player 2's points
   * @return the number of points this player has
   */
  public int getPlayer2Points() {
    return player2points;
  }
  
  /**
   * A method that gets the number of player 1's points
   * @param n the number of points this player is set to
   */
  public void setPlayer2Points(int n) {
    this.player2points = n;
  }
  
  /**
   * A method that sets the number of person cards
   * @param numPersonCards the number of person cards that the players wish to play with in the game
   */
  public void setNumCards(int numPersonCards) {
    // If their input is valid (they have chosen the number between 2 and the number of cards available
    // Sets the number of starting person cards accordingly
    if (numPersonCards > 1 && numPersonCards < 41)
      this.numPersonCards = numPersonCards;
    // If their input is invalid (they have chosen the number smaller than 2 and greater than the number of cards available
    // Sets the game to the default number of cards of 20
    else {
      this.numPersonCards = 20;
      System.out.println("You Have Entered An Invalid Number of Cards. It has been set to the default value of 20.");
    }
  }
  
  /**
   * A method that gets the current turn
   * @return true if it is Player 1's turn, false if it is Player 2's turn
   */
  public boolean getTurn() {
    return player1Turn;
  }
  
  /**
   * A method that switches the current turn from Player 1's turn to Player 2's turn
   */
  public void switchTurn() {
    this.player1Turn = !getTurn();
  }
  
  /**
   * A method that sets up the game
   * @param numPersonCards the number of person cards that the players wish to play with in the game
   */
  public void gameSetUp(int numPersonCards) {
    // Initializes the linked list of person card for the game to all the cards available for play
    personCards.addToFront("King Louis XVI - Royal:  5");
    personCards.addToFront("Marie Antoinette - Royal:  5");
    personCards.addToFront("Regent - Royal:  4");
    personCards.addToFront("Duke - Royal:  3");
    personCards.addToFront("Baron - Royal:  3");
    personCards.addToFront("Count - Royal:  *");
    personCards.addToFront("Countess - Royal:  *");
    personCards.addToFront("Lord - Royal:  *");
    personCards.addToFront("Lady - Royal:  *");
    personCards.addToFront("Cardinal - Church:  5");
    personCards.addToFront("Archbishop - Church:  4");
    personCards.addToFront("Nun - Church:  3");
    personCards.addToFront("Bishop - Church:  2");
    personCards.addToFront("Priest - Church:  1");
    personCards.addToFront("Priest - Church:  1");
    personCards.addToFront("Heretic - Church:  *");
    personCards.addToFront("Governor - Civic:  4");
    personCards.addToFront("Mayor - Civic:  3");
    personCards.addToFront("Councilman - Civic:  3");
    personCards.addToFront("Judge - Civic:  2");
    personCards.addToFront("Judge - Civic:  2");
    personCards.addToFront("Tax Collector - Civic:  *");
    personCards.addToFront("Sheriff - Civic:  1");
    personCards.addToFront("Sheriff - Civic:  1");
    personCards.addToFront("Palace Guard - Military:  *");
    personCards.addToFront("Palace Guard - Military:  *");
    personCards.addToFront("Palace Guard - Military:  *");
    personCards.addToFront("Palace Guard - Military:  *");
    personCards.addToFront("Palace Guard - Military:  *");
    personCards.addToFront("General - Military:  4");
    personCards.addToFront("Colonel - Military:  3");
    personCards.addToFront("Captain - Military:  2");
    personCards.addToFront("Lieutenant - Military:  1");
    personCards.addToFront("Lieutenant - Military:  1");
    personCards.addToFront("Heroic Figure - Commoner:  -3");
    personCards.addToFront("Student - Commoner:  -1");
    personCards.addToFront("Student - Commoner:  -1");
    personCards.addToFront("Student - Commoner:  -1");
    personCards.addToFront("Student - Commoner:  -1");
    personCards.addToFront("Tragic Figure - Commoner:  -1");
    // Randomly selecting the person cards from the above list and creates the array of buttons accordingly
    newPersonList = personCards.getRandomElements(personCards, numPersonCards);
    cards = new Button[1][numPersonCards];
    /* Initializes each button so that they represent the person cards for playing
     * Condition: The loop's index must be less than the card list's length
     */
    for (int i = 0; i < cards[0].length; i++) {
      cards[0][i] = new Button(newPersonList.toStringNodeElement(newPersonList.getNodeFromList(i)));
      cards[0][i].setPrefSize(275, 50);
    }
    // Initializes each button so that they represent the available options to player 1
    player1Options = new Button [1][10];
    player1Options[0][0] = new Button("Move Front Back 4");
    player1Options[0][1] = new Button("Move Front Back 3");
    player1Options[0][2] = new Button("Move Front Back 2");
    player1Options[0][3] = new Button("Move Front Back 1");
    player1Options[0][4] = new Button("Move Front to End");
    player1Options[0][5] = new Button("Move Last Person to Front");
    player1Options[0][6] = new Button("Reverse Line");
    player1Options[0][7] = new Button("Reverse First 5");
    player1Options[0][8] = new Button("Skip Turn");
    player1Options[0][9] = new Button("Take Front Person");
    // Initializes each button so that they represent the available options to player 2
    player2Options = new Button [1][10];
    player2Options[0][0] = new Button("Move Front Back 4");
    player2Options[0][1] = new Button("Move Front Back 3");
    player2Options[0][2] = new Button("Move Front Back 2");
    player2Options[0][3] = new Button("Move Front Back 1");
    player2Options[0][4] = new Button("Move Front to End");
    player2Options[0][5] = new Button("Move Last Person to Front");
    player2Options[0][6] = new Button("Reverse Line");
    player2Options[0][7] = new Button("Reverse First 5");
    player2Options[0][8] = new Button("Skip Turn");
    player2Options[0][9] = new Button("Take Front Person");
  }
  
  /**
   * A method that converts the current card list to buttons by changing existing buttons' texts
   * Condition: The loop's index must be less than the button-card list's vertical length
   */
  public void convertCurrentCardListToButtons() {
    for (int i = 0; i < cards[0].length; i++) {
      cards[0][i].setText(newPersonList.toStringNodeElement(newPersonList.getNodeFromList(i)));
    }
  }
  
  /**
   * A method that handles the event when Player 1's "Move Front Back 4" button is clicked
   * Every time Player 1 chooses to Move Front Back 4, the front card in list will go back 4 places in list
   */
  public void player1MoveFrontBack4() {
    newPersonList.moveBack(4);
  }
  
  /**
   * A method that handles the event when Player 1's "Move Front Back 3" button is clicked
   * Every time Player 1 chooses to Move Front Back 3, the front card in list will go back 3 places in list
   */
  public void player1MoveFrontBack3() {
    newPersonList.moveBack(3);
  }
  
  /**
   * A method that handles the event when Player 1's "Move Front Back 2" button is clicked
   * Every time Player 1 chooses to Move Front Back 2, the front card in list will go back 2 places in list
   */
  public void player1MoveFrontBack2() {
    newPersonList.moveBack(2);
  }
  
  /**
   * A method that handles the event when Player 1's "Move Front Back 1" button is clicked
   * Every time Player 1 chooses to Move Front Back 1, the front card in list will go back 1 place in list
   */
  public void player1MoveFrontBack1() {
    newPersonList.moveBack(1);
  }
  
  /**
   * A method that handles the event when Player 1's "Move Front to End" button is clicked
   * Every time Player 1 chooses to Move Front to End, the front card in list will go to end of list
   */
  public void player1MoveFrontToEnd() {
    newPersonList.moveFirstToLast();
  }
  
  /**
   * A method that handles the event when Player 1's "Move Last Person to Front" button is clicked
   * Every time Player 1 chooses to Move Last Person to Front, the last card in list will go to front of list
   */
  public void player1MoveLastPersonToFront() {
    newPersonList.moveLastToFirst();
  }
  
  /**
   * A method that handles the event when Player 1's "Reverse Line" button is clicked
   * Every time Player 1 chooses to Reverse Line, the entire person cards list will be reversed
   */
  public void player1ReverseLine() {
    newPersonList.reverseList();
  }
  
  /**
   * A method that handles the event when Player 1's "Reverse First 5" button is clicked
   * Every time Player 1 chooses to Reverse First 5, the first 5 cards in the person cards list will be reversed
   */
  public void player1ReverseFirst5() {
    newPersonList.reverseFirstK(5);
  }
  
  /**
   * A method that handles the event when Player 1's "Skip turn" button is clicked
   * Every time Player 1 chooses to skip turn, their turn variavle will be set to false
   */
  public void player1SkipTurn() {
    switchTurn();
  }
  
  /**
   * A method that handles the event when Player 1's "Take Front Person" button is clicked
   * Every time a person is collected from the pile, Player 1 will get that person
   * @return the person card that the player has just collected
   */
  public String player1TakeFrontPerson() {
    // Stores the person card that has just been removed from the front of the card list
    String collect = newPersonList.removeFromFront();
    // Try to see if there is any compile- and run-time exceptions (especially looks for Null pointer exception)
    try {
      // Add to player 1's list of collected people
      player1Collect.addToFront(collect);
      // Add to player 1's points according to the number of points each card is worth
      if (collect.equals("King Louis XIV - Royal:  5"))
        player1points = player1points + 5;
      if (collect.equals("Marie Antoinette - Royal:  5"))
        player1points = player1points + 5;
      if (collect.equals("Regent - Royal:  4"))
        player1points = player1points + 4;
      if (collect.equals("Duke - Royal:  3"))
        player1points = player1points + 3;
      if (collect.equals("Baron - Royal:  3"))
        player1points = player1points + 3;
      // For each Count the player collects, check if the player has already had this person collected.
      // If yes, add 4 points to their points. Otherwise, add 2 points.
      if (collect.equals("Count - Royal:  *")) {
        player1points = player1points + 2;
        if (player1Collect.checkForExistingElement("Countess - Royal:  *"))
          player1points = player1points + 2;
      }
      // For each Countess the player collects, check if the player has already had this person collected.
      // If yes, add 4 points to their points. Otherwise, add 2 points.
      if (collect.equals("Countess - Royal:  *")) {
        player1points = player1points + 2;
        if (player1Collect.checkForExistingElement("Count - Royal:  *"))
          player1points = player1points + 2;
      }
      // For each Lord the player collects, check if the player has already had this person collected.
      // If yes, add 4 points to their points. Otherwise, add 2 points.
      if (collect.equals("Lord - Royal:  *")) {
        player1points = player1points + 2;
        if (player1Collect.checkForExistingElement("Lady - Royal:  *"))
          player1points = player1points + 2;
      }  
      // For each Lady the player collects, check if the player has already had this person collected.
      // If yes, add 4 points to their points. Otherwise, add 2 points.
      if (collect.equals("Lady - Royal:  *")) {
        player1points = player1points + 2;
        if (player1Collect.checkForExistingElement("Lord - Royal:  *"))
          player1points = player1points + 2;
      }
      if (collect.equals("Cardinal - Church:  5"))
        player1points = player1points + 5;
      if (collect.equals("Archbishop - Church:  4"))
        player1points = player1points + 4;
      if (collect.equals("Nun - Church:  3"))
        player1points = player1points + 3;
      if (collect.equals("Bishop - Church:  2"))
        player1points = player1points + 2;
      if (collect.equals("Priest - Church:  1"))
        player1points = player1points + 1;
      // For each Heretic the player collects, check for how many of the Church-type people has already had this person 
      // collected. If yes, add the points accordingly (at least 1 points because this player is collecting a Heretic - 
      // a church person as well.
      if (collect.equals("Heretic - Church:  *")) {
        player1points = player1points + player1Collect.checkForNumberOfExisting("Heretic - Church:  *")
          + player1Collect.checkForNumberOfExisting("Cardinal - Church:  5") + player1Collect.checkForNumberOfExisting("Archbisop - Church:  4") 
          + player1Collect.checkForNumberOfExisting("Nun - Church:  3") + player1Collect.checkForNumberOfExisting("Bishop - Church:  2") 
          + player1Collect.checkForNumberOfExisting("Priest - Church:  1");
      }
      if (collect.equals("Governor - Civic:  4"))
        player1points = player1points + 4;
      if (collect.equals("Mayor - Civic:  3"))
        player1points = player1points + 3;
      if (collect.equals("Councilman - Civic:  3"))
        player1points = player1points + 3;
      if (collect.equals("Judge - Civic:  2"))
        player1points = player1points + 2;
      // For each Tax Collector the player collects, check for how many of the Civic-type people has already had this person 
      // collected. If yes, add the points accordingly (at least 1 points because this player is collecting a Tax Collector - 
      // a Civic person as well.
      if (collect.equals("Tax Collector - Civic:  *")) {
        player1points = player1points + player1Collect.checkForNumberOfExisting("Tax Collector - Civic:  *") 
          + player1Collect.checkForNumberOfExisting("Governor - Civic:  4") + player1Collect.checkForNumberOfExisting("Mayor - Civic:  3") 
          + player1Collect.checkForNumberOfExisting("Councilman - Civic:  3") + player1Collect.checkForNumberOfExisting("Judge - Civic:  2");
      }
      if (collect.equals("Sheriff - Civic:  1"))
        player1points = player1points + 1;
      // For each Place Guard the player collects, check for how many Palace Guards people has already had this person 
      // collected. If yes, add the points accordingly (at least 1 points because this player is collecting a Palace
      // Guard as well.
      if (collect.equals("Palace Guard - Military:  *"))
        player1points = player1points + player1Collect.checkForNumberOfExisting("Palace Guard - Military:  *");
      if (collect.equals("General - Military:  4"))
        player1points = player1points + 4;
      if (collect.equals("Colonel - Military:  3"))
        player1points = player1points + 3;
      if (collect.equals("Captain - Military:  2"))
        player1points = player1points + 2;
      if (collect.equals("Lieutenant - Military:  1"))
        player1points = player1points + 1;
      if (collect.equals("Tragic Figure - Commoner:  -1"))
        player1points = player1points - 1;
      if (collect.equals("Student - Commoner:  -1"))
        player1points = player1points - 1;
      if (collect.equals("Heroic Figure - Commoner:  -3"))
        player1points = player1points - 3;
    }
    /**
     * If the null pointer exception is caught, this means the current list of cards is empty and therefore the game is 
     * ended
     */ 
    catch (NullPointerException a) {
      endGame();
      System.out.println("The game has ended!");
      displayWinner();
    }
    // Return the collected person card by this player
    return collect;
  }
 
  /**
   * A method that handles the event when Player 2's "Move Front Back 4" button is clicked
   * Every time Player 2 chooses to Move Front Back 4, the front card in list will go back 4 places in list
   */
  public void player2MoveFrontBack4() {
    newPersonList.moveBack(4);
  }
  
  /**
   * A method that handles the event when Player 2's "Move Front Back 3" button is clicked
   * Every time Player 2 chooses to Move Front Back 3, the front card in list will go back 3 places in list
   */
  public void player2MoveFrontBack3() {
    newPersonList.moveBack(3);
  }
  
  /**
   * A method that handles the event when Player 2's "Move Front Back 2" button is clicked
   * Every time Player 2 chooses to Move Front Back 2, the front card in list will go back 2 places in list
   */
  public void player2MoveFrontBack2() {
    newPersonList.moveBack(2);
  }
  
  /**
   * A method that handles the event when Player 2's "Move Front Back 1" button is clicked
   * Every time Player 2 chooses to Move Front Back 1, the front card in list will go back 1 place in list
   */
  public void player2MoveFrontBack1() {
    newPersonList.moveBack(1);
  }
  
  /**
   * A method that handles the event when Player 2's "Move Front to End" button is clicked
   * Every time Player 2 chooses to Move Front to End, the front card in list will go to end of list
   */
  public void player2MoveFrontToEnd() {
    newPersonList.moveFirstToLast();
  }
  
  /**
   * A method that handles the event when Player 2's "Move Last Person to Front" button is clicked
   * Every time Player 2 chooses to Move Last Person to Front, the last card in list will go to front of list
   */
  public void player2MoveLastPersonToFront() {
    newPersonList.moveLastToFirst();
  }
  
  /**
   * A method that handles the event when Player 2's "Reverse Line" button is clicked
   * Every time Player 2 chooses to Reverse Line, the entire person cards list will be reversed
   */
  public void player2ReverseLine() {
    newPersonList.reverseList();
  }
  
  /**
   * A method that handles the event when Player 2's "Reverse First 5" button is clicked
   * Every time Player 2 chooses to Reverse First 5, the first 5 cards in the person cards list will be reversed
   */
  public void player2ReverseFirst5() {
    newPersonList.reverseFirstK(5);
  }
  
  /**
   * A method that handles the event when Player 2's "Skip turn" button is clicked
   * Every time Player 1 chooses to skip turn, their turn variavle will be set to false and player 2's turn is true
   * Every time Player 2 chooses to skip turn, their turn variavle will be set to false and player 1's turn is true
   */
  public void player2SkipTurn() {
    switchTurn();
  }
  
  /**
   * A method that handles the event when Player 2's "Take Front Person" button is clicked
   * Every time a person is collected from the pile, Player 1 will get that person
   * @return the person card that the player has just collected
   */
  public String player2TakeFrontPerson() {
    // Stores the person card that has just been removed from the front of the card list
    String collect = newPersonList.removeFromFront();
    // Try to see if there's any exception during compile- and run-time (especially looks for Null pointer exception)
    try {
      // Add to player 2's list of collected people
      player2Collect.addToFront(collect);
      // Add to player 2's points according to the number of points each card is worth
      if (collect.equals("King Louis XIV - Royal:  5"))
        player2points = player2points + 5;
      if (collect.equals("Marie Antoinette - Royal:  5"))
        player2points = player2points + 5;
      if (collect.equals("Regent - Royal:  4"))
        player2points = player2points + 4;
      if (collect.equals("Duke - Royal:  3"))
        player2points = player2points + 3;
      if (collect.equals("Baron - Royal:  3"))
        player2points = player2points + 3;
      // For each Count the player collects, check if the player has already had this person collected.
      // If yes, add 4 points to their points. Otherwise, add 2 points.
      if (collect.equals("Count - Royal:  *")) {
        player2points = player2points + 2;
        if (player2Collect.checkForExistingElement("Countess - Royal:  *"))
          player2points = player2points + 2;
      }
      // For each Countess the player collects, check if the player has already had this person collected.
      // If yes, add 4 points to their points. Otherwise, add 2 points.
      if (collect.equals("Countess - Royal:  *")) {
        player2points = player2points + 2;
        if (player2Collect.checkForExistingElement("Count - Royal:  *"))
          player2points = player2points + 2;
      }
      // For each Lord the player collects, check if the player has already had this person collected.
      // If yes, add 4 points to their points. Otherwise, add 2 points.
      if (collect.equals("Lord - Royal:  *")) {
        player2points = player2points + 2;
        if (player2Collect.checkForExistingElement("Lady - Royal:  *"))
          player2points = player2points + 2;
      }
      // For each Lady the player collects, check if the player has already had this person collected.
      // If yes, add 4 points to their points. Otherwise, add 2 points.
      if (collect.equals("Lady - Royal:  *")) {
        player2points = player2points + 2;
        if (player2Collect.checkForExistingElement("Lord - Royal:  *"))
          player2points = player2points + 2;
      }
      if (collect.equals("Cardinal - Church:  5"))
        player2points = player2points + 5;
      if (collect.equals("Archbishop - Church:  4"))
        player2points = player2points + 4;
      if (collect.equals("Nun - Church:  3"))
        player2points = player2points + 3;
      if (collect.equals("Bishop - Church:  2"))
        player2points = player2points + 2;
      if (collect.equals("Priest - Church:  1"))
        player2points = player2points + 1;
      // For each Heretic the player collects, check for how many of the Church-type people has already had this person 
      // collected. If yes, add the points accordingly (at least 1 points because this player is collecting a Heretic - 
      // a church person as well.
      if (collect.equals("Heretic - Church:  *")) {
        player2points = player2points + player2Collect.checkForNumberOfExisting("Heretic - Church:  *") 
        + player2Collect.checkForNumberOfExisting("Cardinal - Church:  5") + player2Collect.checkForNumberOfExisting("Archbisop - Church:  4") 
        + player2Collect.checkForNumberOfExisting("Nun - Church:  3") + player2Collect.checkForNumberOfExisting("Bishop - Church:  2") 
        + player2Collect.checkForNumberOfExisting("Priest - Church:  1");
      }
      if (collect.equals("Governor - Civic:  4"))
        player2points = player2points + 4;
      if (collect.equals("Mayor - Civic:  3"))
        player2points = player2points + 3;
      if (collect.equals("Councilman - Civic:  3"))
        player2points = player2points + 3;
      if (collect.equals("Judge - Civic:  2"))
        player2points = player2points + 2;
      // For each Tax Collector the player collects, check for how many of the Civic-type people has already had this person 
      // collected. If yes, add the points accordingly (at least 1 points because this player is collecting a Tax Collector - 
      // a Civic person as well.
      if (collect.equals("Tax Collector - Civic:  1")) {
        player2points = player2points + player2Collect.checkForNumberOfExisting("Tax Collector - Civic:  1") 
          + player2Collect.checkForNumberOfExisting("Governor - Civic:  4") + player2Collect.checkForNumberOfExisting("Mayor - Civic:  3") 
          + player2Collect.checkForNumberOfExisting("Councilman - Civic:  3") + player2Collect.checkForNumberOfExisting("Judge - Civic:  2");
      }
      if (collect.equals("Sheriff - Civic:  1"))
        player2points = player2points + 1;
      // For each Place Guard the player collects, check for how many Palace Guards people has already had this person 
      // collected. If yes, add the points accordingly (at least 1 points because this player is collecting a Palace
      // Guard as well.
      if (collect.equals("Palace Guard - Military:  *"))
        player2points = player2points + player2Collect.checkForNumberOfExisting("Palace Guard - Military:  *");
      if (collect.equals("General - Military:  4"))
        player2points = player2points + 4;
      if (collect.equals("Colonel - Military:  3"))
        player2points = player2points + 3;
      if (collect.equals("Captain - Military:  2"))
        player2points = player2points + 2;
      if (collect.equals("Lieutenant - Military:  1"))
        player2points = player2points + 1;
      if (collect.equals("Tragic Figure - Commoner:  -1"))
        player2points = player2points - 1;
      if (collect.equals("Student - Commoner:  -1"))
        player2points = player2points - 1;
      if (collect.equals("Heroic Figure - Commoner:  -3"))
        player2points = player2points - 3;
    }
    /**
     * If the null pointer exception is caught, this means the current list of cards is empty and therefore the game is 
     * ended
     */ 
    catch (NullPointerException a) {
      endGame();
      System.out.println("The game has ended!");
      displayWinner();
    }
    // Return the collected person card by this player
    return collect;
  }
    
  /**
   * A method that returns the array of buttons that represents the list of person cards
   * @return the array of buttons that represents the list of person cards
   */
  public Button[][] getPersonCards() {
    return this.cards;
  }
  
  /**
   * A method that returns the array of buttons that represents the available options for player 1
   * @return the array of buttons that represents the list of available options for player 1 
   */
  public Button[][] getPlayer1Options() {
    return this.player1Options;
  }
  
  /**
   * A method that returns the array of buttons that represents the available options for player 2
   * @return the array of buttons that represents the list of available options for player 2 
   */
  public Button[][] getPlayer2Options() {
    return this.player2Options;
  }
  
  /**
   * A method that returns the array of buttons that represents the collected cards for player 1
   * @return the array of buttons that represents the list of collected cards for player 1 
   */
  public Button[][] getPlayer1Collected() {
    return this.player1Collected;
  }
  
  /**
   * A method that returns the array of buttons that represents the collected cards for player 2
   * @return the array of buttons that represents the list of collected cards for player 2 
   */
  public Button[][] getPlayer2Collected() {
    return this.player2Collected;
  }
  
  /**
   * A method that ends the game and sets the winner based on their accumulated points
   */
  public void endGame() {
    /* If player 1's points is greater than that of player 2, set player 1 as the winner.
     * If player 2's points is greater than that of player 1, set player 2 as the winner.
     * If player 1's points is equal to that of player 2, set the game to tie.
     * Then call the displayWinner() method to display the winner
     */ 
    if (player1points > player2points)
      this.winner = "Player 1";
    else if (player1points < player2points)
      this.winner = "Player 2";
    else if (player1points == player2points)
      this.winner = "Tie";
    displayWinner();
  }
  
  /**
   * A method that sets the winner of the game
   * @return the winner of the game
   */
  public String getWinner() {
    return this.winner;
  }
  
  /**
   * A method that sets the winner of the game
   * @param winner the winner that is wished to be set to
   */
  public void setWinner(String winner) {
    this.winner = winner;
  }
  
  /**
   * A method that displays the winner of the game
   */
  public void displayWinner() {
    /* If winner 1 wins, system will print accordingly to System.out.
     * If winner 2 wins, system will print accordingly to System.out.
     * Otherwise, system will print "This game is a tie~".
     */
    if(this.getWinner().equals("Player 1"))
      System.out.println("Player 1 is the winner of the Guillotine game!");
    else if(this.getWinner().equals("Player 2"))
      System.out.println("Player 2 is the winner of the Guillotine game!");
    else if(this.getWinner().equals("Tie"))
      System.out.println("This game is a tie~");
  }
  
}    