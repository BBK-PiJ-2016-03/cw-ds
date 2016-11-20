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

        assertEquals("0|2|4|6|8|", sample.toString());
    }

    @Test
    public void testSampleableLarge(){
        initList(33);       

        SampleableList sample = list.sample();

        assertEquals("0|2|4|6|8|10|12|14|16|18|20|22|24|26|28|30|32|", sample.toString());
    }
}