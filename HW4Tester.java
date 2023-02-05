import org.junit.*;
import java.util.Arrays;
import junit.framework.TestCase;
import static org.junit.Assert.assertEquals;
import java.util.NoSuchElementException;

/**
 * A test class for all methods in this project.
 * Testing as many classes as possible (except for those using / directly associated with JavaFX gadgets.)
 * Therefore, methods having JavaFX components like buttons, panes, etc. are unable to be tested with this JUnit test,
 * since they are very hard to test.
 * The tests of these methods have been included detailedly in the attached testing document instead
 * @author David Nguyen
 * @since  12/11/2022
 */
public class HW4Tester extends TestCase {
  
 /**
  * Testing the methods in the LinkedList class:
  */
  
  /**
   * Testing the getFirstNode() method
   */
  public void testGetFirstNode() {
    LinkedList<String> list = new LinkedList<String>();
    list.addToFront("abc");
    list.addToFront("xyz");
    // Case 1: Test to see if the program correctly returns the first node by comparing it to another node with the same 
    // element as the first node
    LLNode<String> node = new LLNode<String>("xyz", null);
    assertEquals(true, node.getElement().equals(list.getFirstNode().getElement()));
    // Case 2: Test to see if the program correctly returns the first node by comparing it to another node with the different 
    // element as the first node
    LLNode<String> node1 = new LLNode<String>("abc", null);
    assertEquals(false, node1.getElement().equals(list.getFirstNode().getElement()));
  }
  
  /**
   * Testing the setFirstNode() method
   */
  public void testSetFirstNode() {
    LinkedList<String> list = new LinkedList<String>();
    LLNode<String> node = new LLNode<String>("xyz", null);
    LLNode<String> node1 = new LLNode<String>("xyz", null);
    list.setFirstNode(node);
    // Case 1: Test to see if the program sets the first node for list by comparing the list's first node to another node
    // with the same element as the first node
    assertEquals(true, node1.getElement().equals(list.getFirstNode().getElement()));
    // Case 2: Test to see if the program sets the first node for list by comparing the list's first node to another node
    // with a different element as the first node
    LLNode<String> node2 = new LLNode<String>("aaa", null);
    assertEquals(false, node2.getElement().equals(list.getFirstNode().getElement()));
  }
  
  /**
   * Testing the isEmpty() method
   */
  public void testIsEmpty() {
    LinkedList<String> list = new LinkedList<String>();
    // Case 1: Test with the empty list
    assertEquals(true, list.isEmpty());
    // Case 2: Test with the list that has one element inside
    list.addToFront("abc");
    assertEquals(false, list.isEmpty());
    // Case 3: Test with the list that has more than one element inside
    list.addToFront("abc");
    assertEquals(false, list.isEmpty());
  }
  
  /**
   * Testing the addToFront() method
   */
  public void testAddToFront() {
    LinkedList<String> list = new LinkedList<String>();
    list.addToFront("abc");
    // Case 1: Testing the add to front method to see if it correctly adds the element to front of the list
    // The newly added element will also be the first element of the list, so can use the getFirstNode() method to test
    LLNode<String> node = new LLNode<String>("abc", null);
    assertEquals(true, node.getElement().equals(list.getFirstNode().getElement()));
  }
  
  /**
   * Testing the removeFromFront() method
   */
  public void testRemoveFromFront() {
    LinkedList<String> list = new LinkedList<String>();
    list.addToFront("abc");
    list.addToFront("xyz");
    // Case 1: Tests if the method correctly outputs the first node and sets the second node as the first node
    // Also tests when the node after the first node is NOT null
    assertEquals(true, list.removeFromFront().equals("xyz"));
    assertEquals(true, list.getFirstNode().getElement().equals("abc"));
    // Case 2: Tests if the method behaves correctly when the node after the first node is null
    LinkedList<String> list2 = new LinkedList<String>();
    list2.addToFront("abc");
    assertEquals(true, list.removeFromFront().equals("abc"));
  }
  /**
   * Case 3: Test if removing a node from an empty list will throw a No Such Element Exception:
   */
  public void testRemoveFromFrontException() {
    boolean thrown = false;
    try {
      LinkedList<String> list = new LinkedList<String>();
      list.removeFromFront();
    }
    catch (NoSuchElementException e) {
      thrown = true;
    assertTrue(thrown);
    }
  }
  
