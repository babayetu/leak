package ajoo;

public interface Container{ 
  Component getComponent(Object key); 
  Component getComponentOfType(Class type); 
} 