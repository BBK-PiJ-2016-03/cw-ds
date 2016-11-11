public class FunctionalArrayList extends ArrayList implements FunctionalList{
    
    public ReturnObject head(){
        if(super.isEmpty())
            return new ReturnObject(null, ErrorMessage.EMPTY_STRUCTURE);

        Object head = super.get(0);
        return new ReturnObject(head, ErrorMessage.NO_ERROR);
    }

    public FunctionalList rest(){
        if(super.isEmpty() || super.size() == 1)
            return new FunctionalList();

        FunctionalList restOfList = new FunctionalList();    
        for(int i = 1; i < super.size(); i++){
            restOfList.add(super.get(i));
        }

        return restOfList;
    }
}