  /**
   * Testing the length() method
   */
  public void testLength() {
    LinkedList<String> list = new LinkedList<String>();
    // Case 1: Tests to see if the method correctly outputs the list's length when the length is 0
    assertEquals(0, list.length());
    list.addToFront("abc");
    // Case 2: Tests to see if the method correctly outputs the list's length when the length is 1
    assertEquals(1, list.length());
    list.addToFront("xyz");
    // Case 3: Tests to see if the method correctly outputs the list's length when the length is 2
    assertEquals(2, list.length());
  }
  
  /**
   * Testing the moveBack(int n) method
   */
  public void testMoveBack() {
    LinkedList<String> list = new LinkedList<String>();
    list.addToFront("abc");
    list.addToFront("efg");
    list.addToFront("mns");
    list.addToFront("xyz");
    // Case 1: Test if the method correctly moves first node back 0 place
    // The list will remain as it is
    list.moveBack(0);
    assertEquals(true, list.getFirstNode().getElement().equals("mns"));
    // Case 2: Test if the method correctly moves the first node back 1 place
    // The second node of the list will now become the first node and first node is moved back 1 place
    LinkedList<String> list1 = new LinkedList<String>();
    list1.addToFront("abc");
    list1.addToFront("efg");
    list1.addToFront("mns");
    list1.addToFront("xyz");
    list1.moveBack(1);
    assertEquals(true, list1.getFirstNode().getElement().equals("mns"));
    assertEquals(true, list1.getFirstNode().getNext().getElement().equals("xyz"));
    // Case 3: Test if the method correctly moves the first node back more than 1 place (2 places)
    LinkedList<String> list2 = new LinkedList<String>();
    list2.addToFront("abc");
    list2.addToFront("efg");
    list2.addToFront("mns");
    list2.addToFront("xyz");
    list2.moveBack(2);
    assertEquals(true, list2.getFirstNode().getElement().equals("mns"));
    assertEquals(true, list2.getFirstNode().getNext().getNext().getElement().equals("xyz"));
  }
  /**
   * Case 4: Test if moving a node in an empty list will throw a No Such Element Exception:
   */
  public void testMoveBackException() {
    boolean thrown = false;
    try {
      LinkedList<String> list = new LinkedList<String>();
      list.moveBack(1);
    }
    catch (NoSuchElementException e) {
      thrown = true;
    assertTrue(thrown);
    }
  }
  
    
  /**
   * Testing the moveFirstToLast() method
   */
  public void testMoveFirstToLast() {
    LinkedList<String> list = new LinkedList<String>();
    list.addToFront("abc");
    list.addToFront("efg");
    list.addToFront("mns");
    list.addToFront("xyz");
    list.moveFirstToLast();
    // Case 1: Test if the method correctly moves first node to the end of list and second node becomes first node
    assertEquals(true, list.getFirstNode().getElement().equals("mns"));
    assertEquals(true, list.getFirstNode().getNext().getNext().getNext().getElement().equals("xyz"));
  }
  /**
   * Case 2: Test if moving a node in an empty list will throw a No Such Element Exception:
   */
  public void testMoveFirstToLastException() {
    boolean thrown = false;
    try {
      LinkedList<String> list = new LinkedList<String>();
      list.moveFirstToLast();
    }
    catch (NoSuchElementException e) {
      thrown = true;
    assertTrue(thrown);
    }
  }
    
