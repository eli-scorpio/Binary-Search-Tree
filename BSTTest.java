import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @version 3.1 09/11/15 11:32:15
 *
 *  @author  Eligijus Skersonas
 */

@RunWith(JUnit4.class)
public class BSTTest
{
  
  
	@Test
	public void testIsEmpty() {
		//test empty BST
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		assertTrue("Expected value -> true", bst.isEmpty());
		
		//test on BST with 1 node
		bst.put(0, 1);
		assertFalse("Expected value -> false", bst.isEmpty());
		
		//test on BST with more than 1 node
		bst.put(1, 2);
		assertFalse("Expected value -> false", bst.isEmpty());
	}

	@Test
	public void testSize() {
		//test empty BST
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		assertEquals("Expected value -> 0", 0, bst.size());
		
		//test on BST with 1 node
		bst.put(0, 1);
		assertEquals("Expected value -> 1", 1, bst.size());
		
		//test on BST with more than 1 node
		bst.put(1, 2);
		assertEquals("Expected value -> 2", 2, bst.size());
	}

	@Test
	public void testContains() {
		//test empty BST and doesn't contain
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		assertFalse("Expected value -> false", bst.contains(5));
		
		//test non empty BST that contains key
		bst.put(0, 1);
		assertTrue("Expected value -> true", bst.contains(0));
				
		//test non empty BST that doesn't contain key
		assertFalse("Expected value -> false", bst.contains(3));

		
	}

	@Test
	public void testGet() {
		//test empty BST
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		assertEquals("Expected value -> null", null, bst.get(5));
		
		//test non empty BST and contains key
		bst.put(0, 1);
		bst.put(-1, 4);
		bst.put(1, 3);
		assertEquals("Expected value -> 4", (Integer) 4, bst.get(-1));
				
		//test non empty BST and doesn't contain key
		assertEquals("Expected value -> null", null, bst.get(3));
	}

	@Test
	public void testPut() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		
		//test inserting into empty BST
		bst.put(19, 0);
		assertEquals("inserting into empty BST", "(()19())", bst.printKeysInOrder());
		
		//test inserting with a null value 
		bst.put(19, null);
		assertEquals("inserting null value to key into BST (()19()) ", "()", bst.printKeysInOrder());
		
