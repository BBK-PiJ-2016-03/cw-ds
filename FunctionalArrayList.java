public class FunctionalArrayList extends ArrayList implements FunctionalList{
    
    public ReturnObject head(){
        if(super.isEmpty())
            return new ReturnObjectImpl(null, ErrorMessage.EMPTY_STRUCTURE);

        Object head = super.get(0);
        return new ReturnObjectImpl(head, ErrorMessage.NO_ERROR);
    }

    public FunctionalList rest(){
        if(super.isEmpty() || super.size() == 1)
            return new FunctionalArrayList();

        FunctionalList restOfList = new FunctionalArrayList();    
        for(int i = 1; i < super.size(); i++){
            restOfList.add(super.get(i));
        }

        return restOfList;
    }
}