  /**
   * Testing the moveLastToFirst() method
   */
  public void testMoveLastToFirst() {
    LinkedList<String> list = new LinkedList<String>();
    list.addToFront("abc");
    list.addToFront("efg");
    list.addToFront("mns");
    list.addToFront("xyz");
    list.moveLastToFirst();
    // Case 1: Test if the method correctly moves last node to the front of list
    assertEquals(true, list.getFirstNode().getElement().equals("abc"));
    assertEquals(true, list.getFirstNode().getNext().getElement().equals("xyz"));
  }
  /**
   * Case 2: Test if moving a node in an empty list will throw a No Such Element Exception:
   */
  public void testMoveLastToFirstException() {
    boolean thrown = false;
    try {
      LinkedList<String> list = new LinkedList<String>();
      list.moveLastToFirst();
    }
    catch (NoSuchElementException e) {
      thrown = true;
    assertTrue(thrown);
    }
  }
  
  /**
   * Testing the reverseList() method
   */
  public void testReverseList() {
    LinkedList<String> list = new LinkedList<String>();
    list.addToFront("abc");
    list.addToFront("efg");
    list.addToFront("mns");
    list.addToFront("xyz");
    list.reverseList();
    // Case 1: Test if the method correctly reverses the entire list
    assertEquals(true, list.getFirstNode().getElement().equals("abc"));
    assertEquals(true, list.getFirstNode().getNext().getElement().equals("efg"));
    assertEquals(true, list.getFirstNode().getNext().getNext().getElement().equals("mns"));
    assertEquals(true, list.getFirstNode().getNext().getNext().getNext().getElement().equals("xyz"));
  }
  /**
   * Case 2: Test if moving a node in an empty list will throw a No Such Element Exception:
   */
  public void testReverseListException() {
    boolean thrown = false;
    try {
      LinkedList<String> list = new LinkedList<String>();
      list.reverseList();
    }
    catch (NoSuchElementException e) {
      thrown = true;
    assertTrue(thrown);
    }
  }
    
  /**
   * Testing the reverseFirstK(int k) method
   */
  public void testReverseFirstK() {
    LinkedList<String> list = new LinkedList<String>();
    list.addToFront("abc");
    list.addToFront("efg");
    list.addToFront("mns");
    list.addToFront("xyz");
    list.reverseFirstK(0);
    // Case 1: Test if the method correctly reverses first 0 element of the list
    assertEquals(true, list.getFirstNode().getElement().equals("xyz"));
    assertEquals(true, list.getFirstNode().getNext().getElement().equals("mns"));
    assertEquals(true, list.getFirstNode().getNext().getNext().getElement().equals("efg"));
    assertEquals(true, list.getFirstNode().getNext().getNext().getNext().getElement().equals("abc"));
    // Case 2: Test if the method correctly reverses first 1 element of the list
    list.reverseFirstK(1);
    assertEquals(true, list.getFirstNode().getElement().equals("xyz"));
    assertEquals(true, list.getFirstNode().getNext().getElement().equals("mns"));
    assertEquals(true, list.getFirstNode().getNext().getNext().getElement().equals("efg"));
    assertEquals(true, list.getFirstNode().getNext().getNext().getNext().getElement().equals("abc"));
    // Case 3: Test if the method correctly reverses first 3 elements of the list
    list.reverseFirstK(3);
    assertEquals(true, list.getFirstNode().getElement().equals("efg"));
    assertEquals(true, list.getFirstNode().getNext().getElement().equals("mns"));
    assertEquals(true, list.getFirstNode().getNext().getNext().getElement().equals("xyz"));
    assertEquals(true, list.getFirstNode().getNext().getNext().getNext().getElement().equals("abc"));
  }
  /**
   * Case 4: Test if moving a node in an empty list will throw a No Such Element Exception:
   */
  public void testReverseFirstKException() {
    boolean thrown = false;
    try {
      LinkedList<String> list = new LinkedList<String>();
      list.reverseFirstK(2);
    }
    catch (NoSuchElementException e) {
      thrown = true;
    assertTrue(thrown);
    }
  } 
  
