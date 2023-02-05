import java.util.NoSuchElementException;
import java.util.Random;

/**
 * A class to represent a linked list of nodes.
 * @author Prof. Harold Connamacher
 * @author David Nguyen
 * @since 12/11/2022
 */
public class LinkedList<T> {
  
  /**
   * the first node of the list, or null if the list is empty 
   */
  private LLNode<T> firstNode;
  
  /**
   * A constructor that creates an initially empty linked list
   */
  public LinkedList() {
    firstNode = null;
  }
  
  /**
   * Returns the first node.
   * @return the first node of the list
   */
  public LLNode<T> getFirstNode() {
    return firstNode;
  }

  /**
   * Changes the front node.
   * @param node  the node that will be the first node of the new linked list
   */
  public void setFirstNode(LLNode<T> node) {
    this.firstNode = node;
  }

  /**
   * Return whether the list is empty
   * @return true if the list is empty
   */
  public boolean isEmpty() {
    return (getFirstNode() == null);
  }
  
  /**
   * Add an element to the front of the linked list
   * @param element the element to be added to linked list
   */
  public void addToFront(T element) {
    setFirstNode(new LLNode<T>(element, getFirstNode()));
  }
  
  /**
   * Removes and returns the element at the front of the linked list
   * @return the element removed from the front of the linked list
   * @throws NoSuchElementException if the list is empty
   */
  public T removeFromFront() {
    if (isEmpty())
      throw new NoSuchElementException();
    else {
      // If statement to deal with the null exception (when the list becomes empty)
      if (getFirstNode().getNext() != null) {
        // Variable for saving and returing the element of the current first node to be removed
        T save = getFirstNode().getElement();
        setFirstNode(getFirstNode().getNext());
        return save;
      }
      else {
        return getFirstNode().getElement();
      }
    }
  }

  /**
   * Returns the length of the linked list
   * @return the number of nodes in the list
   */
  public int length() {
    // A variable representing the length of list
    int count = 0;
    // A variable storing the current node while traversing through the list
    LLNode<T> nodeptr = getFirstNode();
    while (nodeptr != null) {
      count++;
      nodeptr = nodeptr.getNext();
    }
    return count;
  }
  
  /**
   * Moves the first node of the list back n places while leaving the rest of the list nodes in place. 
   * The second node of the list becomes the new first node.
   * If there are not at least n+1 nodes in the list, the method does not change the list.
   * @param n the number of places in the list the user wishes to move the node back
   * @throws NoSuchElementException if the list is empty
   */
  public void moveBack(int n) {
    if (isEmpty())
      throw new NoSuchElementException();
    else {
      if (length() >= (n + 1)) {
        // A variable for keeping track of the last node that is before the insertion of the first node
        LLNode<T> lastNode = getFirstNode();
        // A variable for keeping track of the first node of the list
        LLNode<T> firstNode = getFirstNode();
        // A variable for keeping track of the index of the list
        int index = 0;
        // When the position where the first node is moved back is reached, set first node as second node in list
        // Then connect first node to the node after that, and connect the lastNode node to the first node
        while (index < n) {
          lastNode = lastNode.getNext();
          index++;
        }
        setFirstNode(firstNode.getNext());
        firstNode.setNext(lastNode.getNext());
        lastNode.setNext(firstNode);
      }
    }
  }
  
  /**
   * Moves the first node to become the last node of the list, and the second node of the list is now the new first node 
   * The second node of the list becomes the new first node.
   * @throws NoSuchElementException if the list is empty
   */
  public void moveFirstToLast() {
    if (isEmpty())
      throw new NoSuchElementException();
    else {
      // A variable for keeping track of the first node of the list
      LLNode<T> firstNode = getFirstNode();
      // A variable for keeping track of the last node of the list
      LLNode<T> lastNode = getFirstNode();
      // When the end of list is reached, set first node to be the last node and set next of last node to be null
      // Set first node of list to be the list's second node
      while (lastNode.getNext() != null) {
        lastNode = lastNode.getNext();
      }
      setFirstNode(firstNode.getNext());
      firstNode.setNext(null);
      lastNode.setNext(firstNode);
    }
  }
      
