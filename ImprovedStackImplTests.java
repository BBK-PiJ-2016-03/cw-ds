import org.junit.*;
import static org.junit.Assert.*;

public class ImprovedStackImplTests{
    @Test
    public void getReversedStackArray(){
        ImprovedStack stack = new ImprovedStackImpl(new ArrayList());

        for(int i = 0; i < 10; i++){
            stack.push(i);
        }
        assertEquals(9, stack.top().getReturnValue());

        stack = stack.reverse();
        assertEquals(0, stack.top().getReturnValue());            
    }

    @Test
    public void getReversedStackLinked(){
        ImprovedStack stack = new ImprovedStackImpl(new LinkedList());

        for(int i = 0; i < 10; i++){
            stack.push(i);
        }
        assertEquals(9, stack.top().getReturnValue());

        stack = stack.reverse();
        assertEquals(0, stack.top().getReturnValue());            
    }

    @Test
    public void removeElements(){
        ImprovedStack stack = new ImprovedStackImpl(new ArrayList());

        int size = 5;

        for(int i = 0; i < size; i++){
            stack.push(i);
        }

        assertEquals(size, stack.size());

        stack.remove(0);
        stack.remove(2);
        stack.remove(4);
        assertEquals(size-3, stack.size());   
        assertEquals(3, stack.top().getReturnValue());            
    }

    @Test
    public void removeElementsPerformance(){
        ImprovedStack stack = new ImprovedStackImpl(new LinkedList());

        int size = 32;

        stack.push(0);

        for(int i = 0; i < size; i++){
            stack.push(i);
        }

        size++;
        

        assertEquals(size, stack.size());

        stack.remove(0);
        size-=2;     
        assertEquals(size, stack.size());  


        stack.remove(0);
        assertEquals(size, stack.size());  

        stack.remove(2);
        size--;     
        assertEquals(size, stack.size());  

        stack.remove(4);   
        size--;     
        assertEquals(size, stack.size());          
    }
}