		//test on BST assignment example and changing value of a node
		bst.put(19, 0);
		bst.put(24, 0);
		bst.put(24, 1);
		bst.put(5, 0);
		bst.put(18, 0);
		bst.put(1, 0);
		bst.put(3, 0);
		bst.put(8, 0);
		bst.put(13, 0);
		assertEquals("Example from assignment", "(((()1(()3()))5((()8(()13()))18()))19(()24()))", bst.printKeysInOrder());
	}

	@Test
	public void testHeight() {
		//test empty BST
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		assertEquals("Expected value -> -1", -1, bst.height());
		
		//test BST with one node
		bst.put(0, 1);
		assertEquals("Expected value -> 0", 0, bst.height());
		
		//test BST with size(root.left) < size(root.right)
		bst.put(2, 3);
		assertEquals("Expected value -> 1", 1, bst.height());
		
		//test BST with size(root.left) > size(root.right)
		bst.put(-3, 3);
		bst.put(-1, 2);
		assertEquals("Expected value -> 1", 2, bst.height());
		
		//test BST with size(root.left) = size(root.right) and leftHeight <= rightHeight
		bst.put(3, 3);
		assertEquals("Expected value -> 1", 2, bst.height());
		
		//test BST with size(root.left) = size(root.right) and leftHeight > rightHeight
		bst.put(1, 3);
		bst.put(-2, 0);
		assertEquals("Expected value -> 1", 3, bst.height());
		
		//USING EXAMPLE FROM ASSIGNMENT REPLACING LETTERS WITH THEIR NUMERICAL VALUE
		BST<Integer, Integer> bst2 = new BST<Integer, Integer>();
		bst2.put(19, 0);
		bst2.put(24, 0);
		bst2.put(5, 0);
		bst2.put(18, 0);
		bst2.put(1, 0);
		bst2.put(3, 0);
		bst2.put(8, 0);
		bst2.put(13, 0);
		assertEquals("Example in From Assignment", 4, bst2.height());
	}

	@Test
	public void testMedian() {
		//test empty BST
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		assertEquals("Empty BST", null, bst.median());
		
		//USING EXAMPLE FROM ASSIGNMENT REPLACING LETTERS WITH THEIR NUMERICAL VALUE
		//test BST with one element
		bst.put(19, 0);
		assertEquals("BST with one element", (Integer) 19, bst.median());

		//BST with more than one element
		bst.put(24, 0);
		bst.put(5, 0);
		bst.put(18, 0);
		bst.put(1, 0);
		bst.put(3, 0);
		bst.put(8, 0);
		bst.put(13, 0);
		assertEquals("Example from assignment", (Integer) 8, bst.median());

	}

	@Test
	public void testPrintKeysInOrder() {
		//test empty BST
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		assertEquals("Empty BST", "()", bst.printKeysInOrder());
		
		//test BST with one element
		bst.put(2, 0);
		assertEquals("BST with one element", "(()2())", bst.printKeysInOrder());

		//test example in comments
		bst.put(1, 0);
		bst.put(3, 0);
		bst.put(4, 0);
		assertEquals("Example in Comments", "((()1())2(()3(()4())))", bst.printKeysInOrder());
		
		//test example from assignment
		BST<Integer, Integer> bst2 = new BST<Integer, Integer>();
		bst2.put(19, 0);
		bst2.put(24, 0);
		bst2.put(5, 0);
		bst2.put(18, 0);
		bst2.put(1, 0);
		bst2.put(3, 0);
		bst2.put(8, 0);
		bst2.put(13, 0);
		assertEquals("Example from assignment", "(((()1(()3()))5((()8(()13()))18()))19(()24()))", bst2.printKeysInOrder());
	}

  
  /** <p>Test {@link BST#prettyPrintKeys()}.</p> */
      
 @Test
 public void testPrettyPrint() {
     BST<Integer, Integer> bst = new BST<Integer, Integer>();
     assertEquals("Checking pretty printing of empty tree",
             "-null\n", bst.prettyPrintKeys());
      
                          //  -7
                          //   |-3
                          //   | |-1
                          //   | | |-null
     bst.put(7, 7);       //   | |  -2
     bst.put(8, 8);       //   | |   |-null
     bst.put(3, 3);       //   | |    -null
     bst.put(1, 1);       //   |  -6
     bst.put(2, 2);       //   |   |-4
     bst.put(6, 6);       //   |   | |-null
     bst.put(4, 4);       //   |   |  -5
     bst.put(5, 5);       //   |   |   |-null
                          //   |   |    -null
                          //   |    -null
                          //    -8
                          //     |-null
                          //      -null
     
     String result = 
      "-7\n" +
      " |-3\n" + 
      " | |-1\n" +
      " | | |-null\n" + 
      " | |  -2\n" +
      " | |   |-null\n" +
      " | |    -null\n" +
      " |  -6\n" +
      " |   |-4\n" +
      " |   | |-null\n" +
      " |   |  -5\n" +
      " |   |   |-null\n" +
      " |   |    -null\n" +
      " |    -null\n" +
      "  -8\n" +
      "   |-null\n" +
      "    -null\n";
     assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());
     }

  
     /** <p>Test {@link BST#delete(Comparable)}.</p> */
     @Test
     public void testDelete() {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         bst.delete(1);
         assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());
         
         bst.put(7, 7);   //        _7_
         bst.put(8, 8);   //      /     \
         bst.put(3, 3);   //    _3_      8
         bst.put(1, 1);   //  /     \
         bst.put(2, 2);   // 1       6
         bst.put(6, 6);   //  \     /
         bst.put(4, 4);   //   2   4
         bst.put(5, 5);   //        \
                          //         5
         
         assertEquals("Checking order of constructed tree",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
         
         bst.delete(9);
         assertEquals("Deleting non-existent key",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
 
         bst.delete(8);
         assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());
 
         bst.delete(6);
         assertEquals("Deleting node with single left child",
                 "(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());
 
         bst.delete(3);
         assertEquals("Deleting node with two children",
                 "(((()1())2(()4(()5())))7())", bst.printKeysInOrder());
         
         bst.delete(4);
         assertEquals("Deleting node with single right children",
                 "(((()1())2(()5()))7())", bst.printKeysInOrder());
     }
     
}