  /**
   * Testing the getNodeFromList() method
   */   
  public void testGetNodeFromList() {
    LinkedList<String> list = new LinkedList<String>();
    list.addToFront("abc");
    list.addToFront("efg");
    list.addToFront("mns");
    list.addToFront("xyz");
    // Case 1: Test to get node at beginning of the list
    assertEquals(true, list.getNodeFromList(0).getElement().equals("xyz"));
    // Case 2: Test to get node at middle of the list
    assertEquals(true, list.getNodeFromList(2).getElement().equals("efg"));
    // Case 3: Test to get node at end of the list
    assertEquals(true, list.getNodeFromList(3).getElement().equals("abc"));
  }
  
  /**
   * Testing the removeNodeFromList() method
   */   
  public void testRemoveNodeFromList() {
    // Case 1: Test to remove node at front of the list
    LinkedList<String> list = new LinkedList<String>();
    list.addToFront("e");
    list.addToFront("d");
    list.addToFront("c");
    list.addToFront("b");
    list.addToFront("a");
    list.removeNodeFromList(0);
    assertEquals(true, list.getFirstNode().getElement().equals("b"));
    assertEquals(true, list.getFirstNode().getNext().getElement().equals("c"));
    assertEquals(true, list.getFirstNode().getNext().getNext().getElement().equals("d"));
    assertEquals(true, list.getFirstNode().getNext().getNext().getNext().getElement().equals("e"));
    // Case 2: Test to remove node at middle of the list
    LinkedList<String> list1 = new LinkedList<String>();
    list1.addToFront("e");
    list1.addToFront("d");
    list1.addToFront("c");
    list1.addToFront("b");
    list1.addToFront("a");
    list1.removeNodeFromList(2);
    assertEquals(true, list1.getFirstNode().getElement().equals("a"));
    assertEquals(true, list1.getFirstNode().getNext().getElement().equals("b"));
    assertEquals(true, list1.getFirstNode().getNext().getNext().getElement().equals("d"));
    assertEquals(true, list1.getFirstNode().getNext().getNext().getNext().getElement().equals("e"));
    // Case 3: Test to remove node at end of the list
    LinkedList<String> list2 = new LinkedList<String>();
    list2.addToFront("e");
    list2.addToFront("d");
    list2.addToFront("c");
    list2.addToFront("b");
    list2.addToFront("a");
    list2.removeNodeFromList(4);
    assertEquals(true, list2.getFirstNode().getElement().equals("a"));
    assertEquals(true, list2.getFirstNode().getNext().getElement().equals("b"));
    assertEquals(true, list2.getFirstNode().getNext().getNext().getElement().equals("c"));
    assertEquals(true, list2.getFirstNode().getNext().getNext().getNext().getElement().equals("d"));
  }
    
  /**
   * Testing the toStringNodeElement() method
   */  
  public void testToStringNodeElement() {
    LinkedList<String> list = new LinkedList<String>();
    list.addToFront("abc");
    list.addToFront("efg");
    list.addToFront("mns");
    list.addToFront("xyz");
    list.addToFront("a");
    list.addToFront("");
    // Case 1: Test with a node that has 0 character
    assertEquals(true, list.toStringNodeElement(list.getFirstNode()).equals(""));
    // Case 2: Test with a node that has 1 character
    assertEquals(true, list.toStringNodeElement(list.getFirstNode().getNext()).equals("a"));
    // Case 3: Test with a node that has more than 1 character
    assertEquals(true, list.toStringNodeElement(list.getFirstNode().getNext().getNext()).equals("xyz"));
  }
  
  /**
   * Testing the checkForExistingElement(T element) method
   */
  public void testCheckForExistingElement() {
    LinkedList<String> list = new LinkedList<String>();
    list.addToFront("abc");
    list.addToFront("efg");
    list.addToFront("mns");
    list.addToFront("xyz");
    // Case 1: Test of checking for the existence of an element at front of list
    assertEquals(true, list.checkForExistingElement("xyz"));
    // Case 2: Test of checking for the existence of an element in middle of list
    assertEquals(true, list.checkForExistingElement("efg"));
    // Case 3: Test of checking for the existence of an element at end of list
    assertEquals(true, list.checkForExistingElement("abc"));
    // Case 4: Test of checking for the existence of an element that is not in list
    assertEquals(false, list.checkForExistingElement("aaa"));
    // Case 5: Test of checking for the existence of one element that is in list
    assertEquals(true, list.checkForExistingElement("abc"));
    // Case 6: Test of checking for the existence of many elements that are in list
    list.addToFront("efg");
    list.addToFront("efg");
    assertEquals(true, list.checkForExistingElement("efg"));
  }
  
