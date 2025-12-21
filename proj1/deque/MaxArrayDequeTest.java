package deque;

import org.junit.Test;
import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {

    /**
     * Tests the max() method with the comparator provided in constructor
     */
    @Test
    public void maxDefaultComparatorTest() {
        // Create a MaxArrayDeque with a natural order comparator for integers
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(Integer::compareTo);
        
        // Test max on empty deque
        assertNull("max() should return null for empty deque", mad.max());
        
        // Add some elements
        mad.addLast(5);
        mad.addLast(2);
        mad.addLast(8);
        mad.addLast(1);
        
        // Test max on non-empty deque
        assertEquals("max() should return 8", Integer.valueOf(8), mad.max());
    }
    
    /**
     * Tests the max(Comparator<T> c) method with a custom comparator
     */
    @Test
    public void maxCustomComparatorTest() {
        MaxArrayDeque<String> mad = new MaxArrayDeque<>(String::compareTo);
        
        // Add some elements
        mad.addLast("hello");
        mad.addLast("world");
        mad.addLast("abc");
        mad.addLast("zebra");
        
        // Test with default comparator (lexicographic order)
        assertEquals("max() should return 'zebra'", "zebra", mad.max());
        
        // Test with custom comparator (string length)
        Comparator<String> lengthComparator = Comparator.comparing(String::length);
        assertEquals("max() with length comparator should return 'hello'", "hello", mad.max(lengthComparator));
    }
    
    /**
     * Tests max() with multiple elements having the same value
     */
    @Test
    public void maxDuplicateValuesTest() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(Integer::compareTo);
        
        mad.addLast(5);
        mad.addLast(2);
        mad.addLast(5);
        mad.addLast(1);
        
        // Should return one of the maximum elements (5)
        assertEquals("max() should return 5", Integer.valueOf(5), mad.max());
    }
    
    /**
     * Tests that MaxArrayDeque still works as a normal ArrayDeque
     */
    @Test
    public void basicDequeOperationsTest() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(Integer::compareTo);
        
        assertTrue("A newly initialized MaxArrayDeque should be empty", mad.isEmpty());
        mad.addFirst(10);
        assertEquals(1, mad.size());
        assertFalse("mad should now contain 1 item", mad.isEmpty());
        
        mad.addLast(20);
        assertEquals(2, mad.size());
        
        assertEquals("removeFirst should return 10", Integer.valueOf(10), mad.removeFirst());
        assertEquals("removeLast should return 20", Integer.valueOf(20), mad.removeLast());
        assertTrue("mad should be empty now", mad.isEmpty());
    }
}