  /**
   * Moves the first node to become the last node of the list, and the second node of the list is now the new first node 
   * The second node of the list becomes the new first node.
   * @throws NoSuchElementException if the list is empty
   */
  public void moveLastToFirst() {
    if (isEmpty())
      throw new NoSuchElementException();
    else {
      // A variable for keeping track of the last node of the list
      LLNode<T> lastNode = getFirstNode();
      // A variable for keeping track of the second to last node of the list
      LLNode<T> secondLastNode= null;
      // When end of list is reached, set second to last node to be the last node, and set first node to be the last node
      // Condition: the next node after the last node of the list is not null
      while (lastNode.getNext() != null) {
        secondLastNode = lastNode;
        lastNode = lastNode.getNext();
      }
      secondLastNode.setNext(null);
      lastNode.setNext(getFirstNode());
      setFirstNode(lastNode);
    }
  }
  
  /**
   * Reverses all the nodes of the list.
   * @throws NoSuchElementException if the list is empty
   */
  public void reverseList() {
    if (isEmpty())
      throw new NoSuchElementException();
    else {
      // A variable for keeping track of the current node of the list while traversing through the list
      LLNode<T> nodeptr = getFirstNode();
      // A variable for keeping track of the reveresed part of the list
      LLNode<T> reversedPart = null;
      // Goes to the end of the list. Then set the next of nodeptr to the reversedPart variable and set the reversedPart
      // variable to the nodeptr in order to reverse the list
      // Finally, set the first node to be the reversedPart variable to complete the reversion
      // Condition: the current node is not null
      while (nodeptr != null) {
        // A variable for keeping track of the next node in queue of the list. Initializes it to the next node of nodeptr
        LLNode<T> next = nodeptr.getNext();
        nodeptr.setNext(reversedPart);
        reversedPart = nodeptr;
        nodeptr = next;
      }
      setFirstNode(reversedPart);
    }
  }
  
  /**
   * Reverses the first k nodes of the linked list. The rest of the list is unchanged.
   * If there are not at least k nodes in the list, the method makes no changes.
   * Achieves this by breaking the list into two parts.
   * @param  k number of nodes the user wishes to reverse
   * @throws NoSuchElementException if the list is empty
   */
  public void reverseFirstK(int k) {
    if (isEmpty())
      throw new NoSuchElementException();
    else {
      if (length() >= k) {
        // A variable for keeping track of current node of the list while traversing through the list
        LLNode<T> nodeptr = getFirstNode();
        // A varibale for keeping track of the index of the list
        int index = 1;
        // When index is equal to k, break the list into two parts and only reverse the first part.
        // Condition: loop index is less than the given "k" input
        while (index < k) {
          nodeptr = nodeptr.getNext();
          index++;
        }
        // Backing up the break point and then splitting the list into two parts
        // A variable storing the current break point of the list
        LLNode<T> breakpoint = nodeptr.getNext();
        nodeptr.setNext(null);
        // Reversing the list until the break point (reversing the first part of the list)
        // A variable keeping track of the current node while traversing the list
        LLNode<T> nodeptr1 = getFirstNode();
        // A variable keeping track of the reversed parts of the list
        LLNode<T> reversedPart = null;
        // Goes to the end of the list. Then set the next of nodeptr to the reversedPart variable and set the reversedPart
        // variable to the nodeptr in order to reverse the list
        // Finally, set the first node to be the reversedPart variable to complete the reversion
        // Condition: the current node is not null
        while (nodeptr1 != null) {
          LLNode<T> next = nodeptr1.getNext();
          nodeptr1.setNext(reversedPart);
          reversedPart = nodeptr1;
          nodeptr1 = next;
        }
        setFirstNode(reversedPart);
        // Joining two parts of the linked list by first traversing the list until null is found
        // A variable for keeping track the current node of the list while traversing the list
        LLNode<T> nodeptr2 = getFirstNode();
        // Traversing until the last node of the list
        // Condition: The next node after the current node is not null
        while (nodeptr2.getNext() != null)
          nodeptr2 = nodeptr2.getNext();
        // Then setNext of nodeptr to the saved break point to join the parts of the list
        nodeptr2.setNext(breakpoint);
      }
    }
  }
  
  /**
   * A method that helps get an element from a given index from list
   * @param index the desired index at which the user wishes to get the node from
   * @return the desired note at the desired index
   */
  public LLNode<T> getNodeFromList(int index) {
    // Variable for keeping track of the current node
    LLNode<T> nodeptr = getFirstNode();
    // Variable storing the current count as the reference to the index variable
    int count = 0;
    // Traversing the list until the given index input is reached to get the node at that index
    // Condition: the count should be different from the index and the next node of the current one is not null
    while (count != index && nodeptr.getNext() != null) {
      nodeptr = nodeptr.getNext();
      count++;
    }
    return nodeptr;
  }
  