  /**
   * Testing the checkForNumberOfExisting(T element) method
   */
  public void testCheckForNumberOfExisting() {
    LinkedList<String> list = new LinkedList<String>();
    list.addToFront("abc");
    list.addToFront("efg");
    list.addToFront("mns");
    list.addToFront("xyz");
    // Case 1: Test of checking for the existence of an element at front of list
    assertEquals(1, list.checkForNumberOfExisting("xyz"));
    // Case 2: Test of checking for the existence of an element in middle of list
    assertEquals(1, list.checkForNumberOfExisting("efg"));
    // Case 3: Test of checking for the existence of an element at end of list
    assertEquals(1, list.checkForNumberOfExisting("abc"));
    // Case 4: Test of checking for the existence of an element that is not in list
    list.addToFront("aaa");
    list.addToFront("bbb");
    assertEquals(0, list.checkForNumberOfExisting("ccc"));
    // Case 5: Test of checking for the existence of one element that is in list
    assertEquals(1, list.checkForNumberOfExisting("abc"));
    // Case 6: Test of checking for the existence of many elements that are in list
    list.addToFront("efg");
    list.addToFront("efg");
    assertEquals(3, list.checkForNumberOfExisting("efg"));
  }
    
 /**
  * Testing as many "testable" methods in the LLNode<T> class:
  */ 
  
  /**
   * Testing the getElement() method
   */
  public void testGetElement() {
    // Case 1: Test if element of node to be got has 0 characters
    LLNode<String> node2 = new LLNode<String>("", null);
    assertEquals(true, node2.getElement().equals(""));
    // Case 2: Test if element of node to be got has 1 character
    LLNode<String> node1 = new LLNode<String>("a", null);
    assertEquals(true, node1.getElement().equals("a"));
    // Case 3: Test if element of node to be got has many characters
    LLNode<String> node = new LLNode<String>("abcxyz", null);
    assertEquals(true, node.getElement().equals("abcxyz"));
  }

  /**
   * Testing the setElement(T element) method
   */
  public void testSetElement() {
    // Case 1: Test if element of node has 0 characters
    LLNode<String> node2 = new LLNode<String>("aaa", null);
    node2.setElement("");
    assertEquals(true, node2.getElement().equals(""));
    // Case 2: Test if element of node has 1 character
    LLNode<String> node1 = new LLNode<String>("aaa", null);
    node1.setElement("a");
    assertEquals(true, node1.getElement().equals("a"));
    // Case 3: Test if element of node has many characters
    LLNode<String> node = new LLNode<String>("abcxyz", null);
    node.setElement("aaaaaa");
    assertEquals(true, node.getElement().equals("aaaaaa"));
  }
  
  /**
   * Testing the getNext() method
   */
  public void testGetNext() {
    // Case 1: Test if element of next node has 0 characters
    LLNode<String> node3 = new LLNode<String>("", null);
    LLNode<String> node2 = new LLNode<String>("aaa", node3);
    assertEquals(true, node2.getNext().getElement().equals(""));
    // Case 2: Test if element of next node has 1 character
    LLNode<String> node5 = new LLNode<String>("a", null);
    LLNode<String> node4 = new LLNode<String>("aaaaaa", node5);
    assertEquals(true, node4.getNext().getElement().equals("a"));
    // Case 3: Test if element of next node has many characters
    LLNode<String> node = new LLNode<String>("aaaaaa", null);
    LLNode<String> node1 = new LLNode<String>("abcxyz", node);
    assertEquals(true, node1.getNext().getElement().equals("aaaaaa"));
  }
  
