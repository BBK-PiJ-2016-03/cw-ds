import org.junit.*;
import static org.junit.Assert.*;

public class StackImplTests{

    private Stack stack = new StackImpl(new LinkedList());

    private void populateStack(){
        //populate Stack
        populateStack(100);
    }

    private void populateStack(int length){
        //populate Stack
        for(int i = 1; i <= length; i++){
            stack.push(i);
        }
    }

    private void clearStack(){
        while(!stack.isEmpty()){
            stack.pop();
        }
    }

    private void initStack(int size){
        initStack();        
        populateStack(size);
    }

    private void initStack(){
        stack = new StackImpl(new LinkedList());
    }

    @Test
    public void topOnEmpty(){
        initStack();
        
        ReturnObject ret = stack.top();

        assertEquals(true, ret.hasError());
        assertEquals(ErrorMessage.EMPTY_STRUCTURE, ret.getError());
        assertEquals(null, ret.getReturnValue());
    }

    @Test
    public void topOnPopulated(){
        initStack(5);

        ReturnObject ret = stack.top();
        assertEquals(5, ret.getReturnValue());

        assertEquals(false, ret.hasError());
        assertEquals(ErrorMessage.NO_ERROR, ret.getError());

        ret = stack.top();
        assertEquals(5, ret.getReturnValue());

        ret = stack.top();
        assertEquals(5, ret.getReturnValue());

        stack.push(6);

        ret = stack.top();
        assertEquals(6, ret.getReturnValue());

        ret = stack.top();
        assertEquals(6, ret.getReturnValue());

    }

    @Test
    public void popOnEmpty(){
        initStack();

        ReturnObject ret = stack.pop();

        assertEquals(true, ret.hasError());
        assertEquals(ErrorMessage.EMPTY_STRUCTURE, ret.getError());
        assertEquals(null, ret.getReturnValue());
    }

    @Test
    public void popOnPopulated(){
        int size = 33;
        initStack(size); 

        ReturnObject ret = stack.pop();
        size--;
        assertEquals(false, ret.hasError());
        assertEquals(ErrorMessage.NO_ERROR, ret.getError());
        assertEquals(size+1, ret.getReturnValue());

        ret = stack.pop();
        size--;

        assertEquals(false, ret.hasError());
        assertEquals(ErrorMessage.NO_ERROR, ret.getError());
        assertEquals(size+1, ret.getReturnValue());
        assertEquals(size, stack.size());

        assertEquals(size, stack.pop().getReturnValue());
        size--;
        assertEquals(size, stack.pop().getReturnValue());
        size--;
        assertEquals(size, stack.pop().getReturnValue());
        size--;
        assertEquals(size, stack.pop().getReturnValue());
        size--;

        clearStack();
        assertEquals(null, stack.pop().getReturnValue());
    }

    @Test
    public void push(){
        initStack();

        //test push
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(9);

        assertEquals(5, stack.size());

        ReturnObject ret = stack.top();
        assertEquals(9, ret.getReturnValue());

        ret = stack.top();
        assertEquals(9, ret.getReturnValue());

    }
}