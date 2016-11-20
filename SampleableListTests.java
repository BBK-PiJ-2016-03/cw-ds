import org.junit.*;
import static org.junit.Assert.*;

public class SampleableListTests{   

    private SampleableList list = new SampleableListImpl();

    private void initList(){
        list = new SampleableListImpl();
    }

    private void initList(int size){
        initList();        
        populateList(size);
    }

    private void populateList(int length){
        //populate list
        for(int i = 0; i < length; i++){
            list.add(i);
        }
    }
    
    @Test
    public void testSampleableSmall(){
        initList(10);       

        SampleableList sample = list.sample();

        assertEquals("0\n2\n4\n6\n8\n", sample.toString());
    }

    @Test
    public void testSampleableLarge(){
        initList(33);       

        SampleableList sample = list.sample();

        assertEquals("0\n2\n4\n6\n8\n10\n12\n14\n16\n18\n20\n22\n24\n26\n28\n30\n32\n", sample.toString());
    }
}