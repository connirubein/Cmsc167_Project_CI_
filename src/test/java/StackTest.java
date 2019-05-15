import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StackTest {
    Stack stack;

    @Test
    public void push() {
        stack = new Stack(5);
        stack.push('s');
        stack.push('t');
        stack.push('a');
        stack.push('c');
        stack.push('k');
        assertEquals(stack.peek(),'k');
    }

    @Test
    public void pop() {
        stack = new Stack(5);
        stack.push('s');
        stack.push('t');
        stack.push('a');
        stack.push('c');
        stack.push('k');
        assertEquals(stack.pop(),'k');
    }

    @Test
    public void peek() {
        stack = new Stack(5);
        stack.push('s');
        assertEquals(stack.peek(),'s');
    }

    @Test
    public void isEmpty() {
        stack = new Stack(5);
        assertTrue(stack.isEmpty());
    }

}