  /**
   * A method that helps delete an element from a given index from list
   * @param index the desired index at which the user wishes to delete the node from
   */
  public void removeNodeFromList(int index) {
    // Variable for keeping track of the current node
    LLNode<T> current = getFirstNode();
    // Variable for keeping track of the previous node
    LLNode<T> prev = getFirstNode();
    if (index != 0) {
      // Variable storing the current count as the reference to the index variable
      int count = 0;
      // Traversing the list until the given index input is reached to get the node at that index
      // Condition: the count should be different from the index and the next node of the current one is not null
      while (count != index && current.getNext() != null) {
        // Set prev node to current node and current node to next node of current node
        prev = current;
        current = current.getNext();
        count++;
      }
      // Set previous node's next to the next node of current
      prev.setNext(current.getNext());
      // Set current node's next to null to delete that node from list
      current.setNext(null);
    }
    else {
      setFirstNode(getFirstNode().getNext());
    }
  }
  
  /**
   * A method that helps convert any given node in a linked list into a string
   * @param node the desired node at which the user wishes to convert its element into a string
   * @return the string representation of the element of the node
   */
  public String toStringNodeElement(LLNode<String> node) {
    // A variable of type StringBuilder for creating the resulting string
    StringBuilder output = new StringBuilder();
    // Stores the elements got from the given node
    String element = node.getElement();
    // Appends every single element to the output string to convert the node's element into its string representation
    // Condition: the loop's index is less than the node's element's string length
    for (int i = 0; i < element.length(); i++) {
      // Variable storing the current character at current index
      char c = element.charAt(i);
      // Adds the current character to the output, resulting string
      output.append(c);
    }
    return output.toString();
  }
   
  /**
   * A method that randomly selects elements from a given list
   * Also makes sure that the next node to be randomly selected is not duplicated with any of the selected ones
   * @param list the list that the user wishes to randomly selects elements from
   * @param totalItems the total number of items the user wishes to select from the original list
   * @return a new list that has the randomly selected elements of the given list
   */
  public LinkedList<T> getRandomElements(LinkedList<T> list, int totalItems) {  
    // A variable for storing the random number generated
    Random random = new Random();
    // A new list to store the resulting list
    LinkedList<T> newList = new LinkedList<T>();
    // A variable for storing the length of the current list
    int lengthOfList = length();
    // Randomly picks nodes from a given list and insert them to the front of the new list
    // Condition: the loop's index is less than the total number of items the user wishes to select from original list
    for (int i = 0; i < totalItems; i++) {
      // Variable for storing the random number which is index of list at which the element will be drawn from
      int randomIndex = random.nextInt(lengthOfList);
      // Picks node at random indexes from list and immediately delete that node to avoid duplicates
      newList.addToFront(getNodeFromList(randomIndex).getElement());
      removeNodeFromList(randomIndex);
      lengthOfList--;
    }
    return newList;
  }
  
  // Check if an element has already existed in list
  public boolean checkForExistingElement(T element) {
    // Variable for keeping track of the current node
    LLNode<T> nodeptr = getFirstNode();
    // Stores the boolean output
    boolean output = false;
    // Loop that goes through list to see if any same element has already existed in list, then set the output
    // variable accordingly. Condition: current node in list is not null in order to go to the very end of the list
    while (nodeptr != null) {
      if (nodeptr.getElement().equals(element)) {
        output = true;
      }
      nodeptr = nodeptr.getNext();
    }
    return output;
  }
  
  // Check for how many same elements have already existed in list
  public int checkForNumberOfExisting(T element) {
    // Variable for keeping track of the current node
    LLNode<T> nodeptr = getFirstNode();
    // Stores the output
    int output = 0;
    // Loop that goes through list to see how many of the same element has already existed in list, then set the output
    // variable accordingly. 
    // Condition: current node in list is not null in order to go to the very end of the list
    while (nodeptr != null) {
      if (nodeptr.getElement().equals(element)) {
        output = output + 1;
      }
      nodeptr = nodeptr.getNext();
    }
    return output;
  }  
  
}