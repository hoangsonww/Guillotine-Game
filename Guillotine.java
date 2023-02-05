import javax.swing.*;
import java.util.*;
import java.io.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import javafx.scene.paint.*;
import javafx.scene.image.*;
import javafx.geometry.*;

/**
 * A class that represents the Guillotine game that is a JavaFX application
 * @author David Nguyen
 * @since  12/11/2022
 */
public class Guillotine extends Application {
  
  // A field storing a GameMechanics object for playing and running the game
  private static GameMechanics game = new GameMechanics();
  
  // A box that displays player 1's information. 
  // Should be a field because this will be constantly updated in many methods throughout the class.
  VBox player1 = new VBox();
  
  // A box that displays player 2's information. 
  // Should be a field because this will be constantly updated in many methods throughout the class.
  VBox player2 = new VBox();
  
  // Label for displaying Player 1's points.
  // Should be a field because this field will be constantly updated in many methods throughout the class.
  Label player1Pts = new Label ("Points: " + (int)(game.getPlayer1Points()));
  
  // Label for displaying Player 2's points.
  // Should be a field because this will be constantly updated in many methods throughout the class.
  Label player2Pts = new Label ("Points: " + (int)(game.getPlayer2Points()));
  
  // A field storing the number of clicks on the Take Front Person button from both players so far
  int countClicked = 0;
  
