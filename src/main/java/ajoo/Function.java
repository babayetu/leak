package ajoo;

public interface Function{ 
  Class getReturnType(); 
  Class[] getParameterTypes(); 
  Object call(Object[] args); 
}