  /**
   * Testing the setNext() method
   */
  public void testSetNext() {
    // Case 1: Test if the next node to be set has element that has 0 characters
    LLNode<String> node4 = new LLNode<String>("aaa", null);
    LLNode<String> node5 = new LLNode<String>("", null);
    node4.setNext(node5);
    assertEquals(true, node4.getNext().getElement().equals(""));
    // Case 2: Test if the next node to be set has element that has 1 character
    LLNode<String> node3 = new LLNode<String>("", null);
    LLNode<String> node2 = new LLNode<String>("a", null);
    node3.setNext(node2);
    assertEquals(true, node3.getNext().getElement().equals("a"));
    // Case 3: Test if the next node to be set has element that has many characters
    LLNode<String> node = new LLNode<String>("aaaaaa", null);
    LLNode<String> node1 = new LLNode<String>("abcxyz", null);
    node1.setNext(node);
    assertEquals(true, node1.getNext().getElement().equals("aaaaaa"));
  }

 /**
  * Testing as many "testable" methods in the GameMechanics class:
  */   
  
  /**
   * Testing the getNumCards() and setNumCards() methods
   */ 
  public void testSetNumCardsGetNumCards() {
    // Case 1: Test when entered number of cards is valid (between 2 and 40)
    GameMechanics game = new GameMechanics();
    game.setNumCards(10);
    assertEquals(10, game.getNumCards());
    // Case 2: Test when entered number of cards is invalid (not between 2 and 40)
    // The method will automatically set the number of cards to the default value of 20
    GameMechanics game1 = new GameMechanics();
    game.setNumCards(1);
    assertEquals(20, game.getNumCards());
  }
  
  /**
   * Testing the switchTurn() and getTurn() methods
   */ 
  public void testSwitchTurnGetTurn() {
    // Case 1: Test if the current turn is Player 1's turn
    GameMechanics game = new GameMechanics();
    game.switchTurn();
    assertEquals(false, game.getTurn());
    assertEquals(true, (!game.getTurn()));
    // Case 2: Test if the current turn is Player 2's turn
    GameMechanics game1 = new GameMechanics();
    game1.switchTurn();
    game1.switchTurn();
    assertEquals(true, game1.getTurn());
    assertEquals(false, (!game1.getTurn()));
  }
  
  /**
   * Testing the endGame() method
   */ 
  public void testEndGame() {
    GameMechanics game = new GameMechanics();
    // Case 1: Test if player 1 wins
    game.setPlayer1Points(5);
    game.setPlayer2Points(3);
    game.endGame();
    assertEquals("Player 1", game.getWinner());
    // Case 2: Test if player 2 wins
    game.setPlayer1Points(3);
    game.setPlayer2Points(5);
    game.endGame();
    assertEquals("Player 2", game.getWinner());
    // Case 3: Test if the players tie
    game.setPlayer1Points(5);
    game.setPlayer2Points(5);
    game.endGame();
    assertEquals("Tie", game.getWinner());
  }
  
  /**
   * Testing the getPlayer1Points() and setPlayer1Points() method
   */ 
  public void testGetPlayer1PointsSetPlayer1Points() {
    // Case 1: Testing if the getPlayer1Points() and setPlayer1Points() methods behave correctly:
    GameMechanics game = new GameMechanics();
    game.setPlayer1Points(5);
    assertEquals(5, game.getPlayer1Points());
  }
  
  /**
   * Testing the getPlayer2Points() and setPlayer2Points() method
   */ 
  public void testGetPlayer2PointsSetPlayer2Points() {
    // Case 1: Testing if the getPlayer2Points() and setPlayer2Points() methods behave correctly:
    GameMechanics game = new GameMechanics();
    game.setPlayer2Points(3);
    assertEquals(3, game.getPlayer2Points());
  }
   
  
  /* 
   * All methods that are important to the gameplay but not associated with JavaFX gadgets have been tested 
   * with this JUnit Test. Please refer to the testing document for detailed testing information of the
   * remaining methods.
   */
    
     
}