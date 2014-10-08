package ajoo;

public class ValueComponent implements Component{ 
  private final Object v; 
  
  public ValueComponent(Object v) {
	  this.v = v;
  }
  
  public Class getType(){ 
    return v==null?null:v.getClass(); 
  } 
  public Object create(Dependency dep){ 
    return v; 
  } 
  public Class verify(Dependency dep){ 
    return getType(); 
  } 
} 