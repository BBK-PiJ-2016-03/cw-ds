public class ReturnObjectImpl implements ReturnObject{

    private ErrorMessage errorMessage;
    private Object returnObject;

    public ReturnObjectImpl(Object returnObject){
        this(returnObject, null);
    }

    public ReturnObjectImpl(Object returnObject, ErrorMessage errorMessage){        
        this.returnObject = returnObject;
        this.errorMessage = errorMessage == null ? ErrorMessage.NO_ERROR : errorMessage;        
    }

    public boolean hasError(){
        return errorMessage != ErrorMessage.NO_ERROR;
    }

    public ErrorMessage getError(){
        return errorMessage;
    }

    public Object getReturnValue(){
        return errorMessage == ErrorMessage.NO_ERROR ? returnObject : null; 
    }
}