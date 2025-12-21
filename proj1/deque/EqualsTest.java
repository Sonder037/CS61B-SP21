package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class EqualsTest {

    @Test
    public void testDifferentImplSameElements() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<>();

        // Add same elements to both deques
        arrayDeque.addLast(1);
        arrayDeque.addLast(2);
        arrayDeque.addLast(3);

        linkedListDeque.addLast(1);
        linkedListDeque.addLast(2);
        linkedListDeque.addLast(3);

        // They should be equal
        assertTrue("ArrayDeque and LinkedListDeque with same elements should be equal", 
                   arrayDeque.equals(linkedListDeque));
        assertTrue("ArrayDeque and LinkedListDeque with same elements should be equal", 
                   linkedListDeque.equals(arrayDeque));
    }

    @Test
    public void testDifferentImplDifferentElements() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<>();

        // Add different elements to both deques
        arrayDeque.addLast(1);
        arrayDeque.addLast(2);
        arrayDeque.addLast(3);

        linkedListDeque.addLast(1);
        linkedListDeque.addLast(2);
        linkedListDeque.addLast(4); // Different element

        // They should not be equal
        assertFalse("ArrayDeque and LinkedListDeque with different elements should not be equal", 
                    arrayDeque.equals(linkedListDeque));
        assertFalse("ArrayDeque and LinkedListDeque with different elements should not be equal", 
                    linkedListDeque.equals(arrayDeque));
    }

    @Test
    public void testDifferentImplDifferentSizes() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<>();

        // Add different number of elements to both deques
        arrayDeque.addLast(1);
        arrayDeque.addLast(2);

        linkedListDeque.addLast(1);
        linkedListDeque.addLast(2);
        linkedListDeque.addLast(3);

        // They should not be equal
        assertFalse("ArrayDeque and LinkedListDeque with different sizes should not be equal", 
                    arrayDeque.equals(linkedListDeque));
        assertFalse("ArrayDeque and LinkedListDeque with different sizes should not be equal", 
                    linkedListDeque.equals(arrayDeque));
    }

    @Test
    public void testSameImplEquality() {
        ArrayDeque<Integer> arrayDeque1 = new ArrayDeque<>();
        ArrayDeque<Integer> arrayDeque2 = new ArrayDeque<>();

        // Add same elements to both deques
        arrayDeque1.addLast(1);
        arrayDeque1.addLast(2);
        arrayDeque1.addLast(3);

        arrayDeque2.addLast(1);
        arrayDeque2.addLast(2);
        arrayDeque2.addLast(3);

        // They should be equal
        assertTrue("Two ArrayDeques with same elements should be equal", 
                   arrayDeque1.equals(arrayDeque2));
        assertTrue("Two ArrayDeques with same elements should be equal", 
                   arrayDeque2.equals(arrayDeque1));
    }
}
