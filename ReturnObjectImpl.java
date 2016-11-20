public class ReturnObjectImpl implements ReturnObject{

    private ErrorMessage errorMessage;
    private Object returnObject;

    //removed to match clarifications document
    // public ReturnObjectImpl(Object returnObject){
    //     this(returnObject, null);
    // }

    // public ReturnObjectImpl(Object returnObject, ErrorMessage errorMessage){        
    //     this.returnObject = returnObject;
    //     this.errorMessage = errorMessage == null ? ErrorMessage.NO_ERROR : errorMessage;        
    // }

    public ReturnObjectImpl(Object returnObject){
        this.returnObject = returnObject;
        this.errorMessage = ErrorMessage.NO_ERROR;
    }

    public ReturnObjectImpl(ErrorMessage errorMessage){
        this.returnObject = null;
        this.errorMessage = errorMessage;
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