  /**
   * Sets up the initial display for the Guillotine game
   * @param primaryStage is a stage used to display the game 
   */
  public void start(Stage primaryStage) {
    // Try running the application first with the below code to see if any exception appears
    try {
      // Call the pre-defined function in GameMechanics to set up the game
      game.gameSetUp(game.getNumCards());
      // Creates a new background color for Player 1
      BackgroundFill bf = new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY);
      // Stores this background & sets Player 1's region color to this color
      Background background = new Background(bf);
      player1.setBackground(background);
      // Creates a new background color for Player 2
      BackgroundFill bf1 = new BackgroundFill(Color.GOLD, CornerRadii.EMPTY, Insets.EMPTY);
      // Stores this background & sets Player 2's region color to this color
      Background background1 = new Background(bf1);
      player2.setBackground(background1);
      // Stores the layout manager for the stage
      BorderPane pane = new BorderPane();
      // A pane that displays all the person cards, which will hold a VBox later on
      ScrollPane cardDisplay = new ScrollPane();
      // A box that contains the list of person cards
      VBox cards = new VBox();
      // Creates a new background color for the card list
      BackgroundFill bf2 = new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY);
      // Stores this background & sets card display's color to this color
      Background background2 = new Background(bf2);
      cardDisplay.setBackground(background2);
      // Creates a new background color for the card list's label
      BackgroundFill bf5 = new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY);
      Background background5 = new Background(bf5);
      // Label for the card list
      Label cardDisplayLabel = new Label(" THE LIST OF PERSON CARDS AVAILABLE FOR YOU: ");
      // Sets the label of the card list's color to this ORANGE Color
      cardDisplayLabel.setBackground(background5);
      cards.getChildren().add(cardDisplayLabel);
      // Label for displaying Player 1's information
      Label player1label = new Label ("Player 1");
      // Label for displaying Player 2's information
      Label player2label = new Label ("Player 2");
      // Creates a new background color for the label of available actions, player 1 & 2 info for both players
      BackgroundFill bf4 = new BackgroundFill(Color.GREENYELLOW, CornerRadii.EMPTY, Insets.EMPTY);
      // Stores this background & sets Players 1 & 2's available actions label's color to this color
      Background background4 = new Background(bf4);
      player1label.setBackground(background4);
      player1Pts.setBackground(background4);
      player2label.setBackground(background4);
      player2Pts.setBackground(background4);
      // Label for displaying a blank space (helps with game's aesthetics)
      Label space1 = new Label ("   ");
      // Label for displaying a blank space (helps with game's aesthetics)
      Label space2 = new Label ("   ");
      // Label for displaying Player 1's available actions
      Label availActions1 = new Label ("Available Actions:");
      availActions1.setBackground(background4);
      // Label for displaying Player 2's available actions
      Label availActions2 = new Label ("Available Actions:");
      availActions2.setBackground(background4);
      // Add all these labels to their respective player's VBoxes for displaying
      player1.getChildren().add(player1label);
      player1.getChildren().add(player1Pts);
      player1.getChildren().add(space1);
      player1.getChildren().add(availActions1);
      player2.getChildren().add(player2label);
      player2.getChildren().add(player2Pts);
      player2.getChildren().add(space2);
      player2.getChildren().add(availActions2);
      /* Displays and initializes each button for Player 1
       * The loop should sets every button from Player 1's array of buttons to be on screen and set them on action
       * Condition: The loop's index must be less than the player's array of buttons' length
       */
      for (int i = 0; i < game.getPlayer1Options()[0].length; i++) {
        player1.getChildren().add(game.getPlayer1Options()[0][i]);
        game.getPlayer1Options()[0][i].setOnAction(new ButtonHandler());
      }
      /* Displays and initializes each button for Player 1
       * The loop should sets every button from Player 2's array of buttons to be on screen and set them on action
       * Condition: The loop's index must be less than the player's array of buttons' length
       */
      for (int i = 0; i < game.getPlayer2Options()[0].length; i++) {
        player2.getChildren().add(game.getPlayer2Options()[0][i]);
        game.getPlayer2Options()[0][i].setOnAction(new ButtonHandler());
      }
      // A button allowing player 1 to surrender
      Button quitButton1 = new Button("SURRENDER!");
      // A button allowing player 1 to surrender
      Button quitButton2 = new Button("SURRENDER!");
      // A label for surrendering for both players
      Label quitRestart1 = new Label ("Surrender The Game:");
      // A label for surrendering for both players
      Label quitRestart2 = new Label ("Surrender The Game:");
      // A label for a blank space (help with game's aesthetics)
      Label space9 = new Label ("   ");
      // A label for a blank space (help with game's aesthetics)
      Label space10 = new Label ("   ");
      // Add the above labels and buttons to their appropriate locations on the VBox for each of the two players
      quitRestart1.setBackground(background4);
      quitRestart2.setBackground(background4);
      player1.getChildren().add(space9);
      player1.getChildren().add(quitRestart1);
      player2.getChildren().add(space10);
      player2.getChildren().add(quitRestart2);
      player1.getChildren().add(quitButton1);
      player2.getChildren().add(quitButton2);
      // If player 1 chooses to surrender, they'll be asked if they are sure to do so. If they are, the game is ended.
      // And Player 2 is the winner. Otherwise, the game will continue normally.
      quitButton1.setOnAction((ActionEvent event) -> {
        // Array storing the options when the player presses the surrender button
        String[] surrenderOptions = {"Cancel", "Surrender :("};
        // A variable storing the option the player chooses
        int option = 0;
        option = JOptionPane.showOptionDialog(null, "Are you sure you want to surrender?", "SURRENDER?", JOptionPane.INFORMATION_MESSAGE, 1, null, surrenderOptions, surrenderOptions[1]);
        if (option == 1) {
          Platform.exit();
          JOptionPane.showMessageDialog(null, "Player 1 chooses to surrender and the game has been ended. Player 2 is the winner!"); 
          System.out.println("Player 1 chooses to surrender and the game has been ended. Player 2 is the winner!");
        }
      });
      // If player 2 chooses to surrender, they'll be asked if they are sure to do so. If they are, the game is ended.
      // And Player 1 is the winner. Otherwise, the game will continue normally.
      quitButton2.setOnAction((ActionEvent event) -> {
        // Array storing the options when the player presses the surrender button
        String[] surrenderOptions = {"Cancel", "Surrender :("};
        // A variable storing the option the player chooses
        int option = 0;
        option = JOptionPane.showOptionDialog(null, "Are you sure you want to surrender?", "SURRENDER?", JOptionPane.INFORMATION_MESSAGE, 1, null, surrenderOptions, surrenderOptions[1]);
        if (option == 1) {
          Platform.exit();
          JOptionPane.showMessageDialog(null, "Player 2 chooses to surrender and the game has been ended. Player 1 is the winner!"); 
          System.out.println("Player 2 chooses to surrender and the game has been ended. Player 1 is the winner!");
        }
      });
      /* Displays the person cards
       * The loop should sets every person card from the card list to be on screen 
       * Condition: The loop's index must be less than the card list's length
       */
      for (int i = 0; i < game.getPersonCards()[0].length; i++) {
        cards.getChildren().add(game.getPersonCards()[0][i]);
        game.getPersonCards()[0][i].setBackground(background2);
      }
      cardDisplay.setContent(cards);
      // Label for displaying a blank space (helps with game's aesthetics)
      Label space3 = new Label ("   ");
      // Label for displaying a blank space (helps with game's aesthetics)
      Label space4 = new Label ("   ");
      // Label for displaying the text that tells the player 1 of their collected people
      Label peopleCollected1 = new Label ("People Collected:");
      peopleCollected1.setBackground(background4);
      // Label for displaying the text that tells the player 2 of their collected people
      Label peopleCollected2 = new Label ("People Collected:");
      peopleCollected2.setBackground(background4);
      // Add these labels to their respective VBoxes to displays them
      player1.getChildren().add(space3);
      player1.getChildren().add(peopleCollected1);
      player2.getChildren().add(space4);
      player2.getChildren().add(peopleCollected2);
      // Sets up the layout manager
      pane.setCenter(cardDisplay);
      pane.setLeft(player1);
      pane.setRight(player2);
      // Stores the scene for the stage and show the stage
      Scene scene = new Scene(pane, 600, 800);
      scene.setFill(Color.PINK);
      primaryStage.setScene(scene);
      primaryStage.setTitle("Welcome to the Guillotine game!");
      primaryStage.show();
    }
    // If an exception is caught in runtime, print the message
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
  
  /**
   * Sets up the event handler for the buttons of the Guillotine game
   */
  private class ButtonHandler implements EventHandler<ActionEvent> { 
    /**
     * A method that handles every button clicks
     * Overriding the handle method in EventHandler 
     * @param e the information about the button click
     */
    @Override
    public void handle(ActionEvent e) {
      // If it's player 1's turn
      if (game.getTurn()) {
        // Gets the button that was clicked
        Button b = (Button)e.getSource();
        /** 
         * Every time the player chooses an option, that option will no longer be enabled for the player as the 
         * action is considered used. They're only re-enabled when the turn is switched.
         */ 
        // Handling the event when Player 1's move front back 4 button is pressed: Call the handler created in class
        // Game Mechanics, update the current card list, and disable this option for this player
        if (b == game.getPlayer1Options()[0][0]) {
          game.player1MoveFrontBack4();
          game.convertCurrentCardListToButtons();
          game.getPlayer1Options()[0][0].setVisible(false);
        }
        // Handling the event when Player 1's move front back 3 button is pressed: Call the handler created in class
        // Game Mechanics, update the current card list, and disable this option for this player
        else if (b == game.getPlayer1Options()[0][1]) {
          game.player1MoveFrontBack3();
          game.convertCurrentCardListToButtons();
          game.getPlayer1Options()[0][1].setVisible(false);
        }
        // Handling the event when Player 1's move front back 2 button is pressed: Call the handler created in class
        // Game Mechanics, update the current card list, and disable this option for this player
        else if (b == game.getPlayer1Options()[0][2]) {
          game.player1MoveFrontBack2();
          game.convertCurrentCardListToButtons();
          game.getPlayer1Options()[0][2].setVisible(false);
        }
        // Handling the event when Player 1's move front back 1 button is pressed: Call the handler created in class
        // Game Mechanics, update the current card list, and disable this option for this player
        else if (b == game.getPlayer1Options()[0][3]) {
          game.player1MoveFrontBack1();
          game.convertCurrentCardListToButtons();
          game.getPlayer1Options()[0][3].setVisible(false);
        }
        // Handling the event when Player 1's move front to back button is pressed: Call the handler created in class
        // Game Mechanics, update the current card list, and disable this option for this player
        else if (b == game.getPlayer1Options()[0][4]) {
          game.player1MoveFrontToEnd();
          game.convertCurrentCardListToButtons();
          game.getPlayer1Options()[0][4].setVisible(false);
        }
        // Handling the event when Player 1's move last to front button is pressed: Call the handler created in class
        // Game Mechanics, update the current card list, and disable this option for this player
        else if (b == game.getPlayer1Options()[0][5]) {
          game.player1MoveLastPersonToFront();
          game.convertCurrentCardListToButtons();
          game.getPlayer1Options()[0][5].setVisible(false);
        }
        // Handling the event when Player 1's reverse line button is pressed: Call the handler created in class
        // Game Mechanics, update the current card list, and disable this option for this player
        else if (b == game.getPlayer1Options()[0][6]) {
          game.player1ReverseLine();
          game.convertCurrentCardListToButtons();
          game.getPlayer1Options()[0][6].setVisible(false);
        }
        // Handling the event when Player 1's reverse first 5 button is pressed: Call the handler created in class
        // Game Mechanics, update the current card list, and disable this option for this player
        else if (b == game.getPlayer1Options()[0][7]) {
          game.player1ReverseFirst5();
          game.convertCurrentCardListToButtons();
          game.getPlayer1Options()[0][7].setVisible(false);
        }
        // Handling the event when Player 1's skip turn button is pressed: Call the handler created in class
        // Game Mechanics, update the current card list, and show a dialog prompting which player's turn it currently is
        else if (b == game.getPlayer1Options()[0][8]) {
          game.player1SkipTurn();
          game.convertCurrentCardListToButtons();  
          JOptionPane.showMessageDialog(null, "Now it's Player 2's turn!"); 
        }
        // Handling the event when Player 1's take front person is pressed: Call the handler created in class
        // Game Mechanics, update the current card list, show a dialog prompting which player's turn it currently is,
        // And add points to this player.
        else if (b == game.getPlayer1Options()[0][9]) {
          // A variable for storing the person card that this player has just taken
          String save = game.player1TakeFrontPerson();
          // Then switch the turn to the other player and update the card list
          game.player1SkipTurn();
          game.convertCurrentCardListToButtons();
          // Add the information of the person card this player has just collected to the player's information area on the screen
          player1.getChildren().add(new Label(save));
          // End the game when the last card is taken from the list
          if (game.newPersonList.getFirstNode().getNext() == null) {
            game.endGame();
            game.getPlayer1Options()[0][9].setVisible(false);
          }
          // Deleting the last person card from card line by incrementing the number of clicks on the "Take Front Person" Button
          countClicked++;
          game.getPersonCards()[0][game.getPersonCards()[0].length - countClicked].setVisible(false);
          // Update the player's points
          player1Pts.setText("Points: " + (int)(game.getPlayer1Points()));
          JOptionPane.showMessageDialog(null, "Now it's Player 2's turn!"); 
        }
      }
      // If it's player 2's turn
      else if (!game.getTurn()) {
        // Gets the button that was clicked
        Button b = (Button)e.getSource();
        /** 
         * Every time the player chooses an option, that option will no longer be enabled for the player as the 
         * action is considered used. They're only re-enabled when the turn is switched.
         */ 
        // Handling the event when Player 2's move front back 4 button is pressed: Call the handler created in class
        // Game Mechanics, update the current card list, and disable this option for this player
        if (b == game.getPlayer2Options()[0][0]) {
          game.player2MoveFrontBack4();
          game.convertCurrentCardListToButtons();
          game.getPlayer2Options()[0][0].setVisible(false);
        }
        // Handling the event when Player 2's move front back 3 button is pressed: Call the handler created in class
        // Game Mechanics, update the current card list, and disable this option for this player
        else if (b == game.getPlayer2Options()[0][1]) {
          game.player2MoveFrontBack3();
          game.convertCurrentCardListToButtons();
          game.getPlayer2Options()[0][1].setVisible(false);
        }
        // Handling the event when Player 2's move front back 2 button is pressed: Call the handler created in class
        // Game Mechanics, update the current card list, and disable this option for this player
        else if (b == game.getPlayer2Options()[0][2]) {
          game.player2MoveFrontBack2();
          game.convertCurrentCardListToButtons();
          game.getPlayer2Options()[0][2].setVisible(false);
        }
        // Handling the event when Player 2's move front back 1 button is pressed: Call the handler created in class
        // Game Mechanics, update the current card list, and disable this option for this player
        else if (b == game.getPlayer2Options()[0][3]) {
          game.player2MoveFrontBack1();
          game.convertCurrentCardListToButtons();
          game.getPlayer2Options()[0][3].setVisible(false);
        }
        // Handling the event when Player 2's move front to end button is pressed: Call the handler created in class
        // Game Mechanics, update the current card list, and disable this option for this player
        else if (b == game.getPlayer2Options()[0][4]) {
          game.player2MoveFrontToEnd();
          game.convertCurrentCardListToButtons();
          game.getPlayer2Options()[0][4].setVisible(false);
        }
        // Handling the event when Player 2's move last person to front button is pressed: Call the handler created in class
        // Game Mechanics, update the current card list, and disable this option for this player
        else if (b == game.getPlayer2Options()[0][5]) {
          game.player2MoveLastPersonToFront();
          game.convertCurrentCardListToButtons();
          game.getPlayer2Options()[0][5].setVisible(false);
        }
        // Handling the event when Player 2's reverse line button is pressed: Call the handler created in class
        // Game Mechanics, update the current card list, and disable this option for this player
        else if (b == game.getPlayer2Options()[0][6]) {
          game.player2ReverseLine();
          game.convertCurrentCardListToButtons();
          game.getPlayer2Options()[0][6].setVisible(false);
        }
        // Handling the event when Player 2's reverse first 5 button is pressed: Call the handler created in class
        // Game Mechanics, update the current card list, and disable this option for this player
        else if (b == game.getPlayer2Options()[0][7]) {
          game.player2ReverseFirst5();
          game.convertCurrentCardListToButtons();
          game.getPlayer2Options()[0][7].setVisible(false);
        }
        // Handling the event when Player 2's skip turn button is pressed: Call the handler created in class
        // Game Mechanics, update the current card list, and show a dialog prompting which player's turn it currently is
        else if (b == game.getPlayer2Options()[0][8]) {
          game.player2SkipTurn();
          game.convertCurrentCardListToButtons();
          JOptionPane.showMessageDialog(null, "Now it's Player 1's turn!"); 
        }
        // Handling the event when Player 2's take front person is pressed: Call the handler created in class
        // Game Mechanics, update the current card list, show a dialog prompting which player's turn it currently is,
        // And add points to this player.
        else if (b == game.getPlayer2Options()[0][9]) {
          // A variable for storing the person card that this player has just taken
          String save = game.player2TakeFrontPerson();
          // Then switch the turn to the other player and update the card list
          game.player2SkipTurn();
          game.convertCurrentCardListToButtons();
          // Add the information of the person card this player has just collected to the player's information area on the screen
          player2.getChildren().add(new Label(save));
          // End the game when the last card is taken from the list
          if (game.newPersonList.getFirstNode().getNext() == null) {
            game.endGame();
            game.getPlayer2Options()[0][9].setVisible(false);
          }
          // Deleting the last person card from card line
          countClicked++;
          game.getPersonCards()[0][game.getPersonCards()[0].length - countClicked].setVisible(false);
          // Update the player's points
          player2Pts.setText("Points: " + (int)(game.getPlayer2Points()));
          JOptionPane.showMessageDialog(null, "Now it's Player 1's turn!"); 
        }
      }
    }
  }
  
  /** 
   * A method that starts the game
   * @param args the number of person cards the players wish to play with
   */ 
  public static void main(String[] args) {
    // Storing the options available to the user when they start the application/game
    String[] startOptions = {"Play with 20 Cards", "I'd like to set the number of cards", "Show me the instructions"};
    // Storing the options available to the user after they press to see the instructions
    String[] afterInstructionOptions = {"Play with 20 Cards", "I'd like to set the number of cards"};
    // Storing the option the user selects
    int option = 0;
    // Show the dialog to the user prompting them to select one option displayed to them on the screen
    option = JOptionPane.showOptionDialog(null, "WELCOME TO THE GUILLOTINE GAME!", "Guillotine!", JOptionPane.INFORMATION_MESSAGE, 1, null, startOptions, startOptions[2]);
    // If the user chooses to see the instructions
    if (option == 2) {
      option = JOptionPane.showOptionDialog(null, "Here are the instructions: \nYou will first enter the number of cards you would like to play with (should be any integer between 2 and 40). \nEach player will collect the cards from the front of the card list (which will be in the CENTER of your screen), and there is a set of actions you can implement once in the game. You can skip your turn if you don't want the current player in front of the card list. \nEach card is worth a set number of points. There will be 2 players, and who ever got more poins when there's no cards left will win. \nYou can also surrender if you don't see any chance of winning the game by pressing the 'SURRENDER' button. \nHave fun, and remember to think carefully!",
                                            "GUILLOTINE!", JOptionPane.INFORMATION_MESSAGE, 1, null, afterInstructionOptions, afterInstructionOptions[1]);
    }
    // If the user chooses to play with their own desired starting number of cards
    if (option == 1) {
      // Shows the input dialog prompting the user to enter the number of cards they would like to play with
      // Stores the user's chosen number of cards in the String input variable below
      String input = JOptionPane.showInputDialog("Please give us the number of cards you would like to play with (any integer between 2 and 40)");
      // If their input is invalid (they have chosen the number smaller than 2 and greater than the number of cards available
      // Prompts the user that their input is invalid and sets the game to the default number of cards of 20
      if ((int)Double.parseDouble(input) < 2 || (int)Double.parseDouble(input) > 40) {
        JOptionPane.showMessageDialog(null, "You Have Entered An Invalid Number of Cards. It has been set to the default value of 20."); 
        game.setNumCards(20);
      }
      // If their input is valid (they have chosen the number between 2 and the number of cards available
      // Sets the number of starting person cards accordingly
      if ((int)Double.parseDouble(input) >= 2 || (int)Double.parseDouble(input) <= 40) {
        game.setNumCards((int)Double.parseDouble(input));
      }
    }
    // Launch the game accordingly
    Application.launch(args);
  } 
  
}